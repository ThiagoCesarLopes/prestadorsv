package br.com.appanunciobairro.bairroanuncio.Model;

import java.sql.Date;

public class ServiceModel {


    private int id_Service;
    private int nIdUF;
    private int nIdCidade;
    private int nIdBairro;
    private int nIdUrgencia;
    private String TituloServico;
    private String DescricaoServico;


    public int getId_Service() {
        return id_Service;
    }

    public void setId_Service(int id_Service) {
        this.id_Service = id_Service;
    }

    public int getnIdUF() {
        return nIdUF;
    }

    public void setnIdUF(int nIdUF) {
        this.nIdUF = nIdUF;
    }

    public int getnIdCidade() {
        return nIdCidade;
    }

    public void setnIdCidade(int nIdCidade) {
        this.nIdCidade = nIdCidade;
    }

    public int getnIdBairro() {
        return nIdBairro;
    }

    public void setnIdBairro(int nIdBairro) {
        this.nIdBairro = nIdBairro;
    }

    public int getnIdUrgencia() {
        return nIdUrgencia;
    }

    public void setnIdUrgencia(int nIdUrgencia) {
        this.nIdUrgencia = nIdUrgencia;
    }

    public String getTituloServico() {
        return TituloServico;
    }

    public void setTituloServico(String tituloServico) {
        TituloServico = tituloServico;
    }

    public String getDescricaoServico() {
        return DescricaoServico;
    }

    public void setDescricaoServico(String descricaoServico) {
        DescricaoServico = descricaoServico;
    }
}
