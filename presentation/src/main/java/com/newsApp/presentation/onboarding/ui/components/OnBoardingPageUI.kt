package com.newsApp.presentation.onboarding.ui.components

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
import com.newsApp.domain.models.onboarding.PageEntity
import com.newsApp.presentation.R
import com.newsApp.presentation.ui.Dimensions.LargePadding
import com.newsApp.presentation.ui.Dimensions.MediumPadding

@Composable
fun OnBoardingPageUI(
    modifier: Modifier = Modifier,
    page: PageEntity,
) {
    Column(modifier = modifier) {
        Image(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(fraction = 0.6f),
            painter = painterResource(id = page.image),
            contentDescription = null,
            contentScale = ContentScale.Crop
        )
        Spacer(modifier = Modifier.height(MediumPadding))
        Text(
            text = page.title,
            modifier = Modifier.padding(horizontal = LargePadding),
            style = MaterialTheme.typography.displaySmall.copy(
                fontWeight = FontWeight.Bold,
                color = colorResource(id = R.color.display_small)
            )
        )
        Text(
            text = page.description,
            modifier = Modifier.padding(horizontal = LargePadding),
            style = MaterialTheme.typography.bodyMedium,
            color = colorResource(id = R.color.text_medium)
        )
    }
}

@Preview(showBackground = true)
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES, showBackground = true)
@Composable
fun OnBoardingPageUIPreview() {
    OnBoardingPageUI(
        page = PageEntity(
            title = "Title",
            description = "Description",
            image = R.drawable.logo
        )
    )
}