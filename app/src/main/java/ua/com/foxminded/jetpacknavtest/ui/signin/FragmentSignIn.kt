package ua.com.foxminded.jetpacknavtest.ui.signin

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import ua.com.foxminded.jetpacknavtest.R
import ua.com.foxminded.jetpacknavtest.databinding.FragmentSignInBinding
import ua.com.foxminded.jetpacknavtest.di.appComponent

class FragmentSignIn : Fragment() {
    private var _binding: FragmentSignInBinding? = null
    private val binding get() = _binding!!

    private val viewModel by viewModels<SignInViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        appComponent.inject(this)
        if (viewModel.isInitialized.not()) appComponent.inject(viewModel)
        viewModel.init()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSignInBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentSignInBinding.bind(view)
        binding.buttonLogIn.setOnClickListener {
            // todo: call viewModel.signIn() with username and password extracted from EditText widgets.
        }
        binding.forgotPassword.setOnClickListener {
            findNavController().navigate(R.id.action_signIn_to_resetPassword)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}