package com.mindgarden.mindgarden.ui.inventory

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.mindgarden.mindgarden.R
import com.mindgarden.mindgarden.ui.inventory.adapter.GridSpacingItemDecoration.ItemOffset
import com.mindgarden.mindgarden.ui.inventory.adapter.GardenAdapter
import com.mindgarden.mindgarden.ui.inventory.adapter.GridSpacingItemDecoration
import com.mindgarden.mindgarden.ui.inventory.adapter.InventoryAdapter
import com.mindgarden.mindgarden.ui.inventory.model.Item

class InventoryActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_inventory)
        initGardenAdapter()
        initInventoryAdapter()
    }

    private fun initInventoryAdapter() {
        val rv = findViewById<RecyclerView>(R.id.rvInventory)
        val items = mutableListOf<Item>()

        for (i in 1..11) {
            items.add(Item("ivt $i"))
        }


        rv.apply {
            addItemDecoration(
                GridSpacingItemDecoration(
                    this@InventoryActivity,
                    ItemOffset(
                        R.dimen.activity_inventory_item_space,
                        R.dimen.activity_inventory_item_space,
                        null,
                        R.dimen.activity_inventory_item_space
                    )
                )
            )
            adapter = InventoryAdapter(items)
        }
    }

    private fun initGardenAdapter() {
        val rv = findViewById<RecyclerView>(R.id.rvGarden)
        val items = mutableListOf<Item>()

        for (i in 1..36) {
            items.add(Item("$i"))
        }

        rv.apply {
            addItemDecoration(
                GridSpacingItemDecoration(
                    this@InventoryActivity,
                    ItemOffset(
                        R.dimen.activity_inventory_item_space,
                        R.dimen.activity_inventory_item_space,
                        null, null
                    )
                )
            )
            adapter = GardenAdapter(items)
        }
    }
}