package award.viewarchitectures.presenter.interfaces;

import android.content.Context;

import award.viewarchitectures.views.IssuePageView;

/**
 * Created by adamward on 2/1/16.
 */
public interface IssuePagePresenter extends Presenter<IssuePageView> {

    void loadIssues(Context context);

}
