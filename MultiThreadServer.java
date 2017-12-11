//package FinalProject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Date;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

public class MultiThreadServer extends Application{
	private TextArea ta = new TextArea();
	
	private int clientNo = 0;
	
	public ArrayList<HandleAClient> clientThread;
	
	@Override
	public void start(Stage primaryStage){
		Scene scene = new Scene(new ScrollPane(ta), 400, 150);
		primaryStage.setTitle("MultiThreadServer");
		primaryStage.setScene(scene);
		primaryStage.show();
		
		new Thread(() -> {
			try {
				//Create a server socket
				ServerSocket serverSocket = new ServerSocket(8000);
				
				this.clientThread = new ArrayList<HandleAClient>();
				this.clientThread.clear();
				Platform.runLater(() ->
						ta.appendText("MultiThreadServer started at " + new Date() + '\n'));
				
				while(true){
					//Listen for a new connection request
					Socket socket =serverSocket.accept();
					clientNo++;
				
					Platform.runLater(() -> {
						ta.appendText("Client"+clientNo+" Connected at "+new Date()+'\n');
					});
					
					//Create and start a new thread for the connection
					HandleAClient newClient = new HandleAClient(socket);
					new Thread(newClient).start();
					this.clientThread.add(newClient);
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}).start();
	}
	
	//Define the thread class for handlog new connection
	class HandleAClient implements Runnable{
		private Socket socket;
		BufferedReader inputFromClient;
		       
		 PrintWriter outputToClient ;
		
		public HandleAClient(Socket socket){
			this.socket = socket;
		}
		
		 public void out(String tx){
			 outputToClient.write(tx);
			 outputToClient.write("\r\n");
			 outputToClient.flush();
		 }
		
		/**Run a thread*/
		public void run(){
			try {
				inputFromClient =
				        new BufferedReader(
				            new InputStreamReader(socket.getInputStream()));
				outputToClient =
				        new PrintWriter(socket.getOutputStream(), true);
				
				 
				
				 
				 //Continuously server the client
				 while(true){
					 //receive
					 String text = inputFromClient.readLine();
					 
/*					 outputToClient.write(text);
					 outputToClient.write("\r\n");
					 outputToClient.flush();*/
					 for(int i = 0; i< clientThread.size();i++){
						 clientThread.get(i).out(text);
						 
					 }
					 
						
					 //update GUI
/*					 Platform.runLater(() -> {
						 ta.appendText(text + '\n');
					 });*/
				 }
				 
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
			
		}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		launch(args);

	}

}
