package socialinfotech.amazonservice.main;

import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Path;


/**
 * Created by pankaj on 4/07/16.
 */
public interface GitHubService {


    @GET("/bookmix/{id}")
    void login(@Path("id")String login, Callback<ResponseModel> response);


}