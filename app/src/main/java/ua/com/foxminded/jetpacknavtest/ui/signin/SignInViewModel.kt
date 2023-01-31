package ua.com.foxminded.jetpacknavtest.ui.signin

import androidx.lifecycle.ViewModel
import com.google.gson.Gson
import com.google.gson.annotations.SerializedName
import kotlinx.coroutines.runBlocking
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.Header
import retrofit2.http.POST

class SignInViewModel : ViewModel() {

}

interface Api {
    @FormUrlEncoded
    @POST("login")
    suspend fun signIn(
        @Field("login") login: String,
        @Field("password") password: String,
        @Header("Content-Type") contentType: String = "application/x-www-form-urlencoded"
    ): Response<LoginResponse>

    @POST("logout")
    suspend fun logOut(
        @Header("Cookie") cookie: String): Response<Unit>

}

fun main() = runBlocking {
    val loggingInterceptor = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
    val client = OkHttpClient.Builder().addInterceptor(loggingInterceptor).build()
    val retrofit = Retrofit.Builder()
        .baseUrl("https://fleet.kuantic.com/")
        .client(client)
        .addConverterFactory(GsonConverterFactory.create(Gson()))
        .build()
    val api: Api = retrofit.create(Api::class.java)

    val signInResponse: Response<LoginResponse> = api.signIn("olegkondratenko-driver", "P@ss4Cloudmade156!")
    val token = signInResponse.headers().get("set-cookie")
    val logoutResponse = token?.let { api.logOut(it) }
    val result = logoutResponse?.headers()?.get("set-cookie")


    println("LOGIN: $signInResponse")
    println("LOGOUT: $result")
}


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

