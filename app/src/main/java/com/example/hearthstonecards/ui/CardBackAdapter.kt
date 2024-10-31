import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.hearthstonecards.databinding.ItemCardBackBinding

class CardBackAdapter :
    ListAdapter<CardBack, CardBackAdapter.CardBackViewHolder>(CardBackDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardBackViewHolder {
        val binding = ItemCardBackBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CardBackViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CardBackViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class CardBackViewHolder(private val binding: ItemCardBackBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(cardBack: CardBack) {
            binding.cardBackName.text = cardBack.name
            binding.cardBackDescription.text = cardBack.description
            Glide.with(binding.root.context)
                .load(cardBack.img)
                .into(binding.cardBackImage)
        }
    }
}

class CardBackDiffCallback : DiffUtil.ItemCallback<CardBack>() {
    override fun areItemsTheSame(oldItem: CardBack, newItem: CardBack): Boolean {
        return oldItem.name == newItem.name
    }

    override fun areContentsTheSame(oldItem: CardBack, newItem: CardBack): Boolean {
        return oldItem == newItem
    }
}
