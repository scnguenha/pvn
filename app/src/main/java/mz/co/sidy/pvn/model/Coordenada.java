package mz.co.sidy.pvn.model;

import java.io.Serializable;
import java.util.UUID;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by Sidónio Goenha on 07/04/2017.
 */

public class Coordenada extends RealmObject implements Serializable {
    @PrimaryKey
    private String id;
    private Double latitude;
    private Double longitude;
    private float precisao;
    private int ordem;

    public Coordenada() {
        this.id = UUID.randomUUID().toString();
    }

    public Coordenada(Double latitude, Double longitude) {
        this.id = UUID.randomUUID().toString();
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public float getPrecisao() {
        return precisao;
    }

    public void setPrecisao(float precisao) {
        this.precisao = precisao;
    }

    public int getOrdem() {
        return ordem;
    }

    public void setOrdem(int ordem) {
        this.ordem = ordem;
    }
}
