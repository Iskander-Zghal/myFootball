package com.example.myfootball.presentation

import android.content.Context
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.ArrayAdapter
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.myfootball.R
import com.example.myfootball.databinding.FragmentTeamsBinding
import com.example.myfootball.presentation.adapter.TeamsAdapter
import com.example.myfootball.presentation.model.TeamUi
import com.example.myfootball.presentation.model.TeamsViewState
import com.example.myfootball.util.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class TeamsFragment : Fragment(R.layout.fragment_teams) {

    private val teamsViewModel: TeamsViewModel by activityViewModels()
    private val binding by viewBinding(FragmentTeamsBinding::bind)
    private val teamsAdapter = TeamsAdapter(onItemClick = ::onItemClick)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        teamsViewModel.fetchLeagues()
        val layoutManager = GridLayoutManager(requireContext(), 2)
        binding.teamsRecyclerView.layoutManager = layoutManager
        binding.teamsRecyclerView.adapter = teamsAdapter
        initObservers()
        with(binding) {
            search.setOnClickListener {
                if (autoCompleteTextView.text.isNullOrEmpty()
                        .not() && autoCompleteTextView.text.length > 2
                ) {
                    teamsViewModel.fetchTeams(teamName = binding.autoCompleteTextView.text.toString())
                }
            }
        }
    }

    private fun initObservers() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                teamsViewModel.getAllLeagues().collect { list ->
                    binding.autoCompleteTextView.setAdapter(
                        ArrayAdapter(requireContext(), android.R.layout.select_dialog_item, list)
                    )
                }
            }
        }
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                teamsViewModel.teamsViewState.collect { state ->
                    dismissKeyBoard()
                    when (state) {
                        TeamsViewState.Idle -> {
                            binding.teamsRecyclerView.isVisible = false
                            binding.animView.isVisible = false
                            binding.textViewError.isVisible = false
                        }
                        is TeamsViewState.Ready -> {
                            binding.animView.isVisible = false
                            binding.textViewError.isVisible = false
                            binding.teamsRecyclerView.isVisible = true
                            teamsAdapter.submitList(state.data)
                        }
                        is TeamsViewState.Empty -> {
                            binding.animView.isVisible = true
                            binding.textViewError.isVisible = true
                        }
                    }
                }
            }
        }
    }

    private fun onItemClick(team: TeamUi) {
        teamsViewModel.getTeamDetails(team.teamName)
        findNavController().navigate(R.id.action_homeFragment_to_homeFragmentDetails)
    }

    private fun dismissKeyBoard() {
        val imm = context?.getSystemService(Context.INPUT_METHOD_SERVICE) as? InputMethodManager
        imm?.hideSoftInputFromWindow(activity?.currentFocus?.windowToken, 0)
    }
}