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
import com.example.a160919005_lukitaiswara_midterm.model.UserList
import com.example.a160919005_lukitaiswara_midterm.viewmodel.BookViewModel
import com.example.a160919005_lukitaiswara_midterm.viewmodel.UserViewModel
import kotlinx.android.synthetic.main.fragment_book_list.*
import kotlinx.android.synthetic.main.fragment_login.*
import kotlinx.android.synthetic.main.fragment_register.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [LoginFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class LoginFragment : Fragment() {

    private lateinit var viewModel: UserViewModel



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_login, container, false)
    }



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        buttonLogin.setOnClickListener {
            val name = textLoginUsername.text.toString()
            val password = textLoginPassword.text.toString()


            viewModel = ViewModelProvider(this).get(UserViewModel::class.java)
            var check = viewModel.searchUser(name,password)

            Log.d("showuser check", check.toString())


            if(check==true){
                Log.d("showuser check", "its in")
                var userDataMod= viewModel.searchUserInfo(name)
                Log.d("showuser data", userDataMod[0].username.toString())

                UserList.globalImage = userDataMod[0].profilePic.toString()
                UserList.globalUsername = userDataMod[0].username.toString()

                val action = LoginFragmentDirections.actionLoginToProfile( userDataMod[0].username.toString(),
                    userDataMod[0].profilePic.toString(),userDataMod[0].email.toString(),userDataMod[0].age.toString(),
                    userDataMod[0].address.toString(),userDataMod[0].Gender.toString())
                Navigation.findNavController(it).navigate(action)
            }
            /*
            val action = LoginFragmentDirections.actionLoginToProfile(name)
            Navigation.findNavController(it).navigate(action)*/


        }

        buttonFromLoginToRegister.setOnClickListener {

            val action = LoginFragmentDirections.actionLoginToRegister()
            Navigation.findNavController(it).navigate(action)


        }




        /*
        revycleViewBookList.layoutManager = LinearLayoutManager(context)
        revycleViewBookList.adapter = bookListAdapter


        observeViewModel()*/


    }



    fun observeViewModel() {
        /*
        viewModel.booksLD.observe(viewLifecycleOwner, Observer {
            bookListAdapter.updateBookList(it)
        })*/


    }


}