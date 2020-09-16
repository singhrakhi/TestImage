package com.example.testimage.repository

import android.util.Log
import com.example.testimage.MyDataCallback
import com.example.testimage.datamodel.MyData
import com.example.testimage.util.ApiClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainRepository:RepositoryCallback {
    private var call:Call<MyData>?=null
    override fun selectData(name: String,
                            callback: MyDataCallback<MyData>) {

        call= ApiClient.build()?.getData("1")
        call?.enqueue(object : Callback<MyData> {
            override fun onFailure(call: Call<MyData>, t: Throwable) {
                Log.d("TAG", "onFailure: "+t.message)
                callback.onError(t.message)
            }

            override fun onResponse(call: Call<MyData>, response: Response<MyData>) {
                response.body()?.let {
                    if(it.status==1){
                        callback.onSuccess(it)
                    }else{
                        callback.onError("No Image Found For Your Search")
                    }
                }
            }
        })
    }

    override fun cancel() {

    }
}