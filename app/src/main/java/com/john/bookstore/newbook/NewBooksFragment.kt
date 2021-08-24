package com.john.bookstore.newbook

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.john.bookstore.R
import com.john.bookstore.base.BaseFragment
import com.john.bookstore.databinding.FragmentNewbooksBinding
import com.john.bookstore.setup
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NewBooksFragment : BaseFragment() {

    override val _viewModel: NewBooksViewModel by viewModels()
    private var _binding: FragmentNewbooksBinding? = null

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentNewbooksBinding.inflate(inflater, container, false).apply {
            viewModel = _viewModel
        }

        return binding.root
    }

    override fun onResume() {
        super.onResume()
        _viewModel.loadNewBooks()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.lifecycleOwner = this

        binding.searchBooksFAB.setOnClickListener {
            findNavController().navigate(R.id.action_NewBooksFragment_to_bookSearchFragment)
        }
        setupRecyclerView()

    }

    private fun setupRecyclerView() {
        val adapter = BooksAdapter {
            findNavController().navigate(NewBooksFragmentDirections.actionNewBooksFragmentToDetailBookFragment(it.isbn13))
        }
        binding.booksRecyclerView.setup(adapter)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}