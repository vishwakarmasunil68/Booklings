package com.emobi.bjain.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.emobi.bjain.pojo.cart.CartResultPOJO;
import com.emobi.bjain.pojo.wishlist.WishListResultPOJO;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sunil on 04-05-2017.
 */

public class DatabaseHelper {

    DataBaseDef helper;

    public DatabaseHelper(Context context) {
        helper = new DataBaseDef(context);
    }

    public long insertWishlistData(WishListResultPOJO wishListResultPOJO){
        SQLiteDatabase db=helper.getWritableDatabase();
        ContentValues contentValues=new ContentValues();

        contentValues.put(DataBaseDef.entity_id, wishListResultPOJO.getEntity_id()+"");
        contentValues.put(DataBaseDef.wishlist_item_id, wishListResultPOJO.getWishlistItemId()+"");
        contentValues.put(DataBaseDef.wishlist_id, wishListResultPOJO.getWishlistId()+"");
        contentValues.put(DataBaseDef.description, wishListResultPOJO.getDescription()+"");
        contentValues.put(DataBaseDef.qty, wishListResultPOJO.getQty()+"");
        contentValues.put(DataBaseDef.name, wishListResultPOJO.getName()+"");
        contentValues.put(DataBaseDef.price, wishListResultPOJO.getPrice()+"");
        contentValues.put(DataBaseDef.short_description, wishListResultPOJO.getShortDescription()+"");
        contentValues.put(DataBaseDef.sku, wishListResultPOJO.getSku()+"");
        contentValues.put(DataBaseDef.small_image, wishListResultPOJO.getSmallImage()+"");
        contentValues.put(DataBaseDef.thumbnail, wishListResultPOJO.getThumbnail()+"");


        long id=db.insert(DataBaseDef.TABLE_WISHLIST, null, contentValues);
        db.close();
        return id;
    }

    public List<WishListResultPOJO> getAllWishListData(){
        SQLiteDatabase db=helper.getWritableDatabase();
        List<WishListResultPOJO> lst=new ArrayList<>();
        String[] columns={DataBaseDef.ID,
                DataBaseDef.entity_id,
                DataBaseDef.wishlist_item_id,
                DataBaseDef.wishlist_id,
                DataBaseDef.description,
                DataBaseDef.qty,
                DataBaseDef.name,
                DataBaseDef.price,
                DataBaseDef.short_description,
                DataBaseDef.sku,
                DataBaseDef.small_image,
                DataBaseDef.thumbnail
        };
        Cursor cursor=db.query(DataBaseDef.TABLE_WISHLIST, columns, null, null, null, null, null);

        while(cursor.moveToNext()){

            WishListResultPOJO newUrgentChatResultPOJO=
                    new WishListResultPOJO(cursor.getString(1),
                            cursor.getString(2),
                            cursor.getString(3),
                            cursor.getString(4),
                            cursor.getString(5),
                            cursor.getString(6),
                            cursor.getString(7),
                            cursor.getString(8),
                            cursor.getString(9),
                            cursor.getString(10),
                            cursor.getString(11));



            lst.add(newUrgentChatResultPOJO);
        }
        cursor.close();
        db.close();
        return lst;

    }
    public WishListResultPOJO getWishListData(String entity_id){
        SQLiteDatabase db=helper.getWritableDatabase();
//        List<WishListResultPOJO> lst=new ArrayList<>();
        String[] columns={DataBaseDef.ID,
                DataBaseDef.entity_id,
                DataBaseDef.wishlist_item_id,
                DataBaseDef.wishlist_id,
                DataBaseDef.description,
                DataBaseDef.qty,
                DataBaseDef.name,
                DataBaseDef.price,
                DataBaseDef.short_description,
                DataBaseDef.sku,
                DataBaseDef.small_image,
                DataBaseDef.thumbnail
        };
//        Cursor cursor=db.query(DataBaseHelper.OBSTACLE_TABLE, columns, null, null, null, null, null);
        Cursor cursor=db.query(DataBaseDef.TABLE_WISHLIST, columns, DataBaseDef.entity_id+" = "+entity_id, null, null, null, null);
        if(cursor.moveToNext()){

            WishListResultPOJO wishListResultPOJO=
                    new WishListResultPOJO(cursor.getString(1),
                            cursor.getString(2),
                            cursor.getString(3),
                            cursor.getString(4),
                            cursor.getString(5),
                            cursor.getString(6),
                            cursor.getString(7),
                            cursor.getString(8),
                            cursor.getString(9),
                            cursor.getString(10),
                            cursor.getString(11));
//            lst.add(newUrgentChatResultPOJO);
            return wishListResultPOJO;
        }
        cursor.close();
        db.close();
        return null;
    }

