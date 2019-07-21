package com.example.draganddroprecyclerviewitemactivity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.helper.ItemTouchHelper
import com.example.draganddroprecyclerviewitemactivity.model.DashboardResponse
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import com.example.draganddroprecyclerviewitemactivity.model.Item
import java.util.*


class MainActivity : AppCompatActivity() {

    lateinit var myDataSet : List<Item>
    lateinit var dashboardRecyclerAdapter : DashboardRecyclerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        loadDataFromAPI()
//        setOnClickListener()
        dragAndDropItems()
    }

    private fun loadDataFromAPI() {
        var apiInterface : APIInterface = APIClient.getClient().create(APIInterface::class.java)
        val call1 = apiInterface.getDashboardItems()
        call1.enqueue(object : Callback<DashboardResponse> {
            override fun onResponse(call: Call<DashboardResponse>, response: Response<DashboardResponse>) {
                myDataSet = response.body()?.items!!
                addRecyclerAdapter(myDataSet as List<Item>)
            }
            override fun onFailure(call: Call<DashboardResponse>, t: Throwable) {
                call.cancel()
            }
        })
    }

    private fun addRecyclerAdapter(myDataSet: List<Item>) {
        recycler.setLayoutManager(GridLayoutManager(this, 2))
        dashboardRecyclerAdapter = DashboardRecyclerAdapter(this@MainActivity, myDataSet)
        recycler.setAdapter(dashboardRecyclerAdapter)
    }


    private fun dragAndDropItems() {
        val helper = ItemTouchHelper(object :
            ItemTouchHelper.SimpleCallback(
                ItemTouchHelper.UP or ItemTouchHelper.DOWN or ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT,
                0
            ) {
            override fun onMove(
                recyclerView: RecyclerView,
                dragged: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {

                val positionDrag = dragged.adapterPosition
                val positionTarget = target.adapterPosition

                Collections.swap(myDataSet, positionDrag, positionTarget)
                dashboardRecyclerAdapter.notifyItemMoved(positionDrag, positionTarget)
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, i: Int) {

            }
        })
        helper.attachToRecyclerView(recycler)

    }












//    private fun setOnClickListener() {
//        recycler.addOnItemTouchListener(
//            RecyclerItemClickListener(this, recycler, object : RecyclerItemClickListener.OnItemClickListener() {
//                fun onItemClick(view: View, position: Int) {
//                    // do whatever
//                }
//
//                fun onLongItemClick(view: View, position: Int) {
//                    // do whatever
//                }
//            })
//        )
//    }


}
