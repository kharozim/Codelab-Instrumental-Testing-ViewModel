package com.ozimos.academies.ui.bookmark

import com.ozimos.academies.data.CourseEntity

interface BookmarkFragmentCallback {
    fun onShareClick(course: CourseEntity)
}
