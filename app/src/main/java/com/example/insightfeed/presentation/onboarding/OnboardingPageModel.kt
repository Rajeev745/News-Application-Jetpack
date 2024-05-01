package com.example.insightfeed.presentation.onboarding

import androidx.annotation.DrawableRes
import com.example.insightfeed.R

data class OnboardingPageModel(
    var title: String, var description: String, @DrawableRes var image: Int
)

/**
 * Getting the data for the onboarding screens
 */
val onBoardingList = listOf(
    OnboardingPageModel(
        "A Picture's Worth: Captivating Visuals in News",
        "Explore how impactful images shape news narratives, influence perspectives, and enhance storytelling, offering a glimpse into the power of visual journalism",
        R.drawable.news_image_intro
    ), OnboardingPageModel(
        "Navigating the News: Understanding Global Perspectives",
        "Delve into the complexities of global news consumption, highlighting the importance of diverse sources and perspectives in fostering a well-rounded understanding of world events.",
        R.drawable.news_navigate_world
    ), OnboardingPageModel(
        "The News Revolution: Transforming How We Stay Informed",
        "Discover how technological advancements and changing media landscapes are revolutionizing the way news is produced, consumed, and shared, reshaping the future of information dissemination.",
        R.drawable.news_revolution
    )
)

