package fr.be2.gsb;

import android.content.ContentValues;
import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class BDDHelper extends SQLiteOpenHelper {
    public static final String DB_NAME = "GSB.db";
    public static final String DB_TABLE = "FRAIS";

//Definition des champs de la base
    public static final String ID = "ID"; // Identifiant
    public static final String TYPEFORFAIT = "TYPEFORFAIT"; // Le type de forfiat : Nuité / Resto etc..
    public static final String QUANTITE = "QUANTITE";
    public static final String DATEFRAIS = "DATEFRAIS";
    public static final String MONTANT = "MONTANT";
    public static final String DATESAISIE = "DATESASIE";
    public static final String LIBELLE = "LIBELLE";
    public static final String CREATE = "Create table FRAIS (ID INTEGER PRIMARY KEY AUTOINCREMENT, TYPEFORFAIT TEXT, QUANTITE INTEGER,DATEFRAIS TEXT, MONTANT REAL, LIBELLE TEXT,DATESAISIE DATETIME DEFAULT CURRENT TIMESTAMP)" ;


    public BDDHelper(Context context) {

        super(context, DB_NAME, null, 1);//permet d'acceder aux membres de la classe mère

    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(CREATE);


    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public BDDHelper open() throws SQLException {

        SQLiteDatabase db =this.getWritableDatabase();

        return this;

    }
    public boolean insertData(String type, Integer quantite, String date, double montant, String libelle) {
        //on cree une variable de type sqLitedatabase pr pouvoir y acceder
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(TYPE, type);
        contentValues.put(QUANTITE, quantite);
        contentValues.put(DATE, date);
        contentValues.put(MONTANT, montant);
        contentValues.put(LIBELLE, libelle);
        //insert sert a inserer des donnees, elle insere ds notre table contentValue les contenus
        // des variables que l'utilisateur renseigne
        long result = db.insert(DB_TABLE, null, contentValues);
        return result != -1;
    }


}
