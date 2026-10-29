package gnrco.res7.mobile.ui.composable.screen.difficulty.option.content

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import gnrco.res7.mobile.ui.composable.screen.difficulty.option.DifficultyOptionDefaults
import gnrco.res7.mobile.ui.composable.screen.difficulty.option.DifficultyOptionItem
import gnrco.res7.mobile.ui.theme.LocalDimens

@Composable
fun DifficultyOptionContent(
    item: DifficultyOptionItem,
    meter: @Composable () -> Unit,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(LocalDimens.current.contentSpacing),
    ) {
        Column(modifier = Modifier.weight(1f)) {
            Text(text = item.title, style = DifficultyOptionDefaults.titleStyle)
            Text(
                text = DifficultyOptionDefaults.subtitle(item),
                color = DifficultyOptionDefaults.secondaryContentColor,
                style = DifficultyOptionDefaults.subtitleStyle,
            )
        }

        meter()
    }
}
