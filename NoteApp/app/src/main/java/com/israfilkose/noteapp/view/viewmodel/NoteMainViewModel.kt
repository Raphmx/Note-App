package com.israfilkose.noteapp.view.viewmodel

import android.app.Application
import androidx.lifecycle.MutableLiveData
import com.israfilkose.noteapp.view.model.NoteDatabase
import com.israfilkose.noteapp.view.model.NoteModel
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.launch

class  NoteMainViewModel(application: Application):BaseViewModel(application) {


     val notes =MutableLiveData<List<NoteModel>>()



    @InternalCoroutinesApi
    fun newData(){
        getDataFromSql()
    }
    @InternalCoroutinesApi
    private fun getDataFromSql(){
        launch {
            val notes=NoteDatabase.getDatabase(getApplication()).noteDao().getAllData()
            showNotes(notes)
        }
    }



    fun deleteNote(noteDelete:NoteModel){


        if (notes.value!=null){
            val arrayList=ArrayList(notes.value)
            arrayList.remove(noteDelete)
            notes.value=arrayList
           // showNotes(arrayList)

        }

    }
    private fun showNotes(noteList:List<NoteModel>){
        notes.value=noteList
    }


    @InternalCoroutinesApi
    fun deleteNoteDao(deleteNotes:NoteModel){
        launch {
            val noteDelete=NoteDatabase.getDatabase(getApplication()).noteDao()
            noteDelete.deleteNote(deleteNotes)
        }
    }






}

