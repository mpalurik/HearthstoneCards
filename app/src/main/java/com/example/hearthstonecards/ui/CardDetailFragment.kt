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
    ): View? {
        binding = FragmentCardDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Back button functionality
        binding.backButton.setOnClickListener {
            findNavController().navigateUp() // This will navigate back to the previous fragment
        }
        val cardId = arguments?.getString("cardId") ?: return

        // Fetch card details from the API
        viewModel.fetchCardDetail(cardId)

        // Observe changes in card details
        viewModel.card.observe(viewLifecycleOwner) { card ->
            if (card != null) {
                binding.cardNameTextView.text = card.name
                binding.cardHealthView.text = "Health: ${card.health ?: "N/A"}"
                // Bind other card data as needed
                card.img?.let { imgUrl ->
                    Glide.with(this)
                        .load(imgUrl)
                        .into(binding.cardImageView)
                }
            } else {
                Log.e("CardDetailFragment", "Card data is null. Please try again.")
                // Optionally show an error message or placeholder
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
    }

}
