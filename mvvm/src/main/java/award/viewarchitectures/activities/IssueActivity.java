package award.viewarchitectures.activities;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;

import award.viewarchitectures.R;
import award.viewarchitectures.databinding.ActivityIssueBinding;
import award.viewarchitectures.models.GithubIssue;
import award.viewarchitectures.viewModel.GithubIssueViewModel;

public class IssueActivity extends BaseActivity {

    private GithubIssue mGithubIssue;
    private ActivityIssueBinding mBinding;

    public static final String EXTRA_ISSUE = "award.viewarchitectures.activities.IssueActivity.EXTRA_ISSUE";

    public static Intent getStartIntent(Context context, GithubIssue issue) {
        Intent intent = new Intent(context, IssueActivity.class);
        intent.putExtra(EXTRA_ISSUE, issue);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mGithubIssue = getIntent().getParcelableExtra(EXTRA_ISSUE);
        if (mGithubIssue == null) throw new IllegalArgumentException("CommentsActivity requires a GithubIssue object!");

        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_issue);
        mBinding.setViewModel(new GithubIssueViewModel(this,mGithubIssue));

        setupToolbar();
    }

    private void setupToolbar() {
        ActionBar actionBar = getSupportActionBar();
        if(actionBar != null) {
            String title = mGithubIssue.title;
            if (title != null) actionBar.setTitle(title);
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
    }
}
