package com.israfilkose.noteapp.view.viewmodel

import android.app.Application
import androidx.lifecycle.MutableLiveData
import com.israfilkose.noteapp.view.model.NoteDatabase
import com.israfilkose.noteapp.view.model.NoteModel
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.launch

class NoteUpdateViewModel (application: Application):BaseViewModel(application){


    val noteUpdateData = MutableLiveData<NoteModel>()
    val noteLiveData = MutableLiveData<NoteModel>()


    @InternalCoroutinesApi
        fun update(updateNote: NoteModel){
        launch {
            val dao= NoteDatabase.getDatabase(getApplication()).noteDao()
            dao.update(updateNote)



        }
    }





    @InternalCoroutinesApi
    fun getData(uuid: Int) {
        launch {
            val dao = NoteDatabase.getDatabase(getApplication()).noteDao()
            val note = dao.getNote(uuid)
            noteLiveData.value = note


        }
    }

}