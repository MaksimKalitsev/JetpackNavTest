package ua.com.foxminded.jetpacknavtest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.widget.Toolbar
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import ua.com.foxminded.jetpacknavtest.databinding.ActivityAutorizationBinding


class AutorizationActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAutorizationBinding
    private lateinit var navController: NavController
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAutorizationBinding.inflate(layoutInflater)
        setContentView(binding.root)
//       // supportActionBar?.setDisplayHomeAsUpEnabled(true)
        val navHost =
            supportFragmentManager.findFragmentById(R.id.FragmentContainer) as NavHostFragment
        navController = navHost.navController
//
//       // NavigationUI.setupActionBarWithNavController(this, navController)
//    }
//
//    override fun onSupportNavigateUp(): Boolean {
//        return navController.navigateUp() || super.onSupportNavigateUp()
//
    }
}