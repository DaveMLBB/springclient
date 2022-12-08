package com.example.springclient;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Toast;

import com.example.springclient.models.Employee;
import com.example.springclient.retrofit.EmployeeApi;
import com.example.springclient.retrofit.RetrofitService;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;

import java.util.logging.Level;
import java.util.logging.Logger;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initializeComponents();
    }

    private void initializeComponents() {
        TextInputEditText inputEditName = findViewById(R.id.form_textFieldName);
        TextInputEditText inputEditBranch = findViewById(R.id.form_textFieldBranch);
        TextInputEditText inputEditLocation = findViewById(R.id.form_textFieldLocation);
        MaterialButton buttonSaveEmployee = findViewById(R.id.form_buttonSave);

        RetrofitService retrofitService = new RetrofitService();
        EmployeeApi employeeApi = retrofitService.getRetrofit().create(EmployeeApi.class);

        buttonSaveEmployee.setOnClickListener(view -> {
            String name = String.valueOf(inputEditName.getText());
            String branch = String.valueOf(inputEditBranch.getText());
            String location = String.valueOf(inputEditLocation.getText());

            Employee employee = new Employee();
            employee.setName(name);
            employee.setLocation(location);
            employee.setBranch(branch);

            employeeApi.save(employee)
                    .enqueue(new Callback<Employee>() {
                        @Override
                        public void onResponse(Call<Employee> call, Response<Employee> response) {
                            Toast.makeText(MainActivity.this, "Save Successfull", Toast.LENGTH_SHORT).show();
                        }

                        @Override

                        public void onFailure(Call<Employee> call, Throwable t) {
                            if(employee != null) {
                                Toast.makeText(MainActivity.this, "Save 2", Toast.LENGTH_SHORT).show();
                            }
                            Toast.makeText(MainActivity.this, "Save failed!!!", Toast.LENGTH_SHORT).show();
                            Logger.getLogger(MainActivity.class.getName()).log(Level.SEVERE, "Error occured");
                        }
                    });


        });

    }
}