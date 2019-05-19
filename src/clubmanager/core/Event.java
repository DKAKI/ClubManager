/**
 * 
 */
package clubmanager.core;

/**
 * @author NAHED
 *
 */
public class Event {
	
int id_event;
int date_of_event;
String name_event;
String official_sponsor;
String place;
/**
 * @param id_event
 * @param date_of_event
 * @param name_event
 * @param official_sponsor
 * @param place
 */
public Event(int id_event, int date_of_event, String name_event, String official_sponsor, String place) {
	super();
	this.id_event = id_event;
	this.date_of_event = date_of_event;
	this.name_event = name_event;
	this.official_sponsor = official_sponsor;
	this.place = place;
}
public Event(int date_of_event, String name_event, String official_sponsor, String place) {
this(0, date_of_event, name_event, official_sponsor, place);
}

@Override
public String toString() {
	return "Event [id_event=" + id_event + ", date_of_event=" + date_of_event + ", name_event=" + name_event
			+ ", official_sponsor=" + official_sponsor + ", place=" + place + "]";
}

/**
 * @return the id_event
 */
public int getId_event() {
	return id_event;
}

/**
 * @param id_event the id_event to set
 */
public void setId_event(int id_event) {
	this.id_event = id_event;
}

/**
 * @return the date_of_event
 */
public int getDate_of_event() {
	return date_of_event;
}

/**
 * @param date_of_event the date_of_event to set
 */
public void setDate_of_event(int date_of_event) {
	this.date_of_event = date_of_event;
}

/**
 * @return the name_event
 */
public String getName_event() {
	return name_event;
}

/**
 * @param name_event the name_event to set
 */
public void setName_event(String name_event) {
	this.name_event = name_event;
}

/**
 * @return the official_sponsor
 */
public String getOfficial_sponsor() {
	return official_sponsor;
}

/**
 * @param official_sponsor the official_sponsor to set
 */
public void setOfficial_sponsor(String official_sponsor) {
	this.official_sponsor = official_sponsor;
}

/**
 * @return the place
 */
public String getPlace() {
	return place;
}

/**
 * @param place the place to set
 */
public void setPlace(String place) {
	this.place = place;
}

	/**
	 * 
	 */
	public Event() {
		// TODO Auto-generated constructor stub
	}

}
