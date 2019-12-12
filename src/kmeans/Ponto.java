package kmeans;

import java.util.ArrayList;
import java.util.List;

public class Ponto {
    private Float[] data;

    public Ponto(String[] strings) {
	super();
	List<Float> pontos = new ArrayList<Float>();
	for (String string : strings) {
	    pontos.add(Float.parseFloat(string));
	}
	this.data = pontos.toArray(new Float[strings.length]);
    }

    public Ponto(Float[] data) {
	this.data = data;
    }

    public float get(int dimension) {
	return data[dimension];
    }

    public int getTamanho() {
	return data.length;
    }

    @Override
    public String toString() {
	StringBuilder sb = new StringBuilder();
	sb.append(data[0]);
	for (int i = 1; i < data.length; i++) {
	    sb.append(", ");
	    sb.append(data[i]);
	}
	return sb.toString();
    }

    public Double distanciaEuclidiana(Ponto destino) {
	Double d = 0d;
	for (int i = 0; i < data.length; i++) {
	    d += Math.pow(data[i] - destino.get(i), 2);
	}
	return Math.sqrt(d);
    }

    @Override
    public boolean equals(Object obj) {
	Ponto other = (Ponto) obj;
	for (int i = 0; i < data.length; i++) {
	    if (data[i] != other.get(i)) {
		return false;
	    }
	}
	return true;
    }
}