package com.mindgarden.mindgarden.ui.inventory.model

import com.mindgarden.mindgarden.data.db.entity.Mind
import java.time.LocalDateTime

data class InventoryMind(
    val idx: Long? = null,
    val date: LocalDateTime,
    val location: Int,
    var treeIdx: Int? = null, // convert DrawableRes
    var type: GardenType
) {
    companion object {
        fun Mind.convertInventoryMind() = InventoryMind(
            idx = this.idx,
            date = this.date,
            location = this.location,
            treeIdx = this.location,
            type = GardenType.PLANTED)

        fun InventoryMind.convertMind() = Mind(
            idx = this.idx ?: 0,
            date = this.date,
            location = this.location,
            treeIdx = this.treeIdx ?: -1
        )
    }
}