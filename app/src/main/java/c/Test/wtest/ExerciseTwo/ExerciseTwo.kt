package c.Test.wtest.ExerciseTwo

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.content.ContextCompat
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.support.v7.widget.Toolbar
import android.util.Log
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
    lateinit var toolbar: Toolbar

    companion object {
        fun newInstance(): ExerciseTwo = ExerciseTwo()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val rootView = inflater.inflate(R.layout.exercises2, container, false)
        imageTop = rootView.findViewById(c.Test.wtest.R.id.imageExercise2) as ImageView
        zipCodeRecyclerViewTwo = rootView.findViewById(c.Test.wtest.R.id.zipCodeRecyclerViewTwo) as RecyclerView
        toolbar = rootView.findViewById(c.Test.wtest.R.id.appBar3) as Toolbar
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
        zipCodeRecyclerViewTwo.adapter = ZipCodeAdapter(zipCodes!!)
    }
}