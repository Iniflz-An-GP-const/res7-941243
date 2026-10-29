package gnrco.res7.mobile.ui.composable.screen.game.card.feedback.deal

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp
import gnrco.res7.mobile.ui.composable.screen.game.card.feedback.CardFeedbackDefaults
import kotlinx.coroutines.delay
import kotlin.time.Duration.Companion.milliseconds

private val DropHeight = 56.dp

@Composable
fun Modifier.dealEffect(index: Int, round: Int): Modifier {
    val progress = remember { Animatable(0f) }
    val dropPx = with(LocalDensity.current) { DropHeight.toPx() }

    LaunchedEffect(round) {
        progress.snapTo(0f)
        delay((index * CardFeedbackDefaults.DealStaggerMs).coerceAtMost(CardFeedbackDefaults.DealMaxDelayMs).milliseconds)
        progress.animateTo(
            targetValue = 1f,
            animationSpec = spring(
                dampingRatio = Spring.DampingRatioMediumBouncy,
                stiffness = Spring.StiffnessLow
            ),
        )
    }

    return graphicsLayer {
        val value = progress.value
        translationY = -dropPx * (1f - value)
        alpha = value.coerceIn(0f, 1f)
    }
}
