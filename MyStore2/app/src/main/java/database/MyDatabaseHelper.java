package database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import models.Product;

public class MyDatabaseHelper extends SQLiteOpenHelper {

 private static final String TAG = "SQLite";


  // Phiên bản
  private static final int DATABASE_VERSION = 1;


  // Tên cơ sở dữ liệu.
  private static final String DATABASE_NAME = "MyApp";


  // Tên bảng: Note.
  private static final String TABLE_PRODUCT = "Product";

  private static final String COLUMN_PRODUCT_ID ="Id";
  private static final String COLUMN_PRODUCT_NAME ="Name";
  private static final String COLUMN_PRODUCT_PRICE = "Price";
  private static final String COLUMN_PRODUCT_PROMOTION_PRICE = "PromotionPrice";
  private static final String COLUMN_PRODUCT_AMOUNT = "Amount";
  private static final String COLUMN_PRODUCT_SALE_PERCENT = "SalePercent";
  private static final String COLUMN_PRODUCT_DESCRIPTION = "Description";

    public MyDatabaseHelper(Context context)  {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {

        // Script to create table.
        String script = "CREATE TABLE " + TABLE_PRODUCT + "("
                + COLUMN_PRODUCT_ID + " INTEGER PRIMARY KEY,"
                + COLUMN_PRODUCT_NAME + " TEXT,"
                + COLUMN_PRODUCT_PRICE + " DOUBLE,"
                + COLUMN_PRODUCT_PROMOTION_PRICE + " DOUBLE,"
                + COLUMN_PRODUCT_AMOUNT + " INTEGER,"
                + COLUMN_PRODUCT_SALE_PERCENT + " INTEGER,"
                + COLUMN_PRODUCT_DESCRIPTION + " TEXT" + ")";
        // Execute script.
        db.execSQL(script);
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        // Drop table
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PRODUCT);


        // Recreate
        onCreate(db);
        createDefaultNotesIfNeed();
    }
    public void createDefaultNotesIfNeed()  {
        int count = this.getProductsCount();
        if(count ==0 ) {
            Random rd = new Random();
            for (int i = 0; i < 10; i++){

               Product p =  new Product(
                            "Tivi " + (i + 1),
                            rd.nextInt(50)*200,
                            rd.nextInt(50)*200,
                            rd.nextInt(50)*200,
                            rd.nextInt(50)*200,
                            "Siêu phẩm đó mấy ba. mua lẹ đi còn kịp" + rd.nextInt(1)*200
                    );
                this.addProduct(p);
            }
        }
    }
    public void addProduct(Product model) {

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_PRODUCT_NAME, model.getName());
        values.put(COLUMN_PRODUCT_PRICE, model.getPrice());
        values.put(COLUMN_PRODUCT_PROMOTION_PRICE, model.getPromotionPrice());
        values.put(COLUMN_PRODUCT_AMOUNT, model.getAmount());
        values.put(COLUMN_PRODUCT_SALE_PERCENT, model.getSalePercent());
        values.put(COLUMN_PRODUCT_DESCRIPTION, model.getDescription());


        // Trèn một dòng dữ liệu vào bảng.
        db.insert(TABLE_PRODUCT, null, values);
        // Đóng kết nối database.
        db.close();
    }

    public int getProductsCount() {
        Log.i(TAG, "MyDatabaseHelper.getNotesCount ... " );

        String countQuery = "SELECT  * FROM " + TABLE_PRODUCT;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);

        int count = cursor.getCount();

        cursor.close();

        // return count
        return count;
    }

    public List<Product> getAllProduct() {
        Log.i(TAG, "MyDatabaseHelper.getAllNotes ... " );

        List<Product> noteList = new ArrayList<Product>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_PRODUCT;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);


        // Duyệt trên con trỏ, và thêm vào danh sách.
        if (cursor.moveToFirst()) {
            do {
                Product item = new Product();
                item.setId(Integer.parseInt(cursor.getString(0)));
                item.setName(cursor.getString(1));
                item.setPrice(Double.parseDouble(cursor.getString(2)));
                item.setPromotionPrice(Double.parseDouble(cursor.getString(3)));
                item.setAmount(Integer.parseInt(cursor.getString(4)));
                item.setSalePercent(Integer.parseInt(cursor.getString(5)));
                item.setDescription(cursor.getString(6));

                // Thêm vào danh sách.
                noteList.add(item);
            } while (cursor.moveToNext());
        }

        // return note list
        return noteList;
    }
}