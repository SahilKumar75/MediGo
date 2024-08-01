package com.example.musical.common.utils

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.graphics.Paint
import android.graphics.pdf.PdfDocument
import android.net.Uri
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.core.content.FileProvider
import java.io.File
import java.io.FileOutputStream

object PdfUtils {

    private const val APPLICATION_ID = "com.example.musical" // Replace with your actual application ID
    private const val MARGIN = 40f // Margin padding from both sides

    fun createAndOpenBloodReportPdf(context: Context) {
        val pdfDocument = PdfDocument()
        val pageInfo = PdfDocument.PageInfo.Builder(595, 842, 1).create() // A4 size page
        val page = pdfDocument.startPage(pageInfo)

        val canvas = page.canvas
        val paint = Paint()
        paint.textSize = 12f
        paint.color = Color.BLACK

        // Header
        paint.textAlign = Paint.Align.CENTER
        paint.textSize = 16f
        canvas.drawText("PUNJAB INSTITUTE OF MEDICAL SCIENCES", 297.5f, 30f, paint)
        paint.textSize = 12f
        canvas.drawText("(Hospital and Medical College)", 297.5f, 50f, paint)
        paint.textSize = 10f
        canvas.drawText("Run in PPP mode by PIMS Medical & Education Charitable Society", 297.5f, 65f, paint)
        canvas.drawText("Garha Road, Jalandhar. Ph.: 0181-6606000 Emergency (24hrs) : 0181-6606666", 297.5f, 75f, paint)
        canvas.drawText("E-mail : contact@pimsj.com Website : www.pimsj.com", 297.5f, 85f, paint)

        paint.textAlign = Paint.Align.LEFT
        canvas.drawText("Form No. : BB-11", 480f, 30f, paint)
        canvas.drawText("Date: ___________", 480f, 45f, paint)

        // Main Content
        paint.textSize = 12f
        paint.textAlign = Paint.Align.LEFT

        val lines = listOf(
            "Name: __________________________ Age/Date of Birth: ____________________",
            "Hospital Regd. No: _______________ Ward: __________ Bed No: _____________",
            "Hb gm/dl: ___________ Platelet Count: ___________ PT/PTT: ________________",
            "Indication/Diagnosis: ___________________________________________________",
            "Bleeding     Exchange Transfusion     Dialysis     Anaemia     Surgery     Burns     Other",
            "Blood Group: ABO ____ RH ____ Type of Request: Immediate ____ Urgent ____ Routine ____",
            "BLOOD/BLOOD COMPONENTS REQUIRED",
            "Whole Blood/packed RBCs      No of units: ____",
            "PRP / Platelet Conc.          No of units: ____",
            "FFP/SDP/Cryo-ppt             No of units: ____",
            "Donors Provided: Yes/No      No of units: ____",
            "Doctor's Consent",
            "I am ready to give Blood Transfusion to my patient, The Seriousness of illness and risk of blood",
            "Transfusions has been Explained to the patient / relative of Patient.",
            "Name of Doctor: ______________________ Signature: ______________________",
            "Instructions for Blood Requisition",
            "1. Request for routine transfusion should be sent 24 Hrs. in advance.",
            "2. Please send 3-5 ml of Blood sample in plain vial & 2 ml in EDTA/Citrate vial for grouping",
            "    and cross matching.",
            "3. Name & age on vials must tally with the name and age on Requisition Form.",
            "4. Requisition form Unsigned or with any discrepancy will not be accepted.",
            "CONSENT FOR FRESH BLOOD/BLOOD COMPONENTS",
            "Knowing fully the risk of “Fresh Blood Transfusion” and the results of “Rapid Screening Tests for Transfusion",
            "transmittable diseases” I am ready to give Fresh Blood / Blood components to my patient as it is clinically necessary.",
            "The risk of this has been explained to the patient / relative of the patient.",
            "Signature of the Patient/Relative of the patient: _________________________",
            "Signature of the Doctor & Designation With stamp: ______________________"
        )

        var yPos = 110f
        for (line in lines) {
            canvas.drawText(line, MARGIN, yPos, paint)
            yPos += 20f
        }

        pdfDocument.finishPage(page)

        val file = File(context.cacheDir, "blood_report.pdf")
        pdfDocument.writeTo(FileOutputStream(file))
        pdfDocument.close()

        val pdfUri: Uri = FileProvider.getUriForFile(context, "$APPLICATION_ID.provider", file)
        val intent = Intent(Intent.ACTION_VIEW).apply {
            setDataAndType(pdfUri, "application/pdf")
            flags = Intent.FLAG_GRANT_READ_URI_PERMISSION
        }
        context.startActivity(intent)
    }

