package ua.com.foxminded.jetpacknavtest.data.network.repository

import ua.com.foxminded.jetpacknavtest.data.UserPreferences
import ua.com.foxminded.jetpacknavtest.data.models.Trip
import ua.com.foxminded.jetpacknavtest.data.models.TripInfoPoints
import ua.com.foxminded.jetpacknavtest.data.models.toTrips
import ua.com.foxminded.jetpacknavtest.data.network.Api
import ua.com.foxminded.jetpacknavtest.data.network.requests.CustomReportRequest
import ua.com.foxminded.jetpacknavtest.data.network.responses.TripsResponse

interface ITripsRepository{
    suspend fun historyTrips():Result<List<Trip>>
}

class TripsRepository(private val prefs:UserPreferences, private val api: Api):ITripsRepository {
    override suspend fun historyTrips(): Result<List<Trip>> {
        prefs.loginInfo?.let { loginInfo ->
            val driverGroup = loginInfo.group
            val fromDateTime = "2023-01-01"
            val toDateTime = "2023-01-31"
            val driverId = loginInfo.driver.id
            try {
                val request = CustomReportRequest.getTripsRequest(driverGroup,fromDateTime,toDateTime,driverId)
                val tripsResponse = api.getTrips(request)
                val trips = tripsResponse.response.toTrips()
                return Result.success(trips)
            }catch (ex: Exception) {
                return Result.failure(ex)
            }

        } ?: return Result.failure(IllegalStateException("LoginInfo unavailable"))

        }
    }

