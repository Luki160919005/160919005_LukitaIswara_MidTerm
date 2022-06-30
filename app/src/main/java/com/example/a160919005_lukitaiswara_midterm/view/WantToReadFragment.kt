package com.example.a160919005_lukitaiswara_midterm.view

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.a160919005_lukitaiswara_midterm.R
import com.example.a160919005_lukitaiswara_midterm.model.MyBooks

import com.example.a160919005_lukitaiswara_midterm.viewmodel.BookViewModel
import com.example.a160919005_lukitaiswara_midterm.viewmodel.MyBookViewModel
import kotlinx.android.synthetic.main.fragment_book_list.*

import kotlinx.android.synthetic.main.fragment_want_to_read.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [WantToReadFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class WantToReadFragment : Fragment() {
    private lateinit var viewModel: MyBookViewModel
    private val wantToReadAdapter = wantToReadAdapter(arrayListOf(),{ item -> viewModel.clearTask(item as MyBooks) })


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_want_to_read, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this).get(MyBookViewModel::class.java)
        viewModel.refreshLike()

        recViewWanToRead.layoutManager = LinearLayoutManager(context)
        recViewWanToRead.adapter = wantToReadAdapter

        refreshLayoutWantToRead.setOnRefreshListener {
            recViewWanToRead.visibility = View.GONE
            txtErrorWantToRead.visibility = View.GONE
            progressLoadWantToRead.visibility = View.VISIBLE
            viewModel.refreshLike()
            refreshLayoutWantToRead.isRefreshing = false
        }



        observeViewModel()
    }

    fun observeViewModel() {
        Log.d("showbook", "masuk observeviewmodel")
        viewModel.myBookLD.observe(viewLifecycleOwner, Observer {
            Log.d("showbook", "observe 1")
            Log.d("showbook", "panjang = " + it.size)
            wantToReadAdapter.updateBookListLike(it)

            if(it.isEmpty()) {
                textEmptyWantToRead.visibility = View.VISIBLE
            } else {
                recViewWanToRead.visibility = View.VISIBLE
                txtErrorWantToRead.visibility = View.GONE
                progressLoadWantToRead.visibility = View.GONE

                textEmptyWantToRead.visibility = View.GONE
            }
        })


//        viewModel.bookLoadErrorLD.observe(viewLifecycleOwner, Observer {
//            if(it == true) {
//                textErrorBookList.visibility = View.VISIBLE
//            } else {
//                textErrorBookList.visibility = View.GONE
//            }
//        })
//
//        viewModel.loadingLD.observe(viewLifecycleOwner, Observer {
//            if(it) {
//                revycleViewBookList.visibility = View.GONE
//                progressLoadBookList.visibility = View.VISIBLE
//            } else {
//                revycleViewBookList.visibility = View.VISIBLE
//                progressLoadBookList.visibility = View.GONE
//            }
//        })
    }
}