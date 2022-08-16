
import java.util.PriorityQueue;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

class Node implements Comparable<Node> {

    public final String node;
    public Arista[] nodeadyaciente;
    public double distanciaMin = Double.POSITIVE_INFINITY;
    public Node before;

    public Node(String node) {
        this.node = node;
    }
    @Override
    public String toString() {
        return this.node;
    }

    @Override
    public int compareTo(Node node) {
        return Double.compare(distanciaMin, node.distanciaMin);
    }
}

class  Arista{
    public final Node objetivo; //nodo al cual se quiere llegar
    public final double peso;   //peso 

    public Arista(Node objetivo, double peso) {
        this.objetivo = objetivo;
        this.peso = peso;
    }
}

public class Dijkstra {

    public static void rutas(Node root) {
        root.distanciaMin = 0;
        PriorityQueue<Node> vertices = new PriorityQueue<>();
        vertices.add(root);
        while (!vertices.isEmpty()) {
            Node u = vertices.poll(); //accede y elimina el elemento las pequeño
            
            // visita cada arista con el menor peso que sale de u 
            for (Arista e : u.nodeadyaciente) {
                Node v = e.objetivo;
                double peso = e.peso;
                double distanciaU = u.distanciaMin + peso;
                if (distanciaU < v.distanciaMin) {
                     vertices.remove(v);
                    v.distanciaMin = distanciaU;
                    v.before = u;
                    vertices.add(v);
                }
            }
        }
    }
    //metodo para referenciar desde el nodo origen hasta el nodo destino
    public static List<Node> getRutaCorta(Node target) {
        List<Node> ruta = new ArrayList<>();
        for (Node arista = target; arista != null; arista = arista.before) {
            ruta.add(arista);
        }

        Collections.reverse(ruta);
        return ruta;
    }

    public static void main(String[] args) {
        
        //---PRUEBA DE CASO 2 Juan Carlos
        Node v1 = new Node("CasaJC_1");
        Node v2 = new Node("Paradero1_2");
        Node v3 = new Node("Paradero2_3");
        Node v4 = new Node("Desvio1_4");
        Node v5 = new Node("Paradero3_5");
        Node v6 = new Node("Paradero4_6");
        Node v7 = new Node("Desvio2_7");
        Node v8 = new Node("Desvio3_8");
        Node v9 = new Node("Paradero5_9");
        Node v10 = new Node("UNSA_10");
        v1.nodeadyaciente = new Arista[]{new Arista(v2, 210),
                                    new Arista(v3, 700)};
        
        v2.nodeadyaciente = new Arista[]{new Arista(v1, 210),
                                    new Arista(v3, 700),};
        
        v3.nodeadyaciente = new Arista[]{new Arista(v1, 700),
                                    new Arista(v2, 700), 
                                    new Arista(v4, 750),};
        
        v4.nodeadyaciente = new Arista[]{new Arista(v3, 750),
                                    new Arista(v5, 2400),
                                    new Arista(v6, 8500)};
        
        v5.nodeadyaciente = new Arista[]{new Arista(v4, 2400),
                                    new Arista(v7, 2100)};
        
        v6.nodeadyaciente = new Arista[]{new Arista(v4, 8500),
                                    new Arista(v10, 8300)};
        
        v7.nodeadyaciente = new Arista[]{new Arista(v5, 2100),
                                    new Arista(v8, 4300),
                                    new Arista(v10, 5900)};
        
        v8.nodeadyaciente = new Arista[]{new Arista(v7, 4300),
                                    new Arista(v9, 1100),
                                    new Arista(v10, 2600)};
        
        v9.nodeadyaciente = new Arista[]{new Arista(v8, 1100),
                                    new Arista(v10, 1900)};
        
        v10.nodeadyaciente = new Arista[]{new Arista(v6, 8300),
                                    new Arista(v7, 5900),
                                    new Arista(v8, 2600),
                                    new Arista(v9, 1900)};
        
        
        Node[] vertices = {v1, v2, v3, v4, v5, v6, v7, v8, v9, v10};

        rutas(v1);
        for (Node v : vertices) {
            System.out.println("distancia a " + v + ": " + v.distanciaMin);
            List<Node> ruta = getRutaCorta(v);
            System.out.println("Ruta: " + ruta);
        }
        //FIN PRUEBA CASO 2 --
	
        //---PRUEBA DE CASO 1 Carlos Fabian
        Node v01 = new Node("Casa");
        Node v02 = new Node("Paradero1");
        Node v03 = new Node("Paradero2");
        Node v04 = new Node("Desvio");
        Node v05 = new Node("Paradero3");
        Node v06 = new Node("Destino1");
        Node v07 = new Node("Destino2");
        Node v08 = new Node("UNSA");
        
        v01.nodeadyaciente = new Arista[]{new Arista(v02, 278),
                                    new Arista(v03, 241)};
        
        v02.nodeadyaciente = new Arista[]{new Arista(v04, 1440)};
        
        v03.nodeadyaciente = new Arista[]{new Arista(v05, 1920)};
        
        v04.nodeadyaciente = new Arista[]{new Arista(v06, 1700),
                                    new Arista(v07, 1150)};
        
        v05.nodeadyaciente = new Arista[]{new Arista(v07, 748)};
        
        v06.nodeadyaciente = new Arista[]{new Arista(v08, 361)};
        
        v07.nodeadyaciente = new Arista[]{new Arista(v08, 101)};
        
        v08.nodeadyaciente = new Arista[]{new Arista(v06, 10000),
                                          new Arista(v07, 10000),};
        
        
        Node[] vertices1 = {v01, v02, v03, v04, v05, v06, v07, v08};

        rutas(v01);
        for (Node vi : vertices1) {
            System.out.println("distancia a " + vi + ": " + vi.distanciaMin);
            List<Node> ruta = getRutaCorta(vi);
            System.out.println("Ruta: " + ruta);
        }
        //FIN PRUEBA CASO 1 --
        
        
        //---PRUEBA DE CASO 3 Luis Torres
        Node v001 = new Node("Casa");
        Node v002 = new Node("Paradero1");
        Node v003 = new Node("Desvio");
        Node v004 = new Node("Paradero2");
        Node v005 = new Node("Paradero3");
        Node v006 = new Node("UNSA");
        
        v001.nodeadyaciente = new Arista[]{new Arista(v002, 1000)};
        
        v002.nodeadyaciente = new Arista[]{new Arista(v004, 2700),
                                           new Arista(v003, 300)};
        
        v003.nodeadyaciente = new Arista[]{new Arista(v005, 2000)};
        
        v004.nodeadyaciente = new Arista[]{new Arista(v005, 600)};
        
        v005.nodeadyaciente = new Arista[]{new Arista(v006, 200)};
        
        v006.nodeadyaciente = new Arista[]{new Arista(v005, 100000)};
        
        
        Node[] vertices01 = {v001, v002, v003, v004, v005, v006};

        rutas(v001);
        for (Node vCaso3 : vertices01) {
            System.out.println("distancia a " + vCaso3 + ": " + vCaso3.distanciaMin);
            List<Node> ruta = getRutaCorta(vCaso3);
            System.out.println("Ruta: " + ruta);
        }
        //FIN PRUEBA CASO 3 --

    }
}

