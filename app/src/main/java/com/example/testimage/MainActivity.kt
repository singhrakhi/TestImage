package com.example.testimage

import android.content.Context
import android.content.DialogInterface
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.example.testimage.adapter.MyAdapter
import com.example.testimage.di.Injection
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private lateinit var viewModel:MainActivityViewModel
    private lateinit var adpMy:MyAdapter
    var mode = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupViewModel()
        setUpUI()
    }
    fun setupViewModel()
    {
        viewModel=ViewModelProvider(this,Injection.provideLoginViewModelFactory()).get(MainActivityViewModel::class.java)
        viewModel.myData.observe(this, Observer {

            adpMy.update(it)
        })
        viewModel.isLoading.observe(this, Observer {
            if (it)
            {
                progressBar.visibility=View.VISIBLE
            }
            else{
                progressBar.visibility=View.GONE
            }
        })
        viewModel.isError.observe(this, Observer {
            if (!TextUtils.isEmpty(it.toString()))
            Toast.makeText(this,it.toString(),Toast.LENGTH_SHORT).show()
        })
    }
    fun setUpUI()
    {
        adpMy=MyAdapter(this,viewModel.myData.value?: emptyList())
        recyleImage.layoutManager= GridLayoutManager(this, 2)
        recyleImage.adapter=adpMy


        loadImage()
    }
    fun loadImage()
    {
        mode=connectedFrom()
        if (mode==0)
        {
            AlertDialog.Builder(this).setTitle("Connection!!").setMessage("Internet connection not available in your device.").setPositiveButton("Ok",
                DialogInterface.OnClickListener { dialogInterface, i ->
                }).show()
        }
        else if (mode==1)
        {
            viewModel.loadData()
        }
        else{
            viewModel.loadData()
        }
    }
    fun connectedFrom(): Int {
        val connectivityManager =
            getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        if (connectivityManager != null) {
            val capabilities =
                connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)
            if (capabilities != null) {
                if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR)) {
                    Log.i("Internet", "NetworkCapabilities.TRANSPORT_CELLULAR")
                    return 1
                } else if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI)) {
                    Log.i("Internet", "NetworkCapabilities.TRANSPORT_WIFI")
                    return 2
                }
            }
        }
        return 0
    }
}