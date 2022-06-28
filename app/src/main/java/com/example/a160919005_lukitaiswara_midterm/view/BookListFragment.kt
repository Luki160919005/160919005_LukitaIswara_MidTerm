package com.example.a160919005_lukitaiswara_midterm.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.a160919005_lukitaiswara_midterm.R

import com.example.a160919005_lukitaiswara_midterm.viewmodel.BookViewModel
import com.example.a160919005_lukitaiswara_midterm.viewmodel.MyBookViewModel
import kotlinx.android.synthetic.main.fragment_book_list.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER

/**
 * A simple [Fragment] subclass.
 * Use the [BookListFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class BookListFragment : Fragment() {
    private lateinit var viewModel: MyBookViewModel
    private val bookListAdapter  = BookListAdapter(arrayListOf())

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_book_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this).get(MyBookViewModel::class.java)
        viewModel.refresh()


        revycleViewBookList.layoutManager = LinearLayoutManager(context)
        revycleViewBookList.adapter = bookListAdapter


        refreshLayoutBookList.setOnRefreshListener {
            revycleViewBookList.visibility = View.GONE
            textErrorBookList.visibility = View.GONE
            progressLoadBookList.visibility = View.VISIBLE
            viewModel.refresh()

            refreshLayoutBookList.isRefreshing = false
        }

        floatingActionButton.setOnClickListener {
            val action = BookListFragmentDirections.actionAddBook()
            Navigation.findNavController(it).navigate(action)
        }

        observeViewModel()
    }

    fun observeViewModel() {
        viewModel.myBookLD.observe(viewLifecycleOwner, Observer {
            bookListAdapter.updateBookList(it)

            if(it.isEmpty()) {
                textEmpty.visibility = View.VISIBLE
            } else {
                textEmpty.visibility = View.GONE
            }



        })

        viewModel.bookLoadErrorLD.observe(viewLifecycleOwner, Observer {
            if(it == true) {
                textErrorBookList.visibility = View.VISIBLE
            } else {
                textErrorBookList.visibility = View.GONE
            }
        })

        viewModel.loadingLD.observe(viewLifecycleOwner, Observer {
            if(it) {
                revycleViewBookList.visibility = View.GONE
                progressLoadBookList.visibility = View.VISIBLE
            } else {
                revycleViewBookList.visibility = View.VISIBLE
                progressLoadBookList.visibility = View.GONE


            }
        })





    }
}