package com.example.konarr.contactosv3.util;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Checkable;
import android.widget.RelativeLayout;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by konarr on 30-09-14.
 */
public class CheckableRelativeLayaout extends RelativeLayout implements Checkable {
    private boolean is_checked;
    private List<Checkable> checkable_views;

    public CheckableRelativeLayaout(Context context) {
        super(context);
        inicializar(null);
    }

    public CheckableRelativeLayaout(Context context, AttributeSet attrs) {
        super(context, attrs);
        inicializar(attrs);
    }

    public CheckableRelativeLayaout(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        inicializar(attrs);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        final int childCount = this.getChildCount();
        for (int i=0; i < childCount; i++)
            buscarComponentesChekeable(this.getChildAt(i));
    }

    private void buscarComponentesChekeable(View view) {
        if (view instanceof Checkable) this.checkable_views.add((Checkable) view);
        if (view instanceof ViewGroup){
            final ViewGroup vg = (ViewGroup) view;
            final int child_count = vg.getChildCount();
            for (int i=0; i<child_count; i++)
                buscarComponentesChekeable(vg.getChildAt(i));
        }
    }

    private void inicializar(AttributeSet attrs) {
        this.is_checked = false;
        this.checkable_views = new ArrayList<Checkable>();
    }

    //<editor-fold desc="Metodos chekeables">
    @Override
    public void setChecked(boolean is_checked) {
        this.is_checked = is_checked;
        for (Checkable c: checkable_views) c.setChecked(is_checked);
    }

    @Override
    public boolean isChecked() {
        return is_checked;
    }

    @Override
    public void toggle() {
        //this.is_checked = this.is_checked;
        for (Checkable c: checkable_views) c.toggle();
    }
    //</editor-fold>


}
