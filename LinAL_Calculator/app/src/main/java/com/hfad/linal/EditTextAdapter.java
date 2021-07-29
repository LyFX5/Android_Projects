package com.hfad.linal;

import android.content.Context;
import android.text.InputType;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;

import java.util.List;

public class EditTextAdapter extends BaseAdapter {

    private Context context;
    private List<String> elements;

    public EditTextAdapter(Context context, List<String> editTexts) {
        this.context = context;
        this.elements = editTexts;
    }

    @Override
    public int getCount() {
        return elements.size();
    }

    @Override
    public Object getItem(int position) {
        return elements.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        EditText editText;

        if (convertView == null) {
            editText = new EditText(context);
            editText.setText(elements.get(position));
        } else {
            editText = (EditText) convertView;
        }
        editText.setId(position);
        //editText.setInputType(InputType.TYPE_CLASS_NUMBER);

        return editText;
    }
}
