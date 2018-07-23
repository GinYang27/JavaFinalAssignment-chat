//package FinalProject;

import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

public class WPane extends VBox{
	private Label lblServer = new Label();
	private Label lblClient = new Label();
	
	private TextArea taServer = new TextArea();
	private TextArea taClient = new TextArea();
//	private TextField tfClient = new TextField();
	
	
	private Font lblFont = new Font("Times New Roman", 16);
	private Font taFont = new Font("Serif",14);
	
	public WPane(){
		// Set font for all the label
		super(5);
		lblServer.setFont(lblFont);
		lblClient.setFont(lblFont);
		taServer.setFont(taFont);
		taClient.setFont(taFont);
		
		lblServer.setText("Server");
		lblClient.setText("Client");
		
		//Server's TextArea is not editable, Client's is editable
		taServer.setEditable(false);
		taClient.setEditable(true);
		
		//Both are WrapText
		taServer.setWrapText(true);
		taClient.setWrapText(true);
		
		ScrollPane scPaneSe = new ScrollPane(taServer);
		ScrollPane scPaneCl = new ScrollPane(taClient);
//		ScrollPane scPaneCl = new ScrollPane(tfClient);
		super.getChildren().addAll(lblServer, scPaneSe, lblClient, scPaneCl);
		
		super.setPadding(new Insets(5,5,5,5));
		
		
		
		
		
		
	}
	
	public TextArea getServerTA(){
		return taServer;
	}
	
	public TextArea getClientTA(){
		return taClient;
	}
	
/*	public TextField getClientTF(){
		return tfClient;
	}*/
	

}
