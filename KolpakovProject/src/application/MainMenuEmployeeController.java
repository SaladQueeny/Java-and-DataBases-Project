package application;
//Колпаков Егор
import java.awt.Label;
import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import animations.Shake;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class MainMenuEmployeeController extends EmployeeInfo {

	ObservableList<String> langs = FXCollections.observableArrayList(); //лист для значений комнат в комбо боксе
	
	String roomSLString=null;
	//переменные для элементов в сцене
	Employee emp = new Employee();
	
	@FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Label employee_menu_text;

    @FXML
    private Button Back_button;

    @FXML
    private Button end_interaction_button;

    @FXML
    private Button choose_room_button;

    @FXML
    private Button change_password_button;

    @FXML
    private TextArea your_information_area;

    @FXML
    private TextField old_password_field;

    @FXML
    private TextField new_password_field;

    @FXML
    private PasswordField repeat_new_password_field;

    @FXML
    private Button confirm_button;

    @FXML
    private Text success_text;

    @FXML
    private TextField id_field;

    @FXML
    private Button choose_button;

    @FXML
    private Text room_information_text;

    @FXML
    private TextArea room_information_area;

    @FXML
    private Button choose_another_button;

    @FXML
    private Button Enter_room_button;

    @FXML
    private TextArea enter_room_information;

    @FXML
    private Text entering_room_information_text;
    
    @FXML
    private ComboBox<String> choose_room_box;
    
    @FXML
    private Text entered_room_text;

    @FXML
    void initialize() {
    	
    	emp.setFirst_Name(EmployeeInfo.NameString);//сохранение информации о работнике после входа
    	emp.setPassword(EmployeeInfo.PasswordString);
    	
    	try {
        	DatabaseHandler dbHandler=new DatabaseHandler();
    		ResultSet result = dbHandler.getEmployee(emp,true);
    		
    		your_information_area.setText(showData(result));
    		result = dbHandler.getEmployee(emp,true);
    		result.next();
    		emp.setID(result.getString(1));
    		result = dbHandler.getEmployee(emp,false);
    		setEmployeeInformation(result);
    		result = dbHandler.getEmployee(emp,true);
    		your_information_area.setText(showData(result)+"Your Security level:"+emp.getSecurity_level());//отображение информации о работнике
    		
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
    	
		try {
			DatabaseHandler db = new DatabaseHandler();
			ResultSet rsTables = db.getRoomsList(); 
			while(rsTables.next()) {
				langs.add(rsTables.getString(1));//заполнение комбобокса для выбора комнаты
			}
			choose_room_box.setItems(langs);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    	choose_room_button.setOnAction(event->{//действие при выборе показа комнаты
    		
    		end_interaction_button.setVisible(true);
    		Back_button.setVisible(false);
    		//id_field.setVisible(true);
    		choose_button.setVisible(true);
    		change_password_button.setVisible(false);
    		
			choose_room_box.setVisible(true);
    	});
    	
    	change_password_button.setOnAction(event->{//действие при выборе изменения пароля
    		old_password_field.setVisible(true);
    		new_password_field.setVisible(true);
    		repeat_new_password_field.setVisible(true);
    		confirm_button.setVisible(true);
    		
    		end_interaction_button.setVisible(true);
    		Back_button.setVisible(false);
    		choose_room_button.setVisible(false);
    	});
    	
    	confirm_button.setOnAction(event->{//действие при нажатии кнопки подтверждения изменения пароля
    		if(old_password_field.getText().trim()!=""&&new_password_field.getText().trim()!=""&&repeat_new_password_field.getText().trim()!="") {
    			DatabaseHandler dbHandler=new DatabaseHandler();
    			ResultSet result=null;
        		try {
        			Pattern p = Pattern.compile("[a-zA-Z]{1,}[0-9]{4,}");
            		Matcher matchold = null;
            		Matcher matchnew = null;
            		Matcher matchrepeatnew = null;
            		matchold = p.matcher(old_password_field.getText().trim());
            		matchnew = p.matcher(new_password_field.getText().trim());
            		matchrepeatnew = p.matcher(repeat_new_password_field.getText().trim());
            		if(emp.getPassword().equals(old_password_field.getText().trim())&&matchold.matches()&&matchnew.matches()&&matchrepeatnew.matches()&&!(old_password_field.getText().trim().equals(new_password_field.getText().trim()))&&repeat_new_password_field.getText().trim().equals(new_password_field.getText().trim())) {
        				dbHandler.changePasswordEmployee(new_password_field.getText().trim(), emp.getID());
        				result = dbHandler.getEmployee(emp, false);
        				emp.setPassword(new_password_field.getText().trim());
        				your_information_area.setText(showData(result));
        				success_text.setVisible(true);
        				old_password_field.setText("");
        	    		new_password_field.setText("");
        	    		repeat_new_password_field.setText("");
        				//System.out.println("Updated");
        				
        			}else {
        				success_text.setVisible(false);
        				Shake old_password_field_shake = new Shake(old_password_field);
        				Shake new_password_field_shake = new Shake(new_password_field);
        				Shake repeat_new_password_field_shake = new Shake(repeat_new_password_field);
        				old_password_field_shake.playAnim();
        				new_password_field_shake.playAnim();
        				repeat_new_password_field_shake.playAnim();
        			}
        			
    				
    			} catch (Exception e) {
    				
    				e.printStackTrace();
    			}
    		}else {
    			Shake old_password_field_shake = new Shake(old_password_field);
				Shake new_password_field_shake = new Shake(new_password_field);
				Shake repeat_new_password_field_shake = new Shake(repeat_new_password_field);
				old_password_field_shake.playAnim();
				new_password_field_shake.playAnim();
				repeat_new_password_field_shake.playAnim();
    		}
    	});
    	
    	choose_button.setOnAction(event->{//выбор определенной комнаты
    		if(choose_room_box.getValue()!=null) {
				try {
        			String roomID=choose_room_box.getValue();
            		int id = Integer.parseInt (roomID);
            		Pattern p = Pattern.compile("[0-9]{3}");
            		Matcher match = null;
            		match = p.matcher(roomID);
            		DatabaseHandler dbHandler=new DatabaseHandler();
            		ResultSet result = dbHandler.getSecurityLevelRoom(id);
    				if(match.matches()&&!isMyResultSetEmpty(result)) {
    					result.next();
    					//roomSLString =;
    					int roomSL = Integer.parseInt (result.getString(1));
    					int empSL = Integer.parseInt (emp.getSecurity_level());
    					//System.out.println(result.getString(1));
						//System.out.println(result.getString(2));
    					result = dbHandler.getSecurityLevelRoom(id);
    					choose_room_button.setDisable(true);
						choose_button.setDisable(true);
						id_field.setDisable(true);
						room_information_area.setVisible(true);
						
						if(empSL<roomSL) {
							room_information_area.setText(showData(result)+"\nYou cannot enter this room!");
							Enter_room_button.setDisable(true);
						}else {
							room_information_area.setText(showData(result)+"\nYou can enter this room!");
							Enter_room_button.setDisable(false);
						}
						room_information_text.setText("Information about room");
						room_information_text.setVisible(true);
						choose_room_box.setDisable(true);
						Enter_room_button.setVisible(true);
						choose_another_button.setVisible(true);
    				}else {
    					Shake choose_room_box_shake = new Shake(choose_room_box);
    					choose_room_box_shake.playAnim();
    				}
    			} catch (SQLException e) {
    				e.printStackTrace();
    			}
    		}else {
				Shake choose_room_box_shake = new Shake(choose_room_box);
				choose_room_box_shake.playAnim();
			}
    	});
    	
    	Enter_room_button.setOnAction(event->{//войти в комнату
		    GregorianCalendar gcalendar = new GregorianCalendar();
		    int year = gcalendar.get(Calendar.YEAR);
    		Date date = gcalendar.getTime();
    		
    		DateofVisit dateofVisit = new DateofVisit(year, gcalendar.get(Calendar.MONTH)+1, gcalendar.get(Calendar.DATE), date.getHours(), date.getMinutes(), date.getSeconds());
    		
    		DatabaseHandler dbHandler=new DatabaseHandler();
    		if(dbHandler.addTimeToListOfVisits(dateofVisit, emp.getID(), choose_room_box.getValue())) {
    			//System.out.println("OK");
    			enter_room_information.setVisible(true);
    			String years =dateofVisit.getYyyy()+""; 
    			String dateString = year+"-"+dateofVisit.getMm()+"-"+dateofVisit.getDd()+" "+dateofVisit.getHh()+":"+dateofVisit.getMi()+":"+dateofVisit.getSs();
    			enter_room_information.setText("You visit room "+choose_room_box.getValue()+dateString);
    			entering_room_information_text.setVisible(true);
    		}else {
    			dbHandler.UpdateListOfVisits(dateofVisit, emp.getID(), choose_room_box.getValue());
    			//System.out.println("Need update");
    			enter_room_information.setVisible(true);
    			String years =dateofVisit.getYyyy()+""; 
    			String dateString = year+"-"+dateofVisit.getMm()+"-"+dateofVisit.getDd()+" "+dateofVisit.getHh()+":"+dateofVisit.getMi()+":"+dateofVisit.getSs();
    			enter_room_information.setText("You visit room "+choose_room_box.getValue()+dateString);
    			entering_room_information_text.setVisible(true);
    		}
    		
    	});
    	
    	choose_another_button.setOnAction(event->{//выбрать другую комнату
    		choose_room_box.setDisable(false);
    		choose_button.setDisable(false);
    		Enter_room_button.setVisible(false);
    		choose_another_button.setVisible(false);
    		room_information_area.setVisible(false);
    		room_information_text.setVisible(false);
    		enter_room_information.setVisible(false);
    		entering_room_information_text.setVisible(false);
    	});
    	
    	end_interaction_button.setOnAction(event->{//закончить определенный интерактив
    		choose_room_button.setDisable(false);
    		choose_room_box.setDisable(false);
    		choose_room_button.setDisable(false);
    		old_password_field.setVisible(false);
    		new_password_field.setVisible(false);
    		repeat_new_password_field.setVisible(false);
    		confirm_button.setVisible(false);
    		id_field.setVisible(false);
    		Back_button.setVisible(true);
    		choose_button.setVisible(false);
    		choose_button.setDisable(false);
    		choose_room_box.setPromptText("Choose room");
    		choose_room_button.setVisible(true);
    		change_password_button.setVisible(true);
    		end_interaction_button.setVisible(false);
    		success_text.setVisible(false);
    		choose_room_box.setVisible(false);
    		room_information_text.setVisible(false);
    		room_information_area.setVisible(false);
    		Enter_room_button.setVisible(false);
    		choose_another_button.setVisible(false);
    		enter_room_information.setVisible(false);
			entering_room_information_text.setVisible(false);
    	});
    	
    	
    	Back_button.setOnAction(event->{//выйти в начальное меню
        	try {
				Parent root;
				root = FXMLLoader.load(getClass().getResource("StartWindow.fxml"));
				Stage window=(Stage) Back_button.getScene().getWindow();
	    		window.setScene(new Scene(root));
			} catch (IOException e) {
				e.printStackTrace();
			}
        });
    	
    	
    	
    }
    
    public void setEmployeeInformation(ResultSet result) {//заполнить полную информацию о работнике, который вошёл 
    	
    	try {
    		result.next();
    		emp.setID(result.getString(1));
    		emp.setFirst_Name(result.getString(2));
    		emp.setLast_Name(result.getString(3));
    		emp.setEmail(result.getString(4));
    		emp.setTelephone_number(result.getString(5));
    		emp.setPassportID(result.getString(6));
    		emp.setPassword(result.getString(7));
    		emp.setSecurity_level(result.getString(8));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
    }
    
    private String showData(ResultSet result) throws SQLException {//показ данных
    	ResultSetMetaData rsmd = result.getMetaData();
		int numberOfColumns = rsmd.getColumnCount();
		String text  = "";
		for (int i = 1; i < numberOfColumns + 1; i++) { //получаем названия столбцов
			String columnName = rsmd.getColumnName(i);
			text = text + columnName + ", ";
		}
		text = text + "\n";
		while(result.next()) { //получаем данные из строк таблицы
			
			for(int i =0; i<numberOfColumns; i++) {
				if(i+1 == numberOfColumns) {;
					text = text + result.getString(i+1) + "\n";
				}
				else {
					text += result.getString(i+1) + ", ";
				}
			}
		}
		return text;
    }
    
    public static boolean isMyResultSetEmpty(ResultSet rs) throws SQLException {//проверка результата на null
        return (!rs.isBeforeFirst() && rs.getRow() == 0);
    }
}
