package br.com.appanunciobairro.bairroanuncio.Model;

import java.sql.Date;

public class UserModel {
    private int id;
    private String Nome;
    private String Sobrenome;
    private int nIdTipo;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return Nome;
    }

    public void setNome(String Nome) {
        this.Nome = Nome;
    }

    public String getSobrenome() {
        return Sobrenome;
    }

    public void setSobrenome(String Sobrenome) {
        this.Sobrenome = Sobrenome;
    }

    public int getnIdTipo() {
        return nIdTipo;
    }

    public void setnIdTipo(int nIdTipo) {
        this.nIdTipo = nIdTipo;
    }
}