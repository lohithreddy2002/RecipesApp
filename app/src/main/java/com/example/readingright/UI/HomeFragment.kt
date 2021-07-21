package com.example.readingright.UI

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.readingright.Homeviewmodel
import com.example.readingright.R
import com.example.readingright.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {
    lateinit var binding: FragmentHomeBinding
    lateinit var viewmodel: Homeviewmodel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        //viewmodel = (activity as MainActivity).Hviewmodel

        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onStart() {
        super.onStart()
//        binding = FragmentHomeBinding.bind(requireView())
//
//        viewmodel.breakingNews.observe(requireActivity(), {
//            when(it){
//                is Resources.Success ->{
//                    Toast.makeText(context, "sucess", Toast.LENGTH_SHORT).show()
//
//                    binding.category.text = it.data!!.meals!![0].strCategory
//                    Glide.with(this)
//                        .load(it.data.meals!![0].strMealThumb).into(binding.image)
//                    binding.instructions.text = it.data.meals[0].strInstructions
//                    binding.Title.title = it.data.meals[0].strMeal
//
//
//                    binding.insert.setOnClickListener { view ->
//                        viewmodel.insert(it.data.meals[0])
//                    }
//                }
//                is Resources.Failure -> {
//                    Toast.makeText(context, "falure ${it.message}", Toast.LENGTH_SHORT).show()
//                }
//            }
//
//        })
//
//
//
//
    }

}