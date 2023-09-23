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
import com.distribike.features.subfeatures.motorcycleform.model.MotorcycleFormModelUi
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
        private const val PERMISSION_REQUEST_CODE = 101
        private const val FLAG_FILE_TYPE = "application/pdf"

        private const val PERMISSION_GRANTED = "Permissions Granted..."
        private const val PERMISSION_DENIED = "Permissions Denied..."
        private const val TOAST_SUCCESS = "PDF file generated..."
        private const val TOAST_FAILURE = "Fail to generate PDF file..."

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

                )

                {
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

        if (requestCode == PERMISSION_REQUEST_CODE) {
            if (grantResults.isNotEmpty()) {
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED &&
                    grantResults[1] == PackageManager.PERMISSION_GRANTED
                ) {
                    Toast.makeText(this, PERMISSION_GRANTED, Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(this, PERMISSION_DENIED, Toast.LENGTH_SHORT).show()
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
        val motorcycleForm = viewModel.motorcycleFormLiveData.observeAsState(
            MotorcycleFormModelUi(
                username = "",
                codePrep = "",
                model = "",
                chassis = "",
                concessionName = "",
                concessionCode = "",
                positionNumber = ""
            )
        )

        val currentStep = viewModel.stepState.observeAsState(PDFViewModel.StepState.Step1)

        if (checkPermissions(context)) {
            Toast.makeText(context, PERMISSION_GRANTED, Toast.LENGTH_SHORT).show()
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
                    viewModel.setNextStep(step = PDFViewModel.StepState.Step2)


                    generatePDF(
                        context = context,
                        sections = sections.value,
                        motorcycleForm = motorcycleForm
                    )
                    Gdrive(
                        context = context,
                        sections = sections.value,
                        motorcycleForm = motorcycleForm
                    )
                }) {

                Text(
                    modifier = Modifier
                        .padding(10.dp),
                    color = White,
                    fontSize = 22.sp,
                    text = "Etape 1: Envoyer Gdrive"
                )
            }

            Spacer(modifier = Modifier.height(70.dp))

            androidx.compose.material3.Button(
                // on below line we are adding a modifier
                // to it and specifying max width to it.
                colors = ButtonDefaults.buttonColors(containerColor = Blue),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 150.dp),
                enabled = currentStep.value != PDFViewModel.StepState.Step1,
                onClick = {
                    viewModel.setNextStep(step = PDFViewModel.StepState.Step3)

                    Print(
                        context = context,
                        sections = sections.value,
                        motorcycleForm = motorcycleForm
                    )
                }
            ) {
                Text(
                    modifier = Modifier
                        .padding(10.dp),
                    color = White,
                    fontSize = 22.sp,
                    text = "Etape 2: Ouvrir/Imprimer"
                )
            }

            Spacer(modifier = Modifier.height(70.dp))

            androidx.compose.material3.Button(
                // on below line we are adding a modifier
                // to it and specifying max width to it.
                colors = ButtonDefaults.buttonColors(containerColor = RedDark),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 150.dp),
                enabled = currentStep.value == PDFViewModel.StepState.Step3,
                onClick = {
                    viewModel.clearEntities()
                    viewModel.clearChassis()
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

    private fun generatePDF(
        context: Context,
        sections: PDFModelUi,
        motorcycleForm: State<MotorcycleFormModelUi>
    ) {
        // Initialize MotorcycleForm Information
        val pageHeight = 1680
        val pageWidth = 1188
        val dataFormat = SimpleDateFormat("dd/MM/yyyy hh:mm:ss", Locale.FRANCE)
        val currentDate = dataFormat.format(Date())
        val name = motorcycleForm.value.username
        val codePrep = motorcycleForm.value.codePrep
        val model = motorcycleForm.value.model
        val chassis = motorcycleForm.value.chassis
        val concessionName = motorcycleForm.value.concessionName
        val concessionCode = motorcycleForm.value.concessionCode
        val position = motorcycleForm.value.positionNumber
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
        // paint is for drawing shapes (like background)
        // textPaint is for adding text in our PDF file.
        val imagePaint = Paint()
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


        // Add page info to PDF file with
        // pageWidth, pageHeight and number of pages
        // Then create PDF file.
        val myPageInfo: PdfDocument.PageInfo? =
            PdfDocument.PageInfo.Builder(pageWidth, pageHeight, 1).create()

        val myPageInfo2: PdfDocument.PageInfo? =
            PdfDocument.PageInfo.Builder(pageWidth, pageHeight, 2).create()

        val myPageInfo3: PdfDocument.PageInfo? =
            PdfDocument.PageInfo.Builder(pageWidth, pageHeight, 3).create()

        // Start page for PDF file.
        val myPage: PdfDocument.Page = pdfDocument.startPage(myPageInfo)

        // Create variable for canvas from PDF page.
        val canvas1: Canvas = myPage.canvas
        val canvas4: Canvas = myPage.canvas
        val canvas5: Canvas = myPage.canvas
        val canvas6: Canvas = myPage.canvas

        // Draw image on PDF file.
        canvas4.drawBitmap(scaledBitmap4, 0F, 0F, imagePaint)

        // Login & MotorcycleForm information
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
        canvas1.drawText(
            sections.transmissionSteps?.stepEntityModels?.get(1)?.additionalInfo + " mm",
            370F,
            1393F,
            textPaint
        )
        canvas1.drawText(
            sections.transmissionSteps?.stepEntityModels?.get(3)?.additionalInfo ?: "",
            750F,
            1462F,
            textPaint
        )
        canvas1.drawText(
            sections.transmissionSteps?.stepEntityModels?.get(3)?.additionalInfo2 ?: "",
            900F,
            1462F,
            textPaint
        )

        // GLOBAL POSITION
        // Normalement ces positions ne devraient jamais changer,
        // C'est toujours la même colonne.
        val checkPositionX = 70f
        val passPositionX = 110f

        // GENERAL
        // On initialise un position Y (top) d'origine,
        // Et chaque fois que l'on dessine une nouvelle ligne, on ajoute +30
        var generalOriginPositionY = 450f
        sections.generalSteps?.stepModelUis?.forEach {
            when (it.stepStateUseCaseModel) {
                PDFModelUi.State.NONE -> { /* nothing to do */
                }
                PDFModelUi.State.COMPLETE -> {
                    canvas5.drawBitmap(
                        scaledBitmap5,
                        checkPositionX,
                        generalOriginPositionY,
                        imagePaint
                    )
                    generalOriginPositionY += 30f
                }
                PDFModelUi.State.PASS -> {
                    canvas6.drawBitmap(
                        scaledBitmap6,
                        passPositionX,
                        generalOriginPositionY,
                        imagePaint
                    )
                    generalOriginPositionY += 30f
                }
            }
        }

        var batteryOriginPositionY = 720f
        sections.batterySteps?.stepModelUis?.map {
            when (it.stepStateUseCaseModel) {
                PDFModelUi.State.NONE -> { /* nothing to do */
                }
                PDFModelUi.State.COMPLETE -> {
                    canvas5.drawBitmap(
                        scaledBitmap5,
                        checkPositionX,
                        batteryOriginPositionY,
                        imagePaint
                    )
                    batteryOriginPositionY += 30f
                }
                PDFModelUi.State.PASS -> {
                    canvas6.drawBitmap(
                        scaledBitmap6,
                        passPositionX,
                        batteryOriginPositionY,
                        imagePaint
                    )
                    batteryOriginPositionY += 30f
                }
            }
        }

        var wheelsOriginPositionY = 900f
        sections.wheelsAndTiresSteps?.stepEntityModels?.map {
            when (it.additionalInfo) {
                null -> { /* do nothing */
                }
                else -> canvas1.drawText(it.additionalInfo, 782F, 955F, textPaint)
            }
            when (it.additionalInfo2) {
                null -> { /* do nothing */
                }
                else -> canvas1.drawText(it.additionalInfo2, 925F, 955F, textPaint)
            }
            when (it.stepStateUseCaseModel) {
                PDFModelUi.State.NONE -> { /* nothing to do */
                }
                PDFModelUi.State.COMPLETE -> {
                    canvas5.drawBitmap(
                        scaledBitmap5,
                        checkPositionX,
                        wheelsOriginPositionY,
                        imagePaint
                    )
                    wheelsOriginPositionY += 30f
                }
                PDFModelUi.State.PASS -> {
                    canvas6.drawBitmap(
                        scaledBitmap6,
                        passPositionX,
                        wheelsOriginPositionY,
                        imagePaint
                    )
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
                    canvas5.drawBitmap(
                        scaledBitmap5,
                        checkPositionX,
                        breaksOriginPositionY,
                        imagePaint
                    )
                    breaksOriginPositionY += 30f
                }
                PDFModelUi.State.PASS -> {
                    canvas6.drawBitmap(
                        scaledBitmap6,
                        passPositionX,
                        breaksOriginPositionY,
                        imagePaint
                    )
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
                        imagePaint
                    )
                    suspensionsOriginPositionY += 30f
                }
                PDFModelUi.State.PASS -> {
                    canvas6.drawBitmap(
                        scaledBitmap6,
                        passPositionX,
                        suspensionsOriginPositionY,
                        imagePaint
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
                        imagePaint
                    )
                    transmissionOriginPositionY += 30f
                }
                PDFModelUi.State.PASS -> {
                    canvas6.drawBitmap(
                        scaledBitmap6,
                        passPositionX,
                        transmissionOriginPositionY,
                        imagePaint
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
                    canvas5.drawBitmap(
                        scaledBitmap5,
                        checkPositionX,
                        coolingOriginPositionY,
                        imagePaint
                    )
                    coolingOriginPositionY += 30f
                }
                PDFModelUi.State.PASS -> {
                    canvas6.drawBitmap(
                        scaledBitmap6,
                        passPositionX,
                        coolingOriginPositionY,
                        imagePaint
                    )
                    coolingOriginPositionY += 30f
                }
            }
        }

        // After adding all attributes to PDF file, finish page.
        pdfDocument.finishPage(myPage)

        // Start second page for PDF file.
        val myPage2: PdfDocument.Page = pdfDocument.startPage(myPageInfo2)
        val canvas: Canvas = myPage2.canvas
        val canvas2: Canvas = myPage2.canvas
        val canvas7: Canvas = myPage2.canvas
        val canvas8: Canvas = myPage2.canvas

        // Draw image on PDF file.
        canvas2.drawBitmap(scaledBitmap3, 0F, 0F, imagePaint)
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
                    canvas7.drawBitmap(
                        scaledBitmap7,
                        checkPositionX,
                        engineOriginPositionY,
                        imagePaint
                    )
                    engineOriginPositionY += 30f
                }
                PDFModelUi.State.PASS -> {
                    canvas8.drawBitmap(
                        scaledBitmap8,
                        passPositionX,
                        engineOriginPositionY,
                        imagePaint
                    )
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
                        imagePaint
                    )
                    poweringOriginPositionY += 30f
                }
                PDFModelUi.State.PASS -> {
                    canvas8.drawBitmap(
                        scaledBitmap8,
                        passPositionX,
                        poweringOriginPositionY,
                        imagePaint
                    )
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
                    canvas7.drawBitmap(
                        scaledBitmap7,
                        checkPositionX,
                        clutchOriginPositionY,
                        imagePaint
                    )
                    clutchOriginPositionY += 30f
                }
                PDFModelUi.State.PASS -> {
                    canvas8.drawBitmap(
                        scaledBitmap8,
                        passPositionX,
                        clutchOriginPositionY,
                        imagePaint
                    )
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
                    canvas7.drawBitmap(
                        scaledBitmap7,
                        checkPositionX,
                        othersOriginPositionY,
                        imagePaint
                    )
                    othersOriginPositionY += 30f
                }
                PDFModelUi.State.PASS -> {
                    canvas8.drawBitmap(
                        scaledBitmap8,
                        passPositionX,
                        othersOriginPositionY,
                        imagePaint
                    )
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
                        imagePaint
                    )
                    electricOriginPositionY += 31f
                }
                PDFModelUi.State.PASS -> {
                    canvas8.drawBitmap(
                        scaledBitmap8,
                        passPositionX,
                        electricOriginPositionY,
                        imagePaint
                    )
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
                        imagePaint
                    )
                    steeringOriginPositionY += 30f
                }
                PDFModelUi.State.PASS -> {
                    canvas8.drawBitmap(
                        scaledBitmap8,
                        passPositionX,
                        steeringOriginPositionY,
                        imagePaint
                    )
                    steeringOriginPositionY += 30f
                }
            }
        }

        pdfDocument.finishPage(myPage2)

        // Start page 3 for our PDF file.
        val myPage3: PdfDocument.Page = pdfDocument.startPage(myPageInfo3)
        // Creating a variable for canvas from our page of PDF.
        val canvas3: Canvas = myPage3.canvas
        val canvas9: Canvas = myPage3.canvas
        // Draw our image on our PDF file.
        canvas3.drawBitmap(scaledBitmap2, 0F, 0F, imagePaint)
        canvas9.drawText(model, 695F, 195F, textPaint)
        canvas9.drawText(chassis, 725F, 238F, textPaint)

        // After adding all attributes, we will be finishing our page.
        pdfDocument.finishPage(myPage3)

        // Set the name of our PDF file and its path.
        val file = File(
            Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS).absolutePath,
            "$chassis $position.pdf"
        )
        //)

        // To test on EMULATOR
        // val file = File(Environment.getExternalStorageDirectory(), "$chassis.pdf")


        try {
            // Write PDF file to that location.
            pdfDocument.writeTo(FileOutputStream(file))
            Toast.makeText(context, TOAST_SUCCESS, Toast.LENGTH_SHORT).show()
        } catch (e: Exception) {
            e.printStackTrace()
            Toast.makeText(context, TOAST_FAILURE, Toast.LENGTH_SHORT).show()
        }
        // Close PDF file.
        pdfDocument.close()
        // val uri = FileProvider.getUriForFile(
        //     context,
        //      BuildConfig.APPLICATION_ID + ".provider", file
        //  )
        // Send function
        //   val sendIntent = Intent(Intent.ACTION_SEND)
        //  sendIntent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
        //   sendIntent.putExtra(Intent.EXTRA_SUBJECT, "$chassis $position.pdf")
        //   sendIntent.putExtra(Intent.EXTRA_STREAM, uri)
        //    sendIntent.setDataAndType(uri, FLAG_FILE_TYPE)

        // Open function
        //   val openIntent = Intent(Intent.ACTION_VIEW)
        //   openIntent.setDataAndType(uri, FLAG_FILE_TYPE)
        //   openIntent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)

        // Pick function
        //   val chooserIntent = Intent.createChooser(openIntent, "Selectionner l'application")
        //   chooserIntent.putExtra(Intent.EXTRA_INITIAL_INTENTS, arrayOf(sendIntent))
        //   context.startActivity(chooserIntent)

        // A garder ouvrir unique
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
        val writeStoragePermission = ContextCompat.checkSelfPermission(
            context,
            Manifest.permission.WRITE_EXTERNAL_STORAGE
        )

        val readStoragePermission = ContextCompat.checkSelfPermission(
            context,
            Manifest.permission.READ_EXTERNAL_STORAGE
        )

        return writeStoragePermission == PackageManager.PERMISSION_GRANTED &&
                readStoragePermission == PackageManager.PERMISSION_GRANTED
    }

    private fun requestPermission(activity: Activity) {
        ActivityCompat.requestPermissions(
            activity,
            arrayOf(
                Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.WRITE_EXTERNAL_STORAGE
            ), PERMISSION_REQUEST_CODE
        )
    }

    private fun Gdrive(
        context: Context,
        sections: PDFModelUi,
        motorcycleForm: State<MotorcycleFormModelUi>
    ) {
        val chassis = motorcycleForm.value.chassis
        val position = motorcycleForm.value.positionNumber
        val file = File(
            Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS).absolutePath,
            "$chassis $position.pdf"
        )
        val uri = FileProvider.getUriForFile(
            context,
            BuildConfig.APPLICATION_ID + ".provider", file
        )
        val intent = Intent(Intent.ACTION_SEND).apply {
            addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
            putExtra(Intent.EXTRA_SUBJECT, "$chassis $position.pdf")
            putExtra(Intent.EXTRA_STREAM, uri)
            setDataAndType(uri, FLAG_FILE_TYPE)
        }
        context.startActivity(Intent.createChooser(intent, "Selectionner l'application"))
    }

    private fun Print(
        context: Context,
        sections: PDFModelUi,
        motorcycleForm: State<MotorcycleFormModelUi>
    ) {
        val chassis = motorcycleForm.value.chassis
        val position = motorcycleForm.value.positionNumber
        val file = File(
            Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS).absolutePath,
            "$chassis $position.pdf"
        )
        val uri = FileProvider.getUriForFile(
            context,
            BuildConfig.APPLICATION_ID + ".provider", file
        )
        val intent = Intent(Intent.ACTION_VIEW).apply {
            addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
            setDataAndType(uri, "application/pdf")
        }
        context.startActivity(Intent.createChooser(intent, "Selectionner l'application"))
    }
}
