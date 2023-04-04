package cse250.pa3
/**
 * cse250.pa3.Path
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

object Path
{

  /**
   * Compute the cumulative distance of the edges in a path
   */
  def distance(path: Seq[Edge]): Double =
    path.map { _.distance }.sum

  /**
   * An implicit ordering over sequences of edges that places the shortest
   * path first.
   * 
   * Use by <tt>import cse250.pa3.Path.decreasingPathDistanceOrder</tt>
   * Once imported, any PriorityQueue[Seq[Edge]]() will order its paths
   * in decreasing order of path distance.
   */
  implicit val decreasingPathDistanceOrder = new Ordering[Seq[Edge]]() 
  {

    override def compare(x: Seq[Edge], y: Seq[Edge]): Int = 
    {
      // note the flipped x, y for decreasing sort order
      Ordering[Double].compare(distance(y), distance(x))
    }
    
  }


}