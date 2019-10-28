package dev.foodie.navigationdemo


import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import dev.foodie.navigationdemo.databinding.FragmentSpecifyAmountBinding
import dev.foodie.navigationdemo.utils.Utils
import kotlinx.android.synthetic.main.fragment_specify_amount.*

class SpecifyAmountFragment : Fragment() {

    private lateinit var recipient_arg: String
    private var amount_arg: Int = 0

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val binding: FragmentSpecifyAmountBinding = DataBindingUtil.inflate(
                inflater,
                R.layout.fragment_specify_amount,
                container,
                false
            )

        binding.sendButton.setOnClickListener {
            val amount = amount_edit_text.text.toString()
            if (amount.isEmpty()) {
                Toast.makeText(context, "Amount is Empty!", Toast.LENGTH_SHORT).show()
            } else {
                amount_arg = amount.toInt()
                recipient_arg = arguments?.getString(Utils.KEY_RECIPIENT)!!

                Log.i("SpecifyAmountFragment", amount.toInt().toString())

                val bundle = Bundle()
                bundle.apply {
                    putString(Utils.KEY_RECIPIENT, recipient_arg)
                    putString(Utils.KEY_AMOUNT, amount.toInt().toString())
                }

                it.findNavController().navigate(R.id.action_specifyAmountFragment_to_confirmationFragment, bundle)
            }
        }

        binding.cancelButton.setOnClickListener {
            it.findNavController().navigate(R.id.action_specifyAmountFragment_to_mainFragment)
        }

        arguments?.apply {
            Log.i("SpecifyAmountFragment", getString(Utils.KEY_RECIPIENT)!!)
        }

        return binding.root

    }


}
