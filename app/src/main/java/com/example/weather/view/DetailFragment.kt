package com.example.weather.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.weather.R

/**
 * A fragment representing a detail screen.
 * This fragment is either contained in a [ListActivity] in two-pane mode (on tablets)
 * or a [DetailActivity] on handsets.
 */
class DetailFragment : Fragment() {

    private var details: DetailsViewData? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.let {
            if (it.containsKey(ARG_DETAILS)) {
                details = it.getParcelable(ARG_DETAILS)
            }
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val rootView = inflater.inflate(R.layout.details, container, false)

        details?.let {
            rootView.findViewById<TextView>(R.id.details).text = it.wind
        }

        return rootView
    }

    companion object {
        const val ARG_DETAILS = "details"
    }
}