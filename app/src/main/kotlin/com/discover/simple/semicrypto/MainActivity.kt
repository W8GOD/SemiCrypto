package com.discover.simple.semicrypto

import android.os.Build
import android.os.Bundle
import android.text.Html
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.TabRowDefaults.Divider
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.text.HtmlCompat
import androidx.paging.ExperimentalPagingApi
import coil.annotation.ExperimentalCoilApi
import coil.compose.ImagePainter
import coil.compose.LocalImageLoader
import coil.compose.rememberImagePainter
import coil.decode.SvgDecoder
import com.discover.simple.common.FileUtil
import com.discover.simple.core.model.Coin
import com.discover.simple.semicrypto.ui.theme.*
import kotlinx.coroutines.FlowPreview
import kotlin.time.ExperimentalTime


@FlowPreview
@ExperimentalPagingApi
@ExperimentalCoilApi
@ExperimentalTime
class MainActivity : ComponentActivity() {
    private val coinViewModel: CoinViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SemiCryptoTheme {
                Column {
                    val textState = remember { mutableStateOf(TextFieldValue("")) }
                    SearchView(textState)
                    Divider(color = brightGray, thickness = 1.dp)
                    CoinList(
                        viewModel = coinViewModel,
                        state = textState,
                        onLoadCompleted = {

                        },
                        onLoadFailed = {
                            Toast.makeText(
                                this@MainActivity, it,
                                Toast.LENGTH_SHORT
                            ).show()
                        },
                        onItemClicked = {
                            Toast.makeText(
                                this@MainActivity, it.name,
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    )
                }
            }
        }
    }
}

@Composable
fun SearchView(state: MutableState<TextFieldValue>) {
    TextField(
        value = state.value,
        onValueChange = { value ->
            state.value = value
        },
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 20.dp, bottom = 20.dp, start = 16.dp, end = 16.dp),
        textStyle = TextStyle(color = spanishGray, fontSize = 14.sp),
        leadingIcon = {
            Icon(
                Icons.Default.Search,
                contentDescription = null,
                modifier = Modifier
                    .padding(15.dp)
                    .size(18.dp)
            )
        },
        trailingIcon = {
            if (state.value != TextFieldValue("")) {
                IconButton(
                    onClick = {
                        state.value = TextFieldValue("")
                    }
                ) {
                    Icon(
                        Icons.Default.Close,
                        contentDescription = "",
                        modifier = Modifier
                            .padding(15.dp)
                            .size(24.dp)
                    )
                }
            }
        },
        singleLine = true,
        shape = RoundedCornerShape(8.dp),
        colors = TextFieldDefaults.textFieldColors(
            textColor = spanishGray,
            cursorColor = spanishGray,
            backgroundColor = brightGray,
            leadingIconColor = spanishGray,
            trailingIconColor = spanishGray,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            disabledIndicatorColor = Color.Transparent
        ),
        placeholder = {
            Text(
                stringResource(id = R.string.search),
                style = TextStyle(color = spanishGray, fontSize = 14.sp)
            )
        }
    )
}

@ExperimentalCoilApi
@Composable
fun CoinWithContentItem(coin: Coin, onClick: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick),
        verticalArrangement = Arrangement.Center,
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Surface(
                modifier = Modifier
                    .size(100.dp)
                    .padding(16.dp),
                shape = CircleShape
            ) {
                Image(
                    painter = getImagePainter(coin.iconUrl),
                    contentDescription = null,
                    contentScale = ContentScale.FillBounds,
                )
            }
            Column(
                modifier = Modifier
                    .align(Alignment.CenterVertically)
            ) {
                Text(
                    text = coin.name,
                    fontWeight = FontWeight.Bold,
                    style = TextStyle(fontSize = 16.sp),
                    color = textColorTitle,
                    overflow = TextOverflow.Ellipsis,
                    maxLines = 1,
                    modifier = Modifier.padding(end = 8.dp)
                )
                DescriptionText(coin.description)
            }
        }
        Divider(color = brightGray, thickness = 1.dp)
    }
}

@ExperimentalCoilApi
@Composable
fun CoinItem(coin: Coin, onClick: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.End,
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(
                text = coin.name,
                fontWeight = FontWeight.Bold,
                style = TextStyle(fontSize = 16.sp),
                color = textColorTitle,
                overflow = TextOverflow.Ellipsis,
                maxLines = 1,
                modifier = Modifier.padding(start = 8.dp, end = 8.dp)
            )
            Surface(
                modifier = Modifier
                    .size(100.dp)
                    .padding(16.dp),
                shape = CircleShape
            ) {
                Image(
                    painter = getImagePainter(coin.iconUrl),
                    contentDescription = null,
                    contentScale = ContentScale.FillBounds,
                )
            }
        }
        Divider(color = brightGray, thickness = 1.dp)
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

@ExperimentalCoilApi
@Composable
fun getImagePainter(imageUrl: String): ImagePainter {
    return if (FileUtil.getExtension(imageUrl).lowercase() == "svg") {
        rememberImagePainter(
            data = imageUrl,
            imageLoader = LocalImageLoader.current,
            builder = {
                crossfade(true)
                decoder(SvgDecoder(LocalContext.current))
                placeholder(R.drawable.ic_placeholder)
                error(R.drawable.ic_placeholder)
            }
        )
    } else {
        rememberImagePainter(imageUrl, builder = {
            crossfade(true)
            placeholder(R.drawable.ic_placeholder)
            error(R.drawable.ic_placeholder)
        })
    }
}