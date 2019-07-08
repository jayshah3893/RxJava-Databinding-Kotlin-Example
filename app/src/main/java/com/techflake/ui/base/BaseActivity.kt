package com.techflake.ui.base

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.app.ProgressDialog
import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.techflake.R
import android.view.inputmethod.InputMethodManager


open class BaseActivity : AppCompatActivity() {

    var mProgressDialog: ProgressDialog? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mProgressDialog = ProgressDialog(this)
    }

    fun hideLoader() {
        if (mProgressDialog != null && mProgressDialog!!.isShowing) {
            mProgressDialog!!.dismiss()
            mProgressDialog = null
        }
    }

    fun showProgressLoader(msg: String = getString(R.string.please_wait)) {
        if (mProgressDialog == null) {
            mProgressDialog = ProgressDialog(this, R.style.AppCompatAlertDialogStyle)
            mProgressDialog!!.setMessage(msg)
            mProgressDialog!!.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            mProgressDialog!!.setCancelable(false)
        }
        mProgressDialog!!.show()
    }

    fun showToast(aMessage : String){
        Toast.makeText(this,aMessage,Toast.LENGTH_SHORT).show()
    }

    fun makeMeShake(view: View) {
        val rotate = ObjectAnimator.ofFloat(view, "rotation", 0f, 20f, 0f, -20f, 0f)
        rotate.repeatCount = 1
        rotate.duration = 50
        rotate.start()
    }


    fun loadFragment_(containerId: Int, fragment: Fragment) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(containerId, fragment)
        transaction.commit()
        fadeOutIn(findViewById(containerId))
    }

    fun fadeOutIn(view: View) {
        view.alpha = 0f
        val animatorSet = AnimatorSet()
        val animatorAlpha = ObjectAnimator.ofFloat(view, "alpha", 0f, 0.5f, 1f)
        ObjectAnimator.ofFloat(view, "alpha", 0f).start()
        animatorAlpha.duration = 500
        animatorSet.play(animatorAlpha)
        animatorSet.start()
    }

    fun hideKeyboard(view: View) {
        val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(view.windowToken, 0)
    }

}
