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
import android.widget.TextView

class SplashActivity : AppCompatActivity() {
    private var dbHandler: DatabaseHandler? = null
    private var validateSize: ArrayList<Zipcodes>?= null
    private var returnForUpdateDB: Boolean = false
    lateinit var txtStatus: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        initItems()

        dbHandler = DatabaseHandler(this)
        validateSize = dbHandler!!.getOneZipCodes()

        if(validateSize!!.size == 0) {
            startDownload()
        } else {
            startedActivity()
        }
    }

    private fun initItems() {
        txtStatus = findViewById(R.id.txtStatus)

        btnDownload.setOnClickListener {
            btnDownload.visibility = View.GONE
            startDownload()
        }
    }

    private fun startDownload() {
        if(isOnline()) {
            val call = ApiRest().zipCodeService().download()

            call.enqueue(object: Callback<List<Zipcodes>?> {
                override fun onResponse(call: Call<List<Zipcodes>?>?,
                                        response: Response<List<Zipcodes>?>?) {
                    response?.let {
                        val zipCodes: List<Zipcodes>? = it.body()
                        if (zipCodes != null) returnForUpdateDB = dbHandler!!.addZipCodeWithTransactions(zipCodes)
                        if(returnForUpdateDB) startedActivity()
                    }
                }

                override fun onFailure(call: Call<List<Zipcodes>?>?,
                                       t: Throwable?) {
                    t?.let {
                        Log.e("onFailure error", t.message)
                        dbHandler!!.clearZipCode()
                        if (t is SocketTimeoutException) {
                            btnDownload.visibility = View.VISIBLE
                        }
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

    private fun startedActivity() {
        val intent = Intent(baseContext, MainActivity::class.java)
        startActivity(intent)
    }
}
