package c.Test.wtest.ExerciseFour

import android.content.Context
import android.net.ConnectivityManager
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import android.widget.Button
import android.widget.TextView
import c.Test.wtest.BuildConfig
import c.Test.wtest.R

class ExerciseFour: Fragment() {
    lateinit var webView: WebView
    lateinit var txtWebView: TextView
    lateinit var btnWebView: Button

    companion object {
        fun newInstance(): ExerciseFour = ExerciseFour()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val rootView = inflater.inflate(R.layout.exercise4, container, false)
        webView = rootView.findViewById(R.id.webExercise4) as WebView
        txtWebView = rootView.findViewById(R.id.txtWebView) as TextView
        btnWebView = rootView.findViewById(R.id.btnWebView) as Button

        btnWebView.setOnClickListener {
            initWebView()
        }
        initWebView()
        return rootView
    }

    private fun initWebView() {
        if(isOnline()){
            txtWebView.visibility = View.GONE
            btnWebView.visibility = View.GONE
            webView.visibility = View.VISIBLE
            webView.loadUrl(BuildConfig.apiBaseUrl)
        } else {
            webView.visibility = View.GONE
            btnWebView.visibility = View.VISIBLE
            txtWebView.visibility = View.VISIBLE
        }
    }

    private fun isOnline(): Boolean {
        val cm = context!!.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val netInfo = cm.activeNetworkInfo
        return netInfo != null && netInfo.isConnectedOrConnecting
    }


}