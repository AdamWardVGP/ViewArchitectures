package award.viewarchitectures.fragments;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

import java.util.List;

import award.viewarchitectures.R;
import award.viewarchitectures.adapters.GithubIssueAdapter;
import award.viewarchitectures.models.GithubIssue;
import award.viewarchitectures.presenter.IssuePagePresenterImpl;
import award.viewarchitectures.util.DialogFactory;
import award.viewarchitectures.views.IssuePageView;
import butterknife.Bind;
import butterknife.ButterKnife;


public class GithubIssuesFragment extends BaseFragment implements SwipeRefreshLayout.OnRefreshListener, IssuePageView {

    @Bind(R.id.swipe_container) SwipeRefreshLayout mSwipeRefreshLayout;
    @Bind(R.id.recycler_issues) RecyclerView mRecyclerView;
    @Bind(R.id.layout_offline) LinearLayout mOfflineContainer;
    @Bind(R.id.progress_indicator) ProgressBar mProgressBar;
    @Bind(R.id.toolbar) Toolbar mToolbar;

    private IssuePagePresenterImpl mPresenter;

    private GithubIssueAdapter mIssueAdapter;

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
        mIssueAdapter = new GithubIssueAdapter(getContext());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View fragmentView = inflater.inflate(R.layout.fragment_github_issues, container, false);
        ButterKnife.bind(this, fragmentView);
        mSwipeRefreshLayout.setOnRefreshListener(this);
        mSwipeRefreshLayout.setColorSchemeResources(R.color.va_blue);

        setupToolbar();
        setupRecyclerView();

        mPresenter = new IssuePagePresenterImpl(getActivity());
        mPresenter.attachView(this);
        mPresenter.loadIssues(getContext());

        return fragmentView;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mPresenter.detachView();
    }

    @Override
    public void onRefresh() {
        mPresenter.loadIssues(getContext());
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
        mRecyclerView.setAdapter(mIssueAdapter);
    }

    @Override
    public void showIssues(List<GithubIssue> issues) {
        GithubIssueAdapter adapter = (GithubIssueAdapter) mRecyclerView.getAdapter();
        adapter.setItems(issues);
    }

    @Override
    public void showError() {
        DialogFactory.createSimpleOkErrorDialog(
                getActivity(),
                getString(R.string.error_issues)
        ).show();
    }

    @Override
    public void hideLoadingViews() {
        mProgressBar.setVisibility(View.GONE);
        mSwipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public void showOfflineLayout(boolean isOffline) {
        mOfflineContainer.setVisibility(isOffline ? View.VISIBLE : View.GONE);
        mRecyclerView.setVisibility(isOffline ? View.GONE : View.VISIBLE);
        mProgressBar.setVisibility(isOffline ? View.GONE : View.VISIBLE);
    }
}
