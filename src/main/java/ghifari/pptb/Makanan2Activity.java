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
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

public class Makanan2Activity extends AppCompatActivity {
    private DatabaseReference Menu, Makanan;
    private RecyclerView recycler2;
    FirebaseRecyclerAdapter<Blog2, BlogViewHolder2> adapter2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_makanan2);

        Menu = FirebaseDatabase.getInstance().getReference("Menu");
        Menu.keepSynced(false);

        Makanan = FirebaseDatabase.getInstance().getReference("Makanan");
        Makanan.keepSynced(false);

        recycler2 = (RecyclerView) findViewById((R.id.recycleview2));
        recycler2.setHasFixedSize(true);
        recycler2.setLayoutManager(new LinearLayoutManager(this));

        loadMakanan();
    }

    public void loadMakanan() {
        adapter2 = new FirebaseRecyclerAdapter<Blog2, BlogViewHolder2>
                (Blog2.class,
                        R.layout.recycle2,
                        BlogViewHolder2.class,
                        Makanan.orderByChild("Makananid").equalTo("Makanand")) {
            @Override
            protected void populateViewHolder(final BlogViewHolder2 viewHolder, Blog2 model, final int position2) {
                final String post_key = getRef(position2).getKey();

                viewHolder.setFoto(getApplicationContext(),(model.getFoto()));
                viewHolder.setNamamakanan(model.getNamamakanan());
                viewHolder.setJumlahkalori(model.getJumlahkalori());

                viewHolder.mView2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent AkhirActivity = new Intent(Makanan2Activity.this, AkhirActivity.class);
                        AkhirActivity.putExtra("blog_id", post_key);

                        startActivity(AkhirActivity);
                    }
                });


            }
        };
        recycler2.setAdapter(adapter2);
    }



    public static class BlogViewHolder2 extends RecyclerView.ViewHolder
    {
        View mView2;

        public  BlogViewHolder2(View itemView) {
            super(itemView);
            mView2 = itemView;
        }

        public void setFoto(Context ctx, String foto)
        {
            ImageView mFoto=(ImageView) mView2.findViewById(R.id.post_foto);
            Glide.with(ctx).load(foto).into(mFoto);
        }

        public void setNamamakanan(String nama)
        {
            TextView mMakanan=(TextView)mView2.findViewById(R.id.post_namamakanan);
            mMakanan.setText(nama);
        }

        public void setJumlahkalori(Long kalori)
        {
            TextView Jumlahkalori=(TextView)mView2.findViewById(R.id.post_jumlahkalori);
            Jumlahkalori.setText("Total kcal per 100 gram : "+kalori +" kcal");
        }
    }
}
