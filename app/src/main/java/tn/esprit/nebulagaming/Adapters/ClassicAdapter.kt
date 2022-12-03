package tn.esprit.nebulagaming.adapters

import androidx.recyclerview.widget.RecyclerView

abstract class ClassicAdapter<T : RecyclerView.ViewHolder, Y>(private val data: MutableList<Y>) :
    RecyclerView.Adapter<T>() {

    open fun getItem(position: Int): Y = data[position]

    open fun add(movie: Y) {
        data.add(movie)
        notifyItemInserted(data.size - 1)
    }

    open fun addAll(moveResults: MutableList<Y>) {
        for (result in moveResults) {
            add(result)
        }
    }

    open fun remove(r: Y) {
        val position: Int = data.indexOf(r)
        if (position > -1) {
            data.removeAt(position)
            notifyItemRemoved(position)
        }
    }

    open fun clear() {
        while (itemCount > 0) {
            remove(getItem(0))
        }
    }

}