package gnrco.res7.mobile.ui.composable.screen.game.card.feedback.match

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import gnrco.res7.mobile.ui.composable.screen.game.card.feedback.CardFeedbackDefaults
import kotlinx.coroutines.delay
import kotlin.time.Duration.Companion.milliseconds

private const val PEAK_SCALE = 1.15f
private const val RISE_MS = 110

@Composable
fun Modifier.matchEffect(isMatched: Boolean): Modifier {
    val scale = remember { Animatable(1f) }

    LaunchedEffect(isMatched) {
        if (isMatched) {
            delay(CardFeedbackDefaults.MatchDelayMs.milliseconds)
            scale.animateTo(PEAK_SCALE, tween(durationMillis = RISE_MS))
            scale.animateTo(1f, spring(dampingRatio = Spring.DampingRatioMediumBouncy))
        } else {
            scale.snapTo(1f)
        }
    }

    return graphicsLayer {
        scaleX = scale.value
        scaleY = scale.value
    }
}
