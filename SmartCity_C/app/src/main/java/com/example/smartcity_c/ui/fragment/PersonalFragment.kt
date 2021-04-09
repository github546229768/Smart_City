package com.example.smartcity_c.ui.fragment

import android.content.Context
import android.content.SharedPreferences
import androidx.core.content.edit
import com.bumptech.glide.Glide
import com.example.smartcity_c.R
import com.example.smartcity_c.contract.PersonalContract
import com.example.smartcity_c.presenter.PersonalPresenter
import com.example.smartcity_c.ui.activity.LoginActivity
import com.example.smartcity_c.ui.activity.personal.OrderListActivity
import com.example.smartcity_c.ui.activity.personal.PersonInfoActivity
import com.example.smartcity_c.ui.activity.personal.SuggestActivity
import com.example.smartcity_c.ui.activity.personal.UpdatePasswordActivity
import kotlinx.android.synthetic.main.activity_person_info.*
import kotlinx.android.synthetic.main.fragment_personal.*
import org.jetbrains.anko.alert
import org.jetbrains.anko.noButton
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.yesButton

class PersonalFragment : BaseFragment(),PersonalContract.View {
    val presenter by lazy { PersonalPresenter(requireActivity(),this) }

    val sp: SharedPreferences by lazy {
        requireActivity().getSharedPreferences("fistShow",Context.MODE_PRIVATE)
    }
    override fun init() {
        personLayout.setOnClickListener { context?.startActivity<PersonInfoActivity>() }
        orderLayout.setOnClickListener { context?.startActivity<OrderListActivity>() }
        suggestLayout.setOnClickListener { context?.startActivity<SuggestActivity>() }
        passwordLayout.setOnClickListener { context?.startActivity<UpdatePasswordActivity>() }
        presenter.loadUserData()
        bt_logout.setOnClickListener {
            context?.alert("退出后，将退出无法接受消息") {
                yesButton {
                    requireActivity().startActivity<LoginActivity>()
                    requireActivity().finish()
                    sp.edit { putBoolean("isFirst",true) }
                }
                noButton { dialog ->  dialog.dismiss() }
            }?.show()
        }
    }
    override fun getLayoutInflaterId() = R.layout.fragment_personal

    override fun setUserInfo(img: String, name: String, sex: String, iphone: String, carId: String) {
        name?.let { tv_name.text = it }
        Glide.with(this).load("http://124.93.196.45:10002${img}").error(R.mipmap.ic_launcher).into(img_avatar)
    }

    override fun checkUn() {

    }
}