package com.example.insightfeed.presentation.news_navigation

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.insightfeed.R
import com.example.insightfeed.presentation.Dimens
import com.example.insightfeed.ui.theme.InsightFeedTheme

@Composable
fun NewsBottomNavigation(
    items: List<NewsNavigationItem>,
    selectedItem: Int,
    onItemClick: (Int) -> Unit
) {

    NavigationBar(
        modifier = Modifier.fillMaxWidth(),
        containerColor = MaterialTheme.colorScheme.background,
        tonalElevation = 10.dp
    ) {
        items.forEachIndexed { index, newsNavigationItem ->
            NavigationBarItem(
                selected = index == selectedItem,
                onClick = { onItemClick(index) },
                icon = {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Icon(
                            painter = painterResource(id = newsNavigationItem.icon),
                            contentDescription = null,
                            modifier = Modifier.size(Dimens.iconSize)
                        )
                        Spacer(modifier = Modifier.height(Dimens.smallPadding))
                        Text(
                            text = newsNavigationItem.title,
                            style = MaterialTheme.typography.displaySmall
                        )
                    }
                },
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = MaterialTheme.colorScheme.primary,
                    selectedTextColor = MaterialTheme.colorScheme.primary,
                    unselectedIconColor = colorResource(id = R.color.body),
                    unselectedTextColor = colorResource(id = R.color.body),
                    indicatorColor = MaterialTheme.colorScheme.background
                )
            )
        }
    }
}

@Preview
@Preview(uiMode = UI_MODE_NIGHT_YES)
@Composable
fun NewsBottomNavigationPreview() {
    InsightFeedTheme(dynamicColor = false) {
        NewsBottomNavigation(items = listOf(
            NewsNavigationItem(icon = R.drawable.ic_home, title = "Home"),
            NewsNavigationItem(icon = R.drawable.ic_search, title = "Search"),
            NewsNavigationItem(icon = R.drawable.ic_bookmark_unsaved, title = "Bookmark")
        ), selectedItem = 0, onItemClick = {})
    }
}