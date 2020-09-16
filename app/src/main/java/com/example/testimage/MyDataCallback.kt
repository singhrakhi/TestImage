package com.example.testimage

import com.example.testimage.datamodel.MyData

interface MyDataCallback<T> {
        fun onSuccess(data: MyData)
        fun onError(error:String?)
}