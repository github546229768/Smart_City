package com.example.smartcity_c.ui.activity.livespend

import android.view.View
import android.widget.EditText
import android.widget.RadioButton
import androidx.appcompat.app.AlertDialog
import com.example.smartcity_c.R
import com.example.smartcity_c.ui.activity.BaseActivity
import kotlinx.android.synthetic.main.activity_group.*
import org.jetbrains.anko.startActivity

class GroupingActivity : BaseActivity() {
    override fun init() {
        textView57.setOnClickListener {
            val builder = AlertDialog.Builder(this)
            val editText = EditText(this)
            builder.setView(editText)
            builder.setNegativeButton("确定") { dialog,_, ->
                editText.text.also { textView56.text = it }
                textView56.visibility  = View.VISIBLE
                dialog.dismiss()
            }
            builder.create().show()
        }

        button10.setOnClickListener {
            for (i in 0 until radioGroup.childCount){
                if(((radioGroup.getChildAt(i)) as RadioButton).isChecked){
                    startActivity<DomicileActivity>("data" to ((radioGroup.getChildAt(i)) as RadioButton).text.toString())
                    finish()
                }
            }
        }
    }

    override fun getLayoutResID() = R.layout.activity_group
}