package com.example.a160919005_lukitaiswara_midterm.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.a160919005_lukitaiswara_midterm.R
import com.example.a160919005_lukitaiswara_midterm.viewmodel.ReviewViewModel
import kotlinx.android.synthetic.main.fragment_review_list.*
import kotlinx.android.synthetic.main.fragment_want_to_read.refreshLayout

class review_list : Fragment() {
    private lateinit var viewModel: ReviewViewModel
    private val ReviewListAdapter  = ReviewListAdapter(arrayListOf())

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



        viewModel = ViewModelProvider(this).get(ReviewViewModel::class.java)
        viewModel.refresh(bisbn)
        recyclerViewReview.layoutManager = LinearLayoutManager(context)
        recyclerViewReview.adapter = ReviewListAdapter

        refreshLayout.setOnRefreshListener {
            recyclerViewReview.visibility = View.GONE
            textErrorReview.visibility = View.GONE
            progressLoadReview.visibility = View.VISIBLE
            viewModel.refresh(bisbn)
            refreshLayout.isRefreshing = false
        }

        observeViewModel()

        buttonAddReview.setOnClickListener {
            var myText= textInputReview.text.toString();
            viewModel.addReview(bisbn,myText)
        }
    }

    fun observeViewModel() {
        viewModel.reviewLD.observe(viewLifecycleOwner, Observer {
            ReviewListAdapter.updateBookListLike(it)
        })

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
        })





    }

}