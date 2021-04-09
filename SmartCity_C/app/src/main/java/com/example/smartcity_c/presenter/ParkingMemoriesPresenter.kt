package com.example.smartcity_c.presenter

import com.example.smartcity_c.contract.ParkingMemoriesContract
import com.example.smartcity_c.emp.ParkingMemoriesEmp
import com.example.smartcity_c.network.MM
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

class ParkingMemoriesPresenter(val view : ParkingMemoriesContract.View):ParkingMemoriesContract.Presenter {

    val parkingMemoriesItems  = mutableListOf<ParkingMemoriesEmp>()

    override fun searchRecyclerData(startTime: String, endTime: String) {
        parkingMemoriesItems.clear()
        doAsync {
            val body = MM.getData().getParkRecordList(startTime,endTime).execute().body()
            body?.let {
                for (i in it.rows.indices){
                    parkingMemoriesItems.add(
                        ParkingMemoriesEmp(it.rows[i].plateNumber,
                            it.rows[i].parkName,
                            it.rows[i].outTime,
                            it.rows[i].entryTime,
                            it.rows[i].monetary)
                    )
                }
            }
            uiThread { view.refresh() }
        }
    }

    override fun loadRecyclerData() {
        parkingMemoriesItems.clear()
        doAsync {
            val body = MM.getData().getParkRecordList().execute().body()
                body?.let {
                    for (i in 0 .. 5){
                        parkingMemoriesItems.add(
                            ParkingMemoriesEmp(it.rows[i].plateNumber,
                                it.rows[i].parkName,
                                it.rows[i].outTime,
                                it.rows[i].entryTime,
                                it.rows[i].monetary)
                        )
                    }
                  }
            uiThread { view.refresh() }
        }
    }

    override fun loadMoreRecyclerData() {
        parkingMemoriesItems.clear()
        doAsync {
            val body = MM.getData().getParkRecordList().execute().body()
            body?.let {
                for (i in it.rows.indices){
                    parkingMemoriesItems.add(
                        ParkingMemoriesEmp(it.rows[i].plateNumber,
                            it.rows[i].parkName,
                            it.rows[i].outTime,
                            it.rows[i].entryTime,
                            it.rows[i].monetary)
                    )
                }
            }
            uiThread { view.refresh() }
        }
    }
}