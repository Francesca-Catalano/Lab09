
package it.polito.tdp.borders;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.Set;

import it.polito.tdp.borders.model.Country;
import it.polito.tdp.borders.model.Model;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class FXMLController {

	private Model model;
	
    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="txtAnno"
    private TextField txtAnno; // Value injected by FXMLLoader

    @FXML // fx:id="txtResult"
    private TextArea txtResult; // Value injected by FXMLLoader

    @FXML
    private ComboBox<Country> ComboBox;

    @FXML
    private Button btnRicerccaStati;
    
    @FXML
    void doRicercaStati(ActionEvent event) {
    	if (this.ComboBox.getValue()==null && this.txtAnno==null)
    	{this.txtResult.appendText("Inserire prima un anno e poi uno stato!\n");
    	return;}
    	if (this.ComboBox.getValue()==null )
    	{this.txtResult.appendText("Inserire  uno stato!\n");
    	return;}
    Set<Country> list = 	this.model.getListaVicini2(this.ComboBox.getValue());
    if(list==null)
    {this.txtResult.appendText("Nessun vicino trovato\n");
	return;}
    this.txtResult.appendText("Lista di stati vicini : \n");
    for(Country c : list)
    {
    	  this.txtResult.appendText(c+"\n");
    }
    
    this.txtResult.appendText(list.size()+"\n");
    

    }

    @FXML
    void doCalcolaConfini(ActionEvent event) {
    	int x ;
    	try
    	{
    		x= Integer.parseInt(this.txtAnno.getText());
    	} catch(NumberFormatException e)
    	{
    		this.txtResult.setText("Formato non valido\n");
    		return;
    	}
    	if(x < 1816 || x >2016)
    	{
    		this.txtResult.setText("Inserire un anno compreso tra 1816 e 2016!\n");
    		this.txtAnno.clear();
    		return;
    	}
    	//calcolo dei confini 
    	this.model.creaGrafo(x);
    	this.ComboBox.getItems().addAll(this.model.getGrafo().vertexSet());
    	if(this.model.VertixSize()==0 || this.model.getGrafo().vertexSet()==null)
    	{
    		this.txtResult.appendText("Errore! Il grafo non presente vertici!\n");
    		return;
    	}
    	if(this.model.EdgeSize()==0)
    	{
    		this.txtResult.appendText("Errore! Il grafo non presente archi!\n");
    		return;
    	}
    	this.txtResult.appendText("Numero Vertivi : "+ this.model.VertixSize()+"\n");
    	this.txtResult.appendText("Numero Archi : "+ this.model.EdgeSize()+"\n");
    	
    	
    	for( Country c : this.model.getGrafo().vertexSet())
    	{
    		this.txtResult.appendText(c.getName()+"  : "+ this.model.getGrafo().degreeOf(c)+"\n" );
    	}
    	
    	if(this.model.getNumberComponentiConnesse()==0)
    	{
    		this.txtResult.appendText("Errore: nessuna componente connessa trovata!\n");
    		return;
    	}
    	
    	this.txtResult.appendText("Numero di componenti connesse : "+this.model.getNumberComponentiConnesse()+"\n" );

    }


    @FXML
    void initialize() {
        assert txtAnno != null : "fx:id=\"txtAnno\" was not injected: check your FXML file 'Scene.fxml'.";
        assert ComboBox != null : "fx:id=\"ComboBox\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnRicerccaStati != null : "fx:id=\"btnRicerccaStati\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtResult != null : "fx:id=\"txtResult\" was not injected: check your FXML file 'Scene.fxml'.";

    }
    
    public void setModel(Model model) {
    	this.model = model;
    }
}