    fun createAndShareBloodReportPdf(context: Context) {
        val pdfDocument = PdfDocument()
        val pageInfo = PdfDocument.PageInfo.Builder(595, 842, 1).create() // A4 size page
        val page = pdfDocument.startPage(pageInfo)

        val canvas = page.canvas
        val paint = Paint()
        paint.textSize = 12f
        paint.color = Color.BLACK

        // Header
        paint.textAlign = Paint.Align.CENTER
        paint.textSize = 16f
        canvas.drawText("PUNJAB INSTITUTE OF MEDICAL SCIENCES", 297.5f, 30f, paint)
        paint.textSize = 12f
        canvas.drawText("(Hospital and Medical College)", 297.5f, 50f, paint)
        paint.textSize = 10f
        canvas.drawText("Run in PPP mode by PIMS Medical & Education Charitable Society", 297.5f, 65f, paint)
        canvas.drawText("Garha Road, Jalandhar. Ph.: 0181-6606000 Emergency (24hrs) : 0181-6606666", 297.5f, 75f, paint)
        canvas.drawText("E-mail : contact@pimsj.com Website : www.pimsj.com", 297.5f, 85f, paint)

        paint.textAlign = Paint.Align.LEFT
        canvas.drawText("Form No. : BB-11", 480f, 30f, paint)
        canvas.drawText("Date: ___________", 480f, 45f, paint)

        // Main Content
        paint.textSize = 12f
        paint.textAlign = Paint.Align.LEFT

        val lines = listOf(
            "Name: __________________________ Age/Date of Birth: ____________________",
            "Hospital Regd. No: _______________ Ward: __________ Bed No: _____________",
            "Hb gm/dl: ___________ Platelet Count: ___________ PT/PTT: ________________",
            "Indication/Diagnosis: ___________________________________________________",
            "Bleeding     Exchange Transfusion     Dialysis     Anaemia     Surgery     Burns     Other",
            "Blood Group: ABO ____ RH ____ Type of Request: Immediate ____ Urgent ____ Routine ____",
            "BLOOD/BLOOD COMPONENTS REQUIRED",
            "Whole Blood/packed RBCs      No of units: ____",
            "PRP / Platelet Conc.          No of units: ____",
            "FFP/SDP/Cryo-ppt             No of units: ____",
            "Donors Provided: Yes/No      No of units: ____",
            "Doctor's Consent",
            "I am ready to give Blood Transfusion to my patient, The Seriousness of illness and risk of blood",
            "Transfusions has been Explained to the patient / relative of Patient.",
            "Name of Doctor: ______________________ Signature: ______________________",
            "Instructions for Blood Requisition",
            "1. Request for routine transfusion should be sent 24 Hrs. in advance.",
            "2. Please send 3-5 ml of Blood sample in plain vial & 2 ml in EDTA/Citrate vial for grouping",
            "    and cross matching.",
            "3. Name & age on vials must tally with the name and age on Requisition Form.",
            "4. Requisition form Unsigned or with any discrepancy will not be accepted.",
            "CONSENT FOR FRESH BLOOD/BLOOD COMPONENTS",
            "Knowing fully the risk of “Fresh Blood Transfusion” and the results of “Rapid Screening Tests for Transfusion",
            "transmittable diseases” I am ready to give Fresh Blood / Blood components to my patient as it is clinically necessary.",
            "The risk of this has been explained to the patient / relative of the patient.",
            "Signature of the Patient/Relative of the patient: _________________________",
            "Signature of the Doctor & Designation With stamp: ______________________"
        )

        var yPos = 110f
        for (line in lines) {
            canvas.drawText(line, MARGIN, yPos, paint)
            yPos += 20f
        }

        pdfDocument.finishPage(page)

        val file = File(context.cacheDir, "blood_report.pdf")
        pdfDocument.writeTo(FileOutputStream(file))
        pdfDocument.close()

        val pdfUri: Uri = FileProvider.getUriForFile(context, "$APPLICATION_ID.provider", file)
        val shareIntent = Intent().apply {
            action = Intent.ACTION_SEND
            type = "application/pdf"
            putExtra(Intent.EXTRA_STREAM, pdfUri)
            flags = Intent.FLAG_GRANT_READ_URI_PERMISSION
        }
        context.startActivity(Intent.createChooser(shareIntent, "Share Report"))
    }

