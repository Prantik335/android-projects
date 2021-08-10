package me.prantik.guessingnumber;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

public class SplashActivity extends AppCompatActivity {

    private ImageView splashImageView;
    private TextView titleTextView;

    Animation imageAnimation, textAnimation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        if(getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }

        splashImageView = findViewById(R.id.imageViewSplash);
        titleTextView = findViewById(R.id.textViewTitle);

        imageAnimation = AnimationUtils.loadAnimation(this, R.anim.image_animation);
        textAnimation = AnimationUtils.loadAnimation(this, R.anim.text_animation);

        splashImageView.setAnimation(imageAnimation);
        titleTextView.setAnimation(textAnimation);

        new CountDownTimer(4000, 1000) {
            @Override
            public void onTick(long l) {

            }

            @Override
            public void onFinish() {
                Intent intent = new Intent(SplashActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        }.start();
    }
}