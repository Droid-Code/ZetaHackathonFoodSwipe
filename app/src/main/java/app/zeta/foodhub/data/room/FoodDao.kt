package app.zeta.foodhub.data.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import app.zeta.foodhub.model.food.Food
import io.reactivex.Completable

@Dao
interface FoodDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertFood(vararg food: Food) : Completable
}