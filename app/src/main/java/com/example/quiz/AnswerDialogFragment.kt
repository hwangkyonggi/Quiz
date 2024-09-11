package com.example.quiz

import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.DialogFragment
import com.google.android.material.dialog.MaterialAlertDialogBuilder

class AnswerDialogFragment : DialogFragment() {

    override fun  onCreateDialog (savedInstanceState: Bundle?): Dialog {

        val dialog = activity?.let {
            MaterialAlertDialogBuilder(it)
                .setTitle(arguments?.getString("TITLE"))
                .setMessage(arguments?.getString("MESSAGE"))
                .setPositiveButton("OK"){ _, _ ->
                    (activity as MainActivity).checkQuizCount()
                }
                .create()
        }
        return dialog ?: throw IllegalStateException("アクティビティがNullです。")
    }
}