package gnrco.res7.mobile.ui.composable.screen.game.card

import androidx.compose.ui.graphics.vector.ImageVector
import gnrco.res7.mobile.data.model.CardState

data class MemoryCardItem(
    val symbol: ImageVector,
    val state: CardState,
)
