package c.Test.wtest.ExerciseFour

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import android.widget.EditText
import c.Test.wtest.BuildConfig
import c.Test.wtest.ExerciseOne.ExerciseOne
import c.Test.wtest.R

class ExerciseFour: Fragment() {
  lateinit var webView: WebView
    companion object {
        fun newInstance(): ExerciseFour = ExerciseFour()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val rootView = inflater.inflate(R.layout.exercise4, container, false)
        webView = rootView.findViewById(R.id.webExercise4) as WebView
        webView.loadUrl(BuildConfig.apiBaseUrl)
        return rootView
    }
}