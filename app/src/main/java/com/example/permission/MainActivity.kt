package com.example.permission

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.example.permission.databinding.ActivityMainBinding
import android.Manifest
import android.view.Menu
import android.view.MenuItem

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    private var activityToNavigate: Class<*>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        binding.cameraPermissonBTN.setOnClickListener {
            activityToNavigate = CameraActivity::class.java
            requestPermission(Manifest.permission.CAMERA)
        }

        binding.contactPermissionBTN.setOnClickListener {
            activityToNavigate = ContactsActivity::class.java
            requestPermission(Manifest.permission.READ_CONTACTS)
        }
    }

    private fun requestPermission(permission: String) {
        permissionLauncherSingle.launch(permission)
    }

    private val permissionLauncherSingle = registerForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { isGranted ->
        if (isGranted) {
            singlePermissionGranted()

            activityToNavigate?.let {
                startActivity(Intent(this, it))
            }
        } else {
            Toast.makeText(this, "В разрешении отказано...", Toast.LENGTH_SHORT).show()
        }
    }

    private fun singlePermissionGranted() {
        Toast.makeText(this, "Разрешение получено", Toast.LENGTH_SHORT).show()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.exit -> {
                finish()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}