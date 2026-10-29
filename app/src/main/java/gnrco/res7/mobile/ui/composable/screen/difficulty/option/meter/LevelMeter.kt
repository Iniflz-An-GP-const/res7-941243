package gnrco.res7.mobile.ui.composable.screen.difficulty.option.meter

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.LocalContentColor
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import gnrco.res7.mobile.ui.composable.screen.difficulty.option.DifficultyOptionDefaults

private val BarWidth = 7.dp
private val BarGap = 3.dp
private val MinBarHeight = 8.dp
private val BarHeightRange = 16.dp

@Composable
fun LevelMeter(
    level: Int,
    levelCount: Int,
    modifier: Modifier = Modifier,
) {
    val color = LocalContentColor.current
    val shape = RoundedCornerShape(2.dp)

    Row(
        modifier = modifier.height(MinBarHeight + BarHeightRange),
        horizontalArrangement = Arrangement.spacedBy(BarGap),
        verticalAlignment = Alignment.Bottom,
    ) {
        repeat(levelCount) { index ->
            Box(
                modifier = Modifier
                    .width(BarWidth)
                    .height(MinBarHeight + BarHeightRange * ((index + 1f) / levelCount))
                    .background(
                        color = if (index < level) color else color.copy(alpha = DifficultyOptionDefaults.INACTIVE_ALPHA),
                        shape = shape,
                    ),
            )
        }
    }
}
