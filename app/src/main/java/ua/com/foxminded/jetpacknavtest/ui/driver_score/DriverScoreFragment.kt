package ua.com.foxminded.jetpacknavtest.ui.driver_score

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import ua.com.foxminded.jetpacknavtest.databinding.FragmentDriverScoreBinding

class DriverScoreFragment : Fragment() {

    private var _binding: FragmentDriverScoreBinding? = null


    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val driverScoreViewModel =
            ViewModelProvider(this).get(DriverScoreViewModel::class.java)

        _binding = FragmentDriverScoreBinding.inflate(inflater, container, false)
        val root: View = binding.root

//        val textView: TextView = binding.textNotifications
//        notificationsViewModel.text.observe(viewLifecycleOwner) {
//            textView.text = it
//        }
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}