package com.project_prm392;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.project_prm392.Activity.LoginTabFragment;
import com.project_prm392.entity.Category;
import com.project_prm392.entity.Item;
import com.project_prm392.entity.User;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;

public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String databaseName = "Project.db";

    public DatabaseHelper(Context context) {
        super( context, "Project.db", null, 1);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("Create table User(id INTEGER PRIMARY KEY AUTOINCREMENT, email text, username text, " +
                "phone text, address text, password text, role integer)");
        db.execSQL(" Create table Category(caId INTEGER PRIMARY KEY AUTOINCREMENT, caName text, caImageUrl text)");
        db.execSQL("Insert into Category values(1,'Burgers', 'https://media.istockphoto.com/id/1184633031/vi/vec-to/phim-ho%E1%BA%A1t-h%C3%ACnh-burger-vector-c%C3%B4-l%E1%BA%ADp-minh-h%E1%BB%8Da.jpg?s=170667a&w=0&k=20&c=jYBNRAhYaURWgts2H8-jyPDV8t_uyLnjHPR5onOl9EE=');");
        db.execSQL("Insert into Category values(2,'Pizza', 'https://static.vecteezy.com/system/resources/previews/003/087/901/original/realistic-pizza-with-pepperoni-and-different-types-of-sauces-vector.jpg')");
        db.execSQL("Insert into Category values(3,'Fried Chicken', 'https://us.123rf.com/450wm/stockee/stockee2305/stockee230509794/205356704-this-is-a-2d-vector-image-of-a-fried-chicken-on-a-white-background-the-image-has-no-gradients-and.jpg?ver=6')");
        db.execSQL("Insert into Category values(4,'Noodles', 'https://thumbs.dreamstime.com/b/japanese-ramen-soup-chopsticks-flat-icon-noodle-boiled-egg-pork-meat-traditional-national-food-vector-illustration-101288697.jpg')");
        db.execSQL("Insert into Category values(5,'Drinks', 'https://icons.veryicon.com/png/o/food--drinks/delicious-food-1/orange-soda.png')");
        db.execSQL("Insert into Category values(6,'Ice Cream and Desserts', 'https://img.freepik.com/premium-vector/cute-cartoon-ice-creams-ice-cream-dessert-icon_316493-1365.jpg?w=2000')");


        db.execSQL(" Create table Item(itemId INTEGER PRIMARY KEY AUTOINCREMENT, categoryId integer, itemName text, itemUrl text" +
                ", price integer, categories integer, description text, FOREIGN KEY (categoryId) REFERENCES Category(categoryId) ) ");
        db.execSQL("Insert into Item values(1, 5,'BlackBerry Lemonade', 'https://digitaleat.kfc.com/menus/image/bare/kfc-BlackberryLemonade?q=75&w=1280'" +
                ", 5, 200, 'This ice cold lemonade delivers the perfect balance of sweet and tart flavors in every sip by combining the refreshing taste of our famous Colonels Lemonade with a splash of crisp blackberry puree. ');");
        db.execSQL("Insert into Item values(2, 5,'Diet Pepsi', 'https://digitaleat.kfc.com/menus/image/bare/kfc-dietPepsi?q=75&w=1280'" +
                ", 6, 0, 'A crisp tasting, refreshing pop of sweet, fizzy bubbles without calories.');");
        db.execSQL("Insert into Item values(3, 5,'Starry', 'https://digitaleat.kfc.com/menus/image/bare/kfc-Starry?q=75&w=1280'" +
                ", 2, 80, 'Caffeine-free soda that provides a crisp, clear burst of lemon lime flavor.');");
        db.execSQL("Insert into Item values(4, 5,'Mountain Dew', 'https://digitaleat.kfc.com/menus/image/bare/kfc-mtndewsweetlightning?q=75&w=1280'" +
                ", 11, 190, 'Sweet Lightning blends the southern flavors of sweet peach and smooth honey to create a refreshing taste that wonderfully complements our crispy fried chicken.');");
        db.execSQL("Insert into Item values(5, 5,'Lemonade', 'https://digitaleat.kfc.com/menus/image/bare/kfc-LemMed?q=75&w=1280'" +
                ", 10, 50, 'Find the perfect match for your KFC® meal or combo with our refreshing, ice cold Classic Lemonade.');");
        db.execSQL("Insert into Item values(6, 5,'Sweet Tea', 'https://digitaleat.kfc.com/menus/image/bare/kfc-sweetTea?q=75&w=1280'" +
                ", 9, 100, 'Complete your perfect fried chicken combo meal with the delicious taste of our refreshing KFC® Sweet Tea!');");
        db.execSQL("Insert into Item values(7, 5,'Dr.pepper', 'https://digitaleat.kfc.com/menus/image/bare/kfc-drPepper?q=75&w=1280'" +
                ", 6, 230, 'Pair the bold, refreshing flavor of Dr. Pepper® with your next fried chicken meal, bucket or combo!');");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists User");
        db.execSQL("drop table if exists Category");
        db.execSQL("drop table if exists Item");

    }

    public Boolean insertUser(User user){
        SQLiteDatabase db  = this.getWritableDatabase();
        ContentValues content = new ContentValues();
        content.put("email", user.getEmail());
        content.put("username", user.getUsername() );
        content.put("phone", user.getPhone());
        content.put("address", user.getAddress());
        content.put("password", user.getPassword());
        content.put("role", 1);
        long result = db.insert("User", null, content );
        if(result == -1){
            return false;
        } else{
            return true;
        }
    }

    public Boolean checkEmail(String email){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from User where email = ? ", new String[]{email});
        if(cursor.getCount() > 0){
            return true;
        }else{
            return  false;
        }
    }

    public User getUserByEmail(String emailX){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from User where email = ? ", new String[]{emailX});
        User user = null;
        if(cursor.getCount() > 0){
            cursor.moveToFirst();
            int id = cursor.getInt(0);
            String email= cursor.getString(1);
            String name = cursor.getString(2);
            String phone = cursor.getString(3);
            String address = cursor.getString(4);
            String password = cursor.getString(5);
            int role = cursor.getInt(6);
            user = new User(id, email, name, phone, address, password, role);
        }
        cursor.close();
        return user;
    }

    public Boolean checkAccountForLogin(String email, String password){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("Select * from User where email = ? and password = ?", new String[]{email, password});
        if(cursor.getCount() > 0){
            return true;
        }else{
            return  false;
        }
    }

    public Boolean editProfile(User user) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("id", user.getId());
        values.put("username", user.getUsername());
        values.put("phone", user.getPhone());
        values.put("address", user.getAddress());
        values.put("email", user.getEmail());
        values.put("password", user.getPassword());
        values.put("role", user.getRole());
        long result = db.update("User", values, "id" + " = ?", new String[] { String.valueOf(user.getId()) });
        if(result == -1){
            return false;
        } else{
            return true;
        }
    }

    public Boolean changePw(String password, int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("password", password);
        long result = db.update("User", values, "id" + " = ?", new String[] { String.valueOf(id) });
        if(result == -1){
            return false;
        } else{
            return true;
        }
    }

    public ArrayList<Category> getAllCategories() {
        ArrayList<Category> listCategory = new ArrayList<>();
        String query = "SELECT * FROM Category" ;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        cursor.moveToFirst();

        while(cursor.isAfterLast() == false) {
            Category category = new Category(cursor.getInt(0), cursor.getString(1), cursor.getString(2));
            listCategory.add(category);
            cursor.moveToNext();
        }
        cursor.close();
        return listCategory;
    }

    public ArrayList<Item> getListItemByCategory(int categoryId){
        ArrayList<Item> listItem = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from Item where categoryId = ? ", new String[]{String.valueOf(categoryId)});
        cursor.moveToFirst();
        while(cursor.isAfterLast() == false) {
            Item item = new Item(cursor.getInt(0), cursor.getInt(1), cursor.getString(2), cursor.getString(3), cursor.getInt(4), cursor.getInt(5),  cursor.getString(6));
            listItem.add(item);
            cursor.moveToNext();
        }
        cursor.close();
        return listItem;
    }

}
