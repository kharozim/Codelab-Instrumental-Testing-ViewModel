package com.ozimos.academies.ui.reader.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ozimos.academies.data.ModuleEntity
import com.ozimos.academies.databinding.ItemsModuleListCustomBinding

class ModuleListAdapter internal constructor(private val listener: MyAdapterClickListener) :
    RecyclerView.Adapter<ModuleListAdapter.MyViewHolder>() {

    private val listModules = ArrayList<ModuleEntity>()
    internal fun setModules(courses: List<ModuleEntity>) {
        if (courses == null) return
        this.listModules.clear()
        this.listModules.addAll(courses)
    }


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MyViewHolder {
        val moduleInflater =
            ItemsModuleListCustomBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(moduleInflater)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val module = listModules[position]
        holder.bind(module)
        holder.itemView.setOnClickListener {
            listener.onItemClick(
                position,
                listModules[position].moduleId
            )
        }
    }

    override fun getItemCount(): Int = listModules.size

    inner class MyViewHolder(private val binding: ItemsModuleListCustomBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(module: ModuleEntity) {
            binding.textModuleTitle.text = module.title
        }
    }

    internal interface MyAdapterClickListener {
        fun onItemClick(position: Int, moduleId: String)
    }
}