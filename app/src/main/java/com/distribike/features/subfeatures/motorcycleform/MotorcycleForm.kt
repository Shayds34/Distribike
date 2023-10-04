package com.distribike.features.subfeatures.motorcycleform

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.ArrowDropUp
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
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
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.unit.toSize
import androidx.hilt.navigation.compose.hiltViewModel
import com.airbnb.lottie.compose.*
import com.distribike.R
import com.distribike.features.subfeatures.motorcycleform.model.MotorcycleFormModelUi
import com.distribike.features.subfeatures.motorcycleform.viewmodel.MotorcycleFormViewModel
import com.distribike.ui.theme.Green
import com.distribike.ui.theme.RedDark

@Preview
@Composable
fun MotorcycleFormPreview() {
    MotorcycleForm()
}

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

    var username by remember {
        mutableStateOf("")
    }

    val savedUsername = viewModel.usernameLiveData.observeAsState("")

    var codePrep by remember {
        mutableStateOf("")
    }

    var model by remember {
        mutableStateOf("")
    }

    var numberChassis by remember {
        mutableStateOf("")
    }

    val chassis = viewModel.barcodeLiveData.observeAsState("")

    var nomConcession by remember {
        mutableStateOf("")
    }

    val concessionName = viewModel.concessionState.collectAsState()

    var codeConcession by remember {
        mutableStateOf("")
    }

    var position by remember {
        mutableStateOf("")
    }

    var expanded2 by remember { mutableStateOf(false) }
    val suggestions2 = listOf(
        "AGUENI Antoine",
        "ALBALADEJO Michel",
        "BARRON Paco",
        "BEUSSE Nans",
        "BROUILLARD Alain",
        "BRUEL Louis",
        "CORSI Alexandre",
        "CROISSIAU Steven",
        "DELUCHE Eric",
        "Degermenci Rochdy",
        "FAVEDE Laurent",
        "FROTTIER Ryad",
        "FRUNZA Thomas",
        "GONZALEZ Emmanuel",
        "IACONIS Axel",
        "LOPEZ Arnaud",
        "LOPES Luca",
        "MASVIDAL Christian",
        "MERTENS Yoann",
        "MAHFOUD Youness",
        "PERE Bastien",
        "RASSE Laurent",
        "ROULET Julien",
        "VIGNERON Alexandre",
        "YASSIN Omar",
        "ZAHI Lyes"
    )
    /// var selectedText2 by remember { mutableStateOf("") }

    var textfieldSize by remember { mutableStateOf(Size.Zero) }

    val icon = if (expanded2)
        Icons.Filled.ArrowDropUp //it requires androidx.compose.material:material-icons-extended
    else
        Icons.Filled.ArrowDropDown

    var expanded by remember { mutableStateOf(false) }
    val suggestions = listOf(
        "ADV350",
        "ADV750",
        "CB500F",
        "CB500X",
        "CB650R",
        "CB750",
        "CBR1000",
        "CBR500R",
        "CBR650R",
        "CL500",
        "CMX1100",
        "CMX500",
        "CRF1100",
        "CRF300L",
        "GL1800",
        "NC750X",
        "NSS125",
        "NSS350",
        "NSS750",
        "NT1100",
        "ST125",
        "WW125",
        "XL750"
    )
    // var selectedText by remember { mutableStateOf("") }

    var textFieldSize2 by remember { mutableStateOf(Size.Zero) }

    val icon2 = if (expanded)
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
                    value = savedUsername.value.ifEmpty { username },
                    onValueChange = { username = it },
                    singleLine = true,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 150.dp)
                        .onGloballyPositioned { coordinates ->
                            //This value is used to assign to the DropDown the same width
                            textFieldSize2 = coordinates.size.toSize()
                        },
                    label = { Text("") },
                    keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
                    textStyle = TextStyle.Default.copy(fontSize = 28.sp),
                    trailingIcon = {
                        Icon(icon, "contentDescription",
                            Modifier.clickable {
                                viewModel.clearUsername()
                                expanded2 = !expanded2
                            }
                        )
                    })
                DropdownMenu(
                    expanded = expanded2,
                    onDismissRequest = { expanded2 = false },
                    modifier = Modifier
                        .width(with(LocalDensity.current) { textFieldSize2.width.toDp() })
                ) {
                    suggestions2.forEach { label ->
                        DropdownMenuItem(onClick = {
                            username = label
                            expanded2 = false
                        },
                            text = {
                                Text(text = label)
                            }
                        )
                    }
                }

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
                    value = codePrep.uppercase(),
                    onValueChange = { codePrep = it },
                    singleLine = true,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 150.dp),
                    textStyle = TextStyle.Default.copy(fontSize = 28.sp),
                    keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next)

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
                    value = model.uppercase(),
                    onValueChange = { model = it },
                    singleLine = true,

                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 150.dp)
                        .onGloballyPositioned { coordinates ->
                            //This value is used to assign to the DropDown the same width
                            textfieldSize = coordinates.size.toSize()
                        },
                    label = { Text("") },
                    keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
                    textStyle = TextStyle.Default.copy(fontSize = 28.sp),
                    trailingIcon = {
                        Icon(icon, "contentDescription",
                            Modifier.clickable { expanded = !expanded })
                    }
                )

                DropdownMenu(
                    expanded = expanded,
                    onDismissRequest = { expanded = false },
                    modifier = Modifier
                        .width(with(LocalDensity.current) { textfieldSize.width.toDp() })
                ) {
                    suggestions.forEach { label ->
                        DropdownMenuItem(onClick = {
                            model = label
                            expanded = false
                        },
                            text = {
                                Text(text = label)
                            }
                        )
                    }
                }

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
                    value = chassis.value.ifEmpty { numberChassis.uppercase() },
                    onValueChange = { numberChassis = it },
                    singleLine = true,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 150.dp),
                    textStyle = TextStyle.Default.copy(fontSize = 28.sp),
                    keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next)
                )

                Row(
                    horizontalArrangement = Arrangement.spacedBy(20.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 280.dp),

                    ) {
                    Button(
                        onClick = {
                            viewModel.onScanClicked()
                        },
                        colors = ButtonDefaults.buttonColors(containerColor = RedDark),

                        ) {
                        Text(
                            text = "Scanner", fontSize = 16.sp
                        )
                    }
                    Button(
                        onClick = {
                            viewModel.clearChassis()
                        },
                        colors = ButtonDefaults.buttonColors(containerColor = RedDark),

                        ) {
                        Text(
                            text = "Reset", fontSize = 16.sp
                        )
                    }
                }

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
                    value = codeConcession.uppercase(),
                    onValueChange = {
                        codeConcession = it
                        viewModel.onConcessionCodeEntered(it)
                    },
                    singleLine = true,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 150.dp),
                    textStyle = TextStyle.Default.copy(fontSize = 28.sp),
                    keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next)
                )

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
                    value = concessionName.value.ifEmpty { nomConcession.uppercase() },
                    onValueChange = { nomConcession = it },
                    singleLine = true,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 150.dp),
                    textStyle = TextStyle.Default.copy(fontSize = 28.sp),
                    keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
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
                    value = position.uppercase(),
                    onValueChange = { position = it },
                    singleLine = true,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 150.dp),
                    textStyle = TextStyle.Default.copy(fontSize = 28.sp),
                    keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
                )

                Spacer(modifier = Modifier.padding(8.dp))

                Button(
                    onClick = {
                        viewModel.onValidateClicked(
                            MotorcycleFormModelUi(
                                username = savedUsername.value.ifEmpty { username },
                                codePrep = codePrep,
                                model = model,
                                chassis = chassis.value.ifEmpty { numberChassis },
                                concessionName = concessionName.value.ifEmpty { nomConcession },
                                concessionCode = codeConcession,
                                positionNumber = position
                            )
                        )
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

    var username by remember {
        mutableStateOf("")
    }

    var codePrep by remember {
        mutableStateOf("")
    }
    var model by remember {
        mutableStateOf("")
    }
    var numberChassis by remember {
        mutableStateOf("")
    }
    val chassis = viewModel.barcodeLiveData.observeAsState("")

    var nomConcession by remember {
        mutableStateOf("")
    }
    val concessionName = viewModel.concessionState.collectAsState()

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
            text = "Information à remplir",
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
            text = "Nom Prénom:",
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
            text = "Modèle:",
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
            text = "NIV Châssis:",
            style = TextStyle(
                fontWeight = FontWeight.Bold, letterSpacing = 1.sp
            ),
            fontSize = 18.sp
        )

        Spacer(modifier = Modifier.padding(4.dp))

        OutlinedTextField(
            value = chassis.value.ifEmpty { numberChassis },
            onValueChange = { numberChassis = it },
            singleLine = true,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp),
            textStyle = TextStyle.Default.copy(fontSize = 16.sp)
        )

        Spacer(modifier = Modifier.padding(4.dp))

        Button(
            onClick = {
                viewModel.onScanClicked()
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
            text = "N° de position:",
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
                viewModel.onValidateClicked(
                    MotorcycleFormModelUi(
                        username = username,
                        codePrep = codePrep,
                        model = model,
                        chassis = chassis.value.ifEmpty { numberChassis },
                        concessionName = concessionName.value.ifEmpty { nomConcession },
                        concessionCode = codeConcession,
                        positionNumber = position
                    )
                )
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
