package com.assesment.mvvmrecyclerview.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.assesment.mvvmrecyclerview.data.model.User
import com.assesment.mvvmrecyclerview.databinding.ItemUserBinding

class HomeRecyclerViewAdapter : RecyclerView.Adapter<HomeRecyclerViewAdapter.DataViewHolder>() {

    var item = listOf<User>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }


    inner class DataViewHolder(val binding: ItemUserBinding) :
        RecyclerView.ViewHolder(binding.root)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataViewHolder {
        val binding = ItemUserBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)

        return DataViewHolder(binding)
    }


    override fun getItemCount(): Int = item.size

    override fun onBindViewHolder(holder: DataViewHolder, position: Int) {

        with(holder) {
            with(item[position]) {

                binding.textViewName.text = this.name
                binding.textViewEmail.text = this.email
            //  binding.imageViewUser.loadImage(this.avatar)

            }
        }
    }

}
