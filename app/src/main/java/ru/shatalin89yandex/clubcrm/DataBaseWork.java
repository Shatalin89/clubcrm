package ru.shatalin89yandex.clubcrm;


import java.sql.*;



public class DataBaseWork {
    public String querystring;
    public ResultSet resquery;
    public String conres;
    Connection conn;
    public int[] idlist;

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
        String query = "SELECT * FROM "+table;
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



    public void getDataID (int id, String table) throws SQLException{
        String query ="SELECT * FROM club."+table+" WHERE client.id="+id;
        Statement stmt=conn.createStatement();
        resquery=stmt.executeQuery(query);

    }



}
