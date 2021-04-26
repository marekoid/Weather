package com.example.weather.view

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.weather.Details
import com.example.weather.Item
import com.example.weather.Repository
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers

class MyViewModel(private val repository: Repository) : ViewModel() {

    val items: LiveData<List<Item>> get() = _items
    private val _items by lazy {
        MutableLiveData<List<Item>>().also { _items ->
            repository
                .itemsWithDetails()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe { itemsWithDetails ->
                    _items.value = itemsWithDetails
                        .map { it.first }
                        .toList()
                    _details.value = itemsWithDetails
                        .map { it.second }
                        .toList()
                }
        }
    }

    val details: LiveData<List<Details>> get() = _details
    private val _details = MutableLiveData<List<Details>>()
}