package ua.com.foxminded.jetpacknavtest.data.models

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize
import ua.com.foxminded.jetpacknavtest.data.network.responses.LoginResponse
@Parcelize
data class TestLogin(
    val username: String,
    val key: String,
    val email: String
):Parcelable

@Parcelize
data class LoginInfo (
    val cookie: String,
    val key: String,
    val username: String,
    val group: String,
    val firstName: String,
    val lastName: String,
    val rightIds: List<Int>,
    val prefs: List<LoginResponse.PrefsResponse>,
    val timezone: String,
    val locale: String,
    val email: String,
    val access: List<LoginResponse.PrefsResponse>,
    val localisation: LoginResponse.LocalizationResponse,
    var driver: DriverInfo,
): Parcelable {
    val isFleetManager: Boolean
        get() = run {
            this.access.forEach {
                if (it.name == "SMART_FLEET_MANAGER") return@run true
            }
            return@run false
        }

}

@Parcelize
data class DriverInfo (
    @SerializedName("id")
    val id: String,
    @SerializedName("email")
    val email: String
): Parcelable {
    companion object {
        fun emptyInstance() = DriverInfo("", "")
    }
}