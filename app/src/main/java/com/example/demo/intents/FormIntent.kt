package com.example.demo.intents

import com.example.demo.states.FinancePurpose


sealed class FormIntent {
    data class EnteredRequestedAmount(val amount: String) : FormIntent()
    data class EnteredInstallmentTenure(val tenure: String) : FormIntent()
    data class SelectedFinancePurpose(val purpose: FinancePurpose) : FormIntent()
    data object SubmitForm : FormIntent()
}
