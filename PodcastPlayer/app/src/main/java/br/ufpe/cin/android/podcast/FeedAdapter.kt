package br.ufpe.cin.android.podcast

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.itemlista.view.*
import android.content.Intent
import android.widget.Toast

class FeedAdapter(private val itemFeedList: List<ItemFeed>, private val c: Context ):RecyclerView.Adapter<FeedAdapter.ViewHolder>()
{
    class ViewHolder( item: View ): RecyclerView.ViewHolder(item){
        val title = item.item_title
        val date = item.item_date
        val button = item.item_action

        init{
            button.setOnClickListener{
                Toast.makeText(itemView.context,"Downloading...",Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun getItemCount(): Int {
        return itemFeedList.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FeedAdapter.ViewHolder {
        val view = LayoutInflater.from(c).inflate(R.layout.itemlista,parent,false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val itemFeed = itemFeedList[position]
        holder.title.text = itemFeed.title
        holder.date.text = itemFeed.pubDate

        holder.title.setOnClickListener{
            val intent = Intent(c, EpisodeDetailActivity::class.java)
            intent.putExtra("Title", itemFeed.title)
            intent.putExtra("Description", itemFeed.description)
            intent.putExtra("Imagem", itemFeed.imageUrl)
            intent.putExtra("Link", itemFeed.link)
            c.startActivity(intent)
        }
    }

}