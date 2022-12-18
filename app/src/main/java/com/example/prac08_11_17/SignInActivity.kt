package com.example.and_cafe
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.Volley
import com.example.and_cafe.databinding.ActivitySignInBinding
import org.json.JSONArray
import org.json.JSONObject


class SignInActivity : AppCompatActivity() {
    lateinit var binding: ActivitySignInBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_sign_in)
        binding = ActivitySignInBinding.inflate(layoutInflater)
        setContentView(binding.root)
        var logIn = false

        binding.IsSignIn.setOnClickListener {
            val id = binding.idid.text.toString()
            val pass = binding.passpass.text.toString()
            var url = "http://10.0.2.2:8080/data"
            //var url = "https://jsonplaceholder.typicode.com/posts/1"
//            val stringRequest = StringRequest(
//                Request.Method.GET, url,
//                Response.Listener<String>{
//                    Log.d("jj","serverdata $it")
//            },
//                Response.ErrorListener{ error->
//                    Log.d("jj", "$it")
//
//                }
//            )
//            val queue = Volley.newRequestQueue(this)
//            queue.add(stringRequest)

            val jsonArrayRequest = JsonArrayRequest(
                Request.Method.GET, url, null,
                Response.Listener<JSONArray> { response ->
                    for(i in 0 until response.length()){
                        val jsonObject = response[i] as JSONObject
                        val title = jsonObject.getString("email")
                        val body = jsonObject.getString("password")
                        if(id == title)
                            if(pass == body)  {
                                logIn = true
                            }
                    }
                },
                Response.ErrorListener { error -> Log.d("jj","error")  })

            if(logIn){ // 로그인시 화면 변경
                Toast.makeText(this,"$id 님 환영합니다.", Toast.LENGTH_SHORT).show()
                val intent = Intent(this, LoadingActivity::class.java)
                startActivity(intent)
                finish()
            }
            else Toast.makeText(this,"정보가 일치하지 않습니다.", Toast.LENGTH_SHORT).show()

            val queue = Volley.newRequestQueue(this)
            queue.add(jsonArrayRequest)
        }
    }
    override fun onBackPressed() {
        super.onBackPressed()
        val intent = Intent(this, LoginActivity::class.java)
        startActivity(intent)
        finish()
    }
}