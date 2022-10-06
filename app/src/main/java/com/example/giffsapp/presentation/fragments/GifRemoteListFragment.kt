package com.example.giffsapp.presentation.fragments

import android.os.Bundle
import android.view.*
import android.widget.PopupMenu
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.giffsapp.GifApplication
import com.example.giffsapp.R
import com.example.giffsapp.data.local.entities.SimpleGif
import com.example.giffsapp.presentation.adapters.GifCallback
import com.example.giffsapp.presentation.adapters.GifsAdapter
import com.example.giffsapp.presentation.viewmodels.GifViewModel
import javax.inject.Inject

class GifRemoteListFragment : Fragment(), GifCallback {

    private lateinit var recyclerView: RecyclerView

    @Inject
    lateinit var viewModel: GifViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
        (requireContext().applicationContext as GifApplication).appComponent.inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_gif_remotelist, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        requireActivity().title = getString(R.string.remote)
        recyclerView = view.findViewById(R.id.recyclerView)
        recyclerView.layoutManager = GridLayoutManager(requireContext(), 3)
        updateList(viewModel.localLiveData.value!!)

        viewModel.remoteLiveData.observe(requireActivity(), {
            updateList(it)
        })
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.toolbar_search, menu)
        val searchItem = menu.findItem(R.id.actionSearch)
        val searchView = searchItem?.actionView as SearchView
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                val newList = viewModel.localLiveData.value!!
                    .filter { it.name.contains(newText!!) }
                updateList(newList)
                return false
            }
        })
        return super.onCreateOptionsMenu(menu, inflater)

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.menuUpdate -> {
                requireActivity().title = getString(R.string.remote)
                updateList(viewModel.remoteLiveData.value!!)
            }
            R.id.menuLocalData -> {
                requireActivity().title = getString(R.string.local)
                updateList(viewModel.getLocalData())
            }
        }
        return true
    }

    override fun onClick(list: List<SimpleGif>, id: Int) {
        val gifFullscreenFragment = GifFullscreenFragment()
        val bundle = Bundle()
        bundle.putInt("id", id)
        bundle.putString("type", requireActivity().title.toString())
        gifFullscreenFragment.arguments = bundle
        parentFragmentManager.beginTransaction()
            .add(R.id.fragmentContainerView, gifFullscreenFragment)
            .commit()
    }

    override fun onLongClick(view: View, list: List<SimpleGif>, id: Int): Boolean {
        val popupMenu = PopupMenu(
            requireContext(),
            view
        )
        if (requireActivity().title == getString(R.string.local)) {
            popupMenu.inflate(R.menu.delete_menu)
            popupMenu.setOnMenuItemClickListener {
                viewModel.deleteData(list[id])
                val newList = viewModel.localLiveData.value
                updateList(newList!!)
                return@setOnMenuItemClickListener true
            }
            popupMenu.show()
        }
        return true
    }

    private fun updateList(list: List<SimpleGif>) {
        recyclerView.adapter = GifsAdapter(list, requireContext(), this)
    }
}