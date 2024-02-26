package com.magh.magh_07_mod4practicafinal

import android.content.Intent
import android.os.Bundle
import android.text.SpannableString
import android.text.Spanned
import android.text.method.LinkMovementMethod
import android.text.style.ForegroundColorSpan
import android.text.style.UnderlineSpan
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.snackbar.Snackbar
import com.magh.magh_07_mod4practicafinal.databinding.FragmentLoginBinding

class LoginFragment : Fragment() {

    private lateinit var binding : FragmentLoginBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentLoginBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //Make register link clickable
        binding.lblRegister.movementMethod = LinkMovementMethod.getInstance()
        binding.lblRegister.isClickable = true

        val spanString = SpannableString("${getString(R.string.text_no_account)} ${getString(R.string.label_register_link)}")
        val start = getString(R.string.text_no_account).length + 1
        val end = spanString.length

        val colorSpan = ForegroundColorSpan(resources.getColor(R.color.green, requireContext().theme))
        val clickableSpan = ClickSpan {
            parentFragmentManager.beginTransaction()
                .replace(R.id.fragmentContainer,RegisterFragment.newInstance())
                .addToBackStack("RegisterFragment")
                .commit()
        }

        spanString.setSpan(clickableSpan, start, end, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
        spanString.setSpan(colorSpan, start, end, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
        spanString.setSpan(UnderlineSpan(), start, end, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)

        binding.lblRegister.text = spanString

        //btnLoginSubmit
        binding.btnLoginSubmit.setOnClickListener {
            //Validation
            val emailRegex = "^[A-Za-z](.*)([@])(.+)(\\.)(.+)".toRegex()
            if(binding.txtLoginEmail.text.toString().trim()=="") {
                Snackbar.make(binding.btnLoginSubmit, getString(R.string.validation_no_email), 1000).show()
            }else if(!emailRegex.matches(binding.txtLoginEmail.text.toString().trim())){
                Snackbar.make(binding.btnLoginSubmit, getString(R.string.validation_wrong_email_format), 1000).show()
            }else if(binding.txtLoginPassword.text.toString().trim()==""){
                Snackbar.make(binding.btnLoginSubmit, getString(R.string.validation_no_password), 1000).show()
            }else {
                //Send data
                val user = User(
                    null, null, null,
                    binding.txtLoginEmail.text.toString(),
                    binding.txtLoginPassword.text.toString()
                )
                val nextActivity = Intent(requireContext(), MainActivity::class.java).apply {
                    putExtra("EXTRA_USER", user)
                }
                startActivity(nextActivity)
            }
        }

    }

    companion object {
        @JvmStatic
        fun newInstance() = LoginFragment()
    }
}