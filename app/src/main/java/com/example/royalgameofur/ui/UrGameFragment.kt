package com.example.royalgameofur.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.example.royalgameofur.R
import com.example.royalgameofur.repository.UrGameRepository
import com.example.royalgameofur.viewmodel.UrGameViewModel
import kotlinx.android.synthetic.main.fragment_board.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlin.random.Random

class UrGameFragment : Fragment() {

    private val viewModel: UrGameViewModel by activityViewModels()
    private lateinit var repository: UrGameRepository

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        repository = UrGameRepository(requireContext())
        fillPlayers()
        clearBoard()

        btn_roll_p2.visibility = View.INVISIBLE

        btn_roll_p1.setOnClickListener {
            RollDice(1)
            btn_roll_p1.visibility = View.INVISIBLE
            btn_roll_p2.visibility = View.VISIBLE
        }

        btn_roll_p2.setOnClickListener {
            RollDice(2)
            btn_roll_p2.visibility = View.INVISIBLE
            btn_roll_p1.visibility = View.VISIBLE
        }
    }

    private fun fillBoard() {
        TODO("Not yet implemented")
    }

    private fun RollDice(id: Int) {
        val randomnumber = Random.nextInt(0, 5)

        CoroutineScope(Dispatchers.IO).launch {
            var player = repository.getScore(id)
            player.dice = randomnumber
            repository.insertPlayer(player)
        }
    }

    private fun clearBoard() {
        p0.setImageResource(0)
        p1.setImageResource(0)
        p2.setImageResource(0)
//        p3.setImageResource(0)
        p4.setImageResource(0)
        p5.setImageResource(0)
        p5.setImageResource(0)
        p6.setImageResource(0)
        p7.setImageResource(0)
        p8.setImageResource(0)
        p9.setImageResource(0)
        p10.setImageResource(0)
        p11.setImageResource(0)
        p12.setImageResource(0)
        p13.setImageResource(0)
        p14.setImageResource(0)
        p15.setImageResource(0)
        p16.setImageResource(0)
        p17.setImageResource(0)
        p18.setImageResource(0)
//        p19.setImageResource(0)
        p20.setImageResource(0)
        p21.setImageResource(0)
        p22.setImageResource(0)
        p23.setImageResource(0)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_board, container, false)
    }


    private fun fillPlayers() {
        viewModel.getPlayer1().observe(viewLifecycleOwner, { player1 ->
            name1.text = player1.name
            diceScorep1.text = player1.dice.toString()
            piecesleftp1.text = player1.piecesleft.toString()
            scorep1.text = player1.score.toString()
        })
        viewModel.getPlayer2().observe(viewLifecycleOwner, { player2 ->
            name2.text = player2.name
            diceScorep2.text = player2.dice.toString()
            piecesleftp2.text = player2.piecesleft.toString()
            scorep2.text = player2.score.toString()
        })
    }

}