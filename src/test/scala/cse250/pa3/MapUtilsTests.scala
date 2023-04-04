package cse250.pa3
/**
 * cse250.pa3.MapUtilsTests
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
 * Submission author
 * UBIT:
 * Person#:
 *
 * Collaborators (include UBIT name of each, comma separated):
 * UBIT:
 */

import org.scalatest.flatspec.AnyFlatSpec

class MapUtilsTests extends AnyFlatSpec {

  val graph = StreetGraph.load("data/buffalo_map.xml")

  behavior of "MapUtilsTests"

  it must "load data correctly" in {
    assert(graph.intersections.size == 282843)
    assert(graph.edges.size == 38636)
  }
}