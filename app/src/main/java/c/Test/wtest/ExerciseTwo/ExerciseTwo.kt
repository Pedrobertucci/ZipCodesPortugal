package c.Test.wtest.ExerciseTwo

import android.content.Context
import android.os.Bundle
import android.support.design.widget.CollapsingToolbarLayout
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toolbar
import c.Test.wtest.ExerciseOne.ZipCodeAdapter
import c.Test.wtest.R
import c.Test.wtest.database.DatabaseHandler
import c.Test.wtest.models.Zipcodes
import com.squareup.picasso.Picasso

class ExerciseTwo: Fragment() {
    lateinit var imageTop: ImageView
    private lateinit var zipCodeRecyclerViewTwo: RecyclerView
    private var zipCodes: ArrayList<Zipcodes>?= null
    private var dbHandler: DatabaseHandler? = null
    lateinit var toolbarExercise2: Toolbar

    companion object {
        fun newInstance(): ExerciseTwo = ExerciseTwo()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val rootView = inflater.inflate(R.layout.exercises2, container, false)
        imageTop = rootView.findViewById(R.id.imageExercise2) as ImageView
        zipCodeRecyclerViewTwo = rootView.findViewById(R.id.zipCodeRecyclerViewTwo) as RecyclerView
        initItems(inflater.context)
        return rootView
    }

    private fun initItems(context: Context){
        Picasso.with(context)
            .load("https://www.almadeviajante.com/wp-content/uploads/azenhas-do-mar-portugal.jpg")
            .into(imageTop)
        dbHandler = DatabaseHandler(activity!!)
        zipCodes = dbHandler!!.getFirstZipCodes()
        zipCodeRecyclerViewTwo.layoutManager = LinearLayoutManager(activity)
        zipCodeRecyclerViewTwo.adapter = ZipCodeAdapter(activity!!, zipCodes!!)
    }

}