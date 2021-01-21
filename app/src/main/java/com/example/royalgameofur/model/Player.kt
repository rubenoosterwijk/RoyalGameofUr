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

    @ColumnInfo(name = "piecesonbench")
    var piecesonbench: Int?,

    @ColumnInfo(name = "dice")
    var dice: Int,

    @ColumnInfo(name = "movenumber")
    var movenumber: Int?,


    @ColumnInfo(name = "piece1")
    var piece1: Int,

    @ColumnInfo(name = "piece2")
    var piece2: Int,

    @ColumnInfo(name = "piece3")
    var piece3: Int,

    @ColumnInfo(name = "piece4")
    var piece4: Int,

    @ColumnInfo(name = "piece5")
    var piece5: Int,

    @ColumnInfo(name = "piece6")
    var piece6: Int,

    @ColumnInfo(name = "piece7")
    var piece7: Int,


    @PrimaryKey()
    @ColumnInfo(name = "id")
    var id: Int? = null
)