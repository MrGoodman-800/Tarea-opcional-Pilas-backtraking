import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Stack;

public class Laberinto {

        private String[][] tablero;
        private int filas;
        private int cols;

        public Laberinto(String rutaArchivo) {
            cargarLaberinto(rutaArchivo);
        }

        private void cargarLaberinto(String ruta) {
            try (BufferedReader br = new BufferedReader(new FileReader(ruta))) {
                this.filas = Integer.parseInt(br.readLine().trim());
                this.cols = Integer.parseInt(br.readLine().trim());

                this.tablero = new String[filas][cols];
                for (int i = 0; i < filas; i++) {
                    String linea = br.readLine();
                    if (linea != null) {
                        tablero[i] = linea.split(",");
                    }
                }
            } catch (IOException e) {
                System.out.println("Error al leer el archivo del laberinto: " + e.getMessage());
            }
        }

        public Coordenada buscarInicio() {
            for (int i = 0; i < filas; i++) {
                for (int j = 0; j < cols; j++) {
                    if (tablero[i][j].trim().equalsIgnoreCase("S")) {
                        return new Coordenada(i, j);
                    }
                }
            }
            return null;
        }

        public boolean esValido(int x, int y, boolean[][] visitados) {
            if (x >= 0 && x < filas && y >= 0 && y < cols) {
                String val = tablero[x][y].trim();
                return (val.equals("0") || val.equalsIgnoreCase("E")) && !visitados[x][y];
            }
            return false;
        }

        public boolean esSalida(int x, int y) {
            return tablero[x][y].trim().equalsIgnoreCase("E");
        }

        public void marcarCamino(Stack<Coordenada> pila) {
            for (Coordenada c : pila) {
                int x = c.getX();
                int y = c.getY();
                if (tablero[x][y].trim().equals("0")) {
                    tablero[x][y] = "*";
                }
            }
        }

        public void imprimirTablero() {
            for (int i = 0; i < filas; i++) {
                for (int j = 0; j < cols; j++) {
                    System.out.print(tablero[i][j] + " ");
                }
                System.out.println();
            }
        }

        public int getFilas() { return filas; }
        public int getCols() { return cols; }
    }

