package c.Test.wtest.ExerciseOne

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import c.Test.wtest.R
import c.Test.wtest.database.DatabaseHandler
import c.Test.wtest.models.Zipcodes

class ExerciseOne : Fragment() {
    private var zipCodeRecyclerView: RecyclerView?= null
    private var dbHandler: DatabaseHandler? = null
    private var zipCodes: ArrayList<Zipcodes>?= null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        searchZipCodes()
        val rootView = inflater.inflate(R.layout.exercises1, container, false)
        zipCodeRecyclerView = rootView.findViewById(R.id.recyclerViewZipCodes) as RecyclerView // Add this
        zipCodeRecyclerView!!.layoutManager = LinearLayoutManager(activity)
        zipCodeRecyclerView!!.adapter = ZipCodeAdapter(activity!!, zipCodes!!)
        return rootView
    }

    private fun searchZipCodes() {
        dbHandler = DatabaseHandler(activity!!)
        zipCodes = dbHandler!!.getFirstZipCodes()
    }

    companion object {
        fun newInstance(): ExerciseOne = ExerciseOne()
    }
}