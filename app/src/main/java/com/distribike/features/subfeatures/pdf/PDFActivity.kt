package com.distribike.features.subfeatures.pdf

import android.Manifest
import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.*
import android.graphics.pdf.PdfDocument
import android.os.Bundle
import android.os.Environment
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material3.ButtonDefaults
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.*
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.content.FileProvider
import androidx.hilt.navigation.compose.hiltViewModel
import com.distribike.BuildConfig
import com.distribike.R
import com.distribike.features.subfeatures.motorcycleform.MotorcycleFormActivity
import com.distribike.features.subfeatures.pdf.model.PDFModelUi
import com.distribike.features.subfeatures.pdf.viewmodel.PDFViewModel
import com.distribike.ui.theme.*
import dagger.hilt.android.AndroidEntryPoint
import java.io.File
import java.io.FileOutputStream
import java.text.SimpleDateFormat
import java.util.*

@AndroidEntryPoint
class PDFActivity : ComponentActivity() {

    companion object {
        fun newInstance(context: Context) = Intent(context, PDFActivity::class.java)
    }

    @SuppressLint("UnusedMaterialScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Surface(
                modifier = Modifier.fillMaxSize(),
                color = MaterialTheme.colors.background
            ) {
                Scaffold(
                    topBar = {
                        TopAppBar(
                            backgroundColor = Green,
                            title = {
                                Text(
                                    text = "PDF",
                                    modifier = Modifier.fillMaxWidth(),
                                    textAlign = TextAlign.Center,
                                    color = White
                                )
                            }
                        )
                    }
                ) {
                    PDFGenerator()
                }
            }
        }
    }

    @Deprecated("Deprecated in Java")
    @Suppress("DEPRECATION")
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        if (requestCode == 101) {
            if (grantResults.isNotEmpty()) {
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED && grantResults[1] == PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(this, "Permission Granted..", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(this, "Permission Denied..", Toast.LENGTH_SHORT).show()
                    finish()
                }
            }
        }
    }

    @Composable
    fun PDFGenerator() {
        val context = LocalContext.current
        val activity = (LocalContext.current as? Activity)

        val viewModel = hiltViewModel<PDFViewModel>()
        val sections = viewModel.formRecordLiveData.observeAsState(PDFModelUi())

        if (checkPermissions(context)) {
            Toast.makeText(context, "Permissions Granted..", Toast.LENGTH_SHORT).show()
        } else {
            requestPermission(activity!!)
        }

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .fillMaxSize()
                .padding(6.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(50.dp))

            Text(
                text = "Validation du PDI",
                color = Green,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                fontSize = 40.sp
            )

            Image(
                painter = painterResource(id = R.drawable.login_image5),
                contentDescription = null,
                modifier = Modifier
                    .height(540.dp)
                    .width(580.dp)
            )

            Spacer(modifier = Modifier.height(50.dp))

            androidx.compose.material3.Button(
                colors = ButtonDefaults.buttonColors(containerColor = Green),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 150.dp),
                onClick = {
                    generatePDF(
                        context = context,
                        sections = sections.value
                    )
                }) {

                Text(
                    modifier = Modifier
                        .padding(10.dp),
                    color = White,
                    fontSize = 22.sp,
                    text = "Ouvrir/Envoyer/Imprimer PDF"
                )
            }

            Spacer(modifier = Modifier.height(80.dp))

            androidx.compose.material3.Button(
                // on below line we are adding a modifier
                // to it and specifying max width to it.
                colors = ButtonDefaults.buttonColors(containerColor = RedDark),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 150.dp),
                onClick = {
                    finish()
                    startActivity(MotorcycleFormActivity.newInstance(context = applicationContext))
                }
            ) {
                Text(
                    modifier = Modifier
                        .padding(10.dp),
                    color = White,
                    fontSize = 22.sp,
                    text = "Commencer un nouveau châssis"
                )
            }
        }
    }

    private fun generatePDF(context: Context, sections: PDFModelUi) {

        // Initialize MotorcycleForm Information
        val pageHeight = 1680
        val pageWidth = 1188
        val dataFormat = SimpleDateFormat("dd/M/yyyy hh:mm:ss", Locale.FRANCE)
        val currentDate = dataFormat.format(Date())
        val name = "NOM PRENOM"
        val codePrep = "555555"
        val model = "CMX500"
        val chassis = "JH25ML14578912314"
        val concessionName = "MOTO ARLES SARL TEST"
        val concessionCode = "1055577"
        val position = "921127"
        val frontWheelPressure = "20"
        val backWheelPressure = "25"
        val articulation = "5"
        val markLeft = "2"
        val markRight = "5"

        lateinit var scaledBitmap2: Bitmap

        lateinit var scaledBitmap3: Bitmap

        lateinit var scaledBitmap4: Bitmap

        lateinit var scaledBitmap5: Bitmap

        lateinit var scaledBitmap6: Bitmap

        lateinit var scaledBitmap7: Bitmap

        lateinit var scaledBitmap8: Bitmap

        // Creating an object variable for our PDF document.
        val pdfDocument = PdfDocument()

        // Two variables for paint:
        // paint is for drawing shapes
        // textPaint is for adding text in our PDF file.
        val paint = Paint()
        val textPaint = Paint()

        // On below line we are initializing our bitmap and scaled bitmap.

        val bmp2: Bitmap = BitmapFactory.decodeResource(context.resources, R.drawable.page3)
        scaledBitmap2 = Bitmap.createScaledBitmap(bmp2, 1188, 1680, false)

        val bmp3: Bitmap = BitmapFactory.decodeResource(context.resources, R.drawable.page2)
        scaledBitmap3 = Bitmap.createScaledBitmap(bmp3, 1188, 1680, false)

        val bmp4: Bitmap = BitmapFactory.decodeResource(context.resources, R.drawable.page1)
        scaledBitmap4 = Bitmap.createScaledBitmap(bmp4, 1188, 1680, false)

        val bmp5: Bitmap = BitmapFactory.decodeResource(context.resources, R.drawable.check)
        scaledBitmap5 = Bitmap.createScaledBitmap(bmp5, 30, 28, false)

        val bmp6: Bitmap = BitmapFactory.decodeResource(context.resources, R.drawable.pass)
        scaledBitmap6 = Bitmap.createScaledBitmap(bmp6, 30, 28, false)

        val bmp7: Bitmap = BitmapFactory.decodeResource(context.resources, R.drawable.check)
        scaledBitmap7 = Bitmap.createScaledBitmap(bmp7, 30, 28, false)

        val bmp8: Bitmap = BitmapFactory.decodeResource(context.resources, R.drawable.pass)
        scaledBitmap8 = Bitmap.createScaledBitmap(bmp8, 30, 28, false)


        // we are adding page info to our PDF file
        // in which we will be passing our pageWidth,
        // pageHeight and number of pages and after that
        // we are calling it to create our PDF.
        val myPageInfo: PdfDocument.PageInfo? =
            PdfDocument.PageInfo.Builder(pageWidth, pageHeight, 1).create()

        val myPageInfo2: PdfDocument.PageInfo? =
            PdfDocument.PageInfo.Builder(pageWidth, pageHeight, 2).create()

        val myPageInfo3: PdfDocument.PageInfo? =
            PdfDocument.PageInfo.Builder(pageWidth, pageHeight, 3).create()
        // below line is used for setting
        // start page for our PDF file.
        val myPage: PdfDocument.Page = pdfDocument.startPage(myPageInfo)

        // creating a variable for canvas
        // from our page of PDF.
        val canvas1: Canvas = myPage.canvas
        val canvas4: Canvas = myPage.canvas
        val canvas5: Canvas = myPage.canvas
        val canvas6: Canvas = myPage.canvas

        // below line is used to draw our image on our PDF file.
        // the first parameter of our drawbitmap method is
        // our bitmap
        // second parameter is position from left
        // third parameter is position from top and last
        // one is our variable for paint.

        canvas4.drawBitmap(scaledBitmap4, 0F, 0F, paint)

        //GENERAL
        //canvas5.drawBitmap(scaledbmp5, 100F, 450F, paint)
        textPaint.typeface = Typeface.create(Typeface.DEFAULT, Typeface.NORMAL)
        textPaint.textSize = 20F
        textPaint.color = ContextCompat.getColor(context, R.color.black)
        canvas1.drawText(name, 255F, 139F, textPaint)
        canvas1.drawText(codePrep, 255F, 180F, textPaint)
        canvas1.drawText(model, 702F, 139F, textPaint)
        canvas1.drawText(chassis, 730F, 182F, textPaint)
        canvas1.drawText(concessionName, 831F, 227F, textPaint)
        canvas1.drawText(concessionCode, 831F, 267F, textPaint)
        canvas1.drawText(position, 762F, 312F, textPaint)
        canvas1.drawText(frontWheelPressure, 782F, 955F, textPaint)
        canvas1.drawText(backWheelPressure, 925F, 955F, textPaint)
        canvas1.drawText("$articulation cm", 370F, 1393F, textPaint)
        canvas1.drawText(markLeft, 750F, 1462F, textPaint)
        canvas1.drawText(markRight, 900F, 1462F, textPaint)

        // GLOBAL POSITION
        // Normalement ces positions ne devraient jamais changer,
        // C'est toujours la même colonne.
        val checkPositionX = 70f
        val passPositionX = 110f

        // On initialise un position Y (top) d'origine,
        // Et chaque fois que l'on dessine une nouvelle ligne, on ajoute +30
        var generalOriginPostionY = 450f
        sections.generalSteps?.stepModelUis?.forEach {
            when (it.stepStateUseCaseModel) {
                PDFModelUi.State.NONE -> { /* nothing to do */
                }
                PDFModelUi.State.COMPLETE -> {
                    canvas5.drawBitmap(scaledBitmap5, checkPositionX, generalOriginPostionY, paint)
                    generalOriginPostionY += 30f
                }
                PDFModelUi.State.PASS -> {
                    canvas6.drawBitmap(scaledBitmap6, passPositionX, generalOriginPostionY, paint)
                    generalOriginPostionY += 30f
                }
            }
        }

        var batteryOriginPositionY = 720f
        sections.batterySteps?.stepModelUis?.map {
            when (it.stepStateUseCaseModel) {
                PDFModelUi.State.NONE -> { /* nothing to do */
                }
                PDFModelUi.State.COMPLETE -> {
                    canvas5.drawBitmap(scaledBitmap5, checkPositionX, batteryOriginPositionY, paint)
                    batteryOriginPositionY += 30f
                }
                PDFModelUi.State.PASS -> {
                    canvas6.drawBitmap(scaledBitmap6, passPositionX, batteryOriginPositionY, paint)
                    batteryOriginPositionY += 30f
                }
            }
        }

        var wheelsOriginPositionY = 900f
        sections.wheelsAndTiresSteps?.stepEntityModels?.map {
            when (it.stepStateUseCaseModel) {
                PDFModelUi.State.NONE -> { /* nothing to do */
                }
                PDFModelUi.State.COMPLETE -> {
                    canvas5.drawBitmap(scaledBitmap5, checkPositionX, wheelsOriginPositionY, paint)
                    wheelsOriginPositionY += 30f
                }
                PDFModelUi.State.PASS -> {
                    canvas6.drawBitmap(scaledBitmap6, passPositionX, wheelsOriginPositionY, paint)
                    wheelsOriginPositionY += 30f
                }
            }
        }

        var breaksOriginPositionY = 1085f
        sections.breaksSteps?.stepEntityModels?.map {
            when (it.stepStateUseCaseModel) {
                PDFModelUi.State.NONE -> { /* nothing to do */
                }
                PDFModelUi.State.COMPLETE -> {
                    canvas5.drawBitmap(scaledBitmap5, checkPositionX, breaksOriginPositionY, paint)
                    breaksOriginPositionY += 30f
                }
                PDFModelUi.State.PASS -> {
                    canvas6.drawBitmap(scaledBitmap6, passPositionX, breaksOriginPositionY, paint)
                    breaksOriginPositionY += 30f
                }
            }
        }

        var suspensionsOriginPositionY = 1255f
        sections.suspensionSteps?.stepEntityModels?.map {
            when (it.stepStateUseCaseModel) {
                PDFModelUi.State.NONE -> { /* nothing to do */
                }
                PDFModelUi.State.COMPLETE -> {
                    canvas5.drawBitmap(
                        scaledBitmap5,
                        checkPositionX,
                        suspensionsOriginPositionY,
                        paint
                    )
                    suspensionsOriginPositionY += 30f
                }
                PDFModelUi.State.PASS -> {
                    canvas6.drawBitmap(
                        scaledBitmap6,
                        passPositionX,
                        suspensionsOriginPositionY,
                        paint
                    )
                    suspensionsOriginPositionY += 30f
                }
            }
        }

        var transmissionOriginPositionY = 1340f
        sections.transmissionSteps?.stepEntityModels?.map {
            when (it.stepStateUseCaseModel) {
                PDFModelUi.State.NONE -> { /* nothing to do */
                }
                PDFModelUi.State.COMPLETE -> {
                    canvas5.drawBitmap(
                        scaledBitmap5,
                        checkPositionX,
                        transmissionOriginPositionY,
                        paint
                    )
                    transmissionOriginPositionY += 30f
                }
                PDFModelUi.State.PASS -> {
                    canvas6.drawBitmap(
                        scaledBitmap6,
                        passPositionX,
                        transmissionOriginPositionY,
                        paint
                    )
                    transmissionOriginPositionY += 30f
                }
            }
        }

        var coolingOriginPositionY = 1510f
        sections.coolingSystemSteps?.stepEntityModels?.map {
            when (it.stepStateUseCaseModel) {
                PDFModelUi.State.NONE -> { /* nothing to do */
                }
                PDFModelUi.State.COMPLETE -> {
                    canvas5.drawBitmap(scaledBitmap5, checkPositionX, coolingOriginPositionY, paint)
                    coolingOriginPositionY += 30f
                }
                PDFModelUi.State.PASS -> {
                    canvas6.drawBitmap(scaledBitmap6, passPositionX, coolingOriginPositionY, paint)
                    coolingOriginPositionY += 30f
                }
            }
        }

        // after adding all attributes to our
        // PDF file we will be finishing our page.
        pdfDocument.finishPage(myPage)

        // below line is used for setting
        // start page for our PDF file.
        val myPage2: PdfDocument.Page = pdfDocument.startPage(myPageInfo2)

        // creating a variable for canvas
        // from our page of PDF.
        val canvas: Canvas = myPage2.canvas
        val canvas2: Canvas = myPage2.canvas
        val canvas7: Canvas = myPage2.canvas
        val canvas8: Canvas = myPage2.canvas

        // below line is used to draw our image on our PDF file.
        // the first parameter of our drawbitmap method is
        // our bitmap
        // second parameter is position from left
        // third parameter is position from top and last
        // one is our variable for paint.
        canvas2.drawBitmap(scaledBitmap3, 0F, 0F, paint)

        // after adding all attributes to our
        // PDF file we will be finishing our page.

        textPaint.typeface = Typeface.create(Typeface.DEFAULT, Typeface.NORMAL)
        textPaint.textSize = 20F
        textPaint.color = ContextCompat.getColor(context, R.color.black)
        canvas.drawText(currentDate, 830F, 1300F, textPaint)
        canvas.drawText(name, 830F, 1400F, textPaint)

        var engineOriginPositionY = 90f
        sections.engineSteps?.stepEntityModels?.map {
            when (it.stepStateUseCaseModel) {
                PDFModelUi.State.NONE -> { /* nothing to do */
                }
                PDFModelUi.State.COMPLETE -> {
                    canvas7.drawBitmap(scaledBitmap7, checkPositionX, engineOriginPositionY, paint)
                    engineOriginPositionY += 30f
                }
                PDFModelUi.State.PASS -> {
                    canvas8.drawBitmap(scaledBitmap8, passPositionX, engineOriginPositionY, paint)
                    engineOriginPositionY += 30f
                }
            }
        }

        var poweringOriginPositionY = 215f
        sections.poweringSteps?.stepEntityModels?.map {
            when (it.stepStateUseCaseModel) {
                PDFModelUi.State.NONE -> { /* nothing to do */
                }
                PDFModelUi.State.COMPLETE -> {
                    canvas7.drawBitmap(
                        scaledBitmap7,
                        checkPositionX,
                        poweringOriginPositionY,
                        paint
                    )
                    poweringOriginPositionY += 30f
                }
                PDFModelUi.State.PASS -> {
                    canvas8.drawBitmap(scaledBitmap8, passPositionX, poweringOriginPositionY, paint)
                    poweringOriginPositionY += 30f
                }
            }
        }

        var clutchOriginPositionY = 330f
        sections.clutchSteps?.stepEntityModels?.map {
            when (it.stepStateUseCaseModel) {
                PDFModelUi.State.NONE -> { /* nothing to do */
                }
                PDFModelUi.State.COMPLETE -> {
                    canvas7.drawBitmap(scaledBitmap7, checkPositionX, clutchOriginPositionY, paint)
                    clutchOriginPositionY += 30f
                }
                PDFModelUi.State.PASS -> {
                    canvas8.drawBitmap(scaledBitmap8, passPositionX, clutchOriginPositionY, paint)
                    clutchOriginPositionY += 30f
                }
            }
        }

        var othersOriginPositionY = 500f
        sections.othersSteps?.stepEntityModels?.map {
            when (it.stepStateUseCaseModel) {
                PDFModelUi.State.NONE -> { /* nothing to do */
                }
                PDFModelUi.State.COMPLETE -> {
                    canvas7.drawBitmap(scaledBitmap7, checkPositionX, othersOriginPositionY, paint)
                    othersOriginPositionY += 30f
                }
                PDFModelUi.State.PASS -> {
                    canvas8.drawBitmap(scaledBitmap8, passPositionX, othersOriginPositionY, paint)
                    othersOriginPositionY += 30f
                }
            }
        }

        var electricOriginPositionY = 680f
        sections.electricSystemSteps?.stepEntityModels?.map {
            when (it.stepStateUseCaseModel) {
                PDFModelUi.State.NONE -> { /* nothing to do */
                }
                PDFModelUi.State.COMPLETE -> {
                    canvas7.drawBitmap(
                        scaledBitmap7,
                        checkPositionX,
                        electricOriginPositionY,
                        paint
                    )
                    electricOriginPositionY += 31f
                }
                PDFModelUi.State.PASS -> {
                    canvas8.drawBitmap(scaledBitmap8, passPositionX, electricOriginPositionY, paint)
                    electricOriginPositionY += 31f
                }
            }
        }

        var steeringOriginPositionY = 1045f
        sections.steeringSteps?.stepEntityModels?.map {
            when (it.stepStateUseCaseModel) {
                PDFModelUi.State.NONE -> { /* nothing to do */
                }
                PDFModelUi.State.COMPLETE -> {
                    canvas7.drawBitmap(
                        scaledBitmap7,
                        checkPositionX,
                        steeringOriginPositionY,
                        paint
                    )
                    steeringOriginPositionY += 30f
                }
                PDFModelUi.State.PASS -> {
                    canvas8.drawBitmap(scaledBitmap8, passPositionX, steeringOriginPositionY, paint)
                    steeringOriginPositionY += 30f
                }
            }
        }

        // On initialise un position Y (top) d'origine,
        // Et chaque fois que l'on dessine une nouvelle ligne, on ajoute +30


        pdfDocument.finishPage(myPage2)

        // below line is used for setting
        // start page for our PDF file.
        val myPage3: PdfDocument.Page = pdfDocument.startPage(myPageInfo3)

        // creating a variable for canvas
        // from our page of PDF.
        val canvas3: Canvas = myPage3.canvas
        val canvas9: Canvas = myPage3.canvas
        // below line is used to draw our image on our PDF file.
        // the first parameter of our drawbitmap method is
        // our bitmap
        // second parameter is position from left
        // third parameter is position from top and last
        // one is our variable for paint.
        canvas3.drawBitmap(scaledBitmap2, 0F, 0F, paint)
        canvas9.drawText(model, 695F, 195F, textPaint)
        canvas9.drawText(chassis, 725F, 238F, textPaint)

        // after adding all attributes to our
        // PDF file we will be finishing our page.
        pdfDocument.finishPage(myPage3)

        // below line is used to set the name of
        // our PDF file and its path.
        val file = File(
            Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS).absolutePath,
            "N°CHASSIS.pdf"
        )

        try {
            // after creating a file name we will
            // write our PDF file to that location.
            pdfDocument.writeTo(FileOutputStream(file))

            // on below line we are displaying a toast message as PDF file generated..
            Toast.makeText(context, "PDF file generated..", Toast.LENGTH_SHORT).show()
        } catch (e: Exception) {
            // below line is used
            // to handle error
            e.printStackTrace()

            // on below line we are displaying a toast message as fail to generate PDF
            Toast.makeText(context, "Fail to generate PDF file..", Toast.LENGTH_SHORT)
                .show()
        }
        // after storing our pdf to that
        // location we are closing our PDF file.
        pdfDocument.close()
        val uri = FileProvider.getUriForFile(
            context,
            BuildConfig.APPLICATION_ID + ".provider", file
        )
        val sendintent = Intent(Intent.ACTION_SEND)

        sendintent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
        sendintent.putExtra(Intent.EXTRA_SUBJECT, "PDI")
        sendintent.putExtra(Intent.EXTRA_STREAM, uri)
        sendintent.setDataAndType(uri, "application/pdf")

        val openintent = Intent(Intent.ACTION_VIEW)
        openintent.setDataAndType(uri, "application/pdf")
        openintent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)

        val chooserIntent = Intent.createChooser(openintent, "Selectionner l'application")

        chooserIntent.putExtra(Intent.EXTRA_INITIAL_INTENTS, arrayOf(sendintent))

        context.startActivity(chooserIntent)

