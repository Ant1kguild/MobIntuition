package screens

import AppViewModel
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import data.Person
import data.ScreenState
import org.jetbrains.compose.resources.ExperimentalResourceApi

@OptIn(ExperimentalResourceApi::class, ExperimentalLayoutApi::class)
@Composable
fun FactsScreen(appViewModel: AppViewModel) {

    val persons by appViewModel.facts.collectAsState(emptyList())

    FlowRow(
        modifier = Modifier.fillMaxSize().padding(100.dp),
        maxItemsInEachRow = 6,
        horizontalArrangement = Arrangement.Center,
        verticalArrangement = Arrangement.Center
    ) {
        persons.onEach {
            PersonFactItem(it) { appViewModel.onSelectPersonFact(it) }
        }
    }
}

@Composable
private fun PersonFactItem(person: Person, onClick: () -> Unit) {
    Box(modifier = Modifier.padding(20.dp)) {
        Button(
            onClick = onClick,
            enabled = !person.guessed,
            modifier = Modifier.size(width = 650.dp, 50.dp)
        ) {
            Text(
                text = person.fact,
                fontSize = 24.sp
            )
        }
    }
}