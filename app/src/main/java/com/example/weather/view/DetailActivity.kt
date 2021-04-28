package com.example.weather.view

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.example.weather.R

/**
 * An activity representing a detail screen.
 * This activity is only used on narrow width devices.
 * On tablet-size devices, item details are presented
 * side-by-side with a list of items in a [ListActivity].
 */
class DetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.detail_activity)
        setSupportActionBar(findViewById(R.id.detailToolbar))
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        if (savedInstanceState == null) {
            val fragment = DetailFragment().apply {
                arguments = Bundle().apply {
                    putParcelable(
                        DetailFragment.ARG_DETAILS,
                        intent.getParcelableExtra<DetailsViewData>(DetailFragment.ARG_DETAILS)
                    )
                }
            }

            supportFragmentManager.beginTransaction()
                    .add(R.id.detailContainer, fragment)
                    .commit()
        }
    }

    override fun onOptionsItemSelected(item: MenuItem) =
            when (item.itemId) {
                android.R.id.home -> {
                    navigateUpTo(Intent(this, ListActivity::class.java))
                    true
                }
                else -> super.onOptionsItemSelected(item)
            }
}