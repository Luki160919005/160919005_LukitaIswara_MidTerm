package com.example.a160919005_lukitaiswara_midterm.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.a160919005_lukitaiswara_midterm.R
import com.example.a160919005_lukitaiswara_midterm.model.Review

import com.example.a160919005_lukitaiswara_midterm.util.loadImage2
import kotlinx.android.synthetic.main.book_list_item.view.*
import kotlinx.android.synthetic.main.fragment_review_list_item.view.*

class ReviewListAdapter(val reviewList:ArrayList<Review>):RecyclerView.Adapter<ReviewListAdapter.ReviewViewHolder>()  {

    class ReviewViewHolder(var view: View): RecyclerView.ViewHolder(view)

    fun updateBookList(newBookList:List<Review>){
        reviewList.clear()
        reviewList.addAll(newBookList)
        notifyDataSetChanged()
    }

    fun updateBookListLike(newBookList:List<Review>){
        reviewList.clear()

        reviewList.addAll(newBookList)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReviewViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.fragment_review_list_item, parent, false)
        return ReviewViewHolder(view)
    }

    override fun onBindViewHolder(holder: ReviewViewHolder, position: Int) {
        holder.view.textReviewName.setText(reviewList[position].myUser)
        holder.view.textReviewDescription.setText(reviewList[position].reviewInfo)
        holder.view.imageView3.loadImage2(reviewList[position].profImage.toString())


    }

    override fun getItemCount(): Int {
        return reviewList.size
    }
}