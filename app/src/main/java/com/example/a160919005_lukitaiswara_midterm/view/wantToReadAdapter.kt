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
import com.example.a160919005_lukitaiswara_midterm.model.Book
import com.example.a160919005_lukitaiswara_midterm.model.MyBooks
import com.example.a160919005_lukitaiswara_midterm.util.loadImage2
import com.example.a160919005_lukitaiswara_midterm.viewmodel.BookDetailViewModel
import kotlinx.android.synthetic.main.book_list_item.view.*

class wantToReadAdapter(val bookList:ArrayList<MyBooks>, val adapterOnClick : (MyBooks) -> Unit)
    :RecyclerView.Adapter<wantToReadAdapter.WantToReadViewHolder>(),
    ButtonDetailClickListener, ButtonUpdate, ButtonEditListener,ButtonDeleteListener, TodoCheckedChangeListerner{
    class WantToReadViewHolder(var view: BookListItemBinding):RecyclerView.ViewHolder(view.root)


    class BookViewHolderList(var view: View): RecyclerView.ViewHolder(view)

    fun updateBookListLike(newBookList:List<MyBooks>){
        bookList.clear()
        bookList.addAll(newBookList)
        notifyDataSetChanged()
    }



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WantToReadViewHolder {
        val inflater = LayoutInflater.from(parent.context)

        val view = DataBindingUtil.inflate<BookListItemBinding>(inflater,
            R.layout.book_list_item, parent, false)
        return wantToReadAdapter.WantToReadViewHolder(view)
    }


    override fun getItemCount(): Int {
        return bookList.size
    }


    private lateinit var viewModel: BookDetailViewModel
    override fun onBindViewHolder(holder: WantToReadViewHolder, position: Int) {
        Log.d("showbook check", "hello")
        Log.d("showbook showtag", bookList[position].toString())

        holder.view.books = bookList[position]
        holder.view.listener = this
        holder.view.editListener = this
        holder.view.deleteListener=this
        holder.view.listenerOnCheck = this

        /*
        holder.view.textTitle.setText(bookListApapt[position].title)
        holder.view.textAuthor.setText(bookListApapt[position].author)
        holder.view.textStars.setText((bookListApapt[position].rating).toString())
        holder.view.textPublishWhen.setText(bookListApapt[position].publisher)
        holder.view.textUpdateAt.setText(bookListApapt[position].date)
        holder.view.imageBook.loadImage2(bookListApapt[position].photo.toString())

        holder.view.buttonBookListDetail.setOnClickListener {

            val btitle     = bookListApapt[position].title.toString()
            val bauthor   = bookListApapt[position].author.toString()

            val bdescription  = bookListApapt[position].description.toString()

            val bphoto  = bookListApapt[position].photo.toString()
            val bISBN     = bookListApapt[position].ISBN.toString()

            val bPublisher     = bookListApapt[position].publisher.toString()
            val bRating     = bookListApapt[position].rating.toString()
            val bGenres     = bookListApapt[position].genres.toString()
            val bDate     = bookListApapt[position].date.toString()
            val bQty     = bookListApapt[position].qty.toString()

            //val action = WantToReadFragmentDirections.actionItemMyBooksToBookDetailFragment(btitle)
            //Navigation.findNavController(it).navigate(action)
        }*/
    }

    override fun onButtonDetailClick(v: View) {
        Log.d("showtag", v.tag.toString())

        val action = WantToReadFragmentDirections.actionItemMyBooksToBookDetailFragment(
            v.tag.toString().toInt())
        Navigation.findNavController(v).navigate(action)


    }

    override fun onButtonUpdate(v: View) {
        val uuid = v.tag.toString().toInt()
        val action = WantToReadFragmentDirections.actionWantToEdit(uuid)

        Navigation.findNavController(v).navigate(action)
    }

    override fun onButtonEdit(v: View) {
        val uuid = v.tag.toString().toInt()
        val action = WantToReadFragmentDirections.actionWantToEdit(uuid)

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