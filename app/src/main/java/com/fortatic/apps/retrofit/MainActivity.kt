package com.fortatic.apps.retrofit

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnGetPostById.setOnClickListener {
            getPostById(3)
        }
        btnGetAllPosts.setOnClickListener {
            getAllPosts()
        }
        btnGetAllUsers.setOnClickListener {
            getAllUsers()
        }
    }

    private fun getPostById(postId: Int) {
        Api.retrofitService.getPostById(postId).enqueue(object : Callback<Post> {
            override fun onFailure(call: Call<Post>, t: Throwable) {
                Log.d("AFRC", "onFailure: ${t.message}")
            }

            override fun onResponse(call: Call<Post>, response: Response<Post>) {
                Log.d("AFRC", "onResponse: ${response.body()}")
                if (response.isSuccessful) {
                    tvResult.text = ""
                    response.body()?.let {
                        tvResult.text = it.toString()
                    }
                }
            }
        })
    }

    private fun getAllPosts() {
        Api.retrofitService.getAllPosts().enqueue(object : Callback<List<Post>> {
            override fun onFailure(call: Call<List<Post>>, t: Throwable) {
                Log.d("AFRC", "onFailure: ${t.message}")
            }

            @SuppressLint("SetTextI18n")
            override fun onResponse(call: Call<List<Post>>, response: Response<List<Post>>) {
                if (response.isSuccessful) {
                    tvResult.text = ""
                    response.body()?.forEach {
                        tvResult.text = "${tvResult.text}\n\r${it}"
                    }
                }
            }
        })
    }

    private fun getAllUsers() {
        Api.retrofitService.getAllUsers().enqueue(object : Callback<List<User>> {
            override fun onFailure(call: Call<List<User>>, t: Throwable) {
                Log.d("AFRC", "onFailure: ${t.message}")
            }

            @SuppressLint("SetTextI18n")
            override fun onResponse(call: Call<List<User>>, response: Response<List<User>>) {
                if (response.isSuccessful) {
                    tvResult.text = ""
                    response.body()?.forEach {
                        // \n\r nos da el salto de linea.
                        tvResult.text = "${tvResult.text}\n\r${it.name}"
                    }
                }
            }
        })
    }
}
