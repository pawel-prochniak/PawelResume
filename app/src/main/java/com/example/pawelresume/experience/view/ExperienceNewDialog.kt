package com.example.pawelresume.experience.view

import android.app.DatePickerDialog
import android.app.Dialog
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.DialogFragment
import com.example.pawelresume.R
import com.example.pawelresume.experience.data.ExperienceInput
import kotlinx.android.synthetic.main.dialog_experience_new.view.*
import java.text.SimpleDateFormat
import java.util.*

class ExperienceNewDialog(
    private val createCallback: (ExperienceInput) -> Unit,
    private val dismissCallback: (() -> Unit)? = null
) : DialogFragment() {

    private val dateFormat = SimpleDateFormat("dd MMM yyyy", Locale.US)

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        super.onCreate(savedInstanceState)
        val builder = AlertDialog.Builder(requireContext())
        val view = requireActivity().layoutInflater.inflate(R.layout.dialog_experience_new, null)
        setupInputFields(view)
        builder.apply {
            setView(view)
            setPositiveButton(R.string.experience_dialog_positive_button) { _, _ ->
                val input = ExperienceInput(
                    view.position_input.text.toString(),
                    view.company_input.text.toString(),
                    dateFormat.parse(view.from_input.text.toString()),
                    dateFormat.parse(view.to_input.text.toString())
                )
                createCallback(input)
            }
            setOnDismissListener { dismissCallback?.let { it() } }
        }

        return builder.create()
    }

    private fun setupInputFields(view: View) {
        view.apply {
            fun validateInput() {
                val positiveButton = (dialog as AlertDialog).getButton(AlertDialog.BUTTON_POSITIVE)
                positiveButton.isEnabled  = position_input.text.isNotBlank()
                        && company_input.text.isNotBlank()
                        && from_input.text.isNotBlank()
                        && to_input.text.isNotBlank()
            }

            position_input.addTextChangedListener {
                validateInput()
            }

            company_input.addTextChangedListener {
                validateInput()
            }

            from_input.setOnFocusChangeListener { _, hasFocus ->
                if (hasFocus) {
                    callDatePicker { date ->
                        from_input.setText(dateFormat.format(date))
                        validateInput()
                    }
                }
            }

            to_input.setOnFocusChangeListener { _, hasFocus ->
                if (hasFocus) {
                    callDatePicker { date ->
                        to_input.setText(dateFormat.format(date))
                        validateInput()
                    }
                }
            }

            to_input.setText(dateFormat.format(Calendar.getInstance().time))
        }

    }

    private fun callDatePicker(pickedDateCallback: (Date) -> Unit) {
            DatePickerDialog(requireContext(), 0, { _, year, month, dayOfMonth ->
                val calendar = Calendar.getInstance()
                calendar.set(year, month, dayOfMonth)
                pickedDateCallback(calendar.time)
            }, 2010, 1, 1)
                .show()
    }

}