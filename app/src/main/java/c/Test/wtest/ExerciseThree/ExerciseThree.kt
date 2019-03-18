package c.Test.wtest.ExerciseThree

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import c.Test.wtest.R
import kotlinx.android.synthetic.main.exercises2.*

class ExerciseThree: Fragment() {
    lateinit var recyclerView: RecyclerView

    companion object {
        fun newInstance(): ExerciseThree = ExerciseThree()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val rootView = inflater.inflate(R.layout.exercise3, container, false)
        recyclerView = rootView.findViewById(R.id.recyclerView3) as RecyclerView
        initItems()
        return rootView
    }

    private fun initItems() {
        recyclerView.layoutManager = LinearLayoutManager(activity)
        recyclerView.adapter = ExerciseThreeAdapter(activity!!, 50)
    }
}