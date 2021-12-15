package com.ozimos.academies.ui.detail

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ozimos.academies.data.ModuleEntity
import com.ozimos.academies.databinding.ItemsModuleListBinding

class DetailCourseAdapter : RecyclerView.Adapter<DetailCourseAdapter.ModuleViewHolder>() {

    private val listModules = ArrayList<ModuleEntity>()

    fun setModules(module: List<ModuleEntity>) {
        if (module == null) return
        this.listModules.clear()
        this.listModules.addAll(module)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ModuleViewHolder {
        val moduleInflater =
            ItemsModuleListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ModuleViewHolder(moduleInflater)
    }

    override fun onBindViewHolder(holder: ModuleViewHolder, position: Int) {
        holder.bindData(listModules[position])
    }

    override fun getItemCount(): Int = listModules.size

    class ModuleViewHolder(private val binding: ItemsModuleListBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bindData(item: ModuleEntity) {
            binding.textModuleTitle.text = item.title
        }
    }
}