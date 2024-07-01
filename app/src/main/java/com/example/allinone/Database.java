package com.example.allinone;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class Database extends SQLiteOpenHelper {

    Context context;
    private static final String DATABASE_NAME = "allinone";
    private static final int DATABASE_VERSION = 9;

    // below variable is for our table name.
    private static final String TABLE_NAME_HEAT = "heatblastone";

    // below variable is for our id column.
    private static final String ID_COL_HEAT = "idheat";

    // below variable is for our heatblast title column
    private static final String TITLE_COL_HEAT = "titleheat";

    // below variable id for our heatblast description column.
    private static final String DESCRIPTION_COL_HEAT = "descriptionheat";

    /*********************************************/

    // below variable is for our table name.
    private static final String TABLE_NAME = "mycourses";

    // below variable is for our id column.
    private static final String ID_COL = "id";

    // below variable is for our course name column
    private static final String NAME_COL = "name";

    // below variable for our course description column.
    private static final String DESCRIPTION_COL = "description";


    /*****************************/

    // below variable is for our table name.
    private static final String TABLE_NAME_PAGE = "page";

    // below variable is for id , updateidcolumn.
    private static final String ID_COL_PAGE = "idpage";

    // below variable is for title , updatetitle column
    private static final String TITLE_COL_PAGE = "titlepage";

    // below variable is for description , updatedescription column.
    private static final String DESCRIPTION_COL_PAGE = "descriptionpage";

    //below variable is for currentdatetime , updatedatetimecolumn
    private static final String CURRENT_DATETIME_COL_PAGE = "currentdatetimepage";

    //below variable is for updatedatetime column
    private static final String UPDATE_DATETIME_COL_PAGE = "updatedatetimepage";
    /******************************************************************/

    /**/

            /*********************/

    private static final String TABLE_CLOCKWORK = "clockwork";

    private static final String CLOCKWORK_ID_COL = "clockworkid";
    private static final String CLOCKWORK_TITLE_COL = "clockworktitle";
    private static final String CLOCKWORK_DESCRIPTION_COL = "clockworkdescription";
    private static final String CLOCKWORK_DATE_COL = "clockworkdate";
    private static final String CLOCKWORK_TIME_COL = "clockworktime";
    private static final String CLOCKWORK_UID_COL = "clockworkuid";

    /******************************************************************/

    /******************************************************************/
    /**/

    private static final String TABLE_BLOXX = "bloxx";

    private static final String BLOXX_ID_COL = "bloxxid";
    private static final String BLOXX_TITLE_COL = "bloxxtitle";
    private static final String BLOXX_DESCRIPTION_COL = "bloxxdescription";
    private static final String BLOXX_CURRENT_DATETIME_COL = "bloxxcurrentdatetime";
    private static final String BLOXX_UPDATE_DATETIME_COL = "bloxxupdatedatetime";

    /******************************************************************/


    /******************************************************************/
    /**/

    private static final String TABLE_AMPFIBIAN = "ampfibian";

    private static final String AMPFIBIAN_ID_COL = "ampfibianid";
    private static final String AMPFIBIAN_TITLE_COL = "ampfibiantitle";
    private static final String AMPFIBIAN_DESCRIPTION_COL = "ampfibiandescription";
    private static final String AMPFIBIAN_CURRENT_DATETIME_COL = "ampfibiancurrentdatetime";
    private static final String AMPFIBIAN_UPDATE_DATETIME_COL = "ampfibianupdatedatetime";

    /******************************************************************/
    /**/

    private static final String TABLE_HEATBLAST = "heatblast";

    private static final String HEATBLAST_ID_COL = "heatblastid";
    private static final String HEATBLAST_TITLE_COL = "heatblasttitle";
    private static final String HEATBLAST_DESCRIPTION_COL = "heatblastdescription";
    private static final String HEATBLAST_CURRENT_DATETIME_COL = "heatblastcurrentdatetime";
    private static final String HEATBLAST_UPDATE_DATETIME_COL = "heatblastupdatedatetime";

    /******************************************************************/

    /******************************************************************/
    /**/

    private static final String TABLE_SECURED_NOTES = "securednotes";

    private static final String SECURED_NOTES_ID_COL = "securednotesid";
    private static final String SECURED_NOTES_TITLE_COL = "securednotestitle";
    private static final String SECURED_NOTES_DESCRIPTION_COL = "securednotesdescription";
    private static final String SECURED_NOTES_CURRENT_DATETIME_COL = "securednotescurrentdatetime";
    private static final String SECURED_NOTES_UPDATE_DATETIME_COL = "securednotesupdatedatetime";
    private static final String SECURED_NOTES_PASSWORD_COL = "securednotespassword";

    /******************************************************************/

    private static final String TEXT_TYPE = " TEXT";
    private static final String INTEGER_TYPE = " INTEGER";
    private static final String COMMA_SEP = ",";

    private static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + MemoryContract.MemoryEntry.TABLE_NAME + " (" +
                    MemoryContract.MemoryEntry._ID + INTEGER_TYPE + " PRIMARY KEY AUTOINCREMENT" + COMMA_SEP +
                    MemoryContract.MemoryEntry.COLUMN_TITLE + TEXT_TYPE + COMMA_SEP +
                    MemoryContract.MemoryEntry.COLUMN_IMAGE + TEXT_TYPE + " )";


    // creating a constructor for our database handler.
    public Database(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // below method is for creating a database by running a sqlite query
    @Override
    public void onCreate(SQLiteDatabase db) {

        String query100 = "CREATE TABLE " + TABLE_NAME + " ("
                + ID_COL + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + NAME_COL + " TEXT,"
                + DESCRIPTION_COL + " TEXT)";

        // at last we are calling a exec sql
        // method to execute above sql query
        db.execSQL(query100);

        db.execSQL(SQL_CREATE_ENTRIES);

        /*************************************************************/

        String query14 = "CREATE TABLE " + TABLE_SECURED_NOTES + " ("
                + SECURED_NOTES_ID_COL + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + SECURED_NOTES_TITLE_COL + " TEXT,"
                + SECURED_NOTES_DESCRIPTION_COL + " TEXT,"
                + SECURED_NOTES_CURRENT_DATETIME_COL + " TEXT,"
                + SECURED_NOTES_UPDATE_DATETIME_COL + " TEXT,"
                + SECURED_NOTES_PASSWORD_COL + " TEXT)";

        db.execSQL(query14);

        /*************************************************************/

        String query13 = "CREATE TABLE " + TABLE_CLOCKWORK + " ("
                + CLOCKWORK_ID_COL + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + CLOCKWORK_TITLE_COL + " TEXT,"
                + CLOCKWORK_DESCRIPTION_COL + " TEXT,"
                + CLOCKWORK_DATE_COL + " TEXT,"
                + CLOCKWORK_TIME_COL + " TEXT,"
                + CLOCKWORK_UID_COL + " INTEGER)";

        db.execSQL(query13);

        /*************************************************************/

        String query999 = "CREATE TABLE " + TABLE_NAME_HEAT + " ("
                + ID_COL_HEAT + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + TITLE_COL_HEAT + " TEXT,"
                + DESCRIPTION_COL_HEAT + " TEXT)";

        // at last we are calling a exec sql method to execute above sql query
        db.execSQL(query999);

        /*************************************************************/

        String query11 = "CREATE TABLE " + TABLE_BLOXX + " ("
                + BLOXX_ID_COL + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + BLOXX_TITLE_COL + " TEXT,"
                + BLOXX_DESCRIPTION_COL + " TEXT,"
                + BLOXX_CURRENT_DATETIME_COL + " TEXT,"
                + BLOXX_UPDATE_DATETIME_COL + "DATETIME DEFAULT CURRENT_TIMESTAMP)";

        db.execSQL(query11);

        /*************************************************************/


        String query101 = "CREATE TABLE " + TABLE_NAME_PAGE + " ("
                + ID_COL_PAGE + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + TITLE_COL_PAGE+ " TEXT,"
                + DESCRIPTION_COL_PAGE + " TEXT,"
                + UPDATE_DATETIME_COL_PAGE + " TEXT,"
                + CURRENT_DATETIME_COL_PAGE + "DATETIME DEFAULT CURRENT_TIMESTAMP)";

        // at last we are calling a exec sql method to execute above sql query
        db.execSQL(query101);

        /*************************************************************/

        String query9 = "CREATE TABLE " + TABLE_AMPFIBIAN + " ("
                + AMPFIBIAN_ID_COL + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + AMPFIBIAN_TITLE_COL + " TEXT,"
                + AMPFIBIAN_DESCRIPTION_COL + " TEXT,"
                + AMPFIBIAN_CURRENT_DATETIME_COL + " TEXT,"
                + AMPFIBIAN_UPDATE_DATETIME_COL + "DATETIME DEFAULT CURRENT_TIMESTAMP)";

        db.execSQL(query9);

        /*************************************************************/

        /*************************************************************/

        String query5 = "CREATE TABLE " + TABLE_HEATBLAST + " ("
                + HEATBLAST_ID_COL + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + HEATBLAST_TITLE_COL + " TEXT,"
                + HEATBLAST_DESCRIPTION_COL + " TEXT,"
                + HEATBLAST_CURRENT_DATETIME_COL + " TEXT,"
                + HEATBLAST_UPDATE_DATETIME_COL + "DATETIME DEFAULT CURRENT_TIMESTAMP)";

        db.execSQL(query5);

        /*************************************************************/
        /*************************************************************/

        String createTable = "CREATE TABLE " + TaskEntry.TABLE + " ( " +
                TaskEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                TaskEntry.COL_TASK_TITLE + " TEXT NOT NULL);";

        db.execSQL(createTable);
    }

    public class TaskEntry implements BaseColumns {
        public static final String TABLE = "tasks";

        public static final String COL_TASK_TITLE = "title";
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
//        // this method is called to check if the table exists already.

        db.execSQL("DROP TABLE IF EXISTS " + TaskEntry.TABLE);

        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CLOCKWORK);

        db.execSQL("DROP TABLE IF EXISTS " + TABLE_BLOXX);

        db.execSQL("DROP TABLE IF EXISTS " + TABLE_AMPFIBIAN);

        db.execSQL("DROP TABLE IF EXISTS " + TABLE_HEATBLAST);

        db.execSQL("DROP TABLE IF EXISTS " + TABLE_SECURED_NOTES);

        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);

        db.execSQL("DROP TABLE IF EXISTS " + MemoryContract.MemoryEntry.TABLE_NAME);

        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME_PAGE);

        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME_HEAT);

        onCreate(db);
    }

    /*****************************************************************/

    //operations for memories
    //add fake update also

    public Cursor readAllMemories() {
        SQLiteDatabase db = getReadableDatabase();

        return db.query(
                MemoryContract.MemoryEntry.TABLE_NAME,
                null,
                null,
                null,
                null,
                null,
                null
        );
    }

    public void addMemory(Memory memory) {
        SQLiteDatabase db = getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(MemoryContract.MemoryEntry.COLUMN_TITLE, memory.getTitle());
        values.put(MemoryContract.MemoryEntry.COLUMN_IMAGE, memory.getImageAsString());

        db.insert(MemoryContract.MemoryEntry.TABLE_NAME, null, values);
    }

    public void deleteSingleMemory(String title){
        SQLiteDatabase db = getWritableDatabase();
        db.delete(MemoryContract.MemoryEntry.TABLE_NAME, "title=?", new String[]{title});
        db.close();
    }

    public void deleteAllMemory(){
        SQLiteDatabase db = getWritableDatabase();
        String query = "DELETE FROM " + MemoryContract.MemoryEntry.TABLE_NAME;
        db.execSQL(query);
        db.close();
    }

    /*****************************************************************/

    public ArrayList<RModel> readAllHeatblast() {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_CLOCKWORK, null);

        ArrayList<RModel> heatblastList = new ArrayList<>();

        if (cursor.moveToFirst()) {
            do {
                heatblastList.add(new RModel(cursor.getString(0), cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getString(4), cursor.getInt(5)));
            } while (cursor.moveToNext());
        }
        cursor.close();
        return heatblastList;
    }

    public void addNewHeatblast(String title, String description, String date, String time, int uniid) {

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(CLOCKWORK_TITLE_COL, title);
        values.put(CLOCKWORK_DESCRIPTION_COL, description);
        values.put(CLOCKWORK_DATE_COL, date);
        values.put(CLOCKWORK_TIME_COL, time);
        values.put(CLOCKWORK_UID_COL, uniid);

        db.insert(TABLE_CLOCKWORK, null, values);

        db.close();

    }

    public void deleteSingleHeatblast(String id){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_CLOCKWORK, "clockworkid=?", new String[]{id});
        db.close();
    }

    public void deleteAllHeatblast(){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "DELETE FROM " + TABLE_CLOCKWORK;
        db.execSQL(query);
        db.close();
    }

    /************************************************************/
    // this method is use to add new course to our sqlite database.
    public void addNewCourse(String courseName,String courseDescription) {

        // on below line we are creating a variable for
        // our sqlite database and calling writable method
        // as we are writing data in our database.
        SQLiteDatabase db = this.getWritableDatabase();

        // on below line we are creating a
        // variable for content values.
        ContentValues values = new ContentValues();

        // on below line we are passing all values
        // along with its key and value pair.
        values.put(NAME_COL, courseName);
        values.put(DESCRIPTION_COL, courseDescription);

        // after adding all values we are passing
        // content values to our table.
        db.insert(TABLE_NAME, null, values);

        // at last we are closing our
        // database after adding database.
        db.close();
    }

    // we have created a new method for reading all the courses.
    public ArrayList<CourseModal> readCourses() {
        // on below line we are creating a
        // database for reading our database.
        SQLiteDatabase db = this.getReadableDatabase();

        // on below line we are creating a cursor with query to read data from database.
        Cursor cursorCourses = db.rawQuery("SELECT * FROM " + TABLE_NAME, null);

        // on below line we are creating a new array list.
        ArrayList<CourseModal> courseModalArrayList = new ArrayList<>();

        // moving our cursor to first position.
        if (cursorCourses.moveToFirst()) {
            do {
                // on below line we are adding the data from cursor to our array list.
                courseModalArrayList.add(new CourseModal(cursorCourses.getString(1),
                        cursorCourses.getString(2)));
            } while (cursorCourses.moveToNext());
            // moving our cursor to next.
        }
        // at last closing our cursor
        // and returning our array list.
        cursorCourses.close();
        return courseModalArrayList;
    }

    // below is the method for updating our courses
    public void updateCourse(String originalCourseName, String courseName, String courseDescription) {

        // calling a method to get writable database.
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        // on below line we are passing all values
        // along with its key and value pair.
        values.put(NAME_COL, courseName);
        values.put(DESCRIPTION_COL, courseDescription);

        // on below line we are calling a update method to update our database and passing our values.
        // and we are comparing it with name of our course which is stored in original name variable.
        db.update(TABLE_NAME, values, "name=?", new String[]{originalCourseName});
        db.close();
    }

    //below is the method to delete our course
    public void deleteCourse(String courseName){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_NAME, "name=?", new String[]{courseName});
        db.close();
    }






    /*********************************************************************************/
    // we have created a new method for reading all the heatblast.
    public ArrayList<BModel> HreadAllHeatblast() {
        // on below line we are creating a
        // database for reading our database.
        SQLiteDatabase db = this.getReadableDatabase();

        // on below line we are creating a cursor with query to read data from database.
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME_PAGE, null);

        // on below line we are creating a new array list.
        ArrayList<BModel> hList = new ArrayList<>();

        // moving our cursor to first position.
        if (cursor.moveToFirst()) {
            do {
                // on below line we are adding the data from cursor to our array list.
                //adding data in our arraylist
                hList.add(new BModel(cursor.getString(0), cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getString(4)));
            } while (cursor.moveToNext());
            // moving our cursor to next.
        }
        // at last closing our cursor
        // and returning our array list.
        cursor.close();
        return hList;
    }

    // this method is use to add new heatblast to our sqlite database.
    public void HaddNewHeatblast(String title, String description) {

        // on below line we are creating a variable for our sqlite database and calling writable method
        // as we are writing data in our database.
        SQLiteDatabase db = this.getWritableDatabase();

        // on below line we are creating a variable for content values.
        ContentValues values = new ContentValues();
        @SuppressLint("SimpleDateFormat") String date = new SimpleDateFormat("E LLLL yyyy HH:mm:ss aaa").format(new Date());
        // on below line we are passing all values along with its key and value pair.
        values.put(TITLE_COL_PAGE, title);
        values.put(DESCRIPTION_COL_PAGE, description);
        values.put(UPDATE_DATETIME_COL_PAGE, date);

        // after adding all values we are passing content values to our table.
        db.insert(TABLE_NAME_PAGE, null, values);

        // at last we are closing our database after adding database.
        db.close();
    }

    // below is the method for updating our heatblast
    public void HupdateHeatblast(String title, String description, String id) {

        // calling a method to get writable database.
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        @SuppressLint("SimpleDateFormat") String date = new SimpleDateFormat("E LLLL yyyy HH:mm:ss aaa").format(new Date());
        // on below line we are passing all values along with its key and value pair.
        values.put(TITLE_COL_PAGE, title);
        values.put(DESCRIPTION_COL_PAGE, description);
        values.put(UPDATE_DATETIME_COL_PAGE, date);

        // on below line we are calling a update method to update our database and passing our values.
        // and we are comparing it with id of our heatblast
        db.update(TABLE_NAME_PAGE, values, "idpage=?", new String[]{id});
        db.close();
    }

    //below is the method to delete our single heatblast
    public void HdeleteSingleHeatblast(String id){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_NAME_PAGE, "idpage=?", new String[]{id});
        db.close();
    }

    //below is the method to delete our all heatblast
    public void HdeleteAllHeatblast(){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "DELETE FROM " + TABLE_NAME_PAGE;
        db.execSQL(query);
        db.close();
    }

    // we have created a new method for reading all the heatblast.
    public ArrayList<Model> heatreadAllHeatblast() {
        // on below line we are creating a
        // database for reading our database.
        SQLiteDatabase db = this.getReadableDatabase();

        // on below line we are creating a cursor with query to read data from database.
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME_HEAT, null);

        // on below line we are creating a new array list.
        ArrayList<Model> heatList = new ArrayList<>();

        // moving our cursor to first position.
        if (cursor.moveToFirst()) {
            do {
                // on below line we are adding the data from cursor to our array list.
                //adding data in our arraylist
                heatList.add(new Model(cursor.getString(0), cursor.getString(1), cursor.getString(2)));
            } while (cursor.moveToNext());
            // moving our cursor to next.
        }
        // at last closing our cursor
        // and returning our array list.
        cursor.close();
        return heatList;
    }

    // this method is use to add new heatblast to our sqlite database.
    public void heataddNewHeatblast(String title, String description) {

        // on below line we are creating a variable for our sqlite database and calling writable method
        // as we are writing data in our database.
        SQLiteDatabase db = this.getWritableDatabase();

        // on below line we are creating a variable for content values.
        ContentValues values = new ContentValues();

        // on below line we are passing all values along with its key and value pair.
        values.put(TITLE_COL_HEAT, title);
        values.put(DESCRIPTION_COL_HEAT, description);

        // after adding all values we are passing content values to our table.
        db.insert(TABLE_NAME_HEAT, null, values);

        // at last we are closing our database after adding database.
        db.close();
    }

    // below is the method for updating our heatblast
    public void heatupdateHeatblast(String title, String description, String id) {

        // calling a method to get writable database.
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        // on below line we are passing all values along with its key and value pair.
        values.put(TITLE_COL_HEAT, title);
        values.put(DESCRIPTION_COL_HEAT, description);

        // on below line we are calling a update method to update our database and passing our values.
        // and we are comparing it with id of our heatblast
        db.update(TABLE_NAME_HEAT, values, "idheat=?", new String[]{id});
        db.close();
    }

    //below is the method to delete our single heatblast
    public void heatdeleteSingleHeatblast(String id){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_NAME_HEAT, "idheat=?", new String[]{id});
        db.close();
    }

    //below is the method to delete our all heatblast
    public void heatdeleteAllHeatblast(){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "DELETE FROM " + TABLE_NAME_HEAT;
        db.execSQL(query);
        db.close();
    }
}