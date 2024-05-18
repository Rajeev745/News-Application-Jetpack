package com.example.insightfeed.presentation.common

import android.content.res.Configuration
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.insightfeed.R
import com.example.insightfeed.domain.model.news.ArticlesModel
import com.example.insightfeed.domain.model.news.SourceModel
import com.example.insightfeed.presentation.Dimens.articlesCardSize
import com.example.insightfeed.presentation.Dimens.iconSize
import com.example.insightfeed.presentation.Dimens.paddingTextHorizontal
import com.example.insightfeed.presentation.Dimens.smallPadding
import com.example.insightfeed.presentation.Dimens.smallPadding2
import com.example.insightfeed.ui.theme.InsightFeedTheme

@Composable
fun ArticlesCard(
    modifier: Modifier = Modifier, articlesModel: ArticlesModel, onClick: (() -> Unit)? = null
) {
    val context = LocalContext.current

    Row(modifier = modifier
        .clickable { onClick?.invoke() }
        .padding(paddingTextHorizontal, 0.dp)) {

        AsyncImage(
            modifier = Modifier
                .size(articlesCardSize)
                .clip(MaterialTheme.shapes.medium),
            contentScale = ContentScale.Crop,
            model = ImageRequest.Builder(context = context).data(articlesModel.urlToImage).build(),
            contentDescription = context.resources.getString(R.string.articles_image_desc),
            error = painterResource(id = R.drawable.ic_news_launcher)
        )

        Spacer(modifier = Modifier.width(paddingTextHorizontal))

        Column(
            verticalArrangement = Arrangement.SpaceAround,
            modifier = Modifier
                .padding(smallPadding)
                .height(articlesCardSize)
        ) {
            Text(
                text = articlesModel.title ?: "",
                style = MaterialTheme.typography.bodyMedium,
                color = colorResource(id = R.color.text_title),
                maxLines = 2,
                overflow = TextOverflow.Ellipsis
            )

            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(
                    text = articlesModel.source?.name ?: "",
                    style = MaterialTheme.typography.labelMedium.copy(fontWeight = FontWeight.Bold),
                    color = colorResource(id = R.color.body)
                )

                Spacer(modifier = Modifier.width(smallPadding2))
            }

            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Start
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_author),
                    contentDescription = null,
                    modifier = Modifier.size(
                        iconSize
                    ),
                    tint = colorResource(id = R.color.body)
                )

                Spacer(modifier = Modifier.width(smallPadding2))

                Text(
                    text = articlesModel.author ?: "",
                    style = MaterialTheme.typography.bodySmall,
                    color = colorResource(id = R.color.text_title),
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun ArticlesCardPreview() {
    InsightFeedTheme {
        ArticlesCard(
            articlesModel = ArticlesModel(
                source = SourceModel(id = "", name = "BBC"),
                author = "Rajeev",
                title = "Title",
                description = "",
                url = "",
                urlToImage = "",
                content = ""
            )
        )
    }
}