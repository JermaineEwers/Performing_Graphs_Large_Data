package cse250.pa3
/**
 * cse250.pa3.Main
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

object Main
{


  def main(args: Array[String]): Unit =
  {
    val graph = StreetGraph.load("data/buffalo_map.xml")

    println(s"Loaded ${graph.intersections.size} intersections")
    println(s"Loaded ${graph.edges.size} edges")

    val outgoing = MapUtils.computeOutgoingEdges(graph)

    println(s"${outgoing.size} intersections with outgoing edges")

    {
      val route = MapUtils.pathWithFewestIntersections(graph, outgoing, "111408692", "111370576")
      println(
        MapUtils.nameOfIntersection(route.head.from.id, outgoing)
      )
      println(
        route.tail.map { _.to.id }.map {
          MapUtils.nameOfIntersection(_, outgoing)
        }.mkString("\n")
      )
    }

    println("-------")

    {
      val route = MapUtils.pathWithFewestIntersections(graph, outgoing, "111408692", "111571000")
      println(
        MapUtils.nameOfIntersection(route.head.from.id, outgoing)
      )
      println(
        route.tail.map { _.to.id }.map {
          MapUtils.nameOfIntersection(_, outgoing)
        }.mkString("\n")
      )
    }

    println("-------")

    // Compare what you get to 
    // https://www.bing.com/maps?cp=42.889382%7E-78.877321&lvl=16.3
    {
      val route = MapUtils.pathWithShortestDistance(graph, outgoing, "111408692", "111571000")
      println(
        MapUtils.nameOfIntersection(route.head.from.id, outgoing)
      )
      println(
        route.tail.map { _.to.id }.map {
          MapUtils.nameOfIntersection(_, outgoing)
        }.mkString("\n")
      )
    }
  }
}