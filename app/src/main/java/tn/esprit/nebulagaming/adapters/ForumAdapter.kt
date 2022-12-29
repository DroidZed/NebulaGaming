package tn.esprit.nebulagaming.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import tn.esprit.nebulagaming.R
import tn.esprit.nebulagaming.data.ForumPost
import tn.esprit.nebulagaming.viewholders.ForumViewHolder

class ForumAdapter(
    private val list: MutableList<ForumPost>,
    private val context: Context
) : RecyclerView.Adapter<ForumViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ForumViewHolder =
        ForumViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.layout_post, parent, false)
        )

    override fun onBindViewHolder(holder: ForumViewHolder, position: Int) =
        holder.bind(list[position], context)

    override fun getItemCount(): Int = list.size
}
