package cse250.pa3
/**
 * cse250.pa3.Location
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

/**
 * A location given as a latitude/longitude pair.  Assumes latitude/longitude are 
 * given in degrees
 */
case class Intersection(
  id: String,
  latitude: Double,
  longitude: Double,
)
{
  /**
   * Compute the distance (in kilometers) to the other location
   * 
   * Based on http://edwilliams.org/avform147.htm
   */
  def distanceTo(other: Intersection): Double = 
  {
    // convert units to radians (which is what [[Math]] expects)
    val lat1 = this.latitude   * (Math.PI / 180)
    val lon1 = this.longitude  * (Math.PI / 180)
    val lat2 = other.latitude  * (Math.PI / 180)
    val lon2 = other.longitude * (Math.PI / 180)

    val distanceInRadians = // in radians
      2 * Math.asin(
        Math.sqrt(
          Math.pow( Math.sin((lat1-lat2)/2), 2 )
          + Math.cos(lat1) * Math.cos(lat2)
            * Math.pow( Math.sin((lon1-lon2)/2), 2)
        )
      )

    // Assume a perfectly spherical cow that says...
    //  _________________________________________
    // < The earth's radius is about 6371.009 km >
    //  -----------------------------------------
    //         \   ^__^
    //          \  (oo)\_______
    //             (__)\       )\/\
    //                 ||----w |
    //                 ||     ||
    // So the circumference is 2π * r, which means that
    // 2π radians = 2πr, or...
    // 1 radian ≈ 6371.009 km

    val distanceInKM: Double =
      distanceInRadians * 6371.009

    return distanceInKM
  }

}