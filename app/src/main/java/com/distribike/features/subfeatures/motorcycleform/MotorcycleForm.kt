package com.distribike.features.subfeatures.motorcycleform

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.airbnb.lottie.compose.*
import com.distribike.R
import com.distribike.features.subfeatures.motorcycleform.viewmodel.MotorcycleFormViewModel
import com.distribike.ui.theme.RedDark
import com.distribike.ui.theme.Green
import dagger.hilt.android.lifecycle.HiltViewModel

@Preview
@Composable
fun MotorcycleFormPreview() {
    MotorcycleForm()
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MotorcycleForm() {
    val viewModel = hiltViewModel<MotorcycleFormViewModel>()

    val configuration = LocalConfiguration.current
    val screenWidth = configuration.screenWidthDp.dp
    val screenHeight = configuration.screenHeightDp.dp

    var username by remember {
        mutableStateOf("")
    }

    var codePrep by remember {
        mutableStateOf("")
    }
    var model by remember {
        mutableStateOf("")
    }
    var chassis by remember {
        mutableStateOf("")
    }
    var nomConcession by remember {
        mutableStateOf("")
    }
    var codeConcession by remember {
        mutableStateOf("")
    }
    var position by remember {
        mutableStateOf("")
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top,
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .clip(RoundedCornerShape(topStart = 30.dp, topEnd = 30.dp))
                .background(Color.White)
        ) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {

                Spacer(modifier = Modifier.padding(0.dp))

                Image(
                    painter = painterResource(R.drawable.honda1),
                    contentDescription = null,
                    modifier = Modifier.size(160.dp, 120.dp)
                )

                Spacer(modifier = Modifier.padding(8.dp))

                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 150.dp),
                    text = "Information à remplir",
                    style = TextStyle(
                        fontWeight = FontWeight.Bold, color = Green, letterSpacing = 2.sp
                    ),
                    fontSize = 40.sp
                )

                Spacer(modifier = Modifier.padding(8.dp))

                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 150.dp),
                    text = "Nom Prénom:",
                    style = TextStyle(
                        fontWeight = FontWeight.Bold, letterSpacing = 1.sp
                    ),
                    fontSize = 22.sp
                )

                Spacer(modifier = Modifier.padding(2.dp))

                OutlinedTextField(
                    value = username,
                    onValueChange = { username = it },
                    singleLine = true,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 150.dp),
                    textStyle = TextStyle.Default.copy(fontSize = 28.sp)
                )

                Spacer(modifier = Modifier.padding(8.dp))

                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 150.dp),
                    text = "Code Prep:",
                    style = TextStyle(
                        fontWeight = FontWeight.Bold, letterSpacing = 1.sp
                    ),
                    fontSize = 22.sp
                )

                Spacer(modifier = Modifier.padding(2.dp))

                OutlinedTextField(
                    value = codePrep,
                    onValueChange = { codePrep = it },
                    singleLine = true,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 150.dp),
                    textStyle = TextStyle.Default.copy(fontSize = 28.sp)
                )

                Spacer(modifier = Modifier.padding(8.dp))

                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 150.dp),
                    text = "Modèle:",
                    style = TextStyle(
                        fontWeight = FontWeight.Bold, letterSpacing = 1.sp
                    ),
                    fontSize = 22.sp
                )

                Spacer(modifier = Modifier.padding(2.dp))

                OutlinedTextField(
                    value = model,
                    onValueChange = { model = it },
                    singleLine = true,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 150.dp),
                    textStyle = TextStyle.Default.copy(fontSize = 28.sp)
                )

                Spacer(modifier = Modifier.padding(8.dp))

                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 150.dp),
                    text = "NIV Châssis:",
                    style = TextStyle(
                        fontWeight = FontWeight.Bold, letterSpacing = 1.sp
                    ),
                    fontSize = 22.sp
                )

                Spacer(modifier = Modifier.padding(2.dp))

                OutlinedTextField(
                    value = chassis,
                    onValueChange = { chassis = it },
                    singleLine = true,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 150.dp),
                    textStyle = TextStyle.Default.copy(fontSize = 28.sp)
                )

                Button(
                    onClick = {
                        // TODO("SCAN")
                    },
                    colors = ButtonDefaults.buttonColors(containerColor = RedDark),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 340.dp),
                ) {
                    Text(
                        text = "Scan", fontSize = 16.sp
                    )
                }

                Spacer(modifier = Modifier.padding(8.dp))

                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 150.dp),
                    text = "Nom concessionnaire:",
                    style = TextStyle(
                        fontWeight = FontWeight.Bold, letterSpacing = 1.sp
                    ),
                    fontSize = 22.sp
                )

                Spacer(modifier = Modifier.padding(2.dp))

                OutlinedTextField(
                    value = nomConcession,
                    onValueChange = { nomConcession = it },
                    singleLine = true,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 150.dp),
                    textStyle = TextStyle.Default.copy(fontSize = 28.sp)
                )

                Spacer(modifier = Modifier.padding(8.dp))

                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 150.dp),
                    text = "Code concessionnaire:",
                    style = TextStyle(
                        fontWeight = FontWeight.Bold, letterSpacing = 1.sp
                    ),
                    fontSize = 22.sp
                )

                Spacer(modifier = Modifier.padding(2.dp))

                OutlinedTextField(
                    value = codeConcession,
                    onValueChange = { codeConcession = it },
                    singleLine = true,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 150.dp),
                    textStyle = TextStyle.Default.copy(fontSize = 28.sp)
                )

                Spacer(modifier = Modifier.padding(8.dp))

                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 150.dp),
                    text = "N° de position:",
                    style = TextStyle(
                        fontWeight = FontWeight.Bold, letterSpacing = 1.sp
                    ),
                    fontSize = 22.sp
                )

                Spacer(modifier = Modifier.padding(2.dp))

                OutlinedTextField(
                    value = position,
                    onValueChange = { position = it },
                    singleLine = true,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 150.dp),
                    textStyle = TextStyle.Default.copy(fontSize = 28.sp)
                )

                Spacer(modifier = Modifier.padding(8.dp))

                Button(
                    onClick = {
                        viewModel.onValidateClicked()
                    },
                    colors = ButtonDefaults.buttonColors(containerColor = Green),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 24.dp)
                        .height(60.dp)
                ) {
                    Text(
                        text = "Valider", fontSize = 28.sp
                    )
                }
            }
        }
    }
}