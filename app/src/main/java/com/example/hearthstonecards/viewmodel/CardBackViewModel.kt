import androidx.lifecycle.*
import kotlinx.coroutines.launch
import retrofit2.Response

class CardBackViewModel : ViewModel() {
    private val _cardBacks = MutableLiveData<List<CardBack>>()
    val cardBacks: LiveData<List<CardBack>> get() = _cardBacks

    fun fetchCardBacks(apiKey: String) {
        viewModelScope.launch {
            try {
                val response: Response<List<CardBack>> = RetrofitClient.apiService.getCardBacks(apiKey)
                if (response.isSuccessful) {
                    _cardBacks.postValue(response.body())
                }
            } catch (e: Exception) {
                // Handle errors here
            }
        }
    }
}
