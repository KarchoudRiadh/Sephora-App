package rk.mk.sephora.presentation.view.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import rk.mk.sephora.R

class HomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        val listFrag = ListFragment()
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.frameFragmentHome, listFrag)
        fragmentTransaction.addToBackStack(null)
        fragmentTransaction.commit()
    }
}