package com.ozimos.academies.ui.detail

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.ui.AppBarConfiguration
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.ozimos.academies.R
import com.ozimos.academies.data.CourseEntity
import com.ozimos.academies.databinding.ActivityDetailCourseBinding
import com.ozimos.academies.databinding.ContentDetailCourseBinding
import com.ozimos.academies.ui.reader.CourseReaderActivity
import com.ozimos.academies.utils.DataDummy

class DetailCourseActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var contentBinding: ContentDetailCourseBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityDetailCourseBinding.inflate(layoutInflater)
        contentBinding = binding.detailContent
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayShowHomeEnabled(true)


        val adapter = DetailCourseAdapter()
        val extras = intent.extras
        if (extras != null) {
            val courseId = extras.getString(EXTRA_COURSE) ?: "0"
            val modules = DataDummy.generateDummyModules(courseId)
            adapter.setModules(modules)
            for (course in DataDummy.generateDummyCourses()) {
                if (course.courseId == courseId) {
                    populateCourse(course)
                }
            }
        }

        with(contentBinding.rvModule) {
            isNestedScrollingEnabled = false
            layoutManager = LinearLayoutManager(this@DetailCourseActivity)
            setHasFixedSize(true)
            this.adapter = adapter
            val dividerItemDecoration =
                DividerItemDecoration(this.context, DividerItemDecoration.VERTICAL)
            addItemDecoration(dividerItemDecoration)
        }

    }

    private fun populateCourse(courseEntity: CourseEntity) {
        contentBinding.textTitle.text = courseEntity.title
        contentBinding.textDescription.text = courseEntity.description
        contentBinding.textDate.text =
            resources.getString(R.string.deadline_date, courseEntity.deadline)

        Glide.with(this)
            .load(courseEntity.imagePath)
            .transform(RoundedCorners(20))
            .apply(
                RequestOptions.placeholderOf(R.drawable.ic_loading)
                    .error(R.drawable.ic_error)
            )
            .into(contentBinding.ivPoster)

        contentBinding.btnStart.setOnClickListener {
            val intent = Intent(this, CourseReaderActivity::class.java)
            intent.putExtra(CourseReaderActivity.EXTRA_COURSE_ID, courseEntity.courseId)
            startActivity(intent)
        }
    }

    companion object {
        const val EXTRA_COURSE = "EXTRA_COURSE"
    }
}