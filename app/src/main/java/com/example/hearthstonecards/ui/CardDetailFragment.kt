package com.example.hearthstonecards
import CardDetailViewModel
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.example.hearthstonecards.databinding.FragmentCardDetailBinding

class CardDetailFragment : Fragment() {

    private lateinit var binding: FragmentCardDetailBinding
    private val viewModel: CardDetailViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCardDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Back button functionality
        binding.backButton.setOnClickListener {
            findNavController().navigateUp() // Navigate back
        }

        // Show loading spinner initially
        showLoading()

        // Get the cardId from arguments
        val cardId = arguments?.getString("cardId") ?: return

        // Fetch card details from the API
        viewModel.fetchCardDetail(cardId)

        // Observe changes in card details
        viewModel.card.observe(viewLifecycleOwner) { card ->
            if (card != null) {
                // Update the UI with card details
                binding.cardNameTextView.text = card.name

                binding.cardHealthView.text = "Health: ${card.health ?: "N/A"}"
                binding.cardCostView.text = "Cost: ${card.cost ?: "N/A"}"
                binding.cardArmourView.text = "Armour: ${card.armor ?: "N/A"}"
                binding.cardAttackView.text = "Attack: ${card.attack ?: "N/A"}"
                binding.cardTypeView.text = "Type: ${card.type}"
                binding.cardRarityView.text = "Rarity: ${card.rarity ?: "N/A"}"
                binding.cardPlayerClassView.text = "Player class: ${card.playerClass ?: "N/A"}"
                binding.cardSetView.text = "Card set: ${card.cardSet}"
                binding.cardFactionView.text = "Faction: ${card.faction ?: "N/A"}"
                binding.cardArtistView.text = "Artist: ${card.artist ?: "N/A"}"

                // Load card image using Glide
                card.img?.let { imgUrl ->
                    Glide.with(this)
                        .load(imgUrl)
                        .into(binding.cardImageView)
                }

                // Hide loading spinner and show the content
                hideLoading()

            } else {
                Log.e("CardDetailFragment", "Card data is null. Please try again.")
                // Optionally handle the error case (e.g., show an error message)
                hideLoading()
            }
        }
    }

    private fun showLoading() {
        // Show loading spinner and hide the card details
        binding.loadingSpinner.visibility = View.VISIBLE
        binding.cardImageView.visibility = View.GONE
        binding.cardNameTextView.visibility = View.GONE
        binding.backButton.visibility = View.GONE
        binding.cardHealthView.visibility = View.GONE
        binding.cardCostView.visibility = View.GONE
        binding.cardArmourView.visibility = View.GONE
        binding.cardAttackView.visibility = View.GONE
        binding.cardTypeView.visibility = View.GONE
        binding.cardRarityView.visibility = View.GONE
        binding.cardPlayerClassView.visibility = View.GONE
        binding.cardSetView.visibility = View.GONE
        binding.cardFactionView.visibility = View.GONE
        binding.cardArtistView.visibility = View.GONE
    }

    private fun hideLoading() {
        // Hide loading spinner and show the card details
        binding.loadingSpinner.visibility = View.GONE
        binding.cardImageView.visibility = View.VISIBLE
        binding.cardNameTextView.visibility = View.VISIBLE
        binding.backButton.visibility = View.VISIBLE
        binding.cardHealthView.visibility = View.VISIBLE
        binding.cardCostView.visibility = View.VISIBLE
        binding.cardArmourView.visibility = View.VISIBLE
        binding.cardAttackView.visibility = View.VISIBLE
        binding.cardTypeView.visibility = View.VISIBLE
        binding.cardRarityView.visibility = View.VISIBLE
        binding.cardPlayerClassView.visibility = View.VISIBLE
        binding.cardSetView.visibility = View.VISIBLE
        binding.cardFactionView.visibility = View.VISIBLE
        binding.cardArtistView.visibility = View.VISIBLE
    }
}


        /*
        val cardId = arguments?.getString("cardId") ?: return

        viewModel.fetchCardDetail(cardId)

        // Observe the LiveData for card details
        viewModel.card.observe(viewLifecycleOwner) { card ->
            if (card != null) {
                binding.cardNameTextView.text = card.name
                binding.cardHealthView.text = "Health: ${card.health ?: "N/A"}"

                // If the card has an image URL, load it using a library like Glide or Coil
                card.img?.let { imgUrl ->
                    Glide.with(this)
                        .load(imgUrl)
                        .into(binding.cardImageView)
                }

                // Handle other properties such as cardSet, rarity, etc.
            }
        }


        */

