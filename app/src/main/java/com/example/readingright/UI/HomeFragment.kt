package com.example.readingright.UI

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.example.readingright.Homeviewmodel
import com.example.readingright.R
import com.example.readingright.databinding.FragmentHomeBinding
import com.example.readingright.db.ingredients
import com.example.readingright.util.Resources

class HomeFragment : Fragment() {
    lateinit var binding: FragmentHomeBinding
    lateinit var viewmodel: Homeviewmodel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        viewmodel = (activity as MainActivity).Hviewmodel

        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onStart() {
        super.onStart()
        binding = FragmentHomeBinding.bind(requireView())

        viewmodel.breakingNews.observe(requireActivity(), {
            when (it) {
                is Resources.Success -> {
                    Toast.makeText(context, "sucess", Toast.LENGTH_SHORT).show()
                    binding.Recipiename.text = it.data!!.meals!![0].strMeal
                    Glide.with(this)
                        .load(it.data.meals!![0].strMealThumb).into(binding.image)

                    binding.ingredients.text = it.data.meals[0].strInstructions
                    binding.category.text = it.data.meals[0].strCategory
                    binding.area.text = it.data.meals[0].strArea
                    Log.d("ingre", "${it.data.meals[0].meal()}")

                    binding.ingredientslist.setOnClickListener { view ->
                        val ing = ingredients(
                            rearrange(it.data.meals[0].meal()),
                            rearrange(it.data.meals[0].measure())
                        )
                        val action =
                            HomeFragmentDirections.actionHomeFragment2ToIngredientsFragment(ing)
                        findNavController().navigate(action)
                    }

                    binding.Ycard.setOnClickListener { view ->
                        val action =
                            HomeFragmentDirections.actionHomeFragment2ToWebviewFragment(it.data.meals[0].strYoutube!!)
                        findNavController().navigate(action)
                    }
                }
                is Resources.Failure -> {
                    Toast.makeText(context, "falure ${it.message}", Toast.LENGTH_SHORT).show()
                }
            }

        })


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