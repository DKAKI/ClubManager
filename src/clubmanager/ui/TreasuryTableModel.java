/**
 * 
 */
package clubmanager.ui;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import clubmanager.core.Treasury;

/**
 * @author NAHED
 *
 */
public class TreasuryTableModel extends AbstractTableModel {
	
	public static final int OBJECT_COL = -1;// why we initialized it with -1
	private static final int YEAR_COL = 1;
	private static final int SUM_COL = 3;
	private static final int STATE_OF_TREASURY_COL = 2;
	private static final int RESPONIBLE_TREASURY_COL = 0;

	private String[] columnNames = { "Responsible of treasury", "Year", "State of Treasury", "Contributions"};
	private List<Treasury> treasury;
	/**
	 * 
	 */
	public TreasuryTableModel(List<Treasury> theTreasury) {
		//what's the difference btw this method and this.theTreasury=theTreasury
		treasury = theTreasury;
	}

	@Override
	public int getColumnCount() {
		return columnNames.length;
	}

	@Override
	public int getRowCount() {
		return treasury.size();
	}

	@Override
	public String getColumnName(int col) {
		return columnNames[col];
	}

	@Override
	public Object getValueAt(int row, int col) {

		Treasury tempTreasury = treasury.get(row);
		
		switch (col) {
		//getId_treasury() getYear()  getSum() getState_of_treasury() getResponsible_treasury()		
		case YEAR_COL:
			return tempTreasury.getYear();
		case SUM_COL:
			return tempTreasury.getSum();
		case STATE_OF_TREASURY_COL:
			return tempTreasury.getState_of_treasury();
		case RESPONIBLE_TREASURY_COL:
			return tempTreasury.getResponsible_treasury();
		case OBJECT_COL:
			return tempTreasury;
		default:
			return tempTreasury.getResponsible_treasury();
		}
	}

	@Override
	public Class getColumnClass(int c) {
		return getValueAt(0, c).getClass();
		//a quoi ça sert
	}
	
}
