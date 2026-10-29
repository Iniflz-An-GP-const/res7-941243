package gnrco.res7.mobile.data.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import gnrco.res7.mobile.data.model.Difficulty
import java.time.LocalDateTime

@Entity("high_scores")
data class HighScore(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val difficulty: Difficulty,
    val moves: Int,
    @ColumnInfo(name = "time_ms") val timeMs: Long,
    val timestamp: LocalDateTime,
)
