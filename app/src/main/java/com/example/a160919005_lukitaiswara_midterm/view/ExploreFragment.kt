package com.example.a160919005_lukitaiswara_midterm.view

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.a160919005_lukitaiswara_midterm.R
import com.example.a160919005_lukitaiswara_midterm.viewmodel.BookViewModel
import kotlinx.android.synthetic.main.fragment_explore.*



class ExploreFragment : Fragment() {

    private lateinit var viewModel: BookViewModel
    //private val wantListAdapter  = wantToReadAdapter(arrayListOf())


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        return inflater.inflate(R.layout.fragment_explore, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var checkGenre = "";
        var search = textExploreSearch.text
        buttonGenre.setOnClickListener {

            if(radioBiography.isChecked){
                checkGenre = "Biography"
            }
            if(radioButtonFantasy.isChecked){
                checkGenre ="Fantasy";
            }
            if(radioFiction.isChecked){
                checkGenre ="Fiction";
            }
            if(radioHistory.isChecked){
                checkGenre ="History";
            }
            /*
            Log.d("showvoley check", checkGenre.toString());
            viewModel = ViewModelProvider(this).get(BookViewModel::class.java)
            viewModel.refreshGenre(checkGenre.toString(), search.toString())
            recViewExplore.layoutManager = LinearLayoutManager(context)
            recViewExplore.adapter = wantListAdapter

            refreshLayoutExplore.setOnRefreshListener {
                recViewExplore.visibility = View.GONE
                textExploreError.visibility = View.GONE
                progressLoadExplore.visibility = View.VISIBLE
                viewModel.refreshGenre(checkGenre, search.toString())
                refreshLayout.isRefreshing = false
            }

            observeViewModel()*/
        }




    }



    fun observeViewModel() {
        /*
        viewModel.booksLD.observe(viewLifecycleOwner, Observer {
            wantListAdapter.updateBookListLike(it)
        })

        viewModel.bookLoadErrorLD.observe(viewLifecycleOwner, Observer {
            if(it == true) {
                textExploreError.visibility = View.VISIBLE
            } else {
                textExploreError.visibility = View.GONE
            }
        })

        viewModel.loadingLD.observe(viewLifecycleOwner, Observer {
            if(it) {
                recViewExplore.visibility = View.GONE
                progressLoadExplore.visibility = View.VISIBLE
            } else {
                recViewExplore.visibility = View.VISIBLE
                progressLoadExplore.visibility = View.GONE


            }
        })*/
    }


}