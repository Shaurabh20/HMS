package com.example.hms;

import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface RetrofitInterface {
    @POST("/imposeFine")
    Call<String> imposeFine(@Body HashMap<String,String> map);

    @POST("/removeStudent")
    Call<String> removeStudent(@Body HashMap<String,String> map);

    @POST("/updateStudent")
    Call<String> updateStudent(@Body HashMap<String,String> map);

    @POST("/raiseComplaint")
    Call<String> raiseComplaint(@Body HashMap<String,String> map);

    @POST("/addStudent")
    Call<String> addStudent(@Body HashMap<String,String> map);

    @POST("/allotRoom")
    Call<String> allotRoom(@Body HashMap<String,String> map);

    @POST("/loginStudent")
    Call<String> loginStudent(@Body HashMap<String,String> map);

    @POST("/getLeaveDetails")
    Call<Leave> getLeaveDetails(@Body HashMap<String,String> map);

    @POST("/updateLeaveStatus")
    Call<String> updateLeaveStatus(@Body HashMap<String,String> map);

    @POST("/getStudent")
    Call<StudentData> getStudent(@Body HashMap<String,String> map);

    @GET("/getLeaves")
    Call<List<Leave>> getLeaves();

    @GET("/showFineWarden")
    Call<List<Fine>> showFineWarden();

    @POST("/getLeaveStatus")
    Call<List<Leave>> getLeaveStatus(@Body HashMap<String,String> map);

    @GET("/getLeaves")
    Call<List<Complaint>> getComplaints();

    @POST("/loginWarden")
    Call<String> loginWarden(@Body HashMap<String,String> map);

    @POST("/applyLeave")
    Call<String> applyLeave(@Body HashMap<String,String> map);

    @POST("/viewLeave")
    Call<String> viewLeave(@Body HashMap<String,String> map);

    @POST("/showFineStudent")
    Call<List<Fine>> showFineStudent(@Body HashMap<String,String> map);
}
