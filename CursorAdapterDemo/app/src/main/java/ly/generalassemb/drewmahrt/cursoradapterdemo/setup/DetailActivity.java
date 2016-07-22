package ly.generalassemb.drewmahrt.cursoradapterdemo.setup;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import ly.generalassemb.drewmahrt.cursoradapterdemo.ExampleSQLiteOpenHelper;
import ly.generalassemb.drewmahrt.cursoradapterdemo.R;

public class DetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        Intent intent = getIntent();
        int id = intent.getIntExtra("id", 0);

        TextView idText = (TextView) findViewById(R.id.showId);
        TextView detailText = (TextView) findViewById(R.id.detailTextView);

        idText.setText("id: " + id);

        ExampleSQLiteOpenHelper db = ExampleSQLiteOpenHelper.getInstance(getBaseContext());
//        Cursor cursor = db.getDescription(id);
//
//        cursor.moveToFirst();
//        String description = cursor.getString(0);
        String description = db.getDescription(id);

        detailText.setText(description);

    }
}
