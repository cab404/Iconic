package com.cab404.iconic.test;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.SeekBar;

import com.cab404.iconic.numbers.InterpolationUtils;
import com.cab404.iconic.numbers.VectorIcon;
import com.cab404.iconic.numbers.VectorIconInterpolator;
import com.cab404.iconic.ui.VectorIconDrawable;

/**
 * Well, sorry for no comments here!
 * Still you can send me your question to me@cab404.ru!
 * <p/>
 * Created at 08:22 on 03/06/15
 *
 * @author cab404
 */
public class TestActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test);
        VectorIconDrawable d = new VectorIconDrawable();

        final VectorIconInterpolator vii = new VectorIconInterpolator();
        vii.newInterpolation(
                new VectorIcon(this, R.xml.plus),
                new VectorIcon(this, R.xml.star),
                InterpolationUtils.IP_START_BUMP
        );

        VectorIcon icon = vii.interpolate(0f);
        d.setIcon(icon);

        ((SeekBar) findViewById(R.id.progress)).setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                vii.interpolate(progress / 1000f);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        ((ImageView) findViewById(R.id.icon)).setImageDrawable(d);
    }

}
