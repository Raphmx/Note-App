package com.israfilkose.noteapp.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.Navigation
import com.israfilkose.noteapp.R
import com.israfilkose.noteapp.databinding.FragmentDetailsBinding
import com.israfilkose.noteapp.view.model.NoteModel
import com.israfilkose.noteapp.view.viewmodel.NoteDetailsViewModel
import kotlinx.coroutines.InternalCoroutinesApi


class DetailsFragment : Fragment() {

    private lateinit var dataBinding: FragmentDetailsBinding

    private lateinit var viewModel: NoteDetailsViewModel

    private var uuid = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        dataBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_details, container, false)
        return dataBinding.root

    }

    @InternalCoroutinesApi
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)






        viewModel = ViewModelProviders.of(this).get(NoteDetailsViewModel::class.java)


        dataBinding.fabDetails.setOnClickListener {
            buttonInsert()

            val titleText=dataBinding.titleTextInput.editableText.toString()
            val messageText=dataBinding.messageTextInput.editableText.toString()
            val note=NoteModel(0,titleText,messageText)
            viewModel.insertNote(note)







            val action=DetailsFragmentDirections.actionDetailsFragmentToMainFragment()
            Navigation.findNavController(view).navigate(action)
        }



    }





    private fun buttonInsert() {

        viewModel.noteLiveData.observe(viewLifecycleOwner,Observer{noteInsert->
            noteInsert?.let {

                dataBinding.selectedNote=noteInsert


            }
        })


    }



}






