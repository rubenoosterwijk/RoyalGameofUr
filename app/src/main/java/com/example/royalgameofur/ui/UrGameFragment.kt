package com.example.royalgameofur.ui

import android.media.Image
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.activityViewModels
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
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

    private lateinit var navController: NavController
    private val black = R.drawable.button_black
    private val red = R.drawable.button_red

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        repository = UrGameRepository(requireContext())
        navController = findNavController()

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

    private fun initGame() {
        clearBoard()
        fillPlayers()
    }


    private fun RollDice(id: Int) {
        val randomnumber = Random.nextInt(0, 5)

        CoroutineScope(Dispatchers.IO).launch {
            var player = repository.getPlayerInfo(id)
            player.dice = randomnumber
            var movenumber = player.movenumber
            println(movenumber)
            println("move1")
            movenumber = movenumber!! + 1
            println(movenumber)
            println("move2")
            player.movenumber = movenumber
            repository.updatePlayer(player)
            calculateMove(id)
            println(player.movenumber)
            println("move3")
        }
    }

    private fun calculateMove(id: Int) {
        CoroutineScope(Dispatchers.IO).launch {
            val player = repository.getPlayerInfo(id)
            var dice = player.dice
            var validMove: Boolean
            var piecesBenched = player.piecesonbench
            var score = player.score
            var move = player.movenumber
            println(move)
            println("move")

            withContext(Dispatchers.Main) {
                if (piecesBenched!! == 0) {
                    if (id == 1) {
                        r0.setImageResource(0)
                    } else if (id == 2) {
                        z0.setImageResource(0)
                    }
                } else if (piecesBenched > 0) {
                    if (id == 1) {
                        r0.setImageResource(red)
                    } else if (id == 2) {
                        z0.setImageResource(black)
                    }
                }

                if (score == 0) {
                    println("dice$dice")
                    val oldLocation = player.piece1
                    println("oldLocation$oldLocation")
                    var newLocation: Int = (oldLocation + dice)
                    println("newLocation$newLocation")

                    if (newLocation > 15) {
                        newLocation = oldLocation
                        println("newLocation$newLocation")
                        validMove = false
                    } else {
                        validMove = true
                    }
                    player.piece1 = newLocation
                    repository.updatePlayer(player)

                    println("XD" + player.piece1)
                    if (validMove) {
                        if (id == 1) {
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

                            if (oldLocation != 0 && dice != 0 && validMove) {
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

                        if (id == 2) {
                            when (player.piece1) {
                                0 -> z0.setImageResource(black)
                                1 -> z1.setImageResource(black)
                                2 -> z2.setImageResource(black)
                                3 -> z3.setImageResource(black)
                                4 -> z4.setImageResource(black)
                                5 -> p5.setImageResource(black)
                                6 -> p6.setImageResource(black)
                                7 -> p7.setImageResource(black)
                                8 -> p8.setImageResource(black)
                                9 -> p9.setImageResource(black)
                                10 -> p10.setImageResource(black)
                                11 -> p11.setImageResource(black)
                                12 -> p12.setImageResource(black)
                                13 -> z13.setImageResource(black)
                                14 -> z14.setImageResource(black)
                                15 -> earnPoint(id)
                            }
                            if (oldLocation != 0 && dice != 0 && validMove) {
                                when (oldLocation) {
                                    1 -> z1.setImageResource(0)
                                    2 -> z2.setImageResource(0)
                                    3 -> z3.setImageResource(0)
                                    4 -> z4.setImageResource(0)
                                    5 -> p5.setImageResource(0)
                                    6 -> p6.setImageResource(0)
                                    7 -> p7.setImageResource(0)
                                    8 -> p8.setImageResource(0)
                                    9 -> p9.setImageResource(0)
                                    10 -> p10.setImageResource(0)
                                    11 -> p11.setImageResource(0)
                                    12 -> p12.setImageResource(0)
                                    13 -> z13.setImageResource(0)
                                    14 -> z14.setImageResource(0)
                                    15 -> z15.setImageResource(0)
                                }
                            }
                        }

                        if (oldLocation == 0 && newLocation != 0 && validMove) {
                            piecesBenched -= 1
                            player.piecesonbench = piecesBenched
                            repository.updatePlayer(player)
                        }

                    }


                }

                if (score == 1) {
                    println("dice$dice")
                    val oldLocation = player.piece2
                    println("oldLocation$oldLocation")
                    var newLocation: Int = (oldLocation + dice)
                    println("newLocation$newLocation")

                    if (newLocation > 15) {
                        newLocation = oldLocation
                        println("newLocation$newLocation")
                        validMove = false
                    } else {
                        validMove = true
                    }
                    player.piece2 = newLocation
                    repository.updatePlayer(player)

                    println("XD" + player.piece2)
                    if (validMove) {
                        if (id == 1) {
                            when (player.piece2) {
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

                            if (oldLocation != 0 && dice != 0 && validMove) {
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

                        if (id == 2) {
                            when (player.piece2) {
                                0 -> z0.setImageResource(black)
                                1 -> z1.setImageResource(black)
                                2 -> z2.setImageResource(black)
                                3 -> z3.setImageResource(black)
                                4 -> z4.setImageResource(black)
                                5 -> p5.setImageResource(black)
                                6 -> p6.setImageResource(black)
                                7 -> p7.setImageResource(black)
                                8 -> p8.setImageResource(black)
                                9 -> p9.setImageResource(black)
                                10 -> p10.setImageResource(black)
                                11 -> p11.setImageResource(black)
                                12 -> p12.setImageResource(black)
                                13 -> z13.setImageResource(black)
                                14 -> z14.setImageResource(black)
                                15 -> earnPoint(id)
                            }
                            if (oldLocation != 0 && dice != 0 && validMove) {
                                when (oldLocation) {
                                    1 -> z1.setImageResource(0)
                                    2 -> z2.setImageResource(0)
                                    3 -> z3.setImageResource(0)
                                    4 -> z4.setImageResource(0)
                                    5 -> p5.setImageResource(0)
                                    6 -> p6.setImageResource(0)
                                    7 -> p7.setImageResource(0)
                                    8 -> p8.setImageResource(0)
                                    9 -> p9.setImageResource(0)
                                    10 -> p10.setImageResource(0)
                                    11 -> p11.setImageResource(0)
                                    12 -> p12.setImageResource(0)
                                    13 -> z13.setImageResource(0)
                                    14 -> z14.setImageResource(0)
                                    15 -> z15.setImageResource(0)
                                }
                            }
                        }

                        if (oldLocation == 0 && newLocation != 0 && validMove) {
                            piecesBenched -= 1
                            player.piecesonbench = piecesBenched
                            repository.updatePlayer(player)
                        }
                    }
                }

                if (score == 2) {
                    println("dice$dice")
                    val oldLocation = player.piece3
                    println("oldLocation$oldLocation")
                    var newLocation: Int = (oldLocation + dice)
                    println("newLocation$newLocation")

                    if (newLocation > 15) {
                        newLocation = oldLocation
                        println("newLocation$newLocation")
                        validMove = false
                    } else {
                        validMove = true
                    }
                    player.piece3 = newLocation
                    repository.updatePlayer(player)

                    println("XD" + player.piece3)
                    if (validMove) {
                        if (id == 1) {
                            when (player.piece3) {
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

                            if (oldLocation != 0 && dice != 0 && validMove) {
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

                        if (id == 2) {
                            when (player.piece3) {
                                0 -> z0.setImageResource(black)
                                1 -> z1.setImageResource(black)
                                2 -> z2.setImageResource(black)
                                3 -> z3.setImageResource(black)
                                4 -> z4.setImageResource(black)
                                5 -> p5.setImageResource(black)
                                6 -> p6.setImageResource(black)
                                7 -> p7.setImageResource(black)
                                8 -> p8.setImageResource(black)
                                9 -> p9.setImageResource(black)
                                10 -> p10.setImageResource(black)
                                11 -> p11.setImageResource(black)
                                12 -> p12.setImageResource(black)
                                13 -> z13.setImageResource(black)
                                14 -> z14.setImageResource(black)
                                15 -> earnPoint(id)
                            }
                            if (oldLocation != 0 && dice != 0 && validMove) {
                                when (oldLocation) {
                                    1 -> z1.setImageResource(0)
                                    2 -> z2.setImageResource(0)
                                    3 -> z3.setImageResource(0)
                                    4 -> z4.setImageResource(0)
                                    5 -> p5.setImageResource(0)
                                    6 -> p6.setImageResource(0)
                                    7 -> p7.setImageResource(0)
                                    8 -> p8.setImageResource(0)
                                    9 -> p9.setImageResource(0)
                                    10 -> p10.setImageResource(0)
                                    11 -> p11.setImageResource(0)
                                    12 -> p12.setImageResource(0)
                                    13 -> z13.setImageResource(0)
                                    14 -> z14.setImageResource(0)
                                    15 -> z15.setImageResource(0)
                                }
                            }
                        }

                        if (oldLocation == 0 && newLocation != 0 && validMove) {
                            piecesBenched -= 1
                            player.piecesonbench = piecesBenched
                            repository.updatePlayer(player)
                        }
                    }
                }

                if (score == 3) {
                    println("dice$dice")
                    val oldLocation = player.piece4
                    println("oldLocation$oldLocation")
                    var newLocation: Int = (oldLocation + dice)
                    println("newLocation$newLocation")

                    if (newLocation > 15) {
                        newLocation = oldLocation
                        println("newLocation$newLocation")
                        validMove = false
                    } else {
                        validMove = true
                    }
                    player.piece4 = newLocation
                    repository.updatePlayer(player)

                    println("XD" + player.piece4)
                    if (validMove) {
                        if (id == 1) {
                            when (player.piece4) {
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

                            if (oldLocation != 0 && dice != 0 && validMove) {
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

                        if (id == 2) {
                            when (player.piece4) {
                                0 -> z0.setImageResource(black)
                                1 -> z1.setImageResource(black)
                                2 -> z2.setImageResource(black)
                                3 -> z3.setImageResource(black)
                                4 -> z4.setImageResource(black)
                                5 -> p5.setImageResource(black)
                                6 -> p6.setImageResource(black)
                                7 -> p7.setImageResource(black)
                                8 -> p8.setImageResource(black)
                                9 -> p9.setImageResource(black)
                                10 -> p10.setImageResource(black)
                                11 -> p11.setImageResource(black)
                                12 -> p12.setImageResource(black)
                                13 -> z13.setImageResource(black)
                                14 -> z14.setImageResource(black)
                                15 -> earnPoint(id)
                            }
                            if (oldLocation != 0 && dice != 0 && validMove) {
                                when (oldLocation) {
                                    1 -> z1.setImageResource(0)
                                    2 -> z2.setImageResource(0)
                                    3 -> z3.setImageResource(0)
                                    4 -> z4.setImageResource(0)
                                    5 -> p5.setImageResource(0)
                                    6 -> p6.setImageResource(0)
                                    7 -> p7.setImageResource(0)
                                    8 -> p8.setImageResource(0)
                                    9 -> p9.setImageResource(0)
                                    10 -> p10.setImageResource(0)
                                    11 -> p11.setImageResource(0)
                                    12 -> p12.setImageResource(0)
                                    13 -> z13.setImageResource(0)
                                    14 -> z14.setImageResource(0)
                                    15 -> z15.setImageResource(0)
                                }
                            }
                        }

                        if (oldLocation == 0 && newLocation != 0 && validMove) {
                            piecesBenched -= 1
                            player.piecesonbench = piecesBenched
                            repository.updatePlayer(player)
                        }
                    }
                }

                if (score == 4) {
                    println("dice$dice")
                    val oldLocation = player.piece5
                    println("oldLocation$oldLocation")
                    var newLocation: Int = (oldLocation + dice)
                    println("newLocation$newLocation")

                    if (newLocation > 15) {
                        newLocation = oldLocation
                        println("newLocation$newLocation")
                        validMove = false
                    } else {
                        validMove = true
                    }
                    player.piece5 = newLocation
                    repository.updatePlayer(player)

                    println("XD" + player.piece5)
                    if (validMove) {
                        if (id == 1) {
                            when (player.piece5) {
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

                            if (oldLocation != 0 && dice != 0 && validMove) {
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

                        if (id == 2) {
                            when (player.piece5) {
                                0 -> z0.setImageResource(black)
                                1 -> z1.setImageResource(black)
                                2 -> z2.setImageResource(black)
                                3 -> z3.setImageResource(black)
                                4 -> z4.setImageResource(black)
                                5 -> p5.setImageResource(black)
                                6 -> p6.setImageResource(black)
                                7 -> p7.setImageResource(black)
                                8 -> p8.setImageResource(black)
                                9 -> p9.setImageResource(black)
                                10 -> p10.setImageResource(black)
                                11 -> p11.setImageResource(black)
                                12 -> p12.setImageResource(black)
                                13 -> z13.setImageResource(black)
                                14 -> z14.setImageResource(black)
                                15 -> earnPoint(id)
                            }
                            if (oldLocation != 0 && dice != 0 && validMove) {
                                when (oldLocation) {
                                    1 -> z1.setImageResource(0)
                                    2 -> z2.setImageResource(0)
                                    3 -> z3.setImageResource(0)
                                    4 -> z4.setImageResource(0)
                                    5 -> p5.setImageResource(0)
                                    6 -> p6.setImageResource(0)
                                    7 -> p7.setImageResource(0)
                                    8 -> p8.setImageResource(0)
                                    9 -> p9.setImageResource(0)
                                    10 -> p10.setImageResource(0)
                                    11 -> p11.setImageResource(0)
                                    12 -> p12.setImageResource(0)
                                    13 -> z13.setImageResource(0)
                                    14 -> z14.setImageResource(0)
                                    15 -> z15.setImageResource(0)
                                }
                            }
                        }

                        if (oldLocation == 0 && newLocation != 0 && validMove) {
                            piecesBenched -= 1
                            player.piecesonbench = piecesBenched
                            repository.updatePlayer(player)
                        }
                    }
                }

                if (score == 5) {
                    println("dice$dice")
                    val oldLocation = player.piece6
                    println("oldLocation$oldLocation")
                    var newLocation: Int = (oldLocation + dice)
                    println("newLocation$newLocation")

                    if (newLocation > 15) {
                        newLocation = oldLocation
                        println("newLocation$newLocation")
                        validMove = false
                    } else {
                        validMove = true
                    }
                    player.piece6 = newLocation
                    repository.updatePlayer(player)

                    println("XD" + player.piece6)
                    if (validMove) {
                        if (id == 1) {
                            when (player.piece6) {
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

                            if (oldLocation != 0 && dice != 0 && validMove) {
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

                        if (id == 2) {
                            when (player.piece6) {
                                0 -> z0.setImageResource(black)
                                1 -> z1.setImageResource(black)
                                2 -> z2.setImageResource(black)
                                3 -> z3.setImageResource(black)
                                4 -> z4.setImageResource(black)
                                5 -> p5.setImageResource(black)
                                6 -> p6.setImageResource(black)
                                7 -> p7.setImageResource(black)
                                8 -> p8.setImageResource(black)
                                9 -> p9.setImageResource(black)
                                10 -> p10.setImageResource(black)
                                11 -> p11.setImageResource(black)
                                12 -> p12.setImageResource(black)
                                13 -> z13.setImageResource(black)
                                14 -> z14.setImageResource(black)
                                15 -> earnPoint(id)
                            }
                            if (oldLocation != 0 && dice != 0 && validMove) {
                                when (oldLocation) {
                                    1 -> z1.setImageResource(0)
                                    2 -> z2.setImageResource(0)
                                    3 -> z3.setImageResource(0)
                                    4 -> z4.setImageResource(0)
                                    5 -> p5.setImageResource(0)
                                    6 -> p6.setImageResource(0)
                                    7 -> p7.setImageResource(0)
                                    8 -> p8.setImageResource(0)
                                    9 -> p9.setImageResource(0)
                                    10 -> p10.setImageResource(0)
                                    11 -> p11.setImageResource(0)
                                    12 -> p12.setImageResource(0)
                                    13 -> z13.setImageResource(0)
                                    14 -> z14.setImageResource(0)
                                    15 -> z15.setImageResource(0)
                                }
                            }
                        }

                        if (oldLocation == 0 && newLocation != 0 && validMove) {
                            piecesBenched -= 1
                            player.piecesonbench = piecesBenched
                            repository.updatePlayer(player)
                        }
                    }
                }

                if (score == 6) {
                    println("dice$dice")
                    val oldLocation = player.piece7
                    println("oldLocation$oldLocation")
                    var newLocation: Int = (oldLocation + dice)
                    println("newLocation$newLocation")

                    if (newLocation > 15) {
                        newLocation = oldLocation
                        println("newLocation$newLocation")
                        validMove = false
                    } else {
                        validMove = true
                    }
                    player.piece7 = newLocation
                    repository.updatePlayer(player)

                    println("XD" + player.piece7)
                    if (validMove) {
                        if (id == 1) {
                            when (player.piece7) {
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

                            if (oldLocation != 0 && dice != 0 && validMove) {
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

                        if (id == 2) {
                            when (player.piece7) {
                                0 -> z0.setImageResource(black)
                                1 -> z1.setImageResource(black)
                                2 -> z2.setImageResource(black)
                                3 -> z3.setImageResource(black)
                                4 -> z4.setImageResource(black)
                                5 -> p5.setImageResource(black)
                                6 -> p6.setImageResource(black)
                                7 -> p7.setImageResource(black)
                                8 -> p8.setImageResource(black)
                                9 -> p9.setImageResource(black)
                                10 -> p10.setImageResource(black)
                                11 -> p11.setImageResource(black)
                                12 -> p12.setImageResource(black)
                                13 -> z13.setImageResource(black)
                                14 -> z14.setImageResource(black)
                                15 -> earnPoint(id)
                            }
                            if (oldLocation != 0 && dice != 0 && validMove) {
                                when (oldLocation) {
                                    1 -> z1.setImageResource(0)
                                    2 -> z2.setImageResource(0)
                                    3 -> z3.setImageResource(0)
                                    4 -> z4.setImageResource(0)
                                    5 -> p5.setImageResource(0)
                                    6 -> p6.setImageResource(0)
                                    7 -> p7.setImageResource(0)
                                    8 -> p8.setImageResource(0)
                                    9 -> p9.setImageResource(0)
                                    10 -> p10.setImageResource(0)
                                    11 -> p11.setImageResource(0)
                                    12 -> p12.setImageResource(0)
                                    13 -> z13.setImageResource(0)
                                    14 -> z14.setImageResource(0)
                                    15 -> z15.setImageResource(0)
                                }
                            }
                        }

                        if (oldLocation == 0 && newLocation != 0 && validMove) {
                            piecesBenched -= 1
                            player.piecesonbench = piecesBenched
                            repository.updatePlayer(player)
                        }
                    }
                }

                if (score == 7) {
                    winner(id)
                }
            }
        }
    }

    private fun winner(id: Int) {
        CoroutineScope(Dispatchers.IO).launch {
            var player = repository.getPlayerInfo(id)
            player.winner = true
            repository.updatePlayer(player)
        }

        navController.navigate(R.id.action_urGameFragment_to_winnerFragment)
    }

    private fun earnPoint(id: Int) {
        CoroutineScope(Dispatchers.IO).launch {
            val player = repository.getPlayerInfo(id)
            var score = player.score
            score = score?.plus(1)
            println(score)
            player.score = score
            repository.updatePlayer(player)
//            repository.updatePlayer(Player(player.name, score, player.piecesonbench, player.dice, player.piece1,player.piece2, player.piece3,player.piece4,player.piece5,player.piece6,player.piece6,player.piece7))
        }
    }


    private fun clearBoard() {
        z13.setImageResource(0)
        z14.setImageResource(0)
        z15.setImageResource(0)
        z0.setImageResource(0)
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
        r0.setImageResource(0)
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