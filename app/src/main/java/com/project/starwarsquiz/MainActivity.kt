package com.project.starwarsquiz

import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Bundle
import android.provider.Settings
import android.view.HapticFeedbackConstants
import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        checkInternetAndPrompt()
    }

    // Prompt to take the user to settings to enable an internet connection
    private fun promptToEnableInternet() {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Enable Internet Connection")
        builder.setMessage(
            "To fully experience the Star Wars Quiz app, please enable your " +
                    "internet connection. This will allow you to take part in the quiz."
        )

        // Button to take the user to their WiFi Settings
        builder.setPositiveButton("Settings") { dialogInterface: DialogInterface, i: Int ->
            val intent = Intent(Settings.ACTION_WIFI_SETTINGS)
            startActivity(intent)
        }

        builder.create().show()
    }

    // Check if connected, if not, prompt to connect
    private fun checkInternetAndPrompt() {
        if (!isInternetConnected()) {
            promptToEnableInternet()
        }
    }

    // Used to make sure that the app is able to connect to the internet
    // If it can't, the API used for Question Generation can't be reached and thus, the app crashes
    private fun isInternetConnected(): Boolean {
        val connectivityManager =
            getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val network = connectivityManager.activeNetwork ?: return false
        val activeNetwork = connectivityManager.getNetworkCapabilities(network) ?: return false

        return when {
            activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
            activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
            activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> true
            else -> false
        }
    }

    // Check if connected to the internet, prompt if not
    // Send vibration (haptic feedback) to the phone
    // Show the CategorySelection screen
    fun selectCategoryButtonClick(view: View) {
        checkInternetAndPrompt()
        view.isHapticFeedbackEnabled = true
        view.performHapticFeedback(HapticFeedbackConstants.CONFIRM)
        val intent = Intent(this@MainActivity, CategorySelection::class.java)
        startActivity(intent)

    }
}