package com.distribike.features.subfeatures.motorcycleform

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.ArrowDropUp
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.unit.toSize
import androidx.hilt.navigation.compose.hiltViewModel
import com.airbnb.lottie.compose.*
import com.distribike.R
import com.distribike.features.subfeatures.motorcycleform.viewmodel.MotorcycleFormViewModel
import com.distribike.ui.theme.Green
import com.distribike.ui.theme.RedDark

@Preview
@Composable
fun MotorcycleFormPreview() {
    MotorcycleForm()
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MotorcycleForm() {
    val configuration = LocalConfiguration.current
    val screenWidth = configuration.screenWidthDp.dp

    if (screenWidth > 400.dp) {
        TabletMotorcycleForm()
    } else {
        MobileMotorcycleForm()
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TabletMotorcycleForm() {
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

    var expanded by remember { mutableStateOf(false) }
    val suggestions = listOf("ADV350","ADV750","CB500F","CB500X","CB650R","CB750","CBR1000","CBR500R","CBR650R","CMX1100","CMX500","CRF1100","CRF300L","GL1800","NC750X","NSS125","NSS750","NT1100","ST125","WW125")
    var selectedText by remember { mutableStateOf("") }

    var textfieldSize by remember { mutableStateOf(Size.Zero)}

    val icon = if (expanded)
        Icons.Filled.ArrowDropUp //it requires androidx.compose.material:material-icons-extended
    else
        Icons.Filled.ArrowDropDown

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
                    text = "Information ?? remplir",
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
                    text = "Nom Pr??nom:",
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
                    text = "Mod??le:",
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
                        .padding(horizontal = 150.dp)
                        .onGloballyPositioned { coordinates ->
                            //This value is used to assign to the DropDown the same width
                            textfieldSize = coordinates.size.toSize()
                        },
                    label = {Text("")},

                    textStyle = TextStyle.Default.copy(fontSize = 28.sp),
                            trailingIcon = {
                        Icon(icon,"contentDescription",
                            Modifier.clickable { expanded = !expanded })
                    }
                )

                DropdownMenu(
                    expanded = expanded,
                    onDismissRequest = { expanded = false },
                    modifier = Modifier
                        .width(with(LocalDensity.current){textfieldSize.width.toDp()})
                ) {
                    suggestions.forEach { label ->
                        DropdownMenuItem(onClick = {
                            model = label
                            expanded = false
                        },
                            text ={
                                Text(text=label)
                            }
                        )
                    }
                }

                Spacer(modifier = Modifier.padding(8.dp))

                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 150.dp),
                    text = "NIV Ch??ssis:",
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
                        text = "Scanner", fontSize = 16.sp
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
                    text = "N?? de position:",
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
                        .padding(horizontal = 150.dp)
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MobileMotorcycleForm() {
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
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top,
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .clip(RoundedCornerShape(topStart = 30.dp, topEnd = 30.dp))
            .background(Color.White)
    ) {
        Spacer(modifier = Modifier.padding(10.dp))

        Image(
            painter = painterResource(R.drawable.honda1),
            contentDescription = null,
            modifier = Modifier.size(100.dp, 120.dp)
        )

        Spacer(modifier = Modifier.padding(10.dp))

        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp),
            text = "Information ?? remplir",
            style = TextStyle(
                fontWeight = FontWeight.Bold, color = Green, letterSpacing = 2.sp
            ),
            fontSize = 24.sp
        )

        Spacer(modifier = Modifier.padding(8.dp))

        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp),
            text = "Nom Pr??nom:",
            style = TextStyle(
                fontWeight = FontWeight.Bold,
                letterSpacing = 1.sp
            ),
            fontSize = 16.sp
        )

        Spacer(modifier = Modifier.padding(4.dp))

        OutlinedTextField(
            value = username,
            onValueChange = { username = it },
            singleLine = true,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp),
            textStyle = TextStyle.Default.copy(fontSize = 16.sp)
        )

        Spacer(modifier = Modifier.padding(10.dp))

        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp),
            text = "Code Prep:",
            style = TextStyle(
                fontWeight = FontWeight.Bold,
                letterSpacing = 1.sp
            ),
            fontSize = 16.sp
        )

        Spacer(modifier = Modifier.padding(4.dp))

        OutlinedTextField(
            value = codePrep,
            onValueChange = { codePrep = it },
            singleLine = true,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp),
            textStyle = TextStyle.Default.copy(fontSize = 16.sp)
        )

        Spacer(modifier = Modifier.padding(10.dp))

        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp),
            text = "Mod??le:",
            style = TextStyle(
                fontWeight = FontWeight.Bold, letterSpacing = 1.sp
            ),
            fontSize = 18.sp
        )

        Spacer(modifier = Modifier.padding(4.dp))

        OutlinedTextField(
            value = model,
            onValueChange = { model = it },
            singleLine = true,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp),
            textStyle = TextStyle.Default.copy(fontSize = 16.sp)
        )

        Spacer(modifier = Modifier.padding(10.dp))

        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp),
            text = "NIV Ch??ssis:",
            style = TextStyle(
                fontWeight = FontWeight.Bold, letterSpacing = 1.sp
            ),
            fontSize = 18.sp
        )

        Spacer(modifier = Modifier.padding(4.dp))

        OutlinedTextField(
            value = chassis,
            onValueChange = { chassis = it },
            singleLine = true,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp),
            textStyle = TextStyle.Default.copy(fontSize = 16.sp)
        )

        Spacer (modifier = Modifier.padding(4.dp))

        Button(
            onClick = {
                // TODO("SCAN")
            },
            colors = ButtonDefaults.buttonColors(containerColor = RedDark),
            modifier = Modifier
                .wrapContentSize()
                .padding(horizontal = 20.dp),
        ) {
            Text(
                text = "Scaner",
                fontSize = 16.sp
            )
        }

        Spacer(modifier = Modifier.padding(10.dp))

        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp),
            text = "Nom concessionnaire:",
            style = TextStyle(
                fontWeight = FontWeight.Bold,
                letterSpacing = 1.sp
            ),
            fontSize = 18.sp
        )

        Spacer(modifier = Modifier.padding(4.dp))

        OutlinedTextField(
            value = nomConcession,
            onValueChange = { nomConcession = it },
            singleLine = true,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp),
            textStyle = TextStyle.Default.copy(fontSize = 16.sp)
        )

        Spacer(modifier = Modifier.padding(10.dp))

        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp),
            text = "Code concessionnaire:",
            style = TextStyle(
                fontWeight = FontWeight.Bold,
                letterSpacing = 1.sp
            ),
            fontSize = 18.sp
        )

        Spacer(modifier = Modifier.padding(4.dp))

        OutlinedTextField(
            value = codeConcession,
            onValueChange = { codeConcession = it },
            singleLine = true,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp),
            textStyle = TextStyle.Default.copy(fontSize = 16.sp)
        )

        Spacer(modifier = Modifier.padding(10.dp))

        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp),
            text = "N?? de position:",
            style = TextStyle(
                fontWeight = FontWeight.Bold, letterSpacing = 1.sp
            ),
            fontSize = 18.sp
        )

        Spacer(modifier = Modifier.padding(4.dp))

        OutlinedTextField(
            value = position,
            onValueChange = { position = it },
            singleLine = true,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp),
            textStyle = TextStyle.Default.copy(fontSize = 16.sp)
        )

        Spacer(modifier = Modifier.padding(10.dp))

        Button(
            onClick = {
                viewModel.onValidateClicked()
            },
            colors = ButtonDefaults.buttonColors(containerColor = Green),
            modifier = Modifier
                .wrapContentSize()
                .padding(horizontal = 20.dp)
        ) {
            Text(
                text = "Valider", fontSize = 16.sp
            )
        }
    }
}
