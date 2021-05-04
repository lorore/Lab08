package it.polito.tdp.extflightdelays.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import it.polito.tdp.extflightdelays.model.Airport;

public class DAO {
	
	public void getAllAirports(Map<Integer, Airport> idMap) {
		String sql = "SELECT * FROM airports";
		

		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			ResultSet rs = st.executeQuery();

			while (rs.next()) {
				Airport airport = new Airport(rs.getInt("ID"), rs.getString("IATA_CODE"), rs.getString("AIRPORT"),
						rs.getString("CITY"), rs.getString("STATE"), rs.getString("COUNTRY"), rs.getDouble("LATITUDE"),
						rs.getDouble("LONGITUDE"), rs.getDouble("TIMEZONE_OFFSET"));
				idMap.put(rs.getInt("ID"), airport);
			}

			conn.close();
			

		} catch (SQLException e) {
			e.printStackTrace();
		//	System.out.println("Errore connessione al database");
			throw new RuntimeException("Error Connection Database");
		}
		
		
	}

	public List<Adiacenza> getConnessioni(double d){
	String sql="SELECT f.ORIGIN_AIRPORT_ID, f.DESTINATION_AIRPORT_ID, AVG(f.DISTANCE) AS media "
			+ "FROM flights AS f "
			+ "GROUP BY  f.ORIGIN_AIRPORT_ID, f.DESTINATION_AIRPORT_ID ";
	List<Adiacenza> result=new ArrayList<Adiacenza>();
	try {
		Connection conn = ConnectDB.getConnection();
		PreparedStatement st = conn.prepareStatement(sql);
		ResultSet rs = st.executeQuery();

		while (rs.next()) {
			Adiacenza a=new Adiacenza(rs.getInt("ORIGIN_AIRPORT_ID"), rs.getInt("DESTINATION_AIRPORT_ID"), rs.getDouble("media"));
			result.add(a);
		}

		conn.close();
		return result;

	} catch (SQLException e) {
		e.printStackTrace();
	//	System.out.println("Errore connessione al database");
		throw new RuntimeException("Error Connection Database");
	}
	
	
	}
	 
	
}
