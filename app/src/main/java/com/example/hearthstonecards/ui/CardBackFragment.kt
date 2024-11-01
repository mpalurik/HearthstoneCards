package com.example.hearthstonecards
import CardBackAdapter
import CardBackViewModel
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.hearthstonecards.databinding.FragmentCardBackBinding

class CardBackFragment : Fragment() {

    private val viewModel: CardBackViewModel by viewModels()
    private lateinit var binding: FragmentCardBackBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCardBackBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = CardBackAdapter()
        binding.recyclerViewCardBacks.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerViewCardBacks.adapter = adapter

        // Show progress bar while loading
        showLoading()

        // Fetch the card backs from the ViewModel
        viewModel.fetchCardBacks("5579e6899emshd67a40cb4cb5bd4p1c3a0cjsnd783888563e3")

        // Observe LiveData for card backs
        viewModel.cardBacks.observe(viewLifecycleOwner) { cardBacks ->
            if (cardBacks.isNullOrEmpty()) {
                showLoading() // Show loading spinner if data is not yet available
            } else {
                hideLoading() // Hide spinner once data is loaded
                adapter.submitList(cardBacks)
            }
        }
    }

    // Helper function to show the loading spinner
    private fun showLoading() {
        binding.loadingSpinner.visibility = View.VISIBLE
        binding.recyclerViewCardBacks.visibility = View.GONE
    }

    // Helper function to hide the loading spinner
    private fun hideLoading() {
        binding.loadingSpinner.visibility = View.GONE
        binding.recyclerViewCardBacks.visibility = View.VISIBLE
    }
}

