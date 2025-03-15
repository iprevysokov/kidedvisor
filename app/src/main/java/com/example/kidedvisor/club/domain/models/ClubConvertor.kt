package com.example.kidedvisor.club.domain.models

import com.example.kidedvisor.core.roomdb.entity.ClubEntity

class ClubConvertor {

    fun mapToClub(clubEntity: ClubEntity): Club {
        return Club(
            id = clubEntity.id,
            name = clubEntity.name,
            address = clubEntity.address,
            forAge = clubEntity.forAge,
            contacts = clubEntity.contacts,
            workDays = clubEntity.workDays,
            workTime = clubEntity.workTime,
            price = clubEntity.price,
            schedule = clubEntity.schedule,
            description = clubEntity.description,
            photo = clubEntity.photo,
            isRecommend = clubEntity.isRecommend
        )
    }

}