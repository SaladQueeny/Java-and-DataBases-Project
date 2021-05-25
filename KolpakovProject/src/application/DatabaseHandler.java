package application;
//�������� ����
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

import org.w3c.dom.UserDataHandler;//����� ��� �������� � ��

public class DatabaseHandler extends Configs {
	Connection dbConnection;
	
	public Connection getDBConnection() throws ClassNotFoundException, SQLException{//�������� ����������� 
		String connectionString = "jdbc:mysql://"+dbHost+":"+dbPort+"/"+dbName;
		Class.forName("com.mysql.cj.jdbc.Driver");
		dbConnection = DriverManager.getConnection(connectionString, dbUser, dbPass);
		return dbConnection;
		
	}
	
	public boolean addUser(Employee emp) {//����� ��� ���������� ������������
		String password=emp.getFirst_Name()+emp.getID();
		int IDint = Integer.parseInt(emp.getID());
		String insert = "INSERT INTO "+Consts.EMPLOYEE_TABLE+" VALUES (?,?,?,?,?,?,?)";

		try {
			PreparedStatement prSt = getDBConnection().prepareStatement(insert);
			prSt.setInt(1, IDint);
			prSt.setString(2, emp.getFirst_Name());
			prSt.setString(3, emp.getLast_Name());
			prSt.setString(4, emp.getEmail());
			prSt.setString(5, emp.getTelephone_number());
			prSt.setString(6, emp.getPassportID());
			prSt.setString(7, password);
			prSt.executeUpdate();//���������� �������
			insert = "INSERT INTO "+Consts.SECURITY_LEVEL_EMPLOYEE_TABLE+" VALUES (?,?)";
			prSt = getDBConnection().prepareStatement(insert);
			prSt.setString(1, emp.getSecurity_level());
			prSt.setString(2, emp.getID());
			prSt.executeUpdate();
			return true;
		} catch (ClassNotFoundException e) {
			
			e.printStackTrace();
			return false;
		} catch (SQLException e) {
			
			e.printStackTrace();
			return false;
		}
		
	}
	public boolean addTimeToListOfVisits(DateofVisit dov, String empID, String roomID) {//����� ��� ���������� � list of visit 

		String insert = "INSERT INTO "+Consts.LISTS_OF_VISITS_TABLE+" VALUES (?,?,?)";
		
		try {
			PreparedStatement prSt = getDBConnection().prepareStatement(insert);
			//System.out.println(dov.getYyyy()+"");
			String year =dov.getYyyy()+""; 
			String dateString = year+"-"+dov.getMm()+"-"+dov.getDd()+" "+dov.getHh()+":"+dov.getMi()+":"+dov.getSs();
			System.out.println(dateString);
			prSt.setString(1, dateString);
			prSt.setString(2, empID);
			prSt.setString(3, roomID);
			//System.out.println(prSt);
			prSt.executeUpdate();
			return true;
		} catch (ClassNotFoundException e) {
			
			e.printStackTrace();
			return false;
		} catch (SQLException e) {
			
			e.printStackTrace();
			return false;
		}
		
	}
	public boolean UpdateListOfVisits(DateofVisit dov, String empID, String roomID) {//��������� ������ ��������� ���� ������������ ��� ��� ���

		String insert = "UPDATE "+Consts.LISTS_OF_VISITS_TABLE+" SET "+Consts.LISTS_OF_VISITS_DATE+"=?"+" WHERE "+Consts.LISTS_OF_VISITS_EMPLOYEE_ID+"=?"+" AND "+Consts.LISTS_OF_VISITS_ROOM_ID+"=?" +";";

		try {
			PreparedStatement prSt = getDBConnection().prepareStatement(insert);
			//System.out.println(dov.getYyyy()+"");
			String year =dov.getYyyy()+""; 
			String dateString = year+"-"+dov.getMm()+"-"+dov.getDd()+" "+dov.getHh()+":"+dov.getMi()+":"+dov.getSs();
			//System.out.println(dateString);
			prSt.setString(1, dateString);
			prSt.setString(2, empID);
			prSt.setString(3, roomID);
			//System.out.println(prSt);
			prSt.executeUpdate();
			return true;
		} catch (ClassNotFoundException e) {
			
			e.printStackTrace();
			return false;
		} catch (SQLException e) {
			
			e.printStackTrace();
			return false;
		}
		
	}
	public boolean changeSLRoom(String level, String ID) {//����� ��� ��������� ������ ������� ��� �������

		String insert = "UPDATE "+Consts.SECURITY_LEVEL_TABLE+" SET "+Consts.SECURITY_LEVEL_LEVELNAME+"=?"+" WHERE "+Consts.SECURITY_LEVEL_ROOM_ID+"=?"+";";

		try {
			PreparedStatement prSt = getDBConnection().prepareStatement(insert);
			prSt.setString(1, level);
			prSt.setString(2, ID);
			prSt.executeUpdate();
			
			return true;
		} catch (ClassNotFoundException e) {
			
			e.printStackTrace();
			return false;
		} catch (SQLException e) {
			
			e.printStackTrace();
			return false;
		}
		
	}
	public boolean changeSLEmployee(String level, String ID) {//������ ��� �������� ������� ������� ���������

		String insert = "UPDATE "+Consts.SECURITY_LEVEL_EMPLOYEE_TABLE+" SET "+Consts.SECURITY_LEVEL_EMPLOYEE_LEVELNAME+"=?"+" WHERE "+Consts.SECURITY_LEVEL_EMPLOYEE_EMPLOYEE_ID+"=?"+";";

		try {
			PreparedStatement prSt = getDBConnection().prepareStatement(insert);
			prSt.setString(1, level);
			prSt.setString(2, ID);
			prSt.executeUpdate();
			
			return true;
		} catch (ClassNotFoundException e) {
			
			e.printStackTrace();
			return false;
		} catch (SQLException e) {
			
			e.printStackTrace();
			return false;
		}
		
	}
	public boolean changePasswordEmployee(String password, String ID) {//����� ��� ��������� ������ ��������� 

		String insert = "UPDATE "+Consts.EMPLOYEE_TABLE+" SET "+Consts.EMPLOYEE_PASSWORD+"=?"+" WHERE "+Consts.EMPLOYEE_ID+"=?"+";";

		try {
			PreparedStatement prSt = getDBConnection().prepareStatement(insert);
			prSt.setString(1, password);
			prSt.setString(2, ID);
			prSt.executeUpdate();
			
			return true;
		} catch (ClassNotFoundException e) {
			
			e.printStackTrace();
			return false;
		} catch (SQLException e) {
			
			e.printStackTrace();
			return false;
		}
		
	}
	public boolean deleteEmployee(Employee emp) {//����� ��� �������� ������������

		try {
			int IDint = Integer.parseInt(emp.getID());
			String delete = "DELETE FROM "+Consts.SECURITY_LEVEL_EMPLOYEE_TABLE+" WHERE "+Consts.SECURITY_LEVEL_EMPLOYEE_EMPLOYEE_ID+"=?"+";";
			PreparedStatement prSt = getDBConnection().prepareStatement(delete);
			prSt.setInt(1, IDint);
			prSt.executeUpdate();
			
			delete = "DELETE FROM "+Consts.EMPLOYEE_TABLE+" WHERE "+Consts.EMPLOYEE_ID+"=?"+";";
			prSt = getDBConnection().prepareStatement(delete);
			prSt.setInt(1, IDint);
			prSt.executeUpdate();
			return true;
		} catch (ClassNotFoundException e) {
			
			e.printStackTrace();
			return false;
		} catch (SQLException e) {
			
			e.printStackTrace();
			return false;
		}
		
	}
	public ResultSet getEmployee(Employee emp, Boolean password) {//����� ��� ����� ������ � ��������� 
		ResultSet resSet=null;
		String select=null;
		if(password) {
			select= "SELECT * FROM "+Consts.EMPLOYEE_TABLE+" WHERE "+Consts.EMPLOYEE_FIRST_NAME+"=? AND "+Consts.EMPLOYEE_PASSWORD+"=?"+";";
			try {
				PreparedStatement prSt = getDBConnection().prepareStatement(select, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
				prSt.setString(1, emp.getFirst_Name());
				prSt.setString(2, emp.getPassword());
				resSet=prSt.executeQuery();

			} catch (ClassNotFoundException e) {
				
				e.printStackTrace();
				
			} catch (SQLException e) {
				
				e.printStackTrace();
				
			}
		}else {
			select= "SELECT * FROM "+Consts.EMPLOYEE_TABLE+" JOIN "+Consts.SECURITY_LEVEL_EMPLOYEE_TABLE+" ON "+Consts.EMPLOYEE_TABLE+"."
		+Consts.EMPLOYEE_ID+"="+Consts.SECURITY_LEVEL_EMPLOYEE_TABLE+"."+Consts.SECURITY_LEVEL_EMPLOYEE_EMPLOYEE_ID+" WHERE "+Consts.EMPLOYEE_ID+"=?"+";";
			try {
				int IDint = Integer.parseInt(emp.getID());
				PreparedStatement prSt = getDBConnection().prepareStatement(select, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
				prSt.setInt(1, IDint);
				
				resSet=prSt.executeQuery();

			} catch (ClassNotFoundException e) {
				
				e.printStackTrace();
				
			} catch (SQLException e) {
				
				e.printStackTrace();
				
			}
		}
		
		return resSet;
		
	}

	
	public ResultSet getAdmin(Admin emp) {//����� ��� ��������� ���������� � ������
		ResultSet resSet=null;
		String select = "SELECT * FROM "+Consts.ADMIN_TABLE+" WHERE "+Consts.ADMIN_NAME+"=? AND "+Consts.ADMIN_PASSWORD+"=?"+";";

		try {
			PreparedStatement prSt = getDBConnection().prepareStatement(select);
			prSt.setString(1, emp.getName());
			prSt.setString(2, emp.getPassword());
			resSet=prSt.executeQuery();

			
		} catch (ClassNotFoundException e) {
			
			e.printStackTrace();
			
		} catch (SQLException e) {
			
			e.printStackTrace();
			
		}
		return resSet;
		
	}
	public ResultSet getRoom(int ID) {//����� ��� ��������� ���������� � �������
		ResultSet resSet=null;
		String select = "SELECT * FROM "+Consts.ROOM_TABLE+" JOIN "+Consts.SECURITY_LEVEL_TABLE+" ON "+Consts.ROOM_TABLE+"."+Consts.ROOM_ID+"="+Consts.SECURITY_LEVEL_TABLE+"."+Consts.SECURITY_LEVEL_ROOM_ID+" WHERE "+Consts.ROOM_ID+"=?"+";";
		try {
			PreparedStatement prSt = getDBConnection().prepareStatement(select);
			prSt.setInt(1, ID);
			resSet=prSt.executeQuery();

		} catch (ClassNotFoundException e) {
			
			e.printStackTrace();
			
		} catch (SQLException e) {
			
			e.printStackTrace();
			
		}
		return resSet;
	}
	
	public ResultSet getSecurityLevelRoom(int ID) {//����� ��� ��������� ������ ������� �������
		ResultSet resSet=null;
		String select = "SELECT * FROM "+Consts.SECURITY_LEVEL_TABLE+" WHERE "+Consts.SECURITY_LEVEL_ROOM_ID+"=?"+";";
		try {
			PreparedStatement prSt = getDBConnection().prepareStatement(select);
			prSt.setInt(1, ID);
			resSet=prSt.executeQuery();

		} catch (ClassNotFoundException e) {
			
			e.printStackTrace();
			
		} catch (SQLException e) {
			
			e.printStackTrace();
			
		}
		return resSet;
	}
	
	public ResultSet getSecurityLevelEmployee(int ID) {//����� ��� ��������� ������ ������� ���������
		ResultSet resSet=null;
		String select = "SELECT * FROM "+Consts.SECURITY_LEVEL_EMPLOYEE_TABLE+" WHERE "+Consts.SECURITY_LEVEL_EMPLOYEE_EMPLOYEE_ID+"=?"+";";
		try {
			PreparedStatement prSt = getDBConnection().prepareStatement(select);
			prSt.setInt(1, ID);
			resSet=prSt.executeQuery();

		} catch (ClassNotFoundException e) {
			
			e.printStackTrace();
			
		} catch (SQLException e) {
			
			e.printStackTrace();
			
		}
		return resSet;
	}
	public ResultSet getRoomsList() {//����� ��� ��������� ������ ������
		ResultSet resSet=null;
		String select = "SELECT "+Consts.ROOM_ID+" FROM "+Consts.ROOM_TABLE+";";
		try {
			PreparedStatement prSt = getDBConnection().prepareStatement(select);
			//System.out.println(prSt);
			resSet=prSt.executeQuery();

		} catch (ClassNotFoundException e) {
			
			e.printStackTrace();
			
		} catch (SQLException e) {
			
			e.printStackTrace();
			
		}
		return resSet;
	}
	
	public ResultSet getListofVisits(int ID) {//����� ��� ��������� ������ ��������� ��� ������������ �������
		ResultSet resSet=null;
		String select = "SELECT * FROM "+Consts.LISTS_OF_VISITS_TABLE+" WHERE "+Consts.LISTS_OF_VISITS_ROOM_ID +"=?"+";";
		try {
			PreparedStatement prSt = getDBConnection().prepareStatement(select);
			prSt.setInt(1, ID);
			resSet=prSt.executeQuery();

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return resSet;
	}
	
	public ResultSet getTables() {//����� ��� ��������� ������ ������
		ResultSet resSet=null;
		String select = "Show tables;";
		try {
			PreparedStatement prSt = getDBConnection().prepareStatement(select);
			resSet=prSt.executeQuery();

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return resSet;

	}
	
	public ResultSet getTable(String name) {//����� ��� ��������� ���������� � �������
		ResultSet resSet=null;
		String select = "SELECT * FROM "+name+";";
		try {
			
			PreparedStatement prSt = getDBConnection().prepareStatement(select);
			resSet=prSt.executeQuery();
			
		} catch (ClassNotFoundException e) {
			
			e.printStackTrace();
			
		} catch (SQLException e) {
			
			e.printStackTrace();
			
		}
		return resSet;
	}
	
	
	
}
