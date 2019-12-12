package kmeans;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import au.com.bytecode.opencsv.CSVReader;

public class Start {
    @SuppressWarnings("resource")
	public static void main(String[] args) throws IOException {
	CSVReader reader = new CSVReader(new FileReader("data.csv"));
	FileWriter writer = new FileWriter("out.csv");

	List<String[]> myEntries = reader.readAll();
	List<Ponto> pontos = new ArrayList<Ponto>();

	for (String[] strings : myEntries) {
	    Ponto p = new Ponto(strings);
	    pontos.add(p);
	}

	KMeans kmeans = new KMeans();
	for (int k = 1; k <= 2; k++) {
	    KMeansResultado resultado = kmeans.calcular(pontos, k);
	    writer.write("------- k=" + k + " ofv=" + resultado.getOfv()
		    + " -------\n");
	    int i = 0;
	    for (Cluster cluster : resultado.getClusters()) {
		i++;
		writer.write("-- Cluster " + i + " --\n");
		for (Ponto ponto : cluster.getPontos()) {
		    writer.write(ponto.toString() + "\n");
		}
		writer.write("\n");
		writer.write(cluster.getCentroide().toString());
		writer.write("\n\n");
	    }
	}
	System.out.println("Arquivo out.csv criado com sucesso!!!");
	writer.close();
    }
}