package com.distribike.features.subfeatures.pdf


import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.*
import android.graphics.pdf.PdfDocument
import android.os.Build
import android.os.Bundle
import android.os.Environment
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.*
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.distribike.R
import com.distribike.ui.theme.*
import java.io.File
import java.io.FileOutputStream

@Suppress("DEPRECATION")
class PDFActivity : ComponentActivity() {

    companion object {
        fun newInstance(context: Context) = Intent(context, PDFActivity::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Surface(
                // on below line we are specifying
                // modifier and color for our app
                modifier = Modifier.fillMaxSize(),
                color = MaterialTheme.colors.background
            ) {

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
}

// on below line we are creating a
// pdf generator composable function for ui.
@Composable
fun PDFGenerator() {

    // on below line we are creating a variable for
    // our context and activity and initializing it.
    val ctx = LocalContext.current
    val activity = (LocalContext.current as? Activity)

    // on below line we are checking permission
    if (checkPermissions(ctx)) {
        // if permission is granted we are displaying a toast message.
        Toast.makeText(ctx, "Permissions Granted..", Toast.LENGTH_SHORT).show()
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
        verticalArrangement = Arrangement.Center,

        // on below line we are adding
        // horizontal alignment for our column.
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        // on below line we are creating a simple text as a PDF Generator.
        Text(
            // on below line we are setting text to our text
            text = "Générateur PDF",

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
            fontSize = 20.sp
        )

        // on below line we are adding
        // spacer between text and a button.
        Spacer(modifier = Modifier.height(60.dp))

        // on the below line we are creating a button.
        Button(
            // on below line we are adding a modifier
            // to it and specifying max width to it.
            modifier = Modifier
                .fillMaxWidth()

                // on below line we are adding
                // padding for our button.
                .padding(20.dp),

            // on below line we are adding
            // on click for our button.
            onClick = {

                // inside on click we are calling our
                // generate PDF method to generate our PDF
                generatePDF(ctx)
            }) {

            // on the below line we are displaying a text for our button.
            Text(modifier = Modifier.padding(6.dp), text = "Ouvrir PDF")
        }
    }

}

// on below line we are creating a generate PDF
// method which is use to generate our PDF file.
@RequiresApi(Build.VERSION_CODES.KITKAT)
fun generatePDF(context: Context) {

    // declaring width and height
    // for our PDF file.
    var pageHeight = 1680
    var pageWidth = 1188

    // creating a bitmap variable
    // for storing our images
    lateinit var bmp: Bitmap
    lateinit var scaledbmp: Bitmap

    lateinit var bmp2: Bitmap
    lateinit var scaledbmp2: Bitmap

    lateinit var bmp3: Bitmap
    lateinit var scaledbmp3: Bitmap

    lateinit var bmp4: Bitmap
    lateinit var scaledbmp4: Bitmap

    // creating an object variable
    // for our PDF document.
    var pdfDocument: PdfDocument = PdfDocument()

    // two variables for paint "paint" is used
    // for drawing shapes and we will use "title"
    // for adding text in our PDF file.
    var paint: Paint = Paint()
    var title: Paint = Paint()

    // on below line we are initializing our bitmap and scaled bitmap.
    bmp = BitmapFactory.decodeResource(context.resources, R.drawable.honda1)
    scaledbmp = Bitmap.createScaledBitmap(bmp, 90, 90, false)

    bmp2 = BitmapFactory.decodeResource(context.resources, R.drawable.page3)
    scaledbmp2 = Bitmap.createScaledBitmap(bmp2, 1188, 1680, false)

    bmp3 = BitmapFactory.decodeResource(context.resources, R.drawable.page2)
    scaledbmp3 = Bitmap.createScaledBitmap(bmp3, 1188, 1680, false)

    bmp4 = BitmapFactory.decodeResource(context.resources, R.drawable.page1)
    scaledbmp4 = Bitmap.createScaledBitmap(bmp4, 1188, 1680, false)

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
    var canvas: Canvas = myPage.canvas
    var canvas4: Canvas = myPage.canvas

    // below line is used to draw our image on our PDF file.
    // the first parameter of our drawbitmap method is
    // our bitmap
    // second parameter is position from left
    // third parameter is position from top and last
    // one is our variable for paint.
    canvas.drawBitmap(scaledbmp, 620F, 10F, paint)
    canvas4.drawBitmap(scaledbmp4, 0F, 0F, paint)

    // below line is used for adding typeface for
    // our text which we will be adding in our PDF file.
    //title.setTypeface(Typeface.create(Typeface.DEFAULT, Typeface.NORMAL))

    // below line is used for setting text size
    // which we will be displaying in our PDF file.
    //title.textSize = 25F

    // below line is sued for setting color
    // of our text inside our PDF file.
   // title.setColor(ContextCompat.getColor(context, R.color.black))

    //canvas.drawText("Préparation Machine neuve par Distribike", 20F, 80F, title)
    // below line is used for adding typeface for
    // our text which we will be adding in our PDF file.
   // title.setTypeface(Typeface.create(Typeface.DEFAULT, Typeface.NORMAL))

    // below line is used for setting text size
    // which we will be displaying in our PDF file.
    //title.textSize = 15F

    // below line is sued for setting color
    // of our text inside our PDF file.
   // title.setColor(ContextCompat.getColor(context, R.color.black))

    // below line is used to draw text in our PDF file.
    // the first parameter is our text, second parameter
    // is position from start, third parameter is position from top
    // and then we are passing our variable of paint which is title.
    //canvas.drawText("Nom Technicien : Jérome", 20F, 140F, title)
    //canvas.drawText("Code Prep : 389102", 20F, 160F, title)
   // canvas.drawText("Modèle:", 480F, 140F, title)
    //canvas.drawText("NIV Châssis:", 480F, 160F, title)
    //canvas.drawText("Nom concessionnaire:", 480F, 180F, title)
    //canvas.drawText("Code concessionaire:", 480F, 200F, title)
    //canvas.drawText("N° de position:", 480F, 220F, title)
   // canvas.drawText("Liste des vérifications", 20F, 220F, title)
    title.setTypeface(Typeface.defaultFromStyle(Typeface.NORMAL))
    title.setColor(ContextCompat.getColor(context, R.color.black))
   // title.textSize = 30F
    //canvas.drawText("Général", 20F, 300F, title)
   // title.textSize = 18F
    //canvas.drawText("Vérification des pièces à monter d'après la liste du manuel de montage.", 80F, 340F, title)
    title.textSize = 30F
    // below line is used for setting
    // our text to center of PDF.
    title.textAlign = Paint.Align.CENTER
    canvas.drawText("En cours...", 396F, 560F, title)



    // after adding all attributes to our
    // PDF file we will be finishing our page.
    pdfDocument.finishPage(myPage)

    // below line is used for setting
    // start page for our PDF file.
    var myPage2: PdfDocument.Page = pdfDocument.startPage(myPageInfo2)

    // creating a variable for canvas
    // from our page of PDF.
    var canvas2: Canvas = myPage2.canvas

    // below line is used to draw our image on our PDF file.
    // the first parameter of our drawbitmap method is
    // our bitmap
    // second parameter is position from left
    // third parameter is position from top and last
    // one is our variable for paint.
    canvas2.drawBitmap(scaledbmp3, 0F, 0F, paint)

    // after adding all attributes to our
    // PDF file we will be finishing our page.
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
    val file: File = File(Environment.getExternalStorageDirectory(), "N°CHASSIS.pdf")

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
