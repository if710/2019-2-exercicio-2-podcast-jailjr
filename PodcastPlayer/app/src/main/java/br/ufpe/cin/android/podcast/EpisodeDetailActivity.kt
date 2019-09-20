package br.ufpe.cin.android.podcast

import android.graphics.BitmapFactory
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_episode_detail.*
import java.net.URL
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

class EpisodeDetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_episode_detail)

        val title = intent.getStringExtra("Title")
        val description = intent.getStringExtra("Description")
        val link = intent.getStringExtra("Link")

        ep_title.text = title
        ep_description.text = description
        ep_link.text = link


        doAsync {
            val url = URL(intent.getStringExtra("Imagem"))
            val bmp = BitmapFactory.decodeStream(url.openConnection().getInputStream())

            uiThread { ep_img.setImageBitmap(bmp)}

        }

    }
}
