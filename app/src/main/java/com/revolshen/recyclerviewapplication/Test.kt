/*
package com.revolshen.recyclerviewapplication

import android.app.Activity
import android.content.ContentResolver
import android.database.Cursor
import android.os.Bundle
import android.provider.ContactsContract

class GetContactsDemo : Activity() {
    /** Called when the activity is first created.  */
    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        readContacts()
    }

    fun readContacts() {
        val cr = contentResolver
        val cur = cr.query(ContactsContract.Contacts.CONTENT_URI, null, null, null, null)

        if (cur!!.count > 0) {
            while (cur.moveToNext()) {
                val id = cur.getString(cur.getColumnIndex(ContactsContract.Contacts._ID))
                val name = cur.getString(cur.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME))
                if (Integer.parseInt(cur.getString(cur.getColumnIndex(ContactsContract.Contacts.HAS_PHONE_NUMBER))) > 0) {
                    println("name : $name, ID : $id")

                    // get the phone number
                    val pCur = cr.query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null,
                            ContactsContract.CommonDataKinds.Phone.CONTACT_ID + " = ?",
                            arrayOf(id), null)
                    while (pCur!!.moveToNext()) {
                        val phone = pCur.getString(
                                pCur.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER))
                        println("phone$phone")
                    }
                    pCur.close()


                    // get email and type

                    val emailCur = cr.query(
                            ContactsContract.CommonDataKinds.Email.CONTENT_URI, null,
                            ContactsContract.CommonDataKinds.Email.CONTACT_ID + " = ?",
                            arrayOf(id), null)
                    while (emailCur!!.moveToNext()) {
                        // This would allow you get several email addresses
                        // if the email addresses were stored in an array
                        val email = emailCur.getString(
                                emailCur.getColumnIndex(ContactsContract.CommonDataKinds.Email.DATA))
                        val emailType = emailCur.getString(
                                emailCur.getColumnIndex(ContactsContract.CommonDataKinds.Email.TYPE))

                        println("Email $email Email Type : $emailType")
                    }
                    emailCur.close()

                    // Get note.......
                    val noteWhere = ContactsContract.Data.CONTACT_ID + " = ? AND " + ContactsContract.Data.MIMETYPE + " = ?"
                    val noteWhereParams = arrayOf(id, ContactsContract.CommonDataKinds.Note.CONTENT_ITEM_TYPE)
                    val noteCur = cr.query(ContactsContract.Data.CONTENT_URI, null, noteWhere, noteWhereParams, null)
                    if (noteCur!!.moveToFirst()) {
                        val note = noteCur.getString(noteCur.getColumnIndex(ContactsContract.CommonDataKinds.Note.NOTE))
                        println("Note $note")
                    }
                    noteCur.close()

                    //Get Postal Address....

                    val addrWhere = ContactsContract.Data.CONTACT_ID + " = ? AND " + ContactsContract.Data.MIMETYPE + " = ?"
                    val addrWhereParams = arrayOf(id, ContactsContract.CommonDataKinds.StructuredPostal.CONTENT_ITEM_TYPE)
                    val addrCur = cr.query(ContactsContract.Data.CONTENT_URI, null, null, null, null)
                    while (addrCur!!.moveToNext()) {
                        val poBox = addrCur.getString(
                                addrCur.getColumnIndex(ContactsContract.CommonDataKinds.StructuredPostal.POBOX))
                        val street = addrCur.getString(
                                addrCur.getColumnIndex(ContactsContract.CommonDataKinds.StructuredPostal.STREET))
                        val city = addrCur.getString(
                                addrCur.getColumnIndex(ContactsContract.CommonDataKinds.StructuredPostal.CITY))
                        val state = addrCur.getString(
                                addrCur.getColumnIndex(ContactsContract.CommonDataKinds.StructuredPostal.REGION))
                        val postalCode = addrCur.getString(
                                addrCur.getColumnIndex(ContactsContract.CommonDataKinds.StructuredPostal.POSTCODE))
                        val country = addrCur.getString(
                                addrCur.getColumnIndex(ContactsContract.CommonDataKinds.StructuredPostal.COUNTRY))
                        val type = addrCur.getString(
                                addrCur.getColumnIndex(ContactsContract.CommonDataKinds.StructuredPostal.TYPE))

                        // Do something with these....

                    }
                    addrCur.close()

                    // Get Instant Messenger.........
                    val imWhere = ContactsContract.Data.CONTACT_ID + " = ? AND " + ContactsContract.Data.MIMETYPE + " = ?"
                    val imWhereParams = arrayOf(id, ContactsContract.CommonDataKinds.Im.CONTENT_ITEM_TYPE)
                    val imCur = cr.query(ContactsContract.Data.CONTENT_URI, null, imWhere, imWhereParams, null)
                    if (imCur!!.moveToFirst()) {
                        val imName = imCur.getString(
                                imCur.getColumnIndex(ContactsContract.CommonDataKinds.Im.DATA))
                        val imType: String
                        imType = imCur.getString(
                                imCur.getColumnIndex(ContactsContract.CommonDataKinds.Im.TYPE))
                    }
                    imCur.close()

                    // Get Organizations.........

                    val orgWhere = ContactsContract.Data.CONTACT_ID + " = ? AND " + ContactsContract.Data.MIMETYPE + " = ?"
                    val orgWhereParams = arrayOf(id, ContactsContract.CommonDataKinds.Organization.CONTENT_ITEM_TYPE)
                    val orgCur = cr.query(ContactsContract.Data.CONTENT_URI, null, orgWhere, orgWhereParams, null)
                    if (orgCur!!.moveToFirst()) {
                        val orgName = orgCur.getString(orgCur.getColumnIndex(ContactsContract.CommonDataKinds.Organization.DATA))
                        val title = orgCur.getString(orgCur.getColumnIndex(ContactsContract.CommonDataKinds.Organization.TITLE))
                    }
                    orgCur.close()
                }
            }
        }
    }

}

*/