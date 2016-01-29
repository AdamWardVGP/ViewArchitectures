package award.viewarchitectures.data;

import java.util.List;

import award.viewarchitectures.models.GithubIssue;
import retrofit.http.GET;
import retrofit.http.Path;
import retrofit.http.Query;
import rx.Observable;

public interface GithubAPI {

    String ENDPOINT = "https://api.github.com/";

    @GET("/repos/{owner}/{repo}/issues")
    Observable<List<GithubIssue>> getIssuesList(@Path("owner") String user,
                                             @Path("repo") String repo,
                                             @Query("state") String issueState);
}
