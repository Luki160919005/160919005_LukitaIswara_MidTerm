package com.example.a160919005_lukitaiswara_midterm.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.a160919005_lukitaiswara_midterm.R
import com.example.a160919005_lukitaiswara_midterm.model.Book
import com.example.a160919005_lukitaiswara_midterm.util.loadImage
import com.example.a160919005_lukitaiswara_midterm.util.loadImage2
import kotlinx.android.synthetic.main.book_list_item.view.*
import kotlinx.android.synthetic.main.fragment_book_detail.view.*

class BookListAdapter(val bookList:ArrayList<Book>):RecyclerView.Adapter<BookListAdapter.BookViewHolder>() {

    class BookViewHolder(var view: View): RecyclerView.ViewHolder(view)

    fun updateBookList(newBookList:List<Book>){
        bookList.clear()
        bookList.addAll(newBookList)
        notifyDataSetChanged()
    }

    fun updateBookListLike(newBookList:List<Book>){
        bookList.clear()

        bookList.addAll(newBookList)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.book_list_item, parent, false)
        return BookViewHolder(view)
    }

    override fun onBindViewHolder(holder: BookViewHolder, position: Int) {
        holder.view.textTitle.setText(bookList[position].title)
        holder.view.textAuthor.setText(bookList[position].author)
        holder.view.textStars.setText((bookList[position].rating).toString())
        holder.view.textPublishWhen.setText(bookList[position].publisher)
        holder.view.textUpdateAt.setText(bookList[position].date)
        holder.view.imageBook.loadImage2(bookList[position].photo.toString())

        holder.view.buttonBookListDetail.setOnClickListener {

            val btitle     = bookList[position].title.toString()
            val bauthor   = bookList[position].author.toString()

            val bdescription  = bookList[position].description.toString()

            val bphoto  = bookList[position].photo.toString()
            val bISBN     = bookList[position].ISBN.toString()

            val bPublisher     = bookList[position].publisher.toString()
            val bRating     = bookList[position].rating.toString()
            val bGenres     = bookList[position].genres.toString()
            val bDate     = bookList[position].date.toString()
            val bQty     = bookList[position].qty.toString()

            val action = BookListFragmentDirections.actionItemHomeToBookDetailFragment(btitle, bauthor, bdescription, bphoto,bISBN,
                bPublisher,bRating,bGenres,bDate,bQty)
            Navigation.findNavController(it).navigate(action)
        }


    }

    override fun getItemCount(): Int {
        return bookList.size
    }
}