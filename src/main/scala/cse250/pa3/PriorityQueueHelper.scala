package cse250.pa3
/**
 * cse250.pa3.PriorityQueueHelper
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
 * DO NOT MODIFY THIS FILE
 */

import scala.collection.mutable.PriorityQueue
import math.Ordering

object PriorityQueueHelper
{
  def create[T](c: (T, T) => Int): PriorityQueue[T] =
  {
    PriorityQueue.empty[T](
      new Ordering[T] {
        def compare(a: T, b: T): Int = c(a, b)
      }
    )
  }
}