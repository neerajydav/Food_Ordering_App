package com.example.mealmate.Activity;

import static java.util.ResourceBundle.getBundle;

import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.bumptech.glide.Glide;
import com.example.mealmate.Domain.FoodDomain;
import com.example.mealmate.Helper.ManagmentCart;
import com.example.mealmate.R;

public class DetailActivity extends AppCompatActivity {

//    private boolean addToCartBtn;
    private AppCompatButton addToCartBtn;
    private TextView plusBtn,minusBtn,titleTxt,feeTxt,descriptionTxt, numberOrderTxt, startTxt, caloryTxt, timeTxt;
    private ImageView picFood;
    private FoodDomain object;
    private int numberOrder = 1;
    private ManagmentCart managmentCart;
    private ImageView backBtn;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setBackgroundDrawable(
                    new ColorDrawable(getResources().getColor(R.color.grey))
            );
        }


        backBtn = findViewById(R.id.backBtn);
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        managmentCart = new ManagmentCart(DetailActivity.this);

        initView();
        getBundle();
    }

    private void getBundle() {
        object = (FoodDomain) getIntent().getSerializableExtra("object");

        int drawableResourceId = this.getResources().getIdentifier(object.getPicUrl(), "drawable", this.getPackageName());
        Glide.with(this)
                .load(drawableResourceId)
                .into(picFood);

        titleTxt.setText(object.getTitle());
        feeTxt.setText("Rs"+object.getPrice());
        descriptionTxt.setText(object.getDescription());
        numberOrderTxt.setText(""+numberOrder);
        caloryTxt.setText(object.getEnergy() + " Cal");
        startTxt.setText(object.getScore() + "");
        timeTxt.setText(object.getTime() + " min");
        addToCartBtn.setText("Add to cart + Rs "+Math.round(numberOrder*object.getPrice()));

        plusBtn.setOnClickListener(v -> {
            numberOrder=numberOrder + 1;
            numberOrderTxt.setText(""+numberOrder);
            addToCartBtn.setText("Add to cart + Rs "+Math.round(numberOrder*object.getPrice()));
        });
        minusBtn.setOnClickListener(v -> {
            numberOrder=numberOrder - 1;
            numberOrderTxt.setText(""+numberOrder);
            addToCartBtn.setText("Add to cart + Rs "+Math.round(numberOrder*object.getPrice()));
        });

        addToCartBtn.setOnClickListener(v -> {
            object.setNumberinCart(numberOrder);
            managmentCart.insertFood(object);
        });

    }

    private void initView() {
        addToCartBtn = findViewById(R.id.addToCartBtn);
        timeTxt = findViewById(R.id.timeTxt);
        feeTxt = findViewById(R.id.priceTxt);
        titleTxt = findViewById(R.id.titleTxt);
        descriptionTxt = findViewById(R.id.descriptionTxt);
        numberOrderTxt = findViewById(R.id.numberItemTxt);
        plusBtn = findViewById(R.id.plusCartBtn);
        minusBtn = findViewById(R.id.minusCartBtn);
        startTxt = findViewById(R.id.StarTxt);
        caloryTxt = findViewById(R.id.calTxt);
        picFood = findViewById(R.id.foodPic);
    }
}