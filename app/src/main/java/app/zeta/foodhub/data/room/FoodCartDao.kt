package app.zeta.foodhub.data.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import app.zeta.foodhub.model.food.FoodCart
import io.reactivex.Completable

@Dao
interface FoodCartDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertFood(vararg food: FoodCart) : Completable

    @Query("SELECT * FROM FoodCart")
    fun getAll(): List<FoodCart>

    @Query("DELETE FROM FoodCart")
    fun deleteAll()
}