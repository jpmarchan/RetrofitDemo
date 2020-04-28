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
        CoroutineScope(Dispatchers.Main).launch {
            val postIdDeferred = Api.retrofitService.getPostByIdAsync(postId)
            try {
                tvResult.text = getString(R.string.wait_text)
                val post = postIdDeferred.await()
                tvResult.text = post.toString()
            } catch (e: Exception) {
                tvResult.text = getString(R.string.error_text, e.message)
            }
        }
    }

    private fun getAllPosts() {
        CoroutineScope(Dispatchers.Main).launch {
            val postsDeferred = Api.retrofitService.getAllPostsAsync()
            try {
                tvResult.text = getString(R.string.wait_text)
                val postList = postsDeferred.await()
                tvResult.text = postList.toString()
            } catch (e: Exception) {
                tvResult.text = getString(R.string.error_text, e.message)
            }
        }
    }

    private fun getAllUsers() {
        CoroutineScope(Dispatchers.Main).launch {
            val usersDeferred = Api.retrofitService.getAllUsersAsync()
            try {
                tvResult.text = getString(R.string.wait_text)
                val userList = usersDeferred.await()
                tvResult.text = userList.toString()
            } catch (e: Exception) {
                tvResult.text = getString(R.string.error_text, e.message)
            }
        }
    }
}
