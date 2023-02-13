package ua.com.foxminded.jetpacknavtest.ui.signin


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import ua.com.foxminded.jetpacknavtest.R
import ua.com.foxminded.jetpacknavtest.databinding.FragmentSignInBinding
import ua.com.foxminded.jetpacknavtest.di.appComponent

enum class RequestState {
    LOADING, SUCCESS, ERROR;
}

class SignInFragment : Fragment() {
    private var _binding: FragmentSignInBinding? = null
    private val binding get() = _binding!!

    private val viewModel by viewModels<SignInViewModel>()

    private val retryAction = object : View.OnClickListener {
        override fun onClick(v: View?) {
// todo:
        }
    }

    private fun showSnackbar() {
        val mySnackbar =
            Snackbar.make(binding.mainLayout, R.string.error_snackbar, Snackbar.LENGTH_INDEFINITE)
        mySnackbar.setAction(R.string.retry_snackbar, retryAction)
        mySnackbar.setActionTextColor(ContextCompat.getColor(requireContext(), R.color.primary))
        mySnackbar.show()
    }

    private val progressObserver = Observer<RequestState> {
        when (it) {
            RequestState.LOADING -> {
                binding.progressBar.visibility = View.VISIBLE
            }
            RequestState.ERROR -> {
                binding.progressBar.visibility = View.GONE
                showSnackbar()
            }
            RequestState.SUCCESS -> {
                binding.progressBar.visibility = View.GONE
//                findNavController().navigate(R.id.action_AuthorizationActivity_to_MainActivity)
  //              requireActivity()//.finish()
            }
        }
    }

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
        viewModel.progressLiveData.observe(viewLifecycleOwner, progressObserver)
        _binding = FragmentSignInBinding.bind(view)

        binding.buttonLogIn.setOnClickListener {
            viewModel.signIn(
                username = binding.emailTextInput.text.toString(),
                password = binding.passwordTextInput.text.toString()
            )
//
        }

        binding.forgotPassword.setOnClickListener {
            findNavController().navigate(R.id.action_signIn_to_resetPassword)
        }

        binding.emailTextInput.doAfterTextChanged {
            checkEnabledEditText()

        }
        binding.passwordTextInput.doAfterTextChanged {
            checkEnabledEditText()

        }
    }

    private fun checkEnabledEditText() = with(binding) {
        buttonLogIn.isEnabled =
            emailTextInput.text.isNullOrEmpty().not()  && passwordTextInput.text.isNullOrEmpty().not()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}