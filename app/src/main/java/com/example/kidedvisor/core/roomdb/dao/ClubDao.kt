package com.example.kidedvisor.core.roomdb.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.kidedvisor.core.roomdb.entity.ClubEntity
import kotlinx.coroutines.flow.Flow
import java.util.UUID

@Dao
interface ClubDao {
    @Insert
    suspend fun addClub(clubEntity: ClubEntity)

    @Query("SELECT * FROM $TABLE_NAME")
    suspend fun getAllClubs(): List<ClubEntity>

    @Query("SELECT * FROM $TABLE_NAME WHERE id IN (:ids)")
    suspend fun getClubsById(ids: List<UUID>): List<ClubEntity>

    companion object {
        const val TABLE_NAME = "Club"
    }
}