package ru.mtuci.drivenext

import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import androidx.appcompat.app.AppCompatActivity

class SplashActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySplashBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // кнопка "повторить попытку" на экране No Connection
        binding.retryButton.setOnClickListener {
            checkAndGo()
        }

        // через 3 секунды проверяем интернет и идём дальше
        Handler(Looper.getMainLooper()).postDelayed({
            checkAndGo()
        }, 3000)
    }

    // проверка интернета и переход
    private fun checkAndGo() {
        if (isOnline()) {
            // интернет есть — показываем splash, прячем no connection
            binding.splashGroup.visibility = View.VISIBLE
            binding.noInternetGroup.visibility = View.GONE

            // онбординг
            val prefs = getSharedPreferences("app_prefs", Context.MODE_PRIVATE)
            val onboardingDone = prefs.getBoolean("onboarding_done", false)
            val next = if (onboardingDone) LoginActivity::class.java
            else OnboardingActivity::class.java

            startActivity(Intent(this, next))
            finish()
        } else {
            // нет интернета — показываем No Connection
            binding.splashGroup.visibility = View.GONE
            binding.noInternetGroup.visibility = View.VISIBLE
        }
    }

    // проверка наличия интернета через ConnectivityManager
    private fun isOnline(): Boolean {
        val cm = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val network = cm.activeNetwork ?: return false
        val caps = cm.getNetworkCapabilities(network) ?: return false
        return caps.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
    }
}