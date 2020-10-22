package mz.co.sidy.pvn.model;

import java.io.Serializable;
import java.util.UUID;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by Sidónio Goenha on 03/02/2017.
 */

public class Marca extends RealmObject implements Serializable {
    @PrimaryKey
    private String id;
    private String codMarca;
    private String descMarca;
    private RealmList<Modelo> modelos;

    public Marca() {
        this.id = UUID.randomUUID().toString();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCodMarca() {
        return codMarca;
    }

    public void setCodMarca(String codMarca) {
        this.codMarca = codMarca;
    }

    public String getDescMarca() {
        return descMarca;
    }

    public void setDescMarca(String descMarca) {
        this.descMarca = descMarca;
    }

    public RealmList<Modelo> getModelos() {
        return modelos;
    }

    public void setModelos(RealmList<Modelo> modelos) {
        this.modelos = modelos;
    }

    @Override
    public String toString() {
        return descMarca;
    }
}
