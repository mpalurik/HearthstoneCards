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

        // Fetch the card backs from the ViewModel
        viewModel.fetchCardBacks("5579e6899emshd67a40cb4cb5bd4p1c3a0cjsnd783888563e3")
        viewModel.cardBacks.observe(viewLifecycleOwner) { cardBacks ->
            adapter.submitList(cardBacks)
        }
    }
}
