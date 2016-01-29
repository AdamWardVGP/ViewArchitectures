package award.viewarchitectures.data;

import java.util.List;

import award.viewarchitectures.models.GithubIssue;
import rx.Observable;
import rx.Scheduler;

public class RequestManager {

    private GithubAPI mGithubAPI;
    private Scheduler mNetworkIOScheduler;

    public RequestManager(GithubAPI githubAPI, Scheduler networkIOScheduler) {
        this.mGithubAPI = githubAPI;
        this.mNetworkIOScheduler = networkIOScheduler;
    }

    public Scheduler getScheduler() {
        return mNetworkIOScheduler;
    }

    public Observable<List<GithubIssue>> getIssues(String user, String repo, String state) {
        return mGithubAPI.getIssuesList(user, repo, state);
    }
}
