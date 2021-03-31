package com.example.silka

import android.content.ContentValues
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.BaseColumns
import android.widget.Toast
import kotlinx.android.synthetic.main.nowy_trening1.*

class new_trening1 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.nowy_trening1)

        //--------------------- LACZENIE Z BAZA ----------------
        val dbHelper = DataBaseHelper(applicationContext)
        val db = dbHelper.writableDatabase
        val save_info_toast = Toast.makeText(applicationContext, "Cwiczenie zapisane", Toast.LENGTH_SHORT)

        if(intent.hasExtra("workout_name"))add_name.setText(intent.getStringExtra("workout_name"))
        if(intent.hasExtra("set"))add_set.setText(intent.getStringExtra("set"))
        if(intent.hasExtra("rep"))add_rep.setText(intent.getStringExtra("rep"))
        if(intent.hasExtra("weight"))add_weight.setText(intent.getStringExtra("weight"))




//------------------- FUNCKJA ZAPISUJACA CWICZENIE ---------------------
        save_workout.setOnClickListener{
            val name = add_name.text.toString()
            val sets = add_set.text.toString()
            val reps = add_rep.text.toString()
            val weight = add_weight.text.toString()
            val value = ContentValues()
            value.put(TableInfo.TABLE_COLUMN_WORKOUT, name)
            value.put(TableInfo.TABLE_COLUMN_SETS, sets)
            value.put(TableInfo.TABLE_COLUMN_REPS, reps)
                value.put(TableInfo.TABLE_COLUMN_WEIGHT, weight)

            if(intent.hasExtra("ID"))
            {
                db.update(TableInfo.TABLE_NAME, value, BaseColumns._ID + "=?", arrayOf(intent.getStringExtra("ID")))
                Toast.makeText(applicationContext, "Notatka edytowana", Toast.LENGTH_SHORT).show()

            }
            else
                if(!name.isNullOrEmpty() || !sets.isNullOrEmpty() || !reps.isNullOrEmpty() || !weight.isNullOrEmpty()){
                    db.insertOrThrow(TableInfo.TABLE_NAME, null, value)
                    save_info_toast.show()

                }
            else
                {
                    Toast.makeText(applicationContext, "Brak treningu do zapisania", Toast.LENGTH_SHORT).show()
                }
        db.close()
            onBackPressed()



        }



    }
}