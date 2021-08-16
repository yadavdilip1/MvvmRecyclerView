package com.assesment.mvvmrecyclerview.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.assesment.mvvmrecyclerview.data.model.User
import com.assesment.mvvmrecyclerview.data.repository.Repository
import com.assesment.mvvmrecyclerview.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repository: Repository
) : ViewModel() {

    private val _homeListItemsLiveData = MutableLiveData<Resource<List<User>>>()
    val homeListItemsLiveData: LiveData<Resource<List<User>>>
        get() = _homeListItemsLiveData

    init {
        getHomeListItems()
    }

    fun getHomeListItems() = viewModelScope.launch {

        _homeListItemsLiveData.value = Resource.Loading
        _homeListItemsLiveData.value = repository.getUsers()
    }
}