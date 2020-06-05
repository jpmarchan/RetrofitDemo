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

        btnGetCommentById.setOnClickListener {
            getCommentById(3)
        }
        btnGetAllComments.setOnClickListener {
            getAllComments()
        }
        btnGetAllUsers.setOnClickListener {
            getAllUsers()
        }
    }

    private fun getCommentById(commentId: Int) {
        Api.retrofitService.getCommentById(commentId).enqueue(object : Callback<Comment> {
            override fun onFailure(call: Call<Comment>, error: Throwable) {
                Log.d("FATAL", "onFailure: ${error.message}")
            }

            override fun onResponse(call: Call<Comment>, response: Response<Comment>) {
                //Verificamos que la respuesta sea exitosa.
                if (response.isSuccessful) {
                    //Almacenamos el comment recuperado en una variable
                    val comment = response.body()
                    //Solo si la variable no es nulla, la mostramos en el TextView.
                    comment?.let {
                        tvResult.text = it.toString()
                    }
                }
            }
        })
    }

    private fun getAllComments() {
        Api.retrofitService.getAllComments().enqueue(object : Callback<List<Comment>> {
            override fun onFailure(call: Call<List<Comment>>, error: Throwable) {
                Log.d("FATAL", "onFailure: ${error.message}")
            }

            @SuppressLint("SetTextI18n")
            override fun onResponse(call: Call<List<Comment>>, response: Response<List<Comment>>) {
                //Verificamos que la respuesta sea exitosa.
                if (response.isSuccessful) {
                    //Almacenamos la lista recuperada en la variable commentsList.
                    val commentsList = response.body()
                    //Limpiamos el textView
                    tvResult.text = ""
                    //Solo si la variable commentsList no es nula, la recorremos.
                    commentsList?.forEach { comment ->
                        // \n\r nos da el salto de linea.
                        tvResult.text = "${tvResult.text}\n\r $comment"
                    }
                }
            }
        })
    }

    private fun getAllUsers() {
        Api.retrofitService.getAllUsers().enqueue(object : Callback<List<User>> {
            override fun onFailure(call: Call<List<User>>, error: Throwable) {
                Log.d("FATAL", "onFailure: ${error.message}")
            }

            @SuppressLint("SetTextI18n")
            override fun onResponse(call: Call<List<User>>, response: Response<List<User>>) {
                //Verificamos que la respuesta sea exitosa.
                if (response.isSuccessful) {
                    //Almacenamos la lista recuperada en la variable userList.
                    val userList = response.body()
                    //Limpiamos el textView
                    tvResult.text = ""
                    //Solo si la variable userList no es nula, la recorremos.
                    userList?.forEach { user ->
                        // \n\r nos da el salto de linea.
                        tvResult.text = "${tvResult.text}\n\r $user"
                    }
                }
            }
        })
    }
}
