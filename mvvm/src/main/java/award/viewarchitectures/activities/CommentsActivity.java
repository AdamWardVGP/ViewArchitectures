package award.viewarchitectures.activities;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

import java.util.ArrayList;
import java.util.List;

import award.viewarchitectures.R;
import award.viewarchitectures.ViewArchitecturesApplication;
import award.viewarchitectures.adapters.GithubCommentAdapter;
import award.viewarchitectures.data.RequestManager;
import award.viewarchitectures.models.GithubComment;
import award.viewarchitectures.models.GithubIssue;
import award.viewarchitectures.util.DataUtils;
import award.viewarchitectures.util.DialogFactory;
import butterknife.Bind;
import butterknife.ButterKnife;
import rx.Observer;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.subscriptions.CompositeSubscription;

public class CommentsActivity extends BaseActivity {

    @Bind(R.id.recycler_comments)
    RecyclerView mRecyclerView;

    @Bind(R.id.layout_offline)
    LinearLayout mOfflineContainer;

    @Bind(R.id.progress_indicator)
    ProgressBar mProgressBar;

    @Bind(R.id.toolbar)
    Toolbar mToolbar;

    public static final String EXTRA_ISSUE = "award.viewarchitectures.activities.CommentsActivity.EXTRA_ISSUE";

    private RequestManager mRequestManager;
    private Subscription mSubscription;
    private List<GithubComment> mComments;
    private GithubCommentAdapter mCommentAdapter;
    private GithubIssue mIssue;

    public static Intent getStartIntent(Context context, GithubIssue issue) {
        Intent intent = new Intent(context, CommentsActivity.class);
        intent.putExtra(EXTRA_ISSUE, issue);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comments);
        ButterKnife.bind(this);

        mIssue = getIntent().getParcelableExtra(EXTRA_ISSUE);
        if (mIssue == null) throw new IllegalArgumentException("CommentsActivity requires a GithubIssue object!");

        mRequestManager = ViewArchitecturesApplication.getRequestManager(this);
        mSubscription = new CompositeSubscription();
        mComments = new ArrayList<>();

        setupToolbar();
        setupRecyclerView();
        loadCommentsIfOnline();
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        mSubscription.unsubscribe();
    }

    private void setupRecyclerView() {
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mCommentAdapter = new GithubCommentAdapter(this, mComments);
        mRecyclerView.setAdapter(mCommentAdapter);
    }

    private void setupToolbar() {
        setSupportActionBar(mToolbar);
        ActionBar actionBar = getSupportActionBar();
        if(actionBar != null) {
            String title = mIssue.title;
            if (title != null) actionBar.setTitle(title);
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
    }

    private void loadCommentsIfOnline() {
        if (DataUtils.isNetworkAvailable(this)) {
            showOfflineLayout(false);
            getComments();
        } else {
            showOfflineLayout(true);
        }
    }

    private void getComments() {
        mSubscription = mRequestManager.getIssueComments(
                getResources().getString(R.string.repo_owner),
                getResources().getString(R.string.repo_name),
                String.valueOf(mIssue.number))
                .subscribeOn(mRequestManager.getScheduler())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<List<GithubComment>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        hideLoadingViews();
                        DialogFactory.createSimpleOkErrorDialog(
                                CommentsActivity.this,
                                getString(R.string.error_comments)
                        ).show();
                        Log.e("MVVMGIF", "There was a problem loading the issues list " + e);
                        e.printStackTrace();
                    }

                    @Override
                    public void onNext(List<GithubComment> githubComments) {
                        hideLoadingViews();
                        mComments.clear();
                        mComments.addAll(githubComments);
                        mCommentAdapter.notifyDataSetChanged();
                    }
                });
    }

    private void hideLoadingViews() {
        mProgressBar.setVisibility(View.GONE);
    }

    private void showOfflineLayout(boolean isOffline) {
        mOfflineContainer.setVisibility(isOffline ? View.VISIBLE : View.GONE);
        mRecyclerView.setVisibility(isOffline ? View.GONE : View.VISIBLE);
        mProgressBar.setVisibility(isOffline ? View.GONE : View.VISIBLE);
    }

}
