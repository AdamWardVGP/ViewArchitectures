package award.viewarchitectures.viewModel;

import android.content.Context;
import android.databinding.BaseObservable;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.UnderlineSpan;
import android.view.View;

import award.viewarchitectures.CommentsActivity;
import award.viewarchitectures.R;
import award.viewarchitectures.models.GithubIssue;

public class GithubIssueViewModel extends BaseObservable {

    private Context context;
    private GithubIssue issue;

    public GithubIssueViewModel(Context context, GithubIssue issue) {
        this.context = context;
        this.issue = issue;
    }

    public String getIssueTitle() {
        return issue.title;
    }

    public String getBody() {
        return issue.body;
    }

    public Spannable getIssueAuthor() {
        String login = issue.user.login;
        String author = context.getString(R.string.issue_author, login);
        SpannableString spannable = new SpannableString(author);
        int index = author.indexOf(login);
        spannable.setSpan(new UnderlineSpan(), index, login.length() + index, 0);
        return spannable;
    }

    public String getIssueNumber() {
        return context.getResources().getString(R.string.issue_number) + String.valueOf(issue.number);
    }

    public String getIssueBody() {
        return issue.body;
    }

    public int getCommentsVisibility() {
        return  issue.comments == 0 ?  View.GONE : View.VISIBLE;
    }

    public View.OnClickListener onClickComments() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                launchCommentsActivity();
            }
        };
    }

    public View.OnClickListener onClickIssue() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                launchCommentsActivity();
            }
        };
    }

    private void launchCommentsActivity() {
        context.startActivity(CommentsActivity.getStartIntent(context, issue));
    }
}

