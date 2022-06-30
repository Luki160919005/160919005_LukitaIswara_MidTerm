package com.example.a160919005_lukitaiswara_midterm.view

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.example.a160919005_lukitaiswara_midterm.R
import com.example.a160919005_lukitaiswara_midterm.databinding.FragmentBookDetailBinding
import com.example.a160919005_lukitaiswara_midterm.databinding.FragmentEditBookBinding
import com.example.a160919005_lukitaiswara_midterm.util.loadImage2
import com.example.a160919005_lukitaiswara_midterm.viewmodel.BookDetailViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.schedulers.Schedulers
import kotlinx.android.synthetic.main.fragment_book_detail.*
import kotlinx.android.synthetic.main.fragment_edit_book.*
import java.util.concurrent.TimeUnit



class BookDetailFragment : Fragment(), ButtonCreateNotification {

    private lateinit var viewModel: BookDetailViewModel
    private lateinit var binding: FragmentBookDetailBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        //binding = DataBindingUtil.inflate(inflater, R.layout.fragment_book_detail, container, false)
        //binding.listenerNotification = this
        binding = DataBindingUtil.inflate<FragmentBookDetailBinding>(inflater,R.layout.fragment_book_detail,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        /*
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

        */
        var bisbn = 0
        if(arguments != null) {
            bisbn     = BookDetailFragmentArgs.fromBundle(requireArguments()).detailISBN
        }


        viewModel = ViewModelProvider(this).get(BookDetailViewModel::class.java)
        viewModel.fetchBook(bisbn)
        observeViewModel()

        buttonWantToRead.setOnClickListener {
            viewModel.changeLikeBook(bisbn)
            val action = BookDetailFragmentDirections.actionDetailToWantToRead()
            Navigation.findNavController(it).navigate(action)

        }
        buttonDetailReview.setOnClickListener {
            val action = BookDetailFragmentDirections.actionBookDetailFragmentToReviewList(bisbn.toString())
            Navigation.findNavController(it).navigate(action)
        }


    }

    var bookName = "";
    fun observeViewModel() {
        viewModel.myBookLD.observe(viewLifecycleOwner, Observer {
            binding.bookDetail = it
            bookName = it.title.toString()

        })

        Log.d("showvoley","masuk")
        Log.d("showvoley","masuk:"+viewModel.bookLD.value?.ISBN)
        /*
        viewModel.bookLD.observe(viewLifecycleOwner, Observer {
            Log.d("showvoley","id:"+it.publisher.toString())
            textDetailAuthor.setText(it.author.toString())
            textDetailTitle.setText(it.title.toString())
            textDetailDescription.setText(it.description.toString())

            imageBookDetail.loadImage2(it.photo.toString())




        })*/


        /*
        viewModel.bookLD.observe(viewLifecycleOwner, Observer {
            Log.d("showtag2",it.toString())

            /*val bookDetailData = MyBooks(it.ISBN.toString(), it.title.toString(),it.author.toString(),it.publisher.toString(),it.photo.toString()
            , it.rating.toString(), it.description.toString(), it.genres.toString(), it.date.toString(), it.qty.toString()
            , it.like.toString())*/



            //binding.bookDetail = it

            bookName = it.title.toString()




        })*/


    }

    override fun onButtonCreateNotification(v: View) {
        Log.d("showcek", "create notif")
        Observable.timer(5, TimeUnit.SECONDS)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                Log.d("Messages", "five seconds")
                MainActivity.showNotification(bookName,
                    "A new notification created",
                    R.drawable.ic_baseline_person_24)
            }
    }


}