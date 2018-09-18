package com.mahavira.partnersms.inventory.presentation;

import android.databinding.BindingAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import java.util.List;

/**
 * Created by norman on 18/09/18.
 */

public class ProductBindingAdapter {

    @BindingAdapter("components")
    public static void setComponents(LinearLayout layout, List<String> components) {
        if(components != null && components.size() != 0) {
            for (String component : components) {
                EditText et = new EditText(layout.getContext());
                et.setText(component);
                layout.addView(et);
            }
        }
    }
}
