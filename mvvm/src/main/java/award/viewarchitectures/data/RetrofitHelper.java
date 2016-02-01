package award.viewarchitectures.data;

import com.google.gson.GsonBuilder;

import retrofit.RestAdapter;
import retrofit.converter.GsonConverter;

public class RetrofitHelper {
    public static GithubAPI CreateGithubApi() {

        RestAdapter restAdapter = new RestAdapter.Builder()
                .setEndpoint(GithubAPI.ENDPOINT)
                .setLogLevel(RestAdapter.LogLevel.FULL)
                .setConverter(new GsonConverter(new GsonBuilder().create()))
                .build();

        //TODO: add request interceptor to include auth header with requests
//        final String githubToken = getResources().getString(R.string.github_oauth_token);
//        if (!isEmpty(githubToken)) {
//            builder.setRequestInterceptor(new RequestInterceptor() {
//                @Override
//                public void intercept(RequestFacade request) {
//                    request.addHeader("Authorization", format("token %s", githubToken));
//                }
//            });
//        }

        return restAdapter.create(GithubAPI.class);
    }

}
