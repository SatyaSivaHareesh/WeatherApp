package com.example.myapplication.ui

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import com.example.myapplication.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupObservers()
        viewModel.getWeather()
    }


    private fun setupObservers() {
        viewModel.getViewState().flowWithLifecycle(lifecycle, Lifecycle.State.STARTED)
            .onEach { state -> handleStateChange(state) }.launchIn(lifecycleScope)
    }

    private fun handleStateChange(state: MainViewState) {
        when (state) {
            is MainViewState.Init -> Unit
            is MainViewState.Success -> {
                if (state.data.isNotEmpty()) {
                    val result = state.data.joinToString {
                        it.name + " " + it.temp
                    }
                    binding.nameTv.text = result
                }
            }

            is MainViewState.Error -> {
                if (state.error.isNotEmpty()) {
                    val result = state.error.joinToString {
                        it.asString(this@MainActivity)
                    }
                    binding.nameTv.text = result
                }
            }
        }
    }
}