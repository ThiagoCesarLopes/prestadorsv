package br.com.appanunciobairro.bairroanuncio.Model;

public class ItensServiceModel {
    public String img; //Image URL
    public String name; //Name
    public String last_name; //last_Name
    public int score; //last_Name
    public String desc_bairro; //last_Name

    public ItensServiceModel(String name,String last_name,String url_picture,int score,String desc_bairro)

    {
        this.name = name;
        this.last_name = last_name;
        this.img = url_picture;
        this.score= score;
        this.desc_bairro= desc_bairro;
    }

    public String getImg() {return img;}

    public String getName() { return name; }

    public String getLastName() { return last_name;}

    public int getScore() { return score; }

    public String getDescBairro() { return desc_bairro; }


}
