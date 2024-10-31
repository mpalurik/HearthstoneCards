import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header

interface HearthstoneApiService {
    @GET("cardbacks")
    suspend fun getCardBacks(
        @Header("X-RapidAPI-Key") apiKey: String
    ): Response<List<CardBack>>
}
