package com.android.myandroidcertification

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.myandroidcertification.activities.DiceRoller
import com.android.myandroidcertification.activities.TipCalculator
import com.android.myandroidcertification.databinding.ActivityMainBinding
import kotlin.reflect.KClass

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val layoutManager = LinearLayoutManager(this)
        binding.mRecyclerView.layoutManager = layoutManager
        binding.mRecyclerView.itemAnimator = DefaultItemAnimator()
        binding.mRecyclerView.adapter = RecyclerViewAdapter(this, ClassMap.getAllClasses())
    }

    private class RecyclerViewAdapter(val context: Context, var arrayList: ArrayList<String>) :
        RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>() {

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.main_card_view_list_content, parent, false)

            return ViewHolder(view)
        }

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            try {
                val currItem = arrayList[position]
                holder.textView.text = currItem
                holder.mainLayout.setOnClickListener {
                    var nextClass: KClass<*> = MainActivity::class
                    for (map: ClassMap in ClassMap.values()) {
                        if (map.s.equals(currItem, true)) {
                            nextClass = map.c
                            break
                        }
                    }
                    val intent = Intent(context, nextClass::class.java)
                    context.startActivity(intent)
                }
            } catch (e: Exception) {
                Log.e("RecyclerViewAdapter", e.toString())
            }
        }

        override fun getItemCount(): Int {
            return arrayList.size
        }

        class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
            val mainLayout: CardView = view.findViewById(R.id.mainLayout)
            val textView: TextView = view.findViewById(R.id.textView)
        }
    }

    enum class ClassMap(var s: String, var c: KClass<*>) {
        DiceRoll("Dice Roller", DiceRoller::class),
        CalculateTip("Tip Calculator", TipCalculator::class)
        ;

        companion object {
            fun getAllClasses(): ArrayList<String> {
                val arrayList = ArrayList<String>()
                for (cls: ClassMap in values()) {
                    arrayList.add(cls.s)
                }
                return arrayList
            }
        }
    }
}