package com.magh.magh_07_mod4practicafinal

import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.magh.magh_07_mod4practicafinal.databinding.FragmentUserDataBinding

class UserDataFragment : Fragment() {

    private lateinit var binding : FragmentUserDataBinding

    private var user : User? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.let{
             user =if(Build.VERSION.SDK_INT>= Build.VERSION_CODES.TIRAMISU){
                it.getSerializable("EXTRA_USER", User::class.java)
            }else{
                it.getSerializable("EXTRA_USER") as User
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentUserDataBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var info = ""
        user?.let{
            user!!.firstName?.let{
                info += "${getString(R.string.label_first_name)}: ${user!!.firstName}\n\n"
            }
            user!!.lastName?.let{
                info += "${getString(R.string.label_last_name)}: ${user!!.lastName}\n\n"
            }
            user!!.country?.let{
                info += "${getString(R.string.label_country)}: ${user!!.country}\n\n"
            }
            user!!.email?.let{
                info += "${getString(R.string.label_email)}: ${user!!.email}\n\n"
            }
            user!!.password?.let{
                info += "${getString(R.string.label_password)}: ${user!!.password}"
            }
        }
        binding.lblUserData.text = info
    }

    companion object {
        @JvmStatic
        fun newInstance(user: User?) =
            UserDataFragment().apply{
                arguments = Bundle().apply{
                    putSerializable("EXTRA_USER",user)
                }
            }
    }
}