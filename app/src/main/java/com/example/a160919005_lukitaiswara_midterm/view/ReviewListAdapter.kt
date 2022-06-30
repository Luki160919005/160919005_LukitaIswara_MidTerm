package com.example.a160919005_lukitaiswara_midterm.view

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CompoundButton
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.a160919005_lukitaiswara_midterm.R
import com.example.a160919005_lukitaiswara_midterm.databinding.BookListItemBinding
import com.example.a160919005_lukitaiswara_midterm.databinding.FragmentReviewListItemBinding
import com.example.a160919005_lukitaiswara_midterm.model.MyBooks
import com.example.a160919005_lukitaiswara_midterm.model.MyReview
import com.example.a160919005_lukitaiswara_midterm.model.Review

import com.example.a160919005_lukitaiswara_midterm.util.loadImage2
import com.example.a160919005_lukitaiswara_midterm.viewmodel.BookDetailViewModel
import com.example.a160919005_lukitaiswara_midterm.viewmodel.MyReviewViewModel
import kotlinx.android.synthetic.main.book_list_item.view.*
import kotlinx.android.synthetic.main.fragment_review_list_item.view.*



class ReviewListAdapter(val reviewList:ArrayList<MyReview>, val adapterOnClick : (MyReview) -> Unit)
    :RecyclerView.Adapter<ReviewListAdapter.ReviewViewHolder>(),
    TodoCheckedChangeListernerReview {
    class ReviewViewHolder(var view: FragmentReviewListItemBinding): RecyclerView.ViewHolder(view.root)



    override fun getItemCount(): Int {
        return reviewList.size
    }



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReviewListAdapter.ReviewViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        //val view = inflater.inflate(R.layout.fragment_review_list_item, parent, false)
        val view = DataBindingUtil.inflate<FragmentReviewListItemBinding>(inflater,
            R.layout.fragment_review_list_item, parent, false)
        return ReviewViewHolder(view)
    }

    override fun onBindViewHolder(holder: ReviewViewHolder, position: Int) {
        /*
        holder.view.textReviewName.setText(reviewList[position].myUser)
        holder.view.textReviewDescription.setText(reviewList[position].reviewInfo)
        holder.view.imageView3.loadImage2(reviewList[position].profImage.toString())*/
        holder.view.reviews = reviewList[position]


    }
    fun updateReviewList(newReviewList: List<MyReview>){
        reviewList.clear()
        reviewList.addAll(newReviewList)
        notifyDataSetChanged()
    }

    private lateinit var viewModel: MyReviewViewModel

    override fun onTodoCheckedChange(cb: CompoundButton, isChecked: Boolean, obj: MyReview) {
        if(isChecked) {
            adapterOnClick(obj)
        }
    }
}