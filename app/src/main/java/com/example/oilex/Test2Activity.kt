package com.example.oilex

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_test2.*

class Test2Activity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test2)
        button1_2.setOnClickListener {
            finish()
        }
        val list = ArrayList<Word>()
        for (i in 1..100) list.add(Word("1111",false))

        recyclerview2.apply{
            adapter = MyAdapter(list,this@Test2Activity)
            layoutManager = LinearLayoutManager(this@Test2Activity)
        }

    }
}
