package com.example.silka

import android.content.Context
import android.content.Intent
import android.database.sqlite.SQLiteDatabase
import android.provider.BaseColumns
import android.view.LayoutInflater
import android.view.View
import android.view.View.inflate
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.nowy_trening1.view.*
import kotlinx.android.synthetic.main.pojedynczy_trening.view.*

class TreningAdapter(
    val context: Context,
    val db: SQLiteDatabase,
    var delete_trening: ArrayList<trening>
) :
    RecyclerView.Adapter<MyViewHolder2>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder2 {

        val layoutInflater = LayoutInflater.from(parent.context)
        val pojedynczytrening = layoutInflater.inflate(R.layout.pojedynczy_trening, parent, false)
        return MyViewHolder2((pojedynczytrening))

    }

    override fun getItemCount(): Int {

        val cursor = db.query(TableInfo.TABLE_NAME, null, null, null, null, null, null)
        val liczbaWierszy = cursor.count
        cursor.close()
        return liczbaWierszy

    }

    override fun onBindViewHolder(holder: MyViewHolder2, position: Int) {

        val pojedynczy__trening = holder.view.trening_pojedynczy
        val w_name = holder.view.workout_name
        val w_sets = holder.view.set
        val w_reps = holder.view.rep
        val w_weight = holder.view.weight
        val context: Context = holder.view.context

        w_name.setText(delete_trening[holder.adapterPosition].workout_name)
        w_sets.setText(delete_trening[holder.adapterPosition].sets)
        w_reps.setText(delete_trening[holder.adapterPosition].reps)
        w_weight.setText(delete_trening[holder.adapterPosition].weight)


        val cursor = db.query(
            TableInfo.TABLE_NAME,
            null,
            BaseColumns._ID + "=?",
            arrayOf(holder.adapterPosition.plus(1).toString()),
            null,
            null,
            null
        )


        if (cursor.moveToFirst()) {
            if (!(cursor.getString(1).isNullOrEmpty() && cursor.getString(2)
                    .isNullOrEmpty() && cursor.getString(3).isNullOrEmpty() && cursor.getString(4)
                    .isNullOrEmpty())
            ) {

                w_name.setText(cursor.getString(1))
                w_sets.setText(cursor.getString(2))
                w_reps.setText(cursor.getString(3))
                w_weight.setText(cursor.getString(4))
            }
        }


        pojedynczy__trening.setOnClickListener {
            val intent_edit = Intent(context, new_trening1::class.java)

            val w_name_edit = delete_trening[holder.adapterPosition].workout_name
            val w_sets_edit = delete_trening[holder.adapterPosition].sets
            val w_reps_edit = delete_trening[holder.adapterPosition].reps
            val w_weight_edit = delete_trening[holder.adapterPosition].weight
            val id_edit = delete_trening[holder.adapterPosition].id.toString()


            intent_edit.putExtra("workout_name", w_name_edit)
            intent_edit.putExtra("set", w_sets_edit)
            intent_edit.putExtra("rep", w_reps_edit)
            intent_edit.putExtra("weight", w_weight_edit)
            intent_edit.putExtra("ID", id_edit)
            context.startActivity(intent_edit)

        }


        pojedynczy__trening.setOnLongClickListener(object : View.OnLongClickListener {

            override fun onLongClick(v: View?): Boolean {
                db.delete(
                    TableInfo.TABLE_NAME,
                    BaseColumns._ID + "=?",
                    arrayOf(delete_trening[holder.adapterPosition].id.toString())
                )
                delete_trening.removeAt(holder.adapterPosition)
                notifyItemRemoved(holder.adapterPosition)

                return true
            }


        })


    }

}


class MyViewHolder2(val view: View) : RecyclerView.ViewHolder(view)