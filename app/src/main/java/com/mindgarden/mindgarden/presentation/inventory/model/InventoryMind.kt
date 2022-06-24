package com.mindgarden.mindgarden.presentation.inventory.model

import com.mindgarden.mindgarden.data.db.entity.Mind
import com.mindgarden.mindgarden.util.ext.now

data class InventoryMind(
    val location: Int,
    var tree: Tree? = null, // convert DrawableRes
    var type: GardenType
) {
    companion object {
        fun from(location: Int, type: GardenType) = InventoryMind(
            location = location,
            type = type
        )

        fun Mind.convertInventoryMind() = InventoryMind(
            location = this.location,
            tree = this.tree,
            type = GardenType.PLANTED
        )

        fun InventoryMind.convertMind() = Mind(
            date = now(),
            location = this.location,
            tree = this.tree!!
        )
    }
}