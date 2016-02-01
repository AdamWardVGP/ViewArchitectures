package award.viewarchitectures.fragments;

import android.databinding.DataBindingUtil;
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

import java.util.ArrayList;
import java.util.List;

import award.viewarchitectures.R;
import award.viewarchitectures.ViewArchitecturesApplication;
import award.viewarchitectures.adapters.GithubIssueAdapter;
import award.viewarchitectures.data.RequestManager;
import award.viewarchitectures.databinding.FragmentGithubIssuesBinding;
import award.viewarchitectures.models.GithubIssue;
import award.viewarchitectures.viewModel.GithubIssuePageViewModel;
import award.viewarchitectures.viewModel.GithubIssuePageViewModel.IssuePageVMListener;
import butterknife.Bind;
import butterknife.ButterKnife;


public class GithubIssuesFragment extends BaseFragment implements SwipeRefreshLayout.OnRefreshListener, IssuePageVMListener {

    @Bind(R.id.swipe_container)
    SwipeRefreshLayout mSwipeRefreshLayout;

    @Bind(R.id.recycler_issues)
    RecyclerView mRecyclerView;

    @Bind(R.id.toolbar)
    Toolbar mToolbar;

    private GithubIssuePageViewModel mViewModel;
    private
    GithubIssueAdapter mAdapter;

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
        mViewModel = new GithubIssuePageViewModel(getContext(),this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        FragmentGithubIssuesBinding binding = DataBindingUtil.inflate(inflater, R.layout.fragment_github_issues, container, false);

        ButterKnife.bind(this, binding.getRoot());

        setupToolbar();
        setupRecyclerView();

        mSwipeRefreshLayout.setOnRefreshListener(this);
        mSwipeRefreshLayout.setColorSchemeResources(R.color.va_blue);


        binding.setViewModel(mViewModel);
        mViewModel.requestIssues();


        return binding.getRoot();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onRefresh() {
        mViewModel.requestIssues();
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
        mAdapter = new GithubIssueAdapter(getActivity());
        mRecyclerView.setAdapter(mAdapter);
    }

    @Override
    public void onRepositoriesChanged(List<GithubIssue> issues) {
        mAdapter.setItems(issues);
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void onFinishRefreshing() {
        mSwipeRefreshLayout.setRefreshing(false);
    }
}
