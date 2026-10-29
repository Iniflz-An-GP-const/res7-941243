package gnrco.res7.mobile.ui.composable.screen.main_menu.content.start.pulse.timing

import gnrco.res7.mobile.ui.composable.screen.main_menu.content.start.pulse.PulseTiming

/** The standard tempo: a moderate pulse, a short rest, the standard amplitude. */
val StartPulseTiming = PulseTiming(
    durationMs = 1_400,
    pauseMs = 800,
    intensity = 1f,
)
