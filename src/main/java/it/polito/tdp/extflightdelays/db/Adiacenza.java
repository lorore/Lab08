package it.polito.tdp.extflightdelays.db;

public class Adiacenza {

	int idPartenza;
	int idArrivo;
	double media;
	public Adiacenza(int idPartenza, int idArrivo, double media) {
		super();
		this.idPartenza = idPartenza;
		this.idArrivo = idArrivo;
		this.media = media;
	}
	public int getIdPartenza() {
		return idPartenza;
	}
	public int getIdArrivo() {
		return idArrivo;
	}
	public double getMedia() {
		return media;
	}
	
	

}
