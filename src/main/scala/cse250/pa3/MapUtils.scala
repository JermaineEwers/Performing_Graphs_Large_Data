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
  def computeOutgoingEdges(graph: StreetGraph): mutable.Map[String, mutable.Seq[Edge]] =
  {
   val edges=graph.edges
    val inter=graph.intersections.keys
    var mapp: mutable.Map[String, mutable.Seq[Edge]]=mutable.Map()
    for(key<-inter){
      var lis:  mutable.Seq[Edge]= mutable.Seq()
    for(i<-edges){
     if(i.from.id==key){
       lis= lis :+(i)
     }
    }
     mapp+=(key->lis)
  }

    mapp

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
  def pathWithFewestIntersections(graph: StreetGraph, outgoingEdges: mutable.Map[String, mutable.Seq[Edge]], from: String, to: String): List[Edge] =
  {
    var i:Int=0
    var listofedge: List[Edge]=List()
    var result: List[Edge]=List()
   /* var result:List[Edge]=List()
    var i: Int=0
    var edlist = new mutable.Queue[Edge]()
    var toexplore = new mutable.Queue[Edge]()
    toexplore.enqueue(outgoingEdges(from)(0))
    for(inter<-graph.intersections.keys){
      for(ed<-outgoingEdges(inter)){
     // listofedge=listofedge :+ed
      edlist.enqueue(ed)
      }
    }
    while(!edlist.isEmpty){
      /*if(toexplore(0).to.id==to){
        result=result :+ toexplore(0)
      }*/

      result=result :+ toexplore.dequeue()
    }
*/

    def computeOutgoingEdges(graph: StreetGraph): mutable.Map[String, mutable.Queue[Edge]] = {
      val edges = graph.edges
      val inter = graph.intersections.keys
      var mapp: mutable.Map[String, mutable.Queue[Edge]] = mutable.Map()
      for (key <- inter) {
        var lis: mutable.Queue[Edge] = mutable.Queue()
        for (i <- edges) {
          if (i.from.id == key) {
            lis.enqueue(i)
          }
        }
        mapp += (key -> lis)
      }

      mapp

    }

var edges =computeOutgoingEdges(graph)
//var edges: mutable.Queue[mutable.Map[String, mutable.Seq[Edge]]]=outgoingEdges
   def bfs(graph: StreetGraph, start: String): List[Edge] = {


     listofedge=listofedge.empty
     var explored: String = start
      var toExplore: mutable.Queue[String] = new mutable.Queue[String]()
      toExplore.enqueue(start)

      //var Mapp: Map[Edge, String] = Map()

      //while (toExplore.nonEmpty) {
        var nodeToExplore = toExplore.dequeue()
        val check = outgoingEdges
      var fedge: mutable.Seq[Edge] = edges(nodeToExplore)

        while (!edges(nodeToExplore).isEmpty) {
          fedge= edges(nodeToExplore)




          if (!edges(nodeToExplore).isEmpty) {
            val edge=fedge(i)
            if (edges(nodeToExplore)(0).from.id != from) {
              edges(nodeToExplore).dequeue()
            }
            toExplore.enqueue(edge.to.id)

            explored = edge.to.id

            listofedge = listofedge :+ (edge)
            nodeToExplore = toExplore.dequeue()

            if (edge.to.id == to) {
              return listofedge
              //}
            }


          }
          //if (!explored.contains(edge)) {






        }

       return result

     // }

    }

      bfs(graph,from)
    result=listofedge
    while(edges(from).length>1){

      edges(from).dequeue()
      bfs(graph,from)
      if( (listofedge.reverse(0).to==to)&&(listofedge.length < result.length) ) {
        result = listofedge
      }
      else if (edges(from).length == 1) {

      }
    }



    result
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