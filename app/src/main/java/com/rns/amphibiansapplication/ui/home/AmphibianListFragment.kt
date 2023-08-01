package com.rns.amphibiansapplication.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.rns.amphibiansapplication.R
import com.rns.amphibiansapplication.databinding.FragmentAmphibianListBinding
import com.rns.amphibiansapplication.ui.AmphibianViewModel

class AmphibianListFragment : Fragment() {

    private val viewModel: AmphibianViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        val binding : FragmentAmphibianListBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_amphibian_list, container, false)

        binding.lifecycleOwner = this
        binding.viewModel = viewModel
        binding.recyclerView.adapter = AmphibianListAdapter(AmphibianListener { amphibian ->
            viewModel.onAmphibianClicked(amphibian)
            findNavController().navigate(R.id.action_amphibianListFragment_to_amphibianDetailFragment)
        })

        return binding.root
    }
}
