package com.example.musical.common.utils

import android.content.Context
import android.content.Intent
import android.graphics.pdf.PdfDocument
import android.net.Uri
import java.io.File
import java.io.FileOutputStream

object PdfUtils {

    fun createAndOpenPdf(context: Context, title: String, description: String) {
        val pdfDocument = PdfDocument()
        val pageInfo = PdfDocument.PageInfo.Builder(300, 600, 1).create()
        val page = pdfDocument.startPage(pageInfo)

        val canvas = page.canvas
        val paint = android.graphics.Paint()
        paint.textSize = 12f

        canvas.drawText(title, 10f, 25f, paint)
        canvas.drawText(description, 10f, 45f, paint)

        pdfDocument.finishPage(page)

        val file = File(context.cacheDir, "report.pdf")
        pdfDocument.writeTo(FileOutputStream(file))
        pdfDocument.close()

        val pdfUri: Uri = Uri.fromFile(file)
        val intent = Intent(Intent.ACTION_VIEW).apply {
            setDataAndType(pdfUri, "application/pdf")
            flags = Intent.FLAG_ACTIVITY_NO_HISTORY
        }
        context.startActivity(intent)
    }

    fun createAndSharePdf(context: Context, title: String, description: String) {
        val pdfDocument = PdfDocument()
        val pageInfo = PdfDocument.PageInfo.Builder(300, 600, 1).create()
        val page = pdfDocument.startPage(pageInfo)

        val canvas = page.canvas
        val paint = android.graphics.Paint()
        paint.textSize = 12f

        canvas.drawText(title, 10f, 25f, paint)
        canvas.drawText(description, 10f, 45f, paint)

        pdfDocument.finishPage(page)

        val file = File(context.cacheDir, "report.pdf")
        pdfDocument.writeTo(FileOutputStream(file))
        pdfDocument.close()

        val pdfUri: Uri = Uri.fromFile(file)
        val shareIntent = Intent().apply {
            action = Intent.ACTION_SEND
            type = "application/pdf"
            putExtra(Intent.EXTRA_STREAM, pdfUri)
        }
        context.startActivity(Intent.createChooser(shareIntent, "Share Report"))
    }
}
