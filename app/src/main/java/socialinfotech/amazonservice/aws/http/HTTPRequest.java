package socialinfotech.amazonservice.aws.http;

import org.apache.commons.codec.binary.Hex;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URLEncodedUtils;

import java.net.URI;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import socialinfotech.amazonservice.Credentials;
import socialinfotech.amazonservice.aws.awsauth.AWS4SignerBase;
import socialinfotech.amazonservice.aws.awsauth.AWS4SignerForAuthorizationHeader;
import socialinfotech.amazonservice.aws.awsauth.AuthHeaderModel;

/**
 * Created by pankaj on 4/07/16.
 */
public class HTTPRequest {


    public AuthHeaderModel AWSPostData(String url, String data) {
        HttpResponse response = null;
        try {
            System.out.print("-------------------------------------url-------------------------------------------------" + "\r\n");
            System.out.print(url + "\r\n");
            System.out.print("-------------------------------------url-------------------------------------------------" + "\r\n");
            AWS4SignerForAuthorizationHeader a = new AWS4SignerForAuthorizationHeader(new URL(url), "POST", Credentials.Service_Name, Credentials.AWS_Region);
            byte[] dta = AWS4SignerBase.hash(data);
            String decoded = Hex.encodeHexString(dta);

            Map<String, String> header = new HashMap<String, String>();
            Map<String, String> q = new HashMap<String, String>();
            // add headers here before computing signature
            //header.put("Content-Type","application/json");
            AuthHeaderModel auth = a.computeSignature(header, q, decoded, Credentials.AccessKey, Credentials.Secret_key);
            return auth;
        } catch (Exception ex) {
            System.out.print(ex);
        }

        return null;
    }

    public AuthHeaderModel AWSGetData(String url, boolean containsQueryParams) {
        HttpResponse response = null;
        String data = "";
        try {
            System.out.print("-------------------------------------url-------------------------------------------------" + "\r\n");
            System.out.print(url + "\r\n");
            System.out.print("-------------------------------------url-------------------------------------------------" + "\r\n");
            AWS4SignerForAuthorizationHeader a = new AWS4SignerForAuthorizationHeader(new URL(url), "GET", Credentials.Service_Name, Credentials.AWS_Region);
            byte[] dta = AWS4SignerBase.hash(data);
            String decoded = new String(Hex.encodeHex(dta));
            HttpGet httpget = new HttpGet(url);
            Map<String, String> header = new HashMap<String, String>();
            Map<String, String> q = new HashMap<String, String>();
            if (containsQueryParams) {
                List<NameValuePair> params = URLEncodedUtils.parse(new URI(url), "UTF-8");

                for (NameValuePair param : params) {
                    q.put(param.getName(), param.getValue());
                }
            }
            // add headers here before computing signature
            //header.put("Content-Type","application/json");
            AuthHeaderModel auth = a.computeSignature(header, q, decoded, Credentials.AccessKey, Credentials.Secret_key);
            return auth;
        } catch (Exception ex) {
            System.out.print(ex);

        }

        return null;
    }


}
