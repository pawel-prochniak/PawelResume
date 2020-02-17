package com.example.pawelresume.experience

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.pawelresume.R
import com.example.pawelresume.databinding.ListItemExperienceBinding
import com.example.pawelresume.experience.data.ExperienceEntry
import java.text.SimpleDateFormat
import java.util.*

class ExperienceAdapter : ListAdapter<ExperienceEntry, ExperienceAdapter.ViewHolder>(
    ExperienceDiffCallback()
) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.list_item_experience,
                parent,
                false)
        )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class ViewHolder(private val binding: ListItemExperienceBinding)
        : RecyclerView.ViewHolder(binding.root) {

        private val dateFormat = SimpleDateFormat("dd MMM yyyy", Locale.US)

        fun bind(item: ExperienceEntry) {
            with (binding) {
                company.text = item.company
                position.text = item.position
                fromDate.text = dateFormat.format(item.from)
                toDate.text = dateFormat.format(item.to)
            }

        }
    }
}

private class ExperienceDiffCallback: DiffUtil.ItemCallback<ExperienceEntry>() {
    override fun areItemsTheSame(oldItem: ExperienceEntry, newItem: ExperienceEntry): Boolean =
        oldItem.entryId == newItem.entryId

    override fun areContentsTheSame(oldItem: ExperienceEntry, newItem: ExperienceEntry): Boolean =
        oldItem == newItem
}