package it.polito.tdp.poweroutages.model;

import java.util.List;

public class TestModel {

	public static void main(String[] args) {
		
		Model model = new Model();
		System.out.println(model.getNercList());

		List<PowerOutage> ls=model.worstCase(new Nerc(3, "MAAC"), 4, 2);
		System.out.println(ls);
		int somma=0;
		for (PowerOutage p:ls)
		{
			somma+=p.getHourInterval();
		}
		System.out.println(somma);
	}

}
