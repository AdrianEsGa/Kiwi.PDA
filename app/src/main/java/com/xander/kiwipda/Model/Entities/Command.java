package com.xander.kiwipda.Model.Entities;

import android.graphics.drawable.Drawable;

import java.util.Date;

public class Command {
    private int _id;
    private int _employeeId;
    private int _tableId;
    private int _stationId;
    private Date _date;
    private CommandStatus _status;
    private Drawable _image;

    public Command(int id, int employeeId, int tableId, int stationId, Date date, int status ){
        _id = id;
        _employeeId = employeeId;
        _tableId = tableId;
        _stationId = stationId;
        _date = date;
        _status =  CommandStatus.values()[status];
    }

    public String GetName(){
        return "Comanda " + _id;
    }

    public String GetDescription(){
        return _status.toString();
    }

    public Drawable GetImage(){
        return _image;
    }

    public void SetImage(Drawable image){
        _image = image;
    }

    public String toString(){
        return "Comanda " + _id;
    }
}

