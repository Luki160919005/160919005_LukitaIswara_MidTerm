package com.example.a160919005_lukitaiswara_midterm.view

import android.view.View
import android.widget.CompoundButton
import androidx.lifecycle.ViewModelProvider
import com.example.a160919005_lukitaiswara_midterm.model.MyBooks
import com.example.a160919005_lukitaiswara_midterm.model.MyReview

interface ButtonDetailClickListener{
    fun onButtonDetailClick(v: View)
}

interface ButtonUpdate{
    fun onButtonUpdate(v: View)
}

interface ButtonEditListener {
    fun onButtonEdit(v: View)
}

interface ButtonDeleteListener {
    fun onButtonDelete(v: View)
    abstract fun ViewModelProvider(bookListAdapter: BookListAdapter): ViewModelProvider
}

interface ButtonAddReviewListener {
    fun onButtonAddReviewListener(v: View)
}


interface TodoCheckedChangeListerner {
    fun onTodoCheckedChange(cb: CompoundButton, isChecked: Boolean, obj: MyBooks)
}

interface TodoCheckedChangeListernerReview {
    fun onTodoCheckedChange(cb: CompoundButton, isChecked: Boolean, obj: MyReview)
}

interface ButtonCreateNotification{
    fun onButtonCreateNotification(v: View)
}

