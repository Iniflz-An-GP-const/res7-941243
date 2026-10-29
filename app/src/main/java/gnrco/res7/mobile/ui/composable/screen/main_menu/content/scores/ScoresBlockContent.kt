package gnrco.res7.mobile.ui.composable.screen.main_menu.content.scores

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.widthIn
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import gnrco.res7.mobile.ui.composable.screen.main_menu.content.block.MenuBlockDefaults
import gnrco.res7.mobile.ui.composable.screen.main_menu.content.scores.ScoresBlockItem
import gnrco.res7.mobile.ui.composable.screen.main_menu.content.scores.ScoresStat
import gnrco.res7.mobile.ui.composable.screen.main_menu.content.scores.hiddenWhen
import gnrco.res7.mobile.ui.theme.LocalDimens

private const val PlaceholderValue = "–"
private val StatMinWidth = 56.dp

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun ScoresBlockContent(
    item: ScoresBlockItem,
    modifier: Modifier = Modifier,
) {
    val dimens = LocalDimens.current

    Column(
        modifier = modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(dimens.itemSpacing),
    ) {
        FlowRow(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(
                dimens.itemSpacing,
                Alignment.CenterHorizontally
            ),
            verticalArrangement = Arrangement.spacedBy(dimens.tightSpacing),
        ) {
            Icon(
                imageVector = item.icon,
                contentDescription = null,
                modifier = Modifier.size(MenuBlockDefaults.IconSize),
            )
            Text(
                text = item.title,
                style = MaterialTheme.typography.titleMedium,
                textAlign = TextAlign.Center,
            )
        }

        // The numbers are always laid out, so the block keeps one height with or without
        // records; while empty they are invisible and the hint sits over them
        Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
            FlowRow(
                modifier = Modifier
                    .fillMaxWidth()
                    .hiddenWhen(item.isEmpty),
                horizontalArrangement = Arrangement.spacedBy(
                    dimens.contentSpacing,
                    Alignment.CenterHorizontally
                ),
                verticalArrangement = Arrangement.spacedBy(dimens.itemSpacing),
            ) {
                StatColumn(stat = item.moves)
                StatColumn(stat = item.time)
            }

            if (item.isEmpty) {
                Text(
                    text = item.emptyHint,
                    color = LocalContentColor.current.copy(alpha = MenuBlockDefaults.SecondaryAlpha),
                    style = MaterialTheme.typography.bodyMedium,
                    textAlign = TextAlign.Center,
                )
            }
        }
    }
}

@Composable
private fun StatColumn(stat: ScoresStat) {
    Column(
        modifier = Modifier.widthIn(min = StatMinWidth),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(LocalDimens.current.tightSpacing),
    ) {
        Text(
            text = stat.value ?: PlaceholderValue,
            style = MaterialTheme.typography.headlineMedium,
            textAlign = TextAlign.Center,
        )
        Text(
            text = stat.label,
            color = LocalContentColor.current.copy(alpha = MenuBlockDefaults.SecondaryAlpha),
            style = MaterialTheme.typography.labelMedium,
            textAlign = TextAlign.Center,
        )
    }
}
