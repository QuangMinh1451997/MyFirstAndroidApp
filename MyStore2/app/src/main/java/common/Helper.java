package common;

import android.content.Context;
import android.support.v7.app.AlertDialog;

import com.example.mystore.R;

public class Helper {

    public static AlertDialog.Builder GetDialogBuilder(Context ctx, String title, String mess){
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(ctx, R.style.MyAlertDialogTheme)
                .setTitle(title)
                .setMessage(mess);
        return dialogBuilder;
    }
}
