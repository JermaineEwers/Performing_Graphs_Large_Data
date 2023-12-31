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

import scala.collection.mutable

class MapUtilsTests extends AnyFlatSpec {

  val graph = StreetGraph.load("data/buffalo_map.xml")

  behavior of "MapUtilsTests"

  it must "load data correctly" in {
    assert(graph.intersections.size == 282843)
    assert(graph.edges.size == 38636)
  }

  it must "test1" in {
    var testgraph = new StreetGraph

    val a = Intersection("a", 1, 3)
    val b =  Intersection("b", 3, 3)
    val c =  Intersection("c", 1, 1)
    val d =  Intersection("d", 3, 1)
    testgraph.intersections("a")=a
    testgraph.intersections("b")=b
    testgraph.intersections("c")=c
    testgraph.intersections("d")=d



    // mutable.Map("a"->a,"b"->b,"c"->c,"d"->d)
    val ab = new Edge(a, b, "ab")
    val ac = new Edge(a, c, "ac")
    val bd = new Edge(b, d, "bd")
    val cd = new Edge(c, d, "cd")
    val ad = new Edge(a, d, "ad")

    testgraph.edges.append(ab)
    testgraph.edges.append(ac)
    testgraph.edges.append(bd)
    testgraph.edges.append(cd)
    testgraph.edges.append(ad)


    assert(MapUtils.computeOutgoingEdges(testgraph)("a")== mutable.Seq(ab,ac,ad))
    assert(MapUtils.pathWithFewestIntersections(testgraph,MapUtils.computeOutgoingEdges(testgraph),"a","d")==Seq(ad))
  println(ad.from.distanceTo(ad.to))
    println(ab.distance)
  assert(MapUtils.pathWithShortestDistance(testgraph, MapUtils.computeOutgoingEdges(testgraph), "a", "d") == Seq(ad))

    var testgraph1 = new StreetGraph

    val a1 = Intersection("a", 1, 3)
    val b1 = Intersection("b", 3, 3)
    val c1 = Intersection("c", 1, 1)
    val d1 = Intersection("d", 3, 1)


    testgraph1.intersections("a") = a1
    testgraph1.intersections("b") = b1
    testgraph1.intersections("c") = c1
   testgraph1.intersections("d") = d1
    val ab1 = new Edge(a1, b1, "ab")
    val ac1 = new Edge(a1, c1, "ac")
    val bd1 = new Edge(b1, d1, "bd")
    val cd1 = new Edge(c1, d1, "cd")
    val ad1 = new Edge(a1, d1, "ad")






    testgraph1.edges.append(ab1)
    testgraph1.edges.append(ac1)
    testgraph1.edges.append(bd1)
    testgraph1.edges.append(cd1)

   assert(MapUtils.pathWithFewestIntersections(testgraph1, MapUtils.computeOutgoingEdges(testgraph1), "a", "d") == Seq(ab1,bd1))
  assert(MapUtils.pathWithShortestDistance(testgraph1, MapUtils.computeOutgoingEdges(testgraph1), "a", "d") == Seq(ab1,bd1))

    val e = Intersection("e", 4, 2)
    val f = Intersection("f", 3, 0)
    val g = Intersection("g", 4, 0)

    testgraph1.intersections("e") = e
    testgraph1.intersections("f") = f
    testgraph1.intersections("g") = g

    testgraph1.edges.append(ad1)
    val bg = new Edge(b1, g, "bg")
    val be = new Edge(b1, e, "be")
    val df = new Edge(d1, f, "df")
    val eg = new Edge(e, g, "eg")
    val fg = new Edge(f, g, "fg")
    testgraph1.edges.append(bg)
    testgraph1.edges.append(be)
    testgraph1.edges.append(df)
    testgraph1.edges.append(eg)
    testgraph1.edges.append(fg)
    assert(MapUtils.pathWithFewestIntersections(testgraph1, MapUtils.computeOutgoingEdges(testgraph1), "a", "g") == Seq(ab1, bg))
    assert(MapUtils.pathWithShortestDistance(testgraph1, MapUtils.computeOutgoingEdges(testgraph1), "a", "g") == Seq(ad1, df,fg))



    //println(Intersection("a", 1, 3).distanceTo(Intersection("a", 3, 3)))
    /* println("ab", a.distanceTo(b))
    println("ac", a.distanceTo(c))
    println("cd", c.distanceTo(d))
    println("bd", b.distanceTo(d))
    println("ad", a.distanceTo(d))*/
  }


