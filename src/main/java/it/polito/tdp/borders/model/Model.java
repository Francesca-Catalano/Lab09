package it.polito.tdp.borders.model;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;

import org.jgrapht.Graphs;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleGraph;

import it.polito.tdp.borders.db.BordersDAO;

public class Model {

	private SimpleGraph<Country, DefaultEdge> graph ;
	private Map<Integer,Country> map;
	private BordersDAO dao;


	public Model() {
		this.graph= new SimpleGraph<>(DefaultEdge.class);
		map = new HashMap<>();
		dao= new BordersDAO();
		dao.loadAllCountries(map);
	}

	/**
	 * @param anno
	 */
	public void creaGrafo(int anno)
	{
		  List<Border> confini = this.dao.getCountryPairs(anno, map);
		  if(confini.size()==0) { System.out.print("Non sono presenti confini");
		  return; } 
		  for(Border b : confini)
		  { 
			  if(!this.graph.containsVertex(b.getC1()))
			  {this.graph.addVertex(b.getC1());}
			  if(!this.graph.containsVertex(b.getC2()))
		      this.graph.addVertex(b.getC2()); 
			
			  
			  if(this.graph.containsVertex(b.getC1()) &&  this.graph.containsVertex(b.getC2()))
				 {DefaultEdge e = this.graph.getEdge(b.getC1(),b.getC2());
				if(e==null)
				{
					this.graph.addEdge(b.getC1(), b.getC2());
				}
		  
				 }
		
		 
		
		  }
		  System.out.print(this.graph);
	}
	

	
	public int VertixSize()
	{
		return this.graph.vertexSet().size();
	}
	public int EdgeSize()
	{
		return this.graph.edgeSet().size();
	}
	public SimpleGraph<Country, DefaultEdge> getGrafo()
	{return this.graph;}

	//grado vertice = Il numero di archi incidenti in un vertice v ∈ V (cioè il numero di archi che si connettono ad esso) prende il nome di grado del vertice v. Un arco che si connette al vertice ad entrambe le estremità (un cappio) è contato due volte.
}
