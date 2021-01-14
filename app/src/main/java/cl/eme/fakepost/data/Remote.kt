package cl.eme.fakepost.data

import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

// POJO
data class Post(val userId: Int, val id: Int, val title: String, val body: String)

// Interfaz de operaciones
interface PostAPI {

    @GET("posts")
    suspend fun getPosts(): Response<List<Post>>
}

// Cliente de retrofit
class RetrofitClient {
    companion object {
        private const val BASE_URL = "https://jsonplaceholder.typicode.com/"

        fun retrofitCliente () : PostAPI {
            val retrofit = Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create()).build()

            return retrofit.create(PostAPI::class.java)
        }
    }
}