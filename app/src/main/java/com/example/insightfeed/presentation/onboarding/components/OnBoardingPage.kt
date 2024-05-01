package com.example.insightfeed.presentation.onboarding.components

import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import com.example.insightfeed.R
import com.example.insightfeed.presentation.Dimens.paddingMedium1
import com.example.insightfeed.presentation.Dimens.paddingTextHorizontal
import com.example.insightfeed.presentation.onboarding.OnboardingPageModel
import com.example.insightfeed.ui.theme.InsightFeedTheme

@Composable
fun OnBoardingPage(
    modifier: Modifier = Modifier, onBoardingPageInfo: OnboardingPageModel
) {

    Column(modifier = modifier) {
        Image(
            modifier = Modifier
                .fillMaxHeight(0.5f)
                .fillMaxWidth(),
            painter = painterResource(id = onBoardingPageInfo.image),
            contentDescription = onBoardingPageInfo.description.toString(),
            contentScale = ContentScale.Crop
        )

        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .height(paddingMedium1)
        )

        Text(
            modifier = Modifier.padding(horizontal = paddingTextHorizontal),
            text = onBoardingPageInfo.title.toString(),
            style = MaterialTheme.typography.displaySmall.copy(fontWeight = FontWeight.Bold),
            color = colorResource(id = R.color.text_title)
        )

        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .height(paddingMedium1)
        )

        Text(
            text = onBoardingPageInfo.description.toString(),
            modifier = Modifier.padding(horizontal = paddingTextHorizontal),
            color = colorResource(id = R.color.text_description),
            style = MaterialTheme.typography.bodyMedium
        )
    }
}

@Preview(showBackground = true)
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun OnBoardingPagePreview() {
    InsightFeedTheme {
        OnBoardingPage(
            onBoardingPageInfo = OnboardingPageModel(
                "Title",
                "Discover how technological advancements and changing media landscapes are revolutionizing the way news is produced, consumed, and shared, reshaping the future of information dissemination.",
                R.drawable.news_image_intro
            )
        )
    }
}