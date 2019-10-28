package dev.foodie.navigationdemo.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import dev.foodie.navigationdemo.R
import dev.foodie.navigationdemo.models.Entry

class TxnsAdapater(var ctx: Context, var txns: MutableList<Entry>) : RecyclerView.Adapter<TxnsAdapater.TxnViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TxnViewHolder {
        val view = LayoutInflater.from(ctx).inflate(R.layout.transaction_item, parent, false)

        return TxnViewHolder(view)
    }

    override fun onBindViewHolder(holder: TxnViewHolder, position: Int) {
        val txn = txns.get(position)
        holder.recipient.text = txn.recipient
        holder.amount.text = "$${ txn.amount }"
    }

    override fun getItemCount(): Int {
        return txns.size
    }

    class TxnViewHolder(txnItem: View) : RecyclerView.ViewHolder(txnItem) {
        var recipient: TextView = txnItem.findViewById(R.id.txn_recipient)
        var amount: TextView = txnItem.findViewById(R.id.txn_amount)
    }

}