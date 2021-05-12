package com.israfilkose.noteapp.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import com.israfilkose.noteapp.R
import com.israfilkose.noteapp.databinding.ItemNoteCardviewBinding
import com.israfilkose.noteapp.view.MainFragmentDirections
import com.israfilkose.noteapp.view.UpdateFragmentDirections
import com.israfilkose.noteapp.view.model.NoteModel
import kotlinx.android.synthetic.main.item_note_cardview.view.*


class NoteAdapter( val noteList:ArrayList<NoteModel>) :
    RecyclerView.Adapter<NoteAdapter.NoteViewHolder>(), NoteListener {

    class NoteViewHolder(val view: ItemNoteCardviewBinding) : RecyclerView.ViewHolder(view.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        val inflater = LayoutInflater.from(parent.context)

        val binding = DataBindingUtil.inflate<ItemNoteCardviewBinding>(
            inflater,
            R.layout.item_note_cardview,
            parent,
            false
        )
        return NoteViewHolder(binding)
    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        holder.view.notemodel = noteList[position]
        holder.view.listener = this
    }

    override fun getItemCount(): Int {
        return noteList.size
    }





    fun updateNoteList(newNoteList: List<NoteModel>) {
        noteList.clear()
        noteList.addAll(newNoteList)
        notifyDataSetChanged()
    }




    override fun noteClickListened(view: View) {
        val uuid=view.note_uuid_text.text.toString().toInt()
        val action = MainFragmentDirections.actionMainFragmentToUpdateFragment(uuid)
        Navigation.findNavController(view).navigate(action)
    }
/*
    override fun noteClickListened(v: View) {

        // val uuid = v.note_uuid_text.text.toString().toInt()

    }


 */



}