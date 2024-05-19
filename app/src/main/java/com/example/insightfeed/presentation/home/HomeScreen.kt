package com.example.insightfeed.presentation.home

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.basicMarquee
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.paging.compose.LazyPagingItems
import com.example.insightfeed.R
import com.example.insightfeed.domain.model.news.ArticlesModel
import com.example.insightfeed.presentation.Dimens
import com.example.insightfeed.presentation.Dimens.headingSize
import com.example.insightfeed.presentation.common.ArticlesList
import com.example.insightfeed.presentation.common.SearchBar

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun HomeScreen(
    articlesModel: LazyPagingItems<ArticlesModel>, navigateToDetail: (ArticlesModel) -> Unit
) {

    val titles by remember {
        derivedStateOf {
            if (articlesModel.itemCount > 10) {
                articlesModel.itemSnapshotList.items.slice(IntRange(start = 0, endInclusive = 9))
                    .joinToString(separator = " \uD83D\uDFE5 ") { it.title ?: "" }
            } else {
                ""
            }
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = Dimens.paddingMedium1)
            .statusBarsPadding()
    ) {
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
            Text(
                text = "HOME",
                fontSize = headingSize,
                fontWeight = FontWeight.Bold,
                style = MaterialTheme.typography.headlineMedium,
                color = colorResource(id = R.color.text_title)
            )
        }

        Spacer(modifier = Modifier.height(Dimens.paddingMedium1))

        SearchBar(modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = Dimens.paddingMedium1),
            text = "",
            readOnly = true,
            onValueChange = {},
            onSearch = {},
            onClick = {
                navigateToDetail
            })

        Spacer(modifier = Modifier.height(Dimens.paddingMedium1))

        Text(
            text = titles,
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = Dimens.paddingMedium1)
                .basicMarquee(),
            fontSize = 12.sp,
            color = colorResource(id = R.color.placeholder)
        )

        Spacer(modifier = Modifier.height(Dimens.paddingMedium1))

        ArticlesList(
            articles = articlesModel,
            modifier = Modifier.padding(horizontal = Dimens.paddingMedium1),
            onClick = navigateToDetail
        )
    }
}