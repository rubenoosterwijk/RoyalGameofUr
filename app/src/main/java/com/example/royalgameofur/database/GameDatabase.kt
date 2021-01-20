package com.example.royalgameofur.database

import android.content.Context
import androidx.room.*
import com.example.royalgameofur.converter.Converters
import com.example.royalgameofur.dao.UrGameDao
import com.example.royalgameofur.model.Board
import com.example.royalgameofur.model.Player

@Database(entities = [Board::class, Player::class], version = 3, exportSchema = false)
@TypeConverters(Converters::class)
abstract class GameDatabase : RoomDatabase() {

    abstract fun UrGameDao(): UrGameDao

    companion object {
        private const val DATABASE_NAME = "GAME_DB  "

        @Volatile
        private var INSTANCE: GameDatabase? = null

        fun getDatabase(context: Context): GameDatabase? {
            if (INSTANCE == null) {
                synchronized(GameDatabase::class.java) {
                    if (INSTANCE == null) {
                        INSTANCE = Room.databaseBuilder(
                            context.applicationContext,
                            GameDatabase::class.java,
                            DATABASE_NAME
                        )
                            .fallbackToDestructiveMigration()
                            .build()
                    }
                }
            }
            return INSTANCE
        }

    }

}
