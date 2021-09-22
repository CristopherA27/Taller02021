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
	
	/*
	public static void desplegarMatriz(int [][] matriz) {
		for(int i=0;i<10;i++) {
			for(int j=0;j<30;j++) {
				System.out.print(matriz[i][j]+"|");
			}
			System.out.println();
		}
	}*/
	
	public static String verificarRut(String rutNormal) {
		String nuevoRut = rutNormal.replace(".","");
		nuevoRut = nuevoRut.replace("-","");
		nuevoRut = nuevoRut.replace("K","k");
		return nuevoRut;
	}
	//sirve para crear la matriz
	public static void crearMatrizSala(int [] lista,char [] listaLetras,int [][]matriz) {
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
		}
    }
	public static void imprimirSala(int [] lista,char [] listaLetras,int [][]matriz) {
		for(int k=0;k<30;k++) {
			lista[k]= k+1;
			System.out.print("\t"+lista[k]);
		}
		System.out.println();
		for(int i = 0 ; i<10 ; i++) {
			System.out.print(listaLetras[i]+" ");
			for(int c= 0;c<30;c++) {				
				System.out.print("\t"+matriz[i][c]);
			
			}
			System.out.println("");	
		}
	}
	
	//Esta Lista
	public static int horariosDisponiblesPelicula(String [] lpeliculas,String [] ltipos,int cantPeliculas,boolean [][] matrizMaņana,boolean [][] matrizTarde) {
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
		return posPelicula;

	}
	public static void obtenerSalaDeFuncion(int [] listanumeros,char [] listaLetras,int[][]matriz1M,int [][] matriz2M,int [][] matriz3M,int [][] matriz1T,int [][] matriz2T,int [][] matriz3T) {
		System.out.print("Seleccione la funcion a la que desea comprar entradas:");
		String funcion = leer.nextLine();
		for(int i=1;i<=3;i++) {
			if(funcion.equalsIgnoreCase(i+"M")) {
				switch(i) {
				case(1):
					System.out.println("\t-------------------------------------------------------SALA "+i+"M-------------------------------------------------\t|");
					imprimirSala(listanumeros, listaLetras, matriz1M);break;
				case(2):
					System.out.println("\t-------------------------------------------------------SALA "+i+"M-------------------------------------------------\t|");
					imprimirSala(listanumeros, listaLetras, matriz2M);break;
				case(3):
					System.out.println("\t-------------------------------------------------------SALA "+i+"M-------------------------------------------------\t|");
					imprimirSala(listanumeros, listaLetras, matriz3M);break;
				default:break;
				}
			}else if(funcion.equalsIgnoreCase(i+"T")) {
				switch(i) {
				case(1):
					System.out.println("\t-------------------------------------------------------SALA "+i+"T-------------------------------------------------\t|");
					imprimirSala(listanumeros, listaLetras, matriz1T);break;
				case(2):
					System.out.println("\t-------------------------------------------------------SALA "+i+"T-------------------------------------------------\t|");
					imprimirSala(listanumeros, listaLetras, matriz2T);break;
				case(3):
					System.out.println("\t-------------------------------------------------------SALA "+i+"T-------------------------------------------------\t|");
					imprimirSala(listanumeros, listaLetras, matriz3T);break;
				default:break;
				}
			}
		}
	}
	
	public static void comprarEntrada(int [] listanumeros,char [] listaLetras) {
		//Se podria obtener la i dela funcion anteior y la T o M para reconocer la matriz donde hara cambios.
		System.out.println("Cuantos asientos desea comprar:");
		int cantAsientos = leer.nextInt();
		for(int i=0;i<cantAsientos;i++) {
			System.out.print("Ingrese la letra de la fila que desea comprar:");
			String letraFila = leer.nextLine();
			System.out.print("Ingrese el numero de la columna que desea comprar: ");
			int numeroColumna = leer.nextInt();
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
		int [] lsalas = new int[11];
		//
		boolean [][] matrizMaņana = new boolean[100][3];//[FILAS][COLUMNAS] 100 por que es el contador de peliculas
		
		boolean [][] matrizTarde = new boolean[100][3];//[FILAS][COLUMNAS]
		
		int [] listanumeros = new int[30];
		char[] listaLetras = {'A','B','C','D','E','F','G','H','I','J'};
		
		int [][] matriz1M =new int[10][30];
		crearMatrizSala(listanumeros, listaLetras, matriz1M);
		//imprimirSala(listanumeros, listaLetras, matriz1M);
		//
		int [][] matriz2M =new int[10][30];
		crearMatrizSala(listanumeros, listaLetras, matriz2M);
		int [][] matriz3M =new int[10][30];
		crearMatrizSala(listanumeros, listaLetras, matriz3M);
	
		int [][] matriz1T =new int[10][30];
		crearMatrizSala(listanumeros, listaLetras, matriz1T);
		
		int [][] matriz2T =new int[10][30];
		crearMatrizSala(listanumeros, listaLetras, matriz2T);
		
		int [][] matriz3T =new int[10][30];
		crearMatrizSala(listanumeros, listaLetras, matriz3T);
		//
		int cantClientes = leerClientes(lnombres, lapellidos, lruts, lcontraseņas, lsaldos);
		String [] listaPaseMovilidad = new String[cantClientes];
		leerStatus(lruts, listaPaseMovilidad, cantClientes);
		int cantPeliculas = leerPeliculas(lpeliculas, ltipos, listaRecaudacionPelicula, matrizMaņana, matrizTarde);
		//horariosDisponiblesPelicula(lpeliculas, ltipos, cantPeliculas, matrizMaņana, matrizTarde);
		
		//obtenerSalaDeFuncion(listanumeros, listaLetras, matriz1M,matriz2M,matriz3M,matriz1T,matriz2T,matriz3T);
	
		//desplegar(lruts, listaPaseMovilidad, cantClientes);COMPROBE EL PARALELISMO DE EL PASE DE MOVILIDAD CON LOS RUTS
		
		/*for(int i=0;i<7;i++) {
			for(int j=0;j<3;j++) {
				System.out.print(matrizMaņana[i][j]+" ");
			}
			System.out.println();
		}*/
		for(int i=0;i<30;i++) {
			System.out.println(listanumeros[i]);
		}
		

	}
	public static Scanner leer = new Scanner(System.in);

}
