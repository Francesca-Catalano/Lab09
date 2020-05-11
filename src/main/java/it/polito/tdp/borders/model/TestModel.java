package it.polito.tdp.borders.model;

import java.util.Set;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class TestModel {

	public static void main(String[] args) {

		Model model = new Model();
		
		model.creaGrafo(2002);

		/*
		 * System.out.println("TestModel -- TODO");
		 * 
		 * System.out.println("Creo il grafo relativo al 2000"); model.creaGrafo(2000);
		 * 
		 * System.out.println("V : " + model.VertixSize());
		 */
		 
		//System.out.println("A : " + model.EdgeSize());
		//Set<Country> result = model.getGrafo().vertexSet().stream().sorted(Country::getName());
		//System.out.println(model.getGrafo().vertexSet().size());
		
//		List<Country> countries = model.getCountries();
//		System.out.format("Trovate %d nazioni\n", countries.size());

//		System.out.format("Numero componenti connesse: %d\n", model.getNumberOfConnectedComponents());
		
//		Map<Country, Integer> stats = model.getCountryCounts();
//		for (Country country : stats.keySet())
//			System.out.format("%s %d\n", country, stats.get(country));		
		
	}

}
