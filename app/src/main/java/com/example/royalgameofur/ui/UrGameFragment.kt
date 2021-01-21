package com.example.royalgameofur.ui

import android.media.Image
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.activityViewModels
import com.example.royalgameofur.R
import com.example.royalgameofur.model.Board
import com.example.royalgameofur.model.Piece
import com.example.royalgameofur.model.Player
import com.example.royalgameofur.repository.UrGameRepository
import com.example.royalgameofur.viewmodel.UrGameViewModel
import kotlinx.android.synthetic.main.fragment_board.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlin.random.Random

class UrGameFragment : Fragment() {

    private val viewModel: UrGameViewModel by activityViewModels()
    private lateinit var repository: UrGameRepository

    private val black = R.drawable.button_black
    private val red = R.drawable.button_red


    val routeR = mapOf(0 to "p0", 1 to "p1", 2 to "p2", 3 to "p3")

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        repository = UrGameRepository(requireContext())

        initGame()

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

    private fun clearBoard_DB() {
        CoroutineScope(Dispatchers.IO).launch {
            for (i in 0..20) {
                repository.insertBoard(Board(i, 0))
            }
        }
    }

    private fun movePiece(piece: Piece, boardPlace: Int) {

    }

    private fun initGame() {
        clearBoard()
        fillPlayers()
    }

//    private fun givePieces() {
//        repository.insertPiece()
//    }


    private fun RollDice(id: Int) {
        val randomnumber = Random.nextInt(0, 5)

        CoroutineScope(Dispatchers.IO).launch {
            var player = repository.getPlayerInfo(id)
            player.dice = randomnumber
            repository.updatePlayer(player)
            CalculateMove(id)
        }
    }

//    private checkOnBench(){
//        //
//    }

    private fun CalculateMove(id: Int) {
        CoroutineScope(Dispatchers.IO).launch {
            val player = repository.getPlayerInfo(id)
            var dice = player.dice
            var score = player.score
            withContext(Dispatchers.Main) {
                if (player.piecesonbench!! == 0) {
                    if (id == 1) {
                        r0.setImageResource(0)
                    } else if (id == 2) {
                        z0.setImageResource(0)
                    }
                } else if (player.piecesonbench!! > 0) {
                    if (id == 1) {
                        r0.setImageResource(red)
                    } else if (id == 2) {
                        z0.setImageResource(black)
                    }
                }


                if (id == 1) {
                    println("dice$dice")
                    val oldLocation = player.piece1
                    println("oldLocation$oldLocation")
                    var newLocation: Int = (oldLocation + dice)
                    println("newLocation$newLocation")

                    if (newLocation > 15) {
                        newLocation = oldLocation
                        println("newLocation$newLocation")
                    }

                        player.piece1 = newLocation
                        repository.updatePlayer(player)


                    println("XD"+ player.piece1)
                    if (player.piece1 in 1..15) {

                        when (player.piece1) {
                            0 -> r0.setImageResource(red)
                            1 -> r1.setImageResource(red)
                            2 -> r2.setImageResource(red)
                            3 -> r3.setImageResource(red)
                            4 -> r4.setImageResource(red)
                            5 -> p5.setImageResource(red)
                            6 -> p6.setImageResource(red)
                            7 -> p7.setImageResource(red)
                            8 -> p8.setImageResource(red)
                            9 -> p9.setImageResource(red)
                            10 -> p10.setImageResource(red)
                            11 -> p11.setImageResource(red)
                            12 -> p12.setImageResource(red)
                            13 -> r13.setImageResource(red)
                            14 -> r14.setImageResource(red)
                            15 -> earnPoint(id)
                        }
                        if (oldLocation != 0 && dice != 0) {
                            when (oldLocation) {
                                1 -> r1.setImageResource(0)
                                2 -> r2.setImageResource(0)
                                3 -> r3.setImageResource(0)
                                4 -> r4.setImageResource(0)
                                5 -> p5.setImageResource(0)
                                6 -> p6.setImageResource(0)
                                7 -> p7.setImageResource(0)
                                8 -> p8.setImageResource(0)
                                9 -> p9.setImageResource(0)
                                10 -> p10.setImageResource(0)
                                11 -> p11.setImageResource(0)
                                12 -> p12.setImageResource(0)
                                13 -> r13.setImageResource(0)
                                14 -> r14.setImageResource(0)
                                15 -> r15.setImageResource(0)
                            }
                        }
                    }
                }
            }
        }
    }

    private fun earnPoint(id: Int){
        CoroutineScope(Dispatchers.IO).launch {
            var player = repository.getPlayerInfo(id)
            var score = player.score
            score = score?.plus(1)
            println(score)
            repository.updatePlayer(player)
//            repository.updatePlayer(Player(player.name, score, player.piecesonbench, player.dice, player.piece1,player.piece2, player.piece3,player.piece4,player.piece5,player.piece6,player.piece6,player.piece7))
        }
    }


    private fun clearBoard() {
        z13.setImageResource(0)
        z14.setImageResource(0)
        z15.setImageResource(0)
//        z0.setImageResource(0)
        z1.setImageResource(0)
        z2.setImageResource(0)
        z2.setImageResource(0)
        z3.setImageResource(0)
        z4.setImageResource(0)
        p12.setImageResource(0)
        p11.setImageResource(0)
        p10.setImageResource(0)
        p9.setImageResource(0)
        p11.setImageResource(0)
        p12.setImageResource(0)
        p8.setImageResource(0)
        p7.setImageResource(0)
        p6.setImageResource(0)
        p5.setImageResource(0)
        r13.setImageResource(0)
        r14.setImageResource(0)
        r15.setImageResource(0)
//        r0.setImageResource(0)
        r1.setImageResource(0)
        r2.setImageResource(0)
        r3.setImageResource(0)
        r4.setImageResource(0)

        clearBoard_DB()
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding = inflater.inflate(R.layout.fragment_board, container, false)
        return binding
    }


    private fun fillPlayers() {
        viewModel.getPlayer1().observe(viewLifecycleOwner, { player1 ->
            name1.text = player1.name
            diceScorep1.text = player1.dice.toString()
            piecesleftp1.text = player1.piecesonbench.toString()
            scorep1.text = player1.score.toString()
        })
        viewModel.getPlayer2().observe(viewLifecycleOwner, { player2 ->
            name2.text = player2.name
            diceScorep2.text = player2.dice.toString()
            piecesleftp2.text = player2.piecesonbench.toString()
            scorep2.text = player2.score.toString()
        })
    }

}