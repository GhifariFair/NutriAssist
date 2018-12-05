package ghifari.pptb;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

public class PenyakitActivity extends AppCompatActivity {
    private DatabaseReference Menu, Penyakit;
    private RecyclerView recycler0;
    FirebaseRecyclerAdapter<Blog0, BlogViewHolder0> adapter0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_penyakit);

        Menu = FirebaseDatabase.getInstance().getReference("Menu");
        Menu.keepSynced(false);

        Penyakit = FirebaseDatabase.getInstance().getReference().child("Penyakit");
        Penyakit.keepSynced(false);

        recycler0 = (RecyclerView) findViewById((R.id.recycleview0));
        recycler0.setHasFixedSize(true);
        recycler0.setLayoutManager(new LinearLayoutManager(this));

        loadPenyakit();
    }

    public void loadPenyakit() {
        adapter0 = new FirebaseRecyclerAdapter <Blog0, BlogViewHolder0>
                (Blog0.class ,
                        R.layout.recycle0 ,
                        BlogViewHolder0.class ,
                        Penyakit) {
            @Override
            protected void populateViewHolder(final BlogViewHolder0 viewHolder , Blog0 model , final int position0) {
                final String post_key = getRef(position0).getKey();

                viewHolder.setNamapenyakit(model.getNamapenyakit());
                viewHolder.setFoto(getApplicationContext() , (model.getFoto()));

                viewHolder.mView0.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent MakananActivity = new Intent(PenyakitActivity.this , MakananActivity.class);
                        MakananActivity.putExtra("blog_id" , post_key);

                        startActivity(MakananActivity);
                    }
                });

            }
        };
        recycler0.setAdapter(adapter0);
    }


    public static class BlogViewHolder0 extends RecyclerView.ViewHolder
    {
        View mView0;

        public  BlogViewHolder0(View itemView)
        {
            super(itemView);
            mView0=itemView;
        }
        public void setNamapenyakit(String namapenyakit)
        {
            TextView mPenyakit=(TextView)mView0.findViewById(R.id.post_namapenyakit);
            mPenyakit.setText(namapenyakit);
        }
        public void setFoto(Context ctx, String foto)
        {
            ImageView mFoto=(ImageView)mView0.findViewById(R.id.post_foto);
            Glide.with(ctx).load(foto).into(mFoto);
        }
    }
}
