import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.countrymaster.ui.screens.CountryInfoScreen
import com.example.countrymaster.ui.theme.CountryMasterTheme
import com.example.countrymaster.networking.CountryInfo

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CountryMasterTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    // Example country info, replace with actual data from your API
                    val countryInfo = CountryInfo(
                        "USA",
                        "United States of America",
                        "USD",
                        "Americas",
                        "Washington, D.C.",
                        listOf("CAN", "MEX"),
                        9833517.0,
                        331449281,
                        "green, white, green"
                    )

                    // Display the CountryInfoScreen with the example country info
                    CountryInfoScreen(countryInfo = countryInfo, onBackClicked = {})
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MainActivityPreview() {
    CountryMasterTheme {
        // Example country info, replace with actual data from your API
        val countryInfo = CountryInfo(
            "USA",
            "United States of America",
            "USD",
            "Americas",
            "Washington, D.C.",
            listOf("CAN", "MEX"),
            9833517.0,
            331449281,
            "green, white, green"
        )

        // Display the CountryInfoScreen with the example country info in the preview
        CountryInfoScreen(countryInfo = countryInfo, onBackClicked = {})
    }
}
