package com.revolshen.recyclerviewapplication


import android.content.ContentResolver
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import android.provider.ContactsContract
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*


class MainActivity : AppCompatActivity() {

    companion object {
        val listaKontaktow = arrayListOf<String>()
        val listaNumerow = arrayListOf<String>()
        val listaID = arrayListOf<String>()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if(savedInstanceState != null){
            screen.setText(savedInstanceState.getString("Liczba"))
        }
        else{
            screen.setText("${Random().nextInt(100)}")
        }

        Log.d("ZMIANA", "Wykonała się metoda onCreate")

        //Element odpowiedzialny za ustawienie widoków w formie listy
        recyclerView.layoutManager = LinearLayoutManager(this)
        //Element odpowiedzialny za połączenie między danymi, a widokami
        recyclerView.adapter = MyAdapter()

        val contentResolver = contentResolver
        val cursor = contentResolver.query(ContactsContract.Contacts.CONTENT_URI,
                null,
                null,
                null,
                ContactsContract.Contacts.DISPLAY_NAME_PRIMARY,
                null)

        try {
            if (cursor != null) {
                while (cursor.moveToNext()) {
                    val id = cursor.getString(cursor.getColumnIndexOrThrow(ContactsContract.Contacts._ID))
                    listaID.add(id)
                    val name = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME))
                    listaKontaktow.add(name)
                    readPhoneNumber(contentResolver, id)
                   } }
        }
        finally {
            cursor?.close()
        }
    }

    override fun onSaveInstanceState(outState: Bundle?) {
        super.onSaveInstanceState(outState)
        outState?.putString("Liczba", screen.text.toString())

    }


    override fun onDestroy() {
        super.onDestroy()
        listaID.clear()
        listaNumerow.clear()
        listaKontaktow.clear()

        Log.d("ZMIANA", "Wykonała się metoda onDestroy")

    }

}

fun readPhoneNumber(contentResolver: ContentResolver, id: String){

    val phoneCursor = contentResolver.query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null,
            ContactsContract.CommonDataKinds.Phone.CONTACT_ID + "= ?", arrayOf(id), null)

        if(phoneCursor!!.moveToFirst()){
            val number = phoneCursor.getString(
                    phoneCursor.getColumnIndexOrThrow(ContactsContract.CommonDataKinds.Phone.NUMBER))
            MainActivity.listaNumerow.add(number)

        }
        else MainActivity.listaNumerow.add("Brak numeru")

        phoneCursor.close()
}


























fun readPhoneNumberORGIN(contentResolver: ContentResolver, id: String){
    // get the phone number
    var phoneCursor = contentResolver.query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null,
            ContactsContract.CommonDataKinds.Phone.CONTACT_ID + " = ?",
            arrayOf(id), null)
    phoneCursor.moveToFirst()
    val phone = phoneCursor.getString(
            phoneCursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER))
    MainActivity.listaNumerow.add(phone)
    phoneCursor.close()
}

