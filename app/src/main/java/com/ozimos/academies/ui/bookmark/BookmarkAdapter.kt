package com.ozimos.academies.ui.bookmark

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.ozimos.academies.R
import com.ozimos.academies.data.CourseEntity
import com.ozimos.academies.databinding.ItemsBookmarkBinding
import com.ozimos.academies.ui.detail.DetailCourseActivity

class BookmarkAdapter(
    private val callback: BookmarkFragmentCallback
) : RecyclerView.Adapter<BookmarkAdapter.MyViewHolder>() {

    private val listCourses = ArrayList<CourseEntity>()

    fun setCourses(courses: List<CourseEntity>?) {
        if (courses == null) return
        this.listCourses.clear()
        this.listCourses.addAll(courses)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MyViewHolder {
        val bookmarkInflater =
            ItemsBookmarkBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(bookmarkInflater)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val course = listCourses[position]
        holder.bindData(course)
    }

    override fun getItemCount(): Int = listCourses.size

    inner class MyViewHolder(private val binding: ItemsBookmarkBinding) :
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
                imgShare.setOnClickListener {
                    callback.onShareClick(item)
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