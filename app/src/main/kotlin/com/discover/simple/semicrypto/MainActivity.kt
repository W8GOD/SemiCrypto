package com.discover.simple.semicrypto

import android.os.Build
import android.os.Bundle
import android.text.Html
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Surface
import androidx.compose.material.TabRowDefaults.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.text.HtmlCompat
import androidx.paging.ExperimentalPagingApi
import coil.annotation.ExperimentalCoilApi
import coil.compose.LocalImageLoader
import coil.compose.rememberImagePainter
import coil.decode.SvgDecoder
import com.discover.simple.core.entity.CoinsEntity
import com.discover.simple.semicrypto.ui.theme.SemiCryptoTheme
import com.discover.simple.semicrypto.ui.theme.divider
import com.discover.simple.semicrypto.ui.theme.textColorDescription
import com.discover.simple.semicrypto.ui.theme.textColorTitle

@ExperimentalPagingApi
@ExperimentalCoilApi
class MainActivity : ComponentActivity() {
    private val coinViewModel: CoinViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SemiCryptoTheme {
                CoinList(viewModel = coinViewModel, context = this)
            }
        }
    }
}

@ExperimentalCoilApi
@Composable
fun CoinWithContentItem(coinData: CoinsEntity.CoinEntity, onClick: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick),
        verticalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        Row {
            Surface(
                modifier = Modifier
                    .size(100.dp)
                    .padding(16.dp),
                shape = CircleShape
            ) {
                val image = rememberImagePainter(
                    data = coinData.iconUrl,
                    imageLoader = LocalImageLoader.current,
                    builder = {
                        crossfade(true)
                        placeholder(0)
                        decoder(SvgDecoder(LocalContext.current))
                    }
                )
                Image(
                    painter = image,
                    contentDescription = null,
                    contentScale = ContentScale.FillBounds,
                )
            }
            Column(
                modifier = Modifier
                    .align(Alignment.CenterVertically)
            ) {
                Text(
                    text = coinData.name,
                    fontWeight = FontWeight.Bold,
                    style = TextStyle(fontSize = 16.sp),
                    color = textColorTitle,
                    overflow = TextOverflow.Ellipsis,
                    maxLines = 1,
                    modifier = Modifier.padding(end = 8.dp)
                )
                DescriptionText(coinData.description)
            }
        }
        Divider(color = divider, thickness = 1.dp)
    }
}

@ExperimentalCoilApi
@Composable
fun CoinItem(coinData: CoinsEntity.CoinEntity, onClick: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalAlignment = Alignment.End
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Text(
                text = coinData.name,
                fontWeight = FontWeight.Bold,
                style = TextStyle(fontSize = 16.sp),
                color = textColorTitle,
                overflow = TextOverflow.Ellipsis,
                maxLines = 1,
                modifier = Modifier.padding(end = 8.dp)
            )
            Surface(
                modifier = Modifier
                    .size(100.dp)
                    .padding(16.dp),
                shape = CircleShape
            ) {
                val image = rememberImagePainter(
                    data = coinData.iconUrl,
                    imageLoader = LocalImageLoader.current,
                    builder = {
                        crossfade(true)
                        placeholder(0)
                        decoder(SvgDecoder(LocalContext.current))
                    }
                )
                Image(
                    painter = image,
                    contentDescription = null,
                    contentScale = ContentScale.FillBounds,
                )
            }
        }
        Divider(color = divider, thickness = 1.dp)
    }
}

@Composable
private fun DescriptionText(html: String) {
    val description = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
        Html.fromHtml(html, HtmlCompat.FROM_HTML_MODE_LEGACY).toString()
    } else {
        Html.fromHtml(html).toString()
    }
    Text(
        text = description,
        fontWeight = FontWeight.Normal,
        style = TextStyle(fontSize = 14.sp),
        color = textColorDescription,
        overflow = TextOverflow.Ellipsis,
        maxLines = 3,
        modifier = Modifier.padding(top = 8.dp, end = 8.dp)
    )
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    SemiCryptoTheme {

    }
}