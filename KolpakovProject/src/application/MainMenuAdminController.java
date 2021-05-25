package application;

import java.io.IOException;
import java.net.URL;
import java.sql.DatabaseMetaData;
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
// олпаков ≈гор
public class MainMenuAdminController {
	//ObservableList<String> infoOption =  FXCollections.observableArrayList("Employee","Admin");
	ObservableList<String> langs = FXCollections.observableArrayList(); 
	//переменные дл€ обращени€м к элементам сцены
	@FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button Back_button;

    @FXML
    private Button choose_room_button;

    @FXML
    private Button choose_employee_button;

    @FXML
    private Button end_interaction_button;

    @FXML
    private Button choose_button;

    @FXML
    private TextField id_field;

    @FXML
    private TextArea room_information_area;

    @FXML
    private Button Show_list_of_visits_button;

    @FXML
    private Button Change_security_level_button;

    @FXML
    private TextField old_security_level_field;

    @FXML
    private Button confirm_new_level_button;

    @FXML
    private TextArea list_of_visits_area;

    @FXML
    private TextField new_security_level_field;

    @FXML
    private Text Information_about_room_text;

    @FXML
    private Text list_of_visits_text;

    @FXML
    private Text success_security_level_changes_text;

    @FXML
    private Button choose_another_button;

    @FXML
    private Button show_all_info_button;

    @FXML
    private Button add_eemployee_button;

    @FXML
    private ComboBox<String> choose_table_box;

    @FXML
    private Button choose_table_button;

    @FXML
    private TextArea table_information_area;

    @FXML
    private TextArea rooms_information_area;

    @FXML
    private Button delete_employee_button;

    @FXML
    private Button confirm_delete_button;

    @FXML
    private Button cansel_delete_button;
    
    @FXML
    private Text success_delete_button;

