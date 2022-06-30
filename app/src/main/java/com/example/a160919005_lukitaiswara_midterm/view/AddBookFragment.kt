package com.example.a160919005_lukitaiswara_midterm.view

import android.content.DialogInterface
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.app.AlertDialog;
import android.util.Log
import android.widget.RadioButton
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.example.a160919005_lukitaiswara_midterm.R
import com.example.a160919005_lukitaiswara_midterm.model.MyBooks
import com.example.a160919005_lukitaiswara_midterm.model.UserList
import com.example.a160919005_lukitaiswara_midterm.viewmodel.BookDetailViewModel
import com.example.a160919005_lukitaiswara_midterm.viewmodel.BookViewModel
import kotlinx.android.synthetic.main.fragment_add_book.*
import kotlinx.android.synthetic.main.fragment_register.*


class AddBookFragment : Fragment() {
    private lateinit var viewModel: BookDetailViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_add_book, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(BookDetailViewModel::class.java)

        buttonAddBook.setOnClickListener {

            var bookData = MyBooks(textAddTitle.text.toString(),textAddAuthor.text.toString(),textAddPublisher.text.toString(),textAddPhoto.text.toString()
                ,"5",textAddDescription.text.toString(),"",textAddDate.text.toString(),textAddQty.text.toString()
                ,"",0,1)
            Log.d("BookData: ", listOf(bookData).toString());
            viewModel.addBook(listOf(bookData))
            Toast.makeText(view.context, "Book Created", Toast.LENGTH_LONG).show()
            Navigation.findNavController(it).popBackStack()
        }






    }


}