package c.Test.wtest.ExerciseOne

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import c.Test.wtest.R
import c.Test.wtest.models.Zipcodes

class ZipCodeAdapter (private val context: Context, private val zipCodeList: ArrayList<Zipcodes>)
    : RecyclerView.Adapter<ResultViewHolder> (){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ResultViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.card_zipcode, parent, false)
        return ResultViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return zipCodeList.size
    }

    override fun onBindViewHolder(holder: ResultViewHolder, position: Int) {
        holder.txtZipCode.text = zipCodeList[position].code+", "+zipCodeList[position].nameLocate
    }
}