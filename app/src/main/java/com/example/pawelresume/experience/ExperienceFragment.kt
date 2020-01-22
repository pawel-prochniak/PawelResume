package com.example.pawelresume.experience

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.observe
import com.example.pawelresume.databinding.FragmentExperienceBinding
import com.example.pawelresume.experience.data.ExperienceEntry
import com.example.pawelresume.experience.viewmodel.ExperienceViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class ExperienceFragment : Fragment() {

    private val viewModel: ExperienceViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentExperienceBinding.inflate(inflater, container, false)

        viewModel.experienceList.observe(viewLifecycleOwner) { result: List<ExperienceEntry> ->
            val sb = StringBuilder()
             result.forEach {
                 sb.append(it.toString()).append("\n\n")
             }
            binding.title.text = sb.toString()
        }

        return binding.root
    }

}