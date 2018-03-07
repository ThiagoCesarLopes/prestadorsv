package br.com.appanunciobairro.bairroanuncio;

public class ListModel {

    private String name ="";
    private String last_name="";

    public ListModel(String name, String last_name){

        this.name = name;
        this.last_name = last_name;
    }

    public String getName() {
        return this.name;
    }

    public String getLast_name() {
        return last_name;
    }
}