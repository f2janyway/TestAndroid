package com.example.oilex

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_test.*
import kotlinx.android.synthetic.main.activity_test.view.*
import kotlinx.android.synthetic.main.item.view.*
import java.util.*
import kotlin.collections.ArrayList
import kotlin.random.Random

class TestActivity : AppCompatActivity() {

    private lateinit var mAdapter: MyAdapter
    private var isHidden = true
    var copyStringSet: TreeSet<String> = TreeSet()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test)

        //click Event
        button1.setOnClickListener {
            val editText = edittext1.text.toString()
            textview1.text = editText
            textview2.text = editText
        }
        button2.setOnClickListener {
            val test2Intent = Intent(applicationContext, Test2Activity::class.java)
            startActivity(test2Intent)
        }
        button3.setOnClickListener {
            isHidden = !isHidden
            mAdapter.isVisible(isHidden)
        }
        button4.setOnClickListener {
            val copyIntent = Intent(android.content.Intent.ACTION_SEND)
            copyIntent.type = "text/plain"
            val sb = StringBuffer()
            copyStringSet.forEach {
                sb.append(it + "\n")
            }
            copyIntent.putExtra(Intent.EXTRA_TEXT,sb.toString())
            val chooser = Intent.createChooser(copyIntent,"공유하기")
            startActivity(chooser)
        }


        //recycler
        mAdapter = MyAdapter(createList(30), this@TestActivity)
        mAdapter.setOnItemCheckListener(listener = object : MyAdapter.OnItemCheckListener {
            override fun onCheck(str: String, pos: Int, isChecked: Boolean) {
//                Snackbar.make(main," " + pos + view.word.text,Snackbar.LENGTH_SHORT).show()
                if (!isChecked) {
                    copyStringSet.remove(str)
                } else {
                    copyStringSet.add(str)
                }
            }
        })
        recyclerview1.apply {
            layoutManager = LinearLayoutManager(this@TestActivity)
            adapter = mAdapter
        }
    }

    fun setButtonsClickEvent(vararg views: View) {
        views.forEach {
            when (it) {
                it.button1 -> {

                }
            }
        }
    }

    fun makeRandomString(): String {
        val random = Random
        val str: StringBuffer = StringBuffer(100)
        for (i in 0..40) {
            str.append((random.nextInt(26) + 65).toChar())
        }
        return str.toString()
    }

    fun createList(len: Int): List<Word> {
        val list = ArrayList<Word>()
        for (i in 0..len) {
            list.add(Word(makeRandomString(), false))
        }
        return list
    }
}
