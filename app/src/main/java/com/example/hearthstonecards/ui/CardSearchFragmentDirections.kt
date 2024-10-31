package com.example.hearthstonecards

import android.os.Bundle
import androidx.navigation.NavDirections

class CardSearchFragmentDirections private constructor() {

    companion object {
        fun actionCardSearchFragmentToCardDetailFragment(cardId: String): NavDirections =
            ActionOnlyNavDirections(R.id.navigation_card_back, cardId)
    }

    // Nested class to handle action with only an ID
    private class ActionOnlyNavDirections(
        private val destinationId: Int,
        private val cardId: String
    ) : NavDirections {
        override val actionId: Int get() = destinationId // Implement actionId
        override val arguments: Bundle
            get() = Bundle().apply {
                putString("cardId", cardId) // Pass the cardId as a string
            }
    }
}
