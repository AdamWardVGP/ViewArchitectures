package award.viewarchitectures.fragments;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

import java.util.ArrayList;
import java.util.List;

import award.viewarchitectures.R;
import award.viewarchitectures.ViewArchitecturesApplication;
import award.viewarchitectures.adapters.GithubIssueAdapter;
import award.viewarchitectures.data.RequestManager;
import award.viewarchitectures.models.GithubIssue;
import award.viewarchitectures.util.DataUtils;
import butterknife.Bind;
import butterknife.ButterKnife;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.subscriptions.CompositeSubscription;


public class GithubIssuesFragment extends BaseFragment implements SwipeRefreshLayout.OnRefreshListener {

    @Bind(R.id.swipe_container)
    SwipeRefreshLayout mSwipeRefreshLayout;

    @Bind(R.id.recycler_issues)
    RecyclerView mRecyclerView;

    @Bind(R.id.layout_offline)
    LinearLayout mOfflineContainer;

    @Bind(R.id.progress_indicator)
    ProgressBar mProgressBar;

    @Bind(R.id.toolbar)
    Toolbar mToolbar;

    private RequestManager mRequestManager;
    private GithubIssueAdapter mIssueAdapter;
    private CompositeSubscription mSubscriptions;
    private List<GithubIssue> mIssues;

    public static GithubIssuesFragment newInstance() {
        GithubIssuesFragment issuesFragment = new GithubIssuesFragment();
        return issuesFragment;
    }

    public GithubIssuesFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mSubscriptions = new CompositeSubscription();
        mIssues = new ArrayList<>();
        mRequestManager = ViewArchitecturesApplication.getRequestManager(getActivity());
        mIssueAdapter = new GithubIssueAdapter(getActivity());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View fragmentView = inflater.inflate(R.layout.fragment_github_issues, container, false);
        ButterKnife.bind(this, fragmentView);
        mSwipeRefreshLayout.setOnRefreshListener(this);
        mSwipeRefreshLayout.setColorSchemeResources(R.color.va_blue);
        setupToolbar();
        setupRecyclerView();
        loadStoriesIfNetworkConnected();
        return fragmentView;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mSubscriptions.unsubscribe();
    }

    @Override
    public void onRefresh() {
        mSubscriptions.unsubscribe();
        if (mIssueAdapter != null)
            mIssueAdapter.setItems(new ArrayList<GithubIssue>());
        getIssues();
    }

    private void setupToolbar() {
        ((AppCompatActivity) getActivity()).setSupportActionBar(mToolbar);
        ActionBar actionBar = ((AppCompatActivity) getActivity()).getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayShowTitleEnabled(true);
        }
    }

    private void setupRecyclerView() {
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecyclerView.setHasFixedSize(true);
        mIssueAdapter.setItems(mIssues);
        mRecyclerView.setAdapter(mIssueAdapter);
    }

    private void loadStoriesIfNetworkConnected() {
        if (DataUtils.isNetworkAvailable(getActivity())) {
            showOfflineLayout(false);
            getIssues();
        } else {
            showOfflineLayout(true);
        }
    }

    private void hideLoadingViews() {
        mProgressBar.setVisibility(View.GONE);
        mSwipeRefreshLayout.setRefreshing(false);
    }

    private void showOfflineLayout(boolean isOffline) {
        mOfflineContainer.setVisibility(isOffline ? View.VISIBLE : View.GONE);
        mRecyclerView.setVisibility(isOffline ? View.GONE : View.VISIBLE);
        mProgressBar.setVisibility(isOffline ? View.GONE : View.VISIBLE);
    }

    private void getIssues() {
        mSubscriptions.add(mRequestManager.getIssues(
                getResources().getString(R.string.repo_owner), getResources().getString(R.string.repo_name), "")
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(mRequestManager.getScheduler())
                .subscribe(new Subscriber<List<GithubIssue>>() {
                    @Override
                    public void onCompleted() {
                    }

                    @Override
                    public void onError(Throwable e) {
                        hideLoadingViews();
                        Log.e("MVVMGIF", "There was a problem loading the issues list " + e);
                        e.printStackTrace();
                    }

                    @Override
                    public void onNext(List<GithubIssue> issues) {
                        hideLoadingViews();
                        mIssues.clear();
                        mIssues.addAll(issues);
                        mIssueAdapter.notifyDataSetChanged();
                    }
                }));
    }
}
