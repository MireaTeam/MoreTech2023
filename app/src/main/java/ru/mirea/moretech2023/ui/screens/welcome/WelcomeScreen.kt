package ru.mirea.moretech2023.ui.screens.welcome

import android.content.res.Configuration
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SegmentedButton
import androidx.compose.material3.SegmentedButtonDefaults
import androidx.compose.material3.SingleChoiceSegmentedButtonRow
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ru.mirea.moretech2023.R
import ru.mirea.moretech2023.ui.theme.MoreTech2023Theme

@Composable
fun WelcomeScreen(
    navigateToServiceChoiceScreen: (
        latitude: String,
        longtitude: String,
        chosenTransportationMethod: String
    ) -> Unit
) {

    var selectedLocationMethodIndex by rememberSaveable { mutableIntStateOf(0) }
    var typedAddress by rememberSaveable { mutableStateOf("") }

    var selectedTransportationMethodIndex by remember { mutableIntStateOf(0) }

    MoreTech2023Theme {
        Surface(modifier = Modifier.fillMaxSize()) {
            Column {
                Column(Modifier.weight(1f)) {
                    WelcomeText()
                    ChooseAutoOrManualLocation(
                        Modifier.padding(16.dp),
                        { newIndex -> selectedLocationMethodIndex = newIndex },
                        selectedLocationMethodIndex
                    )

                    // TODO: Check address is not empty when click
                    AnimatedVisibility(visible = selectedLocationMethodIndex == 1) {
                        InputAddressManually(
                            value = typedAddress,
                            onValueChange = { typedAddress = it },
                            modifier = Modifier.padding(start = 16.dp)
                        )
                    }

                }

                ChooseWayOfTransportation(
                    { newIndex -> selectedTransportationMethodIndex = newIndex },
                    selectedTransportationMethodIndex,
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally)
                        .padding(bottom = 16.dp)
                )

                NextButton(
                    onClick = {
                        navigateToServiceChoiceScreen(
                            "-1",
                            "-1",
                            "BY_FOOT"
                        )
                    }, // TODO: Add navigation to next screen +
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally)
                        .padding(bottom = 16.dp)
                )
            }
        }
    }
}

@Preview(locale = "ru", uiMode = Configuration.UI_MODE_NIGHT_NO)
@Composable
fun WelcomeScreenPreview() {
    WelcomeScreen(
        { _, _, _ -> }
    )
}

@Composable
fun WelcomeMessage(modifier: Modifier = Modifier) {
    Text(
        text = stringResource(R.string.welcome),
        style = MaterialTheme.typography.titleLarge,
        modifier = modifier
    )
}

@Composable
fun WelcomeDescription(modifier: Modifier = Modifier) {
    Text(
        text = stringResource(R.string.welcome_description),
        style = MaterialTheme.typography.bodyLarge,
        modifier = modifier
    )
}

@Composable
fun WelcomeText(modifier: Modifier = Modifier) {
    Column(modifier = modifier.padding(16.dp)) {
        WelcomeMessage()
        WelcomeDescription(modifier = Modifier.padding(top = 8.dp))
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ChooseAutoOrManualLocation(
    modifier: Modifier = Modifier,
    onChoiceSelected: (Int) -> Unit,
    currentSelected: Int
) {

    val options = listOf(
        stringResource(R.string.nearby),
        stringResource(R.string.input_address),
    )

    Column(modifier = modifier) {
        Text(
            text = stringResource(R.string.look_for_offices),
            style = MaterialTheme.typography.titleMedium
        )

        SingleChoiceSegmentedButtonRow(Modifier.padding(8.dp)) {
            options.forEachIndexed { index, label ->
                SegmentedButton(
                    shape = SegmentedButtonDefaults.itemShape(index = index, count = options.size),
                    onClick = { onChoiceSelected(index) },
                    selected = index == currentSelected
                ) {
                    Text(label, style = MaterialTheme.typography.labelSmall)
                }
            }
        }
    }
}

@Composable
fun InputAddressManually(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    TextField(
        value = value,
        onValueChange = onValueChange,
        label = { Text(stringResource(R.string.input_address)) },
        modifier = modifier
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ChooseWayOfTransportation(
    onChoiceSelected: (Int) -> Unit,
    currentSelected: Int,
    modifier: Modifier = Modifier
) {

    val options = listOf(
        stringResource(R.string.on_foot),
        stringResource(R.string.by_car),
        stringResource(R.string.on_public_transport)
    )

    SingleChoiceSegmentedButtonRow(modifier = modifier) {
        options.forEachIndexed { index, label ->
            SegmentedButton(
                shape = SegmentedButtonDefaults.itemShape(index = index, count = options.size),
                onClick = { onChoiceSelected(index) },
                selected = index == currentSelected
            ) {
                Text(label, style = MaterialTheme.typography.labelSmall)
            }
        }
    }
}

@Composable
fun NextButton(onClick: () -> Unit, modifier: Modifier = Modifier) {
    FilledTonalButton(onClick = onClick, modifier = modifier.padding(8.dp)) {
        Row {
            Icon(
                painter = painterResource(id = R.drawable.baseline_navigate_next_24_white),
                contentDescription = null
            )
            Text(
                text = stringResource(R.string.next),
                style = MaterialTheme.typography.titleMedium,
                textAlign = TextAlign.Center,
                modifier = Modifier.align(Alignment.CenterVertically)
            )
        }
    }
}

