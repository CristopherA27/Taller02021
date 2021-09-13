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
	
	public static void leerstatus(String [] listaPaseMovilidad) throws FileNotFoundException {
		Scanner s = new Scanner(new File("status.txt"));
		int cont = 0;
		while(s.hasNextLine()) {
			String line = s.nextLine();
			String [] partes = line.split(",");
			String paseMovilidad = partes[0];
			String rut = partes[1];
			listaPaseMovilidad[cont] = paseMovilidad;
			cont++;
		}
		s.close();		
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
	

	public static void main(String[] args) throws FileNotFoundException {
		String [] lnombres = new String[200];
		String [] lapellidos  = new String[200];
		String [] lruts = new String[200];
		String [] lcontraseņas = new String[200];
		int [] lsaldos = new int[200];
		int cantClientes = leerClientes(lnombres, lapellidos, lruts, lcontraseņas, lsaldos);
		String [] listaPaseMovilidad = new String[cantClientes];

		System.out.println("Ahora si estamos listos para empezar");
	}

}
