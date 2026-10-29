package gnrco.res7.mobile.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import gnrco.res7.mobile.data.database.converter.Converters
import gnrco.res7.mobile.data.database.dao.HighScoreDao
import gnrco.res7.mobile.data.entity.HighScore

@Database(
    entities = [HighScore::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(Converters::class)
abstract class GCEOEAppDatabase : RoomDatabase() {

    abstract fun highScoreDao(): HighScoreDao
}
