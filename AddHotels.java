package com.s22010176.finderapp;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class AddHotels extends AppCompatActivity {
    DataBaseHelper myDb;
    EditText editHotelName, editAddress, editPhoneNumber, editHotels;
    Button btnAddData, btnViewAll, btnUpdateData,btnDeleteData;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.add_hotels);
        myDb= new DataBaseHelper(this);

        editHotelName= findViewById(R.id.hotelDtxt3);
        editAddress=findViewById(R.id.hotelDtxt5);
        editPhoneNumber=findViewById(R.id.hotelDtxt7);
        btnAddData= findViewById(R.id.hotelDbtn1);
        btnViewAll= findViewById(R.id.hotelDbtn2);
        btnUpdateData=findViewById(R.id.hotelDbtn3);
        editHotels= findViewById(R.id.hotelDtxt8);
        btnDeleteData=findViewById(R.id.hotelDbtn4);

        addData();
        viewAll();
        updateData();
        deleteData();
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    public void addData(){
        btnAddData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean isInserted = myDb.insertData(editHotelName.getText().toString(),editAddress.getText().toString(),editPhoneNumber.getText().toString());
            if (isInserted == true)
                Toast.makeText(AddHotels.this,"Data Inserted Successfully",Toast.LENGTH_LONG).show();
            else
                Toast.makeText(AddHotels.this,"Data Not Inserted Successfully",Toast.LENGTH_LONG).show();

            }
        });
    }

    public  void viewAll(){
        btnViewAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cursor results= myDb.getAllData();
                if (results.getCount()==0){
                    showMessage("Error Message:", "No Data Found");
                    return;
                }
                StringBuffer buffer= new StringBuffer();
                while (results.moveToNext()){
                    buffer.append("ID:" + results.getString(0)+ "\n");
                    buffer.append("Hotel Name:" + results.getString(1)+ "\n");
                    buffer.append("Address:" + results.getString(2)+ "\n");
                    buffer.append("Phone Number:" + results.getString(3)+ "\n");

                    showMessage("List of Data:", buffer.toString());

                }
            }
        });
    }
    public void showMessage(String title, String message){
        AlertDialog.Builder builder= new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();
    }

    public void updateData(){
        btnUpdateData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean isUpdate= myDb.updateData(editHotels.getText().toString(),editHotelName.getText().toString(),editAddress.getText().toString(),editPhoneNumber.getText().toString());
            if (isUpdate == true)
                Toast.makeText(AddHotels.this,"Data Updated", Toast.LENGTH_LONG).show();
            else
                Toast.makeText(AddHotels.this,"Data Not Updated", Toast.LENGTH_LONG).show();
            }
        });
    }

    public  void deleteData(){
        btnDeleteData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Integer deletedatarows = myDb.deleteData(editHotels.getText().toString());
                if (deletedatarows>0)
                    Toast.makeText(AddHotels.this,"Data Deleted", Toast.LENGTH_LONG).show();
                else
                    Toast.makeText(AddHotels.this,"Data Not Deleted",Toast.LENGTH_LONG).show();
            }
        });
    }

}


