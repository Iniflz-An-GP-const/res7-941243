package gnrco.res7.mobile.ui.composable.screen.game

import androidx.compose.ui.graphics.vector.ImageVector
import gnrco.res7.mobile.data.model.CARD_SYMBOL_COUNT
import gnrco.res7.mobile.ui.composable.screen.game.symbols.AppCardSymbols

val CardSymbols: List<ImageVector> = AppCardSymbols.also {
    check(it.size == CARD_SYMBOL_COUNT) { "A card symbol set must have CARD_SYMBOL_COUNT icons, got ${it.size}" }
}
