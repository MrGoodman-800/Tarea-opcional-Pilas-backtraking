import java.util.Stack;

public class Main {
    public static void main(String[] args) {
        String archivo = "src/entrada3.lab";

        Laberinto laberinto = new Laberinto(archivo);
        Coordenada inicio = laberinto.buscarInicio();

        if (inicio == null) {
            System.out.println("No se encontro el inicio");
            return;
        }

        Stack<Coordenada> pila = new Stack<>();
        boolean[][] visitados = new boolean[laberinto.getFilas()][laberinto.getCols()];

        pila.push(inicio);
        boolean encontrado = false;

        int[] dX = {-1, 1, 0, 0};
        int[] dY = {0, 0, -1, 1};

        while (!pila.isEmpty()) {
            Coordenada actual = pila.peek();
            int x = actual.getX();
            int y = actual.getY();

            if (laberinto.esSalida(x, y)) {
                encontrado = true;
                break;
            }

            visitados[x][y] = true;
            boolean movido = false;

            for (int i = 0; i < 4; i++) {
                int nuevoX = x + dX[i];
                int nuevoY = y + dY[i];

                if (laberinto.esValido(nuevoX, nuevoY, visitados)) {
                    pila.push(new Coordenada(nuevoX, nuevoY));
                    movido = true;
                    break;
                }
            }

            if (!movido) {
                pila.pop();
            }
        }

        if (encontrado) {
            System.out.println("soolucion encontrada\n");
            laberinto.marcarCamino(pila);
        } else {
            System.out.println("no hay salida.");
        }

        laberinto.imprimirTablero();
    }
    }
