package cse250.pa3
/**
 * cse250.pa3.Edge
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

object MapUtils
{

  /**
   * Compute the outgoing links from each intersection in the graph.
   * @param    graph     The intersections and links for a map
   * @return             A lookup table of all of the outgoing edges for each intersection.
   * 
   * This function should run in O(graph.edges.size)
   */
  def computeOutgoingEdges(
    graph: StreetGraph
  ): mutable.Map[String, mutable.Seq[Edge]] =
  {
    ???
  }

  /**
   * Compute the path between two intersections that passes through the fewest intersections.
   * @param    graph         The intersections and links for a map
   * @param    outgoingEdges A lookup table of all of the outgoing edges for each intersection.
   * @param    from          The ID of the intersection to route from
   * @param    to            The ID of the intersection to route to
   * @return                 The shortest path needed to get from [[from]] to [[to]]
   * 
   * This function should run in O(graph.edges.size + graph.intersections.size)
    */
  def pathWithFewestIntersections(
    graph: StreetGraph,
    outgoingEdges: mutable.Map[String, mutable.Seq[Edge]],
    from: String,
    to: String
  ): List[Edge] =
  {
    ???
  }

  /**
   * Compute the path between two intersections that passes through the fewest intersections.
   * @param    graph         The intersections and links for a map
   * @param    outgoingEdges A lookup table of all of the outgoing edges for each intersection.
   * @param    from          The ID of the intersection to route from
   * @param    to            The ID of the intersection to route to
   * @return                 The shortest path needed to get from [[from]] to [[to]]
   * 
   * This function should run in O(graph.edges.size * log(graph.intersections.size))
   */
  def pathWithShortestDistance(
    graph: StreetGraph,
    outgoingEdges: mutable.Map[String, mutable.Seq[Edge]],
    from: String,
    to: String
  ): List[Edge] =
  {
    ???
  }

  /**
   * Generate a human-readable name for an intersection
   */
  def nameOfIntersection(id: String, outgoingEdges: mutable.Map[String, mutable.Seq[Edge]]): String =
  {
    id + " @ " + (
      outgoingEdges(id).map { _.streetName }.toSet.toSeq match {
        case Seq() => "Empty Intersection"
        case Seq(a) => s"${a}"
        case Seq(a, b) => s"${a} and ${b}"
        case other     => s"${other.drop(1).mkString(", ")}, and ${other.head}"
      }
    )

  }
}