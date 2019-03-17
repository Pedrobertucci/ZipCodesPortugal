package c.Test.wtest.Activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity;
import android.util.Log
import android.view.View
import c.Test.wtest.R
import c.Test.wtest.Rest.ApiRest
import c.Test.wtest.database.DatabaseHandler
import c.Test.wtest.models.Zipcodes
import kotlinx.android.synthetic.main.activity_splash.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.net.SocketTimeoutException
import android.net.ConnectivityManager
import android.content.Context
import android.content.Intent


class SplashActivity : AppCompatActivity() {
    private var dbHandler: DatabaseHandler? = null
    private var validateSize: ArrayList<Zipcodes>?= null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        dbHandler = DatabaseHandler(this)

        validateSize = dbHandler!!.getFirstZipCodes()

        if(validateSize!!.size == 0) {
            startDownload()
        }
        
        btnDownload.setOnClickListener {
            btnDownload.visibility = View.GONE
            startDownload()
        }
    }

    private fun startDownload() {
        if(isOnline()) {
            txtStatus.text = resources.getString(R.string.status_downloading)
            val call = ApiRest().zipCodeService().download()

            call.enqueue(object: Callback<List<Zipcodes>?> {
                override fun onResponse(call: Call<List<Zipcodes>?>?,
                                        response: Response<List<Zipcodes>?>?) {
                    response?.let {
                        val zipCodes: List<Zipcodes>? = it.body()
                        txtStatus.text = getString(R.string.saving_information)
                        if (zipCodes != null) {
                            dbHandler!!.addZipCode(zipCodes)
                        }
                        val intent = Intent(baseContext, MainActivity::class.java)
                        startActivity(intent)
                    }
                }

                override fun onFailure(call: Call<List<Zipcodes>?>?,
                                       t: Throwable?) {
                    t?.let {
                        if (t is SocketTimeoutException) {
                            txtStatus.text = getString(R.string.error_time_out)
                            btnDownload.visibility = View.VISIBLE
                        }
                        Log.e("onFailure error", t.message)
                    }
                }
            })
        }
    }

    private fun isOnline(): Boolean {
        val cm = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val netInfo = cm.activeNetworkInfo
        return netInfo != null && netInfo.isConnectedOrConnecting
    }
}
