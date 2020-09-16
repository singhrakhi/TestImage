package com.example.testimage.repository

import com.example.testimage.MyDataCallback
import com.example.testimage.datamodel.MyData

interface RepositoryCallback {
    fun selectData(category_id:String,callback: MyDataCallback<MyData>)
    fun cancel()
}