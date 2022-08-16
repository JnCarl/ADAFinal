

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;


import javafx.util.Pair;

public class BFS {
	static class Graph<E> {

	    public List<Node<E>> vertex = new ArrayList<>();

	    public void link(E begin, E end, Integer weight) {

	        boolean createVertexBegin = true;
	        boolean createVertexEnd = true;

	        //search if vertex begin exists
	        for (Node<E> v : vertex) {
	            if (v.data == begin) {
	                v.addNeighbor(weight, end);
	                createVertexBegin = false;
	            }

	            if (v.data == end) {
	                v.addNeighbor(weight, begin);
	                createVertexEnd = false;
	            }
	        }

	        if (createVertexBegin) {
	            Node<E> a = new Node<>(begin);
	            a.addNeighbor(weight, end);
	            vertex.add(a);
	        }

	        if (createVertexEnd) {
	            Node<E> b = new Node<>(end);
	            b.addNeighbor(weight, begin);
	            vertex.add(b);
	        }

	    }

	    @Override
	    public String toString() {
	        return vertex.toString();
	    }
	}
	
	static class GraphAlgorithms<E> {
		private List<E> lista = new LinkedList<E>();
		
	   
	    public List<E> BFS(Graph<E> G) {
	    	Queue<E> cola = new LinkedList<E>();
	    	this.lista.clear();
	    	this.lista.add(G.vertex.get(0).data);
	    	innerBFSGraph(G, G.vertex.get(0), cola);
	        return this.lista;
	    }
	    
	    private void innerBFSGraph(Graph<E> G, Node<E> V, Queue<E> cola) {
	    	
	    	for(int i = 0; i < V.neighbors.size();i++) {
	    		//Si el nodo no ha sido visitado todavia
	    		if(!this.lista.contains(V.neighbors.get(i).getValue())) {
	    			//Se marca como visitado
	    			this.lista.add(V.neighbors.get(i).getValue());
	        		
	        		cola.add(V.neighbors.get(i).getValue());
	    		}
	    	}
	    	//Recursividad para los elementos que están en la cola
	    	for(int i = 0 ; i< V.neighbors.size();i++) {
	    		if(cola.remove(V.neighbors.get(i).getValue())) {
	        		Node<E> node = buscarEnElGrafo(G, V.neighbors.get(i).getValue());
	        		innerBFSGraph(G, node, cola);
	    		}
	    		
	    	}
	    }
	    
	    private Node<E> buscarEnElGrafo(Graph<E> G, E data){
	    	for(int i = 0 ; i < G.vertex.size() ; i++) {
	    		if(G.vertex.get(i).data.equals(data)) {
	    			return G.vertex.get(i);
	    		}
	    	}
	    	return null;
	    }

	}
	
	static class Node<E> {

	    public E data;
	    public List<Pair<Integer, E>> neighbors = new ArrayList<>();
	    public boolean visited = false;

	    public Node(E data) {
	        this.data = data;
	    }
	    //Agregar vecino
	    //Key es el peso, value el vertice
	    public void addNeighbor(Integer weight, E data) {
	        Pair<Integer, E> semiEdge = new Pair<>(weight, data);

	        if (!neighbors.contains(semiEdge)) {
	            neighbors.add(semiEdge);
	        }
	    }

	    public void addNeighbor(E data) {
	        Pair<Integer, E> semiEdge = new Pair<>(1, data);

	        if (!neighbors.contains(semiEdge)) {
	            neighbors.add(semiEdge);
	        }
	    }

	    @Override
	    public String toString() {
	        return "[" + data + "] -> " + neighbors + "\n";
	    }
	}
	
	public static void main(String[] args) {

		GraphAlgorithms<String> rutasBFS1 = new GraphAlgorithms<>();
		
		long inicioCaso1 = System.nanoTime();
		Graph<String> rutas1 = new Graph<>();
		 rutas1.link("Casa", "Paradero1", 278);
		 rutas1.link("Casa", "Paradero2", 241);
		 rutas1.link("Paradero1", "Desvio", 1440);
		 rutas1.link("Desvio", "Destino1", 1700);
		 rutas1.link("Desvio", "Destino2", 748);
		 rutas1.link("Paradero2", "Paradero3", 1920);
		 rutas1.link("Paradero3", "Destino2", 748);
		 rutas1.link("Destino2", "UNSA", 101);
		 rutas1.link("Destino1", "UNSA", 361);
	
		 System.out.println("Caso 1: "+rutasBFS1.BFS(rutas1));
	     long finCaso1 = System.nanoTime();
	     System.out.println(finCaso1-inicioCaso1);
	     
	     long inicioCaso2 = System.nanoTime();
	     GraphAlgorithms<String> rutasBFS2 = new GraphAlgorithms<>();

	     Graph<String> rutas2 = new Graph<>();
	     rutas2.link("Casa", "Paradero1", 210);
	     rutas2.link("Casa", "Paradero2", 700);
	     rutas2.link("Paradero1","Paradero2",700);
	     rutas2.link("Paradero2", "Desvio1", 750);
	     rutas2.link("Desvio1", "Paradero3", 2400);
	     rutas2.link("Desvio1", "Paradero4", 8500);
	     rutas2.link("Paradero3", "Desvio2", 2100);
	     rutas2.link("Paradero4", "UNSA", 8300);
	     rutas2.link("Desvio2", "Desvio3", 4300);
	     rutas2.link("Desvio2", "UNSA", 5900);
	     rutas2.link("Desvio3", "Paradero5", 1100);
	     rutas2.link("Desvio3", "UNSA", 2600);
	     rutas2.link("Paradero5", "UNSA", 1900);
	     
	     System.out.println("Caso 2: "+rutasBFS2.BFS(rutas2));
	     long finCaso2 = System.nanoTime();
	     System.out.println(finCaso2-inicioCaso2);
	     
	     
	     long inicioCaso3 = System.nanoTime();
	     GraphAlgorithms<String> rutasBFS3 = new GraphAlgorithms<>();
	     Graph<String> rutas3 = new Graph<>();
		 rutas3.link("Casa", "Paradero1", 1000);
	     rutas3.link("Paradero1", "Paradero2", 2700);
	     rutas3.link("Paradero1", "Desvio", 300);
	     rutas3.link("Paradero2", "Paradero3", 600);
	     rutas3.link("Desvio", "Paradero3", 2000);
	     rutas3.link("Paradero3", "UNSA", 200);
	     
	     System.out.println("Caso 3: "+rutasBFS3.BFS(rutas3));
	     long finCaso3 = System.nanoTime();
	     System.out.println(finCaso3-inicioCaso3);
	    
	}

}
