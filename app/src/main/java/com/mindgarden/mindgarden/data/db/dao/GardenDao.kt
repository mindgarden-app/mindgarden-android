package com.mindgarden.mindgarden.data.db.dao

import androidx.room.Dao
import androidx.room.Query
import com.mindgarden.mindgarden.data.db.entity.Mind
import kotlinx.coroutines.flow.Flow

@Dao
abstract class GardenDao : BaseDao<Mind> {

    /**
     * 정원 조회
     * date: "yyyy-MM"
     */
    @Query("SELECT * from mind WHERE date LIKE '%' || :date || '%' ORDER BY location")
    abstract fun loadGarden(date: String): Flow<List<Mind>>

    /**
     * 나무 심었는 지 조회
     * date: "yyyy-MM-dd"
     */
    @Query("SELECT COUNT(idx) FROM mind WHERE date LIKE '%' || :date || '%'")
    abstract fun getMindCount(date: String): Int
}