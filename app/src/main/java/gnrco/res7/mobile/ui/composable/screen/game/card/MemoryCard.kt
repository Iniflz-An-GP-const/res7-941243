package gnrco.res7.mobile.ui.composable.screen.game.card

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.LocalIndication
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp
import gnrco.res7.mobile.data.model.CardState
import gnrco.res7.mobile.ui.composable.screen.game.card.MemoryCardBack
import gnrco.res7.mobile.ui.composable.screen.game.card.MemoryCardDefaults
import gnrco.res7.mobile.ui.composable.screen.game.card.MemoryCardFace
import gnrco.res7.mobile.ui.composable.screen.game.card.MemoryCardItem
import gnrco.res7.mobile.ui.composable.screen.shared.modifier.press.pressFeedback

private const val FLIP_DURATION_MS = 350
private const val CAMERA_DISTANCE_DP = 12f

@Composable
fun MemoryCard(
    item: MemoryCardItem,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val interactionSource = remember { MutableInteractionSource() }
    val rotation by animateFloatAsState(
        targetValue = if (item.state == CardState.Hidden) 0f else 180f,
        animationSpec = tween(durationMillis = FLIP_DURATION_MS),
        label = "cardFlip",
    )
    val cameraDistance = with(LocalDensity.current) { CAMERA_DISTANCE_DP.dp.toPx() }

    Box(
        modifier = modifier
            .pressFeedback(interactionSource)
            .graphicsLayer {
                rotationY = rotation
                this.cameraDistance = cameraDistance
            }
            .clip(MemoryCardDefaults.shape)
            .clickable(
                interactionSource = interactionSource,
                indication = LocalIndication.current,
                enabled = item.state == CardState.Hidden,
                onClick = onClick,
            ),
    ) {
        if (rotation <= 90f) {
            MemoryCardBack()
        } else {
            // The face is mirrored by the 180° turn, so turn it back
            MemoryCardFace(
                symbol = item.symbol,
                state = item.state,
                modifier = Modifier.graphicsLayer { rotationY = 180f },
            )
        }
    }
}
