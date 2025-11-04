/**
 * 
 */
package ux;

import java.io.File;
import java.io.FileNotFoundException;
import java.text.ParseException;
import java.util.HashSet;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import worker.Person;
import worker.PrintHandler;
import worker.Role;

/**
 * 
 */
public class ManagementWindow extends Application {

	/**
	 * @author Carson Fujita
	 * The path we use
	 */
	private static String PATH = "work_hours.txt";

	/**
	 * @author Carson Fujita
	 * the registry printer
	 */
	private PrintHandler<WorkRegistry> printHandler;
	
	
	/**
	 * @author Carson Fujita 
	 * the registry manager and working
	 */
	private WorkRegistry workingRegistry = new WorkRegistry(new HashSet<Person>());
	
	//TODO MOVE these
	private TextField txtName;
	private TextField txtRole;
	private TextField txtTotalHours;
	private Label errMessage;
	private Label prvRecords;

	@Override
	public void start(Stage primary) throws Exception {
		load(primary);
	}

	//TODO move this
	public void load(Stage primary) {
		errMessage = new Label("Loading...");
		Scene loading = new Scene(errMessage, 200, 500);
		
		//Set top
		primary.setAlwaysOnTop(true);
		
		//center
		primary.centerOnScreen();
		
		//add scene and title
		primary.setTitle("Loading");
		primary.setScene(loading);
		primary.show();

		//begin loading
		try {
			printHandler = new PrintHandler<WorkRegistry>(new File(ManagementWindow.PATH));
			printHandler.loadTo(workingRegistry); //read file
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		// begin loading the next scene
		txtName = new TextField();
		txtRole = new TextField();
		txtTotalHours = new TextField();
		prvRecords = new Label();
	

		VBox formItems = new VBox();
		formItems.getChildren().add(errMessage);
		formItems.getChildren().add(txtName);
		formItems.getChildren().add(txtRole);
		formItems.getChildren().add(txtTotalHours);
		Button submit = new Button();
		submit.setOnAction(new EventHandler<ActionEvent>(){
			@Override
			public void handle(ActionEvent event) {
				//Store in heap? probbably not a good idea
				Object[] values = {
						validateName(txtName.getText()),
						validateRole(txtRole.getText()),
						validateHours(txtTotalHours.getText()),
				};
			
				// Fail if any null
				for(Object value: values) {
					if(value == null) {
						return;
					}
				}	
				
				
				
			}
		
			/**
			 * validates the name...
			 * @param name
			 * @return
			 */
			private String validateName(String name) {			
				name = name.trim().toLowerCase(); // remove leading an trailing space
				return name;
			}
			
			
			/**
			 * Validages the role
			 * @param role 
			 * @return null if invalid, Role if valid
			 */
			private Role validateRole(String role) {
				role = role.trim().toLowerCase();
				try {
					return Role.parseString(role);
				} catch (ParseException e) {
					errMessage.setText(e.getMessage());
					return null;
				}
				
			}
		
			/**
			 *  Validates the hours
			 * @param hours
			 * @return null if invalid, Integer if valid
			 */
			private Integer validateHours(String hours) {
				hours = hours.trim().toLowerCase();
				hours = hours.replace("hrs", "");
				try {
					return Integer.parseInt(hours);
				} catch(NumberFormatException e) {
					errMessage.setText(e.getMessage());
					return null;
				}
			}
			
		});	
		formItems.getChildren().add(prvRecords);
		formItems.getChildren().add(submit);
		Scene form = new Scene(formItems);
		primary.setScene(form);
	}

}
