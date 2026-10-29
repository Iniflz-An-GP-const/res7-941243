package gnrco.res7.mobile

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import gnrco.res7.mobile.ui.composable.approot.AppRoot
import gnrco.res7.mobile.ui.theme.GCEOEAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            GCEOEAppTheme {
                AppRoot()
            }
        }
    }
}
