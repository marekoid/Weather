package com.example.weather.view

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.weather.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import javax.inject.Inject

@HiltViewModel
class MyViewModel @Inject constructor(
    private val repository: Repository,
    private val viewMapper: ViewMapper
) : ViewModel() {

    val items: LiveData<List<ItemViewData>> get() = _items
    private val _items by lazy {
        MutableLiveData<List<ItemViewData>>().also { _items ->
            repository
                .itemsWithDetails()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe { itemsWithDetails ->
                    _items.value = itemsWithDetails
                        .map { viewMapper.map(it.first) }
                        .toList()
                    _details.value = itemsWithDetails
                        .map { viewMapper.map(it.second) }
                        .toList()
                }
        }
    }

    val details: LiveData<List<DetailsViewData>> get() = _details
    private val _details = MutableLiveData<List<DetailsViewData>>()
}