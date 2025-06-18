package qz.github.model

import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import android.content.Context // Keep if you intend to use Context elsewhere, though it's not used in the original Java code

// Assuming 'Github' interface is defined elsewhere, something like:
// import retrofit2.http.GET
// import retrofit2.http.Path
// interface Github {
//     @GET("users/{username}")
//     fun getInfo(@Path("username") username: String): Call<ProfilInfo>
// }

class Requestsku(private val username: String) {

    private val baseUrl = "https://api.github.com/"
    private lateinit var retrofit: Retrofit // lateinit as it's initialized in init()
    var result: ProfilInfo? = null // Made nullable as the initial value is null and it might fail to fetch

    // Using a companion object for the Retrofit instance can be more efficient
    // if you have multiple requests using the same Retrofit setup.
    // However, for direct conversion, we'll keep it as an instance variable as in Java.

    init {
        // The constructor in Kotlin is where you'd typically initialize properties.
        // We can move the retrofit initialization here, or keep it in a separate `init` method
        // for more control (e.g., if it depends on a network check, which is not in the original).
        // For now, let's keep it similar to the Java structure with a separate `init` function.
    }

    fun init() {
        retrofit = Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        // The original Java code performs a synchronous network request on the main thread,
        // which is bad practice and would cause an Application Not Responding (ANR) error
        // on Android. For a direct conversion, we'll keep it synchronous but emphasize
        // that this should be run on a background thread.
        // In a real Android application, you would use Coroutines, RxJava, or Retrofit's
        // asynchronous `enqueue` method.

        val call = retrofit.create(Github::class.java).getInfo(username)
        try {
            // This is a synchronous call. In a real Android app, this must be run on a background thread.
            // For example, using Kotlin Coroutines:
            // result = withContext(Dispatchers.IO) { call.execute().body() }
            result = call.execute().body()
        } catch (e: Exception) {
            e.printStackTrace()
            result = null // Ensure result is null if there's an error
        }
    }

    // A suspend function is a better way to handle network requests in Kotlin with coroutines
    // as it allows asynchronous operations to be written in a sequential style.
    suspend fun fetchProfileInfo(): ProfilInfo? = withContext(Dispatchers.IO) {
        retrofit = Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val call = retrofit.create(Github::class.java).getInfo(username)
        return@withContext try {
            call.execute().body()
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }

    fun getResult(): ProfilInfo? {
        return result
    }
}
