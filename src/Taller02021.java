import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Taller02021 {
	
	public static int leerClientes(String [] lnombres,String [] lapellidos,String [] lruts,String [] lcontraseņas,int [] lsaldos) throws FileNotFoundException {
		Scanner s = new Scanner(new File("clientes.txt"));
		int cont = 0;
		while(s.hasNextLine()) {
			String line = s.nextLine();
			String [] partes = line.split(",");
			String nombre = partes[0];
			String apellido = partes[1];
			String rut = partes[2];
			String contraseņa = partes[3];
			int saldo = Integer.parseInt(partes[4]);
			lnombres[cont] = nombre;
			lapellidos[cont] = apellido;
			lruts[cont] = rut;
			lcontraseņas[cont] = contraseņa;
			lsaldos[cont] = saldo;
			cont++;
		}
		s.close();
		return cont;
	}
	
	public static void leerStatus(String [] lruts,String [] listaPaseMovilidad,int cantClientes) throws FileNotFoundException {
		Scanner s = new Scanner(new File("status.txt"));
		while(s.hasNextLine()) {
			String line = s.nextLine();
			String [] partes = line.split(",");
			String rut = partes[0];
			int posBuscado = buscarEnLista(lruts, cantClientes, rut);
			if(posBuscado != -1) {
				String paseMovilidad = partes[1];
				listaPaseMovilidad[posBuscado] = paseMovilidad;
			}
		}
		s.close();		
	}
	
	public static int leerPeliculas(String [] lpeliculas,String [] ltipos,int [] listaRecaudacionPelicula,boolean [][] matrizMaņana,boolean [][] matrizTarde) throws FileNotFoundException {
		Scanner s = new Scanner(new File("peliculas.txt"));
		int cont = 0;
		while(s.hasNextLine()) {
			String line = s.nextLine();
			//System.out.println(line);
			String [] partes = line.split(",");
			String nombrePelicula = partes[0];
			String tipo = partes[1];
			int recaudacion = Integer.parseInt(partes[2]);
			for(int i=3;i<partes.length;i+=2) {
				int numeroSala = Integer.parseInt(partes[i]);
				numeroSala--;
				String horario = partes[i+1];
				//System.out.println("\t"+numeroSala+" "+horario);
				if(horario.equalsIgnoreCase("M")) {
					matrizMaņana[cont][numeroSala]=true;

				}else{
					if(horario.equalsIgnoreCase("T")) {
						matrizTarde[cont][numeroSala]=true;
						
					}
				}
			}
			lpeliculas[cont] = nombrePelicula;
			ltipos[cont] = tipo;
			listaRecaudacionPelicula[cont] = recaudacion;
			cont++;
		}
		s.close();
		return cont;
	}
	
	
	public static int buscarEnLista(String [] lista,int cantidad,String dato) {
		for(int i=0;i<cantidad;i++) {
			if(lista[i]== null) {
				break;
			}
			if(lista[i].equalsIgnoreCase(dato)) {
				return i;
			}
		}
		return -1;
	}
	
	public static void desplegar(String []lista,String [] lista2 ,int cantidad) {
		for(int i=0;i<cantidad;i++) {
			System.out.println(lista[i]+" y "+lista2[i]);
		}
	}
	
	public static void desplegarMatriz(int [][] matriz) {
		for(int i=0;i<10;i++) {
			for(int j=0;j<30;j++) {
				System.out.print(matriz[i][j]+"|");
			}
			System.out.println();
		}
	}
	
	public static void rellenarLista(int [] lsalas) {
		for(int i=0;i<=2;i++) {
			lsalas[i]=i+1;
		}
	}
	
	public static String verificarRut(String rutNormal) {
		String nuevoRut = rutNormal.replace(".","");
		nuevoRut = nuevoRut.replace("-","");
		nuevoRut = nuevoRut.replace("K","k");
		return nuevoRut;
	}
	
	public static void crearMatriz(int [][]matriz) {
		int cont = 0;
		for(int fila=0;fila<10;fila++) {
			
			for(int columna=0;columna<30;columna++) {
				if(columna<5 && fila<4 ) {
					matriz[fila][columna]=5;
				}
				if(columna>24 && fila<4) {
					matriz[fila][columna]=5;
				}
				if(columna%2==0 && fila<4 && columna>4 && columna<25) {
					matriz[fila][columna]=-1;
				}
				if(fila>=4 &&columna>0 && columna<30 && columna%2!=0) {
					matriz[fila][columna]=-1;
				}
			}
			//%=resto
			
		}
	}
	//Esta Lista
	public static void horariosDisponiblesPelicula(String [] lpeliculas,String [] ltipos,int cantPeliculas,boolean [][] matrizMaņana,boolean [][] matrizTarde) {
		System.out.print("Ingrese el nombre de la pelicula para ver sus horarios: ");
		String pelicula = leer.nextLine();
		int posPelicula = buscarEnLista(lpeliculas, cantPeliculas, pelicula);
		if(posPelicula == -1) {
			System.out.println("La pelicula ingresada no existe");
		}else {
			for(int j=0;j<3;j++) {
				if(matrizMaņana[posPelicula][j] == true) {
					System.out.print(j+1+"M"+"\n");
				}
				if(matrizTarde[posPelicula][j] == true) {
					System.out.print(j+1+"T"+"\n");
				}
			}
		}
	}
	
	
	
	public static void main(String[] args) throws FileNotFoundException {
		String [] lnombres = new String[200];
		String [] lapellidos  = new String[200];
		String [] lruts = new String[200];
		String [] lcontraseņas = new String[200];
		int [] lsaldos = new int[200];
		String [] lpeliculas = new String [200];
		String [] ltipos = new String[200];
		int [] listaRecaudacionPelicula = new int[200];
		//
		int [] lsalas = new int[3];
		rellenarLista(lsalas);
		//
		boolean [][] matrizMaņana = new boolean[100][3];//[FILAS][COLUMNAS] 100 por que es el contador de peliculas
		
		boolean [][] matrizTarde = new boolean[100][3];//[FILAS][COLUMNAS]
		
		int cantClientes = leerClientes(lnombres, lapellidos, lruts, lcontraseņas, lsaldos);
		String [] listaPaseMovilidad = new String[cantClientes];
		leerStatus(lruts, listaPaseMovilidad, cantClientes);
		int cantPeliculas = leerPeliculas(lpeliculas, ltipos, listaRecaudacionPelicula, matrizMaņana, matrizTarde);
		//horariosDisponiblesPelicula(lpeliculas, ltipos, cantPeliculas, matrizMaņana, matrizTarde);
		
		int [][] ma = new int[10][30]; 
		crearMatriz(ma);
		desplegarMatriz(ma);
		
		
		
		
		
		//desplegar(lruts, listaPaseMovilidad, cantClientes);COMPROBE EL PARALELISMO DE EL PASE DE MOVILIDAD CON LOS RUTS
		
		/*for(int i=0;i<7;i++) {
			for(int j=0;j<3;j++) {
				System.out.print(matrizMaņana[i][j]+" ");
			}
			System.out.println();
		}*/
		

	}
	public static Scanner leer = new Scanner(System.in);

}
