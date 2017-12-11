//package FinalProject;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

public class MulPane extends VBox{
	private Label name = new Label();
	private Label entr = new Label();
	
	private TextField tName = new TextField();
	private TextField tEntr = new TextField();
	
	private TextArea table = new TextArea();
	
	GridPane pane = new GridPane();// upper half
	
	public MulPane(){
		name.setText("Name");
		entr.setText("Enter text");
		
		pane.setAlignment(Pos.TOP_LEFT);
		pane.setPadding(new Insets(2,5,3,3));
		pane.setHgap(5);
		pane.setVgap(5);
		
		pane.add(name, 0, 0);
		pane.add(tName, 1, 0);
		pane.add(entr, 0, 1);
		pane.add(tEntr, 1, 1);
		
		
		table.setEditable(false);
		table.setWrapText(true);
		
		ScrollPane scTa = new ScrollPane(table);
		
		super.getChildren().addAll(pane,scTa);
		
	}
	
	public TextField getNameTF(){
		return tName;
	}
	
	public TextField getEntrTF(){
		return tEntr;
	}
	
	public TextArea getTA(){
		return table;
	}
	
	
	
	
	

}
