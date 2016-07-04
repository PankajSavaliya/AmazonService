package socialinfotech.amazonservice.aws.awsauth;

/**
 * Created by pankaj on 04/07/16.
 */
public class AuthHeaderModel {
    String Authorization;
    String date;

    public AuthHeaderModel(String authorization, String date) {
        Authorization = authorization;
        this.date = date;
    }

    public String getAuthorization() {
        return Authorization;
    }

    public String getDate() {
        return date;
    }
}
