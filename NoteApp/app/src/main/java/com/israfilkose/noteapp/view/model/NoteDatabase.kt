package com.israfilkose.noteapp.view.model

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.internal.synchronized


@Database(entities = [NoteModel::class], version = 1)
abstract class NoteDatabase : RoomDatabase() {

    abstract fun noteDao() : NoteDao

    companion object {

        @Volatile
        private var INSTANCE: NoteDatabase? = null

        @InternalCoroutinesApi
        fun getDatabase(context: Context):NoteDatabase{
            val tempInstance= INSTANCE
            if(tempInstance!=null){
                return tempInstance
            }
            synchronized(this){
                val instance= Room.databaseBuilder(
                    context.applicationContext,NoteDatabase::class.java,"notesdatabase"
                ).build()
                INSTANCE=instance
                return instance
            }
        }


        /*
        private val lock = Any()


        @InternalCoroutinesApi
        operator fun invoke(context: Context) = instance ?: synchronized(lock){
                instance?: makeDatabase(context).also {
                    instance=it
                }
        }

        private fun makeDatabase(context: Context)=Room.databaseBuilder(
            context.applicationContext,NoteDatabase::class.java,"notedatabase"
        ).build()


         */

    }
}