    fun createAndOpenUrineReportPdf(context: Context, title: String, description: String) {
        val pdfDocument = PdfDocument()
        val pageInfo = PdfDocument.PageInfo.Builder(595, 842, 1).create()
        val page = pdfDocument.startPage(pageInfo)

        val canvas = page.canvas
        val paint = Paint()
        paint.textSize = 12f
        paint.color = Color.BLACK

        val margin = MARGIN

        canvas.drawText("Urine Report", margin, 25f, paint)
        canvas.drawText("Title: $title", margin, 45f, paint)
        canvas.drawText("Description: $description", margin, 65f, paint)
        canvas.drawText("Date: ${java.time.LocalDate.now()}", margin, 85f, paint)

        // Add more detailed content specific to urine report here...

        pdfDocument.finishPage(page)

        val file = File(context.cacheDir, "urine_report.pdf")
        pdfDocument.writeTo(FileOutputStream(file))
        pdfDocument.close()

        val pdfUri: Uri = FileProvider.getUriForFile(context, "$APPLICATION_ID.provider", file)
        val intent = Intent(Intent.ACTION_VIEW).apply {
            setDataAndType(pdfUri, "application/pdf")
            flags = Intent.FLAG_GRANT_READ_URI_PERMISSION
        }
        context.startActivity(intent)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun createAndShareUrineReportPdf(context: Context, title: String, description: String) {
        val pdfDocument = PdfDocument()
        val pageInfo = PdfDocument.PageInfo.Builder(595, 842, 1).create()
        val page = pdfDocument.startPage(pageInfo)

        val canvas = page.canvas
        val paint = Paint()
        paint.textSize = 12f
        paint.color = Color.BLACK

        val margin = MARGIN

        canvas.drawText("Urine Report", margin, 25f, paint)
        canvas.drawText("Title: $title", margin, 45f, paint)
        canvas.drawText("Description: $description", margin, 65f, paint)
        canvas.drawText("Date: ${java.time.LocalDate.now()}", margin, 85f, paint)

        // Add more detailed content specific to urine report here...

        pdfDocument.finishPage(page)

        val file = File(context.cacheDir, "urine_report.pdf")
        pdfDocument.writeTo(FileOutputStream(file))
        pdfDocument.close()

        val pdfUri: Uri = FileProvider.getUriForFile(context, "$APPLICATION_ID.provider", file)
        val shareIntent = Intent().apply {
            action = Intent.ACTION_SEND
            type = "application/pdf"
            putExtra(Intent.EXTRA_STREAM, pdfUri)
            flags = Intent.FLAG_GRANT_READ_URI_PERMISSION
        }
        context.startActivity(Intent.createChooser(shareIntent, "Share Report"))
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun createAndOpenXRayReportPdf(context: Context, title: String, description: String) {
        val pdfDocument = PdfDocument()
        val pageInfo = PdfDocument.PageInfo.Builder(595, 842, 1).create()
        val page = pdfDocument.startPage(pageInfo)

        val canvas = page.canvas
        val paint = Paint()
        paint.textSize = 12f
        paint.color = Color.BLACK

        val margin = MARGIN

        canvas.drawText("X-Ray Report", margin, 25f, paint)
        canvas.drawText("Title: $title", margin, 45f, paint)
        canvas.drawText("Description: $description", margin, 65f, paint)
        canvas.drawText("Date: ${java.time.LocalDate.now()}", margin, 85f, paint)

        // Add more detailed content specific to X-Ray report here...

        pdfDocument.finishPage(page)

        val file = File(context.cacheDir, "xray_report.pdf")
        pdfDocument.writeTo(FileOutputStream(file))
        pdfDocument.close()

        val pdfUri: Uri = FileProvider.getUriForFile(context, "$APPLICATION_ID.provider", file)
        val intent = Intent(Intent.ACTION_VIEW).apply {
            setDataAndType(pdfUri, "application/pdf")
            flags = Intent.FLAG_GRANT_READ_URI_PERMISSION
        }
        context.startActivity(intent)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun createAndShareXRayReportPdf(context: Context, title: String, description: String) {
        val pdfDocument = PdfDocument()
        val pageInfo = PdfDocument.PageInfo.Builder(595, 842, 1).create()
        val page = pdfDocument.startPage(pageInfo)

        val canvas = page.canvas
        val paint = Paint()
        paint.textSize = 12f
        paint.color = Color.BLACK

        val margin = MARGIN

        canvas.drawText("X-Ray Report", margin, 25f, paint)
        canvas.drawText("Title: $title", margin, 45f, paint)
        canvas.drawText("Description: $description", margin, 65f, paint)
        canvas.drawText("Date: ${java.time.LocalDate.now()}", margin, 85f, paint)

        // Add more detailed content specific to X-Ray report here...

        pdfDocument.finishPage(page)

        val file = File(context.cacheDir, "xray_report.pdf")
        pdfDocument.writeTo(FileOutputStream(file))
        pdfDocument.close()

        val pdfUri: Uri = FileProvider.getUriForFile(context, "$APPLICATION_ID.provider", file)
        val shareIntent = Intent().apply {
            action = Intent.ACTION_SEND
            type = "application/pdf"
            putExtra(Intent.EXTRA_STREAM, pdfUri)
            flags = Intent.FLAG_GRANT_READ_URI_PERMISSION
        }
        context.startActivity(Intent.createChooser(shareIntent, "Share Report"))
    }
}
