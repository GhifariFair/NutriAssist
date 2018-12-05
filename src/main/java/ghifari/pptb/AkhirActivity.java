package ghifari.pptb;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

public class AkhirActivity extends AppCompatActivity {
    private String mPost_key2 = null;
    private Long Kalori;
    private DatabaseReference Makanan;
    private Button butt;
    private ImageView foto;
    private TextView text;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_akhir);

        Makanan = FirebaseDatabase.getInstance().getReference("Makanan");
        Makanan.keepSynced(false);

        foto = (ImageView) findViewById(R.id.foto);
        butt = (Button) findViewById(R.id.btn);
        text = (TextView) findViewById(R.id.text);

        if (getIntent() != null) {
            mPost_key2 = getIntent().getExtras().getString("blog_id");
            Makanan.child(mPost_key2).addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    String mFoto = (String) dataSnapshot.child("Foto").getValue();
                    Long mKalori = (Long) dataSnapshot.child("Jumlahkalori") .getValue();

                    Glide.with(AkhirActivity.this).load(mFoto).into(foto);
                    Kalori = mKalori;
                    text.setText("Total kcal per 100 gram : "+Kalori+" kcal");
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {
                }
            });
        }

        butt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            Intent KaloriActivity = new Intent(AkhirActivity.this, KaloriActivity.class);
            KaloriActivity.putExtra("number1", String.valueOf(Kalori));

            startActivity(KaloriActivity);
            }
        });
    }
}
