package com.mindgarden.mindgarden.presentation.inventory

import android.graphics.Rect
import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.RecyclerView
import com.mindgarden.mindgarden.R
import com.mindgarden.mindgarden.databinding.FragmentInventoryBinding
import com.mindgarden.mindgarden.presentation.inventory.adapter.garden.GardenAdapter
import com.mindgarden.mindgarden.presentation.inventory.adapter.tree.TreeAdapter
import com.mindgarden.mindgarden.presentation.util.common.navigation.NavigationFragment
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class InventoryFragment :
    NavigationFragment<InventoryViewModel, FragmentInventoryBinding>(R.layout.fragment_inventory) {
    private val args: InventoryFragmentArgs by navArgs()

    @Inject
    lateinit var inventoryViewModelFactory: InventoryViewModel.AssistedFactory

    override val viewModel: InventoryViewModel by viewModels {
        InventoryViewModel.provideFactory(
            inventoryViewModelFactory,
            args.date
        )
    }

    override fun setViewModel() {
        binding.vm = viewModel
    }

    private val gardenAdapter by lazy {
        GardenAdapter(
            if (args.isEmptyGarden) 0 else -1,
            {
                viewModel.clickGarden(it)
            }, {
                viewModel.removeTree(it)
            })
    }

    private val treeAdapter by lazy {
        TreeAdapter {
            viewModel.clickTree(it)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.loadGarden()

        binding.rvGarden.apply {
            adapter = gardenAdapter
            addItemDecoration(GardenItemSpace())
        }

        binding.rvTree.apply {
            adapter = treeAdapter
            addItemDecoration(TreeItemSpace())
        }
    }

    inner class GardenItemSpace : RecyclerView.ItemDecoration() {
        override fun getItemOffsets(
            outRect: Rect,
            view: View,
            parent: RecyclerView,
            state: RecyclerView.State
        ) {
            context?.let {
                outRect.right =
                    it.resources.getDimensionPixelSize(R.dimen.fragment_inventory_garden_item_space)
                outRect.top =
                    it.resources.getDimensionPixelSize(R.dimen.fragment_inventory_garden_item_space)
            }
        }
    }

    inner class TreeItemSpace : RecyclerView.ItemDecoration() {
        override fun getItemOffsets(
            outRect: Rect,
            view: View,
            parent: RecyclerView,
            state: RecyclerView.State
        ) {
            context?.let {
                outRect.right =
                    it.resources.getDimensionPixelSize(R.dimen.fragment_inventory_tree_item_right_space)
                outRect.bottom =
                    it.resources.getDimensionPixelSize(R.dimen.fragment_inventory_tree_item_bottom_space)
                outRect.top =
                    it.resources.getDimensionPixelSize(R.dimen.fragment_inventory_tree_item_top_space)
            }
        }
    }

}
