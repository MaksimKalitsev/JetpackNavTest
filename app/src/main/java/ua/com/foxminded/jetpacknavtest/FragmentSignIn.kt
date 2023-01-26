package ua.com.foxminded.jetpacknavtest

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import ua.com.foxminded.jetpacknavtest.databinding.FragmentSignInBinding

class FragmentSignIn : Fragment(R.layout.fragment_sign_in) {
    private lateinit var binding: FragmentSignInBinding
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentSignInBinding.bind(view)
        binding.buttonLogIn.setOnClickListener {
            findNavController().navigate(R.id.resetPassword)
        }
        binding.forgotPassword.setOnClickListener {
            findNavController().navigate(R.id.action_signIn_to_resetPassword)
        }
    }
}