package com.example.springclient.retrofit;

import com.example.springclient.models.Employee;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface EmployeeApi {

    @GET("/api/employee/get-all")
    Call<List<Employee>> getAllEmployee();

    @POST("/api/employee/save")
    Call<Employee> save(@Body Employee employee);
}
