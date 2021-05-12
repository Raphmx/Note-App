package com.israfilkose.noteapp.view


import android.content.Context
import android.graphics.Canvas
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.Navigation
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import com.israfilkose.noteapp.R
import com.israfilkose.noteapp.databinding.FragmentMainBinding
import com.israfilkose.noteapp.view.adapter.NoteAdapter
import com.israfilkose.noteapp.view.viewmodel.NoteMainViewModel
import it.xabaras.android.recyclerview.swipedecorator.RecyclerViewSwipeDecorator
import kotlinx.coroutines.InternalCoroutinesApi



class MainFragment : Fragment() {

    private lateinit var dataBinding: FragmentMainBinding
    private lateinit var viewModel: NoteMainViewModel



    private var noteAdapter =NoteAdapter(arrayListOf())

    @InternalCoroutinesApi
    private val swipeToDelete=object :ItemTouchHelper.SimpleCallback(0,ItemTouchHelper.LEFT){
        override fun onMove(
            recyclerView: RecyclerView,
            viewHolder: RecyclerView.ViewHolder,
            target: RecyclerView.ViewHolder
        ): Boolean {
           return true
        }

        override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
            val layoutPosition=viewHolder.layoutPosition

            when(direction){
                ItemTouchHelper.LEFT->{
                    val selectedNote= noteAdapter.noteList[layoutPosition]
                    viewModel.deleteNote(selectedNote)
                    viewModel.deleteNoteDao(selectedNote)
                    noteAdapter.notifyDataSetChanged()
                    
                    Snackbar.make(dataBinding.rv,"${selectedNote.noteTitle} is deleted ",Snackbar.LENGTH_LONG   ).setAction("Undo",View.OnClickListener {
                        noteAdapter.noteList.add(layoutPosition,selectedNote)
                        noteAdapter.notifyItemInserted(layoutPosition)
                    }).show()
                }
            }

        }


    }




     override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        dataBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_main, container, false)
        return dataBinding.root

    }

    @InternalCoroutinesApi
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)



        viewModel = ViewModelProviders.of(this).get(NoteMainViewModel::class.java)

        viewModel.newData()
        //adapter
      //  val touchHelper=ItemTouchHelper(swipeToDelete)
       // touchHelper.attachToRecyclerView(dataBinding.rv)


        dataBinding.rv.layoutManager = LinearLayoutManager(context)
        ItemTouchHelper(swipeToDelete).attachToRecyclerView(dataBinding.rv)
        dataBinding.rv.adapter = noteAdapter

        noteAdapter.notifyDataSetChanged()





        dataBinding.floatingActionButton.setOnClickListener {
            val action = MainFragmentDirections.actionMainFragmentToDetailsFragment()
            Navigation.findNavController(view).navigate(action)
        }
        observeLiveData()

    }

    private fun observeLiveData() {
        viewModel.notes.observe(viewLifecycleOwner, Observer { notes ->
            notes?.let { // bos degilse

                    noteAdapter.updateNoteList(notes)


            }
        })
    }


}