package com.fortatic.apps.retrofit

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

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
        CoroutineScope(Dispatchers.IO).launch {
            tvResult.text = getString(R.string.wait_text)
            val postReceived = try {
                Api.retrofitService.getPostById(postId)
            } catch (error: Exception) {
                getString(R.string.error_text, error.message)
            }
            tvResult.text = postReceived.toString()
        }
    }

    private fun getAllPosts() {
        CoroutineScope(Dispatchers.IO).launch {
            tvResult.text = getString(R.string.wait_text)
            val postsReceived = try {
                Api.retrofitService.getAllPosts()
            } catch (error: Exception) {
                getString(R.string.error_text, error.message)
            }
            tvResult.text = postsReceived.toString()
        }
    }

    private fun getAllUsers() {
        CoroutineScope(Dispatchers.IO).launch {
            tvResult.text = getString(R.string.wait_text)
            val usersReceived = try {
                Api.retrofitService.getAllUsers()
            } catch (error: Exception) {
                getString(R.string.error_text, error.message)
            }
            tvResult.text = usersReceived.toString()
        }
    }
}
