package com.example.application.redcarpetup

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_gallery.*


class GalleryActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_gallery)

    println("Activity Started Successfully")

    //Back Button in Toolbar
        supportActionBar?.setDisplayShowHomeEnabled(true)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)


        getIncomingIntent()
}

    override fun onBackPressed() {
        super.onBackPressed()
        this.finish()
    }


    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }


    private fun getIncomingIntent() {

        if (intent.hasExtra("image_url") && intent.hasExtra("image_name")) {  //checking if it is valid intent or not

            val imageUrl = intent.getStringExtra("image_url")
            val imageName = intent.getStringExtra("image_name")

            image_desc?.text = imageName
            setImage(imageUrl)
        }
    }


    private fun setImage(imageUrl: String) {

        Picasso.get()
                .load(imageUrl)
                .into(image)
    }
}