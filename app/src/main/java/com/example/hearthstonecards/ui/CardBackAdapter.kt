
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import android.text.style.StyleSpan
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.ui.graphics.Color
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

        fun bind(cardBack: CardBack) { binding.cardBackName.text = cardBack.name
            binding.cardBackDescription.text = cardBack.description
            binding.cardBackHowToGet.text = cardBack.howToGet
                Glide.with(binding.root.context)
                    .load(cardBack.img)
                    .into(binding.cardBackImage)

            if(cardBack.img.isNullOrEmpty()) {
                binding.cardBackImage.visibility = View.GONE
            }
            else {
                binding.cardBackImage.visibility = View.VISIBLE
        }
            if(cardBack.howToGet.isNullOrEmpty()) {
                binding.cardBackHowToGet.visibility = View.GONE
            }
            else {
                binding.cardBackHowToGet.visibility = View.VISIBLE
                val howToGetText = ("How to get: \n")
                val spannableString = SpannableString(howToGetText + cardBack.howToGet)
                spannableString.setSpan(ForegroundColorSpan(android.graphics.Color.GREEN), 0, howToGetText.length, 0)
                binding.cardBackHowToGet.text = spannableString
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
}
