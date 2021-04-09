package com.example.smartcity_c.presenter

import com.example.smartcity_c.contract.ParkingContract
import com.example.smartcity_c.emp.ParkLotListEmp
import com.example.smartcity_c.network.MM
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

class ParkingPresenter(val  view : ParkingContract.View) : ParkingContract.Presenter {
    val parkingListItems  = mutableListOf<ParkLotListEmp>()
    override fun loadRecyclerData() {
        parkingListItems.clear()
        doAsync {
            val body = MM.getData().getParkLotList().execute().body()
//            body?.let { it.rows.forEach { t ->
//                parkingListItems.add(ParkLotListEmp(t.imgUrl, t.parkName, t.address, t.distance, t.vacancy, t.rates))
//            } }
            body?.let {
                for (i in 0..5){
                    parkingListItems.add(ParkLotListEmp(it.rows[i].imgUrl, it.rows[i].parkName, it.rows[i].address, it.rows[i].distance, it.rows[i].vacancy, it.rows[i].rates,it.rows[i].priceCaps))
                }
            }
            uiThread {
                view.refresh()
            }
        }
    }

    override fun loadMoreRecyclerData() {
        parkingListItems.clear()
        doAsync {
            val body = MM.getData().getParkLotList().execute().body()
            body?.let {
                for (i in it.rows.indices) {
                    parkingListItems.add(
                        ParkLotListEmp(
                            it.rows[i].imgUrl,
                            it.rows[i].parkName,
                            it.rows[i].address,
                            it.rows[i].distance,
                            it.rows[i].vacancy,
                            it.rows[i].rates,
                            it.rows[i].priceCaps
                        )
                    )
                }
            }
            uiThread {
                view.refresh()
            }
        }
    }
}