package com.revolshen.recyclerviewapplication

import android.app.admin.DeviceAdminInfo
import android.content.ContentUris
import android.content.Intent
import android.net.Uri
import android.provider.ContactsContract
import android.support.v4.content.ContextCompat.startActivity
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.revolshen.recyclerviewapplication.MainActivity.Companion.listaID
import kotlinx.android.synthetic.main.contact_row.view.*
import com.revolshen.recyclerviewapplication.MainActivity.Companion.listaKontaktow
import com.revolshen.recyclerviewapplication.MainActivity.Companion.listaNumerow
import kotlinx.android.synthetic.main.contact_row.*
import java.util.*


class MyAdapter: RecyclerView.Adapter<MyViewHolder>(){

    override fun onCreateViewHolder(viewGrop: ViewGroup, p1: Int): MyViewHolder {

        val layoutInflater = LayoutInflater.from(viewGrop.context)
        val contactRow = layoutInflater.inflate(R.layout.contact_row, viewGrop, false)
        return MyViewHolder(contactRow)
    }

    override fun getItemCount(): Int {

        return listaKontaktow.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        val name = holder.view.contact_name
        val number = holder.view.contact_number
        val callBT = holder.view.call_BT
        val smsBT = holder.view.sms_BT

        holder.itemView.setOnLongClickListener(object: View.OnLongClickListener{
            override fun onLongClick(v: View?): Boolean {
                val contact_uri = ContentUris.withAppendedId(ContactsContract.Contacts.CONTENT_URI,
                            listaID[holder.adapterPosition].toLong())

                val edit_intent = Intent()
                    edit_intent.action = Intent.ACTION_EDIT
                    edit_intent.data = contact_uri

                startActivity(holder.itemView.context, edit_intent, null)

                return false
            }

        })

        name.setText(listaKontaktow[holder.adapterPosition])
        number.setText(listaNumerow[holder.adapterPosition])

        smsBT.setOnClickListener{
            val sms_intent = Intent()
            if(listaNumerow[holder.adapterPosition] == "Brak numeru") {
                Toast.makeText(holder.itemView.context, "Nieprawidłowy numer lub brak numeru", Toast.LENGTH_SHORT)
                        .show()
            }
            else{
                sms_intent.data = Uri.parse("sms:"+listaNumerow[holder.adapterPosition])
                sms_intent.action = Intent.ACTION_VIEW
                startActivity(holder.itemView.context, sms_intent, null)
            }

        }

        callBT.setOnClickListener{
            val call_intent = Intent()
            if(listaNumerow[holder.adapterPosition] == "Brak numeru") {
                Toast.makeText(holder.itemView.context, "Nieprawidłowy numer lub brak numeru", Toast.LENGTH_SHORT)
                        .show()
            }
            else{
                call_intent.data = Uri.parse("tel:"+listaNumerow[holder.adapterPosition])
                call_intent.action = Intent.ACTION_CALL
                startActivity(holder.itemView.context, call_intent, null)
            }
        }

    }
}

class MyViewHolder(val view: View): RecyclerView.ViewHolder(view)
















/*

if(listaKontaktow[holder.adapterPosition].length > 12 ){
    name.setText("")
    var x = 0
    while(x < 12 ){
        var znak = listaKontaktow[holder.adapterPosition][x]
        name.append(znak.toString())
        x++
    }
}


smsBT.setOnClickListener{
            val sms_intent = Intent()
            if(listaNumerow[holder.adapterPosition] == "Brak numeru") {
                Toast.makeText(holder.itemView.context, "Nieprawidłowy numer lub brak numeru", Toast.LENGTH_SHORT)
                        .show()
            }
            else{
                sms_intent.data = Uri.parse("sms:"+listaNumerow[holder.adapterPosition])
                sms_intent.action = Intent.ACTION_VIEW
                startActivity(holder.itemView.context, sms_intent, null)
            }
        }



holder.itemView.setOnLongClickListener(object: View.OnLongClickListener{
            override fun onLongClick(v: View?): Boolean {

                val cursor = contentResolver.query(ContactsContract.Contacts.CONTENT_URI, null,
                        ContactsContract.Contacts._ID + "= ?", arrayOf(listaID[holder.adapterPosition]), null)
                cursor.moveToFirst()
                val contact_id = cursor.getLong(cursor.getColumnIndexOrThrow(ContactsContract.Contacts._ID))
                val contact_uri = ContentUris.withAppendedId(ContactsContract.Contacts.CONTENT_URI, contact_id)

                val delete_intent = Intent(Intent.ACTION_EDIT)
                delete_intent.data = contact_uri

                cursor.close()

                startActivity(holder.itemView.context,edit_intent,null)

                return false
            }
        })


*/