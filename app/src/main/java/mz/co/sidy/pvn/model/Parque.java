package mz.co.sidy.pvn.model;

import java.io.Serializable;
import java.util.UUID;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by Sidónio Goenha on 03/02/2017.
 */

public class Parque extends RealmObject implements Serializable {
    @PrimaryKey
    private String id;
    private String nome;
    private RealmList<Viatura> viaturas;
    private RealmList<Endereco> enderecos;
    private RealmList<Compra> compras;
    private RealmList<Coordenada> coordenadas;

    public Parque() {
        this.id = UUID.randomUUID().toString();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public RealmList<Viatura> getViaturas() {
        return viaturas;
    }

    public void setViaturas(RealmList<Viatura> viaturas) {
        this.viaturas = viaturas;
    }

    public RealmList<Endereco> getEnderecos() {
        return enderecos;
    }

    public void setEnderecos(RealmList<Endereco> enderecos) {
        this.enderecos = enderecos;
    }

    public RealmList<Compra> getCompras() {
        return compras;
    }

    public void setCompras(RealmList<Compra> compras) {
        this.compras = compras;
    }

    public RealmList<Coordenada> getCoordenadas() {
        return coordenadas;
    }

    public void setCoordenadas(RealmList<Coordenada> coordenadas) {
        this.coordenadas = coordenadas;
    }

    @Override
    public String toString() {
        return nome;
    }
}
