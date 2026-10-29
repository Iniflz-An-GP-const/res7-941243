package gnrco.res7.mobile.ui.composable.approot.background

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import gnrco.res7.mobile.ui.composable.approot.background.BackgroundPattern
import gnrco.res7.mobile.ui.composable.approot.background.PlacedIcon
import gnrco.res7.mobile.ui.composable.approot.background.placeBackgroundIcons
import kotlin.math.PI
import kotlin.math.sin
import kotlin.random.Random

private const val SCALE_AMPLITUDE = 0.15f

/**
 * One shared clock runs in seconds and loops after [CLOCK_LOOP_SECONDS]; every period below
 * divides it evenly, so the loop never makes a visible jump.
 */
private const val CLOCK_LOOP_SECONDS = 1_000f
private val PeriodsSeconds = listOf(4f, 5f, 8f)

private data class PulseTiming(
    val periodSeconds: Float,
    /** Where in its cycle the icon starts, 0..1, so the icons do not swell in unison. */
    val phase: Float,
)

@Composable
fun AppBackgroundDecor(pattern: BackgroundPattern, modifier: Modifier = Modifier) {
    val items = remember(pattern) {
        val random = Random(System.nanoTime())
        placeBackgroundIcons(random, pattern).map { item ->
            item to PulseTiming(
                periodSeconds = PeriodsSeconds.random(random),
                phase = random.nextFloat(),
            )
        }
    }

    val clock = rememberInfiniteTransition(label = "menuBackgroundPulse").animateFloat(
        initialValue = 0f,
        targetValue = CLOCK_LOOP_SECONDS,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = (CLOCK_LOOP_SECONDS * 1_000).toInt(), easing = LinearEasing),
            repeatMode = RepeatMode.Restart,
        ),
        label = "clock",
    )

    Box(modifier = modifier) {
        items.forEach { (item, timing) -> PulseDecorIcon(item, timing, clock = { clock.value }) }
    }
}

@Composable
private fun BoxScope.PulseDecorIcon(
    item: PlacedIcon,
    timing: PulseTiming,
    clock: () -> Float,
) {
    Icon(
        imageVector = item.icon,
        contentDescription = null,
        tint = MaterialTheme.colorScheme.onBackground,
        modifier = Modifier
            .align(item.anchor)
            .size(item.size)
            .graphicsLayer {
                val cycle = clock() / timing.periodSeconds + timing.phase
                val scale = 1f + SCALE_AMPLITUDE * sin(2f * PI.toFloat() * cycle)
                scaleX = scale
                scaleY = scale
                alpha = item.baseAlpha
            },
    )
}
