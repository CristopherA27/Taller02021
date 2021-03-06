import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

import ucn.ArchivoSalida;
import ucn.Registro;





public class Taller02021 {
	
	/**
	 * saves the customer data in lists and returns the size of the customer list
	 * @param lnombres
	 * @param lapellidos
	 * @param lruts
	 * @param lcontraseņas
	 * @param lsaldos
	 * @throws FileNotFoundException 
	 * return cont
	 */
	
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
	
	/**
	 * save the status of the clients in a parallel list
	 * @param lruts
	 * @param listaPaseMovilidad
	 * @param cantClientes
	 * @throws FileNotFoundException
	 */
	
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
	
	/**
	 * takes the step to the next function
	 * @param num
	 */
	
	public static void avanzar(int num) {
		if(num == 1) {
			System.out.println("\tPrecione Enter para continuar");
			leer.nextLine();
		}
		System.out.println("-------------------------------------------");
	}
	
	/**
	 * the data of the movies is saved in lists and the size of the movie list is returned
	 * @param lpeliculas
	 * @param ltipos
	 * @param listaRecaudacionPelicula
	 * @param matrizMaņana
	 * @param matrizTarde
	 * @throws FileNotFoundException
	 * return cont
	 */
	
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
	
	/**
	 * searches a list for the position of the requested element
	 * @param lista
	 * @param cantidad
	 * @param dato
	 * return int
	 */
	
	
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
	
	/**
	 * look for the position of the number in the list, to return it
	 * @param lsita
	 * @param cantidad
	 * @param dato
	 * return i
	 */
	
	public static int buscarEnListaNumeros(int []lista,int cantidad,int dato) {
		for(int i=0;i<cantidad;i++) {
			if(lista[i]==0) {
				break;
			}
			if(lista[i]==dato) {
				return i;
			}
		}
		return -1;
	}
	
	/**
	 * edit the rut entered on the screen and return the new rut
	 * @param rutNormal
	 * return nuevoRut
	 */
	
	public static String verificarRut(String rutNormal) {
		String nuevoRut = rutNormal.replace(".","");
		nuevoRut = nuevoRut.replace("-","");
		nuevoRut = nuevoRut.replace("K","k");
		return nuevoRut;
	}
	
	/**
	 * models the matrix used for seats in movie theaters
	 * @param lista
	 * @param listaLetras
	 * @param matriz
	 */
	
