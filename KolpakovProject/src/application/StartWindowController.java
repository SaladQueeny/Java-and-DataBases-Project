package application;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import com.mysql.cj.xdevapi.Result;

import animations.Shake;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

public class StartWindowController extends EmployeeInfo {
	ObservableList<String> infoOption =  FXCollections.observableArrayList("Employee","Admin");//arraylist для выбора роли
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField login_field;

    @FXML
    private PasswordField password_field;

    @FXML
    private Button sign_in_button;

    @FXML
    private ComboBox<String> choose_role_box;

    @FXML
    void initialize() {
    	choose_role_box.setItems(infoOption);//добавление ролей в комбобокс
    	sign_in_button.setOnAction(event->{//действие при нажатии на кнопку войти
    		if(choose_role_box.getValue()==null) {
    			Shake choose_buttonShake= new Shake(choose_role_box);
    			choose_buttonShake.playAnim();
    		}else if(!login_field.getText().trim().equals("")&&!password_field.getText().trim().equals("")) {
    			try {
					loginUser();
				} catch (Exception e) {
					e.printStackTrace();
				}
    		}else {
    			Shake userLoginAnimation = new Shake(login_field);
	    		Shake userPasswordAnimation = new Shake(password_field);
	    		userLoginAnimation.playAnim();
	    		userPasswordAnimation.playAnim();
    		}
    	});
    	
    }
    
    @FXML
    private void loginUser() throws Exception {//метод для входа пользователя
    	String loginText=login_field.getText().trim();
		String passwordText=password_field.getText().trim();
		if(choose_role_box.getValue().equals("Admin")) {
    		int counter=0;			
	    	DatabaseHandler dbHandler=new DatabaseHandler();
	    	Admin adm=new Admin();
	    	adm.setName(loginText);
	    	adm.setPassword(passwordText);
	    	ResultSet result = dbHandler.getAdmin(adm);
	    	
	    	try {
				while(result.next()) {
					counter++;
				}
			} catch (SQLException e) {
				
				e.printStackTrace();
			}
	    	if(counter==1) {
	    		System.out.println("good!");
	    		openNewScene("AdminMenu");
	    	}else {
	    		System.out.println("not good!");
	    		Shake userLoginAnimation = new Shake(login_field);
	    		Shake userPasswordAnimation = new Shake(password_field);
	    		userLoginAnimation.playAnim();
	    		userPasswordAnimation.playAnim();
	    	}
		} else if(choose_role_box.getValue().equals("Employee")){
			
			int counter=0;			
	    	DatabaseHandler dbHandler=new DatabaseHandler();
	    	Employee emp=new Employee();
	    	emp.setFirst_Name(loginText);
	    	emp.setPassword(passwordText);
	    	ResultSet result = dbHandler.getEmployee(emp, true);
	    	
	    	try {
				while(result.next()) {
					counter++;
				}
			} catch (SQLException e) {
				
				e.printStackTrace();
			}
	    	if(counter==1) {
	    		EmployeeInfo.NameString=loginText;
	    		EmployeeInfo.PasswordString=passwordText;
	    		System.out.println("good!");
	    		openNewScene("EmployeeMenu");
	    		
	    	}else {
	    		System.out.println("not good!");
	    		Shake userLoginAnimation = new Shake(login_field);
	    		Shake userPasswordAnimation = new Shake(password_field);
	    		userLoginAnimation.playAnim();
	    		userPasswordAnimation.playAnim();
	    	}
		}else {
			System.out.println("not good!");
    		Shake userLoginAnimation = new Shake(login_field);
    		Shake userPasswordAnimation = new Shake(password_field);
    		userLoginAnimation.playAnim();
    		userPasswordAnimation.playAnim();
		}
    }
    
    @FXML
    protected void openNewScene(String scene) throws Exception{//метод для открытия новой сцены
    	scene=scene+".fxml";
		Parent root = FXMLLoader.load(getClass().getResource(scene));
		Stage window=(Stage) sign_in_button.getScene().getWindow();
		window.setScene(new Scene(root));	
	}
}
