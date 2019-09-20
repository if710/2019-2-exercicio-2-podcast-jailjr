package br.ufpe.cin.android.podcast

import androidx.room.*

@Dao
interface ItemFeedDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun inserir(itemFeed: ItemFeed)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun inserirFeed(itemFeed: List<ItemFeed>)

    @Query("SELECT * FROM ItemFeedTable")
    fun buscarTudo(): List<ItemFeed>

    @Query("SELECT * FROM ItemFeedTable WHERE title LIKE :x")
    fun buscar(x:String): ItemFeed

}
