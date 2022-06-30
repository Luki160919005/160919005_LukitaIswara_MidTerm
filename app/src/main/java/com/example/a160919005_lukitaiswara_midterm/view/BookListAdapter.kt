package com.example.a160919005_lukitaiswara_midterm.view

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CompoundButton
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.a160919005_lukitaiswara_midterm.R
import com.example.a160919005_lukitaiswara_midterm.databinding.BookListItemBinding

import com.example.a160919005_lukitaiswara_midterm.model.MyBooks
import com.example.a160919005_lukitaiswara_midterm.util.loadImage
import com.example.a160919005_lukitaiswara_midterm.util.loadImage2
import com.example.a160919005_lukitaiswara_midterm.viewmodel.BookDetailViewModel
import com.example.a160919005_lukitaiswara_midterm.viewmodel.MyBookViewModel
import kotlinx.android.synthetic.main.book_list_item.view.*
import kotlinx.android.synthetic.main.fragment_book_detail.view.*

class BookListAdapter(val bookList:ArrayList<MyBooks>, val adapterOnClick : (MyBooks) -> Unit)
    :RecyclerView.Adapter<BookListAdapter.BookViewHolder>(),
    ButtonDetailClickListener, ButtonUpdate, ButtonEditListener,ButtonDeleteListener, TodoCheckedChangeListerner{
    class BookViewHolder(var view: BookListItemBinding):RecyclerView.ViewHolder(view.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        //val view = inflater.inflate(R.layout.todo_item_layout, parent, false)
        val view = DataBindingUtil.inflate<BookListItemBinding>(inflater,
            R.layout.book_list_item, parent, false)
        return BookViewHolder(view)
    }

    override fun getItemCount(): Int {
        Log.d("showbook", "getitemcount = " + bookList.size)
        return bookList.size
    }
    private lateinit var viewModel: BookDetailViewModel

    override fun onBindViewHolder(holder: BookViewHolder, position: Int) {
        Log.d("showbook check", "hello")
        Log.d("showbook showtag", bookList[position].toString())

        holder.view.books = bookList[position]
        holder.view.listener = this
        holder.view.editListener = this
        holder.view.deleteListener=this
        holder.view.listenerOnCheck = this

        /*
        holder.view.checkTask.setOnCheckedChangeListener { compoundButton, isChecked ->
            if(isChecked) {

                adapterOnClick(bookList[position])
            }
        }*/
        //holder.view.deleteListener = this
        /*
        holder.view.buttonDeleteItem.setOnClickListener {
            viewModel = ViewModelProvider(this).get(BookDetailViewModel::class.java)
            Log.d("String Delete", "hello")
            if (::viewModel.isInitialized) {
                viewModel.updateIsDone(bookList[position].ISBN)
            }
        }*/




    }

    fun updateBookList(newBookList: List<MyBooks>){
        Log.d("showbook", "panjang book di updatebooklist = " + bookList.size)
        bookList.clear()
        bookList.addAll(newBookList)
        notifyDataSetChanged()
        Log.d("showbook", "panjang book di updatebooklist = " + bookList.size)
    }

    fun updateBookListLike(newBookList:List<MyBooks>){
        bookList.clear()
        bookList.addAll(newBookList)
        notifyDataSetChanged()
    }

    override fun onButtonDetailClick(v: View) {
        Log.d("showtag", v.tag.toString())

        val action = BookListFragmentDirections.actionHomeToDetail(
            v.tag.toString().toInt())
        Navigation.findNavController(v).navigate(action)


    }

    override fun onButtonUpdate(v: View) {
        val uuid = v.tag.toString().toInt()
        val action = BookListFragmentDirections.actionHomeEdit(uuid)

        Navigation.findNavController(v).navigate(action)
    }

    override fun onButtonEdit(v: View) {
        val uuid = v.tag.toString().toInt()
        val action = BookListFragmentDirections.actionHomeEdit(uuid)

        Navigation.findNavController(v).navigate(action)
    }

    override fun onButtonDelete(v: View) {
        Log.d("checkString", v.tag.toString())
        val uuid = v.tag.toString().toInt()
        Log.d("checkString tag", v.tag.toString())
        //viewModel = ViewModelProvider(this).get(BookDetailViewModel::class.java)

        viewModel?.updateIsDone(uuid)


    }

    override fun ViewModelProvider(bookListAdapter: BookListAdapter): ViewModelProvider {
        TODO("Not yet implemented")
    }

    override fun onTodoCheckedChange(cb: CompoundButton, isChecked: Boolean, obj: MyBooks) {
        if(isChecked) {
            adapterOnClick(obj)
        }
    }

}


