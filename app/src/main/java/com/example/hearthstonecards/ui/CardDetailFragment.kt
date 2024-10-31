package com.example.hearthstonecards
import CardDetailViewModel
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
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

        val cardId = arguments?.getString("cardId") ?: return

        viewModel.fetchCardDetail(cardId)

        viewModel.card.observe(viewLifecycleOwner) { card ->
            binding.cardNameTextView.text = card.name
            binding.cardHealthView.text = "Health: ${card.health}"

        }
    }
}
