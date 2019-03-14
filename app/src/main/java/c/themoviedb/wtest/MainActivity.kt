package c.themoviedb.wtest

import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import c.themoviedb.wtest.Fragments.ExerciseOne
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.exercise1 -> {
                openFragment(ExerciseOne.newInstance())
                return@OnNavigationItemSelectedListener true
            }
            R.id.exercise2 -> {
                return@OnNavigationItemSelectedListener true
            }
            R.id.exercise3 -> {
                return@OnNavigationItemSelectedListener true
            }
            R.id.exercise4 -> {
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
    }


    private fun openFragment(fragment: Fragment) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.container, fragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }
}
