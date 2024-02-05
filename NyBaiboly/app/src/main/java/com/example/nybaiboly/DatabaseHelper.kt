import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.example.nybaiboly.ChapitreEtVerse
import com.example.nybaiboly.DatabaseOpenHelper
import com.example.nybaiboly.Livres
import com.example.nybaiboly.Testament

class DatabaseHelper private constructor(context: Context) {

    private val openHelper: SQLiteOpenHelper
    private var db: SQLiteDatabase? = null

    init {
        openHelper = DatabaseOpenHelper(context)
    }

    companion object {
        private var instance: DatabaseHelper? = null

        fun getInstance(context: Context): DatabaseHelper {
            if (instance == null) {
                instance = DatabaseHelper(context)
            }
            return instance!!
        }
    }

    fun open() {
        db = openHelper.writableDatabase
    }

    fun close() {
        db?.close()
    }

    fun getData(colonne: String, table: String, id: Int): String? {
        val selectQuery = "SELECT $colonne FROM $table WHERE id = $id"
        val cursor: Cursor = db!!.rawQuery(selectQuery, null)
        var result: String? = null
        if (cursor.moveToFirst()) {
            result = cursor.getString(0)
        }
        cursor.close()
        return result
    }


    fun getTestamentData(): ArrayList<Testament> {
        val arrayList = ArrayList<Testament>()

        val selectQuery = "SELECT * FROM ci_diem_testamenta"
        val cursor: Cursor = db!!.rawQuery(selectQuery, null)
        if (cursor.moveToFirst()) {
            do {
                val id = cursor.getInt(0)
                val nom = cursor.getString(1)
                val testament = Testament(id, nom)
                arrayList.add(testament)
            } while (cursor.moveToNext())
        }
        cursor.close()
        return arrayList
    }

    fun getLivresData(idlivres : Int): ArrayList<Livres> {
        val arrayList = ArrayList<Livres>()
        val selectQuery = "SELECT * FROM ci_diem_boky where b_testid == $idlivres order by b_order"
        val cursor: Cursor = db!!.rawQuery(selectQuery, null)
        if (cursor.moveToFirst()) {
            do {
                val id_livre = cursor.getInt(0)
                val nom_livre = cursor.getString(2)
                val id_testament = cursor.getInt(1)
                var id_order = cursor.getInt(3)
                val livres = Livres(id_livre, nom_livre, id_testament, id_order)
                arrayList.add(livres)
            } while (cursor.moveToNext())
        }
        cursor.close()
        return arrayList
    }

    fun getChapitreData(idlivres : Int): ArrayList<ChapitreEtVerse> {
        val arrayList = ArrayList<ChapitreEtVerse>()
        val selectQuery = "SELECT * FROM ci_diem_andininy WHERE a_bid = $idlivres GROUP By a_toko ORDER by a_order"
        val cursor: Cursor = db!!.rawQuery(selectQuery, null)
        if (cursor.moveToFirst()) {
            do {
                val id_chapitre_et_verse = cursor.getInt(0)
                val id_chapitre = cursor.getString(2)
                val id_verse = cursor.getInt(3)
                var id_livre = cursor.getInt(1)
                var text = cursor.getString(4)
                var id_order = cursor.getInt(6)
                val chapitre = ChapitreEtVerse(id_chapitre_et_verse, id_chapitre, id_verse, id_livre,text,id_order)
                arrayList.add(chapitre)
            } while (cursor.moveToNext())
        }
        cursor.close()
        return arrayList
    }

    fun getVerseData(idlivres : Int, idChapitre : String): ArrayList<ChapitreEtVerse> {
        val arrayList = ArrayList<ChapitreEtVerse>()
        val selectQuery = "SELECT * FROM ci_diem_andininy WHERE a_bid = $idlivres AND a_toko = $idChapitre ORDER by a_order"
        val cursor: Cursor = db!!.rawQuery(selectQuery, null)
        if (cursor.moveToFirst()) {
            do {
                val id_chapitre_et_verse = cursor.getInt(0)
                val id_chapitre = cursor.getString(2)
                val id_verse = cursor.getInt(3)
                var id_livre = cursor.getInt(1)
                var text = cursor.getString(4)
                var id_order = cursor.getInt(6)
                val chapitre = ChapitreEtVerse(id_chapitre_et_verse, id_chapitre, id_verse, id_livre,text,id_order)
                arrayList.add(chapitre)
            } while (cursor.moveToNext())
        }
        cursor.close()
        return arrayList
    }
}
