package com.example.permission

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.permission.databinding.ActivityContactsBinding

class ContactsActivity : AppCompatActivity() {
    private lateinit var binding: ActivityContactsBinding
    private lateinit var contactsAdapter: ContactsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityContactsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val toolbar: Toolbar = binding.toolbar
        setSupportActionBar(toolbar)

        binding.contactsRecyclerView.layoutManager = LinearLayoutManager(this)
        contactsAdapter = ContactsAdapter(getContacts())
        binding.contactsRecyclerView.adapter = contactsAdapter
    }

    private fun getContacts(): List<Contact> {

        return listOf(
            Contact("Contact 1", "123-456-7890"),
            Contact("Contact 2", "098-765-4321"),
            Contact("Contact 3", "555-123-4567"),
            Contact("Contact 4", "321-654-0987"),
            Contact("Contact 5", "987-654-3210")
        )
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