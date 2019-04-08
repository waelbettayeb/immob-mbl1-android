package com.waelkhelil.immob

import androidx.lifecycle.ViewModelProviders
import android.app.Activity.RESULT_OK
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.content.Intent
import android.graphics.BitmapFactory
import android.graphics.Bitmap
import android.net.Uri
import android.os.AsyncTask
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import java.io.InputStream


class AddPostFragment : Fragment() {

    companion object {
        const val RESULT_LOAD_IMG = 0
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

        val lButtonAddImage = view.findViewById<Button>(R.id.button_add_image)
        lButtonAddImage.setOnClickListener { pickPhotos() }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val layoutManager = GridLayoutManager(context,3)
        val recyclerView = view?.findViewById(R.id.rv_image) as RecyclerView
        recyclerView.layoutManager = layoutManager
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
                bimage = BitmapFactory.decodeStream(imageStream)

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
