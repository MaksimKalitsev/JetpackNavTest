package ua.com.foxminded.jetpacknavtest

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import ua.com.foxminded.jetpacknavtest.di.appComponent
import ua.com.foxminded.jetpacknavtest.user.ILoginManager
import javax.inject.Inject

class SplashActivity : AppCompatActivity() {

    @Inject
    lateinit var loginManager: ILoginManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        appComponent.inject(this)
        setContentView(R.layout.activity_splash)
        handleNavigation()
    }

    private fun handleNavigation() {
        if (loginManager.isUserLoggedIn) {
            val intentMainActivity = Intent(this, MainActivity::class.java)
            startActivity(intentMainActivity)
            finish()
        } else {
            val intentAuthorizationActivity = Intent(this, AuthorizationActivity::class.java)
            startActivity(intentAuthorizationActivity)
            finish()
        }
    }
}