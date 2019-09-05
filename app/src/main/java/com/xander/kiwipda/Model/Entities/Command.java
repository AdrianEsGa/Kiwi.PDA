package com.xander.kiwipda.Model.Entities;


import java.util.Date;

public class Command {
    private int _id;
    private int _employeeId;
    private int _tableId;
    private Date _date;
    private int Status;

    public String Name() {
        return "Pedido " + _id;
    }

    public Command(int id, int employeeId, int tableId, Date date, int status ){
        _id = id;
    }

    public String toString(){
        return "Pedido " + _id;
    }
}
