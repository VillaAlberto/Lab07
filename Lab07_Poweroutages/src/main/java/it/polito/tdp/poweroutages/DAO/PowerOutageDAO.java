package it.polito.tdp.poweroutages.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import it.polito.tdp.poweroutages.model.Nerc;
import it.polito.tdp.poweroutages.model.PowerOutage;

public class PowerOutageDAO {
	
	public List<Nerc> getNercList() {

		String sql = "SELECT id, value FROM nerc";
		List<Nerc> nercList = new ArrayList<>();

		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			ResultSet res = st.executeQuery();

			while (res.next()) {
				Nerc n = new Nerc(res.getInt("id"), res.getString("value"));
				nercList.add(n);
			}

			conn.close();

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

		return nercList;
	}
	
	public Map<Integer, PowerOutage> getPowerOutagesByNerc(Nerc nerc) {

		String sql = "SELECT id, customers_affected, date_event_began, date_event_finished FROM poweroutages WHERE nerc_id=? ORDER BY date_event_began";
		Map <Integer, PowerOutage> outageMap = new TreeMap<Integer,PowerOutage>();

		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			st.setInt(1, nerc.getId());
			ResultSet res = st.executeQuery();

			while (res.next()) {
				PowerOutage p = new PowerOutage(res.getInt("id"), nerc, res.getTimestamp("date_event_began"), res.getTimestamp("date_event_finished"), res.getInt("customers_affected"));
				outageMap.put(p.getEventId(), p);
			}

			conn.close();

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

		return outageMap;
	}
	
}
