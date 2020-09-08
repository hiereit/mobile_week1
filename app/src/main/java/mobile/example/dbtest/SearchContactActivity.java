package mobile.example.dbtest;

import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class SearchContactActivity extends Activity {

	ContactDBHelper helper;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_search_contact);

		helper = new ContactDBHelper(this);
	}


	public void onClick(View v) {
		SQLiteDatabase db = helper.getReadableDatabase();

		String[] columns = {"_id", "name", "phone", "category"};
		String selection = "name=?";
		String[] selectArgs = new String[]{"컴퓨터"};
		Cursor cursor
				=	db.query ("contact_table", columns, selection, selectArgs,
				null, null, null, null);

//		Cursor cursor = db.rawQuery("SELECT _id, name, phone, category" +
//				" FROM contact_table WHERE name = '컴퓨터';", null);

		String result = "";
		while (cursor.moveToNext()) {
			int id = cursor.getInt(0);
			String name = cursor.getString(1);
			//cursor.getColumIndex("name");
			String phone = cursor.getString(2);
			String category = cursor.getString(3);
			result += category + ":" + name + "-" + phone + "\n";
			// 결과를 객체에 저장할 경우  DTO 필요
			// ContactDto item = new ContactDto();
			// item.set* 호출
			// list.add(item)
		}
		cursor.close();
		helper.close();
	}
}
