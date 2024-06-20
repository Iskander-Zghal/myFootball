package com.example.myfootball.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.myfootball.R
import com.example.myfootball.databinding.TeamsItemBinding
import com.example.myfootball.presentation.model.TeamUi
import com.squareup.picasso.Picasso

class TeamsAdapter(
    private val onItemClick: (TeamUi) -> Unit,
) : ListAdapter<TeamUi, TeamsAdapter.TeamsViewHolder>(TeamsDiffUtilCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = TeamsViewHolder(
        binding = TeamsItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        ), itemClick = onItemClick
    )

    override fun onBindViewHolder(holder: TeamsViewHolder, position: Int) =
        holder.bind(getItem(position))

    class TeamsViewHolder(
        private val binding: TeamsItemBinding,
        private val itemClick: (TeamUi) -> Unit,
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(team: TeamUi) {
            with(team) {
                if (teamLogo.isNullOrEmpty()) {
                    binding.imagePlayer.setImageResource(R.drawable.team_placeholder)
                } else {
                    Picasso.get().load(teamLogo).into(binding.imagePlayer)
                }
                itemView.setOnClickListener { itemClick(this) }
            }
        }
    }

}

private class TeamsDiffUtilCallback : DiffUtil.ItemCallback<TeamUi>() {
    override fun areItemsTheSame(old: TeamUi, new: TeamUi): Boolean {
        return old === new
    }

    override fun areContentsTheSame(old: TeamUi, new: TeamUi): Boolean {
        return old.teamLogo == new.teamLogo
    }
}