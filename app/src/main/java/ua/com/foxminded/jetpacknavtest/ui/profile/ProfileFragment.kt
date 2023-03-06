package ua.com.foxminded.jetpacknavtest.ui.profile

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import ua.com.foxminded.jetpacknavtest.R
import ua.com.foxminded.jetpacknavtest.databinding.FragmentProfileBinding
import ua.com.foxminded.jetpacknavtest.di.userComponent
import ua.com.foxminded.jetpacknavtest.ui.signin.RequestState

class ProfileFragment : Fragment() {

    private var _binding: FragmentProfileBinding? = null
    private val binding get() = _binding!!

    private val viewModel by viewModels<ProfileViewModel>()
    private val progressObserver = Observer<RequestState> {
        when (it) {
            RequestState.SUCCESS -> {
                findNavController().navigate(R.id.authorizationActivity)
                requireActivity()
                    .finish()
            }
            else -> {}
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        userComponent!!.inject(viewModel)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.progressLiveData.observe(viewLifecycleOwner, progressObserver)
        _binding = FragmentProfileBinding.bind(view)

        binding.buttonLogOut.setOnClickListener {
            viewModel.logOut()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null


    }
}