package com.elnino.adm.appnote1;


import android.widget.EditText;

public class Presenter {
    private OnClick click;
    private EditText edUsername, edPassword, edRole;
    private GhiChuDao ghiChuDao;


    public Presenter(OnClick click) {
        this.click = click;
    }
    public void add(String title,String content){
        if (title.isEmpty()){
            click.errorTitle();
        }else if (content.isEmpty()){
            click.errorContenr();
        }else {
            click.onClick();
        }
    }
}
