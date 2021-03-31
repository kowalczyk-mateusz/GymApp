package com.example.silka

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.provider.BaseColumns

//---------------------- OPIS TABELI -----
object TableInfo2 : BaseColumns {
    const val TABLE_NAME2 = "Trening2"
    const val TABLE_COLUMN_WORKOUT2 = "workoutName2"
    const val TABLE_COLUMN_SETS2 = "serie2"
    const val TABLE_COLUMN_REPS2 = "potworzenia2"
    const val TABLE_COLUMN_WEIGHT2 = "Waga2"
}
//--------------------


//-------------------- PODSTAWOWE KOMENDY SQL ---------------
object BasicCommand2 {

    const val SQL_CREATE_TABLE2 =
        "CREATE TABLE ${TableInfo2.TABLE_NAME2} (" +
                "${BaseColumns._ID} INTEGER PRIMARY KEY, " +
                "${TableInfo2.TABLE_COLUMN_WORKOUT2} TEXT NOT NULL," +
                "${TableInfo2.TABLE_COLUMN_SETS2} TEXT NOT NULL," +
                "${TableInfo2.TABLE_COLUMN_REPS2} TEXT NOT NULL," +
                "${TableInfo2.TABLE_COLUMN_WEIGHT2} TEXT NOT NULL)"

    const val SQL_DELETE_TABLE2 = "DROP TABLE IF EXISTS ${TableInfo2.TABLE_NAME2}"
}

//-------------------------------------------------------------------------------

class DataBaseHelper2(context2: Context): SQLiteOpenHelper(context2, TableInfo2.TABLE_NAME2, null, 1){
    override fun onCreate(db2: SQLiteDatabase?) {
        db2?.execSQL(BasicCommand2.SQL_CREATE_TABLE2)
    }

    override fun onUpgrade(db2: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db2?.execSQL(BasicCommand2.SQL_DELETE_TABLE2)
        onCreate(db2)

    }

}

