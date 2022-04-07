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
import com.example.a160919005_lukitaiswara_midterm.R
import com.example.a160919005_lukitaiswara_midterm.util.loadImage2
import com.example.a160919005_lukitaiswara_midterm.viewmodel.BookDetailViewModel
import kotlinx.android.synthetic.main.fragment_book_detail.*
import java.util.concurrent.TimeUnit


class BookDetailFragment : Fragment() {

    private lateinit var viewModel: BookDetailViewModel


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_book_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var bisbn = ""
        var bauthor = ""
        var btitle = ""
        var bdescription = ""
        var bimage = ""
        var bPublisher = ""
        var bRating = ""
        var bGenres = ""
        var bDate = ""
        var bQty = ""

        if(arguments != null) {
            bisbn     = BookDetailFragmentArgs.fromBundle(requireArguments()).bisbn
            bauthor   = BookDetailFragmentArgs.fromBundle(requireArguments()).bauthor
            btitle    = BookDetailFragmentArgs.fromBundle(requireArguments()).btitle
            bdescription  = BookDetailFragmentArgs.fromBundle(requireArguments()).bdescription
            bimage  = BookDetailFragmentArgs.fromBundle(requireArguments()).bimage
            bPublisher = BookDetailFragmentArgs.fromBundle(requireArguments()).bPublisher
            bRating = BookDetailFragmentArgs.fromBundle(requireArguments()).bRating
            bGenres = BookDetailFragmentArgs.fromBundle(requireArguments()).bGenres
            bDate = BookDetailFragmentArgs.fromBundle(requireArguments()).bDate
            bQty = BookDetailFragmentArgs.fromBundle(requireArguments()).bQty
        }

        viewModel = ViewModelProvider(this).get(BookDetailViewModel::class.java)
        viewModel.fetch(bisbn)
        observeViewModel()

        buttonWantToRead.setOnClickListener {
            viewModel.changeLike(bisbn)
        }
        buttonDetailReview.setOnClickListener {
            val action = BookDetailFragmentDirections.actionDetailToReview(bisbn)
            Navigation.findNavController(it).navigate(action)
        }


    }

    fun observeViewModel() {
        Log.d("showvoley","masuk")
        Log.d("showvoley","masuk:"+viewModel.bookLD.value?.ISBN)
        viewModel.bookLD.observe(viewLifecycleOwner, Observer {
            Log.d("showvoley","id:"+it.publisher.toString())
            textDetailAuthor.setText(it.author.toString())
            textDetailTitle.setText(it.title.toString())
            textDetailDescription.setText(it.description.toString())

            imageBookDetail.loadImage2(it.photo.toString())




        })


    }


}