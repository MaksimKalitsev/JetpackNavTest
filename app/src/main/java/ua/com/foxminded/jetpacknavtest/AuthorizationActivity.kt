package ua.com.foxminded.jetpacknavtest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.widget.Toolbar
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import ua.com.foxminded.jetpacknavtest.databinding.ActivityAutorizationBinding


class AuthorizationActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAutorizationBinding
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAutorizationBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navHost =
            supportFragmentManager.findFragmentById(R.id.FragmentContainer) as NavHostFragment
        navController = navHost.navController

    }
}