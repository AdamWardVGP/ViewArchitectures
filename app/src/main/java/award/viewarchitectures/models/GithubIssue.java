package award.viewarchitectures.models;

final public class GithubIssue {
    public final int id;
    public final String url;
    public final int comments;
    public final String comments_url;
    public final int number;
    public final String title;
    public final String body;
    public final GithubUser user;

    public GithubIssue(int id, String url, int comments, String comments_url, int number, String title, String body, GithubUser user) {
        this.id = id;
        this.url = url;
        this.comments = comments;
        this.comments_url = comments_url;
        this.number = number;
        this.title = title;
        this.body = body;
        this.user = user;
    }
}
