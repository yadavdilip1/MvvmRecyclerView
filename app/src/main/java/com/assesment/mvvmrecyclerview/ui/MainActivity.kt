package com.assesment.mvvmrecyclerview.ui

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.assesment.mvvmrecyclerview.databinding.ActivityMainBinding
import com.assesment.mvvmrecyclerview.utils.Resource
import com.assesment.mvvmrecyclerview.utils.handleApiError
import com.assesment.mvvmrecyclerview.utils.hide
import com.assesment.mvvmrecyclerview.utils.show
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val viewModel by viewModels<HomeViewModel>()
    private val homeRecyclerViewAdapter = HomeRecyclerViewAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.recyclerView.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = homeRecyclerViewAdapter
        }

        binding.swipeRefreshLyt.setOnRefreshListener {

            viewModel.getHomeListItems()
            binding.swipeRefreshLyt.isRefreshing = false

        }

        viewModel.homeListItemsLiveData.observe(this) { result ->
            when (result) {

                is Resource.Loading -> binding.progressBar.show()

                is Resource.Failure -> {

                    binding.progressBar.hide()
                    handleApiError(result)

                }
                is Resource.Success -> {
                    binding.progressBar.hide()
                    homeRecyclerViewAdapter.item = result.value
                }
            }
        }
    }
}