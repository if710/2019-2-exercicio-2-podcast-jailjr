package br.ufpe.cin.android.podcast

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import org.jetbrains.anko.doAsync
import java.net.URL
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.uiThread


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        doAsync {
            try{
                //pegando o feed da url e jogando no parser
                var feed = URL("https://s3-us-west-1.amazonaws.com/podcasts.thepolyglotdeveloper.com/podcast.xml").readText()
                var itemFeedList = Parser.parse(feed)

                //db
                val db = ItemFeedDB.getDatabase(applicationContext).ItemFeedDAO().inserirFeed(itemFeedList)

                //adpter view
                uiThread {
                    recycleView.layoutManager = LinearLayoutManager(this@MainActivity)
                    recycleView.adapter = FeedAdapter(itemFeedList, this@MainActivity)
                    //recycleView.addItemDecoration(DividerItemDecoration(this, LinearLayoutManager.VERTICAL))

                }
            }
            catch (e: Throwable) {
                Log.w("ERRO ADAPTER OR LINK", e.message.toString())
            }
        }
    }
}
