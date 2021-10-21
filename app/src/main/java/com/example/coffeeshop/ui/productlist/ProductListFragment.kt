package com.example.coffeeshop.ui.productlist

import android.app.SearchManager
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.appcompat.widget.SearchView
import androidx.core.content.ContextCompat.getSystemService
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import com.example.coffeeshop.R
import com.example.coffeeshop.databinding.FragmentProductListBinding
import com.example.coffeeshop.ui.BaseFragment
import com.example.coffeeshop.ui.productlist.ProductListAdapter.ProductListOnclickListener
import javax.inject.Inject
import androidx.core.view.MenuItemCompat
import com.example.coffeeshop.ui.MainActivity


class ProductListFragment : BaseFragment() {

    private lateinit var binding: FragmentProductListBinding

    private val viewModel by viewModels<ProductListViewModel> { viewModelFactoryProvider }

    private lateinit var productListAdapter: ProductListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.product_list_search_menu, menu)

        val searchItem = menu.findItem(R.id.action_search)
        val searchView = searchItem.actionView as SearchView
        searchView.queryHint = getString(R.string.search_hint)

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(p0: String?): Boolean {
                return true
            }

            override fun onQueryTextChange(text: String?): Boolean {
                Log.i("Query change", "Written text: $text")
                return true
            }
        })

    }

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