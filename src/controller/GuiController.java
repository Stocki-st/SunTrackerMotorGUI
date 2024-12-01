package controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.URL;
import java.util.ResourceBundle;

import communication.Drive;
import communication.MotorCom;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Control;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;

public class GuiController implements Initializable {

	public enum Endswitches {ES_UP, ES_DOWN, ES_LEFT, ES_RIGHT}
	final static int DEFAUTL_PORT = 7777;
	byte msg_id = 0;

	private final static int MAX_STEPS = 1024;


	//Types
	private final static byte type_SET 		= 0x01;
	private final static byte type_GET 		= 0x02;
	private final static byte type_REPLY 	= 0x04;
	//Commands
	private final static byte cmd_DRIVE 	= 0x01;
	private final static byte cmd_END_STATE = 0x01;
	private final static byte cmd_INIT		= 0x01;
	private final static byte cmd_INIT_END 	= 0x01;
	private final static byte cmd_STATE		= 0x01;
	//Direktions
	private final static byte dir_UP 		= 0x01;
	private final static byte dir_DOWN 		= 0x02;
	private final static byte dir_RIGHT 	= 0x04;
	private final static byte dir_LEFT 		= 0x08;
	private final static byte dir_STOP 		= 0x10;
	//States
	private final static byte state_ON 		= (byte)0xFF;
	private final static byte state_OFF 	= 0x00;
	//Init
	private final static byte init_START 	= (byte)0xFF;
	private final static byte init_STOP 	= 0x00;



/*
	try(
			Socket clientSocket = new Socket("127.0.0.1", DEFAUTL_PORT);
			PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
			BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
	) {
		out.println("Your are now connected on Port " + DEFAUTL_PORT + " - Welcome!");
		String inputLine;
		while ((inputLine = in.readLine()) != null) {
			System.out.println(inputLine);
		}
	} catch (IOException e) {
		System.out.println("Error catched:" + e.getMessage());
	}

 */




	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		EndswitchInit();
	}

	@FXML
	private Button btnRight, btnLeft, btnUp, btnDown;

	@FXML
	private Label stateES_Right, stateES_Left, stateES_Up, stateES_Down, textBar;

	@FXML
	private TextField textField_Steps;



	public void changeEndswitchText(Endswitches endswitch, Boolean state){
		switch(endswitch){
		case ES_UP:
			if(state){
				stateES_Up.setText("active");
				stateES_Up.setTextFill(Color.RED);
			}else{
				stateES_Up.setText("inactive");
				stateES_Up.setTextFill(Color.GREEN);
			}
			break;
		case ES_DOWN:
			if(state){
				stateES_Down.setText("active");
				stateES_Down.setTextFill(Color.RED);
			}else{
				stateES_Down.setText("inactive");
				stateES_Down.setTextFill(Color.GREEN);
			}
			break;
		case ES_LEFT:
			if(state){
				stateES_Left.setText("active");
				stateES_Left.setTextFill(Color.RED);
			}else{
				stateES_Left.setText("inactive");
				stateES_Left.setTextFill(Color.GREEN);
			}
			break;
		case ES_RIGHT:
			if(state){
				stateES_Right.setText("active");
				stateES_Right.setTextFill(Color.RED);
			}else{
				stateES_Right.setText("inactive");
				stateES_Right.setTextFill(Color.GREEN);
			}
			break;
		default:
			break;
		}
	}

	public void EndswitchInit(){
		changeEndswitchText(Endswitches.ES_DOWN, false);
		changeEndswitchText(Endswitches.ES_UP, false);
		changeEndswitchText(Endswitches.ES_RIGHT, false);
		changeEndswitchText(Endswitches.ES_LEFT, false);
	}

	public void buttonPressed(ActionEvent event) throws IOException{
		byte st[] = new byte[13];
		String buttonId = ((Control)event.getSource()).getId();
		System.out.println( buttonId + " pressed");
		char stepVal = getSteps();
		switch(buttonId){
			case "btnUp":
				st[0] = msg_id;
				st[1] = type_SET;
				st[2] = cmd_DRIVE;
				st[3] = (byte) 0xFF;
				st[4] = (byte) 0xFF;
				st[5] = (byte) 0x03;
				st[6] = dir_UP;
				if(stepVal > 0xFF) {
					st[7]= (byte) (stepVal >> 8);
					st[8]= (byte) (stepVal);
				}else{
					st[7]= (byte) 0x00;
					st[8]= (byte) (stepVal);
				}
				st[9] = (byte) '\0';
				break;
			case "btnDown":
				st[0] = msg_id;
				st[1] = type_SET;
				st[2] = cmd_DRIVE;
				st[3] = (byte) 0xFF;
				st[4] = (byte) 0xFF;
				st[5] = (byte) 0x03;
				st[6] = dir_DOWN;
				if(stepVal > 0xFF) {
					st[7]= (byte) (stepVal >> 8);
					st[8]= (byte) (stepVal);
				}else{
					st[7]= (byte) 0x00;
					st[8]= (byte) (stepVal);
				}
				st[9] = (byte) '\0';
				break;
			case "btnRight":
				st[0] = msg_id;
				st[1] = type_SET;
				st[2] = cmd_DRIVE;
				st[3] = (byte) 0xFF;
				st[4] = (byte) 0xFF;
				st[5] = (byte) 0x03;
				st[6] = dir_RIGHT;
				if(stepVal > 0xFF) {
					st[7]= (byte) (stepVal >> 8);
					st[8]= (byte) (stepVal);
				}else{
					st[7]= (byte) 0x00;
					st[8]= (byte) (stepVal);
				}
				st[9] = (byte) '\0';
				break;
			case "btnLeft":
				st[0] = msg_id;
				st[1] = type_SET;
				st[2] = cmd_DRIVE;
				st[3] = (byte) 0xFF;
				st[4] = (byte) 0xFF;
				st[5] = (byte) 0x03;
				st[6] = dir_LEFT;
				if(stepVal > 0xFF) {
					st[7]= (byte) (stepVal >> 8);
					st[8]= (byte) (stepVal);
				}else{
					st[7]= (byte) 0x00;
					st[8]= (byte) (stepVal);
				}
				st[9] = (byte) '\0';
				break;
			default:
				st[0] = msg_id;
				st[1] = type_SET;
				st[2] = cmd_DRIVE;
				st[3] = (byte) 0xFF;
				st[4] = (byte) 0xFF;
				st[5] = (byte) 0x01;
				st[6] = dir_STOP;
				st[7] = (byte) '\0';
				break;
		}
		System.out.println(javax.xml.bind.DatatypeConverter.printHexBinary(st));
		msg_id++;
		msg_id = (byte) (msg_id % 0xFF);
	}

	public char getSteps(){
		char Steps = 0;
		String numberOfSteps = textField_Steps.getText();
		try{
		Steps = (char) Integer.parseInt(numberOfSteps);
		}catch (Exception e) {
			Steps = 1;
		}
		if (Steps<1)
			Steps = 1;
		if(Steps> MAX_STEPS)
			Steps = MAX_STEPS;
		textField_Steps.setText(Integer.toString(Steps));
		System.out.println("Steps " + Steps);
		return Steps;
		}
}
