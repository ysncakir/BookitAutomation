package com.bookit.utilities;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;

public class APIUtilities {

    private static String URI = ConfigurationReader.getProperties("bookit.api.qa1");


    /**
     * Method can generate access token
     * @return bearer token
     */


    public static String getToken(){
        Response response = given().queryParam("email", ConfigurationReader.getProperties("team.leader.email")).
                queryParam("password", ConfigurationReader.getProperties("team.leader.password")).
                when().get("/sign").prettyPeek();

        return response.jsonPath().getString("accessToken");

    }

    public static String getToken(String role){
        String username ="";
        String password ="";

        if(role.toLowerCase().contains("lead")){
            username = ConfigurationReader.getProperties("team.leader.email");
            password = ConfigurationReader.getProperties("team.leader.password");
        } else if(role.toLowerCase().contains("teacher")){
            username = ConfigurationReader.getProperties("teacher.email");
            password = ConfigurationReader.getProperties("teacher.password");
        } else if(role.toLowerCase().contains("member")){
            username = ConfigurationReader.getProperties("team.member.email");
            password = ConfigurationReader.getProperties("team.member.password");
        } else{
            throw new RuntimeException("Invalid user type!");
        }

        Response response = given().queryParam("email", username).
                queryParam("password", password).
                when().get("/sign").prettyPeek();

        return response.jsonPath().getString("accessToken");

    }
}
