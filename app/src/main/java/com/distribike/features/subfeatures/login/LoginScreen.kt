package com.distribike.features.subfeatures.login

import android.media.Image
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.airbnb.lottie.compose.*
import com.distribike.R
import com.distribike.ui.theme.RedDark
import com.distribike.ui.theme.Green
import androidx.compose.foundation.layout.width

@Preview
@Composable
fun LoginScreenPreview() {
    LoginScreen()
}

@Composable
fun LoginLottie(modifier: Modifier = Modifier) {
    // Keep track if the animation is playing
    val isPlaying by remember {
        mutableStateOf(true)
    }

    val speed by remember {
        mutableStateOf(1f)
    }

    val composition by rememberLottieComposition(
        spec = LottieCompositionSpec.RawRes(R.raw.lottie_motobike_animation)
    )

    val progress by animateLottieCompositionAsState(
        composition = composition,
        iterations = LottieConstants.IterateForever,
        isPlaying = isPlaying,
        speed = speed,
        restartOnPlay = false
    )

    LottieAnimation(
        composition = composition,
        progress = progress,
        modifier = modifier
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen() {
    val configuration = LocalConfiguration.current

    val screenWidth = configuration.screenWidthDp.dp
    val screenHeight = configuration.screenHeightDp.dp

    var username by remember {
        mutableStateOf("")
    }

    var password by remember {
        mutableStateOf("")
    }

    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top,
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .clip(RoundedCornerShape(topStart = 30.dp, topEnd = 30.dp))
                .background(Color.White)
                .padding(40.dp)
        ) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                LoginLottie(
                    modifier = Modifier
                        .size(200.dp)
                        .padding(horizontal = 1.dp)
                )


                Spacer(modifier = Modifier.padding(35.dp))


                Image(
                    painter = painterResource(R.drawable.logodistribike2),
                    contentDescription = null,

                    modifier = Modifier
                        .size(600.dp,120.dp)




                            )



                Spacer(modifier = Modifier.padding(40.dp))



                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 20.dp),
                    text = "Connexion PDI",
                    style = TextStyle(
                        fontWeight = FontWeight.Bold,
                        letterSpacing = 2.sp
                    ),
                    fontSize = 40.sp
                )

                Spacer(modifier = Modifier.padding(30.dp))

                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 20.dp),
                    text = "Nom d'utilisateur:",
                    style = TextStyle(
                        fontWeight = FontWeight.Bold,
                        letterSpacing = 2.sp
                    ),
                    fontSize = 24.sp
                )

                Spacer(modifier = Modifier.padding(4.dp))

                OutlinedTextField(
                    value = username,
                    onValueChange = { username = it },
                    singleLine = true,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 20.dp),
                )

                Spacer(modifier = Modifier.padding(30.dp))

                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 20.dp),
                    text = "Mot de passe:",
                    style = TextStyle(
                        fontWeight = FontWeight.Bold,
                        letterSpacing = 2.sp
                    ),
                    fontSize = 24.sp
                )

                Spacer(modifier = Modifier.padding(10.dp))

                BasicTextField(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 250.dp),
                    value = password,
                    onValueChange = {
                        if (it.length <= 4) {
                            password = it
                        }
                    },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.NumberPassword),
                    decorationBox = {
                        Row(horizontalArrangement = Arrangement.SpaceBetween) {
                            repeat(4) { index ->
                                val char = when {
                                    index >= password.length -> ""
                                    else -> password[index].toString()
                                }
                                Text(
                                    modifier = Modifier
                                        .width(45.dp)
                                        .border(
                                            2.dp,
                                            Color.Gray,
                                            RoundedCornerShape(8.dp)
                                        ),
                                    text = char,
                                    style = MaterialTheme.typography.headlineLarge,
                                    color = RedDark,
                                    textAlign = TextAlign.Center
                                )
                            }
                        }
                    }
                )

                Spacer(modifier = Modifier.padding(60.dp))
                Button(
                    onClick = {},
                    colors = ButtonDefaults.buttonColors(containerColor = Green),
                modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 120.dp)
                        .height(80.dp)
                ) {
                    Text(
                        text = "Se connecter",
                        fontSize = 30.sp
                    )
                }
                Spacer(modifier = Modifier.padding(20.dp))
            }
        }
    }
}
