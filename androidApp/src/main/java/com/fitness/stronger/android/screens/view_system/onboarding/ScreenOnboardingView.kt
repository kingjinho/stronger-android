package com.fitness.stronger.android.screens.view_system.onboarding

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.fitness.stronger.android.databinding.ScreenOnboardingOuterBinding
import com.fitness.stronger.android.screens.view_system.adapter.OnboardingAdapter
import com.fitness.stronger.android.viewmodels.OnboardingViewModel
import com.fitness.stronger.android.viewmodels.OnboardingViewModelFactory

class ScreenOnboardingView : Fragment() {

    private val viewModel by viewModels<OnboardingViewModel> {
        OnboardingViewModelFactory()
    }

    private var _binding: ScreenOnboardingOuterBinding? = null
    private val binding: ScreenOnboardingOuterBinding
        get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = ScreenOnboardingOuterBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViewPager()
    }

    override fun onDestroyView() {
        super.onDestroyView()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    private fun initViewPager() {
        binding.onboardingViewPager.adapter = OnboardingAdapter(this)
    }
}