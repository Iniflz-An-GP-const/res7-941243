package gnrco.res7.mobile.ui.composable.screen.main_menu.content.scores

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import gnrco.res7.mobile.ui.composable.screen.main_menu.content.block.shell.MenuBlockShell
import gnrco.res7.mobile.ui.composable.screen.main_menu.content.scores.ScoresBlockContent

@Composable
fun ScoresBlock(
    item: ScoresBlockItem,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    MenuBlockShell(onClick = onClick, emphasis = item.emphasis, modifier = modifier) {
        ScoresBlockContent(item = item)
    }
}
