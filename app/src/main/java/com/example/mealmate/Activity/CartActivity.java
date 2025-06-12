package com.example.mealmate.Activity;

import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.mealmate.Helper.ChangeNumberItemsListner;

import com.example.mealmate.Adapter.CartListAdapter;
import com.example.mealmate.Helper.ManagmentCart;
import com.example.mealmate.R;

public class CartActivity extends AppCompatActivity {

    private RecyclerView.Adapter adapter;
    private RecyclerView recyclerViewList;
    private ManagmentCart managmentCart;
    private TextView totalFeeTxt, taxTxt, deliveryTxt, totalTxt, emptyTxt;
    private double tax;
    private ScrollView scrollView;
    private ImageView backBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setBackgroundDrawable(
                    new ColorDrawable(getResources().getColor(R.color.grey))
            );
        }

        managmentCart=new ManagmentCart(this);
        initView();
        initList();
        calculateCart();
        setVariable();
    }

    private void setVariable() {
        backBtn.setOnClickListener(v -> finish());
    }

    private void initList() {
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerViewList.setLayoutManager(linearLayoutManager);
        adapter = new CartListAdapter(new ChangeNumberItemsListner() {
                    @Override
                    public void changed() {
                        calculateCart();
                    }
                }, this, managmentCart.getListCart());
        recyclerViewList.setAdapter(adapter);

        if(managmentCart.getListCart().isEmpty()){
            emptyTxt.setVisibility(View.VISIBLE);
            scrollView.setVisibility(View.GONE);
        }else{
            emptyTxt.setVisibility(View.GONE);
            scrollView.setVisibility(View.VISIBLE);
        }
    }
    private void calculateCart(){
        double percentTax=0.02; //You can change this item for tax price
        double delivery=0;
        tax=Math.round((managmentCart.getTotalFee()*percentTax*100.0))/100.0;
        double total=Math.round((managmentCart.getTotalFee()+tax+delivery)*100.0)/100;
        double itemTotal=Math.round(managmentCart.getTotalFee()*100.0)/100.0;

        totalFeeTxt.setText("Rs" + itemTotal);
        taxTxt.setText("Rs" + tax);
        deliveryTxt.setText("Rs" + delivery);
        totalTxt.setText("Rs" + total);

    }

    private void initView() {
        totalFeeTxt=findViewById(R.id.totalFeeTxt);
        taxTxt=findViewById(R.id.taxTxt);
        deliveryTxt=findViewById(R.id.deliveryTxt);
        totalTxt=findViewById(R.id.totalTxt);
        recyclerViewList=findViewById(R.id.view3);
        scrollView=findViewById(R.id.scrollView);
        backBtn=findViewById(R.id.backBtn);
        emptyTxt=findViewById(R.id.emptyTxt);
    }
}