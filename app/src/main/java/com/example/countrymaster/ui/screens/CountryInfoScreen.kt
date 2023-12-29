package com.example.countrymaster.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import com.example.countrymaster.CountryViewModel
import com.example.countrymaster.R
import com.example.countrymaster.networking.CountryInfo


@Composable
fun CountryInfoScreen(
    countryInfo: CountryInfo,
    onBackClicked: () -> Unit
) {
    val scrollState = rememberLazyListState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        // Display country flag
        CountryFlag(countryInfo.flag)

        // Display country name
        CountryName(
            officialName = countryInfo.officialName,
            nativeName = countryInfo.nativeName
        )

        // Display country details
        CountryDetails(countryInfo)
    }
}


@Composable
private fun LocationTextField(
    location: String,
    onLocationChanged: (String) -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 4.dp)
    ) {
        Column {
            Text(
                "Location",
                modifier = Modifier.padding(start = 4.dp)
            )
            BasicTextField(
                value = location,
                onValueChange = { onLocationChanged(it) },
                textStyle = TextStyle(color = Color.Black),
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.White, shape = RoundedCornerShape(8.dp))
                    .border(1.dp, Color.Gray, shape = RoundedCornerShape(8.dp))
                    .padding(horizontal = 8.dp, vertical = 4.dp)
            )
        }
    }
}



@Composable
private fun SubmitButton(vm: CountryViewModel, name: String) {
    Button(
        onClick = {
            vm.fetchCountryInfoByName(name)
        },
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 16.dp)
    ) {
        Text("Submit")
    }
}

@Composable
private fun CountryFlag(flagUrl: String) {
    // TODO: Implement loading and displaying the country flag using the provided flagUrl
    // You can use an Image composable or any other approach to load and display the image
    // For simplicity, let's assume the flagUrl is a valid URL to the image
    Image(
        painter = rememberImagePainter(data = flagUrl, builder = {
            // You can customize image loading parameters here if needed
        }),
        contentDescription = null, // Provide a meaningful description if needed
        modifier = Modifier
            .fillMaxWidth()
            .height(200.dp) // Adjust the height as needed
            .clip(shape = RoundedCornerShape(8.dp)) // Clip the image with rounded corners
    )
}

    @Composable
private fun CountryName(officialName: String, nativeName: String) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 16.dp)
    ) {
        Text(
            text = officialName,
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.primary
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = nativeName,
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.primary
        )
    }
}

@Composable
private fun CountryDetails(countryInfo: CountryInfo) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 16.dp)
    ) {
        CountryDetailItem(label = "Currency", value = countryInfo.currency)
        CountryDetailItem(label = "Region", value = countryInfo.region)
        CountryDetailItem(label = "Capital", value = countryInfo.capital)
        CountryDetailItem(label = "Borders", value = countryInfo.borders.joinToString())
        CountryDetailItem(label = "Area", value = "${countryInfo.area} square km")
        CountryDetailItem(label = "Population", value = "${countryInfo.population} people")
    }
}

@Composable
private fun CountryDetailItem(label: String, value: String) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 8.dp)
    ) {
        Text(
            text = label,
            style = MaterialTheme.typography.labelMedium,
            color = MaterialTheme.colorScheme.onSurface
        )
        Text(
            text = value,
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurface
        )
    }
}
@Preview(showBackground = true)
@Composable
fun CountryInfoScreen() {
    val countryInfo = CountryInfo(
        officialName = "United States of America",
        nativeName = "USA",
        currency = "USD",
        region = "Americas",
        capital = "Washington, D.C.",
        borders = listOf("CAN", "MEX"),
        area = 9833517.0,
        population = 331449281,
        flag = "https://restcountries.com/v3/alpha/usa/flag.png" // Replace with the actual flag URL
    )

    CountryInfoScreen(countryInfo = countryInfo, onBackClicked = {})
}
