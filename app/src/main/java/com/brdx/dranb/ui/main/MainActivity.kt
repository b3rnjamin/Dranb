package com.brdx.dranb.ui.main

import android.net.ConnectivityManager
import android.net.LinkProperties
import android.net.Network
import android.net.NetworkCapabilities
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupWithNavController
import com.brdx.dranb.R
import com.brdx.dranb.databinding.ActivityMainBinding
import com.brdx.dranb.ui.main.interfaces.OnScrollListener

class MainActivity : AppCompatActivity(), OnScrollListener {

    private lateinit var binding: ActivityMainBinding

    /*
    private val connectivityManager by lazy { getSystemService(ConnectivityManager::class.java) }
    private val internetCallback = object : ConnectivityManager.NetworkCallback() {
        override fun onAvailable(network : Network) {
            Log.e("TAG", "The default network is now: " + network)
        }

        override fun onLost(network : Network) {
            Log.e("TAG", "The application no longer has a default network. The last default network was " + network)
        }

        override fun onCapabilitiesChanged(network : Network, networkCapabilities : NetworkCapabilities) {
            Log.e("TAG", "The default network changed capabilities: " + networkCapabilities)
        }

        override fun onLinkPropertiesChanged(network : Network, linkProperties : LinkProperties) {
            Log.e("TAG", "The default network changed link properties: " + linkProperties)
        }
    }
    */

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navHostFragment = supportFragmentManager.findFragmentById(R.id.fragmentContainerView) as NavHostFragment
        val navController = navHostFragment.navController

        binding.bottomNavigationView.setupWithNavController(navController)
        binding.bottomNavigationView.setOnItemSelectedListener {
            if (it.itemId == R.id.homeFragment) {
                navController.popBackStack(R.id.homeFragment, false)
                true
            }
            else
                NavigationUI.onNavDestinationSelected(it , navController)
        }

    }

    override fun onScrolled(isScrollLingToUp: Boolean) {
        Log.i("TAG", "isScrollingToUp: $isScrollLingToUp")
    }

    /*
    override fun onResume() {
        connectivityManager.registerDefaultNetworkCallback(internetCallback)
        super.onResume()
    }*/

    /*
    override fun onPause() {
        connectivityManager.unregisterNetworkCallback(internetCallback)
        super.onPause()
    }*/
}