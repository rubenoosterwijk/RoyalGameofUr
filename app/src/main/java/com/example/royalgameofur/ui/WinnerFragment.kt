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
import kotlinx.android.synthetic.main.fragment_winner.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.lang.Boolean.TRUE

class WinnerFragment : Fragment() {


    private val viewModel: UrGameViewModel by activityViewModels()
    private lateinit var repository: UrGameRepository

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        repository = UrGameRepository(requireContext())
        getWinner()
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_winner, container, false)
    }

    private fun getWinner() {
        CoroutineScope(Dispatchers.IO).launch {
            var player = repository.getPlayerWinner(winner = true)
            var id = player.id
            withContext(Dispatchers.Main) {
                showWinner(id)
            }
        }
    }


    private fun showWinner(id: Int?) {

        if (id == 1) {
            viewModel.getPlayer1().observe(viewLifecycleOwner, { player1 ->
                name.text = player1.name
                moves_amount.text = player1.movenumber.toString()
            })
        }

        if (id == 2) {
            viewModel.getPlayer1().observe(viewLifecycleOwner, { player2 ->
                name.text = player2.name
                moves_amount.text = player2.movenumber.toString()
            })
        }
    }
}