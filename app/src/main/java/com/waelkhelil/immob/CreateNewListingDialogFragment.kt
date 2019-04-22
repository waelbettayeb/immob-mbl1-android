package com.waelkhelil.immob

import android.app.Activity.RESULT_OK
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.AsyncTask
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import java.io.InputStream


class CreateNewListingDialogFragment : DialogFragment() {

    companion object {
        const val RESULT_LOAD_IMG = 0
        const val TAG = "CreateNewListingDialogFragment"
    }
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
        return inflater.inflate(R.layout.fragment_add_post, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val toolbar = view.findViewById<Toolbar>(R.id.toolbar_new_listing)
        toolbar.setNavigationIcon(R.drawable.ic_close_white_24dp)
        toolbar.setNavigationOnClickListener{ dismissAllowingStateLoss() }
        toolbar.title = resources.getString(R.string.add_listing)

        val lButtonAddImage = view.findViewById<Button>(R.id.button_add_image)
        lButtonAddImage.setOnClickListener { pickPhotos() }
    }
    override fun onStart() {
        super.onStart()
        val dialog = dialog
        if (dialog != null) {
            val width = ViewGroup.LayoutParams.MATCH_PARENT
            val height = ViewGroup.LayoutParams.MATCH_PARENT
            dialog.window.setLayout(width, height)
        }
    }
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val layoutManager = GridLayoutManager(context,3)
        val recyclerView = view?.findViewById(R.id.rv_image) as RecyclerView
        recyclerView.layoutManager = layoutManager
        recyclerView.setHasFixedSize(true)
        recyclerView.hasFixedSize()
        viewModel.bitmaps.observe(viewLifecycleOwner, Observer<MutableList<Bitmap>> {
            it?.also {
                recyclerView.adapter = ImageAdapter(it)
            }
        })

//        recyclerView.adapter = ImageAdapter(List())
        recyclerView.addItemDecoration(DividerItemDecoration(context, layoutManager.orientation))

//        binding.viewModel = viewModel
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == RESULT_LOAD_IMG) {
            if (resultCode == RESULT_OK) {
                if (data != null) {
                    val clipData = data.clipData
                    if (clipData != null) { // handle multiple photo
                        for (i in 0 until clipData.itemCount) {
                            val uri = clipData.getItemAt(i).uri
                            DownloadImageFromUri().execute(uri)
                        }
                    } else { // handle single photo
                        val uri = data.data
                        DownloadImageFromUri().execute(uri)
                    }
                }
            }
        }
    }

    private fun pickPhotos(){
        val photoPickerIntent = Intent(Intent.ACTION_GET_CONTENT)
        photoPickerIntent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true)
        photoPickerIntent.type = "image/*"
        startActivityForResult(photoPickerIntent, RESULT_LOAD_IMG)
    }

    private inner class DownloadImageFromUri :
        AsyncTask<Uri, Void, Bitmap>() {

        override fun doInBackground(vararg uris: Uri): Bitmap? {
            val imageUri = uris[0]
            lateinit var bimage: Bitmap
            try {
                val contentResolver = context?.contentResolver
                val imageStream: InputStream? = contentResolver?.openInputStream(imageUri)

                val options = BitmapFactory.Options()
                options.inPreferredConfig = Bitmap.Config.RGB_565

                bimage = BitmapFactory.decodeStream(imageStream, null, options)

            } catch (e: Exception) {
                Log.e("Error Message", e.message)
                e.printStackTrace()
            }

            return bimage
        }

        override fun onPostExecute(result: Bitmap) {
            viewModel.addBitmaps(result)
        }
    }

}
