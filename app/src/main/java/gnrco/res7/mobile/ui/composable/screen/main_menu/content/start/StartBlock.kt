package gnrco.res7.mobile.ui.composable.screen.main_menu.content.start

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import gnrco.res7.mobile.ui.composable.screen.main_menu.content.block.menuBlockMinHeight
import gnrco.res7.mobile.ui.composable.screen.main_menu.content.block.menuBlockSurface
import gnrco.res7.mobile.ui.composable.screen.main_menu.content.start.StartBlockItem
import gnrco.res7.mobile.ui.composable.screen.main_menu.content.start.startPulse
import gnrco.res7.mobile.ui.theme.LocalDimens

private val BadgeSize = 56.dp
private val BadgeIconSize = 36.dp

@Composable
fun StartBlock(
    item: StartBlockItem,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val dimens = LocalDimens.current
    val contentColor = MaterialTheme.colorScheme.onPrimaryContainer

    Row(
        modifier = modifier
            .fillMaxWidth()
            .startPulse(cornerRadius = dimens.cardCornerRadius)
            .menuBlockMinHeight()
            .menuBlockSurface(
                onClick = onClick,
                containerColor = MaterialTheme.colorScheme.primaryContainer,
            ),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(dimens.contentSpacing),
    ) {
        Box(
            modifier = Modifier
                .size(BadgeSize)
                .background(color = MaterialTheme.colorScheme.primary, shape = CircleShape),
            contentAlignment = Alignment.Center,
        ) {
            Icon(
                imageVector = Icons.Filled.PlayArrow,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.onPrimary,
                modifier = Modifier.size(BadgeIconSize),
            )
        }

        Column(verticalArrangement = Arrangement.spacedBy(dimens.tightSpacing)) {
            Text(
                text = item.text,
                color = contentColor,
                style = MaterialTheme.typography.titleLarge,
            )
            item.subtitle?.let { subtitle ->
                Text(
                    text = subtitle,
                    color = contentColor,
                    style = MaterialTheme.typography.bodyMedium,
                )
            }
        }
    }
}
