package cse250.pa3
/**
 * cse250.pa3.StreetGraph
 *
 * Copyright 2022 Oliver Kennedy (okennedy@buffalo.edu)
 *           2022 Eric Mikida (epmikida@buffalo.edu)
 * 
 * Based on work 
 * Copyright 2021 Andrew Hughes (ahughes@buffalo.edu)
 *
 * This work is licensed under the Creative Commons
 * Attribution-NonCommercial-ShareAlike 4.0 International License.
 * To view a copy of this license, visit
 * http://creativecommons.org/licenses/by-nc-sa/4.0/.
 *
 */

import scala.collection.mutable
import scala.xml.XML

class StreetGraph
{
  /**
   * A translation from IntersectionID to the location of the intersection.
   */
  val intersections = mutable.Map[String, Intersection]()

  /**
   * A collection of <b>directed</b> edges between intersections.
   */
  val edges = mutable.Buffer[Edge]()

  /**
   * Load an OSM data file
   * @param     file      The path to an OSM XML export file
   */
  def load(file: String) =
  {
    val data = XML.loadFile(file)

    // Intersections are stored in XML elements of type `node`
    for(node <- data \ "node")
    {
      // Extract the Intersection's attributes
      val id  = node \@ "id"
      val lat = node \@ "lat"
      val lon = node \@ "lon"

      // Sanity check
      assert(!intersections.contains(id), s"Double-loading intersection id = $id")

      // Save the location
      intersections(id) = Intersection(id = id, latitude = lat.toDouble, longitude = lon.toDouble)
    }

    // Streets are stored in XML elements of type `way`
    for(way <- (data \ "way"))
    {
      // Metadata for the way is stored in elements of type `tag` with key+value attributes
      val tags = (way \ "tag").map { tag => (tag \@ "k") -> (tag \@ "v") }.toMap

      // Ways of other types than streets exist, but only streets seem to have the 
      // `name_base` and `name_type` attribute.
      if(tags.contains("tiger:name_base") 
          && tags.contains("tiger:name_type"))
      {
        // A full human-readable `name` is not available for all streets, but 
        // if it isn't, then we can concatenate the `name_base` (e.g., "Derutte") and 
        // `name_type` (e.g., "Aly")
        val name = 
          tags.getOrElse("name", 
            tags("tiger:name_base") + " " + tags("tiger:name_type"))

        // The intersections that the street passes through are elements of type `nd` and
        // stored in-order
        val nodes = (way \ "nd").map { node => node \@ "ref" }

        // Sanity check: make sure all of the incident intersections exist.
        for( node <- nodes )
        { 
          assert(intersections.contains(node), s"Missing node $node @ $name") 
        }

        // Assuming the way actually hits SOME intersection...
        if(!nodes.isEmpty)
        {

          // Create edges moving along the edge list
          var from = nodes.head
          for( to <- nodes.drop(1) )
          {
            edges.append( Edge(from = intersections(from), to = intersections(to), streetName = name) )
            from = to
          }

          // As well as in reverse order, but only if it's a two-way street
          if(tags.getOrElse("oneway", "no") != "yes")
          {
            from = nodes.last
            for( to <- nodes.reverse.drop(1) )
            {
              edges.append( Edge(from = intersections(from), to = intersections(to), streetName = name) )
              from = to
            }
          }
        }

      }
    }
  }
}

/**
 * Companion object for [[StreetGraph]]
 */
object StreetGraph
{
  /**
   * Create a StreetGraph by loading an OSM data file
   * @param     file      The path to an OSM XML export file
   */
  def load(file: String): StreetGraph = 
  {
    val graph = new StreetGraph()
    graph.load(file)
    return graph
  }
}