import RetrofitClient.apiService
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import retrofit2.Response

class CardDetailViewModel : ViewModel() {

    private val _card = MutableLiveData<CardDetail?>() // Allow null values to be posted
    val card: LiveData<CardDetail?> = _card

    private val apiKey = "5579e6899emshd67a40cb4cb5bd4p1c3a0cjsnd783888563e3"

    fun fetchCardDetail(cardId: String) {
        viewModelScope.launch {
            try {
                val response: Response<List<CardDetail>> = apiService.getCardDetail(apiKey, cardId) // Change the response to a List<CardDetail>

                // Log the raw response to see if we get anything
                Log.d("CardDetailViewModel", "API Response: ${response.raw()}")

                if (response.isSuccessful) {
                    val cardDetailsList = response.body()

                    if (!cardDetailsList.isNullOrEmpty()) {
                        val cardDetail = cardDetailsList[0] // Get the first item from the list
                        _card.postValue(cardDetail)
                        Log.d("CardDetailViewModel", "Card Detail: $cardDetail")
                    } else {
                        Log.e("CardDetailViewModel", "Card detail list is empty")
                    }
                } else {
                    Log.e("CardDetailViewModel", "API Error: ${response.errorBody()}")
                }
            } catch (e: Exception) {
                Log.e("CardDetailViewModel", "Network Error: ${e.message}")
            }
        }
    }
}
