package com.example.myfootball.presentation

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import com.example.myfootball.R
import com.example.myfootball.databinding.FragmentTeamsDetailsBinding
import com.example.myfootball.presentation.model.TeamDetailsUiModel
import com.example.myfootball.presentation.model.TeamDetailsViewState
import com.example.myfootball.util.viewBinding
import com.squareup.picasso.Picasso
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class TeamsDetailsFragment : Fragment(R.layout.fragment_teams_details) {

    private val sharedTeamViewModel: TeamsViewModel by activityViewModels()
    private val binding by viewBinding(FragmentTeamsDetailsBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initObservers()
        binding.backBtn.setOnClickListener {
            findNavController().popBackStack()
        }
    }

    private fun initObservers() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                sharedTeamViewModel.teamDetailsViewState.collect { state ->
                    when (state) {
                        TeamDetailsViewState.Idle -> {
                            binding.animLoading.isVisible = true
                        }
                        is TeamDetailsViewState.Ready -> {
                            binding.animLoading.isVisible = false
                            displayTeamDetails(teamDetailsUiModel = state.data)
                        }
                        is TeamDetailsViewState.Empty -> {
                            binding.animError.isVisible = true
                            binding.textViewError.isVisible = true
                        }
                    }
                }
            }
        }
    }

    private fun displayTeamDetails(teamDetailsUiModel: TeamDetailsUiModel) {
        with(teamDetailsUiModel) {
            with(binding) {
                if (bannerImage.isEmpty().not()) {
                    Picasso.get().load(bannerImage).into(BannerImageView)
                }
                CountyTextView.text = countryName
                LeagueNameTextView.text = leagueName
                DescriptionTextView.text = descriptionTeam
                titleTeamTextView.text = teamName
            }
        }
    }
}