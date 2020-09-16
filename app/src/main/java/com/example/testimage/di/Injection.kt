package com.example.testimage.di

import androidx.lifecycle.ViewModelProvider
import com.example.testimage.MainActivityViewModelFactory
import com.example.testimage.repository.MainRepository

object Injection {

    private val _image:MainRepository = MainRepository()
    private val image = MainActivityViewModelFactory(_image)
    fun provideLoginViewModelFactory(): ViewModelProvider.Factory{
        return image
    }



}