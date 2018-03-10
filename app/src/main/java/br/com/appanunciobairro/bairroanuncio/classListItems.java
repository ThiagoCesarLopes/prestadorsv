package br.com.appanunciobairro.bairroanuncio;

public class classListItems {
    public String img; //Image URL
    public String name; //Name
    public String last_name; //last_Name

    public classListItems(String name,String last_name,String url_picture)
    {
        this.name = name;
        this.last_name = last_name;
        this.img = url_picture;
    }

    public String getImg() {return img;}

    public String getName() { return name; }
    public String getLastName() { return last_name;}
}