    public int UpdateStoreImagePath(WishListResultPOJO wishListResultPOJO){
        //UPDATE TABLE SET Name='vav' where Name=?
        SQLiteDatabase db=helper.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put(DataBaseDef.entity_id, wishListResultPOJO.getEntity_id()+"");
        contentValues.put(DataBaseDef.wishlist_item_id, wishListResultPOJO.getWishlistItemId()+"");
        contentValues.put(DataBaseDef.wishlist_id, wishListResultPOJO.getWishlistId()+"");
        contentValues.put(DataBaseDef.description, wishListResultPOJO.getDescription()+"");
        contentValues.put(DataBaseDef.qty, wishListResultPOJO.getQty()+"");
        contentValues.put(DataBaseDef.name, wishListResultPOJO.getName()+"");
        contentValues.put(DataBaseDef.price, wishListResultPOJO.getPrice()+"");
        contentValues.put(DataBaseDef.short_description, wishListResultPOJO.getShortDescription()+"");
        contentValues.put(DataBaseDef.sku, wishListResultPOJO.getSku()+"");
        contentValues.put(DataBaseDef.small_image, wishListResultPOJO.getSmallImage()+"");
        contentValues.put(DataBaseDef.thumbnail, wishListResultPOJO.getThumbnail()+"");
        String[] whereArgs={wishListResultPOJO.getWishlistId()};
        int count=db.update(DataBaseDef.TABLE_WISHLIST,contentValues, DataBaseDef.wishlist_id+" =? ",whereArgs);

        db.close();
        return count;
    }

    public int deleteWishListItem(String wishlist_item_id){
        SQLiteDatabase db=helper.getWritableDatabase();
        String[] whereArgs={wishlist_item_id};
        int count=db.delete(DataBaseDef.TABLE_WISHLIST, wishlist_item_id+"=?", whereArgs);
        db.close();
        return count;
    }
    public void deleteAllWishListData(){
        SQLiteDatabase db=helper.getWritableDatabase();
        db.execSQL("delete from "+ DataBaseDef.TABLE_WISHLIST);
        db.close();
    }

    /*------------------------------------------------------------------------------------
    * ------------------------------------------------------------------------------------
    * ----------------------------------------------------------------------------------*/
    private final String TAG=getClass().getSimpleName();

    public long insertCartData(CartResultPOJO catCartResultPOJO){

        SQLiteDatabase db=helper.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        Log.d(TAG,"cart in database:-"+catCartResultPOJO.toString());
        Log.d(TAG,"cart in database:-"+catCartResultPOJO.getCart_id());
        Log.d(TAG,"cart in database:-"+catCartResultPOJO.getProduct_id());
        contentValues.put(DataBaseDef.cart_cart_id, catCartResultPOJO.getCart_id()+"");
        contentValues.put(DataBaseDef.cart_product_id, catCartResultPOJO.getProduct_id()+"");
        contentValues.put(DataBaseDef.cart_name, catCartResultPOJO.getName()+"");
        contentValues.put(DataBaseDef.cart_price, catCartResultPOJO.getPrice()+"");
        contentValues.put(DataBaseDef.cart_image, catCartResultPOJO.getImage()+"");
        contentValues.put(DataBaseDef.cart_sku, catCartResultPOJO.getSku()+"");
        contentValues.put(DataBaseDef.cart_qty, catCartResultPOJO.getQty()+"");
        contentValues.put(DataBaseDef.cart_subtotal, catCartResultPOJO.getSubtotal()+"");

        long id=db.insert(DataBaseDef.TABLE_CART, null, contentValues);
        Log.d(TAG,"insert cart items:-"+id);
        db.close();
        return id;
    }

