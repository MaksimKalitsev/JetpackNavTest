package ua.com.foxminded.jetpacknavtest.ui.my_trips

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import ua.com.foxminded.jetpacknavtest.databinding.FragmentMyTripsBinding

class MyTripsFragment : Fragment() {

    private var _binding: FragmentMyTripsBinding? = null


    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val myTripsViewModel =
            ViewModelProvider(this).get(MyTripsViewModel::class.java)

        _binding = FragmentMyTripsBinding.inflate(inflater, container, false)
        val root: View = binding.root

//        val textView: TextView = binding.textHome
//        homeViewModel.text.observe(viewLifecycleOwner) {
//            textView.text = it
//        }
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}