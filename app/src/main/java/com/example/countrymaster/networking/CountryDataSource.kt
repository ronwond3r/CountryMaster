import com.example.countrymaster.networking.CountryInfo
import com.example.countrymaster.networking.CountryApiService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


object CountryDataSource {
    private val retrofit = Retrofit.Builder()
        .baseUrl("https://restcountries.com")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private val countryService = retrofit.create(CountryApiService::class.java)

    suspend fun getCountryInfoByName(countryName: String): Result<CountryInfo> {
        return try {
            val response = countryService.getCountryInfo(countryName)
            if (response.isSuccessful) {
                val countryList = response.body()
                if (countryList.isNullOrEmpty()) {
                    Result.Error(Exception("Country not found"))
                } else {
                    Result.Success(countryList[0]) // Assuming the API returns a list, take the first item
                }
            } else {
                Result.Error(Exception("Failed to fetch country information"))
            }
        } catch (e: Exception) {
            Result.Error(e)
        }
    }
}
