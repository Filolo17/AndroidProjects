package ru.mtuci.drivenext

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import ru.mtuci.drivenext.databinding.ActivityOnboardingBinding

class OnboardingActivity : AppCompatActivity() {

    private lateinit var binding: ActivityOnboardingBinding
    private val pages = listOf<Fragment>(
        OnboardingFragment.newInstance(0),
        OnboardingFragment.newInstance(1),
        OnboardingFragment.newInstance(2)
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOnboardingBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // адаптер для ViewPager2
        binding.viewPager.adapter = object : FragmentStateAdapter(this) {
            override fun getItemCount() = pages.size
            override fun createFragment(position: Int) = pages[position]
        }

        // кнопка "пропустить" - идём на логин
        binding.skipButton.setOnClickListener { goToLogin() }

        // кнопка "далее/поехали"
        binding.nextButton.setOnClickListener {
            val current = binding.viewPager.currentItem
            if (current < pages.size - 1) {
                binding.viewPager.currentItem = current + 1
            } else {
                goToLogin()
            }
        }

        // меняем текст кнопки на последнем слайде + обновляем индикатор
        binding.viewPager.registerOnPageChangeCallback(object :
            ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                binding.nextButton.text =
                    if (position == pages.size - 1) getString(R.string.lets_go)
                    else getString(R.string.next)
            }
        })
    }

    // сохраняем флаг что онбординг пройден (для релиза) и идём на логин
    private fun goToLogin() {
        getSharedPreferences("app_prefs", Context.MODE_PRIVATE)
            .edit()
            .putBoolean("onboarding_done", true)
            .apply()
        startActivity(Intent(this, LoginActivity::class.java))
        finish()
    }
}