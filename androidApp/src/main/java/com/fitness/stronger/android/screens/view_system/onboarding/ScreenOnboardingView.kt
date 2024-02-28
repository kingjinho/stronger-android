package com.fitness.stronger.android.screens.view_system.onboarding

import android.Manifest
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.fitness.stronger.android.databinding.ScreenOnboardingOuterBinding
import com.fitness.stronger.android.screens.compose.navigation.Route
import com.fitness.stronger.android.screens.view_system.adapter.OnboardingAdapter
import com.fitness.stronger.android.utils.ext.isPermissionGranted

class ScreenOnboardingView : Fragment() {

    private var _binding: ScreenOnboardingOuterBinding? = null
    private val binding: ScreenOnboardingOuterBinding
        get() = _binding!!

    private val permissionLauncher = registerForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) {
        navigateToMain()
    }

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
        setButtonNextClickListener()
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

    private fun setButtonNextClickListener() {
        binding.btnNext.setOnClickListener {
            if (binding.onboardingViewPager.currentItem == 0) {
                binding.onboardingViewPager.currentItem =
                    binding.onboardingViewPager.currentItem + 1
            } else if (binding.onboardingViewPager.currentItem == 1) {
                requestCameraPermissionIfNotGranted()
            }
        }
    }

    private fun requestCameraPermissionIfNotGranted() {
        if (isPermissionGranted(Manifest.permission.CAMERA)) {
            navigateToMain()
        } else {
            permissionLauncher.launch(Manifest.permission.CAMERA)
        }
    }

    private fun navigateToMain() {
        findNavController().navigate(Route.Main.name)
    }
}