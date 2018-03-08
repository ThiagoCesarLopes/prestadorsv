package br.com.appanunciobairro.bairroanuncio;

public class ListModel {

        public String img; //Image URL
        public String name; //Name
        public String last_name; //last_Name

     public ListModel(String name, String img, String last_name)
        {
            this.img = img;
            this.name = name;
            this.name = last_name;
        }

    public String getImg() {return img;}

    public String getName() { return name; }
    public String getLastName() { return last_name;}
}

