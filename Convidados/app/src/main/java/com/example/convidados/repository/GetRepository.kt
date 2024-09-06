package com.example.convidados.repository

import android.content.ContentValues
import android.content.Context
import com.example.convidados.GetModel
import com.example.convidados.constants.DataBaseConstants

class GetRepository private constructor(context: Context) {
    private val getDatabase = GetDataBase(context)

    // Singleton
    companion object {

        private lateinit var repository: GetRepository
        fun getInstance(context: Context): GetRepository {
            if (!Companion::repository.isInitialized) {
                // verifica se a variavel foi inicializada alguma vez
                repository = GetRepository(context)
            }
            return repository
        }


    }

    fun insert(guest: GetModel): Boolean {
        try {
            val db = getDatabase.writableDatabase
            val values = ContentValues()
            val presence = if (guest.presence) 1 else 0
            values.put(DataBaseConstants.GUEST.COLUMNS.NAME, guest.name)
            values.put(DataBaseConstants.GUEST.COLUMNS.PRESENCE, presence)
            db.insert(DataBaseConstants.GUEST.TABLE_NAME, null, values)
            return true
        } catch (e: Exception) {
            return false
        }
    }

    fun update(guest: GetModel): Boolean {
        return try {
            val db = getDatabase.writableDatabase
            val presence = if (guest.presence) 1 else 0

            val values = ContentValues()
            values.put(DataBaseConstants.GUEST.COLUMNS.PRESENCE, presence)
            values.put(DataBaseConstants.GUEST.COLUMNS.NAME, guest.name)
            val selection = DataBaseConstants.GUEST.COLUMNS.ID + " = ? "
            val args = arrayOf(guest.id.toString())

            db.update(DataBaseConstants.GUEST.TABLE_NAME, values, selection, args)
            true
        } catch (e: Exception) {
            false
        }
    }

    fun delete(id: Int): Boolean {
        return try {
            val db = getDatabase.writableDatabase
            val selection = DataBaseConstants.GUEST.COLUMNS.ID + " = ? "
            val args = arrayOf(id.toString())
            db.delete(DataBaseConstants.GUEST.TABLE_NAME, selection, args)
            true
        } catch (e: Exception) {
            false
        }

    }
    fun getAll(): List<GetModel>{
        val list= mutableListOf<GetModel>()
        try {
            val db = getDatabase.readableDatabase
            val projection = arrayOf(
                DataBaseConstants.GUEST.COLUMNS.ID,
                DataBaseConstants.GUEST.COLUMNS.NAME,
                DataBaseConstants.GUEST.COLUMNS.PRESENCE
            )
            val cursor = db.query(
                DataBaseConstants.GUEST.TABLE_NAME, projection,
                null, null, null,
                null, null
            )
            if (cursor != null && cursor.count > 0) {
                while (cursor.moveToNext()) {
                    val id = cursor.getInt(cursor.getColumnIndex(DataBaseConstants.GUEST.COLUMNS.ID))
                    val name = cursor.getString(cursor.getColumnIndex(DataBaseConstants.GUEST.COLUMNS.NAME))
                    val presence = cursor.getInt(cursor.getColumnIndex(DataBaseConstants.GUEST.COLUMNS.PRESENCE))
                    val guest = GetModel(id, name, presence == 1)
                    list.add(guest)
                }
            }
            cursor.close()

        } catch (e: Exception){
            return list
        }
        return list
    }
    fun getPresence(): List<GetModel>{
        val list= mutableListOf<GetModel>()
        try {
            val db = getDatabase.readableDatabase
            val projection = arrayOf(
                DataBaseConstants.GUEST.COLUMNS.ID,
                DataBaseConstants.GUEST.COLUMNS.NAME,
                DataBaseConstants.GUEST.COLUMNS.PRESENCE
            )
          val  selection=DataBaseConstants.GUEST.COLUMNS + " = ?"
          val args= arrayOf("1")
            val cursorPresence= db.rawQuery("SELECT *from Guest WHERE  presence=1", null)
            val cursor = db.query(
                DataBaseConstants.GUEST.TABLE_NAME, projection,
                null, null, null,
                null, null
            )
            if (cursor != null && cursor.count > 0) {
                while (cursor.moveToNext()) {
                    val id = cursor.getInt(cursor.getColumnIndex(DataBaseConstants.GUEST.COLUMNS.ID))
                    val name = cursor.getString(cursor.getColumnIndex(DataBaseConstants.GUEST.COLUMNS.NAME))
                    val presence = cursor.getInt(cursor.getColumnIndex(DataBaseConstants.GUEST.COLUMNS.PRESENCE))
                    val guest = GetModel(id, name, presence == 1)
                    list.add(guest)
                }
            }
            cursor.close()

        } catch (e: Exception){
            return list
        }
        return list
    }

    fun getAbsent(): List<GetModel>{
        val list= mutableListOf<GetModel>()
        try {
            val db = getDatabase.readableDatabase
            val projection = arrayOf(
                DataBaseConstants.GUEST.COLUMNS.ID,
                DataBaseConstants.GUEST.COLUMNS.NAME,
                DataBaseConstants.GUEST.COLUMNS.PRESENCE
            )
            val  selection==DataBaseConstants.GUEST.COLUMNS + " = ?";
            val args= arrayOf("1")
            val cursorPresence= db.rawQuery("SELECT *from Guest WHERE  presence=0", null)
            val cursor = db.query(
                DataBaseConstants.GUEST.TABLE_NAME, projection,
                null, null, null,
                null, null
            )
            if (cursor != null && cursor.count > 0) {
                while (cursor.moveToNext()) {
                    val id = cursor.getInt(cursor.getColumnIndex(DataBaseConstants.GUEST.COLUMNS.ID))
                    val name = cursor.getString(cursor.getColumnIndex(DataBaseConstants.GUEST.COLUMNS.NAME))
                    val presence = cursor.getInt(cursor.getColumnIndex(DataBaseConstants.GUEST.COLUMNS.PRESENCE))
                    val guest = GetModel(id, name, presence == 1)
                    list.add(guest)
                }
            }
            cursor.close()

        } catch (e: Exception){
            return list
        }
        return list
    }
}
}

