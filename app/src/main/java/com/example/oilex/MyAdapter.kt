package com.example.oilex

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item.view.*

data class Word(val word: String, var checked: Boolean)
class MyAdapter(val list: List<Word>, val context: Context) :
    RecyclerView.Adapter<MyAdapter.ViewHolder>() {
    var isHidden = true

    var checkEvent: OnItemCheckListener? = null

    interface OnItemCheckListener {
        fun onCheck(str: String, pos: Int, isChecked: Boolean)
    }

    fun setOnItemCheckListener(listener: OnItemCheckListener) {
        checkEvent = listener
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyAdapter.ViewHolder =
        ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item, parent, false))

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: MyAdapter.ViewHolder, position: Int) {


        holder.itemView.apply {
            pos.text = (position + 1).toString()
            word.text = list[position].word
            checkbox.setOnCheckedChangeListener { _, isChecked ->
                list[position].checked = isChecked
                if (checkEvent != null) {
                    checkEvent!!.onCheck("" + position + " " + word.text, position, isChecked)
                }
            }
            if (!isHidden) {
                checkbox.visibility = View.VISIBLE
                checkbox.isChecked = list[position].checked
            } else {
                list[position].checked = false
                checkbox.visibility = View.GONE
            }
        }
    }

    fun isVisible(flag: Boolean) {
        isHidden = flag
        notifyDataSetChanged()
    }
}