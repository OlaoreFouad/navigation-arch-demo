package dev.foodie.navigationdemo


import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import dev.foodie.navigationdemo.adapters.TxnsAdapater
import dev.foodie.navigationdemo.databinding.FragmentViewTransactionsBinding
import dev.foodie.navigationdemo.models.Entry
import dev.foodie.navigationdemo.viewmodels.AppViewModel
import java.lang.Exception

class ViewTransactionsFragment : Fragment() {

    lateinit var binding: FragmentViewTransactionsBinding
    lateinit var viewModel: AppViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
                inflater,
                R.layout.fragment_view_transactions,
                container,
                false
        )

        viewModel = activity?.run { ViewModelProviders.of(this).get(AppViewModel::class.java) } ?:
        throw Exception("Invalid activity")

        setList()

        return binding.root
    }

    private fun setList() {
        Log.i("S", "here...")

        viewModel.entries.observe((this as LifecycleOwner), Observer {
            binding.txnsList.apply {
                setHasFixedSize(true)
                layoutManager = LinearLayoutManager(context)
                adapter = TxnsAdapater(context, it)
            }

            if (it.isEmpty()) {
                binding.noTransactionsYetText.visibility = View.VISIBLE
            } else {
                binding.listContainer.visibility = View.VISIBLE
            }
        })
    }


}
