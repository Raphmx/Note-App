package com.israfilkose.noteapp.view.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "note_table")
data class NoteModel(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val noteId:Int=0,

    @ColumnInfo(name="title")
    val noteTitle:String?,

    @ColumnInfo(name="message")
    val noteMessage:String?


)
