package demo.com.xiongyantest01.activity;


import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;

import demo.com.xiongyantest01.ContentProvider.BookProvider;
import demo.com.xiongyantest01.R;
import demo.com.xiongyantest01.bean.ProviderBean.Book;
import demo.com.xiongyantest01.bean.ProviderBean.User;

/**
 * Created by xiongyan on 2017/8/23.
 */

public class ContentProviderActivity extends BaseActivity {
    public static final String CONTENT_PROVIDER_URI = "content://demo.com.xiongyantest01.ContentProvider";

    @Override
    protected int setLayoutId() {

        return R.layout.content_provider_activity;
    }

    @Override
    protected void loadData() {
//        getProvider();
        insertBookDb();
        insertUserDb();
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initIntent() {

    }

    private void getProvider() {
        Uri uri = Uri.parse(CONTENT_PROVIDER_URI);
        getContentResolver().query(uri, null, null, null, null);
    }

    private void insertBookDb() {
        ContentValues values = new ContentValues();
        values.put("_id", 6);
        values.put("name", "艺术家");
        getContentResolver().insert(BookProvider.BOOK_CONTENT_URI, values);

        Cursor bookCursor = getContentResolver().query(BookProvider.BOOK_CONTENT_URI, new String[]{"_id", "name"}, null, null, null);
        while (bookCursor.moveToNext()) {
            Book book = new Book();
            book.setId(bookCursor.getInt(0));
            book.setName(bookCursor.getString(1));
            System.out.println("-------------ID--------" + book.getId());
            System.out.println("-------------名字--------" + book.getName());
        }
        bookCursor.close();
    }

    private void insertUserDb() {
        Cursor userCursor = getContentResolver().query(BookProvider.USER_CONTENT_URI, new String[]{"_id", "name", "sex"}, null, null, null);
        while (userCursor.moveToNext()) {
            User user = new User();
            user.setId(userCursor.getInt(0));
            user.setName(userCursor.getString(1));
            user.setAge(userCursor.getInt(2));
            System.out.println("-------------userID--------" + user.getId());
            System.out.println("-------------user名字--------" + user.getName());
            System.out.println("-------------user性别--------" + user.getAge());
        }
        userCursor.close();
    }
}
