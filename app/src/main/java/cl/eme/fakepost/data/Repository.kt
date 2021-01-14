package cl.eme.fakepost.data

import timber.log.Timber

class Repository {

    private val postDatabase = PostApplication.postDatabase!!

    val postList = postDatabase.postDao().getPosts()

    suspend fun loadFromApiToDB() {
        val response = RetrofitClient.retrofitCliente().getPosts()

        when(response.isSuccessful) {
            true -> {
                response.body()?.let { postList ->
                    // Hacemos la conversion desde el objeto de la API a DB
                    val r = postList.map { mapAPI2DB(it) }
                    // Guardamos en la DB la lista de posts
                    postDatabase.postDao().insert(r)
                }
            }
            false -> {
                Timber.d("ups, respuesta OK pero sin body ${response.errorBody()}")
            }
        }
    }

}