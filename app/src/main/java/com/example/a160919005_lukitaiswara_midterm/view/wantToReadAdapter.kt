package com.example.a160919005_lukitaiswara_midterm.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.a160919005_lukitaiswara_midterm.R
import com.example.a160919005_lukitaiswara_midterm.model.Book
import com.example.a160919005_lukitaiswara_midterm.util.loadImage2
import kotlinx.android.synthetic.main.book_list_item.view.*

class wantToReadAdapter(val bookListApapt:ArrayList<Book>):RecyclerView.Adapter<wantToReadAdapter.BookViewHolderList>()  {


    class BookViewHolderList(var view: View): RecyclerView.ViewHolder(view)

    fun updateBookList(newBookList:List<Book>){
        bookListApapt.clear()
        bookListApapt.addAll(newBookList)
        notifyDataSetChanged()
    }

    fun updateBookListLike(newBookList:List<Book>){
        bookListApapt.clear()

        bookListApapt.addAll(newBookList)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookViewHolderList {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.book_list_item, parent, false)
        return BookViewHolderList(view)
    }


    override fun getItemCount(): Int {
        return bookListApapt.size
    }



    override fun onBindViewHolder(holder: BookViewHolderList, position: Int) {
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

            val action = WantToReadFragmentDirections.actionItemMyBooksToBookDetailFragment(btitle, bauthor, bdescription, bphoto,bISBN,
                bPublisher,bRating,bGenres,bDate,bQty)
            //Navigation.findNavController(it).navigate(action)
        }
    }
}