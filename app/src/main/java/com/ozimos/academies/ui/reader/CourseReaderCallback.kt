package com.ozimos.academies.ui.reader

import java.text.FieldPosition

interface CourseReaderCallback {
    fun moveTo(position: Int, moduleId: String)
}