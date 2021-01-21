package com.example.royalgameofur.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.example.royalgameofur.R
import com.example.royalgameofur.model.Player
import com.example.royalgameofur.repository.UrGameRepository
import com.google.android.material.button.MaterialButton
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.android.synthetic.main.fragment_start.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class StartFragment : Fragment() {

    private lateinit var navController: NavController
    private lateinit var repository: UrGameRepository

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        repository = UrGameRepository(requireContext())
        navController = findNavController()
        view.findViewById<MaterialButton>(R.id.play).setOnClickListener {
            val name = namefield1.text.toString()
//            println(name)
//            println("XD")
            val name2 = namefield2.text.toString()

            CoroutineScope(Dispatchers.IO).launch {
                repository.insertPlayer(Player(name, 0, 7,0, 0, 1))
                repository.insertPlayer(Player(name2, 0, 7,0, 0, 2))
            }
            navController.navigate(R.id.action_startFragment_to_urGameFragment)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_start, container, false)
    }
}