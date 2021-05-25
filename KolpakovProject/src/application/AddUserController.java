package application;
// олпаков ≈гор
import java.io.IOException;
import java.net.URL;
import java.nio.file.FileSystemNotFoundException;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import animations.Shake;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class AddUserController {

	@FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button Back_button;

    @FXML
    private TextField ID_field;

    @FXML
    private TextField firstName_field;

    @FXML
    private TextField lastName_field;

    @FXML
    private TextField email_field;

    @FXML
    private TextField telephone_field;

    @FXML
    private TextField passportID_filed;

    @FXML
    private Button addUser_button;

    @FXML
    private TextField securityLevel_field;

    @FXML
    private Text added_text;

    @FXML
    private Text not_added_text;
    
    @FXML
    void initialize() {
    	added_text.setVisible(false);
    	
    	not_added_text.setVisible(false);
    	
        addUser_button.setOnAction(event->{//ƒействие при нажатии кнопки добавление пользовател€
        	boolean good = false;
        	Pattern pid = Pattern.compile("[0-9]{4,}");//шаблоны дл€ проверки данных
        	Pattern pFN = Pattern.compile("[a-zA-Z]{2,}");
        	Pattern pLN= Pattern.compile("[a-zA-Z]{2,}");
        	Pattern pmail = Pattern.compile("[A-Za-z]+@{1}[A-Za-z]+.{1}[A-Za-z]+");
        	Pattern ptele = Pattern.compile("[+]?[7-8][0-9]{3}[0-9]{3}-?[0-9]{2}-?[0-9]{2}");
        	Pattern pPassID = Pattern.compile("[0-9]{5,}");
        	Pattern pSL = Pattern.compile("[0-9]{1}");
    		Matcher match = null;
    		match = pid.matcher(ID_field.getText().trim());
    		if(match.matches()) {
    			System.out.println(match.matches());
    			match = pFN.matcher(firstName_field.getText().trim());
    			if(match.matches()) {
    				System.out.println(match.matches());
    				match = pLN.matcher(lastName_field.getText().trim());
    				if(match.matches()) {
    					System.out.println(match.matches());
    					match = pmail.matcher(email_field.getText().trim());
    					if(match.matches()) {
    						System.out.println(match.matches());
    						match = ptele.matcher(telephone_field.getText().trim());
    						if(match.matches()) {
    							System.out.println(match.matches());
    							match = pPassID.matcher(passportID_filed.getText().trim());
    							if(match.matches()) {
    								System.out.println(match.matches());
    								match = pSL.matcher(securityLevel_field.getText().trim());
    								if(match.matches()) {
    									System.out.println(match.matches());
    					    			good=true;
    					    		}
    				    		}
    			    		}
    		    		}
    	    		}
        		}
    		}
    		System.out.println(good);
    		//проверка дл€ добавлени€ пользовател€
        	if(good&&!(ID_field.getText().trim().equals("")||firstName_field.getText().trim().equals("")||lastName_field.getText().trim().equals("")||email_field.getText().trim().equals("")||telephone_field.getText().trim().equals("")||passportID_filed.getText().trim().equals("")||securityLevel_field.getText().trim().equals(""))){
        		System.out.println("all ok");
        		AddUser();//добавление пользовател€
        	}else {
        		Shake idShake = new Shake(ID_field);//тр€ска элементов если проверка не пройдена
        		Shake fnShake = new Shake(firstName_field);
        		Shake lnShake= new Shake(lastName_field);
        		Shake emailShake= new Shake(email_field);
        		Shake telephoneShake= new Shake(telephone_field);
        		Shake passportShake= new Shake(passportID_filed);
        		Shake securityShake= new Shake(securityLevel_field);
        		idShake.playAnim();
        		fnShake.playAnim();
        		lnShake.playAnim();
        		emailShake.playAnim();
        		telephoneShake.playAnim();
        		passportShake.playAnim();
        		securityShake.playAnim();
        	}
        	
        });
        
        Back_button.setOnAction(event->{//действие при нажатии кнопки возвращени€ в меню админа
        	
			try {
				Parent root;
				root = FXMLLoader.load(getClass().getResource("AdminMenu.fxml"));
				Stage window=(Stage) Back_button.getScene().getWindow();
	    		window.setScene(new Scene(root));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    		
    	});
        

    }

    public void AddUser() {//запрос с добавлением пользовател€
    	
    	String ID=ID_field.getText().trim();
    	String First_Name=firstName_field.getText().trim();
    	String Last_Name=lastName_field.getText().trim();
    	String email=email_field.getText().trim();
    	String Telephone_number=telephone_field.getText().trim();
    	String PassportID=passportID_filed.getText().trim();
    	String password=First_Name+ID;
    	String securityLevel=securityLevel_field.getText().trim();
    	System.out.println(ID+First_Name+Last_Name+email+Telephone_number+PassportID+password+securityLevel);
    	Employee employee = new Employee(ID, First_Name, Last_Name, email, Telephone_number, PassportID, password, securityLevel);
        DatabaseHandler dbHandler = new DatabaseHandler();
		if(dbHandler.addUser(employee)) {//успешное добавление пользовател€
			added_text.setVisible(true);
			added_text.setDisable(false);
			not_added_text.setVisible(false);
			not_added_text.setDisable(true);
			ID_field.setText("");
			firstName_field.setText("");;
			lastName_field.setText("");;
			email_field.setText("");;
			telephone_field.setText("");;
			passportID_filed.setText("");;
			securityLevel_field.setText("");;
		}else {//не успешное
			Shake idShake = new Shake(ID_field);
    		Shake fnShake = new Shake(firstName_field);
    		Shake lnShake= new Shake(lastName_field);
    		Shake emailShake= new Shake(email_field);
    		Shake telephoneShake= new Shake(telephone_field);
    		Shake passportShake= new Shake(passportID_filed);
    		Shake securityShake= new Shake(securityLevel_field);
    		idShake.playAnim();
    		fnShake.playAnim();
    		lnShake.playAnim();
    		emailShake.playAnim();
    		telephoneShake.playAnim();
    		passportShake.playAnim();
    		securityShake.playAnim();
			not_added_text.setVisible(true);

			added_text.setVisible(false);

		}
    	

	}
}
