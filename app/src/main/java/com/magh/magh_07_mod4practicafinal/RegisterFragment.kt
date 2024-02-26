package com.magh.magh_07_mod4practicafinal

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import com.google.android.material.snackbar.Snackbar
import com.magh.magh_07_mod4practicafinal.databinding.FragmentRegisterBinding

class RegisterFragment : Fragment() {

    private lateinit var binding : FragmentRegisterBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentRegisterBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //comCountry
        ArrayAdapter(requireContext(), R.layout.spinner_item, resources.getStringArray(R.array.countries)).also {
            it.setDropDownViewResource(R.layout.spinner_dropdown_item)
            binding.comRegisterCountry.adapter=it
        }

        //btnRegisterSubmit
        binding.btnRegisterSubmit.setOnClickListener {
            //Validation
            val emailRegex = "^[A-Za-z](.*)([@])(.+)(\\.)(.+)".toRegex()
            if(binding.txtRegisterFirstName.text.toString().trim()==""){
                Snackbar.make(binding.btnRegisterSubmit, getString(R.string.validation_no_first_name), 1000).show()
            }else if(binding.txtRegisterLastName.text.toString().trim()=="") {
                Snackbar.make(binding.btnRegisterSubmit, getString(R.string.validation_no_last_name), 1000).show()
            }else if(binding.comRegisterCountry.selectedItemPosition==0){
                Snackbar.make(binding.btnRegisterSubmit, getString(R.string.validation_no_country), 1000).show()
            }else if(binding.txtRegisterEmail.text.toString().trim()=="") {
                Snackbar.make(binding.btnRegisterSubmit, getString(R.string.validation_no_email), 1000).show()
            }else if(!emailRegex.matches(binding.txtRegisterEmail.text.toString().trim())){
                Snackbar.make(binding.btnRegisterSubmit, getString(R.string.validation_wrong_email_format), 1000).show()
            }else if(binding.txtRegisterPassword.text.toString().trim()==""){
                Snackbar.make(binding.btnRegisterSubmit, getString(R.string.validation_no_password), 1000).show()
            }else if(binding.txtRegisterPassword2.text.toString().trim()==""){
                Snackbar.make(binding.btnRegisterSubmit, getString(R.string.validation_no_password2), 1000).show()
            }else if(binding.txtRegisterPassword.text.toString().trim()!=binding.txtRegisterPassword2.text.toString().trim()){
                Snackbar.make(binding.btnRegisterSubmit, getString(R.string.validation_no_password_match), 1000).show()
            }else if(!binding.chkTerms.isChecked){
                Snackbar.make(binding.btnRegisterSubmit,
                    getString(R.string.validation_no_terms), 1000).show()
            }else {
                //Send data
                val user = User(
                    binding.txtRegisterFirstName.text.toString(),
                    binding.txtRegisterLastName.text.toString(),
                    binding.comRegisterCountry.selectedItem.toString(),
                    binding.txtRegisterEmail.text.toString(),
                    binding.txtRegisterPassword.text.toString()
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
        fun newInstance() = RegisterFragment()
    }
}