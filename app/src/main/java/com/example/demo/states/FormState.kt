package com.example.demo.states

data class FormState(
    val requestedAmount: String = "",
    val installmentTenure: String = "",
    val financePurpose: FinancePurpose? = null,
    val errors: Map<FormField, String> = emptyMap()
)

enum class FormField { REQUESTED_AMOUNT, INSTALLMENT_TENURE, FINANCE_PURPOSE }
enum class FinancePurpose { INVENTORY, TOOLS, BOTH }
