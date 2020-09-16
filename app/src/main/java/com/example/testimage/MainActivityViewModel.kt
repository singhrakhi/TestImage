package com.example.testimage

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.testimage.datamodel.*
import com.example.testimage.repository.RepositoryCallback

class MainActivityViewModel(private var repo:RepositoryCallback) :ViewModel(){
    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading : LiveData<Boolean> = _isLoading

    private val _isError = MutableLiveData<Any>()
    val isError : LiveData<Any> =_isError

    private val  _myData=MutableLiveData<List<Data>>()
    val myData:LiveData<List<Data>> = _myData

    fun loadData()
    {
        _isLoading.postValue(true)
        repo.selectData("1",object : MyDataCallback<MyData>{
            override fun onSuccess(data: MyData) {
                _isLoading.postValue(false)
                if (data!=null)
                {
                    _myData.value=data.data
                }
                else{
                    _isError.postValue("Data Not Found")
                }
            }

            override fun onError(error: String?) {
                _isError.postValue(error)
                _isLoading.postValue(false)
            }
        })
    }
}