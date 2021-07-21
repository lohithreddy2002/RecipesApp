package com.example.readingright.UI

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.readingright.R
import com.example.readingright.Searchviewmodel
import com.example.readingright.databinding.FragmentSearchBinding
import com.example.readingright.util.Resources
import kotlinx.android.synthetic.main.fragment_search.*
import kotlinx.coroutines.Job
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


class SearchFragment : Fragment() {

    lateinit var binding: FragmentSearchBinding
    lateinit var viewmodel: Searchviewmodel
    lateinit var Sadapter: RecyclerAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragmen
        return inflater.inflate(R.layout.fragment_search, container, false)

    }


    override fun onStart() {
        super.onStart()

        binding = FragmentSearchBinding.bind(requireView())
        hide()
        recycle()
        viewmodel = (activity as MainActivity).Sviewmodel
        var job: Job? = null

        binding.editText.addTextChangedListener {
            job?.cancel()
            job = MainScope().launch {
                delay(500L)
                it?.let {
                    if (it.toString().isNotEmpty()) {
                        viewmodel.getsearch(it.toString())
                    }
                }
            }
        }
        Sadapter.setOnItemClickListner {
            val action = SearchFragmentDirections.actionSearchFragment2ToRecipieFragment(it)
            findNavController().navigate(action)
        }

        viewmodel.query.observe(this, {
            when (it) {
                is Resources.Success -> {
                    hide()
                    it.data?.let { ingredient ->
                        Sadapter.differ.submitList(ingredient.meals)
                    }
                }
                is Resources.Failure -> {
                    hide()

                }
                is Resources.Loading -> {
                    show()
                }

            }
        })


    }

    private fun recycle() {
        Sadapter = RecyclerAdapter()
        binding.serachrecyclerview.apply {
            adapter = Sadapter
            layoutManager = LinearLayoutManager(activity)

        }
    }

    fun show() {
        progresssearch.visibility = View.VISIBLE
    }

    fun hide() {
        progresssearch.visibility = View.INVISIBLE
    }


}