  it must "test2" in {
    var testgraph = new StreetGraph

    val a = Intersection("a", 1, 3)
    val b = Intersection("b", 3, 3)
    val c = Intersection("c", 4, 3)
    val d = Intersection("d", 5, 3)
    val e = Intersection("e", 4, 5)
    testgraph.intersections("a") = a
    testgraph.intersections("b") = b
    testgraph.intersections("c") = c
    testgraph.intersections("d") = d



    // mutable.Map("a"->a,"b"->b,"c"->c,"d"->d)
    val ab = new Edge(a, b, "ab")

    val bc = new Edge(b, c, "bc")
    val cd = new Edge(c, d, "cd")




    testgraph.edges.append(ab)
    testgraph.edges.append(bc)
    testgraph.edges.append(cd)
    val test: mutable.Map[String, mutable.Seq[Edge]]=mutable.Map("a"->mutable.Seq(ab),"b"->mutable.Seq(bc),"c"->mutable.Seq(cd))
    assert(MapUtils.computeOutgoingEdges(testgraph)==test)


  // println(testgraph.edges(0))
  assert(MapUtils.pathWithFewestIntersections(testgraph,MapUtils.computeOutgoingEdges(testgraph),"a","d") == Seq(ab,bc,cd))


//println("ae",c.distanceTo(d))


  }


  it must "test11" in {
    var testgraph = new StreetGraph

    val a = Intersection("a", 1, 3)
    val b = Intersection("b", 3, 3)
    val c = Intersection("c", 1, 1)
    val d = Intersection("d", 3, 1)
    testgraph.intersections("a") = a
    testgraph.intersections("b") = b
    testgraph.intersections("c") = c
    testgraph.intersections("d") = d


    // mutable.Map("a"->a,"b"->b,"c"->c,"d"->d)
    val ab = new Edge(a, b, "ab")
    val ac = new Edge(a, c, "ac")
    val bd = new Edge(b, d, "bd")
    val cd = new Edge(c, d, "cd")
    val ad = new Edge(a, d, "ad")

    testgraph.edges.append(ab)
    testgraph.edges.append(ac)
    testgraph.edges.append(bd)
    testgraph.edges.append(cd)
    testgraph.edges.append(ad)

    println(MapUtils.computeOutgoingEdges(testgraph)("a"))
    assert(MapUtils.computeOutgoingEdges(testgraph)("a") == mutable.Seq(ab, ac, ad))
   // println(testgraph.edges)
  }


  it must "test3" in {
    var testgraph = new StreetGraph

    val a = Intersection("a", 1, 3)
    val b = Intersection("b", 3, 3)
    val c = Intersection("c", 4, 3)
    val d = Intersection("d", 5, 3)
    val e = Intersection("e", 4, 5)
    testgraph.intersections("a") = a
    testgraph.intersections("e") = e
    testgraph.intersections("b") = b
    testgraph.intersections("c") = c
    testgraph.intersections("d") = d


    // mutable.Map("a"->a,"b"->b,"c"->c,"d"->d)
    val ab = new Edge(a, b, "ab")
    val ae = new Edge(a, e, "ae")
    val ed = new Edge(e, d, "ed")
    val bc = new Edge(b, c, "bc")
    val cd = new Edge(c, d, "cd")


    testgraph.edges.append(ab)
    testgraph.edges.append(ae)
    testgraph.edges.append(ed)
    testgraph.edges.append(bc)
    testgraph.edges.append(cd)







    assert(MapUtils.pathWithShortestDistance(testgraph, MapUtils.computeOutgoingEdges(testgraph), "a", "d") == Seq(ab, bc, cd))


    //println("ae",c.distanceTo(d))


  }



}