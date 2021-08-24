package com.john.bookstore.booklist

import android.os.Bundle
import android.view.*
import androidx.core.view.forEach
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.john.bookstore.R
import com.john.bookstore.base.BaseFragment
import com.john.bookstore.data.ORDER
import com.john.bookstore.databinding.FragmentBooksBinding
import com.john.bookstore.setTitle
import com.john.bookstore.setup
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.item_book.*

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
@AndroidEntryPoint
class BooksFragment : BaseFragment() {

    override val _viewModel: BooksViewModel by viewModels()
    private var _binding: FragmentBooksBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    private var order = ORDER.RATING

    lateinit var menu: Menu

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentBooksBinding.inflate(inflater, container, false).apply {
            viewModel = _viewModel
        }

        setHasOptionsMenu(true)
        return binding.root
    }

    override fun onResume() {
        super.onResume()
        _viewModel.loadBooks(order)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.lifecycleOwner = this
        binding.addBooksFAB.setOnClickListener {
            findNavController().navigate(R.id.action_BooksFragment_to_NewBooksFragment)
        }

        setupRecyclerView()
    }

    private fun setupRecyclerView() {
        val adapter = BooksAdapter {
            findNavController().navigate(BooksFragmentDirections.actionBooksFragmentToDetailBookFragment(it.isbn13))
        }
        binding.booksRecyclerView.setup(adapter)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.menu_main, menu)
        this.menu = menu
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.order_rating ->  {
                order = ORDER.RATING
                _viewModel.loadBooks(order)
            }
            R.id.order_pricing ->  {
                order = ORDER.PRICE
                _viewModel.loadBooks(order)
            }
            R.id.order_publising ->  {
                order = ORDER.PUBLISHED
                _viewModel.loadBooks(order)
            }
            R.id.histroy -> {
                _viewModel.loadHistoryBooks()
                setMenuVisibility(item)
                setTitle(resources.getString(R.string.history))
            }
            R.id.bookmark -> {
                _viewModel.loadBooks(order)
                setMenuVisibility(item)
                setTitle(resources.getString(R.string.bookmark))
            }
            R.id.deleteAll -> {
                _viewModel.deleteAllHistory()
            }
            else -> ORDER.RATING
        }

        return super.onOptionsItemSelected(item)
    }

    fun setMenuVisibility(item: MenuItem) {
        when(item.itemId) {
            R.id.histroy -> {
                menu.forEach {
                    when(it.itemId) {
                        R.id.deleteAll, R.id.bookmark -> it.isVisible = true
                        else -> it.isVisible = false
                    }
                }
            }
            R.id.bookmark -> {
                menu.forEach {
                    when(it.itemId) {
                        R.id.bookmark, R.id.deleteAll -> it.isVisible = false
                        else -> it.isVisible = true
                    }

                }
            }
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}