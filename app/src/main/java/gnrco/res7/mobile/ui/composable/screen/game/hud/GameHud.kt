package gnrco.res7.mobile.ui.composable.screen.game.hud

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import gnrco.res7.mobile.ui.composable.screen.game.hud.content.GameHudContent
import gnrco.res7.mobile.ui.composable.screen.game.hud.shell.GameHudShell

@Composable
fun GameHud(
    item: GameHudItem,
    modifier: Modifier = Modifier,
) {
    GameHudShell(modifier = modifier) {
        GameHudContent(item = item)
    }
}
