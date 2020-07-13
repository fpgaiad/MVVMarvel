package com.mvvmarvel.util

import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.mvvmarvel.viewmodel.CharactersViewModel

abstract class InfiniteScrollListener(
    private val layoutManager: GridLayoutManager,
    private val isFromFavorites: Boolean,
    private val viewModel: CharactersViewModel
) : RecyclerView.OnScrollListener() {

    private val visibleThreshold = 5
    private val startingPage = 0
    private var currentPage = 0
    private var previousTotalItemCount = 0
    private var isLoading = true

    override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
        super.onScrolled(recyclerView, dx, dy)

        if (dy > 0) {
            val lastVisibleItemPosition = layoutManager.findLastVisibleItemPosition()
            val totalItemCount = layoutManager.itemCount

            if (totalItemCount < previousTotalItemCount) {
                this.currentPage = this.startingPage
                this.previousTotalItemCount = totalItemCount
                if (totalItemCount == 0) {
                    this.isLoading = true
                }
            }

            if (isLoading && totalItemCount > previousTotalItemCount) {
                isLoading = false
                previousTotalItemCount = totalItemCount
            }

            if (!isLoading && lastVisibleItemPosition + visibleThreshold > totalItemCount) {
                if (!isFromFavorites && !viewModel.isLastPage) {
                    currentPage++
                    onLoadMore()
                    isLoading = true
                }
            }
        }
    }

    abstract fun onLoadMore()
}