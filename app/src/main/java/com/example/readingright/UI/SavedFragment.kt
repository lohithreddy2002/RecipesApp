package com.example.readingright.UI

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.readingright.Homeviewmodel
import com.example.readingright.R
import com.example.readingright.databinding.FragmentSavedBinding

class SavedFragment : Fragment() {
    lateinit var Sadapter: RecyclerAdapter
    lateinit var viewmodel: Homeviewmodel
    lateinit var binding: FragmentSavedBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_saved, container, false)
    }

    override fun onStart() {
        binding = FragmentSavedBinding.bind(requireView())
        super.onStart()
        setrecycle()
        viewmodel = (activity as MainActivity).Hviewmodel
        viewmodel.getsaved().observe(this, {
            Sadapter.differ.submitList(it)

        })
        Sadapter.setOnItemClickListner {
            val action = SavedFragmentDirections.actionSavedFragmentToRecipieFragment(it)
            findNavController().navigate(action)
        }

    }


    fun setrecycle() {
        Sadapter = RecyclerAdapter()
        binding.savedrecipielist.apply {
            adapter = Sadapter
            layoutManager = LinearLayoutManager(activity)
        }


    }


}
