package screens

import AppViewModel
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mobicon.intuition.intuition.generated.resources.Res
import com.mobicon.intuition.intuition.generated.resources.bckgr_light
import data.Person
import data.ScreenState
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.painterResource

@OptIn(ExperimentalLayoutApi::class, ExperimentalResourceApi::class)
@Composable
fun UsersScreen(appViewModel: AppViewModel) {

    val persons by appViewModel.icons.collectAsState(emptyList())
    val selectFact by appViewModel.selectedPersonFact.collectAsState()

    Column(modifier = Modifier.fillMaxSize().padding(100.dp)) {
        if (selectFact == null) {
            Button(
                onClick = { appViewModel.changeScreen(ScreenState.Facts) },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = "Play"
                )
            }
        } else {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(color = Color(0x992C2C2E))
                    .border(width = 4.dp, color = Color.White, shape = RoundedCornerShape(8.dp))
                    .clip(shape = RoundedCornerShape(8.dp))
                    .padding(24.dp)
            ) {
                Text(
                    text = selectFact!!.fact,
                    modifier = Modifier.fillMaxWidth().align(Alignment.BottomCenter).padding(vertical = 12.dp),
                    color = Color.White,
                    fontSize = 48.sp,
                    textAlign = TextAlign.Center
                )
            }
        }

        Spacer(modifier = Modifier.fillMaxWidth().height(10.dp))

        FlowRow(
            modifier = Modifier.fillMaxSize(),
            maxItemsInEachRow = 6,
            horizontalArrangement = Arrangement.Center,
            verticalArrangement = Arrangement.Center
        ) {
            persons.onEach { PersonItem(it) { appViewModel.checkPersonFact(it) } }
        }
    }


}

@OptIn(ExperimentalResourceApi::class, ExperimentalFoundationApi::class)
@Composable
private fun PersonItem(
    person: Person,
    onLongClick: () -> Unit
) {


    val icon = when (person.guessed) {
        true -> painterResource(Res.drawable.bckgr_light)
        false -> painterResource(person.image)
    }

    Box(
        modifier = Modifier
            .size(200.dp, 250.dp)
            .padding(25.dp)
            .border(width = 4.dp, color = Color.White, shape = RoundedCornerShape(8.dp))
            .clip(shape = RoundedCornerShape(8.dp))
    ) {
        Image(
            painter = icon,
            modifier = Modifier
                .fillMaxSize()
                .align(Alignment.Center)
                .combinedClickable(
                    enabled = true,
                    onClick = {},
                    onDoubleClick = {},
                    onLongClick = onLongClick
                ),
            contentDescription = "",
            contentScale = ContentScale.Crop
        )
        if(!person.guessed) {
            Text(
                text = person.name,
                modifier = Modifier.background(color = Color(0x992C2C2E)).fillMaxWidth().align(Alignment.BottomCenter).padding(vertical = 12.dp),
                color = Color.White,
                fontSize = 24.sp,
                textAlign = TextAlign.Center
            )
        }
    }
}
