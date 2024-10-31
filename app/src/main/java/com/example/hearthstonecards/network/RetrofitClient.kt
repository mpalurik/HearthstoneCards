import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {
    private const val BASE_URL = "https://omgvamp-hearthstone-v1.p.rapidapi.com/"

    val apiService: HearthstoneApiService by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(HearthstoneApiService::class.java)
    }
}
