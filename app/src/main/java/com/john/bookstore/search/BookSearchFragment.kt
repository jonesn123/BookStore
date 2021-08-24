package com.john.bookstore.search

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.john.bookstore.base.BaseFragment
import com.john.bookstore.databinding.FragmentSearchBinding
import com.john.bookstore.newbook.BooksAdapter
import com.john.bookstore.setup
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class BookSearchFragment : BaseFragment() {
    override val _viewModel: BookSearchViewModel by viewModels()

    private var currentPage = 1
    private var _binding: FragmentSearchBinding? = null
    private val binding get() = _binding!!
    lateinit var recyclerAdapter: BooksAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentSearchBinding.inflate(inflater, container, false).apply {
            viewModel = _viewModel
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.lifecycleOwner = this

        binding.search.apply {
            setOnQueryTextListener(object: SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(query: String?): Boolean {
                    query?.let {
                        requestFirst(it)
                    }
                    return false
                }

                override fun onQueryTextChange(newText: String?): Boolean {
                    return false
                }
            })

            setOnQueryTextFocusChangeListener { v, hasFocus ->
                if(hasFocus) {
                    showInputMethod(v.findFocus())
                }
            }
            onActionViewExpanded()

        }

        setupRecyclerView()
    }

    private fun showInputMethod(view: View) {
        val imm = context?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager?
        imm?.showSoftInput(view, 0)
    }
    private fun requestFirst(query: String) {
        currentPage = 1
        recyclerAdapter.clear()
        _viewModel.searchBook(query, currentPage)
    }

    private fun setupRecyclerView() {
        recyclerAdapter = BooksAdapter {
            findNavController().navigate(
                BookSearchFragmentDirections.actionBookSearchFragmentToDetailBookFragment(it.isbn13)
            )
        }
        binding.booksRecyclerView.apply {
            setup(recyclerAdapter)
            addOnScrollListener(object: PaginationListener(layoutManager as LinearLayoutManager) {
                override fun loadMoreItems() {
                    currentPage++
                    _viewModel.searchBook(binding.search.query.toString(), currentPage)
                }

                override fun isLastPage(): Boolean = _viewModel.isLastPage.value ?: false

                override fun isLoading(): Boolean = _viewModel.showLoading.value ?: false
            })
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}