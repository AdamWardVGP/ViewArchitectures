package award.viewarchitectures.presenter.interfaces;

public interface Presenter<V> {

    void attachView(V view);

    void detachView();

}
