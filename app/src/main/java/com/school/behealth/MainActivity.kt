package com.school.behealth

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.school.behealth.databinding.ActivityMainBinding
import com.school.behealth.home.HomeFragment
import com.school.behealth.profile.ProfileFragment
import com.school.behealth.settings.SettingsFragment

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        replaceFragment(HomeFragment())

        binding.bottomNavBarMainActivity.setOnItemSelectedListener {
            when(it.itemId) {
                R.id.home_menu -> replaceFragment(HomeFragment())
                R.id.profile_menu -> replaceFragment(ProfileFragment())
                R.id.setting_menu -> replaceFragment(SettingsFragment())
            }
            true
        }
    }

    private fun replaceFragment(fragment: Fragment){
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.frameLayout_mainActivity, fragment).commit()
    }
}