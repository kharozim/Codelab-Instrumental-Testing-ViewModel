package com.ozimos.academies.ui.academy

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.ozimos.academies.R
import com.ozimos.academies.data.CourseEntity
import com.ozimos.academies.databinding.ItemsAcademyBinding
import com.ozimos.academies.ui.detail.DetailCourseActivity

class AcademyAdapter : RecyclerView.Adapter<AcademyAdapter.AcademyViewHolder>() {

    private var listCourse = ArrayList<CourseEntity>()

    fun setCourse(courses: List<CourseEntity>?) {
        if (courses == null) return
        this.listCourse.clear()
        this.listCourse.addAll(courses)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AcademyViewHolder {
        val academyInflater =
            ItemsAcademyBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return AcademyViewHolder(academyInflater)
    }

    override fun onBindViewHolder(holder: AcademyViewHolder, position: Int) {
        holder.bindData(listCourse[position])
    }

    override fun getItemCount(): Int = listCourse.size

    class AcademyViewHolder(private val binding: ItemsAcademyBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bindData(item: CourseEntity) {
            with(binding) {
                tvItemTitle.text = item.title
                tvItemDate.text =
                    itemView.resources.getString(R.string.deadline_date, item.deadline)
                itemView.setOnClickListener {
                    val intent = Intent(itemView.context, DetailCourseActivity::class.java)
                    intent.putExtra(DetailCourseActivity.EXTRA_COURSE, item.courseId)
                    itemView.context.startActivity(intent)
                }
                Glide.with(itemView.context)
                    .load(item.imagePath)
                    .apply(
                        RequestOptions.placeholderOf(R.drawable.ic_loading)
                            .error(R.drawable.ic_error)
                    )
                    .into(imgPoster)
            }
        }
    }
}