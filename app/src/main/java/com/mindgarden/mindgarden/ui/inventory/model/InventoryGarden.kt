package com.mindgarden.mindgarden.ui.inventory.model

data class InventoryGarden(
    val location: Int,
    val treeIdx: Int,
    var type: GardenType
)