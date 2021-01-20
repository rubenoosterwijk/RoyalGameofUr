package com.example.royalgameofur.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "PlayerTable")
class Player(

    @ColumnInfo(name = "name")
    var name: String,

    @ColumnInfo(name = "score")
    var score: Int?,

    @ColumnInfo(name = "dice")
    var dice: Int?,

    @ColumnInfo(name = "movenumber")
    var movenumber: Int?,

    @PrimaryKey()
    @ColumnInfo(name = "id")
    var id: Int? = null
)