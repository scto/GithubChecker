package qz.github

import android.content.Context
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import qz.github.databinding.ActivityMainBinding
import qz.github.model.ProfilInfo
import qz.github.model.Requestsku
import qz.github.myUtils.UserParse
import qz.github.viewmodel.MAdapter // Corrected import to MAdapter
import java.util.ArrayList
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors // For creating a thread pool, a better alternative to raw Threads

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding // Use lateinit var for properties initialized in onCreate
    private lateinit var lprofilinfo: ArrayList<ProfilInfo>
    private lateinit var adapter: MAdapter // Use lateinit var
    private val TAGS = this::class.java.simpleName // More idiomatic way to get a class tag

    // Consider using a ViewModel and Coroutines for better architecture in a real app
    // private val executorService: ExecutorService = Executors.newFixedThreadPool(4) // Example for managing threads

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        lprofilinfo = ArrayList()
        adapter = MAdapter(lprofilinfo) // Use MAdapter
        binding.listView.adapter = adapter
        binding.listView.layoutManager = LinearLayoutManager(this)

        binding.inputName.setText("https://github.com/QiubyZ")

        binding.start.setOnClickListener {
            lprofilinfo.clear() // Clear existing data
            val urls = binding.inputName.text.toString().split("\n")
            for (url in urls) {
                executors(UserParse.parseUrl(url))
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        // No need to set binding to null with ViewBinding, as it's typically managed by the lifecycle.
        // If you were using data binding or had more complex scenarios, you might.
        // However, for ViewBinding, it's generally handled automatically.
        // For example, if you were using an ExecutorService, you'd shut it down here:
        // executorService.shutdown()
    }

    private fun executors(username: String) {
        val handler = Handler(Looper.getMainLooper())

        // IMPORTANT: The original Java code performs a synchronous network request
        // on a new Thread. While it moves off the main thread, directly creating
        // new Threads for each request can lead to resource exhaustion and issues.
        // In a real Android app, you should use Kotlin Coroutines for async operations
        // with Retrofit, which is much safer and more efficient.

        Thread {
            val request = Requestsku(username)
            // Synchronized block (optional, depending on whether `Requestsku.init`
            // has shared mutable state that needs protection. In this case,
            // `req.init()` only modifies `req.result`, which is local to this thread,
            // so `synchronized(this)` is not strictly necessary unless `init()`
            // itself accesses shared resources. Removed it for simpler direct conversion,
            // but keep in mind if `Requestsku` becomes more complex.)
            // synchronized(this) { // Removed for direct conversion, consider if truly needed
                request.init() // This is a synchronous network call
            // }

            handler.post {
                request.result?.let {
                    lprofilinfo.add(it)
                    adapter.notifyDataSetChanged()
                } ?: run {
                    // Handle the case where request.result is null (e.g., network error)
                    // You might want to show a Toast or log an error here.
                    // Toast.makeText(applicationContext, "Failed to fetch profile for $username", Toast.LENGTH_SHORT).show()
                }
            }
        }.start()
    }

    // Recommended modern Android approach using Coroutines (requires lifecycle-ktx and kotlin-coroutines dependencies)
    /*
    private fun fetchProfileWithCoroutines(username: String) {
        lifecycleScope.launch { // Use lifecycleScope to tie coroutine to Activity/Fragment lifecycle
            val request = Requestsku(username)
            val profilInfo = request.fetchProfileInfo() // Call the suspend function

            profilInfo?.let { // Use 'let' for safe access if profilInfo is not null
                lprofilinfo.add(it)
                adapter.notifyDataSetChanged()
            } ?: run {
                // Handle null case (e.g., network error or no data)
                Toast.makeText(applicationContext, "Failed to fetch profile for $username", Toast.LENGTH_SHORT).show()
            }
        }
    }
    */
}
