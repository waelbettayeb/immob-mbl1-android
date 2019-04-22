package com.waelkhelil.immob


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.appcompat.widget.Toolbar
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI
import ru.dimorinny.floatingtextbutton.FloatingTextButton

class ExploreFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_explore, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val lButtonCreateNewListing = view.findViewById<FloatingTextButton>(R.id.ftb_create_new_listing)
        lButtonCreateNewListing.setOnClickListener {
            val dialog = CreateNewListingDialogFragment()
            val ft = fragmentManager!!.beginTransaction()
            dialog.show(ft, CreateNewListingDialogFragment.TAG)
        }
    }
}