    @FXML
    void initialize() {
    	
    	try {//заполнение комбо бокса названи€ми таблиц
			DatabaseHandler db = new DatabaseHandler();
			ResultSet rsTables = db.getTables(); 
			while(rsTables.next()) {
				langs.add(rsTables.getString(1));
			}
			choose_table_box.setItems(langs);
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
    	

    	Back_button.setOnAction(event->{//возвражение в начальную сцена приложени€
            try {
                Parent root;
                root = FXMLLoader.load(getClass().getResource("StartWindow.fxml"));
                Stage window=(Stage) Back_button.getScene().getWindow();
                window.setScene(new Scene(root));
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    	
    	
    	show_all_info_button.setOnAction(event->{//действие при нажатии кнопки показать всю информацию
    		Back_button.setVisible(false);
    		show_all_info_button.setDisable(true);
    		add_eemployee_button.setVisible(false);
    		choose_employee_button.setVisible(false);
    		choose_room_button.setVisible(false);
    		end_interaction_button.setVisible(true);
    		choose_table_button.setVisible(true);
    		choose_table_box.setVisible(true);
    		

    	});
    	
    	choose_table_button.setOnAction(event->{//действие при нажатии кнопки выбора определенной таблицы дл€ показа информации в ней
    		
    		if(choose_table_box.getValue()!=null) {
    			Back_button.setVisible(false);
    			table_information_area.setVisible(true);
    			try {
        			String tableString = choose_table_box.getValue();
            		DatabaseHandler dbHandler=new DatabaseHandler();
            		ResultSet result = dbHandler.getTable(tableString);
            		String asdasd=showData(result);
            		System.out.println(asdasd);
            		table_information_area.setText(asdasd);
    			} catch (SQLException e) {
    				e.printStackTrace();
    			}
    		}else {
    			Shake choose_table_box_shake = new Shake(choose_table_box);
    			choose_table_box_shake.playAnim();
    		}
    		
    		
    	});
    	
    	choose_employee_button.setOnAction(event->{//действие при нажатии кнопки выбора работника
    		Information_about_room_text.setVisible(true);
			Information_about_room_text.setText("Information about employees");
			DatabaseHandler dbHandler=new DatabaseHandler();
    		ResultSet result = dbHandler.getTable("employee");
    		try {
    			rooms_information_area.setVisible(true);
				rooms_information_area.setText(showData(result));
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    		Back_button.setVisible(false);
    		add_eemployee_button.setVisible(false);
    		choose_room_button.setVisible(false);
    		choose_employee_button.setDisable(true);
    		show_all_info_button.setVisible(false);
    		end_interaction_button.setVisible(true);
    		choose_button.setVisible(true);
    		id_field.setVisible(true);
    		id_field.setPromptText("Employee id");
    		
    	});
    	
    	choose_room_button.setOnAction(event->{//действие при нажатии кнопки выбора комнаты
    		
    		
    		Information_about_room_text.setVisible(true);
			Information_about_room_text.setText("Information about rooms");
			DatabaseHandler dbHandler=new DatabaseHandler();
    		ResultSet result = dbHandler.getTable("room");
    		try {
    			rooms_information_area.setVisible(true);
				rooms_information_area.setText(showData(result));
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    		
    		Back_button.setVisible(false);
    		add_eemployee_button.setVisible(false);
    		choose_room_button.setDisable(true);
    		show_all_info_button.setVisible(false);
    		choose_employee_button.setVisible(false);
    		end_interaction_button.setVisible(true);
    		choose_button.setVisible(true);
    		id_field.setVisible(true);
    		id_field.setPromptText("Room id");
    	});
    	
    	delete_employee_button.setOnAction(event->{//действие при нажатии кнопки удалени€ сотрудника
    		Change_security_level_button.setDisable(true);
    		confirm_delete_button.setVisible(true);
    		cansel_delete_button.setVisible(true);
    	});
    	
    	cansel_delete_button.setOnAction(event->{//действие дл€ отмены удалени€ сотрудника
    		Change_security_level_button.setDisable(false);
    		confirm_delete_button.setVisible(false);
    		cansel_delete_button.setVisible(false);
    	});
    	
    	confirm_delete_button.setOnAction(event->{//действие дл€ подтверждени€ удалени€ сотрудника
    		Employee emp = new Employee();
    		emp.setID(id_field.getText().trim());
    		DatabaseHandler dbHandler=new DatabaseHandler();
    		dbHandler.deleteEmployee(emp);//запрос удалени€
    		success_delete_button.setVisible(true);
    		id_field.setText("Deleted");
    		room_information_area.setText("Deleted");
    	});
    	
    	choose_button.setOnAction(event->{//действие при нажатии кнопки выбора Id сотрудника или комнаты
    		if(id_field.getText().trim()!="") {
    			if(id_field.getPromptText().equals("Room id")) {
    				try {
            			String roomID=id_field.getText().trim();
                		int id = Integer.parseInt (roomID);
                		Pattern p = Pattern.compile("[0-9]{3}");
                		Matcher match = null;
                		match = p.matcher(roomID);
                		DatabaseHandler dbHandler=new DatabaseHandler();
                		ResultSet result = dbHandler.getRoom(id);
        				if(match.matches()&&!isMyResultSetEmpty(result)) {
        					choose_room_button.setDisable(true);
    						choose_button.setDisable(true);
    						id_field.setDisable(true);
    						Show_list_of_visits_button.setVisible(true);
    						Change_security_level_button.setVisible(true);
    						room_information_area.setVisible(true);
    						room_information_area.setText(showData(result));
    						rooms_information_area.setVisible(false);
    						Information_about_room_text.setVisible(true);
    						Information_about_room_text.setText("Information about room");
        					
        				}else {
        					Shake room_id_shake = new Shake(id_field);
        					room_id_shake.playAnim();
        				}
        			} catch (SQLException e) {
        				e.printStackTrace();
        			}
    			} 
    			if(id_field.getPromptText().equals("Employee id")) {
    				try {
            			String EmployeeID=id_field.getText().trim();
            			Employee emp=new Employee();
            	    	emp.setID(EmployeeID);
                		Pattern p = Pattern.compile("[0-9]{4,}");
                		Matcher match = null;
                		match = p.matcher(EmployeeID);
                		DatabaseHandler dbHandler=new DatabaseHandler();
                		ResultSet result = dbHandler.getEmployee(emp, false);
        				if(match.matches()&&!isMyResultSetEmpty(result)) {
        					delete_employee_button.setVisible(true);
        					choose_room_button.setDisable(true);
    						choose_button.setDisable(true);
    						id_field.setDisable(true);
    						Change_security_level_button.setVisible(true);
    						room_information_area.setVisible(true);
    						room_information_area.setText(showData(result));
    						rooms_information_area.setVisible(false);
    						Information_about_room_text.setVisible(true);
    						Information_about_room_text.setText("Information about employee");
        					
        				}else {
        					Shake room_id_shake = new Shake(id_field);
        					room_id_shake.playAnim();
        				}
        			} catch (SQLException e) {
        				e.printStackTrace();
        			}
    			} 
    			
    		}else {
				Shake room_id_shake = new Shake(id_field);
				room_id_shake.playAnim();
			}
    		
    	});
    	
    	
    	
    	Change_security_level_button.setOnAction(event->{//действие при нажатие на кнопку изменени€ уровн€ доступа 
    		new_security_level_field.setVisible(true);
    		old_security_level_field.setVisible(true);
    		confirm_new_level_button.setVisible(true);
    		delete_employee_button.setDisable(true);
    	});
    	
    	

    	confirm_new_level_button.setOnAction(event->{//подтверждение выбора нового уровн€ доступа
    		if(old_security_level_field.getText().trim()!=""&&new_security_level_field.getText().trim()!="") {
    			DatabaseHandler dbHandler=new DatabaseHandler();
    			ResultSet result=null;
        		String ID=id_field.getText().trim();
        		int id = Integer.parseInt (ID);
        		if(Information_about_room_text.getText().trim().equals("Information about room")) {
        			result = dbHandler.getSecurityLevelRoom(id);
        		}else {
        			result = dbHandler.getSecurityLevelEmployee(id);
        		}
        		
        		try {
        			result.next();
        			Pattern p = Pattern.compile("[0-4]{1}");
            		Matcher matchold = null;
            		Matcher matchnew = null;
            		matchold = p.matcher(old_security_level_field.getText().trim());
            		matchnew = p.matcher(new_security_level_field.getText().trim());
            		System.out.println(result.getString(1));
        			if(result.getString(1).equals(old_security_level_field.getText().trim())&&matchold.matches()&&matchnew.matches()&&old_security_level_field.getText().trim()!=new_security_level_field.getText().trim()) {
        				if(Information_about_room_text.getText().trim().equals("Information about room")) {
        					dbHandler.changeSLRoom(new_security_level_field.getText().trim(), id_field.getText().trim());
        					result = dbHandler.getRoom(id);
            				room_information_area.setText(showData(result));
                		}else {
                			dbHandler.changeSLEmployee(new_security_level_field.getText().trim(), id_field.getText().trim());
                			Employee emp= new Employee();
                			emp.setID(ID);
                			result = dbHandler.getEmployee(emp, false);
            				room_information_area.setText(showData(result));
                		}
        				
        				System.out.println("Changed");
        				success_security_level_changes_text.setVisible(true);
        				new_security_level_field.setText("");
        				new_security_level_field.setPrefWidth(230);
        				old_security_level_field.setText("");
        			}else {
        				success_security_level_changes_text.setVisible(false);
        				Shake new_security_level_field_shake = new Shake(new_security_level_field);
        				Shake old_security_level_field_shake = new Shake(old_security_level_field);
        				new_security_level_field_shake.playAnim();
        				old_security_level_field_shake.playAnim();
        				System.out.println("NO");
        			}
        			
    				
    			} catch (SQLException e) {
    				
    				e.printStackTrace();
    			}
    		}else {
    			Shake new_security_level_field_shake = new Shake(new_security_level_field);
				Shake old_security_level_field_shake = new Shake(old_security_level_field);
				new_security_level_field_shake.playAnim();
				old_security_level_field_shake.playAnim();
				System.out.println("NO");
    		}
    		
    		
    	});
    	
    	
    	
    	Show_list_of_visits_button.setOnAction(event->{//действие при нажатии на кнопку показа списка посещени€ дл€ определенной комнаты
    		list_of_visits_area.setVisible(true);
    		list_of_visits_text.setVisible(true);
    		String roomID=id_field.getText().trim();
    		int id = Integer.parseInt (roomID);
    		DatabaseHandler dbHandler=new DatabaseHandler();
    		ResultSet result = dbHandler.getListofVisits(id);
    		
    		try {

    			list_of_visits_area.setText(showData(result));
    			
			} catch (SQLException e) {
				e.printStackTrace();
			}
    	});
    	
    	
    	add_eemployee_button.setOnAction(event->{//действие при нажатии кнопки добавлени€ нового сотрудника
    		try {
                Parent root;
                root = FXMLLoader.load(getClass().getResource("addUser.fxml"));
                Stage window=(Stage) add_eemployee_button.getScene().getWindow();
                window.setScene(new Scene(root));
            } catch (IOException e) {
                e.printStackTrace();
            }
    	});
    	
    	
    	end_interaction_button.setOnAction(event->{	//действие при нажатии кнопки окончани€ определенного интерактива
    		delete_employee_button.setDisable(false);
    		Back_button.setVisible(true);
    		choose_employee_button.setVisible(true);
    		choose_employee_button.setDisable(false);
			success_security_level_changes_text.setVisible(false);
    		end_interaction_button.setVisible(false);
    		choose_room_button.setVisible(true);
    		choose_room_button.setDisable(false);
    		choose_button.setVisible(false);
    		choose_button.setDisable(false);
    		id_field.setText("");
    		id_field.setVisible(false);
    		id_field.setDisable(false);
    		Show_list_of_visits_button.setVisible(false);
			Change_security_level_button.setVisible(false);
			room_information_area.setVisible(false);
			rooms_information_area.setVisible(false);
			Information_about_room_text.setVisible(false);
			Information_about_room_text.setText("");
			new_security_level_field.setVisible(false);
			old_security_level_field.setVisible(false);
			confirm_new_level_button.setVisible(false);
			list_of_visits_area.setVisible(false);
			list_of_visits_text.setVisible(false);
			show_all_info_button.setVisible(true);
			add_eemployee_button.setVisible(true);
			choose_table_button.setVisible(false);
    		choose_table_box.setVisible(false);
    		show_all_info_button.setDisable(false);
    		table_information_area.setVisible(false);
    		Change_security_level_button.setDisable(false);
    		delete_employee_button.setVisible(false);
    		confirm_delete_button.setVisible(false);
    		cansel_delete_button.setVisible(false);
    		success_delete_button.setVisible(false);
    	});
    }
    
    
    
    private String showData(ResultSet result) throws SQLException {//метод дл€ показа данных из таблицы
    	ResultSetMetaData rsmd = result.getMetaData();
		int numberOfColumns = rsmd.getColumnCount();
		String text  = "";
		for (int i = 1; i < numberOfColumns + 1; i++) { //получаем названи€ столбцов
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