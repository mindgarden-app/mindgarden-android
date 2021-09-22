package com.mindgarden.mindgarden.ui.inventory.model

import com.mindgarden.mindgarden.data.model.entity.Mind
import java.time.LocalDateTime

data class InventoryMind(
    val idx: Long? = null,
    val gardenDate: LocalDateTime,
    val date: LocalDateTime,
    val location: Int,
    val treeIdx: Int? = null,
    var type: GardenType
) {
    companion object {
        fun Mind.convertInventoryMind() = InventoryMind(
            idx = this.idx,
            gardenDate = this.gardenDate,
            date = this.mindDate,
            location = this.location,
            treeIdx = this.location,
            type = GardenType.EMPTY)
    }
}