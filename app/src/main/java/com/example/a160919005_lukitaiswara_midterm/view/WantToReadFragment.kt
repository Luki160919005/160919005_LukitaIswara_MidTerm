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

import com.example.a160919005_lukitaiswara_midterm.viewmodel.BookViewModel
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
    private lateinit var viewModel: BookViewModel
    private val wantListAdapter  = wantToReadAdapter(arrayListOf())

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_want_to_read, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this).get(BookViewModel::class.java)
        viewModel.refreshLike()
        recViewWanToRead.layoutManager = LinearLayoutManager(context)
        recViewWanToRead.adapter = wantListAdapter

        refreshLayout.setOnRefreshListener {
            recViewWanToRead.visibility = View.GONE
            textErrorBookList.visibility = View.GONE
            progressLoadBookList.visibility = View.VISIBLE
            viewModel.refreshLike()
            refreshLayout.isRefreshing = false
        }

        observeViewModel()
    }

    fun observeViewModel() {
        viewModel.booksLD.observe(viewLifecycleOwner, Observer {
            wantListAdapter.updateBookListLike(it)
        })

        viewModel.bookLoadErrorLD.observe(viewLifecycleOwner, Observer {
            if(it == true) {
                txtErrorWantToRead.visibility = View.VISIBLE
            } else {
                txtErrorWantToRead.visibility = View.GONE
            }
        })

        viewModel.loadingLD.observe(viewLifecycleOwner, Observer {
            if(it) {
                recViewWanToRead.visibility = View.GONE
                progressLoadWantToRead.visibility = View.VISIBLE
            } else {
                recViewWanToRead.visibility = View.VISIBLE
                progressLoadWantToRead.visibility = View.GONE


            }
        })





    }
}