package ua.com.foxminded.jetpacknavtest.data.network.responses

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize
import ua.com.foxminded.jetpacknavtest.data.models.DriverInfo
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
    val localizationResponse: LocalizationResponse
) {

    fun toLoginInfo(password : String, cookie : String): LoginInfo = LoginInfo(
        cookie,
        password,
        login,
        group,
        first_name,
        last_name,
        right_ids,
        prefs,
        timezone,
        locale,
        email,
        access,
        localizationResponse,
        DriverInfo.emptyInstance()

    )

    @Parcelize
    data class PrefsResponse(
        @SerializedName("name")
        val name: String,
        @SerializedName("value")
        val value: String
    ): Parcelable

    @Parcelize
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
    ): Parcelable {

        @Parcelize
        data class ConsumptionResponse(
            @SerializedName("fuel")
            val fuel: String
        ):Parcelable
    }
}

