package cubex.mahesh.sqlitetest

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.SimpleCursorAdapter
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
  /* var c:Cursor =  dBase.query("employee",
                 arrayOf("id","name","desig","dept"),"id=? and dept=?",
                  arrayOf(et1.text.toString(),et4.text.toString()),null,
                  null,null)*/
    /*        var c:Cursor =  dBase.query("employee",
                    null,null,
                    null,"name",
                    "id>125","id desc")*/
            var c:Cursor =  dBase.query("employee",
                    null,null,
                    null,null,
                    null,null)
            var list= mutableListOf<String>()
            while(c.moveToNext()){
                list.add("${c.getInt(0)} \t ${c.getString(1)} \t ${c.getString(2)} \t ${c.getString(3)}")
            }
            var my_adapter = ArrayAdapter<String>(this,
                    android.R.layout.simple_list_item_single_choice,list)
            lview.adapter  = my_adapter
        }) // read

        update.setOnClickListener({
 /* String table, ContentValues values,
            String whereClause, String[] whereArgs            */
            var cv = ContentValues()
           cv.put("name", et2.text.toString())
            cv.put("desig", et3.text.toString())

          var status:Int =  dBase.update("employee",cv,
                    "id=?", arrayOf(et1.text.toString()))
            if (status == 0) {
                Toast.makeText(this@MainActivity,
                        "Fail to Update", Toast.LENGTH_LONG).show()
            } else {
                Toast.makeText(this@MainActivity,
                        "Record Updated Successfully", Toast.LENGTH_LONG).show()
                et1.setText(""); et2.setText("")
                et3.setText(""); et4.setText("")
            }
        })

        delete.setOnClickListener({
  //String table, String whereClause, String[] whereArgs
       var status:Int = dBase.delete("employee","id=?",
                    arrayOf(et1.text.toString()))
            if (status == 0) {
                Toast.makeText(this@MainActivity,
                        "Fail to Delete", Toast.LENGTH_LONG).show()
            } else {
                Toast.makeText(this@MainActivity,
                        "Record Deleted Successfully", Toast.LENGTH_LONG).show()
                et1.setText(""); et2.setText("")
                et3.setText(""); et4.setText("")
            }
//            dBase.execSQL(
//   "delete from employee where id='${et1.text.toString()}'")

        })

        cadapter.setOnClickListener({

     var c =   dBase.query("employee",null,
             null,null,null,
             null,null)
//Context context, int layout, Cursor c, String[] from, int[] to, int flags
    var cAdapter = SimpleCursorAdapter(this@MainActivity,
            R.layout.indiview,c, arrayOf<String>("id","name","desig","dept"),
  intArrayOf(R.id.indi_id,R.id.indi_name,R.id.indi_desig,R.id.indi_dept),
            0)
    lview.adapter = cAdapter

        })

    }   // on Create
}  // MainActivity
