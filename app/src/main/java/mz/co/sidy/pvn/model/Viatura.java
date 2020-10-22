package mz.co.sidy.pvn.model;

import java.io.Serializable;
import java.util.UUID;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by Sidónio Goenha on 11/28/2016.
 */
public class Viatura extends RealmObject implements Serializable {
    @PrimaryKey
    private String id;
    private String matricula;
    private String chassi;
    private double cilindrada;
    private long quilometragem;
    private int lotacao;
    private String transmissao;
    private int nrPortas;
    private String combustivel;
    private String conjuntoTransmissao;
    private Double preco;
    private String cor;
    private String anoPub;
    private String tipoCarroceira;
    private Modelo modelo;
    private Parque parque;
    private boolean favourite;
    private RealmList<Imagem> imagens;

    public Viatura() {
        this.id = UUID.randomUUID().toString();
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public String getTransmissao() {
        return transmissao;
    }

    public void setTransmissao(String transmissao) {
        this.transmissao = transmissao;
    }

    public int getNrPortas() {
        return nrPortas;
    }

    public void setNrPortas(int nrPortas) {
        this.nrPortas = nrPortas;
    }

    public String getCombustivel() {
        return combustivel;
    }

    public void setCombustivel(String combustivel) {
        this.combustivel = combustivel;
    }

    public double getCilindrada() {
        return cilindrada;
    }

    public void setCilindrada(double cilindrada) {
        this.cilindrada = cilindrada;
    }

    public String getChassi() {
        return chassi;
    }

    public void setChassi(String chassi) {
        this.chassi = chassi;
    }

    public int getLotacao() {
        return lotacao;
    }

    public void setLotacao(int lotacao) {
        this.lotacao = lotacao;
    }

    public String getConjuntoTransmissao() {
        return conjuntoTransmissao;
    }

    public void setConjuntoTransmissao(String conjuntoTransmissao) {
        this.conjuntoTransmissao = conjuntoTransmissao;
    }

    public RealmList<Imagem> getImagens() {
        return imagens;
    }

    public void setImagens(RealmList<Imagem> imagens) {
        this.imagens = imagens;
    }

    public String getCor() {
        return cor;
    }

    public void setCor(String cor) {
        this.cor = cor;
    }

    public Modelo getModelo() {
        return modelo;
    }

    public void setModelo(Modelo modelo) {
        this.modelo = modelo;
    }

    public Parque getParque() {
        return parque;
    }

    public void setParque(Parque parque) {
        this.parque = parque;
    }

    public String getAnoPub() {
        return anoPub;
    }

    public void setAnoPub(String anoPub) {
        this.anoPub = anoPub;
    }

    public Double getPreco() {
        return preco;
    }

    public void setPreco(Double preco) {
        this.preco = preco;
    }

    public long getQuilometragem() {
        return quilometragem;
    }

    public void setQuilometragem(long quilometragem) {
        this.quilometragem = quilometragem;
    }

    public String getTipoCarroceira() {
        return tipoCarroceira;
    }

    public void setTipoCarroceira(String tipoCarroceira) {
        this.tipoCarroceira = tipoCarroceira;
    }

    public boolean isFavourite() {
        return favourite;
    }

    public void setFavourite(boolean favourite) {
        this.favourite = favourite;
    }
}



