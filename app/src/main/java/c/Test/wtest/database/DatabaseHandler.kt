package c.Test.wtest.database

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import c.Test.wtest.models.Zipcodes


class DatabaseHandler(context: Context) :
    SQLiteOpenHelper(context, DB_NAME, null, DB_VERSION) {

    companion object {
        private const val DB_NAME = "WTestBD"
        private const val DB_VERSION = 1
        private const val TABLE_NAME = "ZipCodes"
        private const val ID = "idZipCode"
        private const val CODE = "code"
        private const val TYPE = "type"
        private const val EXT_CODE = "ext"
        private const val NAME_LOCATE = "nameLocate"
        private const val TITLE = "title"
        private const val PORT = "port"
    }

    override fun onCreate(db: SQLiteDatabase?) {
        val CREATE_TABLE = "CREATE TABLE $TABLE_NAME" +
                "($ID Integer PRIMARY KEY," +
                " $CODE TEXT," +
                " $EXT_CODE TEXT," +
                " $NAME_LOCATE TEXT," +
                " $TITLE TEXT," +
                " $TYPE TEXT," +
                " $PORT TEXT)"
        db?.execSQL(CREATE_TABLE)
    }

    fun clearZipCode() {
        val db = this.writableDatabase
        db.execSQL("DELETE FROM $TABLE_NAME")
    }

    fun addZipCodeWithTransactions(zipCode: List<Zipcodes>): Boolean {
        fun insert(values: List<ContentValues>) {
            val db = this.writableDatabase
            try {
                db.beginTransaction()
                values.forEach { db.insert(TABLE_NAME, null, it) }
            } finally {
                db.setTransactionSuccessful()
                db.endTransaction()
                db.close()
            }
        }

        val values =
            zipCode
                .map { zip ->
                    ContentValues().apply {
                        put(CODE, zip.code+"-"+zip.extCode)
                        put(EXT_CODE, zip.extCode)
                        put(NAME_LOCATE, zip.nameLocate)
                        put(TITLE, zip.title)
                        put(TYPE, zip.type)
                        put(PORT, zip.port)
                    }
                }
        insert(values)
        return true
    }

    fun searchZipCodes(search: String): ArrayList<Zipcodes>? {
        var zipcodes: ArrayList<Zipcodes>?= ArrayList()
        val db = readableDatabase
        val selectALLQuery = "SELECT * FROM $TABLE_NAME where " +
                " $CODE LIKE '$search%' OR" +
                " $NAME_LOCATE LIKE '$search%' OR" +
                " $TITLE LIKE '$search%'" +
                " LIMIT 20"

        val cursor = db.rawQuery(selectALLQuery, null)
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                do {
                    val code = cursor.getString(cursor.getColumnIndex(CODE))
                    val extCode = cursor.getString(cursor.getColumnIndex(EXT_CODE))
                    val nameLocate = cursor.getString(cursor.getColumnIndex(NAME_LOCATE))
                    val title =  cursor.getString(cursor.getColumnIndex(TITLE))
                    val type = cursor.getString(cursor.getColumnIndex(TYPE))
                    val port = cursor.getString(cursor.getColumnIndex(PORT))
                    zipcodes!!.add(Zipcodes(code, extCode, nameLocate, title, type, port))

                } while (cursor.moveToNext())
            }
        }
        cursor.close()
        db.close()
        return zipcodes
    }

    fun getFirstZipCodes(): ArrayList<Zipcodes>? {
        var zipCodes: ArrayList<Zipcodes>?= ArrayList()
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
                    val type = cursor.getString(cursor.getColumnIndex(TYPE))
                    val port = cursor.getString(cursor.getColumnIndex(PORT))
                    zipCodes!!.add(Zipcodes(code, extCode, nameLocate, title, type, port))

                } while (cursor.moveToNext())
            }
        }
        cursor.close()
        db.close()
        return zipCodes
    }

    fun getOneZipCodes(): ArrayList<Zipcodes>? {
        var zipCodes: ArrayList<Zipcodes>?= ArrayList()
        val db = readableDatabase
        val selectALLQuery = "SELECT * FROM $TABLE_NAME ORDER BY $TITLE LIMIT 1"
        val cursor = db.rawQuery(selectALLQuery, null)
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                do {
                    val code = cursor.getString(cursor.getColumnIndex(CODE))
                    val extCode = cursor.getString(cursor.getColumnIndex(EXT_CODE))
                    val nameLocate = cursor.getString(cursor.getColumnIndex(NAME_LOCATE))
                    val title =  cursor.getString(cursor.getColumnIndex(TITLE))
                    val type = cursor.getString(cursor.getColumnIndex(TYPE))
                    val port = cursor.getString(cursor.getColumnIndex(PORT))
                    zipCodes!!.add(Zipcodes(code, extCode, nameLocate, title, type, port))

                } while (cursor.moveToNext())
            }
        }
        cursor.close()
        db.close()
        return zipCodes
    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {

    }
}



