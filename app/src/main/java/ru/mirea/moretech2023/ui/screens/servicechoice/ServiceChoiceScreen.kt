package ru.mirea.moretech2023.ui.screens.servicechoice

import android.content.res.Configuration
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ru.mirea.moretech2023.R
import ru.mirea.moretech2023.network.VtbServicesDataSource
import ru.mirea.moretech2023.network.models.ServiceTypeByClient
import ru.mirea.moretech2023.network.models.VtbService
import ru.mirea.moretech2023.ui.theme.MoreTech2023Theme

@Composable
fun ServiceChoiceScreen() {
    val scrollState = rememberScrollState()

    MoreTech2023Theme {
        Surface(modifier = Modifier.fillMaxSize()) {
            Column(modifier = Modifier.verticalScroll(state = scrollState)) {
                TextChooseService(modifier = Modifier.padding(start = 16.dp, top = 16.dp))

                TextForIndividuals(modifier = Modifier.padding(start = 16.dp, top = 8.dp))
                ServiceSingleChoice(
                    requiredServiceType = ServiceTypeByClient.FOR_INDIVIDUALS,
                    modifier = Modifier.padding(start = 16.dp, top = 8.dp, end = 16.dp),
                    onChoiceClicked = {} // TODO: add
                )

                TextForBusiness(modifier = Modifier.padding(start = 16.dp, top = 8.dp))
                ServiceSingleChoice(
                    requiredServiceType = ServiceTypeByClient.FOR_BUSINESS,
                    modifier = Modifier.padding(start = 16.dp, top = 8.dp, end = 16.dp),
                    onChoiceClicked = {} // TODO: add
                )
            }
        }
    }

}

@Preview(locale = "ru", uiMode = Configuration.UI_MODE_NIGHT_NO)
@Composable
fun ServiceChoiceScreenPreview() {
    ServiceChoiceScreen()
}

@Composable
fun TextChooseService(modifier: Modifier = Modifier) {
    Text(
        text = stringResource(R.string.choose_service),
        style = MaterialTheme.typography.headlineMedium,
        modifier = modifier
    )
}

@Composable
fun TextForIndividuals(modifier: Modifier = Modifier) {
    Text(
        text = stringResource(R.string.for_individuals),
        style = MaterialTheme.typography.titleMedium,
        modifier = modifier
    )
}

@Composable
fun TextForBusiness(modifier: Modifier = Modifier) {
    Text(
        text = stringResource(R.string.for_business),
        style = MaterialTheme.typography.titleMedium,
        modifier = modifier
    )
}

@Composable
fun ServiceSingleChoice(
    modifier: Modifier = Modifier,
    serviceData: List<VtbService> = VtbServicesDataSource.vtbServicesList,
    requiredServiceType: ServiceTypeByClient,
    onChoiceClicked: (VtbService) -> Unit
) {

    val foundServices = serviceData.filter { it.type == requiredServiceType }

    for (service in foundServices) {
        ServiceCard(
            serviceData = service,
            onClick = { onChoiceClicked(service) },
            modifier = modifier
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ServiceCard(serviceData: VtbService, onClick: () -> Unit, modifier: Modifier = Modifier) {
    ElevatedCard(
        onClick = onClick,
        modifier = modifier,
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp, pressedElevation = 8.dp)
    ) {
        Text(
            text = serviceData.name,
            modifier = Modifier.padding(8.dp),
            style = MaterialTheme.typography.titleSmall
        )
    }
}

