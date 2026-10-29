package gnrco.res7.mobile.ui.composable.screen.game.card.feedback.mismatch

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.keyframes
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import gnrco.res7.mobile.ui.composable.screen.game.card.feedback.CardFeedbackDefaults
import kotlinx.coroutines.delay
import kotlin.time.Duration.Companion.milliseconds

private const val WOBBLE_DEGREES = 7f

/** Two unmatched cards rock to and fro around their centers, the swing dying down. */
@Composable
fun Modifier.mismatchEffect(isMismatched: Boolean): Modifier {
    val swing = remember { Animatable(0f) }

    LaunchedEffect(isMismatched) {
        if (isMismatched) {
            delay(CardFeedbackDefaults.MismatchDelayMs.milliseconds)
            swing.animateTo(
                targetValue = 0f,
                animationSpec = keyframes {
                    durationMillis = CardFeedbackDefaults.MismatchDurationMs
                    -1f at 60
                    1f at 150
                    -0.7f at 230
                    0.7f at 300
                    -0.3f at 360
                },
            )
        } else {
            swing.snapTo(0f)
        }
    }

    return graphicsLayer { rotationZ = swing.value * WOBBLE_DEGREES }
}
