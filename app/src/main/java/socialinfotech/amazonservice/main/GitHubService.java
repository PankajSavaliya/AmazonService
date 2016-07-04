package socialinfotech.amazonservice.main;

import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Path;


/**
 * Created by pankaj on 4/07/16.
 */
public interface GitHubService {
    public static final String API_URL = "https://n4g08yp8j9.execute-api.us-west-2.amazonaws.com";


    @GET("/bookmix/{id}")
    void login(@Path("id")String login, Callback<ResponseModel> response);


}