package com.distribike.features.subfeatures.pdf


import android.Manifest
import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.*
import android.graphics.pdf.PdfDocument
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.os.Environment
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material.ButtonColors
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.layout.ContentScale
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
import com.distribike.features.subfeatures.form.main.forms.wheelsform.WheelsFormActivity
import com.distribike.features.subfeatures.motorcycleform.MotorcycleFormActivity
import com.distribike.features.subfeatures.pdf.model.PDFModelUi
import com.distribike.features.subfeatures.pdf.viewmodel.PDFViewModel
import com.distribike.ui.theme.*
import dagger.hilt.android.AndroidEntryPoint
import java.io.File
import java.io.FileInputStream
import java.io.FileOutputStream

@Suppress("DEPRECATION")
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
                // on below line we are specifying
               // modifier and color for our app
                modifier = Modifier.fillMaxSize(),
                color = MaterialTheme.colors.background

            )


            {



                // on below line we are specifying theme as scaffold.
                Scaffold(

                    // in scaffold we are specifying top bar.
                    topBar = {

                        // inside top bar we are specifying background color.
                        TopAppBar(backgroundColor = Green,

                            // along with that we are specifying
                            // title for our top bar.
                            title = {

                                // in the top bar we are specifying tile as a text
                                Text(

                                    // on below line we are specifying
                                    // text to display in top app bar.
                                    text = "PDF",

                                    // on below line we are specifying
                                    // modifier to fill max width.
                                    modifier = Modifier.fillMaxWidth(),

                                    // on below line we are
                                    // specifying text alignment.
                                    textAlign = TextAlign.Center,

                                    // on below line we are
                                    // specifying color for our text.
                                    color = Color.White
                                )
                            }
                        )
                    }
                ) {

                    // on below line we are calling pdf generator
                    // method for generating a pdf file.
                    PDFGenerator()
                }
            }
        }
    }

    // on below line we are calling on
// request permission result method.
    @Deprecated("Deprecated in Java")
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        // on below line we are checking if the
        // request code is equal to permission code.
        if (requestCode == 101) {

            // on below line we are checking if result size is > 0
            if (grantResults.size > 0) {

                // on below line we are checking if both the permissions are granted.
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED && grantResults[1] == PackageManager.PERMISSION_GRANTED) {

                    // if permissions are granted we are displaying a toast message.
                    Toast.makeText(this, "Permission Granted..", Toast.LENGTH_SHORT).show()

                } else {

                    // if permissions are not granted we are
                    // displaying a toast message as permission denied.
                    Toast.makeText(this, "Permission Denied..", Toast.LENGTH_SHORT).show()
                    finish()
                }
            }
        }
    }

    // on below line we are creating a
    // pdf generator composable function for ui.
    @Composable
    fun PDFGenerator() {
        // on below line we are creating a variable for
        // our context and activity and initializing it.
        val context = LocalContext.current
        val activity = (LocalContext.current as? Activity)

        val viewModel = hiltViewModel<PDFViewModel>()

        val sections = viewModel.formRecordLiveData.observeAsState(PDFModelUi())

        // on below line we are checking permission
        if (checkPermissions(context)) {
            // if permission is granted we are displaying a toast message.
            Toast.makeText(context, "Permissions Granted..", Toast.LENGTH_SHORT).show()
        } else {
            // if the permission is not granted
            // we are calling request permission method.
            requestPermission(activity!!)
        }

        // on below line we are creating a column for our ui.
        Column(


            // in this column we are adding a modifier for our
            // column and specifying max width, height and size.
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .fillMaxSize()

                // on below line we are adding padding
                // from all sides to our column.
                .padding(6.dp),

            // on below line we are adding vertical
            // arrangement for our column as center
         //   verticalArrangement = Arrangement.Center,

            // on below line we are adding
            // horizontal alignment for our column.
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Spacer(modifier = Modifier.height(50.dp))

            Text(
                // on below line we are setting text to our text
                text = "Validation du PDI",

                // on below line we are
                // setting color for our text
                color = Green,

                // on below line we are setting
                // font weight for our text
                fontWeight = FontWeight.Bold,

                // on below line we are setting
                // alignment for our text as center.
                textAlign = TextAlign.Center,

                // on below line we are
                // setting font size for our text
                fontSize = 40.sp
            )

            Image(
                painter = painterResource(id = R.drawable.login_image5),
                contentDescription = null,
                modifier = Modifier
                    .height(540.dp)
                    .width(580.dp)

            )




            // on below line we are adding
            // spacer between text and a button.
            Spacer(modifier = Modifier.height(50.dp))

            // on the below line we are creating a button.
            androidx.compose.material3.Button(
                // on below line we are adding a modifier
                // to it and specifying max width to it.
                colors = ButtonDefaults.buttonColors(containerColor = Green),
                modifier = Modifier
                    .fillMaxWidth()

                    // on below line we are adding
                    // padding for our button.
                    .padding(horizontal = 150.dp),

                // on below line we are adding
                // on click for our button.
                onClick = {



                    // inside on click we are calling our
                    // generate PDF method to generate our PDF
                    if (sections != null) {
                        generatePDF(
                            context = context,
                            sections = sections.value
                        )
                    }
                }) {

                // on the below line we are displaying a text for our button.
                Text(modifier = Modifier
                    .padding(10.dp),
                    color = White,
                    fontSize = 22.sp,
                    text = "Ouvrir/Envoyer/Imprimer PDF")
            }
            // on below line we are adding
            // spacer between text and a button.

            Spacer(modifier = Modifier.height(80.dp))


            androidx.compose.material3.Button(
                // on below line we are adding a modifier
                // to it and specifying max width to it.
                colors = ButtonDefaults.buttonColors(containerColor = RedDark),
                modifier = Modifier
                    .fillMaxWidth()

                    // on below line we are adding
                    // padding for our button.
                    .padding(horizontal = 150.dp),

                // on below line we are adding
                // on click for our button.
                onClick = {
                    finish()
                    startActivity(MotorcycleFormActivity.newInstance(context = applicationContext))
                }

            )

            {

                // on the below line we are displaying a text for our button.
                Text(modifier = Modifier
                    .padding(10.dp),
                    color = White,
                    fontSize = 22.sp,
                    text = "Commencer un nouveau châssis")


            }
        }

    }

    // on below line we are creating a generate PDF
