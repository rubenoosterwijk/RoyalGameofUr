package com.example.royalgameofur.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "PieceTable", primaryKeys = ["playerID", "pieceNumber"])
class Piece (

    @ColumnInfo(name = "playerID")
    var playerID: Int,

    @ColumnInfo(name = "pieceNumber")
    var pieceNumber: Int,

    @ColumnInfo(name = "boardPlace")
    var boardPlace: Int

)