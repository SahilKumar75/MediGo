package com.example.musical.ui.theme

import android.content.ActivityNotFoundException
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Card
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.material.icons.Icons
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.musical.R
import android.content.Context
import android.content.Intent
import android.graphics.pdf.PdfDocument
import android.os.Environment
import android.util.Log
import android.widget.Toast
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.ui.platform.LocalContext
import androidx.core.content.FileProvider
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import android.graphics.*
import androidx.compose.foundation.clickable
import androidx.compose.material.MaterialTheme
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight


@Composable
fun Report() {
    var showDialog by remember { mutableStateOf(false) }
    val context = LocalContext.current
    val cardBackgroundColor = Color(0xFF006eff) // Replace with your desired color
    val textColor = Color.White // Change to your desired text color
    val textStyle = TextStyle(
        color = textColor,
        fontWeight = FontWeight.Bold
    )

    Column(
        modifier = Modifier.height(200.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = "Your All Medical Reports", style = textStyle)
        Card(
            modifier = Modifier.padding(8.dp),
            elevation = 4.dp,
            backgroundColor = cardBackgroundColor // Set the background color here
        ) {
            Column(modifier = Modifier.padding(8.dp)) {
                Column {
                    Text(text = "Blood Report", style = textStyle)
                    Text(
                        text = "A brief description of the blood report",
                        style = MaterialTheme.typography.body1.copy(color = textColor)
                    )
                    Row(
                        Modifier
                            .fillMaxWidth()
                            .padding(vertical = 8.dp),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        TextButton(onClick = {
                            createAndOpenPdf(context)
                        }) {
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
                }
                Divider(thickness = 1.dp, modifier = Modifier.padding(vertical = 8.dp))
                Row(
                    Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp)
                        .clickable { createAndSharePdf(context) },
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
}


fun createAndOpenPdf(context: Context) {
    // Create a new document
    val document = PdfDocument()

    // Create a new page
    val pageInfo = PdfDocument.PageInfo.Builder(595, 842, 1).create()
    val page = document.startPage(pageInfo)

    // Draw the report header
    val canvas = page.canvas
    val paint = Paint()
    paint.textAlign = Paint.Align.LEFT
    paint.textSize = 16f
    paint.typeface = Typeface.create(Typeface.DEFAULT, Typeface.BOLD)
    canvas.drawText("Medical Report", 80f, 50f, paint)

    // Draw a line under the header
    paint.style = Paint.Style.STROKE
    canvas.drawLine(80f, 60f, 515f, 60f, paint)

    // Draw patient information
    paint.style = Paint.Style.FILL
    paint.textAlign = Paint.Align.LEFT
    paint.textSize = 12f
    paint.typeface = Typeface.create(Typeface.DEFAULT, Typeface.NORMAL)
    canvas.drawText("Patient Name: Daksh Dhaka", 80f, 80f, paint)
    canvas.drawText("Age: 25", 80f, 100f, paint)
    canvas.drawText("Gender: Male", 80f, 120f, paint)
    canvas.drawText("Date: ${java.util.Date()}", 80f, 140f, paint)

    // Draw report details
    paint.typeface = Typeface.create(Typeface.DEFAULT, Typeface.BOLD)
    canvas.drawText("Blood Report", 80f, 180f, paint)
    paint.typeface = Typeface.create(Typeface.DEFAULT, Typeface.NORMAL)
    canvas.drawText("Red Blood Cells: 4.5 - 5.5 million cells/mcL", 80f, 200f, paint)
    canvas.drawText("White Blood Cells: 4,000 - 11,000 cells/mcL", 80f, 220f, paint)
    canvas.drawText("Platelets: 150,000 - 450,000/mcL", 80f, 240f, paint)

    // Finish the page
    document.finishPage(page)

    // Save the document to a file
    val file = File(
        context.getExternalFilesDir(Environment.DIRECTORY_DOCUMENTS),
        "report.pdf"
    )

    try {
        document.writeTo(FileOutputStream(file))
        document.close()

        // Create an Intent to open the PDF file
        val uri = FileProvider.getUriForFile(
            context,
            context.applicationContext.packageName + ".provider",
            file
        )

        val intent = Intent(Intent.ACTION_VIEW)
        intent.setDataAndType(uri, "application/pdf")
        intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION or Intent.FLAG_ACTIVITY_NO_HISTORY)

        val chooser = Intent.createChooser(intent, "Open PDF")
        try {
            context.startActivity(chooser)
        } catch (e: ActivityNotFoundException) {
            Toast.makeText(context, "No PDF viewer found", Toast.LENGTH_SHORT).show()
        }
    } catch (e: IOException) {
        Log.e("Report", "Error writing PDF file", e)
        Toast.makeText(context, "Failed to create PDF", Toast.LENGTH_SHORT).show()
    }
}

fun createAndSharePdf(context: Context) {
    // Create a new document
    val document = PdfDocument()

    // Create a new page
    val pageInfo = PdfDocument.PageInfo.Builder(595, 842, 1).create()
    val page = document.startPage(pageInfo)

    // Draw the report header
    val canvas = page.canvas
    val paint = Paint()
    paint.textAlign = Paint.Align.LEFT
    paint.textSize = 16f
    paint.typeface = Typeface.create(Typeface.DEFAULT, Typeface.BOLD)
    canvas.drawText("Medical Report", 80f, 50f, paint)

    // Draw a line under the header
    paint.style = Paint.Style.STROKE
    canvas.drawLine(80f, 60f, 515f, 60f, paint)

    // Draw patient information
    paint.style = Paint.Style.FILL
    paint.textAlign = Paint.Align.LEFT
    paint.textSize = 12f
    paint.typeface = Typeface.create(Typeface.DEFAULT, Typeface.NORMAL)
    canvas.drawText("Patient Name: Daksh Dhaka", 80f, 80f, paint)
    canvas.drawText("Age: 25", 80f, 100f, paint)
    canvas.drawText("Gender: Male", 80f, 120f, paint)
    canvas.drawText("Date: ${java.util.Date()}", 80f, 140f, paint)

    // Draw report details
    paint.typeface = Typeface.create(Typeface.DEFAULT, Typeface.BOLD)
    canvas.drawText("Blood Report", 80f, 180f, paint)
    paint.typeface = Typeface.create(Typeface.DEFAULT, Typeface.NORMAL)
    canvas.drawText("Red Blood Cells: 4.5 - 5.5 million cells/mcL", 80f, 200f, paint)
    canvas.drawText("White Blood Cells: 4,000 - 11,000 cells/mcL", 80f, 220f, paint)
    canvas.drawText("Platelets: 150,000 - 450,000/mcL", 80f, 240f, paint)

    // Finish the page
    document.finishPage(page)

    // Save the document to a file
    val file = File(
        context.getExternalFilesDir(Environment.DIRECTORY_DOCUMENTS),
        "report.pdf"
    )

    try {
        document.writeTo(FileOutputStream(file))
        document.close()

        // Create an Intent to share the PDF file
        val uri = FileProvider.getUriForFile(
            context,
            context.applicationContext.packageName + ".provider",
            file
        )

        val shareIntent = Intent(Intent.ACTION_SEND)
        shareIntent.type = "application/pdf"
        shareIntent.putExtra(Intent.EXTRA_STREAM, uri)
        shareIntent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)

        val chooser = Intent.createChooser(shareIntent, "Share PDF")
        try {
            context.startActivity(chooser)
        } catch (e: ActivityNotFoundException) {
            Toast.makeText(context, "No app found to share PDF", Toast.LENGTH_SHORT).show()
        }
    } catch (e: IOException) {
        Log.e("Report", "Error writing PDF file", e)
        Toast.makeText(context, "Failed to create PDF", Toast.LENGTH_SHORT).show()
    }
}



