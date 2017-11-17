package com.mirsfang.qqspacescollview

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    lateinit var spaceItems:ArrayList<SpaceItem>
    lateinit var spaceAdapter:SpaceAdapter;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        spaceItems = ArrayList();

        for (index in 0..20){
            var spaceItem  =  SpaceItem()

            if(index != 4){
                spaceItem.viewType = 0
            }else{
                spaceItem.viewType = 1
            }
            spaceItems.add(spaceItem)
        }

        listView.layoutManager = LinearLayoutManager(this)
        spaceAdapter = SpaceAdapter(spaceItems,listView)
        listView.adapter = spaceAdapter
    }
}
