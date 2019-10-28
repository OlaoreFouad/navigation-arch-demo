package dev.foodie.navigationdemo


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.findNavController
import dev.foodie.navigationdemo.databinding.FragmentMainBinding
import dev.foodie.navigationdemo.utils.Utils
import dev.foodie.navigationdemo.viewmodels.AppViewModel

const val KEY_BALANCE = "balance"

class MainFragment : Fragment() {

    private lateinit var binding: FragmentMainBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_main, container, false)
        val model: AppViewModel = activity?.run {
            ViewModelProviders.of(this).get(AppViewModel::class.java)
        } ?: throw Exception("Invalid activity!")

        binding.sendMoneyButton.setOnClickListener { it.findNavController().navigate(R.id.action_mainFragment_to_chooseRecipientsFragment) }
        binding.viewBalanceButton.setOnClickListener {
            val bundle = Bundle()

            bundle.putInt(Utils.KEY_BALANCE, model.balance.value ?: 0)

            it.findNavController().navigate(R.id.action_mainFragment_to_viewBalanceFragment, bundle)
        }

        binding.viewTransactionsButton.setOnClickListener {
            it.findNavController().navigate(R.id.action_mainFragment_to_viewTransactionsFragment)
        }

        return binding.root
    }


}
