package com.demoexample.mystreamchat.ui.fragment.loginfragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.demoexample.mystreamchat.R
import com.demoexample.mystreamchat.databinding.FragmentLoginBinding
import com.demoexample.mystreamchat.model.ChatUser
import com.google.android.material.textfield.TextInputLayout


class LoginFragment : Fragment() {
    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.button.setOnClickListener {

            userInputValidation()
        }
    }

    private fun userInputValidation() {
        var firstName = binding.firstNameEditText.text.toString()
        var userName = binding.usernameEditText.text.toString()
        if (authenticateUser(firstName, binding.firstNameInputLayout) && authenticateUser(userName, binding.usernameInputLayout))
        {
            val action = LoginFragmentDirections.actionLoginFragmentToChannelFragment(
                ChatUser(
                    firstName,
                    userName
                )
            )
            findNavController().navigate(action)
        }


    }

    private fun authenticateUser(
        firstName: String,
        firstNameInputLayout: TextInputLayout
    ): Boolean {
        return if (firstName.length <= 3) {
            firstNameInputLayout.error = "* Minimum 4 Characters Allowed"
            firstNameInputLayout.isErrorEnabled = true
            false
        } else {
            firstNameInputLayout.error = null
            firstNameInputLayout.isErrorEnabled = false
            true
        }

    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}