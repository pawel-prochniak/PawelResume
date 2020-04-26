package com.example.pawelresume.experience

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.lifecycle.observe
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.pawelresume.R
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

        viewModel.experienceList.observe(viewLifecycleOwner) { list: List<ExperienceEntry> ->
            Timber.d("experienceList updated: $list")
            adapter.submitList(list)
        }

        binding.apply {
            experienceRecycler.layoutManager = LinearLayoutManager(context)
            experienceRecycler.adapter = adapter
            addExperienceButton.setOnClickListener {
                showAddExperiencePopup()
            }
        }
        return binding.root
    }

    private fun showAddExperiencePopup() {
        ExperienceNewDialog({ input ->
            viewModel.addExperience(input)
        }).show(parentFragmentManager, "AddExperienceFragment")
    }
}