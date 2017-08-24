package demo.com.xiongyantest01.ContentProvider;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.Context;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import demo.com.xiongyantest01.SQLiteHelper.DbOpenHelper;


/**
 * Created by xiongyan on 2017/8/23.
 */

public class BookProvider extends ContentProvider {

    private String TAG = "BookProvider";
    public static final String AUTHORITY = "demo.com.xiongyantest01.ContentProvider";
    public static final Uri BOOK_CONTENT_URI = Uri.parse("content://" + AUTHORITY + "/book");
    public static final Uri USER_CONTENT_URI = Uri.parse("content://" + AUTHORITY + "/user");
    public static final int BOOK_URI_CODE = 0;
    public static final int UURI_URI_CODE = 1;
    private static final UriMatcher mUriMatcher = new UriMatcher(UriMatcher.NO_MATCH);

    static {
        mUriMatcher.addURI(AUTHORITY, "book", BOOK_URI_CODE);
        mUriMatcher.addURI(AUTHORITY, "user", UURI_URI_CODE);
    }

    private Context mContext;
    private SQLiteDatabase mDB;

    @Override
    public boolean onCreate() {
        mContext = getContext();
        initProviderData();
        return true;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection, @Nullable String selection, @Nullable String[] selectionArgs, @Nullable String sortOrder) {
        String tableName = getTableName(uri);
        if (tableName == null) {
            throw new IllegalArgumentException("Unsupported URL" + uri);
        }
        return mDB.query(tableName, projection, selection, selectionArgs, null, null, sortOrder, null);
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        return null;
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues values) {
        String tableName = getTableName(uri);
        if (tableName == null) {
            throw new IllegalArgumentException("Unsupported URL" + uri);
        }
        mDB.insert(tableName, null, values);
        mContext.getContentResolver().notifyChange(uri, null);
        return uri;
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String selection, @Nullable String[] selectionArgs) {
        String tableName = getTableName(uri);
        if (tableName == null) {
            throw new IllegalArgumentException("Unsupported URL" + uri);
        }
        int count = mDB.delete(tableName, selection, selectionArgs);
        if (count > 0) {
            mContext.getContentResolver().notifyChange(uri, null);
        }
        return count;
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues values, @Nullable String selection, @Nullable String[] selectionArgs) {
        String tableName = getTableName(uri);
        if (tableName == null) {
            throw new IllegalArgumentException("Unsupported URL" + uri);
        }
        int row = mDB.update(tableName, values, selection, selectionArgs);
        if (row > 0) {
            mContext.getContentResolver().notifyChange(uri, null);
        }
        return row;
    }


    private String getTableName(Uri uri) {
        String tableName = null;
        switch (mUriMatcher.match(uri)) {
            case BOOK_URI_CODE:
                tableName = DbOpenHelper.BOOK_TABLE_NAME;
                break;
            case UURI_URI_CODE:
                tableName = DbOpenHelper.USER_TABLE_NAME;
                break;
            default:
                break;
        }
        return tableName;

    }

    private void initProviderData() {
        mDB = new DbOpenHelper(mContext).getWritableDatabase();
        mDB.execSQL("delete from " + DbOpenHelper.BOOK_TABLE_NAME);
        mDB.execSQL("delete from " + DbOpenHelper.USER_TABLE_NAME);
        mDB.execSQL("insert into book values(3,'Android');");
        mDB.execSQL("insert into book values(4,'IOS');");
        mDB.execSQL("insert into book values(5,'HTML');");

        mDB.execSQL("insert into user values(1,'Jordan',1);");
        mDB.execSQL("insert into user values(2,'McGrady',0);");
    }
}
