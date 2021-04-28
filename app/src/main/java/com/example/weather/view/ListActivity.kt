package com.example.weather.view

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.widget.NestedScrollView
import androidx.recyclerview.widget.RecyclerView
import com.example.weather.R
import dagger.hilt.android.AndroidEntryPoint

/**
 * This activity has different presentations for handset and tablet-size devices.
 * On handsets, the activity presents a list of items, which when touched,
 * lead to a [DetailActivity] representing item details.
 * On tablets, the activity presents the list of items and
 * item details side-by-side using two vertical panes.
 */
@AndroidEntryPoint
class ListActivity : AppCompatActivity() {

    private val viewModel: MyViewModel by viewModels()

    private var twoPane: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.list_activity)

        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)
        title = getString(R.string.listTitle)

        if (findViewById<NestedScrollView>(R.id.detailContainer) != null) {
            // The detail container view will be present only in the
            // large-screen layouts (res/values-w900dp).
            // If this view is present, then the
            // activity should be in two-pane mode.
            twoPane = true
        }

        viewModel.items.observe(this) { items ->
            val recyclerView: RecyclerView = findViewById(R.id.list)
            recyclerView.adapter = Adapter(items)
        }
    }

    inner class Adapter(
        private val values: List<ItemViewData>,
    ) : RecyclerView.Adapter<Adapter.ViewHolder>() {

        private val onClickListener = View.OnClickListener { v ->
            val position = v.tag as Int
            val details = viewModel.details.value!![position]
            if (twoPane) {
                val fragment = DetailFragment().apply {
                    arguments = Bundle().apply {
                        putParcelable(DetailFragment.ARG_DETAILS, details)
                    }
                }
                supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.detailContainer, fragment)
                    .commit()
            } else {
                val intent = Intent(v.context, DetailActivity::class.java).apply {
                    putExtra(DetailFragment.ARG_DETAILS, details)
                }
                v.context.startActivity(intent)
            }
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.item, parent, false)
            return ViewHolder(view)
        }

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            val item = values[position]
            holder.first.text = item.day
            holder.second.text = item.time
            holder.third.text = item.feelsLikeTemperature.toString()

            with(holder.itemView) {
                tag = position
                setOnClickListener(onClickListener)
            }
        }

        override fun getItemCount() = values.size

        inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
            val first: TextView = view.findViewById(R.id.first)
            val second: TextView = view.findViewById(R.id.second)
            val third: TextView = view.findViewById(R.id.third)
        }
    }
}