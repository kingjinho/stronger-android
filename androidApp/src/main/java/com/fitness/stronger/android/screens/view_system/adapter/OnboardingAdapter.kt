package com.fitness.stronger.android.screens.view_system.adapter

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.fitness.stronger.android.screens.view_system.onboarding.ScreenOnboardingPermissionView
import com.fitness.stronger.android.screens.view_system.onboarding.ScreenOnboardingWelcomeView

class OnboardingAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {
    override fun getItemCount(): Int {
        return 2
    }

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> {
                ScreenOnboardingWelcomeView()
            }

            1 -> {
                ScreenOnboardingPermissionView()
            }

            else ->
                throw IllegalArgumentException("wrong position: $position")
        }
    }
}