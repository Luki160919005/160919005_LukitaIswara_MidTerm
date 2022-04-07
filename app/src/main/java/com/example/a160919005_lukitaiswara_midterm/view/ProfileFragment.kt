package com.example.a160919005_lukitaiswara_midterm.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import com.example.a160919005_lukitaiswara_midterm.R
import com.example.a160919005_lukitaiswara_midterm.util.loadImage2
import kotlinx.android.synthetic.main.book_list_item.view.*
import kotlinx.android.synthetic.main.fragment_profile.*


class ProfileFragment : Fragment() {



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_profile, container, false)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if(arguments != null){
            val name = ProfileFragmentArgs.fromBundle(requireArguments()).profileUsername
            val picture = ProfileFragmentArgs.fromBundle(requireArguments()).profilePicture
            val profileEmail = ProfileFragmentArgs.fromBundle(requireArguments()).profileEmail
            val profileAddress = ProfileFragmentArgs.fromBundle(requireArguments()).profileAddress
            val profileAge = ProfileFragmentArgs.fromBundle(requireArguments()).profileAge
            val profileGender = ProfileFragmentArgs.fromBundle(requireArguments()).profileGender

            textProfileUsername.text = "Name: $name"
            textAddress.text = "Address: $profileAddress"
            textEmail.text = "Email: $profileEmail"
            textAge.text = "Age: $profileAge"
            textGender.text = "Gender: $profileGender"
            imageProfile.loadImage2(picture.toString())

        }

    }

}