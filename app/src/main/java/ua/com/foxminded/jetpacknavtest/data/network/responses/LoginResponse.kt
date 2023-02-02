package ua.com.foxminded.jetpacknavtest.data.network.responses

import com.google.gson.annotations.SerializedName
import ua.com.foxminded.jetpacknavtest.data.models.LoginInfo

data class LoginResponse(
    @SerializedName("login")
    val login: String,
    @SerializedName("group")
    val group: String,
    @SerializedName("first_name")
    val first_name: String,
    @SerializedName("last_name")
    val last_name: String,
    @SerializedName("right_ids")
    val right_ids: List<Int>,
    @SerializedName("prefs")
    val prefs: List<PrefsResponse>,
    @SerializedName("timezone")
    val timezone: String,
    @SerializedName("locale")
    val locale: String,
    @SerializedName("email")
    val email: String,
    @SerializedName("access")
    val access: List<PrefsResponse>,
    @SerializedName("localisation")
    val localizationResponse: LocalizationResponse,
) {

    fun toLoginInfo(): LoginInfo = TODO("Not implemented")

    data class PrefsResponse(
        @SerializedName("name")
        val name: String,
        @SerializedName("value")
        val value: String
    )

    data class LocalizationResponse(
        @SerializedName("date")
        val date: String,
        @SerializedName("time")
        val time: String,
        @SerializedName("distance")
        val distance: String,
        @SerializedName("firstdayofweek")
        val firstDayOfWeek: Int,
        @SerializedName("speed")
        val speed: String,
        @SerializedName("liquid")
        val liquid: String,
        @SerializedName("consumption")
        val consumptionResponse: ConsumptionResponse
    ) {

        data class ConsumptionResponse(
            @SerializedName("fuel")
            val fuel: String
        )
    }
}

