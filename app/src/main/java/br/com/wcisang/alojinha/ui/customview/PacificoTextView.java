package br.com.wcisang.alojinha.ui.customview;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.view.Gravity;

/**
 * Created by WCisang on 07/06/2018.
 */
public class PacificoTextView extends android.support.v7.widget.AppCompatTextView {


    public PacificoTextView(Context context) {
        super(context);
        initView();
    }

    public PacificoTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    public PacificoTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }

    private void initView() {
        setGravity(Gravity.CENTER);
        formatText();
    }

    private void formatText(){
        setTypeface(Typeface.createFromAsset(getContext().getAssets(),"Pacifico-Regular.ttf"));
    }
}