// method which is use to generate our PDF file.
    @RequiresApi(Build.VERSION_CODES.KITKAT)
    fun generatePDF(context: Context, sections: PDFModelUi) {

        // declaring width and height
        // for our PDF file.
        var pageHeight = 1680
        var pageWidth = 1188

        // creating a bitmap variable
        // for storing our images
        //lateinit var bmp: Bitmap
        //lateinit var scaledbmp: Bitmap

        lateinit var bmp2: Bitmap
        lateinit var scaledbmp2: Bitmap

        lateinit var bmp3: Bitmap
        lateinit var scaledbmp3: Bitmap

        lateinit var bmp4: Bitmap
        lateinit var scaledbmp4: Bitmap

        lateinit var bmp5: Bitmap
        lateinit var scaledbmp5: Bitmap

        lateinit var bmp6: Bitmap
        lateinit var scaledbmp6: Bitmap

        lateinit var bmp7: Bitmap
        lateinit var scaledbmp7: Bitmap

        lateinit var bmp8: Bitmap
        lateinit var scaledbmp8: Bitmap

        // creating an object variable
        // for our PDF document.
        var pdfDocument: PdfDocument = PdfDocument()

        // two variables for paint "paint" is used
        // for drawing shapes and we will use "title"
        // for adding text in our PDF file.
        var paint: Paint = Paint()
        var title: Paint = Paint()

        // on below line we are initializing our bitmap and scaled bitmap.
        //bmp = BitmapFactory.decodeResource(context.resources, R.drawable.honda1)
        //scaledbmp = Bitmap.createScaledBitmap(bmp, 90, 90, false)

        bmp2 = BitmapFactory.decodeResource(context.resources, R.drawable.page3)
        scaledbmp2 = Bitmap.createScaledBitmap(bmp2, 1188, 1680, false)

        bmp3 = BitmapFactory.decodeResource(context.resources, R.drawable.page2)
        scaledbmp3 = Bitmap.createScaledBitmap(bmp3, 1188, 1680, false)

        bmp4 = BitmapFactory.decodeResource(context.resources, R.drawable.page1)
        scaledbmp4 = Bitmap.createScaledBitmap(bmp4, 1188, 1680, false)

        bmp5 = BitmapFactory.decodeResource(context.resources, R.drawable.check)
        scaledbmp5 = Bitmap.createScaledBitmap(bmp5, 30, 28, false)

        bmp6 = BitmapFactory.decodeResource(context.resources, R.drawable.pass)
        scaledbmp6 = Bitmap.createScaledBitmap(bmp6, 30, 28, false)

        bmp7 = BitmapFactory.decodeResource(context.resources, R.drawable.check)
        scaledbmp7 = Bitmap.createScaledBitmap(bmp7, 30, 28, false)

        bmp8 = BitmapFactory.decodeResource(context.resources, R.drawable.pass)
        scaledbmp8 = Bitmap.createScaledBitmap(bmp8, 30, 28, false)


        // we are adding page info to our PDF file
        // in which we will be passing our pageWidth,
        // pageHeight and number of pages and after that
        // we are calling it to create our PDF.
        var myPageInfo: PdfDocument.PageInfo? =
            PdfDocument.PageInfo.Builder(pageWidth, pageHeight, 1).create()

        var myPageInfo2: PdfDocument.PageInfo? =
            PdfDocument.PageInfo.Builder(pageWidth, pageHeight, 2).create()

        var myPageInfo3: PdfDocument.PageInfo? =
            PdfDocument.PageInfo.Builder(pageWidth, pageHeight, 3).create()
        // below line is used for setting
        // start page for our PDF file.
        var myPage: PdfDocument.Page = pdfDocument.startPage(myPageInfo)

        // creating a variable for canvas
        // from our page of PDF.

        var canvas4: Canvas = myPage.canvas
        var canvas5: Canvas = myPage.canvas
        var canvas6: Canvas = myPage.canvas

        // below line is used to draw our image on our PDF file.
        // the first parameter of our drawbitmap method is
        // our bitmap
        // second parameter is position from left
        // third parameter is position from top and last
        // one is our variable for paint.

        canvas4.drawBitmap(scaledbmp4, 0F, 0F, paint)

        //GENERAL
        //canvas5.drawBitmap(scaledbmp5, 100F, 450F, paint)


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
                PDFModelUi.State.NONE -> { /* nothing to do */ }
                PDFModelUi.State.COMPLETE -> {
                    canvas5.drawBitmap(scaledbmp5, checkPositionX, generalOriginPostionY, paint)
                    generalOriginPostionY += 30f
                }
                PDFModelUi.State.PASS -> {
                    canvas6.drawBitmap(scaledbmp6, passPositionX, generalOriginPostionY, paint)
                    generalOriginPostionY += 30f
                }
            }
        }

        var batteryOriginPositionY = 720f
        sections.batterySteps?.stepModelUis?.map {
            when (it.stepStateUseCaseModel) {
                PDFModelUi.State.NONE -> { /* nothing to do */ }
                PDFModelUi.State.COMPLETE -> {
                    canvas5.drawBitmap(scaledbmp5, checkPositionX, batteryOriginPositionY, paint)
                    batteryOriginPositionY += 30f
                }
                PDFModelUi.State.PASS -> {
                    canvas6.drawBitmap(scaledbmp6, passPositionX, batteryOriginPositionY, paint)
                    batteryOriginPositionY += 30f
                }
            }
        }

        var wheelsOriginPositionY = 900f
        sections.wheelsAndTiresSteps?.stepEntityModels?.map {
            when (it.stepStateUseCaseModel) {
                PDFModelUi.State.NONE -> { /* nothing to do */ }
                PDFModelUi.State.COMPLETE -> {
                    canvas5.drawBitmap(scaledbmp5, checkPositionX, wheelsOriginPositionY, paint)
                    wheelsOriginPositionY += 30f
                }
                PDFModelUi.State.PASS -> {
                    canvas6.drawBitmap(scaledbmp6, passPositionX, wheelsOriginPositionY, paint)
                    wheelsOriginPositionY += 30f
                }
            }
        }

        var breaksOriginPositionY = 1085f
        sections.breaksSteps?.stepEntityModels?.map {
            when (it.stepStateUseCaseModel) {
                PDFModelUi.State.NONE -> { /* nothing to do */ }
                PDFModelUi.State.COMPLETE -> {
                    canvas5.drawBitmap(scaledbmp5, checkPositionX, breaksOriginPositionY, paint)
                    breaksOriginPositionY += 30f
                }
                PDFModelUi.State.PASS -> {
                    canvas6.drawBitmap(scaledbmp6, passPositionX, breaksOriginPositionY, paint)
                    breaksOriginPositionY += 30f
                }
            }
        }

        var suspensionsOriginPositionY = 1255f
        sections.suspensionSteps?.stepEntityModels?.map {
            when (it.stepStateUseCaseModel) {
                PDFModelUi.State.NONE -> { /* nothing to do */ }
                PDFModelUi.State.COMPLETE -> {
                    canvas5.drawBitmap(scaledbmp5, checkPositionX, suspensionsOriginPositionY, paint)
                    suspensionsOriginPositionY += 30f
                }
                PDFModelUi.State.PASS -> {
                    canvas6.drawBitmap(scaledbmp6, passPositionX, suspensionsOriginPositionY, paint)
                    suspensionsOriginPositionY += 30f
                }
            }
        }

        var transmissionOriginPositionY = 1340f
        sections.transmissionSteps?.stepEntityModels?.map {
            when (it.stepStateUseCaseModel) {
                PDFModelUi.State.NONE -> { /* nothing to do */ }
                PDFModelUi.State.COMPLETE -> {
                    canvas5.drawBitmap(scaledbmp5, checkPositionX, transmissionOriginPositionY, paint)
                    transmissionOriginPositionY += 30f
                }
                PDFModelUi.State.PASS -> {
                    canvas6.drawBitmap(scaledbmp6, passPositionX, transmissionOriginPositionY, paint)
                    transmissionOriginPositionY += 30f
                }
            }
        }

        var coolingOriginPositionY = 1510f
        sections.coolingSystemSteps?.stepEntityModels?.map {
            when (it.stepStateUseCaseModel) {
                PDFModelUi.State.NONE -> { /* nothing to do */ }
                PDFModelUi.State.COMPLETE -> {
                    canvas5.drawBitmap(scaledbmp5, checkPositionX, coolingOriginPositionY, paint)
                    coolingOriginPositionY += 30f
                }
                PDFModelUi.State.PASS -> {
                    canvas6.drawBitmap(scaledbmp6, passPositionX, coolingOriginPositionY, paint)
                    coolingOriginPositionY += 30f
                }
            }
        }

        // TODO: et ainsi de suite pour toutes les sections présentes dans [sections...]




        // after adding all attributes to our
        // PDF file we will be finishing our page.
        pdfDocument.finishPage(myPage)

        // below line is used for setting
        // start page for our PDF file.
        var myPage2: PdfDocument.Page = pdfDocument.startPage(myPageInfo2)

        // creating a variable for canvas
        // from our page of PDF.
        var canvas2: Canvas = myPage2.canvas
        var canvas7: Canvas = myPage2.canvas
        var canvas8: Canvas = myPage2.canvas

        // below line is used to draw our image on our PDF file.
        // the first parameter of our drawbitmap method is
        // our bitmap
        // second parameter is position from left
        // third parameter is position from top and last
        // one is our variable for paint.
        canvas2.drawBitmap(scaledbmp3, 0F, 0F, paint)

        // after adding all attributes to our
        // PDF file we will be finishing our page.

        var engineOriginPositionY = 90f
        sections.engineSteps?.stepEntityModels?.map {
            when (it.stepStateUseCaseModel) {
                PDFModelUi.State.NONE -> { /* nothing to do */ }
                PDFModelUi.State.COMPLETE -> {
                    canvas7.drawBitmap(scaledbmp7, checkPositionX, engineOriginPositionY, paint)
                    engineOriginPositionY += 30f
                }
                PDFModelUi.State.PASS -> {
                    canvas8.drawBitmap(scaledbmp8, passPositionX, engineOriginPositionY, paint)
                    engineOriginPositionY += 30f
                }
            }
        }

        var poweringOriginPositionY = 215f
        sections.poweringSteps?.stepEntityModels?.map {
            when (it.stepStateUseCaseModel) {
                PDFModelUi.State.NONE -> { /* nothing to do */ }
                PDFModelUi.State.COMPLETE -> {
                    canvas7.drawBitmap(scaledbmp7, checkPositionX, poweringOriginPositionY, paint)
                    poweringOriginPositionY += 30f
                }
                PDFModelUi.State.PASS -> {
                    canvas8.drawBitmap(scaledbmp8, passPositionX, poweringOriginPositionY, paint)
                    poweringOriginPositionY += 30f
                }
            }
        }

        var clutchOriginPositionY = 330f
        sections.clutchSteps?.stepEntityModels?.map {
            when (it.stepStateUseCaseModel) {
                PDFModelUi.State.NONE -> { /* nothing to do */ }
                PDFModelUi.State.COMPLETE -> {
                    canvas7.drawBitmap(scaledbmp7, checkPositionX, clutchOriginPositionY, paint)
                    clutchOriginPositionY += 30f
                }
                PDFModelUi.State.PASS -> {
                    canvas8.drawBitmap(scaledbmp8, passPositionX, clutchOriginPositionY, paint)
                    clutchOriginPositionY += 30f
                }
            }
        }

        var othersOriginPositionY = 500f
        sections.othersSteps?.stepEntityModels?.map {
            when (it.stepStateUseCaseModel) {
                PDFModelUi.State.NONE -> { /* nothing to do */ }
                PDFModelUi.State.COMPLETE -> {
                    canvas7.drawBitmap(scaledbmp7, checkPositionX, othersOriginPositionY, paint)
                    othersOriginPositionY += 30f
                }
                PDFModelUi.State.PASS -> {
                    canvas8.drawBitmap(scaledbmp8, passPositionX, othersOriginPositionY, paint)
                    othersOriginPositionY += 30f
                }
            }
        }

        var electricOriginPositionY = 680f
        sections.electricSystemSteps?.stepEntityModels?.map {
            when (it.stepStateUseCaseModel) {
                PDFModelUi.State.NONE -> { /* nothing to do */ }
                PDFModelUi.State.COMPLETE -> {
                    canvas7.drawBitmap(scaledbmp7, checkPositionX, electricOriginPositionY, paint)
                    electricOriginPositionY += 31f
                }
                PDFModelUi.State.PASS -> {
                    canvas8.drawBitmap(scaledbmp8, passPositionX, electricOriginPositionY, paint)
                    electricOriginPositionY += 31f
                }
            }
        }

        var steeringOriginPositionY = 1045f
        sections.steeringSteps?.stepEntityModels?.map {
            when (it.stepStateUseCaseModel) {
                PDFModelUi.State.NONE -> { /* nothing to do */ }
                PDFModelUi.State.COMPLETE -> {
                    canvas7.drawBitmap(scaledbmp7, checkPositionX, steeringOriginPositionY, paint)
                    steeringOriginPositionY += 30f
                }
                PDFModelUi.State.PASS -> {
                    canvas8.drawBitmap(scaledbmp8, passPositionX, steeringOriginPositionY, paint)
                    steeringOriginPositionY += 30f
                }
            }
        }

        // On initialise un position Y (top) d'origine,
        // Et chaque fois que l'on dessine une nouvelle ligne, on ajoute +30



        pdfDocument.finishPage(myPage2)

        // below line is used for setting
        // start page for our PDF file.
        var myPage3: PdfDocument.Page = pdfDocument.startPage(myPageInfo3)

        // creating a variable for canvas
        // from our page of PDF.
        var canvas3: Canvas = myPage3.canvas

        // below line is used to draw our image on our PDF file.
        // the first parameter of our drawbitmap method is
        // our bitmap
        // second parameter is position from left
        // third parameter is position from top and last
        // one is our variable for paint.
        canvas3.drawBitmap(scaledbmp2, 0F, 0F, paint)

        // after adding all attributes to our
        // PDF file we will be finishing our page.
        pdfDocument.finishPage(myPage3)

        // below line is used to set the name of
        // our PDF file and its path.
        val file: File = File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS).absolutePath, "N°CHASSIS.pdf")

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
            BuildConfig.APPLICATION_ID + ".provider", file)
        val sendintent = Intent(Intent.ACTION_SEND)

            sendintent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
            sendintent.putExtra(Intent.EXTRA_SUBJECT, "PDI")
            sendintent.putExtra(Intent.EXTRA_STREAM, uri)
            sendintent.setDataAndType(uri, "application/pdf")

        val openintent = Intent(Intent.ACTION_VIEW)
        openintent.setDataAndType(uri, "application/pdf")
        openintent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)

        val chooserIntent = Intent.createChooser(openintent,"Selectionner l'application")

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

    fun checkPermissions(context: Context): Boolean {
        // on below line we are creating a variable for both of our permissions.

        // on below line we are creating a variable for writing to external storage permission
        var writeStoragePermission = ContextCompat.checkSelfPermission(
            context,
            Manifest.permission.WRITE_EXTERNAL_STORAGE
        )

        // on below line we are creating a variable for
        // reading external storage permission
        var readStoragePermission = ContextCompat.checkSelfPermission(
            context,
            Manifest.permission.READ_EXTERNAL_STORAGE
        )

        // on below line we are returning true if both the
        // permissions are granted and returning false if permissions are not granted.
        return writeStoragePermission == PackageManager.PERMISSION_GRANTED && readStoragePermission == PackageManager.PERMISSION_GRANTED
    }

    // on below line we are creating a function to request permission.
    fun requestPermission(activity: Activity) {

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
