package com.example.royalgameofur.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.madlevel4task2v2.R
import com.example.madlevel4task2v2.model.Game
import kotlinx.android.synthetic.main.item_game.view.*
import java.util.*

class GameAdapter2(private val games: List<Game>) : RecyclerView.Adapter<GameAdapter2.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_game,
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return games.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.databind(games[position])
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun databind(game: Game) {
            when (game.result) {
                Game.Result.LOSE -> itemView.tvResult.text =
                    itemView.context.resources.getString(R.string.you_lose)
                Game.Result.DRAW -> itemView.tvResult.text =
                    itemView.context.resources.getString(R.string.draw)
                Game.Result.WIN -> itemView.tvResult.text =
                    itemView.context.resources.getString(R.string.you_win)
            }

            itemView.tvDate.text = game.date.toString()

            itemView.ivComputer.setImageResource(
                itemView.context.resources.getIdentifier(
                    game.moveComputer.toString().toLowerCase(Locale.getDefault()),
                    "drawable",
                    itemView.context.packageName
                )
            )

            itemView.ivYou.setImageResource(
                itemView.context.resources.getIdentifier(
                    game.movePlayer.toString().toLowerCase(Locale.getDefault()),
                    "drawable",
                    itemView.context.packageName
                )
            )
        }
    }
}