    public List<CartResultPOJO> getAllCartData(){
        SQLiteDatabase db=helper.getWritableDatabase();
        List<CartResultPOJO> lst=new ArrayList<>();
        String[] columns={DataBaseDef.ID,
                DataBaseDef.cart_cart_id,
                DataBaseDef.cart_product_id,
                DataBaseDef.cart_name,
                DataBaseDef.cart_price,
                DataBaseDef.cart_image,
                DataBaseDef.cart_sku,
                DataBaseDef.cart_qty,
                DataBaseDef.cart_subtotal
        };
        Cursor cursor=db.query(DataBaseDef.TABLE_CART, columns, null, null, null, null, null);

        while(cursor.moveToNext()){
            CartResultPOJO cartResultPOJO=
                    new CartResultPOJO(cursor.getString(2),
                            cursor.getString(1),
                            cursor.getString(3),
                            cursor.getString(4),
                            cursor.getString(5),
                            cursor.getString(6),
                            cursor.getString(7),
                            cursor.getString(8));



            lst.add(cartResultPOJO);
        }
        cursor.close();
        db.close();
        return lst;

    }
    public CartResultPOJO getCartData(String product_id){
        SQLiteDatabase db=helper.getWritableDatabase();
//        List<WishListResultPOJO> lst=new ArrayList<>();
        String[] columns={DataBaseDef.ID,
                DataBaseDef.cart_cart_id,
                DataBaseDef.cart_product_id,
                DataBaseDef.cart_name,
                DataBaseDef.cart_price,
                DataBaseDef.cart_image,
                DataBaseDef.cart_sku,
                DataBaseDef.cart_qty,
                DataBaseDef.cart_subtotal
        };
//        Cursor cursor=db.query(DataBaseHelper.OBSTACLE_TABLE, columns, null, null, null, null, null);
        Cursor cursor=db.query(DataBaseDef.TABLE_CART, columns, DataBaseDef.cart_product_id+" = "+product_id, null, null, null, null);
        if(cursor.moveToNext()){

            CartResultPOJO cartResultPOJO=
                    new CartResultPOJO(cursor.getString(2),
                            cursor.getString(1),
                            cursor.getString(3),
                            cursor.getString(4),
                            cursor.getString(5),
                            cursor.getString(6),
                            cursor.getString(7),
                            cursor.getString(8));
//            lst.add(newUrgentChatResultPOJO);
            return cartResultPOJO;
        }
        cursor.close();
        db.close();
        return null;
    }

    public int UpdateCartData(CartResultPOJO catCartResultPOJO){
        //UPDATE TABLE SET Name='vav' where Name=?
        SQLiteDatabase db=helper.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put(DataBaseDef.cart_cart_id, catCartResultPOJO.getCart_id()+"");
        contentValues.put(DataBaseDef.cart_product_id, catCartResultPOJO.getProduct_id()+"");
        contentValues.put(DataBaseDef.cart_name, catCartResultPOJO.getName()+"");
        contentValues.put(DataBaseDef.cart_price, catCartResultPOJO.getPrice()+"");
        contentValues.put(DataBaseDef.cart_image, catCartResultPOJO.getImage()+"");
        contentValues.put(DataBaseDef.cart_sku, catCartResultPOJO.getSku()+"");
        contentValues.put(DataBaseDef.cart_qty, catCartResultPOJO.getQty()+"");
        contentValues.put(DataBaseDef.cart_subtotal, catCartResultPOJO.getSubtotal()+"");


        String[] whereArgs={catCartResultPOJO.getCart_id()};
        int count=db.update(DataBaseDef.TABLE_CART,contentValues, DataBaseDef.cart_cart_id+" =? ",whereArgs);

        db.close();
        return count;
    }

