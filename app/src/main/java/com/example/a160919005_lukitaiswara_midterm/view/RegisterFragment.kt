package com.example.a160919005_lukitaiswara_midterm.view

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.example.a160919005_lukitaiswara_midterm.R
import com.example.a160919005_lukitaiswara_midterm.model.MyBooks
import com.example.a160919005_lukitaiswara_midterm.model.MyUser
import com.example.a160919005_lukitaiswara_midterm.model.UserList
import com.example.a160919005_lukitaiswara_midterm.viewmodel.MyUserViewModel
import com.example.a160919005_lukitaiswara_midterm.viewmodel.UserViewModel
import kotlinx.android.synthetic.main.fragment_add_book.*
import kotlinx.android.synthetic.main.fragment_explore.*
import kotlinx.android.synthetic.main.fragment_login.*
import kotlinx.android.synthetic.main.fragment_register.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [RegisterFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class RegisterFragment : Fragment() {
    private lateinit var viewModel: MyUserViewModel


    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_register, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        buttonRegister.setOnClickListener {
            val name = textInputUsername.text.toString()
            val password = textInputPassword.text.toString()
            val picture = textInputPicture.text.toString()
            val age = textInputAge.text.toString()
            val email = textInputEmail.text.toString()
            val address = textInputAddress.text.toString()
            var checkGender = ""
            if(radioMale.isChecked){
                checkGender = "Male"
            }
            if(radioFemale.isChecked){
                checkGender ="Female";
            }

            viewModel = ViewModelProvider(this).get(MyUserViewModel::class.java)

            var userData = MyUser(name,password,picture,age,email,address,checkGender,0,0)


            viewModel.addUser(listOf(userData))


            /*
            var userDataMod= viewModel.searchUserInfo(name)
            Log.d("showuser data", userDataMod[0].username.toString())

            UserList.globalImage = userDataMod[0].profilePic.toString()
            UserList.globalUsername = userDataMod[0].username.toString()

            val action = RegisterFragmentDirections.actionRegisterToProfile( userDataMod[0].username.toString(),
                userDataMod[0].profilePic.toString(),userDataMod[0].email.toString(),userDataMod[0].age.toString(),
                userDataMod[0].address.toString(),userDataMod[0].Gender.toString())*/
            val action = RegisterFragmentDirections.actionRegisterToLogin()
            Navigation.findNavController(it).navigate(action)



            //val action = RegisterFragmentDirections.actionRegisterToProfile(name)
            //Navigation.findNavController(it).navigate(action)


        }

        buttonRegisterToLogin.setOnClickListener {


            val action = RegisterFragmentDirections.actionRegisterToLogin()
            Navigation.findNavController(it).navigate(action)
        }


    }




}