package cubex.mahesh.sqlitetest

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    lateinit var dBase:SQLiteDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        dBase = openOrCreateDatabase("emp_db",
                Context.MODE_PRIVATE,null)
        dBase.execSQL("create table if not exists employee(id number primary key,name varchar(100),desig varchar(100),dept varchar(100))")
// insert into table_name values(value1,'value2','value3','value4')
        insert.setOnClickListener({
if(et1.text.toString().length>0 && et2.text.toString().length>0 && et3.text.toString().length>0 && et4.text.toString().length>0) {
    // String table, String nullColumnHack, ContentValues values
    var cv = ContentValues()
    cv.put("id", et1.text.toString().toInt())
    cv.put("name", et2.text.toString())
    cv.put("desig", et3.text.toString())
    cv.put("dept", et4.text.toString())
    var status = dBase.insert("employee",
            null, cv)
    if (status == -1.toLong()) {
        Toast.makeText(this@MainActivity,
                "Fail to Insert", Toast.LENGTH_LONG).show()
    } else {
        Toast.makeText(this@MainActivity,
                "Record Inserted Successfully", Toast.LENGTH_LONG).show()
        et1.setText(""); et2.setText("")
        et3.setText(""); et4.setText("")
    }
}else{

}
        })    // Insert

        read.setOnClickListener({
  // select *from table_name
 /* String table, String[] columns,
 String selection, String[] selectionArgs,
 String groupBy, String having,
 String orderBy
  */
   var c:Cursor =  dBase.query("employee",
                  null,null,
                  null,null,
                  null,null)


        }) // read


    }   // on Create
}  // MainActivity
