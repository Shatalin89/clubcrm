package ru.shatalin89yandex.clubcrm;


import android.widget.ArrayAdapter;

import java.math.BigInteger;
import java.sql.*;



public class DataBaseWork {
    public String querystring;
    public ResultSet resquery;
    public String conres;
    Connection conn;
    public Long[] idlist;
    public ArrayAdapter<String> dbadapter;

    //Класс для подключения к БД постгре
    public void ConnectDB(String url, String username, String password) {

        conres="нажали для подключения";

        try {
            Class.forName("org.postgresql.Driver");
            conres="драйвер нашли";
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try {
            conn= DriverManager.getConnection(url, username, password);
            conres="connect!!";
        } catch (SQLException e) {
            e.printStackTrace();
            conres="чтот с сылью";
        }
    }
    //Класс для выборки данных из таблицы
    public void getData (String table) throws SQLException {
        String query = "SELECT * FROM "+table+" ORDER BY id";
        Statement stmt=conn.createStatement();
        resquery =stmt.executeQuery(query);
    }

    public void editData (String table, String id, String column, String column2, String data, String data2) throws SQLException {
        String query="UPDATE club."+table+" SET "+column+"='"+data+"', "+column2+"='"+data2+"' WHERE id="+id;
        Statement stmt=conn.createStatement();
        querystring=query;
        resquery=stmt.executeQuery(query);
        stmt.close();
    }

    public void getDataID (Long id, String table) throws SQLException{
        String query ="SELECT * FROM club."+table+" WHERE client.id="+id;
        Statement stmt=conn.createStatement();
        resquery=stmt.executeQuery(query);

    }

    public void delDataID (String id, String table) throws SQLException{
        String query="DELETE FROM club."+table+" WHERE id="+id;
        Statement stmt=conn.createStatement();
        resquery=stmt.executeQuery(query);
    }

    public void addData (String table, String arg1, String arg2) throws SQLException {
        String query="INSERT INTO club."+table+" (name, telephone) VALUES ('"+arg1+"', '"+arg2+"')";
        Statement stmt=conn.createStatement();
        resquery=stmt.executeQuery(query);
    }


    public Long getlastID(String table) throws SQLException {
        Long id = null;
        String query="SELECT max(id) FROM "+table;
        Statement stmt=conn.createStatement();
        resquery=stmt.executeQuery(query);
        while (resquery.next()){
            id=resquery.getLong(1);
        }
        resquery.close();
        return id;
    }



}
