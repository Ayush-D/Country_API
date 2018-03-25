
package com.example.application.redcarpetup

import android.content.ContentValues.TAG
import android.content.Context
import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.list_item_layout.view.*

class MainAdapterKotlin(private val mContext : Context,private val userList: MainActivity.CuriousConcept):RecyclerView.Adapter<MainAdapterKotlin.ViewHolder>() {

    private val TAG = "MainAdapterJava"

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent?.context).inflate(R.layout.list_item_layout, parent, false)
        return ViewHolder(view,mContext)
    }

    override fun onBindViewHolder(holder: ViewHolder?, position: Int) {
        holder?.bind(userList.WldPop[position])
    }

    override fun getItemCount(): Int {
        return userList.WldPop.count()
    }

    class ViewHolder(itemView: View, private val mContext: Context): RecyclerView.ViewHolder(itemView){
        fun bind(worldpopulation: MainActivity.Worldpopulation) {
            //itemView?.textView?.text= userList.name
            itemView?.tvCountry?.text = worldpopulation.country
            itemView?.tvRank?.text = worldpopulation.rank.toString()
            itemView?.Population?.text = worldpopulation.population
            //Fetching Image
            val thumbnailImageView = itemView?.imageFlag
            Picasso.get().load(worldpopulation.flag).into(thumbnailImageView)

            thumbnailImageView?.setOnClickListener {
                Log.d(TAG, "onClick: Clicked On Image " + worldpopulation.country)
                Toast.makeText(mContext, worldpopulation.country,
                        Toast.LENGTH_LONG).show()

                val i = Intent(mContext, GalleryActivity::class.java)
                i.putExtra("image_url",worldpopulation.flag) //see also mImageUrls.get(position)
                i.putExtra("image_name",worldpopulation.country)//see also mName.get(position)

                mContext.startActivity(i)
            }
        }
    }
}