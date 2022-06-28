package com.example.a160919005_lukitaiswara_midterm.view

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CompoundButton
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.a160919005_lukitaiswara_midterm.R
import com.example.a160919005_lukitaiswara_midterm.databinding.BookListItemBinding

import com.example.a160919005_lukitaiswara_midterm.model.MyBooks
import com.example.a160919005_lukitaiswara_midterm.util.loadImage
import com.example.a160919005_lukitaiswara_midterm.util.loadImage2
import kotlinx.android.synthetic.main.book_list_item.view.*
import kotlinx.android.synthetic.main.fragment_book_detail.view.*

class BookListAdapter(val bookList:ArrayList<MyBooks>, val adapterOnClick : (MyBooks) -> Unit)
    :RecyclerView.Adapter<BookListAdapter.BookViewHolder>(),
    ButtonDetailClickListener{
    class BookViewHolder(var view: BookListItemBinding):RecyclerView.ViewHolder(view.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        //val view = inflater.inflate(R.layout.todo_item_layout, parent, false)
        val view = DataBindingUtil.inflate<BookListItemBinding>(inflater,
            R.layout.book_list_item, parent, false)
        return BookViewHolder(view)
    }


    fun updateBookList(newBookList: List<MyBooks>){
        bookList.clear()
        bookList.addAll(newBookList)
        notifyDataSetChanged()
    }

    fun updateBookListLike(newBookList:List<MyBooks>){
        bookList.clear()

        bookList.addAll(newBookList)
        notifyDataSetChanged()
    }



    override fun onBindViewHolder(holder: BookViewHolder, position: Int) {
        Log.d("check", "hello")
        Log.d("showtag", bookList[position].toString())

        holder.view.books = bookList[position]
        holder.view.listener = this





    }



    override fun getItemCount(): Int {
        return bookList.size
    }

    override fun onButtonDetailClick(v: View) {
        Log.d("showtag", v.tag.toString())

        val action = BookListFragmentDirections.actionItemHomeToBookDetailFragment(
            v.tag.toString(),"","","","","","",""
        ,"","")
        Navigation.findNavController(v).navigate(action)
    }
}


