package com.example.coffeeshop.ui.productlist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import com.example.coffeeshop.databinding.FragmentProductListBinding
import com.example.coffeeshop.ui.BaseFragment
import com.example.coffeeshop.ui.productlist.ProductListAdapter.ProductListOnclickListener
import javax.inject.Inject

class ProductListFragment : BaseFragment() {

    private lateinit var binding: FragmentProductListBinding

    private val viewModel by viewModels<ProductListViewModel> { viewModelFactoryProvider }

    private lateinit var productListAdapter: ProductListAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentProductListBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        productListAdapter = ProductListAdapter(ProductListOnclickListener())
        binding.productsRecyclerview.adapter = productListAdapter
        viewModel.refreshProducts()
        observeMoreViewModels()
    }

    private fun observeMoreViewModels() {
        with(viewModel) {

            products.observe(viewLifecycleOwner) { products ->
                products?.let { list ->
                    productListAdapter.submitList(list)
                }

            }

            dataFetchState.observe(viewLifecycleOwner) { state ->
                binding.apply {
                    productsRecyclerview.isVisible = state
                    productListErrorText?.isVisible = !state
                }
            }
            isLoading.observe(viewLifecycleOwner) { state ->
                binding.productListProgressBar.isVisible = state
            }
        }
        binding.productListSwipeRefresh.setOnRefreshListener {
            initiateRefresh()
        }
    }

    private fun initiateRefresh() {
        binding.productListErrorText?.visibility = View.GONE
        binding.productListProgressBar.visibility = View.VISIBLE
        binding.productsRecyclerview.visibility = View.GONE
        viewModel.refreshProducts()
        binding.productListSwipeRefresh.isRefreshing = false
    }
}