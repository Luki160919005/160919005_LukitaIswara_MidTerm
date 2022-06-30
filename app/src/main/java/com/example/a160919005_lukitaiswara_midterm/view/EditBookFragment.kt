package com.example.a160919005_lukitaiswara_midterm.view

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.a160919005_lukitaiswara_midterm.R
import com.example.a160919005_lukitaiswara_midterm.databinding.FragmentEditBookBinding
import com.example.a160919005_lukitaiswara_midterm.viewmodel.BookDetailViewModel
import androidx.lifecycle.Observer
import androidx.navigation.Navigation
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.schedulers.Schedulers
import kotlinx.android.synthetic.main.fragment_add_book.*
import kotlinx.android.synthetic.main.fragment_edit_book.*
import java.util.concurrent.TimeUnit

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"


class EditBookFragment : Fragment(), ButtonCreateNotification {
    // TODO: Rename and change types of parameters
    private lateinit var viewModel: BookDetailViewModel
    private lateinit var dataBinding: FragmentEditBookBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        dataBinding = DataBindingUtil.inflate<FragmentEditBookBinding>(inflater,R.layout.fragment_edit_book,container,false)
        dataBinding.listenerNotification = this
        return dataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(BookDetailViewModel::class.java)
        val isbn = EditBookFragmentArgs.fromBundle(requireArguments()).id
        Log.d("checkString isbn", isbn.toString());
        viewModel.fetchBook(isbn)

        buttonEditBook.setOnClickListener{

            viewModel.update(textEditTitle.text.toString(),textEditAuthor.text.toString(),
                texEditPublisher.text.toString(),textEditPhoto.text.toString()
                ,"5",textEditDescription.text.toString(),textEditDate.text.toString(),textEditQty.text.toString(),
                isbn)
            Toast.makeText(view.context, "Book updated", Toast.LENGTH_SHORT).show()
            Navigation.findNavController(it).popBackStack()
        }


        observeViewModel()

    }
    var bookName = "";
    fun observeViewModel() {
        viewModel.myBookLD.observe(viewLifecycleOwner, Observer {
            dataBinding.books = it
            if(it.is_done == 0){
                bookName = it.title.toString()

                textEditTitle.setText(it.title)
                textEditAuthor.setText(it.author)
                texEditPublisher.setText(it.publisher)
                textEditPhoto.setText(it.photo)
                textEditDescription.setText(it.description)
                textEditQty.setText(it.qty)
                textEditDate.setText(it.date)


            }

        })
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