	public static void crearMatrizSala(int [] lista,String [] listaLetras,int [][]matriz) {
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
	
	/**
	 * prints the room matrix that is entered
	 * @param lista
	 * @param listaLetras
	 * @param matriz
	 */
	
	public static void imprimirSala(int [] lista,String [] listaLetras,int [][]matriz) {
		reglasSala();
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
	
	/**
	 * checks if the client has the mobility pass enabled to return a boolean type data
	 * @param lruts
	 * @param listaPaseMovilidad
	 * @param cantClientes
	 *@param posCliente
	 *return boolean
	 */
	
	public static boolean tienePaseMovilidad(String [] lruts,String [] listaPaseMovilidad,int cantClientes,int posCliente) {
		for(int i=0;i<cantClientes;i++) {
			if(listaPaseMovilidad[posCliente].equals("HABILITADO"));
			return true;
		}
		return false;
	}
	
	/**
	 *The available times of the movie entered on the screen will be obtained.
	 *@param listaPeliculas
	 *@param lpeliculas
	 *@param ltipos
	 *@param cantPeliculas
	 *@param matrizMaņana
	 *@param matrizTarde 
	 */
	
	public static int horariosDisponiblesPelicula(String [] listaPeliculas,String [] lpeliculas,String [] ltipos,int cantPeliculas,boolean [][] matrizMaņana,boolean [][] matrizTarde) {
		System.out.print("Ingrese el nombre de la pelicula para ver sus horarios: ");
		String pelicula = leer.nextLine();
		listaPeliculas[contPersona]=pelicula;
		int posPelicula = buscarEnLista(lpeliculas, cantPeliculas, pelicula);
		if(posPelicula == -1) {
			System.out.println("La pelicula ingresada no existe");
		}else {
			System.out.println("Los horarios disponibles son:");
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

	/**
	 * returns the function that was entered by keyboard
	 * @param lhorarios
	 * @param lsalas
	 * @param listanumeros
	 * @param listaLetras
	 * @param matriz1M
	 * @param matriz2M
	 * @param matriz3M
	 * @param matriz1T
	 * @param matriz2T
	 * @param matriz3T
	 * return funcion
	 */
	
	public static String obtenerSalaDeFuncion(String [] lhorarios,int [] lsalas,int [] listanumeros,String [] listaLetras,int[][]matriz1M,int [][] matriz2M,int [][] matriz3M,int [][] matriz1T,int [][] matriz2T,int [][] matriz3T) {
		int cont = 0;
		System.out.print("Seleccione la funcion a la que desea comprar entradas:");
		String funcion = leer.nextLine();
		String dato = "";
		for(int i=1;i<=3;i++) {
			if(funcion.equalsIgnoreCase(i+"M")) {
				dato+="M";
				switch(i) {
				case(1):
					tituloMatriz(i,dato);
					imprimirSala(listanumeros, listaLetras, matriz1M);
					lhorarios[contPersona]= dato;
					lsalas[contPersona] = i;
					break;
				case(2):
					tituloMatriz(i,dato);
					imprimirSala(listanumeros, listaLetras, matriz2M);
					lhorarios[contPersona]= dato;
					lsalas[contPersona] = i;

					break;
				case(3):
					tituloMatriz(i,dato);
					imprimirSala(listanumeros, listaLetras, matriz3M);
					lhorarios[contPersona]= dato;
					lsalas[contPersona] = i;
					break;
				default:break;
				}
			}else if(funcion.equalsIgnoreCase(i+"T")) {
				dato+="T";
				switch(i) {
				case(1):
					tituloMatriz(i,dato);
					imprimirSala(listanumeros, listaLetras, matriz1T);
					lhorarios[contPersona]= dato;
					lsalas[contPersona] = i;
					break;
				case(2):
					tituloMatriz(i,dato);
					imprimirSala(listanumeros, listaLetras, matriz2T);
					lhorarios[contPersona]= dato;
					lsalas[contPersona] = i;
					break;
				case(3):
					tituloMatriz(i,dato);
					imprimirSala(listanumeros, listaLetras, matriz3T);
					lhorarios[contPersona]= dato;
					lsalas[contPersona] = i;
					break;
				default:break;
				}
			}
		}
		return funcion;
	}
	/*	String [] rutsClientes = new String [200];
		String [] listaPeliculas = new String [200];
		String [] lhorarios = new String[200];
		int [] lsalas = new int[200];
		int [] listaCantEntradas = new int[200];*/
	
	
	/**
	 * displays the name of the room that was selected
	 * @param i
	 * @param dato
	 */
	
	public static void tituloMatriz(int i,String dato) {
		System.out.println("\t-------------------------------------------------------SALA "+i+dato+"-------------------------------------------------\t|");
	}
	
	/**
	 *displays the room rules on the screen 
	 */
	
	public static void reglasSala() {
		System.out.println();
		System.out.println("Por temas de protocolo covid los asientos que contengan un "+" -1 "+"no pueden ser ocupados ");
		System.out.println("Los asientos que tienen un 5 no estan habilitados");
		System.out.println("Los asientos que contengan un 0 son los asientos que puede comprar!");
		System.out.println();
		avanzar(0);
	}
	
	/**
	 * Customer information will be obtained with their respective purchased tickets.
	 * @param cantidadListaDeAsientos
	 * @param lnombres
	 * @param lapellidos
	 * @param rut
	 * @param posCliente
	 * @param rutsClientes
	 * @param listaPeliculas
	 * @param lhorarios
	 * @param lsalas
	 * @param listaCantEntradas
	 * @param matrizAsientos
	 * @param lsaldos
	 */

	public static void infoUsuario(int cantidadListaDeAsientos,String [] lnombres,String [] lapellidos,String rut,int posCliente,String [] rutsClientes,String [] listaPeliculas,String [] lhorarios,int [] lsalas,int [] listaCantEntradas,String [][] matrizAsientos,int[] lsaldos) {
		for(int i=0;i<cantidadListaDeAsientos;i++) {
			if(rut.equals(rutsClientes[i])) {
				System.out.print(rutsClientes[i]+" "+lnombres[posCliente]+" "+lapellidos[posCliente]+" Saldo restante: "+lsaldos[posCliente]+" PELICULA: "+listaPeliculas[i]+" N°SALA: "+lsalas[i]+" HORARIO: "+lhorarios[i]+" Cantidad Entradas: "+listaCantEntradas[i]+" ->");
				int columnas = listaCantEntradas[i];
				for(int j=0;j<columnas;j++) {
					System.out.print(matrizAsientos[i][j]+" ");
				}
				System.out.println();
			}else {
				break;
			}
		}
		System.out.println();
	}
	/**
	 *This function will obtain the seats to buy on the screen and will fill the matrix of the corresponding room with a 1 when buying the seat
	 *@param matrizAsientos
	 *@param contRecaudacionMaņana
	 *@param contRecaudacionTarde
	 *@param listaCantEntradas
	 *@param lhorarios
	 *@param lsalas
	 *@param posPersona
	 *@param lsaldos
	 *@param matriz1M
	 *@param matriz2M
	 *@param matriz3M
	 *@param matriz1T 
	 *@param matriz2T
	 *@param matriz3T
	 *@param listaNumeros
	 *@param listaletras
	 *@param cantPeliculas
	 *@param listaPeliculas
	 *@param lpeliculas
	 *@param ltipos
	 *@param matrizMaņana
	 *@param matrizTarde
	 */
	
	public static void comprarEntrada( int[] contRecaudacionMaņana ,int[] contRecaudacionTarde ,String [][] matrizAsientos,int [] listaCantEntradas,String [] lhorarios,int [] lsalas,int posPersona,int [] lsaldos,int[][]matriz1M,int [][] matriz2M,int [][] matriz3M,int [][] matriz1T,int [][] matriz2T,int [][] matriz3T,int [] listanumeros,String [] listaLetras,int cantPeliculas,String [] listaPeliculas,String [] lpeliculas,String [] ltipos,boolean [][] matrizMaņana,boolean [][] matrizTarde) {
		int posPelicula = horariosDisponiblesPelicula(listaPeliculas, lpeliculas, ltipos, cantPeliculas, matrizMaņana, matrizTarde);
		String funcion = obtenerSalaDeFuncion(lhorarios, lsalas, listanumeros, listaLetras, matriz1M, matriz2M, matriz3M, matriz1T, matriz2T, matriz3T);
		System.out.print("Cuantos asientos desea comprar:");
		int cantAsientos = leer.nextInt();
		listaCantEntradas[contPersona] = cantAsientos;
		leer.nextLine();
		for(int i=0;i<cantAsientos;i++) {
			System.out.println("Asiento "+(i+1)+"\t");
			System.out.print("Ingrese la letra de la fila que desea comprar:");
			String letraFila = leer.nextLine();
			int posLetra = buscarEnLista(listaLetras, 10, letraFila);
			System.out.print("Ingrese el numero de la columna que desea comprar: ");
			int numeroColumna = leer.nextInt();
			leer.nextLine();
			int posNumero = buscarEnListaNumeros(listanumeros, 30, numeroColumna);
			String dato  = "";
			matrizAsientos[contPersona][i]=letraFila+""+numeroColumna;
			for(int j=1;j<=3;j++) {
				if(funcion.equalsIgnoreCase(j+"M")) {
					dato +="M";
					switch(j) {
					case(1):matriz1M[posLetra][posNumero] = 1;
							imprimirSala(listanumeros, listaLetras, matriz1M);
							break;
					case(2):matriz2M[posLetra][posNumero] = 1;
							tituloMatriz(j,dato);
							imprimirSala(listanumeros, listaLetras, matriz2M);
							break;
					case(3):matriz3M[posLetra][posNumero] = 1;
							imprimirSala(listanumeros, listaLetras, matriz3M);
							break;
					default:break;
					}
				}else if(funcion.equals(j+"T")) {
					switch(j) {
					case(1):matriz1T[posLetra][posNumero] = 1;
							imprimirSala(listanumeros, listaLetras, matriz1T);
							break;
					case(2):matriz2T[posLetra][posNumero] = 1;
							imprimirSala(listanumeros, listaLetras, matriz2T);
							break;
					case(3):matriz3T[posLetra][posNumero] = 1;
							imprimirSala(listanumeros, listaLetras, matriz3T);
							break;
					default:break;
					}
				}
			}
		}
	
		if(ltipos[posPelicula].equalsIgnoreCase("Estreno")) {
			int total = 5500*cantAsientos;
			System.out.println("El total a pagar es: "+total);
			if(lsaldos[posPersona] >= total) {
				lsaldos[posPersona] = lsaldos[posPersona] - total;
				contRecaudacionMaņana[0]+=total;
			}else {
				System.out.println("Su saldo es insuficiente.....Desea recargar saldo? (SI) o (NO)");
				String resp = leer.nextLine();
				if(resp.equalsIgnoreCase("SI")) {
					cargarSaldo(cantAsientos, total, lsaldos);
					lsaldos[posPersona] = lsaldos[posPersona] - total;
				}else {
					System.out.println("No puedo comprar las entradas con el saldo actual");
				}
			}
		}else {
			int total = 4000*cantAsientos;
			System.out.println("El total a pagar es: "+total);
			if(lsaldos[posPersona] >= total) {
				lsaldos[posPersona] = lsaldos[posPersona] - total;
				contRecaudacionTarde[0]+=total;
			}else {
				System.out.println("Su saldo es insuficiente.....Desea recargar saldo? (SI) o (NO)");
				String resp = leer.nextLine();
				if(resp.equalsIgnoreCase("SI")) {
					cargarSaldo(cantAsientos, total, lsaldos);
					lsaldos[posPersona] = lsaldos[posPersona] - total;
				}else {
					System.out.println("No puedo comprar las entradas con el saldo actual");

				}
			}
		}
		
		System.out.print("Esta seguro de realizar la compra? (Si) o (No): ");
		String resp = leer.nextLine();
		if(resp.equalsIgnoreCase("SI")) {
			System.out.println("Asientos comprados con exito.");
			contPersona++;
		}	
		
	}

	/**
	 *The matrix entries of the movie theater that the user enters on screen will be eliminated.
	 *@param matriz
	 *@param cantColumnasAsientos
	 *@param posFila
	 *@param dato
	 */

	public static void eliminarEnMatriz(String [][] matriz,int cantColumnasAsientos,int posFila,String dato) {
		int j;
		boolean encontrado = false;
		for(j=0;j<cantColumnasAsientos;j++) {
			if(matriz[posFila][j]==null) {
				break;
			}
			else if(matriz[posFila][j].equals(dato)) {
					matriz[posFila][j] = null;
					encontrado = true;
					break;

			}
		}
		if(encontrado) {
			for(int k=j;k<cantColumnasAsientos;k++) {
				matriz[posFila][k]= matriz[posFila][k+1];
			}
			cantColumnasAsientos--;
		}
		
	}
	
	/**
	 * allows to make the corresponding arrangements at the time of returning the ticket
	 * @param lpeliculas
	 * @param cantPeliculas
	 * @param cantColumnasAsientos
	 * @param contRecaudacionMaņana
	 * @param contRecaudacionTarde
	 * @param listaLetras
	 * @param listanumeros
	 * @param ltipos
	 * @param cantClientes
	 * @param lnombres
	 * @param lapellidos
	 * @param rut
	 * @param posCliente
	 * @param rutsClientes
	 * @param listaPeliculas
	 * @param lhorarios
	 * @param lsalas
	 * @param listaCantEntradas
	 * @param matrizAsientos
	 * @param lsaldos
	 * @param matriz1M
	 * @param matriz2M
	 * @param matriz3M
	 * @param matriz1T
	 * @param matriz2T
	 * @param matriz3T
	 */
	
	public static void devolucionEntrada(String [] lpeliculas,int cantPeliculas,int cantColumnasAsientos,int[] contRecaudacionMaņana, int[] contRecaudacionTarde,String [] listaLetras,int [] listanumeros,String [] ltipos,int cantClientes, String[] lnombres, String[] lapellidos, String rut, int posCliente, String[] rutsClientes,String[] listaPeliculas, String[] lhorarios, int[] lsalas, int[] listaCantEntradas,String[][] matrizAsientos, int[] lsaldos,int[][]matriz1M,int [][] matriz2M,int [][] matriz3M,int [][] matriz1T,int [][] matriz2T,int [][] matriz3T) {
		System.out.println("Las entradas que usted tiene son: ");
		infoUsuario(cantClientes, lnombres, lapellidos, rut, posCliente, rutsClientes, listaPeliculas, lhorarios, lsalas, listaCantEntradas, matrizAsientos, lsaldos);
		System.out.print("Ingrese el nombre de la pelicula a la cual desea devolver las entradas:");
		String nombrePelicula = leer.nextLine();
		int posPelicula = buscarEnLista(listaPeliculas, listaPeliculas.length, nombrePelicula);
		if(posPelicula == -1) {
			System.out.println("pelicula no ecnontrada.");
			devolucionEntrada(lpeliculas,cantColumnasAsientos, cantPeliculas ,contRecaudacionMaņana, contRecaudacionTarde, listaLetras, listanumeros,ltipos,cantClientes, lnombres, lapellidos, rut, posCliente, rutsClientes, listaPeliculas, lhorarios, lsalas, listaCantEntradas, matrizAsientos, lsaldos, matriz3T, matriz3T, matriz3T, matriz3T, matriz3T, matriz3T);
		}else {
			System.out.print("Ingrese la cantidad de entradas que desea devolver: ");
			int cantEntradas = leer.nextInt();
			leer.nextLine();
			if(listaCantEntradas[posPelicula]>=cantEntradas && cantEntradas >0) {
				for(int i=0;i<cantEntradas;i++) {
					System.out.print("Ingrese la letra de la fila que desea devolver:");
					String letraFila = leer.nextLine();
					int posLetra = buscarEnLista(listaLetras, 10, letraFila);
					System.out.print("Ingrese el numero de la columna que desea devolver: ");
					int numeroColumna = leer.nextInt();
					leer.nextLine();
					int posNumero = buscarEnListaNumeros(listanumeros, 30, numeroColumna);
					int numSala = lsalas[posPelicula];
					String horarioSala = lhorarios[posPelicula];
					String dato =numSala+horarioSala;
					String letraHorario ="";
					for(int j=1;j<=3;j++) {
						if(dato.equalsIgnoreCase(j+"M")) {
							letraHorario += "M";
							switch(j) {
							case(1):matriz1M[posLetra][posNumero] = 0;
									eliminarEnMatriz(matrizAsientos, cantColumnasAsientos, posPelicula, dato);
									tituloMatriz(j,letraHorario);
									imprimirSala(listanumeros, listaLetras, matriz1M);
									break;
							case(2):matriz2M[posLetra][posNumero] = 0;
									eliminarEnMatriz(matrizAsientos, cantColumnasAsientos, posPelicula, dato);
									tituloMatriz(j,letraHorario);
									imprimirSala(listanumeros, listaLetras, matriz2M);
									break;
							case(3):matriz3M[posLetra][posNumero] = 0;
									eliminarEnMatriz(matrizAsientos, cantColumnasAsientos, posPelicula, dato);
									tituloMatriz(j,letraHorario);
									imprimirSala(listanumeros, listaLetras, matriz3M);
									break;
							default:break;
							}
						}else if(dato.equals(j+"T")) {
							letraHorario +="T";
							switch(j) {
							case(1):matriz1T[posLetra][posNumero] = 0;
									eliminarEnMatriz(matrizAsientos, cantColumnasAsientos, posPelicula, dato);
									imprimirSala(listanumeros, listaLetras, matriz1T);
									break;
							case(2):matriz2T[posLetra][posNumero] = 0;
									eliminarEnMatriz(matrizAsientos, cantColumnasAsientos, posPelicula, dato);
									imprimirSala(listanumeros, listaLetras, matriz2T);
									break;
							case(3):matriz3T[posLetra][posNumero] = 0;
									eliminarEnMatriz(matrizAsientos, cantColumnasAsientos, posPelicula, dato);
									imprimirSala(listanumeros, listaLetras, matriz3T);
									break;
							default:break;
							}
						}
						int posTipoPelicula = buscarEnLista(lpeliculas, cantPeliculas, nombrePelicula);
						int total;
						if(ltipos[posTipoPelicula].equalsIgnoreCase("Estreno") && horarioSala == "M") {
							 total = 5500*cantEntradas;
							contRecaudacionMaņana[0] -= (total*0.8); 
							lsaldos[posCliente]+=  (total*0.8);
						}else if (ltipos[posTipoPelicula].equalsIgnoreCase("Estreno") && horarioSala == "T"){
							 total = 5500*cantEntradas;
							contRecaudacionTarde[0] -= (total*0.8); 
							lsaldos[posCliente]+=  (total*0.8);
						}
						
						if(ltipos[posTipoPelicula].equalsIgnoreCase("Liberada") && horarioSala == "M") {
							 total = 4000*cantEntradas;
							contRecaudacionMaņana[0] -= (total*0.8); 
							lsaldos[posCliente]+=  (total*0.8);
						}else if (ltipos[posTipoPelicula].equalsIgnoreCase("Liberada") && horarioSala == "T"){
							 total = 4000*cantEntradas;
							contRecaudacionTarde[0] -= (total*0.8); 
							lsaldos[posCliente]+=  (total*0.8);
						}
					}
						
				}
			}
		}
	}
	
	/**
	 * charge the balance corresponding to the customer entered
	 * @param posCliente
	 * @param cantClientes
	 * @param lsaldos
	 */

	
	public static void cargarSaldo(int posCliente, int cantClientes,int [] lsaldos) {
		System.out.println("Cuanto dinero desea gregar a su saldo?: ");
		int agregado = leer.nextInt();
		lsaldos[posCliente]+=agregado;
		
	}	
	
	/**
	 * this function fills a list with the letters from A to J
	 * @param lista
	 */
	
	public static void rellenarListaLetras(String [] lista) {
		for(int i=0;i<10;i++) {
			switch(i+1) {
			case(1):lista[i]="A";break;
			case(2):lista[i]="B";break;
			case(3):lista[i]="C";break;
			case(4):lista[i]="D";break;
			case(5):lista[i]="E";break;
			case(6):lista[i]="F";break;
			case(7):lista[i]="G";break;
			case(8):lista[i]="H";break;
			case(9):lista[i]="I";break;
			case(10):lista[i]="J";break;
			default:break;
			}	
		}
	}
	
	/**
	 * The movies on the billboard and their respective schedules will be obtained.
	 * @param lpeliculas
	 * @param matrizMaņana
	 * @param matrizTarde
	 * @param cantPeliculas
	 */
	
	public static void obtenerCartelera(String [] lpeliculas,boolean [][] matrizMaņana,boolean [][] matrizTarde,int cantPeliculas) {
		System.out.println("Los horarios disponibles son: ");
		for(int i=0;i<cantPeliculas;i++) {
			System.out.println(lpeliculas[i]);
			for(int j=0;j<3;j++) {
				if(matrizMaņana[i][j] == true) {
					System.out.println("\t"+(j+1)+"M");
				}
				if(matrizTarde[i][j]==true) {
					System.out.println("\t"+(j+1)+"T");
				}
			}
		}
	}
	
	/**
	 * With this function a new user will be registered.
	 * @param lnombres
	 * @param lapellidos
	 * @param lruts
	 * @param lcontraseņas
	 * @param lsaldos
	 * @param cantClientes
	 * return boolean
	 */
	
	public static boolean registrar(String [] lnombres,String [] lapellidos,String [] lruts,String [] lcontraseņas,int [] lsaldos,int cantClientes) {
		System.out.print("Ingresa tu Nombre");
		String nombre = leer.nextLine();
		System.out.print("Ingresa tu Apellido");
		String apellido = leer.nextLine();
		System.out.print("Ingresa tu Rut: ");
		String rut = leer.nextLine();
		System.out.print("Ingresa tu contraseņa:");
		String contraseņa = leer.nextLine();
		System.out.print("Ingresa tu saldo:");
		int saldo = leer.nextInt();
		for(int i=0;i<lnombres.length;i++) {
			if(lnombres[i] == null) {
				lnombres[i] = nombre;
				lapellidos[i] = apellido;
				lruts[i] = rut;
				lcontraseņas[i] = contraseņa;
				lsaldos[i] = saldo;
				System.out.println("Registro finalizado");
				return true;
			}
		}
		System.out.println("No se puedo realizar el registro");
		return false;
	}
	
	/**
	 * This function will recognize if the data of the user entered exists or if it is an administrator
	 *  and if any data entered is incorrect, it will request the information again.Finally returns the customer's position in the list.
	 *  @param rutsClientes
	 *  @param lnombres
	 *  @param lapellidos
	 *  @param lruts
	 *  @param lcontraseņas
	 *  @param lsaldos
	 *  @param cantClientes
	 *  return int
	 */
	
	public static int iniciarSesion(String [] rutsClientes,String [] lnombres,String [] lapellidos,String [] lruts,String [] lcontraseņas,int [] lsaldos,int cantClientes) {
		int cont = 0;
		int contadorPersona= 0;
		while(true) {
			boolean equivocado = false;
			System.out.println("Bienvenido");
			System.out.print("Ingrese su rut porfavor: ");
			String rut = leer.nextLine();
			verificarRut(rut);
			int posCliente = buscarEnLista(lruts, cantClientes, rut);
			if(posCliente == -1) {
				if(rut.equals("ADMIN")) {
					System.out.print("Ingrese su contraseņa:");
					String contraseņa = leer.nextLine();
					if(contraseņa.equals("ADMIN")) {
						return -1;
					}else {
						equivocado = true;
					}
				}else {
					System.out.print("Rut no registrado, Desea Registrarse? (SI) o (NO)");
					String resp = leer.nextLine();
					if(resp.equalsIgnoreCase("SI")) {
						boolean registrado =registrar(lnombres, lapellidos, lruts, lcontraseņas, lsaldos, cantClientes);
						if(registrado) {
							return 2;
						}else {
							return -2;
						}
					}else {
						System.out.println("---------------------");
					}
				}
			}else {
				System.out.print("Ingrese su contraseņa porfavor: ");
				String contraseņa = leer.nextLine();
				if(lcontraseņas[posCliente].equals(contraseņa)) {
					System.out.println("Ingreso exitoso.");
					return posCliente;
				}else {
					equivocado = true;
				}
			}
			if(equivocado) {
				System.out.println("Error al ingresar la contraseņa.");
				System.out.println("-------------------");
			}
			cont++;
		}
	}
	 
	/**
	 * this function opens the Admin menu and calls the corresponding subprograms
	 * @param cantidadListaDeAsientos
	 * @param lnombres
	 * @param lapellidos
	 * @param posCliente
	 * @param rutsClientes
	 * @param listaPeliculas
	 * @param lhorarios
	 * @param lsalas
	 * @param listaCantEntradas
	 * @param matrizAsientos
	 * @param lsaldos
	 * @param contRecaudacionMaņana
	 * @param contRecaudacionTarde
	 */
	
	public static void menuAdmin(int cantidadListaDeAsientos,String [] lnombres,String [] lapellidos,int posCliente,String [] rutsClientes,String [] listaPeliculas,String [] lhorarios,int [] lsalas,int [] listaCantEntradas,String [][] matrizAsientos,int[] lsaldos,int[] contRecaudacionMaņana ,int[] contRecaudacionTarde) {
		while(true) {
			System.out.println("Bienvenido al Menu ADMIN estas son las opciones disponibles");
	    	System.out.println("\tA)Taquilla");
	    	System.out.println("\tB)Info Cliente");
	    	System.out.println("\tC)Cerrar Sesion");
	    	System.out.println("Seleccione una opcion: ");
	    	String opcion = leer.nextLine();
	    	switch(opcion) {
	    	case("A"):
	    		//monto por pelicula prueeba con 1 nomas
	    		System.out.println("A lo largo del día se recaudo: "+(contRecaudacionMaņana[0]+contRecaudacionTarde[0]));
	    		System.out.println("El monto recaudado en la maņana fue: "+contRecaudacionMaņana[0]);
	    		System.out.println("El monto recaudado en la tarde fue: "+contRecaudacionTarde[0]);
	    		break;
	    	case("B"):
	    		System.out.println("Ingresel rut de la persona que desea obtener su informacion:");
	    		String rut = leer.nextLine();
	    		infoUsuario(cantidadListaDeAsientos, lnombres, lapellidos, rut, posCliente, rutsClientes, listaPeliculas, lhorarios, lsalas, listaCantEntradas, matrizAsientos, lsaldos);
	    		break;
	    	case("C"):
	    		break;
	    	case("D"):
	    		break;
	    	default: System.out.println("ELIJA A , B , C porfavor, Si desea salir ingrese D");break;
	    	}
	    	if(opcion.equalsIgnoreCase("D")) {
	    		break;
	    	}
		}
		System.out.println("Saliendo del Menu ADMIN");
		
	}
	
	/**
	 *display the client menu
	 *@param cantColumnasAsientos
	 *@param contRecaudacionMaņana
	 *@param contRecaudacionTarde
	 *@param rutsClientes
	 *@param listaPeliculas
	 *@param lhorarios
	 *@param lsalas
	 *@param listaCantEntradas
	 *@param matrizAsientoS
	 *@param matriz1M
	 *@param matriz2M
	 *@param matriz3M
	 *@param matriz1T
	 *@param matriz2T
	 *@param matriz3T
	 *@param listaNumeros
	 *@param listaLetras
	 *@param lpeliculas
	 *@param ltipos
	 *@param cantPeliculas
	 *@param posCliente
	 *@param matrizMaņana
	 *@param matrizTarde
	 *@param lnombres
	 *@param lapellidos
	 *@param lruts
	 *@param lcontraseņas
	 *@param lsaldos
	 *@param cantClientes
	 */
	
	public static void menuCliente(int cantColumnasAsientos,int[] contRecaudacionMaņana ,int[] contRecaudacionTarde,String [] rutsClientes,String [] listaPeliculas,String [] lhorarios,int [] lsalas,int [] listaCantEntradas,String [][] matrizAsientos,int[][]matriz1M,int [][] matriz2M,int [][] matriz3M,int [][] matriz1T,int [][] matriz2T,int [][] matriz3T,int [] listanumeros,String [] listaLetras,String [] lpeliculas,String [] ltipos,int cantPeliculas,int posCliente,boolean [][] matrizMaņana,boolean [][] matrizTarde,String [] lnombres,String [] lapellidos,String [] lruts,String [] lcontraseņas,int [] lsaldos,int cantClientes) {
		System.out.println("Bienvenido "+lnombres[posCliente]+" "+lapellidos[posCliente]);
		String rut = lruts[posCliente];
		while(true) {
			System.out.println("Bienvenido al Menu Cliente estas son las opciones disponibles");
	    	System.out.println("\tA)Comprar Entrada");
	    	System.out.println("\tB)Informacion Usuario");
	    	System.out.println("\tC)Devolucion Entrada");
	    	System.out.println("\tD)Cartelera");
	    	System.out.println("\tE)Cerrar Sesion");
	    	System.out.print("Seleccione una opción: ");
	    	String opcion = leer.nextLine();
	    	switch(opcion) {
	    		case("A"):
	    			rutsClientes[contPersona] = rut;
	    			comprarEntrada(contRecaudacionMaņana,contRecaudacionTarde,matrizAsientos, listaCantEntradas, lhorarios, lsalas, posCliente, lsaldos, matriz1M, matriz2M, matriz3M, matriz1T, matriz2T, matriz3T, listanumeros, listaLetras, cantPeliculas, listaPeliculas, lpeliculas, ltipos, matrizTarde, matrizTarde);
	    			break;
	    		case("B"):infoUsuario(cantColumnasAsientos,lnombres, lapellidos, rut, posCliente,rutsClientes, listaPeliculas, lhorarios, lsalas, listaCantEntradas, matrizAsientos,lsaldos);
	    				break;
	    		case("C"):
	    			devolucionEntrada(lpeliculas,cantPeliculas,cantColumnasAsientos,contRecaudacionMaņana,contRecaudacionTarde,listaLetras, listanumeros, ltipos, cantClientes, lnombres, lapellidos, rut, posCliente, rutsClientes, listaPeliculas, lhorarios, lsalas, listaCantEntradas, matrizAsientos, lsaldos, matriz3T, matriz3T, matriz3T, matriz3T, matriz3T, matriz3T);
	    				break;
	    		case("D"):obtenerCartelera(lpeliculas, matrizMaņana, matrizTarde, cantPeliculas);
	    				break;
	    		case("E"):
	    			break;
	    		default:System.out.println("Elija A ,B ,C , D o E porfavor ");break;
	    	}
	    	if(opcion.equalsIgnoreCase("E")) {
	    		break;
	    	}
	    	
		}
		System.out.println("\tSALIENDO DEL MENU CLIENTE");
	}

	/**
	 * main function, calls all other subprograms
	 * @param args
	 * @throws FileNotFoundException
	 */
	
	public static void main(String[] args) throws IOException {
		int cantListas = 200;
		String [] lnombres = new String[200];
		String [] lapellidos  = new String[200];
		String [] lruts = new String[200];
		String [] lcontraseņas = new String[200];
		int [] lsaldos = new int[200];
		String [] lpeliculas = new String [200];
		String [] ltipos = new String[200];
		int [] listaRecaudacionPelicula = new int[200];
		//
		String [] rutsClientes = new String [200];
		String [] listaPeliculas = new String [200];
		String [] lhorarios = new String[200];
		int [] lsalas = new int[200];
		int [] listaCantEntradas = new int[200];
		
		int[] contRecaudacionMaņana = new int[1];
		int[] contRecaudacionTarde = new int[1];
		
		int estreno= 5500;
		int liberada = 4000;
		double descuento = 0.15;
		
		
		int [] listanumeros = new int[30];
		for(int i=0;i<30;i++) {
			listanumeros[i] = i+1;
		}
		String [] listaLetras = new String[10];
		rellenarListaLetras(listaLetras);
		
		boolean [][] matrizMaņana = new boolean[100][3];//[FILAS][COLUMNAS] 100 por que es el contador de peliculas
		
		boolean [][] matrizTarde = new boolean[100][3];//[FILAS][COLUMNAS]
		
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
		int cantidadListaDeAsientos = 200;
		int cantColumnasAsientos = 20;
		String [][] matrizAsientos = new String[cantidadListaDeAsientos][cantColumnasAsientos];
		
		
		
		int cantClientes = leerClientes(lnombres, lapellidos, lruts, lcontraseņas, lsaldos);
		String [] listaPaseMovilidad = new String[cantClientes];
		leerStatus(lruts, listaPaseMovilidad, cantClientes);
		int cantPeliculas = leerPeliculas(lpeliculas, ltipos, listaRecaudacionPelicula, matrizMaņana, matrizTarde);
		while(true) {
			int posPersona = iniciarSesion(rutsClientes,lnombres, lapellidos, lruts, lcontraseņas, lsaldos, cantClientes);
			switch(posPersona) {
			case(-1):
				avanzar(0);
				menuAdmin(cantidadListaDeAsientos, lnombres, lapellidos, posPersona, rutsClientes, listaPeliculas, lhorarios, lsalas, listaCantEntradas, matrizAsientos, lsaldos, contRecaudacionMaņana, contRecaudacionTarde);
				break;
			case(2):
				System.out.println("Usuario registrado con exito");
				break;
			case(-2):
				System.out.println("El usuario no puede registrarse por que no hay espacio suficiente");
				break;
			default:
				avanzar(0);
				menuCliente(cantColumnasAsientos,contRecaudacionMaņana, contRecaudacionTarde, rutsClientes,listaPeliculas,lhorarios,lsalas,listaCantEntradas,matrizAsientos,matriz1M,matriz2M,matriz3M,matriz1T, matriz2T, matriz3T,listanumeros,listaLetras,lpeliculas,ltipos,cantPeliculas,posPersona,matrizMaņana,matrizTarde,lnombres,lapellidos,lruts,lcontraseņas,lsaldos,cantClientes);
				break;
			}
			System.out.println("Desea cerrar el sistema? (SI) o (NO)");
			String resp = leer.nextLine();
			while(!resp.equalsIgnoreCase("SI")&& !resp.equalsIgnoreCase("NO")) {
				System.out.println("Desea cerrar el sistema? (SI) o (NO)");
				resp = leer.nextLine();
			}
			if(resp.equalsIgnoreCase("SI")) {
				break;
			}
		}
		sobreescribir(matrizMaņana, matrizTarde, listaPaseMovilidad, cantClientes, cantPeliculas, lnombres, lapellidos, lruts, lcontraseņas, lsaldos);
		System.out.println("DATOS SOBREESCRITOS");

	}
	
	/**
	 * this function overwrites the data used in the system and saves it in txt files
	 * @param matrizMaņana
	 * @param matrizTarde
	 * @param listaPaseMovilidad
	 * @param cantClientes
	 * @param cantPeliculas
	 * @param lnombres
	 * @param lapellidos
	 * @param lruts
	 * @param lcontraseņas
	 * @param lsaldos
	 */
	

	public static void sobreescribir(boolean [][] matrizMaņana,boolean [][] matrizTarde,String [] listaPaseMovilidad,int cantClientes,int cantPeliculas,String [] lnombres,String [] lapellidos,String [] lruts,String [] lcontraseņas,int [] lsaldos) throws IOException {
		ArchivoSalida arch1 = new ArchivoSalida("clientes.txt");
		for(int i=0;i<cantClientes;i++) {
			Registro reg = new Registro(25);
			reg.agregarCampo(lnombres[i]);
			reg.agregarCampo(lapellidos[i]);
			reg.agregarCampo(lruts[i]);
			reg.agregarCampo(lcontraseņas[i]);
			reg.agregarCampo(lsaldos[i]);
		}
		arch1.close();
		ArchivoSalida arch2 = new ArchivoSalida("status.txt");
		for(int k=0;k<cantClientes;k++) {
			Registro reg2 = new Registro(2);
			reg2.agregarCampo(listaPaseMovilidad[k]);
		}
		arch2.close();
		ArchivoSalida arch3 = new ArchivoSalida("peliculas.txt");
		int cont = 0;
		int sala;
		String horario;
		for(int j=0;j<cantPeliculas;j++) {
			if(matrizMaņana[j][0] ==true) {
				sala = 1;
				horario = "M";
			}
			if(matrizMaņana[j][0] ==true) {
				sala = 2;
				horario = "M";
			}
			if(matrizMaņana[j][0] ==true) {
				sala = 3;
				horario = "M";
			}
			if(matrizTarde[j][0] ==true) {
				sala = 1;
				horario = "T";
			}
			if(matrizTarde[j][0] ==true) {
				sala = 2;
				horario = "T";
			}
			if(matrizTarde[j][0] ==true) {
				sala = 3;
				horario = "T";
			}
		}
		arch3.close();
	}
	
	public static Scanner leer = new Scanner(System.in);
	public static int contPersona = 0;
/*
 * String line = s.nextLine();
			//System.out.println(line);
			String [] partes = line.split(",");
			String nombrePelicula = partes[0];
			String tipo = partes[1];
			int recaudacion = Integer.parseInt(partes[2]);
			for(int i=3;i<partes.length;i+=2) {
				int numeroSala = Integer.parseInt(partes[i]);
				numeroSala--;
				String horario = partes[i+1];
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
 */
}
