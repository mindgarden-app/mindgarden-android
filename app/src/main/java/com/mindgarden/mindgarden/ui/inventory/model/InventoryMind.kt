package com.mindgarden.mindgarden.ui.inventory.model

import com.mindgarden.mindgarden.data.model.entity.Mind
import java.time.LocalDateTime

data class InventoryMind(
    val idx: Long? = null,
    val gardenDate: LocalDateTime,
    val date: LocalDateTime,
    val location: Int,
    var treeIdx: Int? = null, // convert DrawableRes
    var type: GardenType
) {
    companion object {
        fun Mind.convertInventoryMind() = InventoryMind(
            idx = this.idx,
            gardenDate = this.gardenDate,
            date = this.mindDate,
            location = this.location,
            treeIdx = this.location,
            type = GardenType.PLANTED)

        fun InventoryMind.convertMind() = Mind(
            idx = null,
            gardenDate = this.gardenDate,
            mindDate = this.date,
            location = this.location,
            treeIdx = this.treeIdx ?: -1
        )
    }
}