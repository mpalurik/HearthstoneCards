import androidx.room.Query
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path

interface HearthstoneApiService {
    @GET("cardbacks")
    suspend fun getCardBacks(
        @Header("X-RapidAPI-Key") apiKey: String
    ): Response<List<CardBack>>

    @GET("cards/search/{cardName}/")
    suspend fun searchCards(
        @Header("X-RapidAPI-Key") apiKey: String,
        @Path("cardName") cardName: String
    ): Response<List<CardSearch>>

    @GET("cards/{cardId}")
    suspend fun getCardDetail(
        @Header("X-RapidAPI-Key") apiKey: String,
        @Path("cardId") cardId: String
    ): Response<List<CardDetail>>

}
