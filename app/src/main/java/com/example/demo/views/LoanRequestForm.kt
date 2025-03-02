package com.example.demo.views

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.demo.components.BoundedTextField
import com.example.demo.components.FinancePurposeSelection
import com.example.demo.intents.FormIntent
import com.example.demo.states.FormField
import com.example.demo.view_models.FormViewModel

@Composable
fun LoanRequestForm(viewModel: FormViewModel, modifier: Modifier) {
    val state by viewModel.state

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(text = "Enter your request details:", fontWeight = FontWeight.Bold)

        Spacer(modifier = Modifier.height(8.dp))

        BoundedTextField(
            value = state.requestedAmount,
            onValueChange = { viewModel.onEvent(FormIntent.EnteredRequestedAmount(it)) },
            label = "Requested Amount",
            errorMessage = state.errors[FormField.REQUESTED_AMOUNT]
        )

        Spacer(modifier = Modifier.height(8.dp))

        BoundedTextField(
            value = state.installmentTenure,
            onValueChange = { viewModel.onEvent(FormIntent.EnteredInstallmentTenure(it)) },
            label = "Installment Tenure",
            errorMessage = state.errors[FormField.INSTALLMENT_TENURE]
        )

        Spacer(modifier = Modifier.height(8.dp))

        FinancePurposeSelection(
            selectedPurpose = state.financePurpose,
            onSelectionChange = { viewModel.onEvent(FormIntent.SelectedFinancePurpose(it)) },
            errorMessage = state.errors[FormField.FINANCE_PURPOSE]
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = { viewModel.onEvent(FormIntent.SubmitForm) },
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFC62828))
        ) {
            Text(text = "Continue", color = Color.White, fontSize = 18.sp)
        }
    }
}
