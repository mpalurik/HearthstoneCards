package com.example.hearthstonecards
import CardSearchAdapter
import CardSearchViewModel
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresExtension
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.hearthstonecards.databinding.FragmentCardSearchBinding
import androidx.navigation.fragment.findNavController


class CardSearchFragment : Fragment() {

    private val viewModel: CardSearchViewModel by viewModels()
    private lateinit var binding: FragmentCardSearchBinding
    private lateinit var adapter: CardSearchAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCardSearchBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Set up the adapter for the RecyclerView
        adapter = CardSearchAdapter { card ->
            findNavController().navigate(
                CardSearchFragmentDirections.actionCardSearchFragmentToCardDetailFragment(card.cardId)
            )
        }
        binding.recyclerViewCards.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerViewCards.adapter = adapter

        // Observe the LiveData from the ViewModel
        viewModel.cards.observe(viewLifecycleOwner) { cards ->
            adapter.submitList(cards)
        }

        // Set up the search functionality
        binding.searchBar.setOnQueryTextListener(object : androidx.appcompat.widget.SearchView.OnQueryTextListener {
            @RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
            override fun onQueryTextSubmit(query: String?): Boolean {
                query?.let {
                    viewModel.searchCards(it) // Trigger search in ViewModel
                }
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                // You can handle text change if needed
                return false
            }
        })
    }
}
