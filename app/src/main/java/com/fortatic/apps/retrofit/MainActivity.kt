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
        btnPostComment.setOnClickListener {
            postComment()
        }
    }

    private fun getCommentById(commentId: Int) {
        Api.retrofitService.getCommentById(commentId).enqueue(object : Callback<Comment> {
            @SuppressLint("SetTextI18n")
            override fun onFailure(call: Call<Comment>, error: Throwable) {
                //Almacenamos el mensaje de error en una variable.
                val errorMessage = error.message
                tvResult.text = "Error: $errorMessage"
                Log.d("FATAL", "onFailure: $errorMessage")
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
            @SuppressLint("SetTextI18n")
            override fun onFailure(call: Call<List<Comment>>, error: Throwable) {
                //Almacenamos el mensaje de error en una variable.
                val errorMessage = error.message
                tvResult.text = "Error: $errorMessage"
                Log.d("FATAL", "onFailure: $errorMessage")
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
            @SuppressLint("SetTextI18n")
            override fun onFailure(call: Call<List<User>>, error: Throwable) {
                //Almacenamos el mensaje de error en una variable.
                val errorMessage = error.message
                tvResult.text = "Error: $errorMessage"
                Log.d("FATAL", "onFailure: $errorMessage")
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

    private fun postComment() {
        //Creamos el objeto `comment` que vamos a postear.
        val comment = Comment(
            1,
            1,
            "Post from AS",
            "This comment has been sent from as"
        )
        //Llamamos al servicio y le pasamos el comment que acabamos de crear.
        Api.retrofitService.postComment(comment).enqueue(object : Callback<Comment>{
            @SuppressLint("SetTextI18n")
            override fun onFailure(call: Call<Comment>, error: Throwable) {
                //Almacenamos el mensaje de error en una variable.
                val errorMessage = error.message
                tvResult.text = "Error: $errorMessage"
                Log.d("FATAL", "onFailure: $errorMessage")
            }

            @SuppressLint("SetTextI18n")
            override fun onResponse(call: Call<Comment>, response: Response<Comment>) {
                //Verificamos que la respuesta sea exitosa.
                if (response.isSuccessful){
                    //Imprimos el codigo de respuesta:
                    //201: Creado
                    val responseCode = response.code()
                    Log.d("FATAL", "Codigo de respuesta: $responseCode")
                    tvResult.text = "Response code: $responseCode"
                }
            }
        })
    }
}
