package ghifari.pptb;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.squareup.picasso.Picasso;

public class DisplayActivity extends AppCompatActivity {
    private String Ka, Kb;
    private int[] Test;
    private Integer Kaloriatas=100, Kaloribawah=0;
    private TextView text1, text2;
    private ImageView image;
    private Button butt;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display);

        text1 = (TextView) findViewById(R.id.text1);
        text2 = (TextView) findViewById(R.id.text2);
        image = (ImageView) findViewById(R.id.image);
        butt = (Button) findViewById(R.id.btn);

        if (getIntent().getExtras() != null) {
            Test = getIntent().getIntArrayExtra("number1");
            text1.setText("Your ideal daily calorie intake is " + Test[0]);
            text2.setText("Your today's calorie intake plan is " + Test[1]);
            if (Test[1] >= 0.25 * Test[0]) {
                image.setImageResource(R.drawable.g);
            } else {
                image.setImageResource(R.drawable.f);
            }
        }
    }
}