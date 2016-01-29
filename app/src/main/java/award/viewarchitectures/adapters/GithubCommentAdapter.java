package award.viewarchitectures.adapters;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import award.viewarchitectures.R;
import award.viewarchitectures.databinding.GithubCommentBinding;
import award.viewarchitectures.models.GithubComment;
import award.viewarchitectures.viewModel.GithubCommentViewModel;

public class GithubCommentAdapter extends RecyclerView.Adapter<GithubCommentAdapter.BindingHolder> {

    private List<GithubComment> mComments;
    private Context mContext;

    public GithubCommentAdapter(Context context, List<GithubComment> comments) {
        mContext = context;
        mComments = comments;
    }

    @Override
    public BindingHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        GithubCommentBinding commentsBinding = DataBindingUtil.inflate(
                LayoutInflater.from(parent.getContext()),
                R.layout.github_comment,
                parent,
                false);

        return new BindingHolder(commentsBinding);
    }

    @Override
    public void onBindViewHolder(BindingHolder holder, int position) {
        GithubCommentBinding binding = holder.binding;
        binding.setViewModel(new GithubCommentViewModel(mContext,mComments.get(position)));
    }

    @Override
    public int getItemCount() {
        return mComments.size();
    }

    public static class BindingHolder extends RecyclerView.ViewHolder {
        private GithubCommentBinding binding;

        public BindingHolder(GithubCommentBinding binding) {
            super(binding.githubCommentRoot);
            this.binding = binding;
        }
    }
}