// a garder ouvrir unique
        //  val uri = FileProvider.getUriForFile(
        //      context,
        //      BuildConfig.APPLICATION_ID + ".provider", file)
        //   val intent = Intent(Intent.ACTION_VIEW).apply {
        //      addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        //      addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
        //     setDataAndType(uri, "application/pdf")


        // }
        //context.startActivity(Intent.createChooser(intent, "Selectionner l'application"))


    }

    private fun checkPermissions(context: Context): Boolean {
        // on below line we are creating a variable for both of our permissions.

        // on below line we are creating a variable for writing to external storage permission
        val writeStoragePermission = ContextCompat.checkSelfPermission(
            context,
            Manifest.permission.WRITE_EXTERNAL_STORAGE
        )

        // on below line we are creating a variable for
        // reading external storage permission
        val readStoragePermission = ContextCompat.checkSelfPermission(
            context,
            Manifest.permission.READ_EXTERNAL_STORAGE
        )

        // on below line we are returning true if both the
        // permissions are granted and returning false if permissions are not granted.
        return writeStoragePermission == PackageManager.PERMISSION_GRANTED && readStoragePermission == PackageManager.PERMISSION_GRANTED
    }

    // on below line we are creating a function to request permission.
    private fun requestPermission(activity: Activity) {

        // on below line we are requesting read and write to
        // storage permission for our application.
        ActivityCompat.requestPermissions(
            activity,
            arrayOf(
                Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.WRITE_EXTERNAL_STORAGE
            ), 101
        )
    }
}
