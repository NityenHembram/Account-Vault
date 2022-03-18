package com.nityen.accountvault

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.nityen.accountvault.databinding.FragmentSecondBinding
import com.nityen.accountvault.model.AccountViewModel
import com.nityen.accountvault.model.Accounts
import kotlinx.coroutines.*

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class SecondFragment : Fragment() {

    private var _binding: FragmentSecondBinding? = null


    private val v by lazy {
        ViewModelProvider(
            this,
            ViewModelProvider.AndroidViewModelFactory.getInstance(requireActivity().application)
        ).get(AccountViewModel::class.java)
    }

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentSecondBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        val name = arguments?.getString("name")
        val email = arguments?.getString("email")
        val pass = arguments?.getString("pass")
        val id = arguments?.getInt("id")

        binding.upName.setText(name)
        binding.upEmail.setText(email)
        binding.upPass.setText(pass)


        binding.upBtn.setOnClickListener{
//            val data  = Accounts(binding.upName.text.toString(),binding.upEmail.text.toString(),
//                binding.upPass.text.toString())

            lifecycleScope.launch{
                v.update1(id!!,binding.upName.text.toString(),binding.upEmail.text.toString(),
                    binding.upPass.text.toString())
            }
            findNavController().navigate(R.id.action_SecondFragment_to_FirstFragment)

        }

        super.onViewCreated(view, savedInstanceState)

    }



    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}