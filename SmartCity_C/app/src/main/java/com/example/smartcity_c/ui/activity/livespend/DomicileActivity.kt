package com.example.smartcity_c.ui.activity.livespend

import android.text.InputType
import android.view.View
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import com.bigkoo.pickerview.builder.OptionsPickerBuilder
import com.example.smartcity_c.R
import com.example.smartcity_c.contract.DomicileContract
import com.example.smartcity_c.presenter.DomicilePresenter
import com.example.smartcity_c.ui.activity.BaseActivity
import kotlinx.android.synthetic.main.activity_domicile.*
import kotlinx.android.synthetic.main.activity_group.*
import kotlinx.android.synthetic.main.activity_person_info.*
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast

class DomicileActivity : BaseActivity(), DomicileContract.View {
    val presenter by lazy {
        DomicilePresenter(this,this)
    }
    override fun init() {
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        textView50.setOnClickListener {
            startActivity<GroupingActivity>()
            finish()
        }
        intent.getStringExtra("data")?.let {
            textView50.text = it
        }
        if (textView50.text.toString() != "新增分组") {
            textView49.visibility = View.VISIBLE
            textView59.visibility = View.VISIBLE
            textView60.visibility = View.VISIBLE
            textView51.visibility = View.VISIBLE
        }
        textView49.setOnClickListener {
            val build = OptionsPickerBuilder(this) { o1, _, _, _ ->
                if (o1.toString() == "0") textView49.text = "水费" else textView49.text = "电费"
            }.build<String>()
            build.setPicker(arrayListOf("水费", "电费"))
            build.show()
        }
        textView51.setOnClickListener {
            val builder = AlertDialog.Builder(this)
            val editText = EditText(this)
            editText.inputType = InputType.TYPE_CLASS_NUMBER
            builder.setView(editText)
            builder.setNegativeButton("确定") { dialog, _ ->
                editText.text.toString().ifEmpty {
                    toast("不能为空")
                    return@setNegativeButton
                }
                editText.text.also { textView51.text = it }
                dialog.dismiss()
            }
            builder.create().show()
        }
        button11.setOnClickListener {
            if (textView50.text == "新增分组")
            return@setOnClickListener
            if (textView49.text == "新增缴费类别")
            return@setOnClickListener
            if (textView51.text == "新增户号")
            return@setOnClickListener
            presenter.saveData(textView50.text.toString(),textView49.text.toString(),textView51.text.toString())
        }
    }

    override fun checkButton() {
        button11.isEnabled = false
    }

    override fun getLayoutResID() = R.layout.activity_domicile
    override fun onSupportNavigateUp(): Boolean {
        finish()
        return super.onSupportNavigateUp()
    }
}