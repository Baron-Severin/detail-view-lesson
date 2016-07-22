package ly.generalassemb.drewmahrt.cursoradapterdemo.setup;

import android.database.Cursor;

import ly.generalassemb.drewmahrt.cursoradapterdemo.ExampleSQLiteOpenHelper;

/**
 * Created by erikrudie on 7/22/16.
 */
public class GroceryItem {
    public int id;
    public String name;
    public String description;

    public GroceryItem(int id, String name, String desc) {
        this.id = id;
        this.name = name;
        this.description = desc;
    }

    public String toString() {
        return this.name;
    }
}
