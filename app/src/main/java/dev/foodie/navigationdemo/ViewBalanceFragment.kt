package dev.foodie.navigationdemo

import android.content.Context
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import dev.foodie.navigationdemo.databinding.FragmentViewBalanceBinding
import dev.foodie.navigationdemo.utils.Utils

class ViewBalanceFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding: FragmentViewBalanceBinding = DataBindingUtil.inflate(
                inflater,
                R.layout.fragment_view_balance,
                container,
                false
        )

        arguments?.apply {
            val balance = getInt(Utils.KEY_BALANCE)
            binding.balance = "$${ balance }"
        }

        return binding.root
    }
}
