package com.example.silka

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_trening_1.*
import kotlinx.android.synthetic.main.pojedynczy_trening.*
import androidx.recyclerview.widget.LinearLayoutManager as LinearLayoutManager

class Trening_1 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_trening_1)

        val dbHelper = DataBaseHelper(applicationContext)
        val db = dbHelper.writableDatabase

    }

//-------------------IMPLEMENTACJA MENU ACTION BAR DO AKTYWOSCI TRENING ----------------

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.actionbar, menu)
        return super.onCreateOptionsMenu(menu)

    }
//--------------DODANIE INTENCJI DO PRZYCISKU Z ACTION BAR
    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if(item?.itemId == R.id.add_button){

            var intent2 = Intent(applicationContext, new_trening1::class.java)
            startActivity(intent2)
            overridePendingTransition(R.anim.slide_in_top, R.anim.slide_out_bottom)
        }
    if(item?.itemId == R.id.other_trening){

        var intent3 = Intent(applicationContext, Trening_2::class.java)
        startActivity(intent3)
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left)
    }

        return super.onOptionsItemSelected(item)



    }



    override fun onResume() {
        super.onResume()

        val dbHelper = DataBaseHelper(applicationContext)
        val db = dbHelper.writableDatabase

        val cursor = db.query(TableInfo.TABLE_NAME, null, null, null, null, null, null)

        val delete_trening = ArrayList<trening>()

        if(cursor.count>0){
            cursor.moveToFirst()
            while(!cursor.isAfterLast){
                var training = trening()
                training.id = cursor.getInt(0)
                training.workout_name = cursor.getString(1)
                training.sets = cursor.getString(2)
                training.reps = cursor.getString(3)
                training.weight = cursor.getString(4)
                delete_trening.add(training)
                cursor.moveToNext()
            }
        }

        RecyclerView_1.layoutManager = LinearLayoutManager(applicationContext)
        RecyclerView_1.adapter = TreningAdapter(applicationContext, db, delete_trening)
    }
}