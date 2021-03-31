package com.example.silka

import android.content.Context
import android.content.Intent
import android.database.sqlite.SQLiteDatabase
import android.provider.BaseColumns
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.pojedynczy_trening2.view.*

class TreningAdapter2(
    val context: Context,
    val db2: SQLiteDatabase,
    var delete_trening2: ArrayList<trening2>
) :
    RecyclerView.Adapter<MyViewHolder3>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder3 {

        val layoutInflater2 = LayoutInflater.from(parent.context)
        val pojedynczytrening2 = layoutInflater2.inflate(R.layout.pojedynczy_trening2, parent, false)
        return MyViewHolder3((pojedynczytrening2))

    }

    override fun getItemCount(): Int {

        val cursor2 = db2.query(TableInfo2.TABLE_NAME2, null, null, null, null, null, null)
        val liczbaWierszy2 = cursor2.count
        cursor2.close()
        return liczbaWierszy2

    }

    override fun onBindViewHolder(holder: MyViewHolder3, position: Int) {

        val pojedynczy__trening2 = holder.view.trening_pojedynczy2
        val w_name2 = holder.view.workout_name2
        val w_sets2 = holder.view.set2
        val w_reps2 = holder.view.rep2
        val w_weight2 = holder.view.weight2
        val context2: Context = holder.view.context

        w_name2.setText(delete_trening2[holder.adapterPosition].workout_name2)
        w_sets2.setText(delete_trening2[holder.adapterPosition].sets2)
        w_reps2.setText(delete_trening2[holder.adapterPosition].reps2)
        w_weight2.setText(delete_trening2[holder.adapterPosition].weight2)


        val cursor2 = db2.query(
            TableInfo2.TABLE_NAME2,
            null,
            BaseColumns._ID + "=?",
            arrayOf(holder.adapterPosition.plus(1).toString()),
            null,
            null,
            null
        )


        if (cursor2.moveToFirst()) {
            if (!(cursor2.getString(1).isNullOrEmpty() && cursor2.getString(2)
                    .isNullOrEmpty() && cursor2.getString(3).isNullOrEmpty() && cursor2.getString(4)
                    .isNullOrEmpty())
            ) {

                w_name2.setText(cursor2.getString(1))
                w_sets2.setText(cursor2.getString(2))
                w_reps2.setText(cursor2.getString(3))
                w_weight2.setText(cursor2.getString(4))
            }
        }


        pojedynczy__trening2.setOnClickListener {
            val intent_edit2 = Intent(context2, new_trening2::class.java)

            val w_name_edit2 = delete_trening2[holder.adapterPosition].workout_name2
            val w_sets_edit2 = delete_trening2[holder.adapterPosition].sets2
            val w_reps_edit2 = delete_trening2[holder.adapterPosition].reps2
            val w_weight_edit2 = delete_trening2[holder.adapterPosition].weight2
            val id_edit2 = delete_trening2[holder.adapterPosition].id.toString()


            intent_edit2.putExtra("workout_name2", w_name_edit2)
            intent_edit2.putExtra("set2", w_sets_edit2)
            intent_edit2.putExtra("rep2", w_reps_edit2)
            intent_edit2.putExtra("weight2", w_weight_edit2)
            intent_edit2.putExtra("ID", id_edit2)
            context2.startActivity(intent_edit2)

        }


        pojedynczy__trening2.setOnLongClickListener(object : View.OnLongClickListener {

            override fun onLongClick(v: View?): Boolean {
                db2.delete(
                    TableInfo2.TABLE_NAME2,
                    BaseColumns._ID + "=?",
                    arrayOf(delete_trening2[holder.adapterPosition].id.toString())
                )
                delete_trening2.removeAt(holder.adapterPosition)
                notifyItemRemoved(holder.adapterPosition)

                return true
            }


        })


    }

}


class MyViewHolder3(val view: View) : RecyclerView.ViewHolder(view)