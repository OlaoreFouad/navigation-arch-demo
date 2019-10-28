package dev.foodie.navigationdemo.data

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import dev.foodie.navigationdemo.models.Entry
import dev.foodie.navigationdemo.utils.Utils

class DatabaseHandler(context: Context) : SQLiteOpenHelper(context, Utils.DATABASE_NAME, null, Utils.DATABASE_VERSION) {

    private lateinit var db: SQLiteDatabase

    override fun onCreate(p0: SQLiteDatabase?) {

        p0?.execSQL(CREATE_ENTRIES_TABLE)

    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {
        // dropping and migrating to a new database!
        p0?.execSQL("DROP TABLE IF EXISTS ${ Utils.TABLE_NAME }")

        onCreate(p0)
    }

    fun addEntry(entry: Entry): Int {
        setWritable()

        val values = ContentValues()
        values.put(Utils.KEY_ID, entry.id)
        values.put(Utils.KEY_RECIPIENT, entry.recipient)
        values.put(Utils.KEY_AMOUNT, entry.amount)

        return this.db.insert(Utils.TABLE_NAME, null, values).toInt()

    }

    fun getEntries(): List<Entry> {
        setReadable()

        val entries = mutableListOf<Entry>()

        val cursor: Cursor
        cursor = this.db.rawQuery(SELECT_ALL_ENTRIES, null)

        if (cursor != null) {
            cursor?.moveToFirst()
            while (cursor.moveToNext()) {
                val entry: Entry
                val id = cursor.getString(cursor.getColumnIndex(Utils.KEY_ID))
                val recipient = cursor.getString(cursor.getColumnIndex(Utils.KEY_RECIPIENT))
                val amount = cursor.getInt(cursor.getColumnIndex(Utils.KEY_AMOUNT))

                entry = Entry(id, recipient, amount)
                entries.add(entry)
            }
        }

        cursor.close()

        return entries
    }

    private fun setReadable() {
        this.db = readableDatabase
    }

    private fun setWritable() {
        this.db = writableDatabase
    }

    companion object {
        const val CREATE_ENTRIES_TABLE = "CREATE TABLE ${ Utils.TABLE_NAME } ( ${Utils.KEY_ID} TEXT PRIMARY KEY, ${ Utils.KEY_RECIPIENT } TEXT, " +
                "${ Utils.KEY_AMOUNT } INTEGER)"

        const val SELECT_ALL_ENTRIES = "SELECT * FROM ${ Utils.TABLE_NAME }"
    }

}