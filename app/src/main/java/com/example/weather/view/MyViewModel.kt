package com.example.weather.view

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.weather.R
import com.example.weather.Repository
import com.example.weather.os.Strings
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import javax.inject.Inject

@HiltViewModel
class MyViewModel @Inject constructor(
    private val repository: Repository,
    private val viewMapper: ViewMapper,
    private val strings: Strings
) : ViewModel() {

    val items: LiveData<ItemsValue> get() = _items
    private val _items by lazy {
        MutableLiveData<ItemsValue>().also { _items ->
            repository
                .itemsWithDetails()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                    { itemsWithDetails ->
                        _items.value = ItemsValue.Items(
                            itemsWithDetails
                                .map { viewMapper.map(it.first) }
                                .toList()
                        )
                        _details.value = itemsWithDetails
                            .map { viewMapper.map(it.second) }
                            .toList()
                    },
                    {
                        _items.value = ItemsValue.Error(strings.get(R.string.fetchError))
                    }
                )
        }
    }

    val details: LiveData<List<DetailsViewData>> get() = _details
    private val _details = MutableLiveData<List<DetailsViewData>>()
}

sealed class ItemsValue {
    data class Items(val items: List<ItemViewData>) : ItemsValue()
    data class Error(val message: CharSequence) : ItemsValue()
}