package br.com.wcisang.alojinha.ui.customview;

import android.content.Context;
import android.graphics.Paint;
import android.util.AttributeSet;

/**
 * Created by WCisang on 09/06/2018.
 */
public class StrikeTextView extends android.support.v7.widget.AppCompatTextView {

    public StrikeTextView(Context context) {
        super(context);
        initView();
    }

    public StrikeTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    public StrikeTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }

    private void initView() {
        setPaintFlags(getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
    }
}
