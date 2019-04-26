package com.waelkhelil.immob


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.RecyclerView
import com.waelkhelil.immob.model.Listing
import ru.dimorinny.floatingtextbutton.FloatingTextButton
import androidx.lifecycle.Observer
import android.util.Log
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import androidx.recyclerview.widget.LinearLayoutManager

class SearchFragment : Fragment() {
    private lateinit var viewModel: MainViewModel
    var data: HashMap<String, String>? = null

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
        return inflater.inflate(R.layout.fragment_search, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        super.onViewCreated(view, savedInstanceState)

        val recyclerView = view.findViewById<RecyclerView>(R.id.rv_search)
        recyclerView.layoutManager = LinearLayoutManager(context)

        val ac = view.findViewById<AutoCompleteTextView>(R.id.search_field)

        viewModel.mListing.observe(viewLifecycleOwner,Observer<List<Listing>>{

            var data = listOf("")
            var selectedItem : String = ""

            for (i in it){
                data += i.mTitle
            }

            val adapter = ArrayAdapter<String>(context,
                android.R.layout.simple_dropdown_item_1line,
                data
            )

            ac.setAdapter(adapter)
            ac.threshold = 1

            ac.onItemClickListener = AdapterView.OnItemClickListener{ parent,view,position,id->
                selectedItem = parent.getItemAtPosition(position).toString()

                it.filter { it.mTitle == selectedItem }.also {
                    recyclerView.adapter = context?.let { it1 -> ListingAdapter(it.reversed(), it1) }
                }
            }
        })

    }
}
