package com.example.smartcity_c.ui.activity.personal

import android.app.AlertDialog
import android.app.Dialog
import android.content.Intent
import android.graphics.Bitmap
import android.os.Environment
import android.provider.MediaStore
import android.text.InputType
import android.text.method.DigitsKeyListener
import android.view.Gravity
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.EditText
import androidx.core.content.FileProvider
import com.bigkoo.pickerview.builder.OptionsPickerBuilder
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.target.SimpleTarget
import com.bumptech.glide.request.transition.Transition
import com.example.smartcity_c.R
import com.example.smartcity_c.contract.PersonalContract
import com.example.smartcity_c.presenter.PersonalPresenter
import com.example.smartcity_c.ui.activity.BaseActivity
import kotlinx.android.synthetic.main.activity_person_info.*
import kotlinx.android.synthetic.main.view_bttom_dialog.view.*
import org.jetbrains.anko.toast
import java.io.File


class PersonInfoActivity : BaseActivity(), PersonalContract.View {
    companion object{
         const val PHOTO_REQUEST_CAREMA = 1;// 拍照
         const val RESULT_LOAD_IMAGE = 2;// 从相册中选择
    }
    private var mCarId = ""
    private var mImagePath = ""
    private lateinit var picture:File
    val presenter by lazy { PersonalPresenter(this, this) }

    override fun init() {
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        presenter.loadUserData()
        person_sex.setOnClickListener {
            val build = OptionsPickerBuilder(this) { o1, _, _, _ ->
                if (o1.toString() == "0") person_sex.text = "男" else person_sex.text = "女"
            }.build<String>()
            build.setPicker(arrayListOf("男", "女"))
            build.show()
            button4.isEnabled = true
        }
        person_name.setOnClickListener {
            val editText = EditText(this)
            AlertDialog.Builder(this).setTitle("修改").setPositiveButton("确定") { _, _ ->
                if (editText.text.toString().isEmpty()) {
                    toast("不能为空")
                    return@setPositiveButton
                } else {
                    person_name.text = editText.text.toString()
                }
                button4.isEnabled = true
            }.setView(editText).show()
        }
        person_phone.setOnClickListener {
            val editText = EditText(this)
            editText.inputType = InputType.TYPE_CLASS_PHONE
            editText.keyListener = DigitsKeyListener.getInstance("1234567890")
            AlertDialog.Builder(this).setTitle("修改").setPositiveButton("确定") { _, _ ->
                if (editText.text.toString().isEmpty()) {
                    toast("不能为空")
                    return@setPositiveButton
                }else if (editText.text.toString().length != 11){
                    toast("请填写手机号码格式！")
                    return@setPositiveButton
                } else {
                    person_phone.text = editText.text.toString()
                }
                button4.isEnabled = true
            }.setView(editText).show()
        }
        person_card_id.setOnClickListener {
            val editText = EditText(this)
            editText.inputType = InputType.TYPE_CLASS_NUMBER
            editText.keyListener = DigitsKeyListener.getInstance("1234567890")
            AlertDialog.Builder(this).setTitle("修改")
                    .setPositiveButton("确定") { _, _ ->
                        if (editText.text.toString().isEmpty()) {
                            toast("不能为空")
                            return@setPositiveButton
                        }else if (editText.text.length<11) {
                        toast("不能小于11位数")
                        return@setPositiveButton
                    } else {
                            person_card_id.text = editText.text.toString()
                        }
                        button4.isEnabled = true
                    }.setView(editText).show()
        }
        person_img.setOnClickListener {
            val dialog = Dialog(this, R.style.dialog_transparent)
            val inflate = LayoutInflater.from(this).inflate(R.layout.view_bttom_dialog, null)
            inflate.btn_pop_cancel.setOnClickListener { dialog.dismiss() }
            inflate.btn_pop_album.setOnClickListener {
                val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
                intent.type = "image/*"
                startActivityForResult(intent, RESULT_LOAD_IMAGE)
                button4.isEnabled = true
                dialog.dismiss()
            }
            inflate.btn_pop_camera.setOnClickListener {
                openCamera()
                button4.isEnabled = true
                dialog.dismiss()
            }
            dialog.apply {
                setContentView(inflate)
                window!!.setGravity(Gravity.BOTTOM)
                val attributes = window!!.attributes
                attributes.width = ViewGroup.LayoutParams.MATCH_PARENT
                attributes.height = ViewGroup.LayoutParams.WRAP_CONTENT
                window!!.attributes = attributes
                window!!.setWindowAnimations(R.style.dialog_animation)
            }.show()

        }
        button4.setOnClickListener {
            println(picture.absoluteFile)
            if (person_card_id.text.contains("*"))
                presenter.saveData(picture,"$mCarId","${person_name.text}","${person_phone.text}",if (person_sex.text=="男") "1" else "0")
            else
                presenter.saveData(picture,"${person_card_id.text}", "${person_name.text}", "${person_phone.text}", if (person_sex.text=="男") "1" else "0")
        }
    }

    private fun openCamera() {
        val imgDir = File(getFilePath())
        val photoName = "${System.currentTimeMillis()}.jpg"
         picture = File(imgDir,photoName)
        if (!picture.exists()) picture.createNewFile()
        mImagePath = picture.absolutePath
        //调用相机
        val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        intent.putExtra(MediaStore.EXTRA_OUTPUT,FileProvider.getUriForFile(this,"com.example.smartcity_c.fileprovider",picture))
        startActivityForResult(intent, PHOTO_REQUEST_CAREMA)
    }

    private fun getFilePath(): String {
        val path = getExternalFilesDir(Environment.MEDIA_MOUNTED)?.absolutePath  //sadasdasd
        val file = File(path)
        if (!file.exists()) file.mkdirs()
        return path.toString()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == RESULT_LOAD_IMAGE){  //图库选择
            val cursor  = managedQuery(data?.data, arrayOf(MediaStore.Images.Media.DATA), null, null, null)
            val columnIndexOrThrow = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA)
            cursor.moveToFirst()
            println(cursor.getString(columnIndexOrThrow))
            picture = File(cursor.getString(columnIndexOrThrow))
            Glide.with(this).load(data?.data).skipMemoryCache(true).diskCacheStrategy(DiskCacheStrategy.NONE).into(person_img)
        }else if (requestCode == PHOTO_REQUEST_CAREMA){
            Glide.with(this).load(mImagePath).skipMemoryCache(true).diskCacheStrategy(DiskCacheStrategy.NONE).into(person_img)
        }
    }

    override fun checkUn() {
        button4.isEnabled = false
    }

    override fun setUserInfo(img: String, name: String, sex: String, iphone: String, carId: String) {
        person_name.text = name
        if (sex == "1") person_sex.text = "男" else person_sex.text = "女"
        person_phone.text = iphone
         mCarId = carId
        val stringBuffer = StringBuffer(carId)
        var newCarId = ""
        if (stringBuffer.length>5) newCarId = stringBuffer.substring(0, 2) + "**********" + stringBuffer.substring(stringBuffer.length - 4, stringBuffer.length)
        person_card_id.text = newCarId
        Glide.with(this).load("http://124.93.196.45:10002${img}").error(R.mipmap.ic_launcher).into(person_img)
    }
    override fun getLayoutResID() = R.layout.activity_person_info
    override fun onSupportNavigateUp(): Boolean {
        finish()
        return super.onSupportNavigateUp()
    }
}