package mz.co.sidy.pvn.model;

import java.io.Serializable;
import java.util.UUID;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by Sidónio Goenha on 03/03/2017.
 */

public class Endereco extends RealmObject implements Serializable {

    @PrimaryKey
    private String id;
    private String provincia;
    private String bairro;
    private String nrParcela;
    private String av_rua;
    private Parque parque;
    private String telefone;
    private String email;
    private String celular;
    private Coordenada coordenada;

    public Endereco() {
        this.id = UUID.randomUUID().toString();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getProvincia() {
        return provincia;
    }

    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getNrParcela() {
        return nrParcela;
    }

    public void setNrParcela(String nrParcela) {
        this.nrParcela = nrParcela;
    }

    public String getAv_rua() {
        return av_rua;
    }

    public void setAv_rua(String av_rua) {
        this.av_rua = av_rua;
    }

    public Parque getParque() {
        return parque;
    }

    public void setParque(Parque parque) {
        this.parque = parque;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public Coordenada getCoordenada() {
        return coordenada;
    }

    public void setCoordenada(Coordenada coordenada) {
        this.coordenada = coordenada;
    }

    public String getEndereco() {
        return provincia + ", " + bairro + ", " + av_rua + ", " + nrParcela;
    }
}
