package award.viewarchitectures.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.Spannable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import award.viewarchitectures.R;
import award.viewarchitectures.models.GithubIssue;
import award.viewarchitectures.presenter.IssueCellPresenterImpl;
import award.viewarchitectures.views.IssueCellView;
import butterknife.Bind;
import butterknife.ButterKnife;

public class GithubIssueAdapter extends RecyclerView.Adapter<GithubIssueAdapter.IssueViewHolder> {
    private List<GithubIssue> mIssues;
    private Context mContext;

    public GithubIssueAdapter(Context context) {
        mIssues = new ArrayList<>();
        mContext = context;
    }

    @Override
    public IssueViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater
            .from(parent.getContext())
            .inflate(R.layout.github_issue, parent, false);

        return new IssueViewHolder(view);
    }

    @Override
    public void onBindViewHolder(IssueViewHolder holder, int position) {
        IssueCellPresenterImpl presenter = new IssueCellPresenterImpl(mContext, mIssues.get(position));
        presenter.attachView(holder);
        presenter.populateView();
    }

    @Override
    public int getItemCount() {
        return mIssues.size();
    }

    public void setItems(List<GithubIssue> issues) {
        mIssues = issues;
        notifyDataSetChanged();
    }

    public static class IssueViewHolder extends RecyclerView.ViewHolder  implements IssueCellView {

        @Bind(R.id.text_issue_number)
        TextView mIssueNumber;
        @Bind(R.id.text_issue_author)
        TextView mAuthor;
        @Bind(R.id.text_issue_body)
        TextView mBody;
        @Bind(R.id.text_issue_title)
        TextView mTitle;
        @Bind(R.id.text_view_comments)
        TextView mComments;


        @Override
        public void setIssueTitle(String title) {
            mTitle.setText(title);
        }

        @Override
        public void setBody(String body) {
            mBody.setText(body);
        }

        @Override
        public void setIssueAuthor(Spannable spannable) {
            mAuthor.setText(spannable);
        }

        @Override
        public void setIssueNumber(String issueNumber) {
            mIssueNumber.setText(issueNumber);
        }

        @Override
        public void setCommentsVisibility(int visibilityState) {
            mComments.setVisibility(visibilityState);
        }

        public IssueViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }

}
