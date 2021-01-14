package cl.eme.fakepost

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import cl.eme.fakepost.databinding.ActivityMainBinding
import timber.log.Timber

class MainActivity : AppCompatActivity() {

    private val viewModel : MyViewModel by viewModels()

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val adapter = PostAdapter()
        binding.rvPosts.adapter = adapter

        initLog()

        viewModel.postList.observe(this, {
            it?.let {
                adapter.update(it)
            }
        })
    }

    private fun initLog() {
        Timber.plant(Timber.DebugTree())
    }
}