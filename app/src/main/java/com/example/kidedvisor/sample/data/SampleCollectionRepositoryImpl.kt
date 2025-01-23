package com.example.kidedvisor.sample.data

import com.example.kidedvisor.R
import com.example.kidedvisor.core.roomdb.AppDatabase
import com.example.kidedvisor.core.roomdb.entity.ActivityBranchClubRelationEntity
import com.example.kidedvisor.core.roomdb.entity.ActivityBranchEntity
import com.example.kidedvisor.core.roomdb.entity.ActivityTypeClubRelationEntity
import com.example.kidedvisor.core.roomdb.entity.ActivityTypeEntity
import com.example.kidedvisor.core.roomdb.entity.ClubEntity
import com.example.kidedvisor.sample.domain.api.SampleCollectionRepository
import java.util.UUID

class SampleCollectionRepositoryImpl(
    private val db: AppDatabase
) : SampleCollectionRepository {

    override suspend fun createSamplesData() {
        if (!isExistLocalData()) {
            createSampleActivityBranch()
            createSampleActivityType()
            createRelationBranch()
            createRelationType()
            createSampleClub()
        }
    }

    private suspend fun isExistLocalData(): Boolean {
        val data = db.activityBranchDao().getAllActivityBranch()
        return data.isNotEmpty()
    }

    private suspend fun createSampleActivityBranch() {
        listOf(
            ActivityBranchEntity(
                id = UUID.fromString("0c02c1c7-fa3f-496e-931b-22bee57f45ac"),
                name = "Искусство"
            ),
            ActivityBranchEntity(
                id = UUID.fromString("59ad4177-60d0-465d-8dbf-ac814fa6d84a"),
                name = "Спорт"
            ),
            ActivityBranchEntity(
                id = UUID.fromString("57bcb2b0-1bec-435b-b104-1691c7b9e156"),
                name = "Наука"
            ),
        ).forEach {
            db.activityBranchDao().addActivityBranch(it)
        }
    }

    private suspend fun createSampleActivityType() {
        listOf(
            ActivityTypeEntity(
                id = UUID.fromString("8e9adfd1-a1d5-4860-afa0-383a8612f6cf"),
                name = "Вокал"
            ),
            ActivityTypeEntity(
                id = UUID.fromString("079e9b3d-6123-429b-b21a-9d802428e903"),
                name = "Живопись"
            ),
            ActivityTypeEntity(
                id = UUID.fromString("74157ef9-96d4-462f-98a5-0977b9c3805a"),
                name = "Футбол"
            ),
            ActivityTypeEntity(
                id = UUID.fromString("7a69d2f3-a226-4ddb-9ee0-868268171b78"),
                name = "Единоборства"
            ),
            ActivityTypeEntity(
                id = UUID.fromString("8775824e-c4e5-4eef-9625-1f34a66417f5"),
                name = "Робототехника"
            ),
            ActivityTypeEntity(
                id = UUID.fromString("8de020ff-3e31-4e1f-b4bc-e4686278dd1f"),
                name = "Программирование"
            ),
        ).forEach {
            db.activityTypeDao().addActivityType(it)
        }

    }

    private suspend fun createSampleClub() {
        listOf(
            // искусство
            ClubEntity(
                id = UUID.fromString("8C0A9050-F44A-4766-8E48-FAEA54369708"),
                name = "Школа искусств \"Тутти\"",
                photo = R.drawable.art1.toString()
            ),
            ClubEntity(
                id = UUID.fromString("E18BA298-B80D-46BE-9D72-CEA20176DA59"),
                name = "Художественная студия \"Пикассо\"",
                photo = R.drawable.art2.toString()
            ),
            ClubEntity(
                id = UUID.fromString("73245E2F-0152-427A-AD1A-1B034595EC52"),
                name = "Художественная студия \"Пикассо\"",
                photo = R.drawable.art3.toString(),
                isRecommend = true
            ),
            ClubEntity(
                id = UUID.fromString("D29D68E2-27A6-4888-9247-784E68BF1E13"),
                name = "Художественная студия \"Пикассо\"",
                photo = R.drawable.art4.toString()
            ),

            // спорт
            ClubEntity(
                id = UUID.fromString("8A7565BD-F1C6-4830-90FD-9C3F0360CE4E"),
                name = "Мир единоборств чертаново \"Пикассо\"",
                photo = R.drawable.sport2.toString()
            ),
            ClubEntity(
                id = UUID.fromString("A0DF9F8E-A036-4FBA-97F7-D1D6D72EE966"),
                name = "Спортивный клуб \"Farteam\"",
                photo = R.drawable.sport4.toString()
            ),
            ClubEntity(
                id = UUID.fromString("8BAA30F4-8C5A-42C9-9AE0-1AEBFCB02A41"),
                name = "Спортивная школа \"Ратмир\"",
                photo = R.drawable.sport1.toString()
            ),
            ClubEntity(
                id = UUID.fromString("725A01FF-3715-451A-BC9A-789DB20D9351"),
                name = "Спортивная школа \"Ратмир\"",
                photo = R.drawable.sport3.toString()
            ),

            // Наука
            ClubEntity(
                id = UUID.fromString("01B64A92-D897-4D6B-8765-F512FBC5DF99"),
                name = "ДК \"Юность\"",
                photo = R.drawable.science1.toString(),
                isRecommend = true
            ),
            ClubEntity(
                id = UUID.fromString("3CDE3827-8969-47EF-8C40-6AF938694DD5"),
                name = "ДК \"Ховринский\"",
                photo = R.drawable.science2.toString()
            ),
            ClubEntity(
                id = UUID.fromString("08D2D968-F86A-4D91-8240-D52A97B17BC8"),
                name = "Языковая школа ILS",
                photo = R.drawable.science3.toString()
            ),
            ClubEntity(
                id = UUID.fromString("9E1C2630-DCB4-40F4-97CA-F14DA151D997"),
                name = "Языковая школа ILS",
                photo = R.drawable.science4.toString()
            ),
        ).forEach {
            db.clubDao().addClub(it)
        }
    }

    private suspend fun createRelationBranch() {
        listOf(
            // искусство
            ActivityBranchClubRelationEntity(
                branchId = UUID.fromString("0c02c1c7-fa3f-496e-931b-22bee57f45ac"),
                clubId = UUID.fromString("8C0A9050-F44A-4766-8E48-FAEA54369708")
            ),
            ActivityBranchClubRelationEntity(
                branchId = UUID.fromString("0c02c1c7-fa3f-496e-931b-22bee57f45ac"),
                clubId = UUID.fromString("E18BA298-B80D-46BE-9D72-CEA20176DA59")
            ),
            ActivityBranchClubRelationEntity(
                branchId = UUID.fromString("0c02c1c7-fa3f-496e-931b-22bee57f45ac"),
                clubId = UUID.fromString("73245E2F-0152-427A-AD1A-1B034595EC52")
            ),
            ActivityBranchClubRelationEntity(
                branchId = UUID.fromString("0c02c1c7-fa3f-496e-931b-22bee57f45ac"),
                clubId = UUID.fromString("D29D68E2-27A6-4888-9247-784E68BF1E13")
            ),
            // спорт
            ActivityBranchClubRelationEntity(
                branchId = UUID.fromString("59ad4177-60d0-465d-8dbf-ac814fa6d84a"),
                clubId = UUID.fromString("8A7565BD-F1C6-4830-90FD-9C3F0360CE4E")
            ),
            ActivityBranchClubRelationEntity(
                branchId = UUID.fromString("59ad4177-60d0-465d-8dbf-ac814fa6d84a"),
                clubId = UUID.fromString("A0DF9F8E-A036-4FBA-97F7-D1D6D72EE966")
            ),
            ActivityBranchClubRelationEntity(
                branchId = UUID.fromString("59ad4177-60d0-465d-8dbf-ac814fa6d84a"),
                clubId = UUID.fromString("8BAA30F4-8C5A-42C9-9AE0-1AEBFCB02A41")
            ),
            ActivityBranchClubRelationEntity(
                branchId = UUID.fromString("59ad4177-60d0-465d-8dbf-ac814fa6d84a"),
                clubId = UUID.fromString("725A01FF-3715-451A-BC9A-789DB20D9351")
            ),

            // наука
            ActivityBranchClubRelationEntity(
                branchId = UUID.fromString("57bcb2b0-1bec-435b-b104-1691c7b9e156"),
                clubId = UUID.fromString("01B64A92-D897-4D6B-8765-F512FBC5DF99")
            ),
            ActivityBranchClubRelationEntity(
                branchId = UUID.fromString("57bcb2b0-1bec-435b-b104-1691c7b9e156"),
                clubId = UUID.fromString("3CDE3827-8969-47EF-8C40-6AF938694DD5")
            ),
            ActivityBranchClubRelationEntity(
                branchId = UUID.fromString("57bcb2b0-1bec-435b-b104-1691c7b9e156"),
                clubId = UUID.fromString("08D2D968-F86A-4D91-8240-D52A97B17BC8")
            ),
            ActivityBranchClubRelationEntity(
                branchId = UUID.fromString("57bcb2b0-1bec-435b-b104-1691c7b9e156"),
                clubId = UUID.fromString("9E1C2630-DCB4-40F4-97CA-F14DA151D997")
            ),
        ).forEach {
            db.activityBranchClubRelationDao().addActivityBranchClubRelation(it)
        }
    }

    private suspend fun createRelationType() {
        listOf(
            // искусство
            ActivityTypeClubRelationEntity(
                typeId = UUID.fromString("8e9adfd1-a1d5-4860-afa0-383a8612f6cf"),
                clubId = UUID.fromString("8C0A9050-F44A-4766-8E48-FAEA54369708")
            ),
            ActivityTypeClubRelationEntity(
                typeId = UUID.fromString("079e9b3d-6123-429b-b21a-9d802428e903"),
                clubId = UUID.fromString("E18BA298-B80D-46BE-9D72-CEA20176DA59")
            ),
            ActivityTypeClubRelationEntity(
                typeId = UUID.fromString("079e9b3d-6123-429b-b21a-9d802428e903"),
                clubId = UUID.fromString("73245E2F-0152-427A-AD1A-1B034595EC52")
            ),
            ActivityTypeClubRelationEntity(
                typeId = UUID.fromString("079e9b3d-6123-429b-b21a-9d802428e903"),
                clubId = UUID.fromString("D29D68E2-27A6-4888-9247-784E68BF1E13")
            ),
            // спорт
            ActivityTypeClubRelationEntity(
                typeId = UUID.fromString("7a69d2f3-a226-4ddb-9ee0-868268171b78"),
                clubId = UUID.fromString("8A7565BD-F1C6-4830-90FD-9C3F0360CE4E")
            ),
            ActivityTypeClubRelationEntity(
                typeId = UUID.fromString("74157ef9-96d4-462f-98a5-0977b9c3805a"),
                clubId = UUID.fromString("A0DF9F8E-A036-4FBA-97F7-D1D6D72EE966")
            ),
            ActivityTypeClubRelationEntity(
                typeId = UUID.fromString("7a69d2f3-a226-4ddb-9ee0-868268171b78"),
                clubId = UUID.fromString("8BAA30F4-8C5A-42C9-9AE0-1AEBFCB02A41")
            ),
            ActivityTypeClubRelationEntity(
                typeId = UUID.fromString("7a69d2f3-a226-4ddb-9ee0-868268171b78"),
                clubId = UUID.fromString("725A01FF-3715-451A-BC9A-789DB20D9351")
            ),

            // наука
            ActivityTypeClubRelationEntity(
                typeId = UUID.fromString("8775824e-c4e5-4eef-9625-1f34a66417f5"),
                clubId = UUID.fromString("01B64A92-D897-4D6B-8765-F512FBC5DF99")
            ),
            ActivityTypeClubRelationEntity(
                typeId = UUID.fromString("8775824e-c4e5-4eef-9625-1f34a66417f5"),
                clubId = UUID.fromString("3CDE3827-8969-47EF-8C40-6AF938694DD5")
            ),
            ActivityTypeClubRelationEntity(
                typeId = UUID.fromString("8de020ff-3e31-4e1f-b4bc-e4686278dd1f"),
                clubId = UUID.fromString("08D2D968-F86A-4D91-8240-D52A97B17BC8")
            ),
            ActivityTypeClubRelationEntity(
                typeId = UUID.fromString("8de020ff-3e31-4e1f-b4bc-e4686278dd1f"),
                clubId = UUID.fromString("9E1C2630-DCB4-40F4-97CA-F14DA151D997")
            ),
        ).forEach {
            db.activityTypeClubRelationDao().addActivityTypeClubRelation(it)
        }
    }
}