package com.example.pawelresume.experience

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.pawelresume.databinding.FragmentExperienceBinding

class ExperienceFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentExperienceBinding.inflate(inflater, container, false)

        return binding.root
    }
}