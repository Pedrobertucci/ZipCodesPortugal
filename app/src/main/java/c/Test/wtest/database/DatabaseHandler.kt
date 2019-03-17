package c.Test.wtest.database

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log
import c.Test.wtest.models.Zipcodes


class DatabaseHandler(context: Context) :
    SQLiteOpenHelper(context, DB_NAME, null, DB_VERSION) {

    companion object {
        private const val DB_NAME = "WTestBD"
        private const val DB_VERSION = 1
        private const val TABLE_NAME = "ZipCodes"
        private const val ID = "idZipCode"
        private const val CODE = "code"
        private const val EXT_CODE = "extCode"
        private const val NAME_LOCATE = "nameLocate"
        private const val TITLE = "title"

    }

    override fun onCreate(db: SQLiteDatabase?) {
        val CREATE_TABLE = "CREATE TABLE $TABLE_NAME" +
                "($ID Integer PRIMARY KEY, $CODE TEXT, $EXT_CODE TEXT, $NAME_LOCATE TEXT, $TITLE TEXT)"
        db?.execSQL(CREATE_TABLE)
    }

    fun addZipCode(zipCode: List<Zipcodes>): Boolean {
        var success: Long
        for(zip in zipCode) {
            val db = this.writableDatabase
            val values = ContentValues()
            values.put(CODE, zip.code)
            values.put(EXT_CODE, zip.extCode)
            values.put(NAME_LOCATE, zip.nameLocate)
            values.put(TITLE, zip.title)
            success = db.insert("$TABLE_NAME", null, values)
            Log.v("InsertedID", "$success")
            db.close()
        }
        return true
    }

    fun getFirstZipCodes(): ArrayList<Zipcodes>? {
        var zipcodes: ArrayList<Zipcodes>?= ArrayList()
        val db = readableDatabase
        val selectALLQuery = "SELECT * FROM $TABLE_NAME ORDER BY $TITLE LIMIT 50"
        val cursor = db.rawQuery(selectALLQuery, null)
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                do {
                    val code = cursor.getString(cursor.getColumnIndex(CODE))
                    val extCode = cursor.getString(cursor.getColumnIndex(EXT_CODE))
                    val nameLocate = cursor.getString(cursor.getColumnIndex(NAME_LOCATE))
                    val title =  cursor.getString(cursor.getColumnIndex(TITLE))
                    zipcodes!!.add(Zipcodes(code, extCode, nameLocate, title))

                } while (cursor.moveToNext())
            }
        }
        cursor.close()
        db.close()
        return zipcodes
    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {

    }
}



