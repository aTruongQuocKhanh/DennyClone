package jp.co.goga.dennysclone;

import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.app.AlertDialog;

/**
 * Created by khanhtq on 3/8/16.
 */
public class DialogManager {

    private static DialogManager mInstance;
    public static DialogManager getInstance() {
        if (mInstance == null) {
            mInstance = new DialogManager();
        }
        return mInstance;
    }

    public AlertDialog createNoticeDialog(Activity activity, String title, String message,
                                     String positiveName,
                                     DialogInterface.OnClickListener positiveClick,
                                     String negativeName,
                                     DialogInterface.OnClickListener negativeClick) {
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        builder.setMessage(message)
                .setTitle(title)
                .setPositiveButton(positiveName, positiveClick)
                .setNegativeButton(negativeName, negativeClick);
        AlertDialog dialog = builder.create();
        return dialog;
    }

    public Dialog createSingleDialog() {
        return null;
    }
}
