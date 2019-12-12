package kmeans;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class KMeans {
    public KMeansResultado calcular(List<Ponto> pontos, Integer k) {
	List<Cluster> clusters = elegerCentroides(pontos, k);

	while (!finalizo(clusters)) {
	    prepararClusters(clusters);
	    assinarPontos(pontos, clusters);
	    recalcularCentroides(clusters);
	}

	Double ofv = calcularFuncaoObjetivo(clusters);

	return new KMeansResultado(clusters, ofv);
    }

    private void recalcularCentroides(List<Cluster> clusters) {
	for (Cluster c : clusters) {
	    if (c.getPontos().isEmpty()) {
		c.setTermino(true);
		continue;
	    }

	    Float[] d = new Float[c.getPontos().get(0).getTamanho()];
	    Arrays.fill(d, 0f);
	    for (Ponto p : c.getPontos()) {
		for (int i = 0; i < p.getTamanho(); i++) {
		    d[i] += (p.get(i) / c.getPontos().size());
		}
	    }

	    Ponto novoCentroide = new Ponto(d);

	    if (novoCentroide.equals(c.getCentroide())) {
		c.setTermino(true);
	    } else {
		c.setCentroide(novoCentroide);
	    }
	}
    }

    private void assinarPontos(List<Ponto> pontos, List<Cluster> clusters) {
	for (Ponto ponto : pontos) {
	    Cluster maisPerto = clusters.get(0);
	    Double distanciaMinima = Double.MAX_VALUE;
	    for (Cluster cluster : clusters) {
		Double distancia = ponto.distanciaEuclidiana(cluster
			.getCentroide());
		if (distanciaMinima > distancia) {
		    distanciaMinima = distancia;
		    maisPerto = cluster;
		}
	    }
	    maisPerto.getPontos().add(ponto);
	}
    }

    private void prepararClusters(List<Cluster> clusters) {
	for (Cluster c : clusters) {
	    c.limparPontos();
	}
    }

    private Double calcularFuncaoObjetivo(List<Cluster> clusters) {
	Double ofv = 0d;

	for (Cluster cluster : clusters) {
	    for (Ponto ponto : cluster.getPontos()) {
		ofv += ponto.distanciaEuclidiana(cluster.getCentroide());
	    }
	}

	return ofv;
	
    }

    private boolean finalizo(List<Cluster> clusters) {
	for (Cluster cluster : clusters) {
	    if (!cluster.isTermino()) {
		return false;
	    }
	}
	return true;
    }

    private List<Cluster> elegerCentroides(List<Ponto> pontos, Integer k) {
	List<Cluster> centroides = new ArrayList<Cluster>();

	List<Float> maximos = new ArrayList<Float>();
	List<Float> minimos = new ArrayList<Float>();
	
	for (int i = 0; i < pontos.get(0).getTamanho(); i++) {
	    Float min = Float.POSITIVE_INFINITY, max = Float.NEGATIVE_INFINITY;

	    for (Ponto ponto : pontos) {
		min = min > ponto.get(i) ? ponto.get(0) : min;
		max = max < ponto.get(i) ? ponto.get(i) : max;
	    }

	    maximos.add(max);
	    minimos.add(min);
	}

	Random random = new Random();

	for (int i = 0; i < k; i++) {
	    Float[] data = new Float[pontos.get(0).getTamanho()];
	    Arrays.fill(data, 0f);
	    for (int d = 0; d < pontos.get(0).getTamanho(); d++) {
		data[d] = random.nextFloat()
			* (maximos.get(d) - minimos.get(d)) + minimos.get(d);
	    }

	    Cluster c = new Cluster();
	    Ponto centroide = new Ponto(data);
	    c.setCentroide(centroide);
	    centroides.add(c);
	}

	return centroides;
	
    }
}

