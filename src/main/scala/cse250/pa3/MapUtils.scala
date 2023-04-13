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
    var mapp: mutable.Map[String, mutable.Seq[Edge]]=mutable.Map()
      for(x<-edges){
       var nn =mapp.getOrElse(x.from.id,1)
        if(nn!=1){
          mapp+=(x.from.id-> (mapp(x.from.id):+x))   //mutable.Seq(mapp(x.from.id).toSeq,x))
        }else(

        mapp+=(x.from.id->mutable.Seq(x))

          )

      }
   /* while(n<interlen){
      var lis:  mutable.Seq[Edge]= mutable.Seq()
      var i: Int=0
    while(i<ed){
     if(edges(i).from.id==inter(n)){
       lis= lis :+(edges(i))
     }
      i+=1
    }
      if(!lis.isEmpty) {
        mapp += (inter(n) -> lis)
      }
      n+=1
  }*/

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
    def computeOutgoingEdgess(graph: StreetGraph): mutable.Map[String, mutable.Queue[Edge]] = {
    val edges = graph.edges
    var mapp: mutable.Map[String, mutable.Queue[Edge]] = mutable.Map()
    for (x <- edges) {
      var nn = mapp.getOrElse(x.from.id, 1)
      if (nn != 1) {
        mapp += (x.from.id -> (mapp(x.from.id) :+ x)) //mutable.Seq(mapp(x.from.id).toSeq,x))
      } else (

        mapp += (x.from.id -> mutable.Queue(x))

        )

    }
      mapp
    }

var edges =computeOutgoingEdgess(graph)


    def mybfs(graph: StreetGraph, start: String): List[Edge] = {


     listofedge=listofedge.empty
     var explored: String = start

      var toExplore: mutable.Queue[String] = new mutable.Queue[String]()
      toExplore.enqueue(start)


        var nodeToExplore = toExplore.dequeue()
        val check = outgoingEdges
      var fedge: mutable.Seq[Edge] = edges(nodeToExplore)
        //exp=exp+ edges(nodeToExplore)
        while (!edges(nodeToExplore).isEmpty ) {
          fedge= edges(nodeToExplore)


          if (edges(nodeToExplore).nonEmpty) {
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

        }

       return result


    }

  /*    mybfs(graph,from)
    result=listofedge
    while(edges(from).length>=1){
      var pp = edges.getOrElse((edges(from)(0).to.id), 1)
      if (pp != 1) {

        if (edges(edges(from)(0).to.id).isEmpty) {
          edges(from).dequeue()
        }
      } /*else {
        edges(from).dequeue()
      }*/
      if (edges(from).isEmpty) {
        return result
      }
      mybfs(graph,from)
      val ni: Int= listofedge.length-1
      if( (listofedge(ni).to.id==to)&&(listofedge.length < result.length) ) {
        result = listofedge
        if((edges(from).length == 1 )){
          edges(from).dequeue()
        }
      }
      else if (edges(from).length == 1) {
        mybfs(graph, from)
        if ((listofedge.length < result.length)) {
          val n: Int= listofedge.length-1
          if(listofedge(n).to.id==to){
            result = listofedge
        }
        edges(from).dequeue()

      }
    }
    }

    result*/

/*
    def computeOutgoingEdges(graph: StreetGraph): mutable.Map[String, mutable.Queue[Edge]] = {
      val edges = graph.edges
      var mapp: mutable.Map[String, mutable.Queue[Edge]] = mutable.Map()
      for (x <- edges) {
        val nn = mapp.getOrElse(x.from.id, mutable.Queue[Edge]())
        mapp += (x.from.id -> (nn :+ x))
      }
      mapp
    }

    var f=edges("111370576")
    f=edges("111408692")

    /*var edg: mutable.Queue[Edge]=mutable.Queue()
    for(p<-edges.keys){
      while(edges(p).nonEmpty){
      edg.enqueue(edges(p).dequeue())
      }
    }*/

//var ff=edg.contains(computeOutgoingEdgess(graph)("111370576")(0))
    var ft=(computeOutgoingEdgess(graph)("111370576"))
    var ii=0
    var tt= List(computeOutgoingEdgess(graph)(from))
   var t= tt(0).length
