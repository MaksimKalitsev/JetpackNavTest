package ua.com.foxminded.jetpacknavtest.data.models

import android.os.Parcelable
import com.google.gson.Gson
import com.google.gson.JsonArray
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

data class Trip(
    val kswon_address: String?,
    val kswoff_address: String?,
    val veh_plate: String?,
    val veh_brand: String?,
    val veh_model: String?,
    val obu_key: String?,
    val kswon_at_datetime: String,
    val kswoff_at_datetime: String,
    val duration_s: Double?,
    val dist_m: Double?,
    val cons_lit100: Double?,
    val cons_kwh100: Double?,
    val trip_info_privacy: Double?,
    val eco_score: Double?,
    val co2_gkm: Double?,
    val trip_info_points: List<LatLon>
)

@Parcelize
data class LatLon(
    @SerializedName("lats")
    val lat: Double,
    @SerializedName("lngs")
    val lon: Double
) : Parcelable

@Parcelize
data class TripInfoPoints(
    @SerializedName("dates")
    val dates: List<String>,
    @SerializedName("lats")
    val lats: List<Double>,
    @SerializedName("lngs")
    val lngs: List<Double>,
    @SerializedName("meters")
    val meters: List<Int>,
    @SerializedName("reasons")
    val reasons: List<Int>,
    @SerializedName("speeds")
    val speeds: List<Double>,
    @SerializedName("bearings")
    val bearings: List<Int>
) : Parcelable

private fun String.toLatLonList(): List<LatLon> {
    val gson = Gson()
    val tripInfoPoints = gson.fromJson(this, TripInfoPoints::class.java)
    return tripInfoPoints.lats.zip(tripInfoPoints.lngs).map { LatLon(it.first, it.second) }

}

fun List<List<Any>>.toTrips(): List<Trip> {
    val resultingList = mutableListOf<Trip>()
    for (item in this) {
        try {
            val trip = Trip(
                kswon_address = item[0] as? String,
                kswoff_address = item[1] as? String,
                veh_plate = item[2] as? String,
                veh_brand = item[3] as? String,
                veh_model = item[4] as? String,
                obu_key = item[5] as? String,
                kswon_at_datetime = item[6] as String,
                kswoff_at_datetime = item[7] as String,
                duration_s = item[8] as? Double,
                dist_m = item[9] as? Double,
                cons_lit100 = item[10] as? Double,
                cons_kwh100 = item[11] as? Double,
                trip_info_privacy = item[12] as? Double,
                eco_score = item[13] as? Double,
                co2_gkm = item[14] as? Double,
                trip_info_points = (item[15] as? String)?.toLatLonList() ?: listOf()
            )
            resultingList.add(trip)
        } catch (ex: Exception) {
            ex.printStackTrace()
        }
    }
    return resultingList
}








