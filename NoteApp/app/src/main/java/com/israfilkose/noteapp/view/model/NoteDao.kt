package com.israfilkose.noteapp.view.model

import androidx.room.*

@Dao
interface NoteDao {

    @Insert (onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(notes: NoteModel)
//:Long

    @Query("SELECT * FROM note_table ORDER BY id DESC")
    suspend fun getAllData(): List<NoteModel>

    @Query("SELECT * FROM note_table WHERE id =:getNoteId")
    suspend fun getNote(getNoteId:Int):NoteModel

   @Delete
    suspend fun deleteNote(note:NoteModel)

    @Update
    suspend fun update(updateNote:NoteModel)

}