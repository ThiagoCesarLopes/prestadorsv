package br.com.appanunciobairro.bairroanuncio.Model;

public class CidadeModel {
    private String desc_cidade;
    private int cidade_id;

    public String getName() {
        return desc_cidade;
    }

    public void setName(String desc_cidade) {
        this.desc_cidade = desc_cidade;
    }

    public int getId() {
        return cidade_id;
    }

    public void setId(int cidade_id) {
        this.cidade_id = cidade_id;
    }
}