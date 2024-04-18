package screens

import AppViewModel
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.geometry.center
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RadialGradientShader
import androidx.compose.ui.graphics.Shader
import androidx.compose.ui.graphics.ShaderBrush
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import data.Person
import org.jetbrains.compose.resources.ExperimentalResourceApi

@OptIn(ExperimentalResourceApi::class, ExperimentalLayoutApi::class)
@Composable
fun FactsScreen(appViewModel: AppViewModel) {

    val persons by appViewModel.facts.collectAsState(emptyList())

    FlowRow(
        modifier = Modifier.fillMaxSize().padding(10.dp),
        maxItemsInEachRow = 6,
        horizontalArrangement = Arrangement.Center,
        verticalArrangement = Arrangement.Center
    ) {
        persons.onEach {
            PersonFactItem(it) { appViewModel.onSelectPersonFact(it) }
        }
    }
}


private val largeRadialGradient = object : ShaderBrush() {
    private val c = Color(0xfd384eaf)
    private val a = Color(0xFF243484)
    override fun createShader(size: Size): Shader {
        val biggerDimension = maxOf(size.height, size.width)
        return RadialGradientShader(
            colors = listOf(c, a),
            center = size.center,
            radius = biggerDimension / 2f,
            colorStops = listOf(0f, 0.95f)
        )
    }
}



private fun Modifier.backgroundCustom(isEnable : Boolean) : Modifier = when(isEnable) {
    true -> this.background(brush = largeRadialGradient, shape = RoundedCornerShape(16.dp))
    false -> this.background(color = Color.Gray, shape = RoundedCornerShape(16.dp))
}

@Composable
private fun PersonFactItem(person: Person, onClick: () -> Unit) {
    Box(
        modifier = Modifier.padding(16.dp),
        contentAlignment = Alignment.Center
    ) {
        Box(
            modifier = Modifier
                .size(width = 550.dp, 100.dp)
                .backgroundCustom(!person.guessed)
                .clickable(enabled = !person.guessed, onClick = onClick),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = person.fact,
                modifier = Modifier.padding(4.dp),
                color = Color.White,
                textAlign = TextAlign.Center,
                fontSize = 24.sp,
                lineHeight = 32.sp
            )
        }
    }
}