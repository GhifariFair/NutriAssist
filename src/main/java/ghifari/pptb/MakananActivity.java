package ghifari.pptb;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

public class MakananActivity extends AppCompatActivity {
    private String mPost_key1 = null;
    private DatabaseReference Penyakit, Makanan;
    private RecyclerView recycler1;
    private TextView text;
    FirebaseRecyclerAdapter<Blog1, BlogViewHolder1> adapter1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_makanan);

        Penyakit = FirebaseDatabase.getInstance().getReference("Penyakit");
        Penyakit.keepSynced(false);

        Makanan = FirebaseDatabase.getInstance().getReference("Makanan");
        Makanan.keepSynced(false);

        recycler1 = (RecyclerView) findViewById((R.id.recycleview1));
        recycler1.setHasFixedSize(true);
        recycler1.setLayoutManager(new LinearLayoutManager(this));


        if (getIntent() != null) {
            mPost_key1 = getIntent().getExtras().getString("blog_id");
            loadMakanan(mPost_key1);
            text = (TextView) findViewById(R.id.editText);
            text.setText("Food list for " + mPost_key1 + " problem");
        }
    }


    private void loadMakanan(final String mPost_key1) {
        adapter1 = new FirebaseRecyclerAdapter<Blog1, BlogViewHolder1>
                (Blog1.class,
                        R.layout.recycle1,
                        BlogViewHolder1.class,
                        Makanan.orderByChild("Makananid").equalTo(mPost_key1)) {
            @Override
            protected void populateViewHolder(final BlogViewHolder1 viewHolder, Blog1 model, final int position1) {
                final String post_key = getRef(position1).getKey();

                viewHolder.setNamamakanan(model.getNamamakanan());
                viewHolder.setJumlahkalori(model.getJumlahkalori());
                viewHolder.setFoto(getApplicationContext(),(model.getFoto()));

                viewHolder.mView1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent AkhirActivity = new Intent(MakananActivity.this, AkhirActivity.class);
                        AkhirActivity.putExtra("blog_id", post_key);

                        startActivity(AkhirActivity);
                    }
                });


            }
        };
        recycler1.setAdapter(adapter1);
    }

    public static class BlogViewHolder1 extends RecyclerView.ViewHolder
    {
        View mView1;

        public  BlogViewHolder1(View itemView) {
            super(itemView);
            mView1 = itemView;
        }

        public void setNamamakanan(String namamakanan)
        {
            TextView mMakanan=(TextView)mView1.findViewById(R.id.post_namamakanan);
            mMakanan.setText(namamakanan);
        }

        public void setJumlahkalori(Long kalori)
        {
            TextView Jumlahkalori=(TextView)mView1.findViewById(R.id.post_jumlahkalori);
            Jumlahkalori.setText("Total kcal per 100 gram : "+kalori +" kcal");
        }

        public void setFoto(Context ctx, String foto)
        {
            ImageView mFoto=(ImageView) mView1.findViewById(R.id.post_foto);
            Glide.with(ctx).load(foto).into(mFoto);
        }
    }
}
