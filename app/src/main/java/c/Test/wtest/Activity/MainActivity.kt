package c.Test.wtest.Activity

import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import c.Test.wtest.ExerciseFour.ExerciseFour
import c.Test.wtest.ExerciseOne.ExerciseOne
import c.Test.wtest.ExerciseThree.ExerciseThree
import c.Test.wtest.ExerciseTwo.ExerciseTwo
import c.Test.wtest.R
import com.facebook.stetho.Stetho
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.exercise1 -> {
                openFragment(ExerciseOne.newInstance())
                return@OnNavigationItemSelectedListener true
            }
            R.id.exercise2 -> {
                openFragment(ExerciseTwo.newInstance())
                return@OnNavigationItemSelectedListener true
            }
            R.id.exercise3 -> {
                openFragment(ExerciseThree.newInstance())
                return@OnNavigationItemSelectedListener true
            }
            R.id.exercise4 -> {
                openFragment(ExerciseFour.newInstance())
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Stetho.initializeWithDefaults(this)
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
        openFragment(ExerciseOne.newInstance())
    }

    private fun openFragment(fragment: Fragment) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.frame_layout, fragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }
}
