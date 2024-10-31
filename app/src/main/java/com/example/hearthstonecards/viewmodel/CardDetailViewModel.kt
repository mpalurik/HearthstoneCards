import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import retrofit2.Response

class CardDetailViewModel(private val apiService: HearthstoneApiService) : ViewModel() {

    private val _card = MutableLiveData<CardDetail>()
    val card: LiveData<CardDetail> = _card

    fun fetchCardDetail(cardId: String) {
        viewModelScope.launch {
            try {
                // Make API call to get card detail by cardId
                val response: Response<CardDetail> = apiService.getCardDetail(apiKey = "5579e6899emshd67a40cb4cb5bd4p1c3a0cjsnd783888563e3", cardId = cardId)
                if (response.isSuccessful) {
                    // Update LiveData with the card detail if successful
                    _card.value = response.body()
                } else {
                    // Handle error case if needed (e.g., log the error or show a message)
                }
            } catch (e: Exception) {
                // Handle exceptions, such as network errors
                // Optionally log the error or update LiveData with an error state
            }
        }
    }
}
