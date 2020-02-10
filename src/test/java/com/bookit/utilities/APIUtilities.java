package com.bookit.utilities;

import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class APIUtilities {

    private static String URI = Environment.BASE_URI;


    /**
     * Method can generate access token
     * @return bearer token
     */


    public static String getToken(){
        Response response = given().queryParam("email", Environment.LEADER_USERNAME).
                queryParam("password", Environment.LEADER_PASSWORD).
                when().get("/sign").prettyPeek();

        return response.jsonPath().getString("accessToken");

    }

    public static String getToken(String role){
        String username ="";
        String password ="";

        if(role.toLowerCase().contains("lead")){
            username = Environment.LEADER_USERNAME;
            password = Environment.LEADER_PASSWORD;
        } else if(role.toLowerCase().contains("teacher")){
            username = Environment.TEACHER_USERNAME;
            password = Environment.TEACHER_PASSWORD;
        } else if(role.toLowerCase().contains("member")){
            username = Environment.MEMBER_USERNAME;
            password = Environment.MEMBER_PASSWORD;
        } else{
            throw new RuntimeException("Invalid user type!");
        }

        Response response = given().queryParam("email", username).
                queryParam("password", password).
                when().get("/sign").prettyPeek();

        return response.jsonPath().getString("accessToken");

    }

    public static Response deleteMe(String email, String password){
        String token = given().
                                queryParam("email", email).
                                queryParam("password", password).
                       when().
                                get("/api/users/me").
                                jsonPath().getString("accessToken");

        int userToDelete = given().
                                    auth().oauth2(token).
                           when().
                                    get("/api/users/me").
                                    jsonPath().getInt("id");
        Response response = given().auth().
                                    oauth2(getToken("teacher")).
                            when().
                                    delete(EndPoints.DELETE_STUDENT, userToDelete);
        response.prettyPeek();
        return response;
    }
}
