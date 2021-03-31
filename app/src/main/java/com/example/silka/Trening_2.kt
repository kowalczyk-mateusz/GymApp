package com.example.silka

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import kotlinx.android.synthetic.main.activity_trening_2.*
import androidx.recyclerview.widget.LinearLayoutManager as LinearLayoutManager

class Trening_2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_trening_2)

        val dbHelper2 = DataBaseHelper2(applicationContext)
        val db2 = dbHelper2.writableDatabase

    }

//-------------------IMPLEMENTACJA MENU ACTION BAR DO AKTYWOSCI TRENING ----------------

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.actionbar2, menu)
        return super.onCreateOptionsMenu(menu)

    }
    //--------------DODANIE INTENCJI DO PRZYCISKU Z ACTION BAR
    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if(item?.itemId == R.id.add_button2){

            var intent3 = Intent(applicationContext, new_trening2::class.java)
            startActivity(intent3)
            overridePendingTransition(R.anim.slide_in_top, R.anim.slide_out_bottom)
        }

        if(item?.itemId == R.id.other_trening2){

            var intent3 = Intent(applicationContext, Trening_1::class.java)
            startActivity(intent3)
            overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right)

        }



        return super.onOptionsItemSelected(item)
    }

    override fun onResume() {
        super.onResume()

        val dbHelper2 = DataBaseHelper2(applicationContext)
        val db2 = dbHelper2.writableDatabase

        val cursor2 = db2.query(TableInfo2.TABLE_NAME2, null, null, null, null, null, null)

        val delete_trening2 = ArrayList<trening2>()

        if(cursor2.count>0){
            cursor2.moveToFirst()
            while(!cursor2.isAfterLast){
                var training2 = trening2()
                training2.id = cursor2.getInt(0)
                training2.workout_name2 = cursor2.getString(1)
                training2.sets2 = cursor2.getString(2)
                training2.reps2 = cursor2.getString(3)
                training2.weight2 = cursor2.getString(4)
                delete_trening2.add(training2)
                cursor2.moveToNext()
            }
        }

        RecyclerView_2.layoutManager = LinearLayoutManager(applicationContext)
        RecyclerView_2.adapter = TreningAdapter2(applicationContext, db2, delete_trening2)
    }
}