package award.viewarchitectures.viewModel;

import android.content.Context;
import android.databinding.BaseObservable;

import award.viewarchitectures.models.GithubComment;

/**
 * Created by adamward on 1/29/16.
 */
public class GithubCommentViewModel extends BaseObservable {

    private Context context;
    private GithubComment comment;

    public GithubCommentViewModel(Context context, GithubComment comment) {
        this.context = context;
        this.comment = comment;
    }

    public String getCommentAuthor() {
        return comment.user.login;
    }

    public String getCommentBody() {
        return comment.body;
    }

    public String getCommentTime() {
        return comment.created_at;
    }
}
