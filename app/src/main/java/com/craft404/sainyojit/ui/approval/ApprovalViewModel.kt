package com.craft404.sainyojit.ui.approval

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.craft404.sainyojit.repository.entity.TicketEntity
import com.craft404.sainyojit.repository.model.ApplicationUserModel
import com.craft404.sainyojit.repository.model.ApprovalRecyclerItem
import com.craft404.sainyojit.repository.model.DocumentModel
import com.craft404.sainyojit.util.AppObjectController
import com.craft404.sainyojit.util.showAppropriateMsg
import com.craft404.sainyojit.util.showToast
import kotlinx.coroutines.launch
import java.util.*

class ApprovalViewModel(application: Application) : AndroidViewModel(application) {
    lateinit var applicationId: String
    lateinit var ticketId: String

    private val _approvalResult: MutableLiveData<List<ApprovalRecyclerItem>> = MutableLiveData()
    val approvalResult: LiveData<List<ApprovalRecyclerItem>>
        get() = _approvalResult

    val isProcessing = MutableLiveData<Boolean>()
    val documentsResult: MutableLiveData<List<DocumentModel>> = MutableLiveData()
    val ticketsResult: MutableLiveData<List<TicketEntity>> = MutableLiveData()
    val application: MutableLiveData<ApplicationUserModel> = MutableLiveData()

    fun fetchApprovals(calendar: Calendar, overdue: Boolean) {
        viewModelScope.launch {
            try {
                isProcessing.postValue(true)
                val response =
                    AppObjectController.commonNetworkService.fetchApproval(
                        calendar.apply {
                            set(Calendar.HOUR_OF_DAY, 0)
                            set(Calendar.MINUTE, 0)
                            set(Calendar.SECOND, 0)
                        }.time,
                        calendar.apply {
                            set(Calendar.HOUR_OF_DAY, 23)
                            set(Calendar.MINUTE, 59)
                            set(Calendar.SECOND, 59)
                        }.time,
                        overdue
                    )
                if (response.isSuccessful) {
                    val list = response.body()?.approvals
                    val groupedList = mutableListOf<ApprovalRecyclerItem>()
                    list?.groupBy { it.title }?.forEach {
                        groupedList.add(ApprovalRecyclerItem.HeaderItem(it.key))
                        groupedList.addAll(it.value.map { ticketEntity -> ApprovalRecyclerItem.ApprovalItem(ticketEntity) })
                    }
                    _approvalResult.postValue(groupedList)
                }
            } catch (e: Exception) {
                e.showAppropriateMsg()
                Log.e("YASH", "fetchApprovals: ", e)
            } finally {
                isProcessing.postValue(false)
            }
        }
    }

    fun fetchDocuments() {
        viewModelScope.launch {
            try {
                isProcessing.postValue(true)
                val response = AppObjectController.commonNetworkService.fetchDocuments(applicationId)
                if (response.isSuccessful) {
                    documentsResult.postValue(response.body())
                }
            } catch (e: Exception) {
                e.printStackTrace()
                e.showAppropriateMsg()
            } finally {
                isProcessing.postValue(false)
            }
        }
    }

    fun fetchTickets() {
        viewModelScope.launch {
            try {
                isProcessing.postValue(true)
                val response = AppObjectController.commonNetworkService.fetchTickets(applicationId)
                if (response.isSuccessful) {
                    ticketsResult.postValue(response.body())
                }
            } catch (e: Exception) {
                e.printStackTrace()
                e.showAppropriateMsg()
            } finally {
                isProcessing.postValue(false)
            }
        }
    }

    fun fetchApplicationDetails() {
        viewModelScope.launch {
            try {
                isProcessing.postValue(true)
                val response = AppObjectController.commonNetworkService.fetchApplicationDetails(applicationId)
                if (response.isSuccessful) {
                    application.postValue(response.body())
                }
            } catch (e: Exception) {
                e.printStackTrace()
                e.showAppropriateMsg()
            } finally {
                isProcessing.postValue(false)
            }
        }
    }

    fun approveTicket() {
        viewModelScope.launch {
            try {
                isProcessing.postValue(true)
                val response = AppObjectController.commonNetworkService.approveTicket(ticketId)
                if (response.isSuccessful) {
                    showToast("Ticket approved successfully")
                }
            } catch (e: Exception) {
                e.printStackTrace()
                e.showAppropriateMsg()
            } finally {
                isProcessing.postValue(false)
            }
        }
    }
}