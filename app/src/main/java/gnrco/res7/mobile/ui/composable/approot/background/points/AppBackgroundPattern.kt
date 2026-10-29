package gnrco.res7.mobile.ui.composable.approot.background.points

import androidx.compose.ui.unit.dp
import gnrco.res7.mobile.ui.composable.approot.background.BackgroundPattern
import gnrco.res7.mobile.ui.composable.approot.background.gridAnchors

/** Many small icons in a tight 6 x 12 grid — a fine texture. */
val AppBackgroundPattern = BackgroundPattern(
    anchors = gridAnchors(columns = 6, rows = 12),
    iconSize = 24.dp,
)
