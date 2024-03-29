package com.xander.kiwipda.Model.Entities;

import android.graphics.drawable.Drawable;

import java.util.ArrayList;
import java.util.Date;

public class Command {
    private int _id;
    private int _employeeId;
    private int _tableId;
    private int _stationId;
    private Date _date;
    private CommandStatus _status;
    private Drawable _image;
    private ArrayList<CommandDetail> _details;

    public Command() {
        _details = new ArrayList<>();
    }

    public Command(int id, int employeeId, int tableId, int stationId, Date date, int status){
        _id = id;
        _employeeId = employeeId;
        _tableId = tableId;
        _stationId = stationId;
        _date = date;
        _status =  CommandStatus.values()[status];
        _details = new ArrayList<>();
    }

    public Command(int employeeId, int tableId){
        _employeeId = employeeId;
        _tableId = tableId;
        _details = new ArrayList<>();
    }

    public int GetId(){
        return _id;
    }

    public String GetName(){
        return "Comanda " + _id;
    }

    public String GetDescription(){
        return _status.toString();
    }

    public CommandStatus GetStatus(){
        return _status;
    }

    public void SetStatus(CommandStatus status){ _status = status; }

    public Drawable GetImage(){
        return _image;
    }

    public void SetImage(Drawable image){
        _image = image;
    }

    public ArrayList<CommandDetail> GetDetails(){
        return _details;
    }

    public int GetEmployeeId(){
        return _employeeId;
    }

    public int GetTableId(){
        return _tableId;
    }

    public String toString(){
        return "Comanda " + _id;
    }
}

