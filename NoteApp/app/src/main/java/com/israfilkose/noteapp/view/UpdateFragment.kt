package com.israfilkose.noteapp.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.Observable
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.Navigation
import com.israfilkose.noteapp.R
import com.israfilkose.noteapp.databinding.FragmentUpdateBinding
import com.israfilkose.noteapp.view.model.NoteModel
import com.israfilkose.noteapp.view.viewmodel.NoteUpdateViewModel
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.channels.consumesAll


class UpdateFragment : Fragment() {
    private lateinit var dataBinding: FragmentUpdateBinding

    private lateinit var updateViewModel: NoteUpdateViewModel

    private var uuid = 0


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        dataBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_update, container, false)
        return dataBinding.root
    }

    @InternalCoroutinesApi
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        arguments?.let {
            uuid = UpdateFragmentArgs.fromBundle(it).uuid
        }


        updateViewModel=ViewModelProviders.of(this).get(NoteUpdateViewModel::class.java)
        updateViewModel.getData(uuid)
        //thats important

        dataBinding.fabDetailsUpdate.setOnClickListener {
           val titleText=dataBinding.titleTextInputUpdate.editableText.toString()
            val messageText=dataBinding.messageTextInputUpdate.editableText.toString()
        val note=NoteModel(uuid,titleText, messageText)

            updateViewModel.update(note)


            val action=UpdateFragmentDirections.actionUpdateFragmentToMainFragment()
            Navigation.findNavController(view).navigate(action)
        }

        observeLiveData()
    }


    private fun observeLiveData() {

        updateViewModel.noteLiveData.observe(viewLifecycleOwner, Observer { noteLiveData ->
            noteLiveData?.let {
                dataBinding.selectedNote = noteLiveData

            }
        })




    }


}