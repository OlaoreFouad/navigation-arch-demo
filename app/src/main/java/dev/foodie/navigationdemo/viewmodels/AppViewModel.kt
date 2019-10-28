package dev.foodie.navigationdemo.viewmodels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dev.foodie.navigationdemo.models.Entry

class AppViewModel : ViewModel() {

    private val _entries = MutableLiveData<MutableList<Entry>>()
    val entries : LiveData<MutableList<Entry>>
        get() = _entries

    private val _balance = MutableLiveData<Int>()
    val balance : LiveData<Int>
        get() = _balance

    init {
        _balance.value = 3000
        _entries.value = mutableListOf()
    }

    fun addEntry(entry: Entry) {
        this._entries.value!!.add(entry)
        Log.i("S", "getting here... Size: ${ _entries.value!!.size }")
    }

    fun effectBalance(amount: Int) {
        val currBalance = this._balance.value
        this._balance.value = currBalance?.minus(amount)
    }

}