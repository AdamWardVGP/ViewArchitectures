package award.viewarchitectures.presenter;

import android.content.Context;
import android.text.SpannableString;
import android.text.style.UnderlineSpan;
import android.view.View;

import award.viewarchitectures.R;
import award.viewarchitectures.models.GithubIssue;
import award.viewarchitectures.presenter.interfaces.IssueCellPresenter;
import award.viewarchitectures.views.IssueCellView;

public class IssueCellPresenterImpl implements IssueCellPresenter {

    public static String TAG = "IssuePagePresenterImpl";

    private IssueCellView mIssueCellView;
    private GithubIssue mIssue;
    private Context mContext;

    public IssueCellPresenterImpl(Context context, GithubIssue issue) {
        mIssue = issue;
        mContext = context;
    }

    @Override
    public void attachView(IssueCellView view) {
        this.mIssueCellView = view;
    }

    @Override
    public void detachView() {
        this.mIssueCellView = null;
    }

    @Override
    public void populateView() {
        mIssueCellView.setBody(mIssue.body);
        mIssueCellView.setCommentsVisibility(mIssue.comments == 0 ? View.GONE : View.VISIBLE);
        mIssueCellView.setIssueNumber(String.valueOf(mIssue.number));
        mIssueCellView.setIssueTitle(mIssue.title);

        String login = mIssue.user.login;
        String author = mContext.getString(R.string.issue_author, login);
        SpannableString spannable = new SpannableString(author);
        int index = author.indexOf(login);
        spannable.setSpan(new UnderlineSpan(), index, login.length() + index, 0);

        mIssueCellView.setIssueAuthor(spannable);
    }

}
