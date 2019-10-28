package dev.foodie.navigationdemo


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import dev.foodie.navigationdemo.databinding.FragmentChooseRecipientsBinding
import dev.foodie.navigationdemo.utils.Utils

class ChooseRecipientsFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding: FragmentChooseRecipientsBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_choose_recipients, container, false)

        binding.cancelButton.setOnClickListener { it.findNavController().navigate(R.id.action_chooseRecipientsFragment_to_mainFragment) }
        binding.nextButton.setOnClickListener {
            val recipient = binding.recipientEditText.text.toString()
            if (recipient.isEmpty()) {
                Toast.makeText(context, "No recipient specified!", Toast.LENGTH_SHORT).show()
            } else {
                val bundle = Bundle()
                bundle.putString(Utils.KEY_RECIPIENT, recipient)
                it.findNavController().navigate(R.id.action_chooseRecipientsFragment_to_specifyAmountFragment, bundle)
            }
        }

        return binding.root
    }


}
