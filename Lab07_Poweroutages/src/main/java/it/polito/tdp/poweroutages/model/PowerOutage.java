package it.polito.tdp.poweroutages.model;

import java.sql.Timestamp;
import java.util.Date;

public class PowerOutage {

	private int eventId;
	private Nerc nerc;
	private Timestamp dateBegan;
	private Timestamp dateFinished;
	private int customersAffected;
	private int hourInterval;
	private int year;

	public PowerOutage(int eventId, Nerc nerc, Timestamp dateBegan, Timestamp dateFinished, int customersAffected) {
		super();
		this.eventId = eventId;
		this.nerc = nerc;
		this.dateBegan = dateBegan;
		this.dateFinished = dateFinished;
		this.customersAffected = customersAffected;
		this.hourInterval = getHourGap(dateFinished, dateBegan);
		this.year = dateBegan.toLocalDateTime().getYear();
	}

	public int getYear() {
		return year;
	}

	public int getHourInterval() {
		return hourInterval;
	}

	public int getEventId() {
		return eventId;
	}

	public Nerc getNerc() {
		return nerc;
	}

	public Date getDateBegan() {
		return dateBegan;
	}

	public Date getDateFinished() {
		return dateFinished;
	}

	public int getCustomersAffected() {
		return customersAffected;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + eventId;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PowerOutage other = (PowerOutage) obj;
		if (eventId != other.eventId)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return year + " " + dateBegan + " " + dateFinished + " " + hourInterval + " " + customersAffected + "\n";
	}

	private int getHourGap(Timestamp d2, Timestamp d1) {
		return (int) (d2.getTime() - d1.getTime()) / (1000 * 60 * 60);
	}

}
