package mz.co.sidy.pvn.model;

import java.io.Serializable;
import java.util.UUID;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by Sidónio Goenha on 03/02/2017.
 */

public class Modelo extends RealmObject implements Serializable {
    @PrimaryKey
    private String id;
    private String codModelo;
    private String descModelo;
    private Marca marca;
    private RealmList<Viatura> viaturas;

    public Modelo() {
        this.id = UUID.randomUUID().toString();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCodModelo() {
        return codModelo;
    }

    public void setCodModelo(String codModelo) {
        this.codModelo = codModelo;
    }

    public String getDescModelo() {
        return descModelo;
    }

    public void setDescModelo(String descModelo) {
        this.descModelo = descModelo;
    }

    public Marca getMarca() {
        return marca;
    }

    public void setMarca(Marca marca) {
        this.marca = marca;
    }

    public RealmList<Viatura> getViaturas() {
        return viaturas;
    }

    public void setViaturas(RealmList<Viatura> viaturas) {
        this.viaturas = viaturas;
    }

    @Override
    public String toString() {
        return descModelo;
    }
}
