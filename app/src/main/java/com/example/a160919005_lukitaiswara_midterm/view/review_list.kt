package com.example.a160919005_lukitaiswara_midterm.view

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.a160919005_lukitaiswara_midterm.R
import com.example.a160919005_lukitaiswara_midterm.model.MyBooks
import com.example.a160919005_lukitaiswara_midterm.model.MyReview
import com.example.a160919005_lukitaiswara_midterm.model.UserList
import com.example.a160919005_lukitaiswara_midterm.viewmodel.MyBookViewModel
import com.example.a160919005_lukitaiswara_midterm.viewmodel.MyReviewViewModel
import com.example.a160919005_lukitaiswara_midterm.viewmodel.ReviewViewModel
import kotlinx.android.synthetic.main.fragment_add_book.*
import kotlinx.android.synthetic.main.fragment_book_list.*
import kotlinx.android.synthetic.main.fragment_review_list.*


class review_list : Fragment() {

    private lateinit var viewModel: MyReviewViewModel
    private val ReviewListAdapter = ReviewListAdapter(arrayListOf(),{ item -> viewModel.clearTask(item as MyReview) })


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_review_list, container, false)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var bisbn = ""

        if(arguments != null) {
            bisbn     = review_listArgs.fromBundle(requireArguments()).idReview

        }



        viewModel = ViewModelProvider(this).get(MyReviewViewModel::class.java)
        viewModel.refreshReviews(bisbn.toString().toInt())
        recyclerViewReview.layoutManager = LinearLayoutManager(context)
        recyclerViewReview.adapter = ReviewListAdapter




        refreshLayoutReview.setOnRefreshListener {
            recyclerViewReview.visibility = View.GONE
            textErrorReview.visibility = View.GONE
            progressLoadReview.visibility = View.VISIBLE
            viewModel.refreshReviews(bisbn.toString().toInt())
            refreshLayoutReview.isRefreshing = false
        }



        buttonAddReview.setOnClickListener {
            var myText= textInputReview.text.toString();
            var myName = UserList.globalUsername
            var reviewData = MyReview(myName,bisbn.toString().toInt(),myText,"",0,0)
            Log.d("ReviewData: ", listOf(reviewData).toString());

            viewModel.addReview(listOf(reviewData))
            Toast.makeText(view.context, "Review Created", Toast.LENGTH_LONG).show()
        }

        observeViewModelReview()

    }

    fun observeViewModelReview() {
        /*
        viewModel.reviewLD.observe(viewLifecycleOwner, Observer {
            ReviewListAdapter.updateBookListLike(it)
        })*/
        Log.d("showbook", "masuk observeviewmodel")


        viewModel.myReviewLD.observe(viewLifecycleOwner, Observer {
            Log.d("showbook", "observe 1")
            Log.d("showbook", "panjang = " + it.size)
            ReviewListAdapter.updateReviewList(it)

            if(it.isEmpty()) {
                textReviewEmpty.visibility = View.VISIBLE
            } else {
                recyclerViewReview.visibility = View.VISIBLE
                textErrorReview.visibility = View.GONE
                progressLoadReview.visibility = View.GONE
                textReviewEmpty.visibility = View.GONE
            }
        })

        /*
        viewModel.reviewLoadErrorLD.observe(viewLifecycleOwner, Observer {
            if(it == true) {
                textErrorReview.visibility = View.VISIBLE
            } else {
                textErrorReview.visibility = View.GONE
            }
        })

        viewModel.loadingLD.observe(viewLifecycleOwner, Observer {
            if(it) {
                recyclerViewReview.visibility = View.GONE
                progressLoadReview.visibility = View.VISIBLE
            } else {
                recyclerViewReview.visibility = View.VISIBLE
                progressLoadReview.visibility = View.GONE


            }
        })*/





    }

}