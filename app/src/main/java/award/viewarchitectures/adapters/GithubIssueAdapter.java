package award.viewarchitectures.adapters;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import award.viewarchitectures.R;
import award.viewarchitectures.databinding.GithubIssueBinding;
import award.viewarchitectures.models.GithubIssue;
import award.viewarchitectures.viewModel.GithubIssueViewModel;

public class GithubIssueAdapter extends RecyclerView.Adapter<GithubIssueAdapter.BindingHolder> {
    private List<GithubIssue> mIssues;
    private Context mContext;

    public GithubIssueAdapter(Context context) {
        mContext = context;
        mIssues = new ArrayList<>();
    }

    @Override
    public BindingHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        GithubIssueBinding issueBinding = DataBindingUtil.inflate(
                LayoutInflater.from(parent.getContext()),
                R.layout.github_issue,
                parent,
                false);
        return new BindingHolder(issueBinding);
    }

    @Override
    public void onBindViewHolder(BindingHolder holder, int position) {
        GithubIssueBinding postBinding = holder.binding;
        postBinding.setViewModel(new GithubIssueViewModel(mContext, mIssues.get(position)));
    }

    @Override
    public int getItemCount() {
        return mIssues.size();
    }

    public void setItems(List<GithubIssue> issues) {
        mIssues = issues;
        notifyDataSetChanged();
    }

    public static class BindingHolder extends RecyclerView.ViewHolder {
        private GithubIssueBinding binding;

        public BindingHolder(GithubIssueBinding binding) {
            super(binding.cardView);
            this.binding = binding;
        }
    }

}
