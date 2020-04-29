package it.polito.tdp.poweroutages.model;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import it.polito.tdp.poweroutages.DAO.PowerOutageDAO;

public class Model {
	
	PowerOutageDAO podao;
	
	public Model() {
		podao = new PowerOutageDAO();
	}
	
	public List<Nerc> getNercList() {
		return podao.getNercList();
	}
	
		List<PowerOutage> worst =new LinkedList<PowerOutage>();
		int worstAffected=0;
		
	public List<PowerOutage> worstCase(Nerc nerc, int maxYears, int maxHours){
		List<PowerOutage> notUsed=new LinkedList<PowerOutage>(podao.getPowerOutagesByNerc(nerc).values());
		List<PowerOutage> outages=new LinkedList<PowerOutage>(notUsed);
		List<PowerOutage> parziale= new LinkedList<PowerOutage>();
		int livello=0;
		
		ricorsiva(parziale, outages, notUsed, livello, maxYears, maxHours);
		return worst;
	}

	private void ricorsiva(List<PowerOutage> parziale, List<PowerOutage> outages,List<PowerOutage> notUsed, int livello, int maxYears, int maxHours) {
		if (livello+getMin(notUsed)>maxHours)
		{
			worst.addAll(parziale);
			return;
		}
		
		for (PowerOutage p: outages)
		{
			parziale.add(p);
			notUsed.remove(p);
			ricorsiva(parziale, outages, notUsed, livello+p.getHourInterval(), maxYears, maxHours);
			parziale.remove(p);
			livello=livello-p.getHourInterval();
			notUsed.add(p);
		}
		
	}

	private int getMin(List<PowerOutage> notUsed) {
		int min=notUsed.get(0).getHourInterval();
		for (PowerOutage p: notUsed)
		{
			if (p.getHourInterval()<min)
			{
				min=p.getHourInterval();
			}
		}
		return min;
	}	
		
	}
