package cl.eme.fakepost

import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import cl.eme.fakepost.data.Repository
import cl.eme.fakepost.data.mapDB2API
import kotlinx.coroutines.launch

class MyViewModel: ViewModel() {

    private val repository = Repository()

    val postList = Transformations.map(repository.postList) { entities ->
        entities.map { mapDB2API(it) }
    }

    init {
        viewModelScope.launch {
            repository.loadFromApiToDB()
        }
    }

}