    public int deleteCartData(String cart_id){
        SQLiteDatabase db=helper.getWritableDatabase();
        String[] whereArgs={cart_id};
        int count=db.delete(DataBaseDef.TABLE_CART, DataBaseDef.cart_cart_id+"=?", whereArgs);
        db.close();
        Log.d(TAG,"delete cart item:-"+count);
        return count;
    }
    public void deleteAllCartItems(){
        SQLiteDatabase db=helper.getWritableDatabase();
        db.execSQL("delete from "+ DataBaseDef.TABLE_CART);
        db.close();
    }



    static class DataBaseDef extends SQLiteOpenHelper {
        private static final String DATABASE_NAME = "booklingdb";

        //table names
        private static final String TABLE_WISHLIST = "wishlisttable";
        private static final String TABLE_CART = "carttable";


        private static final int DATABASE_VERSION = 2;

        //columns for the ItemData
        private static final String ID = "_id";
        private static final String entity_id = "entity_id";
        private static final String wishlist_item_id = "wishlist_item_id";
        private static final String wishlist_id = "wishlist_id";
        private static final String description = "description";
        private static final String qty = "qty";
        private static final String name = "name";
        private static final String price = "price";
        private static final String short_description = "short_description";
        private static final String sku = "sku";
        private static final String small_image = "small_image";
        private static final String thumbnail = "thumbnail";


        private static final String cart_cart_id = "cart_id";
        private static final String cart_product_id = "product_id";
        private static final String cart_name= "name";
        private static final String cart_price = "price";
        private static final String cart_image = "image";
        private static final String cart_sku = "sku";
        private static final String cart_qty = "qty";
        private static final String cart_subtotal = "subtotal";


        private static final String CREATE_WISHLIST_TABLE = "CREATE TABLE " + TABLE_WISHLIST + " (" + ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + entity_id + " VARCHAR(255), "
                + wishlist_item_id + " VARCHAR(255), "
                + wishlist_id + " varchar(255), "
                + description + " TEXT, "
                + qty + " VARCHAR(255), "
                + name + " VARCHAR(255), "
                + price + " VARCHAR(255), "
                + short_description + " VARCHAR(255),  "
                + sku + " VARCHAR(255),  "
                + small_image + " VARCHAR(255),  "
                + thumbnail + " VARCHAR(255) "
                + ");";

        private static final String CREATE_CART_TABLE = "CREATE TABLE " + TABLE_CART + " (" + ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + cart_cart_id + " VARCHAR(255), "
                + cart_product_id + " VARCHAR(255), "
                + cart_name + " varchar(255), "
                + cart_price + " TEXT, "
                + cart_image + " VARCHAR(255), "
                + cart_sku + " VARCHAR(255), "
                + cart_qty + " VARCHAR(255), "
                + cart_subtotal + " VARCHAR(255) "
                + ");";

        private static final String DROP_WISHLIST_TABLE = "DROP TABLE IF EXISTS " + TABLE_WISHLIST;
        private static final String DROP_CART_TABLE = "DROP TABLE IF EXISTS " + TABLE_CART;


        private Context context;


        public DataBaseDef(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
            // TODO Auto-generated constructor stub
            this.context = context;
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            // TODO Auto-generated method stub
            try {

                db.execSQL(CREATE_WISHLIST_TABLE);
                db.execSQL(CREATE_CART_TABLE);

//                Toast.makeText(context, "database called", Toast.LENGTH_SHORT).show();
                Log.d("sunil", "database called");
            } catch (Exception e) {
//                Toast.makeText(context, e.toString(), Toast.LENGTH_SHORT).show();
                Log.d("sunil", e.toString());
            }
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            // TODO Auto-generated method stub
            try {
                db.execSQL(DROP_WISHLIST_TABLE);
                db.execSQL(DROP_CART_TABLE);

//                Toast.makeText(context, "database droped", Toast.LENGTH_SHORT).show();
                Log.d("sunil", "database droped");
                onCreate(db);
            } catch (Exception e) {
//                Toast.makeText(context, e.toString(), Toast.LENGTH_SHORT).show();
                Log.d("sunil", e.toString());
            }
        }
    }
}