*/


    /*var explored: List[Edge] = List(computeOutgoingEdgess(graph)(from)(0))


    def nbfs(){
    for(l<-edg){
      if(explored.head.to ==l.from  /*&& !explored.contains(l)*/){
        explored =  explored :+ l
        //ii+=1

      }
    }
    }

    var n =explored.length
    nbfs()

    def tryy(){
    if(explored.head.to.id!=to){
    while(explored.length!=n){
      n = explored.length
      nbfs()

      if(explored.head.to.id==to){
        return explored
      }
    }
    }else{
      return explored
    }
  }

    while(explored.head.to.id != to && ii<t){
      explored =  computeOutgoingEdgess(graph)(from)(ii) :: explored
      nbfs()
      tryy()

      ii+=1
    }*/





   // var listofedgee: List[mutable.Queue[Edge]] =  List()


    mybfs(graph, from)
    result = listofedge
    while (edges(from).length >= 1) {
      var pp = edges.getOrElse((edges(from)(0).to.id), 1)
      if (pp != 1) {

        if (edges(edges(from)(0).to.id).isEmpty) {
          edges(from).dequeue()
        }
      } /*else {
         edges(from).dequeue()
       }*/
      if (edges(from).isEmpty) {
        return result
      }
      mybfs(graph, from)
      val ni: Int = listofedge.length - 1
      if ((listofedge(ni).to.id == to) && (listofedge.length < result.length)) {
        result = listofedge
        if ((edges(from).length == 1)) {
          edges(from).dequeue()
        }
      }
      else if (edges(from).length == 1) {
        mybfs(graph, from)
        if ((listofedge.length < result.length)) {
          val n: Int = listofedge.length - 1
          if (listofedge(n).to.id == to) {
            result = listofedge
          }
          edges(from).dequeue()

        }
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
  def pathWithShortestDistance(graph: StreetGraph, outgoingEdges: mutable.Map[String, mutable.Seq[Edge]], from: String, to: String): List[Edge] = {

    var i: Int = 0
    var listofedge: List[Edge] = List()
    var result: List[Edge] = List()

  /*  def computeOutgoingEdges(graph: StreetGraph): mutable.Map[String, mutable.PriorityQueue[Seq[Edge]]] = {
      val edges = graph.edges
      var mapp: mutable.Map[String, mutable.PriorityQueue[Seq[Edge]]] = mutable.Map()
      for (x <-  outgoingEdges.keys) {
       // var nn = mapp.getOrElse(x, 1)
       // if (nn != 1) {
          mapp += (x -> mutable.PriorityQueue(outgoingEdges(x).toSeq)(Path.decreasingPathDistanceOrder))
          //mapp += (x -> outgoingEdges(x)) //mutable.Seq(mapp(x.from.id).toSeq,x))
       // }
        /*else (

          mapp += (x -> mutable.PriorityQueue(outgoingEdges(x).toSeq)(Path.decreasingPathDistanceOrder))

          )*/

      }
      mapp
    }



    var edges = computeOutgoingEdges(graph)


        def mybfs(graph: StreetGraph, start: String): List[Edge] = {


          listofedge = listofedge.empty
          var explored: String = start
          var toExplore: mutable.Queue[String] = new mutable.Queue[String]()
          toExplore.enqueue(start)


          var nodeToExplore = toExplore.dequeue()
          val check = outgoingEdges
          var fedge: Seq[Edge] = edges(nodeToExplore).toSeq.head

          while (!edges(nodeToExplore).isEmpty) {
            var ff= edges(nodeToExplore).toSeq
            fedge = edges(nodeToExplore).toSeq(0)


            if (!edges(nodeToExplore).isEmpty) {
              val edge = fedge(i)
              if (edges(nodeToExplore).toSeq(0)(0).from.id != from) {
                edges(nodeToExplore).dequeue()
              }
              toExplore.enqueue(edge.to.id)

              explored = edge.to.id

              listofedge = listofedge :+ (edge)
              nodeToExplore = toExplore.dequeue()

              if (edge.to.id == to) {
                return listofedge
              }
            }
          }

          return result



        }



        var dist: Double=0.0
        def dis(edge: List[Edge]): Double={
          dist=0.0

       for(ed<-edge)(
              dist = dist + ed.distance
        // dist= dist+ ed.from.distanceTo(ed.to)
       )
          return dist
        }


        mybfs(graph, from)
        result = listofedge
        while (edges(from).toSeq(0).length >= 1) {
          var t=edges(from)
          var pp =edges.getOrElse((edges(from).toSeq(0)(0).to.id),1 )
          if(pp!=1 ){

          if (edges(edges(from).toSeq(0)(0).to.id).toSeq.isEmpty) {
            if(result(result.length-1).to==to){
            edges(from).dequeue()
            }else{
              edges(from).dequeue()
              //edges(edges(from).toSeq(0)(0).to.id).dequeue()
            }
          }
          }
           t=edges(from) /*else if (pp==1){
            edges(from).dequeue()
          }*/
          if(edges(from).isEmpty){
            return result
          }
          mybfs(graph, from)
          val ni: Int = listofedge.length - 1

          if ((listofedge(ni).to.id == to) && (dis(listofedge) < dis(result))) {
            result = listofedge
            if ((edges(from).length == 1)) {
              edges(from).dequeue()
            }
          }
          else if (edges(from).length == 1) {
            mybfs(graph, from)
            val ch:Double =dis(listofedge)
            val ch1: Double=dis(result)
            if((ch - ch1)<0 ){
              val n: Int = listofedge.length - 1
              if (listofedge(n).to.id == to) {
                result = listofedge
              }
              edges(from).dequeue()

            }
          }
        }
        result*/

//-------------------------------------------------------------------------------------------------//
    def computeOutgoingEdgess(graph: StreetGraph): mutable.Map[String, mutable.Queue[Edge]] = {
      val edges = graph.edges
      var mapp: mutable.Map[String, mutable.Queue[Edge]] = mutable.Map()
      for (x <- edges) {
        var nn = mapp.getOrElse(x.from.id, 1)
        if (nn != 1) {
          mapp += (x.from.id -> (mapp(x.from.id) :+ x)) //mutable.Seq(mapp(x.from.id).toSeq,x))
        } else (

          mapp += (x.from.id -> mutable.Queue(x))

          )

      }
      mapp
    }

    var edges = computeOutgoingEdgess(graph)
    var exp: Set[Edge]=Set()
    def mybfs(graph: StreetGraph, start: String): List[Edge] = {


      listofedge = listofedge.empty
      var explored: String = start

      var toExplore: mutable.Queue[String] = new mutable.Queue[String]()
      toExplore.enqueue(start)


      var nodeToExplore = toExplore.dequeue()
      val check = outgoingEdges
      var fedge: mutable.Seq[Edge] = edges(nodeToExplore)
      //exp=exp+ edges(nodeToExplore)
      while (  !edges(nodeToExplore).isEmpty) {
        fedge = edges(nodeToExplore)
        if(exp.contains(fedge(0)) && fedge(0).from.id!=from){
          edges(nodeToExplore).dequeue()
        }
        fedge = edges(nodeToExplore)

        if (edges(nodeToExplore).nonEmpty) {
          val edge = fedge(i)
          if (edges(nodeToExplore)(0).from.id != from) {
           // edges(nodeToExplore).dequeue()
          }
          exp=exp+edge
          toExplore.enqueue(edge.to.id)

          explored = edge.to.id

          listofedge = listofedge :+ (edge)
          nodeToExplore = toExplore.dequeue()

          if (edge.to.id == to) {
            return listofedge

          }


        }

      }

      return result


    }


    var dist: Double = 0.0

    def dis(edge: List[Edge]): Double = {
      dist = 0.0

      for (ed <- edge) (
        dist = dist + ed.distance
        // dist= dist+ ed.from.distanceTo(ed.to)
        )
      return dist
    }


    mybfs(graph, from)
    result = listofedge
    while (edges(from).nonEmpty ) {
      var pp = edges.getOrElse((edges(from)(0).to.id), 1)
      if (pp != 1) {

        if (edges(edges(from)(0).to.id).isEmpty) {
          edges(from).dequeue()
          //exp=exp+edges(edges(from)(0).to.id)(0)
        }
      } /*else {
         edges(from).dequeue()
       }*/
      if (edges(from).isEmpty) {
        return result
      }
      mybfs(graph, from)
      val ni: Int = listofedge.length - 1
      if ((listofedge(ni).to.id == to) && (dis(listofedge) < dis(result))) {
        result = listofedge
        if ((edges(from).length == 1)) {
          edges(from).dequeue()
        }
      }
      else if (edges(from).length == 1) {
        mybfs(graph, from)
        if ((dis(listofedge) < dis(result))) {
          val n: Int = listofedge.length - 1
          if (listofedge(n).to.id == to) {
            result = listofedge
            edges(from).dequeue()
          }
         // edges(from).dequeue()

        }
      }
    }

    result



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