package com.example.readingright.UI

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.example.readingright.Homeviewmodel
import com.example.readingright.R
import com.example.readingright.databinding.FragmentRecipieBinding
import com.example.readingright.db.ingredients

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

    @SuppressLint("SetTextI18n")
    override fun onStart() {
        super.onStart()
        binding = FragmentRecipieBinding.bind(requireView())
        binding.toolbar

        val Meal = args.recipie
        Glide.with(this).load(Meal.strMealThumb).into(binding.image)
        binding.category.text = "Category : ${Meal.strCategory}"
        binding.area.text = "Area :${Meal.strArea}"
        binding.ingredients.text = Meal.strInstructions
//        binding.instructions.text = Meal.strInstructions
        binding.Title.title = Meal.strMeal
        binding.insert.setOnClickListener {
            viewmodel.insert(Meal)
        }
        if (Meal.strYoutube != null) {
            binding.Ycard.setOnClickListener {
                val action =
                   RecipieFragmentDirections.actionRecipieFragmentToWebviewFragment(Meal.strYoutube)
                findNavController().navigate(action)
            }
        } else {
            binding.Ycard.visibility = View.INVISIBLE
        }
        if (Meal.strSource != null) {
            binding.Scard.setOnClickListener {
                val action =
                    RecipieFragmentDirections.actionRecipieFragmentToWebviewFragment(Meal.strSource)
                findNavController().navigate(action)
            }
        } else {
            binding.Scard.visibility = View.INVISIBLE
        }

        binding.ingredientslist.setOnClickListener { view ->
            val ing = ingredients(
                rearrange(Meal.meal()),
                rearrange(Meal.measure())
            )
            val action =
                RecipieFragmentDirections.actionRecipieFragmentToIngredientsFragment(ing)
            findNavController().navigate(action)
        }

    }

    fun rearrange(list: List<String?>): List<String> {
        val a = mutableListOf<String>()
        for (i in list) {
            if (i.toString() != "" && i != null) {
                a.add(i.toString())
            }
        }
        return a
    }


}