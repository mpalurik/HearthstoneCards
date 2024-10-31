import android.net.http.HttpException
import android.os.Build
import androidx.annotation.RequiresExtension
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import java.io.IOException

class CardSearchViewModel : ViewModel() {

    private val _cards = MutableLiveData<List<CardSearch>>()
    val cards: LiveData<List<CardSearch>> = _cards

    private val apiKey = "5579e6899emshd67a40cb4cb5bd4p1c3a0cjsnd783888563e3"

    @RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
    fun searchCards(cardName: String) {
        viewModelScope.launch {
            try {
                val response = RetrofitClient.apiService.searchCards(apiKey, cardName)
                if (response.isSuccessful) {
                    response.body()?.let { cardList ->
                        _cards.postValue(cardList) // Now cardList is of type List<CardSearch>
                    } ?: run {
                        // Handle the case where the response body is null
                        _cards.postValue(emptyList()) // or handle appropriately
                    }
                } else {
                    // Handle the error case if needed
                    // Log the error response code or message
                }
            } catch (e: HttpException) {
                e.printStackTrace()
            } catch (e: IOException) {
                e.printStackTrace()
            }
        }
    }
}
