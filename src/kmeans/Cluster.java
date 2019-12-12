package kmeans;

import java.util.ArrayList;
import java.util.List;

public class Cluster {
    private List<Ponto> pontos = new ArrayList<Ponto>();
    private Ponto centroide;
    private boolean termino = false;

    public Ponto getCentroide() {
	return centroide;
    }

    public void setCentroide(Ponto centroide) {
	this.centroide = centroide;
    }

    public List<Ponto> getPontos() {
	return pontos;
    }

    public boolean isTermino() {
	return termino;
    }

    public void setTermino(boolean termino) {
	this.termino = termino;
    }

    public void limparPontos() {
	pontos.clear();
    }

    @Override
    public String toString() {
	return centroide.toString();
    }
}
