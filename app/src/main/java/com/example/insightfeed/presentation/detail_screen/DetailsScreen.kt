package com.example.insightfeed.presentation.detail_screen

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.insightfeed.R
import com.example.insightfeed.domain.model.news.ArticlesModel
import com.example.insightfeed.presentation.Dimens.paddingMedium1
import com.example.insightfeed.presentation.detail_screen.components.TopBar
import com.example.insightfeed.utils.UIComponent

@Composable
fun DetailsScreen(
    articlesModel: ArticlesModel,
    event: (DetailEvent) -> Unit,
    navigateUp: () -> Unit
) {

    val context = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .statusBarsPadding()
    ) {
        TopBar(
            onBackPressed = { navigateUp },
            onShareClick = {
                Intent(Intent.ACTION_SEND).also {
                    it.putExtra(Intent.EXTRA_TEXT, articlesModel.url)
                    it.type = "text/plain"
                    if (it.resolveActivity(context.packageManager) != null) {
                        context.startActivity(it)
                    }
                }
            },
            onBrowseClick = {
                Intent(Intent.ACTION_VIEW).also {
                    it.data = Uri.parse(articlesModel.url)
                    if (it.resolveActivity(context.packageManager) != null) {
                        context.startActivity(it)
                    }
                }
            },
            onBookMarkClick = { event(DetailEvent.InsertDeleteArticle(articlesModel)) }
        )

        LazyColumn(
            modifier = Modifier.fillMaxWidth(),
            contentPadding = PaddingValues(
                start = paddingMedium1,
                top = paddingMedium1,
                end = paddingMedium1
            )
        ) {
            item {
                AsyncImage(
                    model = ImageRequest.Builder(context = context).data(articlesModel.urlToImage)
                        .build(),
                    contentDescription = null,
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight(0.3f)
                        .clip(MaterialTheme.shapes.medium),
                    contentScale = ContentScale.Crop
                )
                Spacer(modifier = Modifier.height(paddingMedium1))
                Text(
                    text = articlesModel.title ?: "",
                    style = MaterialTheme.typography.displaySmall,
                    color = colorResource(
                        id = R.color.text_title
                    )
                )

                Text(
                    text = articlesModel.content ?: "",
                    style = MaterialTheme.typography.bodyMedium,
                    color = colorResource(
                        id = R.color.text_title
                    )
                )
            }
        }
    }
}