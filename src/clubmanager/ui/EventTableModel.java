/**
 * 
 */
package clubmanager.ui;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import clubmanager.core.Event;

/**
 * @author NAHED
 *
 */
public class EventTableModel extends AbstractTableModel {
	
	public static final int OBJECT_COL = -1;// why we initialized it with -1
	private static final int DATE_EVENT_COL = 1;
	private static final int NAME_EVENT_COL = 3;
	private static final int OFFICIAL_SPONSOR_COL = 2;
	private static final int PLACE_COL = 0;

	private String[] columnNames = { "Name of event", "date", "Official Sponsor", "Place"};
	private List<Event> event;
	/**
	 * 
	 */
	public EventTableModel(List<Event> theEvent) {
		//what's the difference btw this method and this.theEvent=theEvent
		event = theEvent;
	}

	@Override
	public int getColumnCount() {
		return columnNames.length;
	}

	@Override
	public int getRowCount() {
		return event.size();
	}

	@Override
	public String getColumnName(int col) {
		return columnNames[col];
	}

	@Override
	public Object getValueAt(int row, int col) {

		Event tempEvent = event.get(row);
		//getId_event()  getDate_of_event() getName_event() getOfficial_sponsor()
		//setId_event setDate_of_event setName_event setOfficial_sponsor
	//  id_event int date_of_event, String name_event, String official_sponsor, String place
		switch (col) {

		case DATE_EVENT_COL:
			return tempEvent.getDate_of_event();
		case NAME_EVENT_COL:
			return tempEvent.getName_event();
		case OFFICIAL_SPONSOR_COL:
			return tempEvent.getOfficial_sponsor();
		case PLACE_COL:
			return tempEvent.getPlace();
		case OBJECT_COL:
			return tempEvent;
		default:
			return tempEvent.getName_event();
		}
	}

	@Override
	public Class getColumnClass(int c) {
		return getValueAt(0, c).getClass();
		//a quoi ça sert
	}
	
}
