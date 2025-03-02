package com.example.demo.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.demo.states.FinancePurpose

@Composable
fun FinancePurposeSelection(
    selectedPurpose: FinancePurpose?,
    onSelectionChange: (FinancePurpose) -> Unit,
    errorMessage: String?
) {
    Column {
        Text(text = "Choose your finance purpose:", fontWeight = FontWeight.Bold)

        listOf(
            FinancePurpose.INVENTORY to "Inventory items & Goods",
            FinancePurpose.TOOLS to "Tools & Equipment",
            FinancePurpose.BOTH to "Both"
        ).forEach { (purpose, label) ->
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { onSelectionChange(purpose) }
                    .padding(vertical = 4.dp)
            ) {
                RadioButton(selected = selectedPurpose == purpose, onClick = { onSelectionChange(purpose) })
                Spacer(modifier = Modifier.width(8.dp))
                Text(label)
            }
        }

        errorMessage?.let {
            Text(text = it, color = Color.Red, fontSize = 12.sp)
        }
    }
}
