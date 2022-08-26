package com.craft404.sainyojit.ui.dialog

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.lifecycleScope
import com.craft404.sainyojit.R
import com.craft404.sainyojit.databinding.DialogTicketBinding
import com.craft404.sainyojit.repository.entity.TicketEntity
import com.craft404.sainyojit.util.AppObjectController
import com.craft404.sainyojit.util.getDateString
import com.craft404.sainyojit.util.showToast
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import kotlinx.coroutines.launch


class TicketDialog : DialogFragment() {
    private lateinit var binding: DialogTicketBinding
    private var ticket: TicketEntity? = null
    private var ticketId: String? = null

    companion object {
        private const val ARG_TICKET = "ARG_TICKET"
        private const val ARG_TICKET_ID = "ARG_TICKET_ID"
        fun newInstance(ticketEntity: TicketEntity? = null, ticketId: String? = null): TicketDialog {
            val args = Bundle()
            args.putParcelable(ARG_TICKET, ticketEntity)
            args.putString(ARG_TICKET_ID, ticketId)
            val fragment = TicketDialog()
            fragment.arguments = args
            return fragment
        }
    }

    override fun onStart() {
        dialog?.window?.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
        super.onStart()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.dialog_ticket, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        ticket = arguments?.getParcelable(ARG_TICKET)
        ticketId = arguments?.getString(ARG_TICKET_ID)
        if (ticket != null) {
            binding.ticket = ticket
            val start = ticket!!.startDate?.getDateString()
            val due = ticket!!.dueDate?.getDateString()
            binding.id.text = "Ticket ID: ${ticket!!.id}"
            binding.duration.text = if (start != null && due != null)
                start.plus(" to ").plus(due)
            else if (start != null && due == null)
                "Started on : $start"
            else if (start == null && due != null)
                "Due on : $due"
            else
                null
            binding.from.text = "From: " + ticket!!.reporter.name
            binding.to.text =
                if (ticket!!.assignees.isNullOrEmpty())
                    null
                else
                    "To: " + ticket!!.assignees?.joinToString(", ") { it.name }
            binding.complete.setOnClickListener {
                MaterialAlertDialogBuilder(requireContext())
                    .setTitle("Confirm Complete")
                    .setMessage("Are you sure you want to complete this ticket?")
                    .setPositiveButton("Yes") { dialog, _ ->
                        completeTicket()
                        dialog.dismiss()
                    }
                    .setNegativeButton("No") { dialog, _ ->
                        dialog.dismiss()
                    }
                    .show()
            }
            binding.cancel.setOnClickListener {
                MaterialAlertDialogBuilder(requireContext())
                    .setTitle("Confirm Cancel")
                    .setMessage("Are you sure you want to cancel this ticket?")
                    .setPositiveButton("Yes") { dialog, _ ->
                        cancelTicket()
                        dialog.dismiss()
                    }
                    .setNegativeButton("No") { dialog, _ ->
                        dialog.dismiss()
                    }
                    .show()
            }
        }
    }

    private fun cancelTicket() {
        lifecycleScope.launch {
            try {
                val response =
                    AppObjectController.commonNetworkService.updateTicket(
                        id = ticket!!.id,
                        map = mapOf("status" to "Cancelled")
                    )
                if (response.isSuccessful) {
                    dismiss()
                    showToast("Ticket closed successfully!")
                } else {
                    showToast("Something went wrong!")
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    private fun completeTicket() {
        lifecycleScope.launch {
            try {
                val response =
                    AppObjectController.commonNetworkService.updateTicket(
                        id = ticket!!.id,
                        map = mapOf("status" to "Completed")
                    )
                if (response.isSuccessful) {
                    dismiss()
                    showToast("Ticket closed successfully!")
                } else {
                    showToast("Something went wrong!")
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}