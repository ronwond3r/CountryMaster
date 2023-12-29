import com.example.countrymaster.networking.CountryInfo
import com.example.countrymaster.networking.CountryApiService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import com.example.countrymaster.data.Result

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
                val countryInfo = response.body()
                if (countryInfo != null) {
                    Result.Success(countryInfo)
                } else {
                    Result.Error(Exception("Country not found"))
                }
            } else {
                Result.Error(Exception("Failed to fetch country information"))
            }
        } catch (e: Exception) {
            Result.Error(e)
        }
    }
}
