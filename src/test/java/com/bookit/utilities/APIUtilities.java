package com.bookit.utilities;

import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class APIUtilities {

    private static String URI = ConfigurationReader.getProperty(ConfigurationReader.getProperty("bookit.api"+
                            ConfigurationReader.getProperty(("environment"))));


    /**
     * Method can generate access token
     * @return bearer token
     */


    public static String getToken(){
        Response response = given().queryParam("email", ConfigurationReader.getProperty("team.leader.email")).
                queryParam("password", ConfigurationReader.getProperty("team.leader.password")).
                when().get("/sign").prettyPeek();

        return response.jsonPath().getString("accessToken");

    }

    public static String getToken(String role){
        String username ="";
        String password ="";

        if(role.toLowerCase().contains("lead")){
            username = ConfigurationReader.getProperty("team.leader.email");
            password = ConfigurationReader.getProperty("team.leader.password");
        } else if(role.toLowerCase().contains("teacher")){
            username = ConfigurationReader.getProperty("teacher.email");
            password = ConfigurationReader.getProperty("teacher.password");
        } else if(role.toLowerCase().contains("member")){
            username = ConfigurationReader.getProperty("team.member.email");
            password = ConfigurationReader.getProperty("team.member.password");
        } else{
            throw new RuntimeException("Invalid user type!");
        }

        Response response = given().queryParam("email", username).
                queryParam("password", password).
                when().get("/sign").prettyPeek();

        return response.jsonPath().getString("accessToken");

    }
}
