package com.example.a160919005_lukitaiswara_midterm.view

import android.content.DialogInterface
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.app.AlertDialog;
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.example.a160919005_lukitaiswara_midterm.R
import com.example.a160919005_lukitaiswara_midterm.model.UserList
import com.example.a160919005_lukitaiswara_midterm.viewmodel.BookViewModel
import kotlinx.android.synthetic.main.fragment_add_book.*
import kotlinx.android.synthetic.main.fragment_register.*


class AddBookFragment : Fragment() {


    private lateinit var viewModel: BookViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_add_book, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var dialogBuilder = AlertDialog.Builder(getActivity())


        buttonAddBook.setOnClickListener {

            if(UserList.globalUsername.equals("admin")) {

                val isbn = textAddISBN.text.toString()
                val title = textAddTitle.text.toString()
                val author = textAddAuthor.text.toString()
                val publisher = textAddPublisher.text.toString()
                val photo = textAddPhoto.text.toString()
                val description = textAddDescription.text.toString()
                val genre = textAddGenre.text.toString()
                val date = textAddDate.text.toString()
                val qty = textAddQty.text.toString()

                if((isbn.equals(""))||(title.equals(""))||(author.equals(""))||(publisher.equals(""))
                    ||(publisher.equals(""))||(photo.equals(""))||(description.equals(""))||(genre.equals(""))
                    ||(date.equals(""))||(qty.equals(""))){

                    // set message of alert dialog
                    dialogBuilder.setMessage("Input Must Not be empty")
                        // if the dialog is cancelable
                        .setCancelable(false)
                        // positive button text and action

                        // negative button text and action
                        .setNegativeButton("Cancel", DialogInterface.OnClickListener {
                                dialog, id -> dialog.cancel()
                        })

                    // create dialog box
                    val alert = dialogBuilder.create()
                    // set title for alert dialog box
                    alert.setTitle("Warning")
                    // show alert dialog
                    alert.show()

                }

            }
            else{



                // set message of alert dialog
                dialogBuilder.setMessage("This application can only be access by Admin")
                    // if the dialog is cancelable
                    .setCancelable(false)
                    // positive button text and action

                    // negative button text and action
                    .setNegativeButton("Cancel", DialogInterface.OnClickListener {
                            dialog, id -> dialog.cancel()
                    })

                // create dialog box
                val alert = dialogBuilder.create()
                // set title for alert dialog box
                alert.setTitle("Warning")
                // show alert dialog
                alert.show()
            }




        }




    }


}