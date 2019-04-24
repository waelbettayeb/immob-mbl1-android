package com.waelkhelil.immob


import android.graphics.Bitmap
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.waelkhelil.immob.model.Listing
import ru.dimorinny.floatingtextbutton.FloatingTextButton

class ExploreFragment : Fragment() {
    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Share data between fragments
        viewModel = activity?.run {
            ViewModelProviders.of(this).get(MainViewModel::class.java)
        } ?: throw Exception("Invalid Activity")
    }
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

        val recyclerView = view.findViewById<RecyclerView>(R.id.rv_listing)
        viewModel.mListing.observe(viewLifecycleOwner, Observer<List<Listing>> {
            it?.also {
                recyclerView.adapter = context?.let { it1 -> ListingAdapter(it.reversed(), it1) }
            }
        })

    }
}
