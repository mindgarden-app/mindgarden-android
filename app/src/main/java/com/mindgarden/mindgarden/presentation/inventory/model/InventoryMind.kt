package com.mindgarden.mindgarden.presentation.inventory.model

import com.mindgarden.mindgarden.data.db.entity.Mind
import com.mindgarden.mindgarden.util.ext.now
import java.time.LocalDateTime

data class InventoryMind(
    val location: Int,
    var tree: Tree? = null, // convert DrawableRes
    val date: LocalDateTime,
    var type: GardenType
) {
    companion object {
        fun from(location: Int, type: GardenType) = InventoryMind(
            location = location,
            date = now(),
            type = type
        )

        fun Mind.convertInventoryMind() = InventoryMind(
            location = this.location,
            tree = this.tree,
            date = date,
            type = GardenType.PLANTED
        )

        fun InventoryMind.convertMind() = Mind(
            date = date,
            location = this.location,
            tree = this.tree!!
        )
    }
}