package award.viewarchitectures.views;

import java.util.List;

import award.viewarchitectures.models.GithubIssue;

public interface IssuePageView {

    void showIssues(List<GithubIssue> repositories);

    void showError();

    void hideLoadingViews();

    void showOfflineLayout(boolean show);

}
