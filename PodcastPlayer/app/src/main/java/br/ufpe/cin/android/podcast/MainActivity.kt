package br.ufpe.cin.android.podcast

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import org.jetbrains.anko.doAsync
import java.net.URL
import android.util.Log


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        doAsync {
            try{
                //pegando o feed da url e jogando no parser
                var feed = URL("https://s3-us-west-1.amazonaws.com/podcasts.thepolyglotdeveloper.com/podcast.xml").readText()
                var itemFeedList = Parser.parse(feed)
            }
            catch (e: Throwable) {
                Log.w("ERRO: LINK QUEBRADO", e.message.toString())
            }
        }
    }
}
