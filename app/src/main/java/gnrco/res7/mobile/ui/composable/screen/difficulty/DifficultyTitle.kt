package gnrco.res7.mobile.ui.composable.screen.difficulty

import gnrco.res7.mobile.R
import gnrco.res7.mobile.data.model.Difficulty

fun Difficulty.titleRes(): Int = when (this) {
    Difficulty.Easy -> R.string.difficulty_easy
    Difficulty.Medium -> R.string.difficulty_medium
    Difficulty.Hard -> R.string.difficulty_hard
}
