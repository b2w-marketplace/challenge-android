package b2w.com.br.olodjinha.util.UIFeedback;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;

import b2w.com.br.olodjinha.R;

public class UIFeedback {

    public static AlertDialog getAlertDialog(Context context, String message,
                                             DialogInterface.OnClickListener listener) {
        AlertDialog alertDialog = new AlertDialog.Builder(context)
                .setMessage(R.string.product_detail_reserved_successfully)
                .setPositiveButton(R.string.product_detail_ok, (dialogInterface, i) -> {
                    listener.onClick(dialogInterface, i);
                }).create();

        return alertDialog;
    }
}
