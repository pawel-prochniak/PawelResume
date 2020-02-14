package com.example.pawelresume.experience

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.observe
import com.example.pawelresume.databinding.FragmentExperienceBinding
import com.example.pawelresume.experience.data.ExperienceEntry
import com.example.pawelresume.experience.viewmodel.ExperienceListViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel
import timber.log.Timber

class ExperienceFragment : Fragment() {

    private val viewModel: ExperienceListViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentExperienceBinding.inflate(inflater, container, false)
        val adapter = ExperienceAdapter()
        binding.experienceRecycler.adapter = adapter

        viewModel.experienceList.observe(viewLifecycleOwner) { list: List<ExperienceEntry> ->
            Timber.d("experienceList updated: $list")
            adapter.submitList(list)
        }


        return binding.root
    }

}