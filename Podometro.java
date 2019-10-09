/**
 * La clase modela un sencillo podómetro que registra información
 * acerca de los pasos, distancia, ..... que una persona (hombre o mujer)
 * ha dado en una semana. 
 * 
 * @author Antonio Nieto 
 * 
 */
public class Podometro {
    private final char HOMBRE = 'H';
    private final char MUJER = 'M';
    private final double ZANCADA_HOMBRE = 0.45;
    private final double ZANCADA_MUJER = 0.41;
    private final int SABADO = 6;
    private final int DOMINGO = 7;
    private String marca;
    private double altura;
    private char sexo;
    private int tiempo;
    private double longitudZancada;
    private double totalPasosLaborables;
    private double totalPasosSabado;
    private double totalPasosDomingo;
    private double totalDistanciaSemana;
    private double totalDistanciaFinSemana;
    private int caminatasNoche;
    private String DiaMayorNumeroPasos;
    /**
     * Inicializa el podómetro con la marca indicada por el parámetro.
     * El resto de atributos se ponen a 0 y el sexo, por defecto, es mujer
     */
    public Podometro(String queMarca) {
        marca = queMarca;
        altura = 0;
        sexo = MUJER;
        longitudZancada = 0;
    }

    /**
     * accesor para la marca
     *  
     */
    public String getMarca() {
        return marca;       
    }

    /**
     * Simula la configuración del podómetro.
     * Recibe como parámetros la altura y el sexo de una persona
     * y asigna estos valores a los atributos correspondiente.
     * Asigna además el valor adecuado al atributo longitudZancada
     * 
     * (leer enunciado)
     *  
     */
    public void configurar(double queAltura, char queSexo) {
        altura = queAltura;
        sexo = queSexo;

        if(sexo == HOMBRE){
            longitudZancada = Math.ceil(altura * ZANCADA_HOMBRE);
        }
        else {
            longitudZancada = Math.floor(altura * ZANCADA_MUJER);
        }

    }

    /**
     *  Recibe cuatro parámetros que supondremos correctos:
     *    pasos - el nº de pasos caminados
     *    dia - nº de día de la semana en que se ha hecho la caminata 
     *              (1 - Lunes, 2 - Martes - .... - 6 - Sábado, 7 - Domingo)
     *    horaInicio – hora de inicio de la caminata
     *    horaFina – hora de fin de la caminata
     *    
     *    A partir de estos parámetros el método debe realizar ciertos cálculos
     *    y  actualizará el podómetro adecuadamente  
     *   
     *   (leer enunciado del ejercicio)
     */
    public void registrarCaminata(int pasos, int dia, int horaInicio,
    int horaFin) {
        int horas = (horaFin /100) - (horaInicio /100);
        int minutos = (horaFin %100) - (horaInicio %100);
        tiempo += (horas * 60) + minutos; 
        String nombreDia;          
        switch (dia)
        {
            case 1: nombreDia= "Lunes";
            break;
            case 2: nombreDia= "Martes";
            break;
            case 3: nombreDia= "Miercoles";
            break;
            case 4: nombreDia= "Jueves";
            break;
            case 5: nombreDia= "Viernes";
            break;
            case 6: nombreDia= "Sabado";
            break;
            case 7: nombreDia= "Domingo";
            break;
            default: nombreDia= "Incorrecto";
            break;
        }
        if (dia <= 5){
            totalPasosLaborables += pasos;
        }
        if (dia == 6){
            totalPasosSabado += pasos;
        }
        if (dia == 7){
            totalPasosDomingo += pasos;
        }
        if ((horaInicio /100) >= 21){
            caminatasNoche ++;
        }

    }

    /**
     * Muestra en pantalla la configuración del podómetro
     * (altura, sexo y longitud de la zancada)
     * 
     * (ver enunciado)
     *  
     */
    public void printConfiguracion() {
        System.out.println(
            "Configuracion de podómetro" +
            "\n*********************************" +
            "\nAltura :" + (altura /100) + " mtos" +
            "\nSexo: " + sexo +
            "\nLongitud zancada: " + (longitudZancada /100) + " mtos");
        System.out.println();

    }

    /**
     * Muestra en pantalla información acerca de la distancia recorrida,
     * pasos, tiempo total caminado, ....
     * 
     * (leer enunciado)
     *  
     */
    public void printEstadísticas() {
        totalDistanciaSemana = ((totalPasosLaborables + totalPasosSabado + totalPasosDomingo) * longitudZancada) /1000;
        totalDistanciaFinSemana = ((totalPasosSabado + totalPasosDomingo) * longitudZancada) /1000;

        System.out.println("Estadísticas" +
            "\n*********************************" +
            "\nDistancia recorrida toda la semana: " + (totalDistanciaSemana /100) + " Km" + 
            "\nDistancia recorrida fin de semana: " + (totalDistanciaFinSemana /100) + " Km");
        System.out.println();
        System.out.println("Nºpasos dias laborables: " + (int)totalPasosLaborables +
            "\nNºpasos SABADO: " + (int)totalPasosSabado +
            "\nNºpasos DOMINGO: " + (int)totalPasosDomingo);
        System.out.println();
        System.out.println("Nºcaminatas realizadas a partir de las 21h: " + caminatasNoche);
        System.out.println();
        System.out.println("Tiempo total caminado en la semana: " + (tiempo /60) + "h. y " + (tiempo %60) + "m");

    }

    /**
     *  Calcula y devuelve un String que representa el nombre del día
     *  en el que se ha caminado más pasos - "SÁBADO"   "DOMINGO" o  "LABORABLES"
     */
    public String diaMayorNumeroPasos() {
        String diaMayorNumeroPasos = "";
        if(totalPasosSabado > totalPasosDomingo && totalPasosSabado > totalPasosLaborables){
            diaMayorNumeroPasos = "SABADO";
        }
        else if(totalPasosDomingo > totalPasosSabado && totalPasosDomingo > totalPasosLaborables){
            diaMayorNumeroPasos = "DOMINGO";
        }
        else if(totalPasosLaborables > totalPasosSabado && totalPasosLaborables > totalPasosDomingo){
            diaMayorNumeroPasos = "LABORABLES";
        }
        return diaMayorNumeroPasos;
    }

    /**
     * Restablecer los valores iniciales del podómetro
     * Todos los atributos se ponen a cero salvo el sexo
     * que se establece a MUJER. La marca no varía
     *  
     */    
    public void reset() {
        altura = 0;
        sexo = MUJER;
        longitudZancada= 0;
        tiempo = 0;
        caminatasNoche = 0;
        totalPasosLaborables = 0;
        totalDistanciaFinSemana = 0;
        totalPasosSabado = 0;
        totalPasosDomingo = 0;
        totalDistanciaSemana = 0;

    }
}
