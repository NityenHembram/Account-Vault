package com.nityen.accountvault.adapter

import android.accounts.Account
import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.recyclerview.widget.RecyclerView
import com.nityen.accountvault.R
import com.nityen.accountvault.model.Accounts


private const val TAG = "gadi"
class RecyclerViewAdapter(val context:Context,  var list:ArrayList<Accounts>, var onItemClickedListener: OnItemClickedListener): RecyclerView.Adapter<RecyclerViewAdapter.Holder>() {






    class Holder(var itemView: View,
                 private var onItemClickedListener: OnItemClickedListener,var listData:ArrayList<Accounts>) : RecyclerView.ViewHolder(itemView),View.OnClickListener{
        val button = itemView.findViewById<ImageView>(R.id.delete_btn)
        val editbtn = itemView.findViewById<ImageView>(R.id.update_btn)
        val itemBtn:LinearLayout = itemView.findViewById(R.id.itemBtn)

        init {
            itemView.setOnClickListener(this)
            button.setOnClickListener(this)
            editbtn.setOnClickListener(this)
        }
        val name = itemView.findViewById<TextView>(R.id.acc_name)
        override fun onClick(v: View?) {
            when(v?.id){
                R.id.update_btn -> onItemClickedListener.onEditClicked(listData,adapterPosition)

                R.id.delete_btn -> onItemClickedListener.onDeleteClicked(listData,adapterPosition)

                R.id.itemBtn ->    onItemClickedListener.onItemClicked(adapterPosition)
            }




        }

    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerViewAdapter.Holder {
        val view = LayoutInflater.from(context).inflate(R.layout.items,parent,false)
        return Holder(view,onItemClickedListener,list)
    }

    override fun onBindViewHolder(holder: RecyclerViewAdapter.Holder, position: Int) {

        val p  = list[position]
//        Log.d(TAG, "onBindViewHolder: ${p.name}")
        holder.name.text = p.account_name




    }

    override fun getItemCount(): Int {
        Log.d(TAG, "getItemCount: ${list.size}")
        return list.size
    }


}



interface OnItemClickedListener{
    fun onItemClicked(position:Int)
    fun onDeleteClicked(list: ArrayList<Accounts>, position:Int)
    fun onEditClicked(list:ArrayList<Accounts>,position:Int)
}