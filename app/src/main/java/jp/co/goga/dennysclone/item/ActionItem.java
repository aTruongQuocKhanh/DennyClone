package jp.co.goga.dennysclone.item;

import android.widget.AdapterView;

/**
 * Created by khanhtq on 3/8/16.
 */
public class ActionItem {
    private String actionName;
    private Action action;

    public ActionItem(String name, Action listener) {
        actionName = name;
        action = listener;
    }

    public String getActionName() {
        return actionName;
    }

    public Action getAction() {
        return action;
    }


    public interface Action {
        void doAction();
    }
}
