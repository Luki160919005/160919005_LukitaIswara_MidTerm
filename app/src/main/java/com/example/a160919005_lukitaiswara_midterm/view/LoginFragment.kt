package com.example.a160919005_lukitaiswara_midterm.view

import android.app.Activity
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.a160919005_lukitaiswara_midterm.R
import com.example.a160919005_lukitaiswara_midterm.model.MyBooks
import com.example.a160919005_lukitaiswara_midterm.model.MyUser
import com.example.a160919005_lukitaiswara_midterm.model.UserList
import com.example.a160919005_lukitaiswara_midterm.viewmodel.BookViewModel
import com.example.a160919005_lukitaiswara_midterm.viewmodel.MyBookViewModel
import com.example.a160919005_lukitaiswara_midterm.viewmodel.MyUserViewModel
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

    private lateinit var myactivity: Activity
    private lateinit var viewModel: MyUserViewModel
    private lateinit var viewModelBooks: MyBookViewModel
    private var userDataMod : List<MyUser>? = null



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_login, container, false)
    }

    private var check = false

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        buttonLogin.setOnClickListener {
            Log.d("showuser", "masuk tombol login")
            val name = textLoginUsername.text.toString()
            val password = textLoginPassword.text.toString()

            Log.d("showuser 1", name + " -- " + password)

            viewModel = ViewModelProvider(this).get(MyUserViewModel::class.java)
            viewModelBooks = ViewModelProvider(this).get(MyBookViewModel::class.java)
            Log.d("showuser 2", name + " -- " + password)

            userDataMod = viewModel.namePasswordVerificatioN(name,password)

//            Log.d("showuser check", check.toString())
//
//            if(check==true){
//                Log.d("showuser check", "its in")
//                var userDataMod= viewModel.searchUserInfo(name)
//                Log.d("showuser data", userDataMod[0].username.toString())
//                /*
//                UserList.globalImage = userDataMod[0].profilePic.toString()
//                UserList.globalUsername = userDataMod[0].username.toString()
//
//                val action = LoginFragmentDirections.actionLoginToProfile( userDataMod[0].username.toString(),
//                    userDataMod[0].profilePic.toString(),userDataMod[0].email.toString(),userDataMod[0].age.toString(),
//                    userDataMod[0].address.toString(),userDataMod[0].Gender.toString())
//                Navigation.findNavController(it).navigate(action)*/
//            }
            /*
            val action = LoginFragmentDirections.actionLoginToProfile(name)
            Navigation.findNavController(it).navigate(action)*/
            observeViewModel()

            if(check==true){
                check=false
                Navigation.findNavController(it).navigate(action)
            //Log.d("showuser", "berhasil login sudahan2")
            }
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

    var action = LoginFragmentDirections.actionLoginToProfile("","","","","","")

    fun observeViewModel() {
        viewModel.returnReadAllData.observe(viewLifecycleOwner, Observer {
            if(it != null) {
                Log.d("showuser berhasil dapat", it.toString())
                UserList.globalImage = it[0].profilePic.toString()
                UserList.globalUsername = it[0].username.toString()

                Log.d("showuser", "berhasil login sudahan")
                Log.d("showuser", "usernalist = " + UserList);

                //var userDataMod= viewModel.searchUserInfo(name)
                //Log.d("showuser data", it[0]..toString())




                //Navigation.findNavController(it).navigate(action)

                if(it[0].username.toString() == "initialTest") {
                    val BooksGenerator = listOf(
                        MyBooks(
                            "Alexander Hamilton",
                            "Ron Chernow",
                            "Penguin Books",
                            "https://cdn.waterstones.com/bookjackets/large/9781/8002/9781800244399.jpg",
                            "4.2",
                            "Few figures in American history have been more hotly debated or more grossly misunderstood than Alexander Hamilton. Chernows biography gives Hamilton his due and sets the record straight, deftly illustrating that the political and economic greatness of today’s America is the result of Hamilton’s countless sacrifices to champion ideas that were often wildly disputed during his time. Chernow here recounts Hamilton turbulent life: an illegitimate, largely self-taught orphan from the Caribbean, he came out of nowhere to take America by storm, rising to become George Washington aide de camp in the Continental Army, coauthoring The Federalist Papers, founding the Bank of New York, leading the Federalist Party, and becoming the first Treasury Secretary of the United States.Historians have long told the story of Americas birth as the triumph of Jefferson’s democratic ideals over the aristocratic intentions of Hamilton. Chernow presents an entirely different man, whose legendary ambitions were motivated not merely by self-interest but by passionate patriotism and a stubborn will to build the foundations of American prosperity and power. His is a Hamilton far more human than we’ve encountered before from his shame about his birth to his fiery aspirations, from his intimate relationships with childhood friends to his titanic feuds with Jefferson, Madison, Adams, Monroe, and Burr, and from his highly public affair with Maria Reynolds to his loving marriage to his loyal wife Eliza. And never before has there been a more vivid account of Hamilton’s famous and mysterious death in a duel with Aaron Burr in July of 1804.",
                            "Biography",
                            "March 29th 2005",
                            "5",
                            "",
                            0,
                            0
                        ),
                        MyBooks(
                            "In Search of Lost Time",
                            "Marcel Proust",
                            "Modern Library",
                            "https://i.gr-assets.com/images/S/compressed.photo.goodreads.com/books/1325095874l/13329904.jpg",
                            "4.35",
                            "In Search of Lost Time is a novel in seven volumes. The novel began to take shape in 1909. Proust continued to work on it until his final illness in the autumn of 1922 forced him to break off. Proust established the structure early on, but even after volumes were initially finished he kept adding new material, and edited one volume after another for publication. The last three of the seven volumes contain oversights and fragmentary or unpolished passages as they existed in draft form at the death of the author; the publication of these parts was overseen by his brother Robert.",

                            "Fiction",
                            "June 3rd 2003",
                            "10",
                            "",
                            0,
                            0
                        ),

                        MyBooks(
                            "The Book of Five Rings",
                            "Miyamoto Musashi",
                            "Gramercy Books",
                            "https://kbimages1-a.akamaihd.net/58eefb8d-dccd-4421-b359-d9384dac86b6/1200/1200/False/the-book-of-five-rings-49.jpg",
                            "4.02",
                            "Shortly before his death in 1645, the undefeated swordsman Miyamoto Musashi retreated to a cave to live as a hermit. There he wrote five scrolls describing the true principles required for victory in the martial arts and on the battlefield. Instead of relying on religion or theory, Musashi based his writings on his own experience, observation, and reason.",

                            "History",
                            "May 28th 1988",
                            "1",
                            "",
                            0,
                            0
                        ),
                        MyBooks(
                            "Nietzsche: Beyond Good and Evi",
                            "Friedrich Nietzsche",
                            "Penguin Classics",
                            "https://images-na.ssl-images-amazon.com/images/I/81pLz6e3IJL.jpg",
                            "4.01",
                            "Frederich Nietzsche (1844-1900) became the chair of classical philology at Basel University at the age of 24 until his bad health forced him to retire in 1879. He divorced himself from society until his final collapse in 1899 when he became insane. A powerfully original thinker, Nietzsche's influence on subsequent writers, such as George Bernard Shaw, D.H. Lawrence, Thomas Mann and Jean-Paul Sartre, was considerable.",

                            "Fiction",
                            "February 27th 2003",
                            "7",
                            "",
                            0,
                            0
                        )

                    )
                    viewModelBooks.addBook(BooksGenerator)


                    val UsernameGenerator = listOf(
                        MyUser(
                            "Alex",
                            "1",
                            "https://images.unsplash.com/photo-1521572267360-ee0c2909d518?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxzZWFyY2h8MjJ8fHByb2ZpbGV8ZW58MHx8MHx8&w=1000&q=80",
                            "20",
                            "Alex@gmail.com",
                            "Yellow Street",
                            "Male",
                            0,
                            0

                        )



                    )
                    viewModel.addUser(UsernameGenerator)
                }


                action = LoginFragmentDirections.actionLoginToProfile( it[0].username.toString(),
                    it[0].profilePic.toString(),it[0].email.toString(),it[0].age.toString(),
                    it[0].address.toString(),it[0].Gender.toString())


                //check = true
                //view?.let { it1 -> Navigation.findNavController(it1).navigate(action) }
                this.findNavController().navigate(action)


//                val action = LoginFragmentDirections.actionLoginToProfile( it[0].username.toString(),
//                    it[0].profilePic.toString(),it[0].email.toString(),it[0].age.toString(),
//                    it[0].address.toString(),it[0].Gender.toString())
//                Navigation.findNavController(myactivity).navigate(action)
            }
        })
        viewModel.validLogin.observe(viewLifecycleOwner, Observer {
            Log.d("showuser observe = ",  "return value = " + it.toString())

            if(it == 1) {
                val name = textLoginUsername.text.toString()
                Toast.makeText(context, "Login Successfull", Toast.LENGTH_LONG).show()
//                var userDataMod= viewModel.searchUserInfo(name)
                Log.d("showuser data", userDataMod?.get(0)?.username.toString())




            }
            else {
                Toast.makeText(context, "Wrong Username or Password", Toast.LENGTH_LONG).show()
            }
        })
    }

    /*
    fun observeViewModel() {
        /*
        viewModel.booksLD.observe(viewLifecycleOwner, Observer {
            bookListAdapter.updateBookList(it)
        })*/
    }*/
}



