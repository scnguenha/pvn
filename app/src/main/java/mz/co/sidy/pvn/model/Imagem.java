package mz.co.sidy.pvn.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;
import java.util.UUID;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by Sidónio Goenha on 03/24/2017.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Imagem extends RealmObject implements Serializable {
    @PrimaryKey
    private String id;
    private String descImagem;
    private double size;
    private byte[] bs;

    public Imagem() {
        this.id = UUID.randomUUID().toString();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDescImagem() {
        return descImagem;
    }

    public void setDescImagem(String descImagem) {
        this.descImagem = descImagem;
    }

    public double getSize() {
        return size;
    }

    public void setSize(double size) {
        this.size = size;
    }

    public byte[] getBs() {
        return bs;
    }

    public void setBs(byte[] bs) {
        this.bs = bs;
    }


}
