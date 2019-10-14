package com.example.firstacm;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import androidx.appcompat.app.AppCompatActivity;

public class AddEntry extends AppCompatActivity {

    EditText edtFullName,edtClass,edtCollage;
    Button btnSaveToDb;
    DatabaseReference reff;
    DataTypes dataTypes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_entry);

        edtFullName=(EditText)findViewById(R.id.txtFullName);
        edtClass=(EditText) findViewById(R.id.txtClass);
        edtCollage=(EditText) findViewById(R.id.txtCollageName);
        btnSaveToDb=(Button) findViewById(R.id.btnSave);
        reff= FirebaseDatabase.getInstance().getReference().child("Entries");
        dataTypes=new DataTypes();

        btnSaveToDb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String fnm = edtFullName.getText().toString();
                String cls = edtClass.getText().toString();
                String cnm = edtCollage.getText().toString();

                dataTypes.setFullName(fnm);
                dataTypes.setClassName(cls);
                dataTypes.setCollageName(cnm);

                reff.push().setValue(dataTypes);
                Toast.makeText(AddEntry.this,"Entry Added Successfully",Toast.LENGTH_LONG).show();

                startActivity(new Intent(getApplicationContext(),WorkshopEntry.class));

            }
        });
    }
}
