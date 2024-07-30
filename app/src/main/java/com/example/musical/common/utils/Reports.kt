package com.example.musical.common.utils

import android.content.Context
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.musical.R

data class MedicalReport(
    val title: String,
    val description: String,
    val date: String,
    val fileName: String
)

@Composable
fun Report() {
    val context = LocalContext.current
    val cardBackgroundColor = Color(0xFF006eff)
    val cardTextColor = Color.White
    val cardTextStyle = TextStyle(
        color = cardTextColor,
        fontWeight = FontWeight.Bold
    )
    val sectionTextStyle = TextStyle(
        color = Color.Black,
        fontWeight = FontWeight.Bold
    )

    val recentReports = listOf(
        MedicalReport("Blood Report", "A brief description of the blood report", "2023-07-29", "blood_report.pdf")
    )

    val oldReports = listOf(
        MedicalReport("Urine Report", "A brief description of the urine report", "2023-06-20", "urine_report.pdf"),
        MedicalReport("X-Ray Report", "A brief description of the X-ray report", "2023-05-10", "xray_report.pdf")
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        // Recent Reports Section
        SectionHeader("Recent Reports", sectionTextStyle)
        recentReports.forEach { report ->
            ReportCard(report = report, context = context, textStyle = cardTextStyle, cardBackgroundColor = cardBackgroundColor)
            Spacer(modifier = Modifier.height(8.dp))
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Old Reports Section
        SectionHeader("Old Reports", sectionTextStyle)
        oldReports.forEach { report ->
            ReportCard(report = report, context = context, textStyle = cardTextStyle, cardBackgroundColor = cardBackgroundColor)
            Spacer(modifier = Modifier.height(8.dp))
        }
    }
}

@Composable
fun SectionHeader(title: String, textStyle: TextStyle) {
    Text(
        text = title,
        style = textStyle,
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 8.dp, top = 8.dp)
    )
    Spacer(modifier = Modifier.height(8.dp))
}

@Composable
fun ReportCard(report: MedicalReport, context: Context, textStyle: TextStyle, cardBackgroundColor: Color) {
    Card(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth(),
        elevation = 4.dp,
        backgroundColor = cardBackgroundColor
    ) {
        Column(modifier = Modifier.padding(8.dp)) {
            Text(text = report.title, style = textStyle)
            Text(
                text = report.description,
                style = MaterialTheme.typography.body1.copy(color = textStyle.color)
            )
            Row(
                Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                TextButton(onClick = { PdfUtils.createAndOpenPdf(context, report.title, report.description) }) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(text = "View Full Report", style = textStyle)
                        Icon(
                            imageVector = Icons.Filled.KeyboardArrowRight,
                            contentDescription = "View Full Report"
                        )
                    }
                }
            }
            Divider(thickness = 1.dp, modifier = Modifier.padding(vertical = 8.dp))
            Row(
                Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp)
                    .clickable { PdfUtils.createAndSharePdf(context, report.title, report.description) },
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_baseline_share_24),
                    contentDescription = "Share your Report",
                    modifier = Modifier.padding(end = 8.dp)
                )
                Text(text = "Share your Report", style = textStyle)
            }
        }
    }
}
