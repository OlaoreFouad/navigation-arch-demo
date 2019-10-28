package dev.foodie.navigationdemo


import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import dev.foodie.navigationdemo.databinding.FragmentConfirmationBinding
import dev.foodie.navigationdemo.models.Entry
import dev.foodie.navigationdemo.utils.Utils
import dev.foodie.navigationdemo.viewmodels.AppViewModel
import java.lang.Exception
import java.util.*

class ConfirmationFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding: FragmentConfirmationBinding = DataBindingUtil.inflate(
                inflater,
                R.layout.fragment_confirmation,
                container,
                false
        )

        val model: AppViewModel
        
        arguments?.apply {
            binding.recipient = "was sent to ${ getString(Utils.KEY_RECIPIENT) }"
            binding.amount = "$${ getString(Utils.KEY_AMOUNT) }"

            model = activity?.run {
                ViewModelProviders.of(this).get(AppViewModel::class.java)
            } ?: throw Exception("Invalid activity!")

            val entry = Entry(UUID.randomUUID().toString(), getString(Utils.KEY_RECIPIENT)!!, getString(Utils.KEY_AMOUNT)!!.toInt())
            model.addEntry(entry)
            model.effectBalance(entry.amount)
        }

        return binding.root
    }


}
