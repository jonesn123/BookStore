package com.john.bookstore.detailbook

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.john.bookstore.R
import com.john.bookstore.base.BaseFragment
import com.john.bookstore.databinding.FragmentDetailBookBinding
import com.john.bookstore.utils.sendNotification
import dagger.hilt.android.AndroidEntryPoint


/**
 * A simple [Fragment] subclass.
 * Use the [DetailBookFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
@AndroidEntryPoint
class DetailBookFragment : BaseFragment() {
    private val navArgs by navArgs<DetailBookFragmentArgs>()
    override val _viewModel: DetailBookViewModel by viewModels()
    private var _binding: FragmentDetailBookBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentDetailBookBinding.inflate(inflater, container, false).apply {
            viewModel = _viewModel
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.lifecycleOwner = viewLifecycleOwner

        binding.favoriteBtn.setOnClickListener {
            _viewModel.toggleLiked(navArgs.isbn13)
            binding.image.performClick()
        }

        binding.saveBtn.setOnClickListener {
            if (binding.memo.text.isNotEmpty()) {
                _viewModel.saveMemo(
                    binding.memo.text.toString(),
                    navArgs.isbn13
                )

                Toast.makeText(context, R.string.save_memo, Toast.LENGTH_SHORT).show()
            }
        }

        binding.toWeb.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(_viewModel.book.value?.url))
            intent.flags = Intent.FLAG_ACTIVITY_SINGLE_TOP
            startActivity(intent)
        }

        _viewModel.book.observe(viewLifecycleOwner, {
            if (it.isLiked) {
                binding.image.setImageResource(R.drawable.outline_favorite)
            } else {
                binding.image.setImageResource(R.drawable.outline_favorite_border)
            }
        })

        _viewModel.sendNotify.observe(viewLifecycleOwner, {
            sendNotification(requireContext(), it)
        })
    }

    override fun onResume() {
        super.onResume()
        _viewModel.fetchBookInformation(navArgs.isbn13)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}