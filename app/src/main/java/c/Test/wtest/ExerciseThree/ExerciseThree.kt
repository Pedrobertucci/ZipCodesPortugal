package c.Test.wtest.ExerciseThree

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.content.res.ResourcesCompat.getColor
import android.support.v7.widget.CardView
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.Toolbar
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

        initItems(inflater.context)
        return rootView
    }

    private fun initItems(context: Context) {
        recyclerView.layoutManager = LinearLayoutManager(activity)
        recyclerView.adapter = ExerciseThreeAdapter(activity!!, 50)
    }
}