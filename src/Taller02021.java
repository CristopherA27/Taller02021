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
	
	public static void avanzar(int num) {
		if(num == 1) {
			System.out.println("\tPrecione Enter para continuar");
			leer.nextLine();
		}
		System.out.println("-------------------------------------------");
	}
	
	public static int leerPeliculas(String [] lpeliculas,String [] ltipos,int [] listaRecaudacionPelicula,boolean [][] matrizMañana,boolean [][] matrizTarde) throws FileNotFoundException {
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
					matrizMañana[cont][numeroSala]=true;

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
	
	
	public static String verificarRut(String rutNormal) {
		String nuevoRut = rutNormal.replace(".","");
		nuevoRut = nuevoRut.replace("-","");
		nuevoRut = nuevoRut.replace("K","k");
		return nuevoRut;
	}
	//sirve para crear la matriz
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
	
	public static boolean tienePaseMovilidad(String [] lruts,String [] listaPaseMovilidad,int cantClientes,int posCliente) {
		for(int i=0;i<cantClientes;i++) {
			if(listaPaseMovilidad[posCliente].equals("HABILITADO"));
			return true;
		}
		return false;
	}
	
	//Esta Lista
	public static int horariosDisponiblesPelicula(String [] listaPeliculas,String [] lpeliculas,String [] ltipos,int cantPeliculas,boolean [][] matrizMañana,boolean [][] matrizTarde) {
		System.out.print("Ingrese el nombre de la pelicula para ver sus horarios: ");
		String pelicula = leer.nextLine();
		listaPeliculas[contPersona]=pelicula;
		int posPelicula = buscarEnLista(lpeliculas, cantPeliculas, pelicula);
		if(posPelicula == -1) {
			System.out.println("La pelicula ingresada no existe");
		}else {
			System.out.println("Los horarios disponibles son:");
			for(int j=0;j<3;j++) {
				if(matrizMañana[posPelicula][j] == true) {
					System.out.print(j+1+"M"+"\n");
				}
				if(matrizTarde[posPelicula][j] == true) {
					System.out.print(j+1+"T"+"\n");
				}
			}
		}
		return posPelicula;

	}

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
	
	
	
	
	public static void tituloMatriz(int i,String dato) {
		System.out.println("\t-------------------------------------------------------SALA "+i+dato+"-------------------------------------------------\t|");
	}
	
	public static void reglasSala() {
		System.out.println();
		System.out.println("Por temas de protocolo covid los asientos que contengan un "+" -1 "+"no pueden ser ocupados ");
		System.out.println("Los asientos que tienen un 5 no estan habilitados");
		System.out.println("Los asientos que contengan un 0 son los asientos que puede comprar!");
		System.out.println();
		avanzar(0);
	}
	public static void ingresarLista(int cantidad,String [] lista,String dato ) {
		for(int i=0;i<cantidad;i++) {
			if(lista[i].equals(null)) {
				lista[i] = dato;
			}
			if(i==cantidad) {
				System.out.println("No hay mas espacio para guardar el dato");
			}
		}
	}
	
	public static void ingresarListaSalas(int cantidad,int [] lista,int dato ) {
		for(int i=0;i<cantidad;i++) {
			if(lista[i]==0) {
				lista[i] = dato;
			}
			if(i==cantidad) {
				System.out.println("No hay mas espacio para guardar el dato");
			}
		}
	}
	
	public static boolean comprobarAsiento(String [][] matrizAsientos,String fila,int columna,String [] rutsClientes,int posCliente) {
		for(int i=0;i<rutsClientes.length;i++) {
			
		}
	}
	

	public static void infoUsuario(int cantClientes,String [] lnombres,String [] lapellidos,String rut,int posCliente,String [] rutsClientes,String [] listaPeliculas,String [] lhorarios,int [] lsalas,int [] listaCantEntradas,String [][] matrizAsientos,int[] lsaldos) {
		for(int i=0;i<cantClientes;i++) {
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
	
<<<<<<< HEAD

	public static void comprarEntrada(String [][] matrizAsientos,int [] listaCantEntradas,String [] lhorarios,int [] lsalas,int posPersona,int [] lsaldos,int[][]matriz1M,int [][] matriz2M,int [][] matriz3M,int [][] matriz1T,int [][] matriz2T,int [][] matriz3T,int [] listanumeros,String [] listaLetras,int cantPeliculas,String [] listaPeliculas,String [] lpeliculas,String [] ltipos,boolean [][] matrizMañana,boolean [][] matrizTarde) {
=======
	/**
	 *This function will obtain the seats to buy on the screen and will fill the matrix of the corresponding room with a 1 when buying the seat
	 *@param matrizAsientos
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
	 *@param matrizMañana
	 *@param matrizTarde
	 */
	
	public static void comprarEntrada( int [] contRecaudacionMañana ,int [] contRecaudacionTarde ,String [][] matrizAsientos,int [] listaCantEntradas,String [] lhorarios,int [] lsalas,int posPersona,int [] lsaldos,int[][]matriz1M,int [][] matriz2M,int [][] matriz3M,int [][] matriz1T,int [][] matriz2T,int [][] matriz3T,int [] listanumeros,String [] listaLetras,int cantPeliculas,String [] listaPeliculas,String [] lpeliculas,String [] ltipos,boolean [][] matrizMañana,boolean [][] matrizTarde) {
>>>>>>> parent of 06c0d41 (Revert "Cris me cai como la cornetera")
		int posPelicula = horariosDisponiblesPelicula(listaPeliculas, lpeliculas, ltipos, cantPeliculas, matrizMañana, matrizTarde);
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
					
					if(ltipos[posPelicula]=="estreno") {
						contRecaudacionMañana[0] += (5500*cantAsientos); 
					
					}
					if(ltipos[posPelicula]=="liberada") {
						contRecaudacionMañana[0] += (4000*cantAsientos);
							
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
					
					if(ltipos[posPelicula]=="estreno") {
						contRecaudacionTarde[0] += (5500*cantAsientos)
					
					}
					if(ltipos[posPelicula]=="liberada") {
						contRecaudacionTarde[0] += (4000*cantAsientos)
					
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
			}else {
				System.out.println("Su saldo es insuficiente.....Desea recargar saldo? (SI) o (NO)");
				String resp = leer.nextLine();
				if(resp.equalsIgnoreCase("SI")) {
					cargarSaldo();
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
			}else {
				System.out.println("Su saldo es insuficiente.....Desea recargar saldo? (SI) o (NO)");
				String resp = leer.nextLine();
				if(resp.equalsIgnoreCase("SI")) {
					cargarSaldo();
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
	
<<<<<<< HEAD
<<<<<<< HEAD
	public static void devolucionEntrada(int [] listanumeros,String [] listaLetras,int cantClientes,String [] lnombres,String [] lapellidos,String rut,int posCliente,String [] rutsClientes,String [] listaPeliculas,String [] lhorarios,int [] lsalas,int [] listaCantEntradas,String [][] matrizAsientos,int[] lsaldos) {
=======
<<<<<<< HEAD
=======
>>>>>>> parent of dd8b9c6 (tu anciana)
	/**
	 *The matrix entries of the movie theater that the user enters on screen will be eliminated.
	 *@param listanumeros
	 *@param listaLetras
	 *@param cantClientes
	 *@param lnombres
	 *@param lapellidos
	 *@param rut
	 *@param posCliente
	 *@param rutsClientes
	 *@param listaPeliculas
	 *@param lhorarios
	 *@param lsalsas
	 *@param listaCantEntradas
	 *@param matrizAsientos
	 *@param lsaldos
	 */
	
	public static void devolucionEntrada(int [] contRecaudacionMañana ,int [] contRecaudacionTarde , int [] listanumeros,String [] listaLetras,int cantClientes,String [] lnombres,String [] lapellidos,String rut,int posCliente,String [] rutsClientes,String [] listaPeliculas,String [] lhorarios,int [] lsalas,int [] listaCantEntradas,String [][] matrizAsientos,int[] lsaldos, String [] ltipos) {
<<<<<<< HEAD
<<<<<<< HEAD
=======
	public static void eliminarEnMatriz(String [][] matriz,int cantClientes,int posFila,String dato) {
		for(int j=0;j<cantClientes;j++) {
			if(matriz[posFila][j]==null) {
				
			}
			else if(matriz[posFila][j].equals(dato)) {
					matriz[posFila][j] = null;
					
			}
		}
	}
	
	public static void devolucionEntrada(int [] listanumeros,String [] listaLetras,int cantClientes,String [] lnombres,String [] lapellidos,String rut,int posCliente,String [] rutsClientes,String [] listaPeliculas,String [] lhorarios,int [] lsalas,int [] listaCantEntradas,String [][] matrizAsientos,int[] lsaldos,int[][]matriz1M,int [][] matriz2M,int [][] matriz3M,int [][] matriz1T,int [][] matriz2T,int [][] matriz3T) {
>>>>>>> 87183d3f2875eecd7485897e8e4afc37e2dbfc99
>>>>>>> parent of e31bdf2 (Revert "tu anciana")
=======
>>>>>>> parent of 06c0d41 (Revert "Cris me cai como la cornetera")
=======
>>>>>>> parent of dd8b9c6 (tu anciana)
		System.out.println("Las entradas que usted tiene son: ");
		infoUsuario(cantClientes, lnombres, lapellidos, rut, posCliente, rutsClientes, listaPeliculas, lhorarios, lsalas, listaCantEntradas, matrizAsientos, lsaldos);
		System.out.print("Ingrese el nombre de la pelicula a la cual desea devolver las entradas:");
		String nombrePelicula = leer.nextLine();
		int posPelicula = buscarEnLista(listaPeliculas, listaPeliculas.length, nombrePelicula);
		if(posPelicula == -1) {
			System.out.println("pelicula no ecnontrada.");
			devolucionEntrada(listanumeros,listaLetras,cantClientes, lnombres, lapellidos, rut, posCliente, rutsClientes, listaPeliculas, lhorarios, lsalas, listaCantEntradas, matrizAsientos, lsaldos);
		}else {
			System.out.println("Ingrese la cantidad de entradas que desea devolver: ");
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
					//String dato  = "";
					int numSala = lsalas[posPelicula];
					String horarioSala = lhorarios[posPelicula];
					System.out.println();
					for(int j=1;j<=3;j++) {
						if(funcion.equalsIgnoreCase(j+"M")) {
							dato +="M";
<<<<<<< HEAD
<<<<<<< HEAD
=======
=======
>>>>>>> parent of 06c0d41 (Revert "Cris me cai como la cornetera")
							
							if(ltipos[posPelicula]=="estreno") {
								contRecaudacionMañana[0] -= (5500*0.8);
							}
							if(ltipos[posPelicula]=="liberada") {
								contRecaudacionMañana[0] -= (4000*0.8);
							}
							
							
<<<<<<< HEAD
<<<<<<< HEAD
=======
						if(dato.equalsIgnoreCase(j+"M")) {
>>>>>>> 87183d3f2875eecd7485897e8e4afc37e2dbfc99
>>>>>>> parent of e31bdf2 (Revert "tu anciana")
=======
>>>>>>> parent of 06c0d41 (Revert "Cris me cai como la cornetera")
=======
>>>>>>> parent of dd8b9c6 (tu anciana)
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
<<<<<<< HEAD
<<<<<<< HEAD
=======
=======
>>>>>>> parent of 06c0d41 (Revert "Cris me cai como la cornetera")
							
							if(ltipos[posPelicula]=="estreno") {
								contRecaudacionTarde[0] -= (5500*0.8)
							}
							if(ltipos[posPelicula]=="liberada") {
								contRecaudacionTarde[0] -= (4000*0.8)
							}
<<<<<<< HEAD
<<<<<<< HEAD
=======
						}else if(dato.equals(j+"T")) {
>>>>>>> 87183d3f2875eecd7485897e8e4afc37e2dbfc99
>>>>>>> parent of e31bdf2 (Revert "tu anciana")
=======
>>>>>>> parent of 06c0d41 (Revert "Cris me cai como la cornetera")
=======
>>>>>>> parent of dd8b9c6 (tu anciana)
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
			}
			
		}
		
	}
	/*for(int i=0;i<cantAsientos;i++) {
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
		}*/
	
	
	
	
	
<<<<<<< HEAD
	
=======
	/**
	 * charge the balance corresponding to the customer entered
	 * @param posCliente
	 * @param cantClientes
	 * @param lsaldos
	 */
<<<<<<< HEAD
=======
	
>>>>>>> 87183d3f2875eecd7485897e8e4afc37e2dbfc99
>>>>>>> parent of e31bdf2 (Revert "tu anciana")
=======
>>>>>>> parent of dd8b9c6 (tu anciana)
	
	public static void cargarSaldo(int posCliente, int cantClientes,int [] lsaldos) {
		System.out.println("Cuanto dinero desea gregar a su saldo?: ");
		int agregado = leer.nextInt();
		lsaldos[posCliente]+=agregado;
		
	}	
	
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
	
	public static void obtenerCartelera(String [] lpeliculas,boolean [][] matrizMañana,boolean [][] matrizTarde,int cantPeliculas) {
		System.out.println("Los horarios disponibles son: ");
		for(int i=0;i<cantPeliculas;i++) {
			System.out.println(lpeliculas[i]);
			for(int j=0;j<3;j++) {
				if(matrizMañana[i][j] == true) {
					System.out.println("\t"+(j+1)+"M");
				}
				if(matrizTarde[i][j]==true) {
					System.out.println("\t"+(j+1)+"T");
				}
			}
		}
	}
	
	public static boolean registrar(String [] lnombres,String [] lapellidos,String [] lruts,String [] lcontraseñas,int [] lsaldos,int cantClientes) {
		System.out.print("Ingresa tu Nombre");
		String nombre = leer.nextLine();
		System.out.print("Ingresa tu Apellido");
		String apellido = leer.nextLine();
		System.out.print("Ingresa tu Rut: ");
		String rut = leer.nextLine();
		System.out.print("Ingresa tu contraseña:");
		String contraseña = leer.nextLine();
		System.out.print("Ingresa tu saldo:");
		int saldo = leer.nextInt();
		for(int i=0;i<lnombres.length;i++) {
			if(lnombres[i] == null) {
				lnombres[i] = nombre;
				lapellidos[i] = apellido;
				lruts[i] = rut;
				lcontraseñas[i] = contraseña;
				lsaldos[i] = saldo;
				System.out.println("Registro finalizado");
				return true;
			}
		}
		System.out.println("No se puedo realizar el registro");
		return false;
	}
	
	public static int iniciarSesion(String [] rutsClientes,String [] lnombres,String [] lapellidos,String [] lruts,String [] lcontraseñas,int [] lsaldos,int cantClientes) {
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
					System.out.print("Ingrese su contraseña:");
					String contraseña = leer.nextLine();
					if(contraseña.equals("ADMIN")) {
						return -1;
					}else {
						equivocado = true;
					}
				}else {
					System.out.print("Rut no registrado, Desea Registrarse? (SI) o (NO)");
					String resp = leer.nextLine();
					if(resp.equalsIgnoreCase("SI")) {
						boolean registrado =registrar(lnombres, lapellidos, lruts, lcontraseñas, lsaldos, cantClientes);
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
				System.out.print("Ingrese su contraseña porfavor: ");
				String contraseña = leer.nextLine();
				if(lcontraseñas[posCliente].equals(contraseña)) {
					System.out.println("Ingreso exitoso.");
					return posCliente;
				}else {
					equivocado = true;
				}
			}
			if(equivocado) {
				System.out.println("Error al ingresar la contraseña.");
				System.out.println("-------------------");
			}
			cont++;
		}
	}
	
	public static void cargarSaldo() {
		
	}
	

	public static void menuAdmin() {
		
		
	}
	
	public static void menuCliente(String [] rutsClientes,String [] listaPeliculas,String [] lhorarios,int [] lsalas,int [] listaCantEntradas,String [][] matrizAsientos,int[][]matriz1M,int [][] matriz2M,int [][] matriz3M,int [][] matriz1T,int [][] matriz2T,int [][] matriz3T,int [] listanumeros,String [] listaLetras,String [] lpeliculas,String [] ltipos,int cantPeliculas,int posCliente,boolean [][] matrizMañana,boolean [][] matrizTarde,String [] lnombres,String [] lapellidos,String [] lruts,String [] lcontraseñas,int [] lsaldos,int cantClientes) {
		int cont = 0;
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
	    			comprarEntrada(matrizAsientos, listaCantEntradas, lhorarios, lsalas, posCliente, lsaldos, matriz1M, matriz2M, matriz3M, matriz1T, matriz2T, matriz3T, listanumeros, listaLetras, cantPeliculas, listaPeliculas, lpeliculas, ltipos, matrizTarde, matrizTarde);
	    			break;
	    		case("B"):infoUsuario(cantClientes,lnombres, lapellidos, rut, posCliente,rutsClientes, listaPeliculas, lhorarios, lsalas, listaCantEntradas, matrizAsientos,lsaldos);
	    				break;
	    		case("C"):
	    			devolucionEntrada(cantClientes, lnombres, lapellidos, rut, posCliente, rutsClientes, listaPeliculas, lhorarios, lsalas, listaCantEntradas, matrizAsientos, lsaldos);
	    				break;
	    		case("D"):System.out.println("d");
	    				
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

	
	public static void main(String[] args) throws FileNotFoundException {
		int cantListas = 200;
		String [] lnombres = new String[200];
		String [] lapellidos  = new String[200];
		String [] lruts = new String[200];
		String [] lcontraseñas = new String[200];
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
		
		int [] contRecaudacionMañana = new int[1];
		int [] contRecaudacionTarde = new int[1];
		
		int estreno= 5500;
		int liberada = 4000;
		double descuento = 0.15;
		
		
		int [] listanumeros = new int[30];
		for(int i=0;i<30;i++) {
			listanumeros[i] = i+1;
		}
		String [] listaLetras = new String[10];
		rellenarListaLetras(listaLetras);
		
		boolean [][] matrizMañana = new boolean[100][3];//[FILAS][COLUMNAS] 100 por que es el contador de peliculas
		
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
		String [][] matrizAsientos = new String[200][10];
		
		int cantClientes = leerClientes(lnombres, lapellidos, lruts, lcontraseñas, lsaldos);
		String [] listaPaseMovilidad = new String[cantClientes];
		leerStatus(lruts, listaPaseMovilidad, cantClientes);
		int cantPeliculas = leerPeliculas(lpeliculas, ltipos, listaRecaudacionPelicula, matrizMañana, matrizTarde);
		while(true) {
			int posPersona = iniciarSesion(rutsClientes,lnombres, lapellidos, lruts, lcontraseñas, lsaldos, cantClientes);
			switch(posPersona) {
			case(-1):
				avanzar(0);
				menuAdmin();
				break;
			case(2):
				System.out.println("Usuario registrado con exito");
				break;
			case(-2):
				System.out.println("El usuario no puede registrarse por que no hay espacio suficiente");
				break;
			default:
				avanzar(0);
				menuCliente(rutsClientes,listaPeliculas,lhorarios,lsalas,listaCantEntradas,matrizAsientos,matriz1M,matriz2M,matriz3M,matriz1T, matriz2T, matriz3T,listanumeros,listaLetras,lpeliculas,ltipos,cantPeliculas,posPersona,matrizMañana,matrizTarde,lnombres,lapellidos,lruts,lcontraseñas,lsaldos,cantClientes);
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
		System.out.println("DATOS SOBREESCRITOS");
		
		for(int i=0;i<3;i++) {
			System.out.println(listaPeliculas[i]);
		}

	}
	
	public static Scanner leer = new Scanner(System.in);
	public static int contPersona = 0;

}
