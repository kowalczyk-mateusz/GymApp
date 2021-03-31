package com.example.silka

import android.content.ContentValues
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.BaseColumns
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_new_trening2.*

class new_trening2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_trening2)

        //--------------------- LACZENIE Z BAZA ----------------
        val dbHelper2 = DataBaseHelper2(applicationContext)
        val db2 = dbHelper2.writableDatabase
        val save_info_toast2 = Toast.makeText(applicationContext, "Cwiczenie zapisane", Toast.LENGTH_SHORT)

        if(intent.hasExtra("workout_name2"))add_name2.setText(intent.getStringExtra("workout_name2"))
        if(intent.hasExtra("set2"))add_set2.setText(intent.getStringExtra("set2"))
        if(intent.hasExtra("rep2"))add_rep2.setText(intent.getStringExtra("rep2"))
        if(intent.hasExtra("weight2"))add_weight2.setText(intent.getStringExtra("weight2"))




//------------------- FUNCKJA ZAPISUJACA CWICZENIE ---------------------
        save_workout2.setOnClickListener{
            val name2 = add_name2.text.toString()
            val sets2 = add_set2.text.toString()
            val reps2 = add_rep2.text.toString()
            val weight2 = add_weight2.text.toString()
            val value2 = ContentValues()
            value2.put(TableInfo2.TABLE_COLUMN_WORKOUT2, name2)
            value2.put(TableInfo2.TABLE_COLUMN_SETS2, sets2)
            value2.put(TableInfo2.TABLE_COLUMN_REPS2, reps2)
            value2.put(TableInfo2.TABLE_COLUMN_WEIGHT2, weight2)

            if(intent.hasExtra("ID"))
            {
                db2.update(TableInfo2.TABLE_NAME2, value2, BaseColumns._ID + "=?", arrayOf(intent.getStringExtra("ID")))
                Toast.makeText(applicationContext, "Notatka edytowana", Toast.LENGTH_SHORT).show()

            }
            else
                if(!name2.isNullOrEmpty() || !sets2.isNullOrEmpty() || !reps2.isNullOrEmpty() || !weight2.isNullOrEmpty()){
                    db2.insertOrThrow(TableInfo2.TABLE_NAME2, null, value2)
                    save_info_toast2.show()

                }
                else
                {
                    Toast.makeText(applicationContext, "Brak treningu do zapisania", Toast.LENGTH_SHORT).show()
                }
            db2.close()
            onBackPressed()



        }



    }
}