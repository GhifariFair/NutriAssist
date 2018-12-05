package ghifari.pptb;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.squareup.picasso.Picasso;

public class KaloriActivity extends AppCompatActivity {
    private Double kalori;
    private int[] Test = {0,0};
    private String test;
    private EditText number1, number2, number3;
    private TextView text, text2;
    private Button calc;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kalori);

        number1 = (EditText) findViewById(R.id.age);
        number2 = (EditText) findViewById(R.id.weight);
        number3 = (EditText) findViewById(R.id.height);
        calc = (Button) findViewById(R.id.btn);
        text = (TextView) findViewById(R.id.text);
        text2 = (TextView) findViewById(R.id.text2);
        text.setText("Insert your age, weight and height to calculate ideal daily calorie");
        if (getIntent().getExtras() != null) {
            test = getIntent().getExtras().getString("number1");
            text2.setText("Food calorie : "+test);
        }
        calc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculate();
            }
        });
    }

    public void calculate() {

        if (!validate()) {
            Toast.makeText(KaloriActivity.this, "Error occurred", Toast.LENGTH_SHORT).show();
        } else {
            Integer age = Integer.parseInt(number1.getText().toString());
            Integer weight = Integer.parseInt(number2.getText().toString());
            Integer height = Integer.parseInt(number3.getText().toString());

            kalori = 5*age + 10*weight + 6.25*height - 80;
            text.setText("Your ideal daily calorie intake : "+kalori);

            if (test != null) {
                Test[0] = kalori.intValue();
                Test[1] = Integer.parseInt(test);
                Intent hehe = new Intent(KaloriActivity.this, DisplayActivity.class);
                hehe.putExtra("number1", Test);

                startActivity(hehe);
            }
        }

    }

    public boolean validate() {
        boolean valid = true;

        String age = number1.getText().toString();
        String weight = number2.getText().toString();
        String height = number3.getText().toString();

        if (age.isEmpty() || age.length() > 3) {
            number1.setError("must be a valid number of age");
            valid = false;
        } else {
            number1.setError(null);
        }

        if (weight.isEmpty() || weight.length() > 3) {
            number2.setError("must be a valid number of weight");
            valid = false;
        } else {
            number2.setError(null);
        }

        if (height.isEmpty() || height.length() > 3) {
            number3.setError("must be a valid number of height");
            valid = false;
        } else {
            number3.setError(null);
        }

        return valid;
    }
}

