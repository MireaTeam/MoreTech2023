package ru.mirea.moretech2023.ui.screens.welcome

import android.content.res.Configuration
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
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
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
fun WelcomeScreen() {
    MoreTech2023Theme {
        Surface(modifier = Modifier.fillMaxSize()) {
            Column {
                Column(Modifier.weight(1f)) {
                    WelcomeText()
                }

                ChooseWayOfTransportation(
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally)
                        .padding(bottom = 16.dp)
                )

                NextButton(
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
    WelcomeScreen()
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
fun ChooseWayOfTransportation(modifier: Modifier = Modifier) {
    var selectedIndex by remember { mutableIntStateOf(0) }
    val options = listOf(
        stringResource(R.string.on_foot),
        stringResource(R.string.by_car), stringResource(R.string.on_public_transport)
    )

    SingleChoiceSegmentedButtonRow(modifier = modifier) {
        options.forEachIndexed { index, label ->
            SegmentedButton(
                shape = SegmentedButtonDefaults.itemShape(index = index, count = options.size),
                onClick = { selectedIndex = index },
                selected = index == selectedIndex
            ) {
                Text(label, style = MaterialTheme.typography.labelSmall)
            }
        }
    }
}

@Composable
fun NextButton(modifier: Modifier = Modifier) {
    FilledTonalButton(onClick = { /*TODO*/ }, modifier = modifier.padding(8.dp)) {
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

