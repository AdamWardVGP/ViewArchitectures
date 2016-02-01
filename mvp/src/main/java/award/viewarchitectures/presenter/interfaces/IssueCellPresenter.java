package award.viewarchitectures.presenter.interfaces;

import award.viewarchitectures.views.IssueCellView;

/**
 * Created by adamward on 2/1/16.
 */
public interface IssueCellPresenter extends Presenter<IssueCellView> {

    void populateView();
}
