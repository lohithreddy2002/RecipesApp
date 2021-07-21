package com.example.readingright.UI

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.example.readingright.R
import com.example.readingright.databinding.FragmentIngredientsBinding

class IngredientsFragment : Fragment() {
    private val args: IngredientsFragmentArgs by navArgs()
    lateinit var binding: FragmentIngredientsBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_ingredients, container, false)
    }

    override fun onStart() {

        super.onStart()
        binding = FragmentIngredientsBinding.bind(requireView())

        binding.inglist.adapter =
            ArrayAdapter(requireContext(), R.layout.listitem, args.ingrdients.ingredients)
        binding.meaasurelist.adapter =
            ArrayAdapter(requireContext(), R.layout.listitem, args.ingrdients.measures)
    }

}