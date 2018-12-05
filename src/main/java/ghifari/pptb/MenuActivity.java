package ghifari.pptb;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

public class MenuActivity extends AppCompatActivity {
    private DatabaseReference Menu, Penyakit, Makanan;
    private ImageView foto1, foto2, foto3, foto4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        if (Menu == null) {
            FirebaseDatabase database0 = FirebaseDatabase.getInstance();
            database0.setPersistenceEnabled(false);
            Menu = database0.getReference().child("Menu");
            Penyakit = database0.getReference().child("Penyakit");
            Makanan = database0.getReference().child("Makanan");
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        foto1 = (ImageView) findViewById(R.id.test1);
        foto2 = (ImageView) findViewById(R.id.test2);
        foto3 = (ImageView) findViewById(R.id.test3);
        foto4 = (ImageView) findViewById(R.id.test4);

        foto1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MenuActivity.this, PenyakitActivity.class);
                startActivity(intent);
            }
        });

        foto2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent2 = new Intent(MenuActivity.this, Makanan2Activity.class);
                startActivity(intent2);
            }
        });

        foto3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent3 = new Intent(MenuActivity.this, KaloriActivity.class);
                startActivity(intent3);
            }
        });

        foto4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent4 = new Intent(MenuActivity.this, DisplayActivity.class);
                startActivity(intent4);
            }
        });
    }
}