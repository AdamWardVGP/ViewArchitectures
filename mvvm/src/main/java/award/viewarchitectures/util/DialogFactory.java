package award.viewarchitectures.util;


import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;

import award.viewarchitectures.R;


public class DialogFactory {

    public static Dialog createSimpleOkErrorDialog(Context context, String message) {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(context)
                .setTitle(context.getString(R.string.dialog_error_title))
                .setMessage(message)
                .setNeutralButton(R.string.dialog_action_ok, null);
        return alertDialog.create();
    }

}
