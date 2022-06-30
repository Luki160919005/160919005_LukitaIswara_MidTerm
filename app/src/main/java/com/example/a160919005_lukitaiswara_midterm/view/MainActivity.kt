package com.example.a160919005_lukitaiswara_midterm.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupWithNavController
import com.example.a160919005_lukitaiswara_midterm.R
import com.example.a160919005_lukitaiswara_midterm.model.BooksDatabase
import com.example.a160919005_lukitaiswara_midterm.model.MyBooks
import com.example.a160919005_lukitaiswara_midterm.util.createNotificationChannel
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private  lateinit var navController: NavController

    init {
        instance = this
    }

    companion object {
        private var instance: MainActivity? = null
        fun showNotification(title: String, content: String, icon: Int) {
            val channelId = "${instance?.packageName}-${instance?.getString(R.string.app_name)}"

            val builder =
                NotificationCompat.Builder(instance!!.applicationContext, channelId).apply {
                    setSmallIcon(icon)
                    setContentTitle(title)
                    setContentText(content)
                    setStyle(NotificationCompat.BigTextStyle())
                    priority = NotificationCompat.PRIORITY_DEFAULT
                    setAutoCancel(false)
                }

            val notificationManager = NotificationManagerCompat.from(instance!!.applicationContext)
            notificationManager.notify(1001, builder.build())

        }


    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        createNotificationChannel(this,
            NotificationManagerCompat.IMPORTANCE_DEFAULT, false,
            getString(R.string.app_name), "App notification channel.")

        navController = (supportFragmentManager.findFragmentById(R.id.fragmentHost) as NavHostFragment).navController

        //navController = Navigation.findNavController(this, R.id.hostFragment)
        NavigationUI.setupActionBarWithNavController(this, navController, drawerlayout)
        NavigationUI.setupWithNavController(navView, navController)
        bottomNav.setupWithNavController(navController)

        val navHostFragment = supportFragmentManager
            .findFragmentById(R.id.fragmentHost) as NavHostFragment
        val navController = navHostFragment.navController
        //navController = Navigation.findNavController(this, R.id.hostFragment)
        NavigationUI.setupActionBarWithNavController(this,navController)


        val dao = BooksDatabase.getInstance(this).booksDao()

        val BooksGenerator = listOf(
            MyBooks("Alexander Hamilton", "Ron Chernow", "Penguin Books"
                , "https://cdn.waterstones.com/bookjackets/large/9781/8002/9781800244399.jpg", "4.2",
                "Few figures in American history have been more hotly debated or more grossly misunderstood than Alexander Hamilton. Chernows biography gives Hamilton his due and sets the record straight, deftly illustrating that the political and economic greatness of today’s America is the result of Hamilton’s countless sacrifices to champion ideas that were often wildly disputed during his time. Chernow here recounts Hamilton turbulent life: an illegitimate, largely self-taught orphan from the Caribbean, he came out of nowhere to take America by storm, rising to become George Washington aide de camp in the Continental Army, coauthoring The Federalist Papers, founding the Bank of New York, leading the Federalist Party, and becoming the first Treasury Secretary of the United States.Historians have long told the story of Americas birth as the triumph of Jefferson’s democratic ideals over the aristocratic intentions of Hamilton. Chernow presents an entirely different man, whose legendary ambitions were motivated not merely by self-interest but by passionate patriotism and a stubborn will to build the foundations of American prosperity and power. His is a Hamilton far more human than we’ve encountered before from his shame about his birth to his fiery aspirations, from his intimate relationships with childhood friends to his titanic feuds with Jefferson, Madison, Adams, Monroe, and Burr, and from his highly public affair with Maria Reynolds to his loving marriage to his loyal wife Eliza. And never before has there been a more vivid account of Hamilton’s famous and mysterious death in a duel with Aaron Burr in July of 1804.",
                "Biography","March 29th 2005","5","",0,0),
            MyBooks("In Search of Lost Time", "Marcel Proust", "Modern Library"
                , "https://i.gr-assets.com/images/S/compressed.photo.goodreads.com/books/1325095874l/13329904.jpg",
                "4.35",
                "In Search of Lost Time is a novel in seven volumes. The novel began to take shape in 1909. Proust continued to work on it until his final illness in the autumn of 1922 forced him to break off. Proust established the structure early on, but even after volumes were initially finished he kept adding new material, and edited one volume after another for publication. The last three of the seven volumes contain oversights and fragmentary or unpolished passages as they existed in draft form at the death of the author; the publication of these parts was overseen by his brother Robert.",

                "Fiction","June 3rd 2003","10","",0,0),

            MyBooks("The Book of Five Rings", "Miyamoto Musashi", "Gramercy Books"
                , "https://kbimages1-a.akamaihd.net/58eefb8d-dccd-4421-b359-d9384dac86b6/1200/1200/False/the-book-of-five-rings-49.jpg",
                "4.02",
                "Shortly before his death in 1645, the undefeated swordsman Miyamoto Musashi retreated to a cave to live as a hermit. There he wrote five scrolls describing the true principles required for victory in the martial arts and on the battlefield. Instead of relying on religion or theory, Musashi based his writings on his own experience, observation, and reason.",

                "History","May 28th 1988","1","",0,0),
            MyBooks("Nietzsche: Beyond Good and Evi", "Friedrich Nietzsche", "Penguin Classics"
                , "https://images-na.ssl-images-amazon.com/images/I/81pLz6e3IJL.jpg",
                "4.01",
                "Frederich Nietzsche (1844-1900) became the chair of classical philology at Basel University at the age of 24 until his bad health forced him to retire in 1879. He divorced himself from society until his final collapse in 1899 when he became insane. A powerfully original thinker, Nietzsche's influence on subsequent writers, such as George Bernard Shaw, D.H. Lawrence, Thomas Mann and Jean-Paul Sartre, was considerable.",

                "Fiction","February 27th 2003","7","",0,0)

        )
        lifecycleScope.launch {
            BooksGenerator.forEach { dao.insertAll(it) }

        }

    }

    override fun onSupportNavigateUp(): Boolean {
        return NavigationUI.navigateUp(navController, drawerlayout) || super.onSupportNavigateUp()
    }
}