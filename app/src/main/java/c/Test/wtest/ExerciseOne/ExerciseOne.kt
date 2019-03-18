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
import kotlinx.android.synthetic.main.exercises1.*
import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText

class ExerciseOne : Fragment() {
    private var zipCodeRecyclerView: RecyclerView?= null
    private var dbHandler: DatabaseHandler? = null
    private var zipCodes: ArrayList<Zipcodes>?= null
    lateinit var edtSearch: EditText

    companion object {
        fun newInstance(): ExerciseOne = ExerciseOne()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val rootView = inflater.inflate(R.layout.exercises1, container, false)
        edtSearch = rootView.findViewById(R.id.edtSearch) as EditText
        zipCodeRecyclerView = rootView.findViewById(R.id.recyclerViewZipCodes) as RecyclerView
        zipCodeRecyclerView!!.layoutManager = LinearLayoutManager(activity)
        searchZipCodes()
        return rootView
    }

    private fun searchZipCodes() {
        dbHandler = DatabaseHandler(activity!!)
        edtSearch.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {
            }

            override fun onTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {
            }

            override fun afterTextChanged(editable: Editable) {
                if(!editable.isEmpty()) {
                    zipCodes = dbHandler!!.searchZipCodes(editable.toString())
                    recyclerViewZipCodes.adapter = ZipCodeAdapter(activity!!, zipCodes!!)
                    recyclerViewZipCodes.adapter.notifyDataSetChanged()
                } else {
                    if(zipCodes != null){
                        zipCodes!!.clear()
                        recyclerViewZipCodes.adapter = ZipCodeAdapter(activity!!, zipCodes!!)
                        recyclerViewZipCodes.adapter.notifyDataSetChanged()
                    }
                }
            }
        })
    }
}