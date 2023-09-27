package com.example.artgallery

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.DrawableRes
import androidx.annotation.VisibleForTesting
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.artgallery.ui.theme.ArtGalleryTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ArtGalleryTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background
                ) {
                    ArtGalleryLayout()
                }
            }
        }
    }
}

data class Masterpiece(
    @DrawableRes val imageIdRes: Int,
    val title: String,
    val author: String,
)

val collections: List<Masterpiece> = listOf(
    Masterpiece(R.drawable.rainbow, "Rainbow Girl", "Levi Ackerman"),
    Masterpiece(R.drawable.cowgirl, "Cowboy Girl", "Ozamu Dazai"),
    Masterpiece(R.drawable.serene_bay, "Serene Bay", "Akutagawa"),
    Masterpiece(R.drawable.melody, "Nanairo Symphony", "Rampo"),
    Masterpiece(R.drawable.sport_boys, "Sports Boys", "Fyodor Dostoevsky"),
)

@VisibleForTesting
internal fun onPreviousClicked(masterpieceNumber: Int): Int {
    return if (masterpieceNumber - 1 < 0) 4
    else masterpieceNumber - 1
}

@VisibleForTesting
internal fun onNextClicked(masterpieceNumber: Int): Int {
    return if (masterpieceNumber + 1 > 4) 0
    else masterpieceNumber + 1
}

@Composable
fun ArtGalleryLayout(modifier: Modifier = Modifier) {
    val masterpieces: List<Masterpiece> by remember { mutableStateOf(collections) }
    var masterpieceNumber: Int by remember { mutableIntStateOf(0) }

    Column(
        horizontalAlignment = CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween,
        modifier = modifier.padding(16.dp).fillMaxSize(),
    ) {
        ImageSection(
            modifier.weight(2f)
            ,
            masterpieces[masterpieceNumber].imageIdRes
        )
        VerticalSpacer(modifier)
        InformationSection(
            modifier,
            masterpieces[masterpieceNumber].title,
            masterpieces[masterpieceNumber].author
        )
        VerticalSpacer(modifier)
        ButtonsSection(
            modifier,
            { masterpieceNumber = onPreviousClicked(masterpieceNumber) },
            { masterpieceNumber = onNextClicked(masterpieceNumber) })
    }
}

@Composable
private fun ImageSection(modifier: Modifier, imageId: Int) {
    Surface(
        color = Color.Transparent,
        shadowElevation = 3.dp,
        modifier = modifier.height(IntrinsicSize.Max)
        ) {
        Image(
            painter = painterResource(id = imageId),
            contentDescription = null,
            modifier = modifier.padding(20.dp).fillMaxWidth(),
        )
    }
}

@Composable
private fun InformationSection(modifier: Modifier, title: String, author: String) {
    Surface(
        modifier = modifier.border(BorderStroke(1.dp, Color.DarkGray)),
        shadowElevation = 40.dp
    ) {
        Column {
            Text(
                text = title,
                modifier = modifier
                    .align(CenterHorizontally)
                    .padding(top = 10.dp),
                fontWeight = FontWeight.Bold
            )
            VerticalSpacer(modifier)
            Row(modifier.padding(10.dp)) {
                Text(
                    text = "Author: ", modifier = modifier
                )
                HorizontalSpacer(modifier)
                Text(
                    text = author, modifier = modifier
                )
            }
        }
    }
}

@Composable
fun ButtonsSection(modifier: Modifier, onPreviousClicked: () -> Unit, onNextClicked: () -> Unit) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = modifier.padding(vertical = 10.dp).fillMaxWidth(),
    ) {
        Button(onClick = onPreviousClicked) {
            Text(text = stringResource(id = R.string.btn_previous))
        }
        HorizontalSpacer(modifier)
        Button(onClick = onNextClicked) {
            Text(text = stringResource(id = R.string.btn_next))
        }
    }
}

@Composable
private fun HorizontalSpacer(modifier: Modifier) = Spacer(modifier.width(10.dp))


@Composable
fun VerticalSpacer(modifier: Modifier) = Spacer(modifier.height(10.dp))


@Preview(showBackground = true, device = Devices.NEXUS_9)
@Preview(showBackground = true, device = Devices.PIXEL_XL)
@Composable
fun GreetingPreview() {
    ArtGalleryTheme {
        ArtGalleryLayout()
    }
}