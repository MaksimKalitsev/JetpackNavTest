package ua.com.foxminded.jetpacknavtest.data.network.requests

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class CustomReportRequest(
    val report_type: String,
    val format: String,
    val async: Boolean,
    val fields: List<String>,
    val criteria: Criteria,
    val driverFilter: Criteria.DriverFilter,
    val aggregation: List<String>,
    val aggregation_graph: List<Unit>
) : Parcelable {

    @Parcelize
    data class Criteria(
        val data_range: List<String>,
        val target_groups: List<String>,
        val daytime_range: List<String> = listOf("00:00:00+00:00", "23:59:59+00:00"),
        val time_scope: String = "start",
        val week_days: List<Int> = listOf(1, 2, 3, 4, 5, 6, 7),
    ) : Parcelable {
        @Parcelize
        data class VehicleFilter(
            val op: String,
            val vehicles: List<Unit>
        ) : Parcelable

        @Parcelize
        data class DriverFilter(
            val op: String,
            val drivers: List<String>
        ) : Parcelable
    }


    companion object {
        val tripsFields = listOf(
            "kswon_address",
            "kswoff_address",
            "veh_vin",
            "veh_plate",
            "veh_name",
            "veh_brand",
            "veh_model",
            "veh_key",
            "obu_key",
            "kswon_at_datetime",
            "kswoff_at_datetime",
            "duration_s",
            "kswon_addr",
            "kswoff_addr",
            "dist_m",
            "cons_lit100",
            "cons_kwh100",
            "trip_info_privacy",
            "eco_score",
            "co2_gkm",
            "nb_accel",
            "nb_decel",
            "nb_centri",
            "trip_info_points"
        )

        fun getTripsRequest(
            driverGroup: String,
            fromDate: String,
            toDate: String,
            driverId: String
        ): CustomReportRequest {
            val criteria = Criteria(
                data_range = listOf(fromDate, toDate),
                target_groups = listOf(driverGroup),
            )
            return CustomReportRequest(
                report_type = "custom",
                format = "raw_json",
                async = false,
                fields = tripsFields,
                aggregation = listOf("trip"),
                aggregation_graph = listOf(Unit),
                criteria = criteria,
                driverFilter = Criteria.DriverFilter("assigned_identified", listOf(driverId))
            )
        }
    }
}







