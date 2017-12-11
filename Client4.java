//package FinalProject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Client4 extends Application{
	
	PrintWriter toServer = null;
	BufferedReader fromServer = null;
	
	@Override
	public void start(Stage primaryStage){
		MulPane pane = new MulPane();
		
		Scene scene = new Scene(pane,400,200);
		primaryStage.setTitle("Client");
		primaryStage.setScene(scene);
		primaryStage.show();
		
		new Thread(() -> {
			pane.getEntrTF().setOnAction(e -> {
				String textName = pane.getNameTF().getText();
				String textEntr = pane.getEntrTF().getText();
				
				String text = textName + ": "+ textEntr;
				
				toServer.write(text);
				toServer.write("\r\n");
				toServer.flush();
			});
			
			try {
				Socket socket = new Socket("localhost", 8000);
				toServer = new PrintWriter(socket.getOutputStream(),true);
				fromServer = new BufferedReader(new InputStreamReader(socket.getInputStream()));
				
				while(true){
					
					String text = fromServer.readLine();
					
					 Platform.runLater(() -> {
						 pane.getTA().appendText(text + '\n');
					 });
				}
				
				
			} catch (UnknownHostException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
		}).start();
		
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		launch(args);
	}

}
