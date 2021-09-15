import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Taller02021 {
	
	public static int leerClientes(String [] lnombres,String [] lapellidos,String [] lruts,String [] lcontraseñas,int [] lsaldos) throws FileNotFoundException {
		Scanner s = new Scanner(new File("clientes.txt"));
		int cont = 0;
		while(s.hasNextLine()) {
			String line = s.nextLine();
			String [] partes = line.split(",");
			String nombre = partes[0];
			String apellido = partes[1];
			String rut = partes[2];
			String contraseña = partes[3];
			int saldo = Integer.parseInt(partes[4]);
			lnombres[cont] = nombre;
			lapellidos[cont] = apellido;
			lruts[cont] = rut;
			lcontraseñas[cont] = contraseña;
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
	
	public static void leerPeliculas(String [] lpeliculas,String [] ltipos,boolean [][] matrizMañana,boolean [][] matrizTarde) throws FileNotFoundException {
		Scanner s = new Scanner(new File("peliculas.txt"));
		int cont = 0;
		while(s.hasNextLine()) {
			String line = s.nextLine();
			String [] partes = line.split(",");
			String nombrePelicula = partes[0];
			String tipo = partes[1];
			int recaudacion = Integer.parseInt(partes[2]);
			lpeliculas[cont] = nombrePelicula;
			ltipos[cont] = tipo;
			cont++;
			int numeroSala = Integer.parseInt(partes[3]);
			while(numeroSala ==1 || numeroSala ==2 ||numeroSala ==3) {
				String horario = partes[4];
				if(horario.equalsIgnoreCase("M")) {
					for(int columna=1;columna<=3;columna++) {
						for(int fila=0;fila<cont;fila++) {
							matrizMañana[cont][numeroSala] = true;
						}
					}
				}
			}
			
		}
		
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
	
	public static void rellenarLista(int [] lsalas) {
		for(int i=0;i<=2;i++) {
			lsalas[i]=i+1;
		}
	}
	

	public static void main(String[] args) throws FileNotFoundException {
		String [] lnombres = new String[200];
		String [] lapellidos  = new String[200];
		String [] lruts = new String[200];
		String [] lcontraseñas = new String[200];
		int [] lsaldos = new int[200];
		String [] lpeliculas = new String [200];
		String [] ltipos = new String[200];
		
		int [] lsalas = new int[3];
		rellenarLista(lsalas);
		
		boolean [][] matrizMañana = new boolean[1][3];//[FILAS][COLUMNAS]
		boolean [][] matrizTarde = new boolean[1][3];//[FILAS][COLUMNAS]
		
		int cantClientes = leerClientes(lnombres, lapellidos, lruts, lcontraseñas, lsaldos);
		String [] listaPaseMovilidad = new String[cantClientes];
		leerStatus(lruts, listaPaseMovilidad, cantClientes);

		//desplegar(lruts, listaPaseMovilidad, cantClientes);COMPROBE EL PARALELISMO DE EL PASE DE MOVILIDAD CON LOS RUTS
		
		

	}

}
