package com.example.demo.view_models

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.demo.states.FormField
import com.example.demo.intents.FormIntent
import com.example.demo.states.FormState

class FormViewModel : ViewModel() {

    private val _state = mutableStateOf(FormState())
    val state = _state

    fun onEvent(event: FormIntent) {
        when (event) {
            is FormIntent.EnteredRequestedAmount -> {
                updateState(FormField.REQUESTED_AMOUNT, event.amount)
            }
            is FormIntent.EnteredInstallmentTenure -> {
                updateState(FormField.INSTALLMENT_TENURE, event.tenure)
            }
            is FormIntent.SelectedFinancePurpose -> {
                _state.value = _state.value.copy(financePurpose = event.purpose)
            }
            is FormIntent.SubmitForm -> {
                validateForm()
            }
        }
    }

    private fun updateState(field: FormField, value: String) {
        val updatedErrors = _state.value.errors.toMutableMap()
        updatedErrors.remove(field)

        _state.value = when (field) {
            FormField.REQUESTED_AMOUNT -> _state.value.copy(requestedAmount = value, errors = updatedErrors)
            FormField.INSTALLMENT_TENURE -> _state.value.copy(installmentTenure = value, errors = updatedErrors)
            else -> _state.value
        }
    }

    private fun validateForm() {
        val errors = mutableMapOf<FormField, String>()

        with(_state.value) {
            if (requestedAmount.isBlank() || requestedAmount.toIntOrNull() !in 30000..400000) {
                errors[FormField.REQUESTED_AMOUNT] = "Amount must be between 30,000 and 400,000"
            }
            if (installmentTenure.isBlank() || installmentTenure.toIntOrNull() !in 6..36) {
                errors[FormField.INSTALLMENT_TENURE] = "Tenure must be between 6 and 36 months"
            }
            if (financePurpose == null) {
                errors[FormField.FINANCE_PURPOSE] = "Please select a finance purpose"
            }
        }

        _state.value = _state.value.copy(errors = errors)
    }
}
