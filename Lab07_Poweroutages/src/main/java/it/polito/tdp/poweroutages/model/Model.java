package it.polito.tdp.poweroutages.model;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import it.polito.tdp.poweroutages.DAO.PowerOutageDAO;

public class Model {

	PowerOutageDAO podao;
	
	private List<PowerOutage> worst;
	private int worstAffected;
	
	public Model() {
		podao = new PowerOutageDAO();
		worst = new LinkedList<PowerOutage>();
		worstAffected=0;
	}

	
	public int getWorstAffected() {
		return worstAffected;
	}


	public void setWorstAffected(int worstAffected) {
		this.worstAffected = worstAffected;
	}


	public List<Nerc> getNercList() {
		return podao.getNercList();
	}



	public List<PowerOutage> worstCase(Nerc nerc, int maxYears, int maxHours) {
		List<PowerOutage> outages= new LinkedList<PowerOutage>(podao.getPowerOutagesByNerc(nerc).values());
		List<PowerOutage> parziale= new LinkedList<PowerOutage>();
		worst= new LinkedList<PowerOutage>();
		worstAffected=0;
		ricorsiva (parziale, outages, maxHours, maxYears);
		
		return worst;
	}

	private void ricorsiva(List<PowerOutage> parziale, List<PowerOutage> outages, int maxHours, int maxYears) {
		int somma= sumAffected(parziale);
		if (somma>worstAffected)
		{
			worstAffected=somma;
			worst=new LinkedList<PowerOutage>(parziale);
		}
		
		for (PowerOutage p: outages)
		{
			if (!parziale.contains(p))
			{
				parziale.add(p);
				if (sumYears(parziale, maxYears)&&sumHours(parziale, maxHours)) {
					ricorsiva(parziale, outages, maxHours, maxYears);
				}
				parziale.remove(p);
			}
			
		}
		
	}

	private boolean sumHours(List<PowerOutage> parziale, int maxHours) {
		int tot=0;
		for (PowerOutage p: parziale)
		{
			tot+=p.getHourInterval();
		}
		return tot<maxHours;
	}
	
	public int sumHoursValue(List<PowerOutage> parziale) {
		int tot=0;
		for (PowerOutage p: parziale)
		{
			tot+=p.getHourInterval();
		}
		return tot;
	}

	private boolean sumYears(List<PowerOutage> parziale, int maxYears) {
		
		if (parziale.size()>=2)
		{int annoInizio=parziale.get(0).getYear();
		int annoFine=parziale.get(0).getYear();
			for (PowerOutage p: parziale)
			{
				if (p.getYear()<annoInizio)
				{
					annoInizio=p.getYear();
				}
				if (p.getYear()>annoFine)
				{
					annoFine=p.getYear();
				}
			}
			return annoFine-annoInizio<maxYears;
		}
		return true;
	}

	private int sumAffected(List<PowerOutage> parziale) {
		int tot=0;
		for (PowerOutage p: parziale)
		{
			tot+=p.getCustomersAffected();
		}
		return tot;
	}

public Map<Integer,PowerOutage> getOutagesByNerc(Nerc nerc)
{
	return podao.getPowerOutagesByNerc(nerc);
}


}
