package com.mutsanna.belajarretrofit

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.LinearLayout
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.ArrayList

class MainActivity : AppCompatActivity() {

    private val list = ArrayList<PostResponse>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        showPosts()
        createPosts()
    }

    private fun createPosts() {
        RetrofitClient.instance.createPost(
            29,
            "Retrofit tutorial",
            "Retrofit tutorial for beginner"
        ).enqueue(object : Callback<CreatePostResponse>{
            override fun onResponse(
                call: Call<CreatePostResponse>,
                response: Response<CreatePostResponse>
            ) {
                val responseText = "Response code: ${response.code()}\n" +
                        "title : ${response.body()?.title}\n" +
                        "body : ${response.body()?.text}\n" +
                        "userId : ${response.body()?.userId}\n" +
                        "id : ${response.body()?.id}"
                tvResponseCode.text = responseText
            }

            override fun onFailure(call: Call<CreatePostResponse>, t: Throwable) {
                tvResponseCode.text = t.message
            }

        })
    }

    private fun showPosts() {
        rvPost.setHasFixedSize(true)
        rvPost.layoutManager = LinearLayoutManager(this)

        RetrofitClient.instance.getPost().enqueue(object: Callback<ArrayList<PostResponse>>{
            override fun onResponse(
                call: Call<ArrayList<PostResponse>>,
                response: Response<ArrayList<PostResponse>>
            ) {
                val responseCode = response.code().toString()
                tvResponseCode.text = responseCode
                response.body()?.let {list.addAll(it)}

                val adapter = PostAdapter(list)
                rvPost.adapter = adapter
            }

            override fun onFailure(call: Call<ArrayList<PostResponse>>, t: Throwable) {

            }

        })
    }
}