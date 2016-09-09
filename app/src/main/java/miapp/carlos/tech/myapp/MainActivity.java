package miapp.carlos.tech.myapp;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

//import com.firebase.client.Firebase;
public class MainActivity extends AppCompatActivity {
   // Firebase myFirebaseRef = new Firebase("https://mi-app-5c24c.firebaseio.com/");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }


    public void modificar(View view){
        TextView recibeText=(TextView)findViewById(R.id.recibeText);
        EditText enviaText=(EditText)findViewById(R.id.enviaText);
        //  myFirebaseRef.child("mensaje").setValue("Do you have data? You'll love Firebase.");
        DatabaseReference ref= FirebaseDatabase.getInstance().getReference();
        DatabaseReference mensajeRef=ref.child("mensaje");
        mensajeRef.setValue(enviaText.getText().toString());

    }

    public void recibe(View view){
        final TextView recibeText=(TextView)findViewById(R.id.recibeText);
         //  myFirebaseRef.child("mensaje").setValue("Do you have data? You'll love Firebase.");
        DatabaseReference ref= FirebaseDatabase.getInstance().getReference();
        DatabaseReference mensajeRef=ref.child("mensaje");
        mensajeRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String value=dataSnapshot.getValue(String.class);
                recibeText.setText(value);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });




    }




    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
