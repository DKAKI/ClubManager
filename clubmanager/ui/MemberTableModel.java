package clubmanager.ui;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import clubmanager.core.Member;

class MemberTableModel extends AbstractTableModel {

	public static final int OBJECT_COL = -1;// why we initialized it with -1
	private static final int LAST_NAME_COL = 0;
	private static final int FIRST_NAME_COL = 1;
	private static final int EMAIL_COL = 2;
	private static final int LOGIN_COL = 3;
	private static final int PASSWORD_COL = 4;

	private String[] columnNames = { "Login", "Password", "Email", "Last Name", "First Name"};
	private List<Member> members;

	public MemberTableModel(List<Member> theEmployees) {
		//what's the difference btw this method and this.theEmployees=theEmployees
		members = theEmployees;
	}

	@Override
	public int getColumnCount() {
		return columnNames.length;
	}

	@Override
	public int getRowCount() {
		return members.size();
	}

	@Override
	public String getColumnName(int col) {
		return columnNames[col];
	}

	@Override
	public Object getValueAt(int row, int col) {

		Member tempMember = members.get(row);
		//le type de retour est object mais dans les differents cas on a String or int( peut etre que on ne considere pas les attribut de type de retour
		 //differents return types
		switch (col) {
		case LAST_NAME_COL:
			return tempMember.getLastName();
		case FIRST_NAME_COL:
			return tempMember.getFirstName();
		case EMAIL_COL:
			return tempMember.getEmail();
		case LOGIN_COL:
			return tempMember.getLogin();
		case PASSWORD_COL:
			return tempMember.getPassword();
		case OBJECT_COL:
			return tempMember;
		default:
			return tempMember.getLastName();
		}
	}

	@Override
	public Class getColumnClass(int c) {
		return getValueAt(0, c).getClass();
		//a quoi ça sert
	}
	
}
