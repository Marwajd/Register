package com.example.register;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "annonces.db";
    private static final int DATABASE_VERSION = 1;
    private final Context context;
    private DBHelper dbHelper;


    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
        dbHelper = new DBHelper(context);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // Création de la table d'annonces
        String createTableSQL = "CREATE TABLE annonces (" +
                "_id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "titre TEXT," +
                "categorie TEXT," +
                "secteur TEXT," +
                "type_contrat TEXT," +
                "description TEXT," +
                "ville TEXT)";
        db.execSQL(createTableSQL);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Cette méthode est appelée lorsque la version de la base de données change
        db.execSQL("DROP TABLE IF EXISTS annonces");
        onCreate(db);
    }

    public int getAnnounceCountForCity(String ville) {
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT COUNT(*) FROM annonces WHERE ville=?";
        Cursor cursor = db.rawQuery(query, new String[]{ville});
        int count = 0;
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                count = cursor.getInt(0);
            }
            cursor.close();
        }
        return count;
    }


    public long insertAnnonce(String titre, String categorie, String secteur, String typeContrat, String description, String ville) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("titre", titre);
        values.put("categorie", categorie);
        values.put("secteur", secteur);
        values.put("type_contrat", typeContrat);
        values.put("description", description);
        values.put("ville", ville);
        long result = db.insertOrThrow("annonces", null, values);
        db.close();
        return result;
    }

    public int updateAnnonce(int id, String titre, String categorie, String secteur, String typeContrat, String description, String ville) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("titre", titre);
        values.put("categorie", categorie);
        values.put("secteur", secteur);
        values.put("type_contrat", typeContrat);
        values.put("description", description);
        values.put("ville", ville);
        int result = db.update("annonces", values, "id=?", new String[]{String.valueOf(id)});
        db.close();
        return result;
    }

    public int deleteAnnonce(int id) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        int result = db.delete("annonces", "id=?", new String[]{String.valueOf(id)});
        db.close();
        return result;
    }

    public Cursor getAllAnnonces() {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        return db.query("annonces", null, null, null, null, null, null);
    }

    public Cursor getAnnoncesByVille(String ville) {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        return db.query("annonces", null, "ville=?", new String[]{ville}, null, null, null);
    }
}


