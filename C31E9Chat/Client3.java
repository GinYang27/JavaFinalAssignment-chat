//package FinalProject;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

public class Client3 extends Application{
	
	PrintWriter toServer = null;
	BufferedReader fromServer = null;
	
	@Override
	public void start(Stage primaryStage){
		WPane clPane = new WPane();
		
		Scene scene = new Scene(clPane, 450, 300);
		primaryStage.setTitle("Client");
		primaryStage.setScene(scene);
		primaryStage.show();
		
		new Thread(() -> {
			//Send text from TextArea to Server
			clPane.getClientTA().setOnKeyPressed(new EventHandler<KeyEvent>(){
				@Override
				public void handle(KeyEvent keyEvent){
					if(keyEvent.getCode().equals(KeyCode.ENTER)){
						
						String text = clPane.getClientTA().getText();
						toServer.write(text);
						toServer.write("\r\n"); //line break
						toServer.flush();
						clPane.getClientTA().clear();
					}
				}
				
			});
			
			try {
				Socket socket = new Socket("localhost", 8000);
				toServer = new PrintWriter(socket.getOutputStream(),true);
				fromServer = new BufferedReader(new InputStreamReader(socket.getInputStream()));
				
				 while(true){
					 //Receive content from the client
					
					 String text = fromServer.readLine();
					
					 //update GUI
					 Platform.runLater(() -> {
						 clPane.getServerTA().appendText(text + '\n');
					 });
				 }

				
			} catch (UnknownHostException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
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
