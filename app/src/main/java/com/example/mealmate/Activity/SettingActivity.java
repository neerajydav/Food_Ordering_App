package com.example.mealmate.Activity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.mealmate.R;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;

public class SettingActivity extends AppCompatActivity {

    TextInputEditText txt_profilename, txt_profilepass, txt_profilenumber;
    TextView plain_profilename, plain_profileemail, text_profileabout;
    ImageView img_profilechangeabout;
    MaterialButton btn_updateprofile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);  // ✅ Make sure this layout exists

        txt_profilename = findViewById(R.id.text_profilename);
        txt_profilenumber = findViewById(R.id.text_profilenumber);
        txt_profilepass = findViewById(R.id.text_profilepassword);

        plain_profilename = findViewById(R.id.plain_profilename);
        plain_profileemail = findViewById(R.id.plain_profileemail);

        btn_updateprofile = findViewById(R.id.btn_updateprofile);
        text_profileabout = findViewById(R.id.text_profileabout);
        img_profilechangeabout = findViewById(R.id.img_profilechangeabout);

        String currentuser = FirebaseAuth.getInstance().getCurrentUser().getUid();

        FirebaseDatabase.getInstance().getReference().child("users")
                .child(currentuser).addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot snapshot) {
                        txt_profilename.setText(snapshot.child("name").getValue().toString());
                        txt_profilepass.setText(snapshot.child("password").getValue().toString());
                        plain_profilename.setText(snapshot.child("name").getValue().toString());
                        plain_profileemail.setText(snapshot.child("email").getValue().toString());

                        if (snapshot.child("about").getValue() != null) {
                            text_profileabout.setText(snapshot.child("about").getValue().toString());
                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError error) {
                        Toast.makeText(SettingActivity.this, "Server Error", Toast.LENGTH_SHORT).show();
                    }
                });

        btn_updateprofile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = txt_profilename.getText().toString();
                String number = txt_profilenumber.getText().toString();
                String password = txt_profilepass.getText().toString();

                HashMap<String, Object> hm = new HashMap<>();
                hm.put("name", name);
                hm.put("number", number);
                hm.put("password", password);

                FirebaseDatabase.getInstance().getReference()
                        .child("users").child(currentuser).updateChildren(hm);

                Toast.makeText(SettingActivity.this, "Profile Updated", Toast.LENGTH_LONG).show();
            }
        });

        img_profilechangeabout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText edit = new EditText(SettingActivity.this);
                AlertDialog.Builder alertbox = new AlertDialog.Builder(SettingActivity.this);
                alertbox.setTitle("About You");
                alertbox.setMessage("Enter about yourself");
                alertbox.setView(edit);

                alertbox.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        HashMap<String, Object> hm = new HashMap<>();
                        hm.put("about", edit.getText().toString());
                        FirebaseDatabase.getInstance().getReference()
                                .child("users").child(currentuser).updateChildren(hm);
                    }
                });

                alertbox.setNegativeButton("Dismiss", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(SettingActivity.this, "Cancelled", Toast.LENGTH_SHORT).show();
                    }
                });

                alertbox.show();
            }
        });
    }
}
