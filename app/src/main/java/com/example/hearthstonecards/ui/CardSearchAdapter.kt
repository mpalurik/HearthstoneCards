import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.hearthstonecards.databinding.ItemCardBinding

class CardSearchAdapter(private val onCardClicked: (CardSearch) -> Unit) :
    ListAdapter<CardSearch, CardSearchAdapter.CardViewHolder>(CardDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardViewHolder {
        val binding = ItemCardBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CardViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CardViewHolder, position: Int) {
        val card = getItem(position)
        holder.bind(card)
    }

    inner class CardViewHolder(private val binding: ItemCardBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(card: CardSearch) {
            binding.cardName.text = card.name
            binding.cardType.text = card.type
            // Set the image if available
            if (!card.img.isNullOrEmpty()) {
                Glide.with(binding.cardImage.context)
                    .load(card.img)
                    .into(binding.cardImage)

            }
            if(card.img.isNullOrEmpty()) {
                binding.cardImage.visibility = View.GONE
            }
            else {
                binding.cardImage.visibility = View.VISIBLE
            }
            // Click listener to trigger card details
            binding.root.setOnClickListener { onCardClicked(card) }
        }
    }

    // DiffUtil callback for efficiently updating the RecyclerView
    class CardDiffCallback : DiffUtil.ItemCallback<CardSearch>() {
        override fun areItemsTheSame(oldItem: CardSearch, newItem: CardSearch): Boolean {
            return oldItem.cardId == newItem.cardId
        }

        override fun areContentsTheSame(oldItem: CardSearch, newItem: CardSearch): Boolean {
            return oldItem == newItem
        }
    }
}