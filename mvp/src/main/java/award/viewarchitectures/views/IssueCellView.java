package award.viewarchitectures.views;

import android.text.Spannable;

public interface IssueCellView {

    void setIssueTitle(String title);

    void setBody(String body);

    void setIssueAuthor(Spannable spannable);

    void setIssueNumber(String issueNumber);

    void setCommentsVisibility(int visibilityState);

}
