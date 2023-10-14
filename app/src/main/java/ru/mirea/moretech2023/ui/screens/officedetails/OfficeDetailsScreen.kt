package ru.mirea.moretech2023.ui.screens.officedetails

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import ru.mirea.moretech2023.repositories.VtbOfficeRepositoryDataSource
import ru.mirea.moretech2023.ui.theme.MoreTech2023Theme

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun OfficeDetailsScreen() {
    val testOffice = VtbOfficeRepositoryDataSource.listOfVtbOffices[0]

    MoreTech2023Theme {
        Surface(modifier = Modifier.fillMaxSize()) {
            Column {
                GlideImage(model = testOffice.imageUrl, contentDescription = null)
                Column(Modifier.padding(8.dp)) {
                    Text(text = testOffice.pointName)
                    Text(text = testOffice.address)
                    Text(text = testOffice.latitude)
                    Text(text = testOffice.longitude)
                    Text(text = testOffice.salePointFormat ?: "")
                    Text(text = testOffice.hasRko.toString())
                }
            }
        }
    }
}

@Preview
@Composable
fun OfficeDetailsScreenPreview() {
    OfficeDetailsScreen()
}