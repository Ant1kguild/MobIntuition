package screens

import AppViewModel
import androidx.compose.animation.*
import androidx.compose.animation.core.tween
import androidx.compose.foundation.*
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.PointerEventType
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mobicon.intuition.intuition.generated.resources.Res
import com.mobicon.intuition.intuition.generated.resources.splash
import data.Person
import data.ScreenState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.painterResource
import org.lighthousegames.logging.logging
import sound.rememberSoundController

@OptIn(ExperimentalLayoutApi::class, ExperimentalResourceApi::class)
@Composable
fun UsersScreen(appViewModel: AppViewModel) {

    val persons by appViewModel.icons.collectAsState(emptyList())
    val selectFact by appViewModel.selectedPersonFact.collectAsState()

    Column(
        modifier = Modifier.padding(50.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        if (selectFact != null) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(color = Color(0x992C2C2E))
                    .border(width = 4.dp, color = Color.White, shape = RoundedCornerShape(8.dp))
                    .clip(shape = RoundedCornerShape(8.dp))
                    .padding(12.dp),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = selectFact!!.fact,
                    modifier = Modifier.padding(vertical = 12.dp),
                    color = Color.White,
                    fontSize = 36.sp,
                    textAlign = TextAlign.Center
                )
            }
        }

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

    val sound = rememberSoundController("files/sound_wait.mp3")

    val icon = when (person.guessed) {
        true -> painterResource(Res.drawable.splash)
        false -> painterResource(person.image)
    }

    var isSelected by remember { mutableStateOf(false) }
    val borderColor = remember(isSelected) {
        when (isSelected) {
            true -> Color.Cyan
            false -> Color.White
        }
    }

    Box(
        modifier = Modifier
            .size(240.dp, 300.dp)
            .padding(20.dp)
            .border(width = 4.dp, color = borderColor, shape = RoundedCornerShape(8.dp))
            .clip(shape = RoundedCornerShape(8.dp))
    ) {
        AnimatedContent(
            targetState = icon,
            transitionSpec = {
                (fadeIn(animationSpec = tween(400)) + scaleIn(animationSpec = tween(400))).togetherWith(
                    fadeOut(animationSpec = tween(400)) + scaleOut(
                        animationSpec = tween(400)
                    )
                )
            }
        ) {
            Image(
                painter = it,
                modifier = Modifier
                    .fillMaxSize()
                    .align(Alignment.Center)
                    .pointerInput(Unit) {
                        awaitPointerEventScope {

                            while (true) {
                                val event = awaitPointerEvent()

                                when(event.type) {
                                    PointerEventType.Enter -> {
                                        isSelected = true
                                        sound.playSound()
                                    }
                                    PointerEventType.Exit -> {
                                        isSelected = false
                                        sound.stopPlaySound()
                                    }
                                }
                            }
                        }
                    }
                    .combinedClickable(
                        enabled = true,
                        onClick = {},
                        onDoubleClick = {},
                        onLongClick = { onLongClick.invoke() }
                    ),
                contentDescription = "",
                contentScale = ContentScale.Inside
            )
        }

        if (!person.guessed) {
            Text(
                text = person.name,
                modifier = Modifier
                    .background(color = Color(0x992C2C2E))
                    .fillMaxWidth().align(Alignment.BottomCenter)
                    .padding(vertical = 12.dp, horizontal = 6.dp),
                color = Color.White,
                fontSize = 24.sp,
                textAlign = TextAlign.Center
            )
        }
    }
}
