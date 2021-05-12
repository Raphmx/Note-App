package com.israfilkose.noteapp.view.viewmodel

import android.app.Application
import androidx.lifecycle.MutableLiveData
import com.israfilkose.noteapp.view.model.NoteDatabase
import com.israfilkose.noteapp.view.model.NoteModel
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.launch

class NoteDetailsViewModel(application: Application) : BaseViewModel(application) {

    val noteLiveData = MutableLiveData<NoteModel>()

    //  var noteTitleLiveData : String?=null
    // var noteMessageLiveData :String?=null
  // val noteInsertData = MutableLiveData<NoteModel>()

   // val noteUpdateData=MutableLiveData<NoteModel>()
    // private val noteModel = NoteModel(0,noteTitleLiveData,noteMessageLiveData)


    @InternalCoroutinesApi
    fun insertNote(noteModels: NoteModel) {

        launch {
            val dao = NoteDatabase.getDatabase(getApplication()).noteDao()
      dao.insert(noteModels)

            //noteInsertData.postValue()

            //val inserto = dao.insert(noteModell)

            // noteModel.noteId = inserto.toInt()
            //noteInsertData.value =inserto.toInt()


        }
    }




}