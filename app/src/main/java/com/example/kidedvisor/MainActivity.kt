package com.example.kidedvisor

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.room.Room
import com.example.kidedvisor.core.roomdb.AppDatabase
import com.example.kidedvisor.sample.data.MainCollectionRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val db = Room.databaseBuilder(this, AppDatabase::class.java, "KidedvisorDatabase").build()
        val repository = MainCollectionRepository(db)

        lifecycleScope.launch(Dispatchers.IO) {
            repository.createSamplesData()
        }
    }
}