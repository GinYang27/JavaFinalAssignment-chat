//package FinalProject;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.Reader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

public class Sever3 extends Application{
	@Override
	public void start(Stage primaryStage){
		//Initiate GUI
		WPane sePane = new WPane();
		Scene scene = new Scene(sePane, 450, 300);
		primaryStage.setScene(scene);
		primaryStage.setTitle("Server");
		primaryStage.show();
		
		
		//it is not appropriate to run server socket accept() in JAVAFX
		//-Application thread. So place it in a separate thread
		
		new Thread(() -> {
			try {
				//establish connection
				ServerSocket serverSocket = new ServerSocket(8000);
				//update GUI
				Platform.runLater(() -> sePane.getServerTA().appendText("***SERVER STARTED AT "
						+ new Date() + "***"+'\n'));
				
				//listen for a conncetion request
				 Socket socket = serverSocket.accept();
				
				//Create string input and output streams
				 BufferedReader inputFromClient =
					        new BufferedReader(
					            new InputStreamReader(socket.getInputStream()));
				 PrintWriter outputToClient =
					        new PrintWriter(socket.getOutputStream(), true);
				 
				 sePane.getClientTA().setOnKeyPressed(new EventHandler<KeyEvent>(){
					 @Override
					 public void handle(KeyEvent keyEvent){
						 if(keyEvent.getCode().equals(KeyCode.ENTER)){
								
							 String text = sePane.getClientTA().getText();
							 outputToClient.write(text);
							 outputToClient.write("\r\n"); //line break
							 outputToClient.flush();
							 sePane.getClientTA().clear();
						 }
					 }
						
				 });
				 
				 while(true){
					 //Receive content from the client
					
					 String text = inputFromClient.readLine();
					
					 //update GUI
					 Platform.runLater(() -> {
						 sePane.getServerTA().appendText(text + '\n');
					 });
				 }
				 
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}).start();
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		launch(args);

	}

}
