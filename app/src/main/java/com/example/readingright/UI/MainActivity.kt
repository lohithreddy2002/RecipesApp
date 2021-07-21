package com.example.readingright.UI

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.readingright.*
import com.example.readingright.databinding.ActivityMainBinding
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    lateinit var Sviewmodel: Searchviewmodel
    lateinit var Hviewmodel: Homeviewmodel
    private lateinit var binding: ActivityMainBinding
    lateinit var navController: NavController
    lateinit var appBarConfiguration: AppBarConfiguration

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val repo = Repo(RecipieDatabase(this))
        val Hfactory = HomeViewFactory(repo)
        val Sfactory = SearchViewfactory(repo)
        Hviewmodel = ViewModelProvider(this, Hfactory).get(Homeviewmodel::class.java)
        Sviewmodel = ViewModelProvider(this, Sfactory).get(Searchviewmodel::class.java)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        navController = fragment.findNavController()
        appBarConfiguration =
            AppBarConfiguration(setOf(R.id.homeFragment2, R.id.savedFragment, R.id.searchFragment2))
        setupActionBarWithNavController(navController, appBarConfiguration)



        findViewById<BottomNavigationView>(R.id.bottomnav).setupWithNavController(navController)

    }

    override fun onSupportNavigateUp(): Boolean {
        return findNavController(R.id.fragment).navigateUp(appBarConfiguration)

    }
}