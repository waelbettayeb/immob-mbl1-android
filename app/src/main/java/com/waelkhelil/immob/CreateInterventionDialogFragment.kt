package com.waelkhelil.immob

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Spinner
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.ViewModelProviders
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import com.waelkhelil.immob.model.Intervention


class CreateInterventionDialogFragment : DialogFragment() {

    companion object {
        const val TAG = "CreateInterventionDialogFragment"
    }
    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Share data between fragments
        viewModel = activity?.run {
            ViewModelProviders.of(this).get(MainViewModel::class.java)
        } ?: throw Exception("Invalid Activity")
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_add_post, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val toolbar = view.findViewById<Toolbar>(R.id.toolbar_new_listing)
        toolbar.setNavigationIcon(R.drawable.ic_close_white_24dp)
        toolbar.setNavigationOnClickListener{
            dismissAllowingStateLoss()
        }
        toolbar.title = resources.getString(R.string.add_intervention)

        val spinner: Spinner = view.findViewById(R.id.spinner_type)
        ArrayAdapter.createFromResource(
            context,
            R.array.intervention_type,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spinner.adapter = adapter
        }
        // Title Input
        val lTextInputLayout = view.findViewById<TextInputLayout>(R.id.text_input_layout_number)
        val lNumberEditText = view.findViewById<TextInputEditText>(R.id.text_input_number)
        val buttonSaveButton = view.findViewById<Button>(R.id.button_save_intervention)

            buttonSaveButton?.setOnClickListener {
            val number = lNumberEditText.text.toString().toInt()
            val type = view.findViewById<Spinner>(R.id.spinner_type).selectedItem.toString()
            val lPlumberName = view.findViewById<TextInputEditText>(R.id.text_input_plumber_name).text.toString()
            val lInterventionType = if(type != "type01") Intervention.InterventionType.TYPE02
                    else Intervention.InterventionType.TYPE01

            if(lNumberEditText.text.toString() == ""){
                lTextInputLayout.error = resources.getString(R.string.msg_please_add_a_number)
            }else{

                viewModel.addIntervention(Intervention(
                    number,
                    lInterventionType,
                    lPlumberName
                ))
                lTextInputLayout.error = null
                dismissAllowingStateLoss()
            }
        }

    }
    override fun onStart() {
        super.onStart()
        val dialog = dialog
        if (dialog != null) {
            val width = ViewGroup.LayoutParams.MATCH_PARENT
            val height = ViewGroup.LayoutParams.MATCH_PARENT
            dialog.window.setLayout(width, height)
        }
    }
}
