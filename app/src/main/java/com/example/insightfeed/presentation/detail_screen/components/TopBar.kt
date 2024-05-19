package com.example.insightfeed.presentation.detail_screen.components

import android.content.res.Configuration
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.insightfeed.R
import com.example.insightfeed.ui.theme.InsightFeedTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar(
    onBackPressed: () -> Unit,
    onShareClick: () -> Unit,
    onBrowseClick: () -> Unit,
    onBookMarkClick: () -> Unit
) {

    TopAppBar(
        colors = TopAppBarDefaults.mediumTopAppBarColors(
            containerColor = Color.Transparent,
            actionIconContentColor = colorResource(id = R.color.body),
            navigationIconContentColor = colorResource(id = R.color.body)
        ),
        title = { /*TODO*/ },
        modifier = Modifier.fillMaxWidth(),
        navigationIcon = {
            IconButton(onClick = { onBackPressed }) {
                Icon(painter = painterResource(id = R.drawable.ic_back), contentDescription = null)
            }
        },
        actions = {
            IconButton(onClick = { onBookMarkClick }) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_baseline_bookmark_border_24),
                    contentDescription = null
                )
            }
            IconButton(onClick = { onShareClick }) {
                Icon(
                    imageVector = Icons.Default.Share,
                    contentDescription = null
                )
            }
            IconButton(onClick = { onBrowseClick }) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_browser),
                    contentDescription = null
                )
            }
        }
    )
}

@Composable
@Preview(showBackground = true)
@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
fun DetailTopBarPreview() {
    InsightFeedTheme {
        TopBar(
            onBackPressed = {},
            onShareClick = {},
            onBrowseClick = {},
            onBookMarkClick = {}
        )
    }
}