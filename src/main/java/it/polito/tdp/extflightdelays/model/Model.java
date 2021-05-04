package it.polito.tdp.extflightdelays.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jgrapht.Graph;
import org.jgrapht.Graphs;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleWeightedGraph;

import it.polito.tdp.extflightdelays.db.Adiacenza;
import it.polito.tdp.extflightdelays.db.DAO;

public class Model {
	
	private DAO dao;
	private Graph<Airport, DefaultWeightedEdge> grafo;
	private Map<Integer, Airport> idMap;
	
	public Model() {
		dao=new DAO();
		idMap=new HashMap<>();
	}
	
	
	public void creaGrafo(int x) {
		this.grafo=new SimpleWeightedGraph<>(DefaultWeightedEdge.class);
		dao.getAllAirports(idMap);
		Graphs.addAllVertices(grafo, idMap.values());
		List<Adiacenza> relazioni=dao.getConnessioni(0);
		for(Adiacenza ad: relazioni) {
			Airport a=idMap.get(ad.getIdPartenza());
			Airport b=idMap.get(ad.getIdArrivo());
			DefaultWeightedEdge e=grafo.getEdge(a, b);
			if(e!=null) {
				
				double d1=grafo.getEdgeWeight(e);
				double d2=ad.getMedia();
				double peso=d1;
				if(d1!=d2) {
			
					peso=calcolaMedia(d1,d2);
				}
				
				if(peso<=x) {
					//c'era, ma va rimosso
					grafo.removeEdge(e);
				}
				else {
					grafo.setEdgeWeight(e, peso);
				}
				
			}else {
				Graphs.addEdge(this.grafo, a, b, ad.getMedia());
			}
			
		}
	
		System.out.println(grafo.edgeSet().size());
		
		System.out.println(grafo.vertexSet().size());
	}
	
	private double calcolaMedia(double d1,double d2) {
		return ((d1+d2)/2);
	}
	
}
