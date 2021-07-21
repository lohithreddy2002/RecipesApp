package com.example.readingright.UI

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.example.readingright.Homeviewmodel
import com.example.readingright.R
import com.example.readingright.databinding.FragmentRecipieBinding

class RecipieFragment : Fragment() {
    lateinit var binding: FragmentRecipieBinding
    lateinit var viewmodel: Homeviewmodel
    private val args: RecipieFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


//        val databse = RecipieDatabase(requireContext())
//        val repo = Repo(databse)
//        val factory = HomeViewFactory(repo)
//
//        viewmodel = ViewModelProvider(this, factory).get(Homeviewmodel::class.java)
        return inflater.inflate(R.layout.fragment_recipie, container, false)
    }

    override fun onStart() {
        super.onStart()
        binding = FragmentRecipieBinding.bind(requireView())
        val toolbar = binding.toolbar

        val Meal = args.recipie
        Glide.with(this).load(Meal.strMealThumb).into(binding.image)
        binding.instructions.text = Meal.strInstructions
        binding.Title.title = Meal.strMeal
        binding.insert.setOnClickListener {
            viewmodel.insert(Meal)
        }
    }


}