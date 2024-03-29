package com.example.finalyearproject.User;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.example.finalyearproject.MainActivity;
import com.example.finalyearproject.R;
import com.google.firebase.auth.FirebaseAuth;

public class User_Activity extends AppCompatActivity implements View.OnClickListener {
    Button user_canteen, user_food_List, user_Available_Food_List, user_offer,wishlist,feedback;
    private FirebaseAuth mAuth;
    private Typeface typeface_1;
    private Typeface typeface_2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_);

        this.setTitle("User Activity");
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        mAuth = FirebaseAuth.getInstance();

        user_canteen = findViewById(R.id.user_canteen_Id);
        wishlist = findViewById(R.id.wishlist_id);
        feedback = findViewById(R.id.user_feedback_Id);
        user_food_List = findViewById(R.id.user_foodlist_Id);
        user_Available_Food_List = findViewById(R.id.user_available_foodlist_Id);
        user_offer = findViewById(R.id.user_offer);

        typeface_1 = Typeface.createFromAsset(getAssets(), "font/action_Man_Bold.ttf");
        typeface_2 = Typeface.createFromAsset(getAssets(), "font/kaushanScript_Regular.otf");
        user_canteen.setTypeface(typeface_1);
        user_food_List.setTypeface(typeface_1);
        user_Available_Food_List.setTypeface(typeface_1);


        user_food_List.setOnClickListener(this);
        user_canteen.setOnClickListener(this);
        user_offer.setOnClickListener(this);
        wishlist.setOnClickListener(this);
        feedback.setOnClickListener(this);
        user_Available_Food_List.setOnClickListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.user_menu_bar, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.logout_menu_id) {
            //Logout
            FirebaseAuth.getInstance().signOut();

            SharedPreferences sharedPreferences = getSharedPreferences("userDetails", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();

            editor.remove("email_Key");
            editor.remove("password_Key");
// Save the changes in SharedPreferences
            editor.commit();

            Intent intent = new Intent(User_Activity.this, MainActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
        } else if (item.getItemId() == R.id.about_menu_id) {
            Intent intent = new Intent(getApplicationContext(), User_About_Us.class);
            startActivity(intent);
        } else if (item.getItemId() == R.id.about_menu_id) {
            Intent intent = new Intent(getApplicationContext(), User_About_Us.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.user_canteen_Id) {
            Intent intent = new Intent(User_Activity.this, User_Picture.class);
            startActivity(intent);
        } else if (v.getId() == R.id.user_foodlist_Id) {
            Intent intent = new Intent(User_Activity.this, User_foodList.class);
            startActivity(intent);
        } else if (v.getId() == R.id.user_available_foodlist_Id) {
            Intent intent = new Intent(User_Activity.this, User_Available_Food_List.class);
            startActivity(intent);
        } else if (v.getId() == R.id.user_offer) {
            Intent intent = new Intent(User_Activity.this, User_Offer_List.class);
            startActivity(intent);
        }else if (v.getId() == R.id.wishlist_id) {
            Intent intent = new Intent(User_Activity.this, User_Wishlist.class);
            startActivity(intent);
        }else if (v.getId() == R.id.user_feedback_Id) {
            Intent intent = new Intent(User_Activity.this, FeedBack.class);
            startActivity(intent);
        }
    }
}