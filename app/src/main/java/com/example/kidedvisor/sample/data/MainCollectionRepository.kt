package com.example.kidedvisor.sample.data

import android.util.Log
import com.example.kidedvisor.R
import com.example.kidedvisor.core.roomdb.AppDatabase
import com.example.kidedvisor.core.roomdb.entity.ActivityBranchEntity
import com.example.kidedvisor.core.roomdb.entity.ActivityTypeEntity
import com.example.kidedvisor.core.roomdb.entity.ClubEntity
import java.util.UUID

class MainCollectionRepository(private val db: AppDatabase) {

    suspend fun createSamplesData() {
       createSampleActivityBranch()
        createSampleActivityType()
        createSampleClub()
    }

    private suspend fun createSampleActivityBranch() {
        val data = db.activityBranch().getAllActivityBranch()
        if (data == null) {
            listOf(
                ActivityBranchEntity(id = UUID.fromString("0c02c1c7-fa3f-496e-931b-22bee57f45ac"), name = "Искусство"),
                ActivityBranchEntity(id = UUID.fromString("59ad4177-60d0-465d-8dbf-ac814fa6d84a"), name = "Спорт"),
                ActivityBranchEntity(id = UUID.fromString("57bcb2b0-1bec-435b-b104-1691c7b9e156"), name = "Наука"),
            ).forEach {
                db.activityBranch().addActivityBranch(it)
            }
        }
    }

    private suspend fun createSampleActivityType() {
        val data = db.activityType().getAllActivityType()
        if (data == null) {
            listOf(
                ActivityTypeEntity(
                    id = UUID.fromString("8e9adfd1-a1d5-4860-afa0-383a8612f6cf"),
                    activityBranchId = UUID.fromString("0c02c1c7-fa3f-496e-931b-22bee57f45ac"),
                    name = "Вокал"
                ),
                ActivityTypeEntity(
                    id = UUID.fromString("079e9b3d-6123-429b-b21a-9d802428e903"),
                    activityBranchId = UUID.fromString("0c02c1c7-fa3f-496e-931b-22bee57f45ac"),
                    name = "Живопись"
                ),
                ActivityTypeEntity(
                    id = UUID.fromString("74157ef9-96d4-462f-98a5-0977b9c3805a"),
                    activityBranchId = UUID.fromString("59ad4177-60d0-465d-8dbf-ac814fa6d84a"),
                    name = "Футбол"
                ),
                ActivityTypeEntity(
                    id = UUID.fromString("7a69d2f3-a226-4ddb-9ee0-868268171b78"),
                    activityBranchId = UUID.fromString("59ad4177-60d0-465d-8dbf-ac814fa6d84a"),
                    name = "Единоборства"
                ),
                ActivityTypeEntity(
                    id = UUID.fromString("8775824e-c4e5-4eef-9625-1f34a66417f5"),
                    activityBranchId = UUID.fromString("57bcb2b0-1bec-435b-b104-1691c7b9e156"),
                    name = "Робототехника"
                ),
                ActivityTypeEntity(
                    id = UUID.fromString("8de020ff-3e31-4e1f-b4bc-e4686278dd1f"),
                    activityBranchId = UUID.fromString("57bcb2b0-1bec-435b-b104-1691c7b9e156"),
                    name = "Программирование"
                ),
            ).forEach {
                db.activityType().addActivityType(it)
            }
        }
    }

    private suspend fun createSampleClub() {
        val data = db.clubDao().getAllClubs()
        Log.d("MyTag", "createSampleClub: $data")
        if (data == null) {
            listOf(
                // искусство
                ClubEntity(
                    name = "Школа искусств \"Тутти\"",
                    activityType = UUID.fromString("8e9adfd1-a1d5-4860-afa0-383a8612f6cf"),
                    photo = R.drawable.art1.toString()
                ),
                ClubEntity(
                    name = "Художественная студия \"Пикассо\"",
                    activityType = UUID.fromString("0c02c1c7-fa3f-496e-931b-22bee57f45ac"),
                    photo = R.drawable.art2.toString()
                ),
                ClubEntity(
                    name = "Художественная студия \"Пикассо\"",
                    activityType = UUID.fromString("0c02c1c7-fa3f-496e-931b-22bee57f45ac"),
                    photo = R.drawable.art3.toString()
                ),
                ClubEntity(
                    name = "Художественная студия \"Пикассо\"",
                    activityType = UUID.fromString("0c02c1c7-fa3f-496e-931b-22bee57f45ac"),
                    photo = R.drawable.art4.toString()
                ),

                // спорт
                ClubEntity(
                    name = "Мир единоборств чертаново \"Пикассо\"",
                    activityType = UUID.fromString("59ad4177-60d0-465d-8dbf-ac814fa6d84a"),
                    photo = R.drawable.sport2.toString()
                ),
                ClubEntity(
                    name = "Спортивный клуб \"Farteam\"",
                    activityType = UUID.fromString("59ad4177-60d0-465d-8dbf-ac814fa6d84a"),
                    photo = R.drawable.sport4.toString()
                ),
                ClubEntity(
                    name = "Спортивная школа \"Ратмир\"",
                    activityType = UUID.fromString("59ad4177-60d0-465d-8dbf-ac814fa6d84a"),
                    photo = R.drawable.sport1.toString()
                ),
                ClubEntity(
                    name = "Спортивная школа \"Ратмир\"",
                    activityType = UUID.fromString("59ad4177-60d0-465d-8dbf-ac814fa6d84a"),
                    photo = R.drawable.sport3.toString()
                ),

                // Наука
                ClubEntity(
                    name = "ДК \"Юность\"",
                    activityType = UUID.fromString("8775824e-c4e5-4eef-9625-1f34a66417f5"),
                    photo = R.drawable.science1.toString()
                ),
                ClubEntity(
                    name = "ДК \"Ховринский\"",
                    activityType = UUID.fromString("8775824e-c4e5-4eef-9625-1f34a66417f5"),
                    photo = R.drawable.science2.toString()
                ),
                ClubEntity(
                    name = "Языковая школа ILS",
                    activityType = UUID.fromString("8775824e-c4e5-4eef-9625-1f34a66417f5"),
                    photo = R.drawable.science3.toString()
                ),
                ClubEntity(
                    name = "Языковая школа ILS",
                    activityType = UUID.fromString("8775824e-c4e5-4eef-9625-1f34a66417f5"),
                    photo = R.drawable.science4.toString()
                ),
            ).forEach {
                db.clubDao().addClub(it)
            }
        }
    }
}