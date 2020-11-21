package app.zeta.foodhub.model.food

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(indices = [Index(value = ["id"], unique = true)])
data class Food(
    @PrimaryKey(autoGenerate = true) val recordId: Int =0,
    @ColumnInfo(name = "id") val id: Int,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "price") val price: Float,
    @ColumnInfo(name = "label") val label: String,
    @ColumnInfo(name = "category") val category: String,
    @ColumnInfo(name = "image") val image: String,
    @ColumnInfo(name = "description") val description: String
) : Parcelable