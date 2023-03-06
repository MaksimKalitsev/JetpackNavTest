package ua.com.foxminded.jetpacknavtest.data.network.responses

import com.google.gson.annotations.SerializedName

data class TripsResponse(
@SerializedName("response")
val response: List<List<Any>>
)
