package com.example.pawelresume.skills

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.pawelresume.databinding.FragmentSkillsBinding

class SkillsFragment : Fragment() {
    private val TAG = SkillsFragment::class.java.simpleName

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentSkillsBinding.inflate(inflater, container, false)

        return binding.root
    }
}