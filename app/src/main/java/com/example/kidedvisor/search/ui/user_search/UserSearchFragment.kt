package com.example.kidedvisor.search.ui.user_search

import android.content.Context
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.kidedvisor.R
import com.example.kidedvisor.club.presentation.ui.fragment.ClubFragmentDirections
import com.example.kidedvisor.databinding.FragmentStartSearchBinding
import com.example.kidedvisor.search.presenter.user_search.UserSearchIntent
import com.example.kidedvisor.search.presenter.user_search.UserSearchState
import com.example.kidedvisor.search.presenter.user_search.UserSearchViewModel
import com.example.kidedvisor.search.ui.SearchFragmentDirections
import com.example.kidedvisor.search.ui.user_search.result_search.ResultSearchAdapter
import com.example.kidedvisor.search.ui.user_search.start_search.HeaderFirstItemDecoration
import com.example.kidedvisor.search.ui.user_search.start_search.StartSearchAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel

class UserSearchFragment : Fragment() {

    private var _binding: FragmentStartSearchBinding? = null
    private val binding get() = _binding!!

    private val viewModel by viewModel<UserSearchViewModel>()

    private val startSearchAdapter = StartSearchAdapter { clubId ->
        Navigation.findNavController(requireActivity(), R.id.container_view)
            .navigate(SearchFragmentDirections.actionSearchFragmentToClubFragment(clubId))
    }
    private val resultSearchAdapter = ResultSearchAdapter { clubId ->
        Navigation.findNavController(requireActivity(), R.id.container_view)
            .navigate(SearchFragmentDirections.actionSearchFragmentToClubFragment(clubId))
    }

    private var inputSearchText = DEF_TEXT

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentStartSearchBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.userSearchState.observe(viewLifecycleOwner) { state ->
            render(state)
        }

        // При открытии фрагмента устанавливается фокус на поле ввода запроса
        binding.editText.requestFocus()

        binding.closeSearchAction.setOnClickListener {
            findNavController().popBackStack()
        }

        //Выполнение запроса на поиск с кнопки на клавиатуре
        binding.editText.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                viewModel.processIntent(
                    UserSearchIntent.ResultSearchIntent(inputSearchText, false)
                )
                hideKeyboard()
            }
            false
        }

        val textWatcher = object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
//                TODO("Not yet implemented")
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                inputSearchText = p0.toString()

                if (inputSearchText.isEmpty()) {
                    viewModel.processIntent(UserSearchIntent.StartSearchIntent)
                } else if (inputSearchText.isNotEmpty()) {
                    viewModel.processIntent(
                        UserSearchIntent.ResultSearchIntent(inputSearchText, true)
                    )
                }
            }

            override fun afterTextChanged(p0: Editable?) {
//                TODO("Not yet implemented")
            }
        }

        binding.editText.addTextChangedListener(textWatcher)

        // клавиатура скрывается при прокрутке recyclerview
        binding.startSearchRecycler.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                if (newState == RecyclerView.SCROLL_STATE_DRAGGING) hideKeyboard()
            }
        })
    }

    private fun render(state: UserSearchState) {
        when (state) {
            is UserSearchState.ResultSearchState -> renderResultSearchState(state)
            is UserSearchState.StartSearchState -> renderStartSearchState(state)
        }
    }

    private fun renderResultSearchState(state: UserSearchState.ResultSearchState) {
        visibilityStartSearch(false)

        hideKeyboard()

        resultSearchAdapter.items = state.resultSearchRVItem
        binding.resultSearch.searchResultRecycler.adapter = resultSearchAdapter
    }

    private fun hideKeyboard() {
        val imm =
            requireContext().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(binding.editText.windowToken, 0)
    }

    private fun visibilityStartSearch(visibility: Boolean) {
        binding.apply {
            startSearchRecycler.isVisible = visibility
            resultSearch.root.isVisible = !visibility
        }
    }

    private fun renderStartSearchState(state: UserSearchState.StartSearchState) {
        visibilityStartSearch(true)

        startSearchAdapter.items = state.searchStartRVItems
        binding.startSearchRecycler.adapter = startSearchAdapter
        binding.startSearchRecycler.addItemDecoration(HeaderFirstItemDecoration())
    }

    companion object {
        private const val DEF_TEXT = ""
    }
}