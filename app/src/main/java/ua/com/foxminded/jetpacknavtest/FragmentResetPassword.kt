package ua.com.foxminded.jetpacknavtest

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import ua.com.foxminded.jetpacknavtest.databinding.FragmentResetPasswordBinding

class FragmentResetPassword : Fragment(R.layout.fragment_reset_password) {
    private lateinit var binding: FragmentResetPasswordBinding
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentResetPasswordBinding.bind(view)
        binding.toolbar.findNavController().navigateUp()

        binding.resetTitle.setOnClickListener {

        }
    }
}