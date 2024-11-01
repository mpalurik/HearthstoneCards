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
        binding.placeholderText.visibility = View.VISIBLE

        // Observe the LiveData for search results and show/hide the progress bar
        viewModel.cards.observe(viewLifecycleOwner) { cards ->
            if (cards.isNullOrEmpty()) {
                showLoading() // Show loading spinner when no cards yet
            } else {
                hideLoading() // Hide spinner and show results
                adapter.submitList(cards)
            }
        }

        // Set up the search functionality
        binding.searchBar.setOnQueryTextListener(object : androidx.appcompat.widget.SearchView.OnQueryTextListener {
            @RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
            override fun onQueryTextSubmit(query: String?): Boolean {
                query?.let {
                    showLoading() // Show loading spinner when search is triggered
                    viewModel.searchCards(it) // Trigger search in ViewModel
                }
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }
        })
/*
        binding.searchBar.setOnSearchClickListener {
            // Expand the search view programmatically to fill the width
            val params = binding.searchBar.layoutParams as ViewGroup.MarginLayoutParams
            params.width = ViewGroup.LayoutParams.MATCH_PARENT
            binding.searchBar.layoutParams = params
            binding.searchBar.requestLayout()
        }

        binding.searchBar.setOnCloseListener {
            // Restore the search view to iconified state and align to the right
            val params = binding.searchBar.layoutParams as ViewGroup.MarginLayoutParams
            params.width = ViewGroup.LayoutParams.WRAP_CONTENT
            binding.searchBar.layoutParams = params
            binding.searchBar.requestLayout()
            false
        }
        */

    }



    private fun showLoading() {
        binding.loadingSpinner.visibility = View.VISIBLE
        binding.recyclerViewCards.visibility = View.GONE
        binding.placeholderText.visibility = View.GONE
    }

    private fun hideLoading() {
        binding.loadingSpinner.visibility = View.GONE
        binding.recyclerViewCards.visibility = View.VISIBLE
    }
}
