/**
 * La clase modela un sencillo pod�metro que registra informaci�n
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
     * Inicializa el pod�metro con la marca indicada por el par�metro.
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
     * Simula la configuraci�n del pod�metro.
     * Recibe como par�metros la altura y el sexo de una persona
     * y asigna estos valores a los atributos correspondiente.
     * Asigna adem�s el valor adecuado al atributo longitudZancada
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
     *  Recibe cuatro par�metros que supondremos correctos:
     *    pasos - el n� de pasos caminados
     *    dia - n� de d�a de la semana en que se ha hecho la caminata 
     *              (1 - Lunes, 2 - Martes - .... - 6 - S�bado, 7 - Domingo)
     *    horaInicio � hora de inicio de la caminata
     *    horaFina � hora de fin de la caminata
     *    
     *    A partir de estos par�metros el m�todo debe realizar ciertos c�lculos
     *    y  actualizar� el pod�metro adecuadamente  
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
     * Muestra en pantalla la configuraci�n del pod�metro
     * (altura, sexo y longitud de la zancada)
     * 
     * (ver enunciado)
     *  
     */
    public void printConfiguracion() {
        System.out.println(
            "Configuracion de pod�metro" +
            "\n*********************************" +
            "\nAltura :" + (altura /100) + " mtos" +
            "\nSexo: " + sexo +
            "\nLongitud zancada: " + (longitudZancada /100) + " mtos");
        System.out.println();

    }

    /**
     * Muestra en pantalla informaci�n acerca de la distancia recorrida,
     * pasos, tiempo total caminado, ....
     * 
     * (leer enunciado)
     *  
     */
    public void printEstad�sticas() {
        totalDistanciaSemana = ((totalPasosLaborables + totalPasosSabado + totalPasosDomingo) * longitudZancada) /1000;
        totalDistanciaFinSemana = ((totalPasosSabado + totalPasosDomingo) * longitudZancada) /1000;

        System.out.println("Estad�sticas" +
            "\n*********************************" +
            "\nDistancia recorrida toda la semana: " + (totalDistanciaSemana /100) + " Km" + 
            "\nDistancia recorrida fin de semana: " + (totalDistanciaFinSemana /100) + " Km");
        System.out.println();
        System.out.println("N�pasos dias laborables: " + (int)totalPasosLaborables +
            "\nN�pasos SABADO: " + (int)totalPasosSabado +
            "\nN�pasos DOMINGO: " + (int)totalPasosDomingo);
        System.out.println();
        System.out.println("N�caminatas realizadas a partir de las 21h: " + caminatasNoche);
        System.out.println();
        System.out.println("Tiempo total caminado en la semana: " + (tiempo /60) + "h. y " + (tiempo %60) + "m");

    }

    /**
     *  Calcula y devuelve un String que representa el nombre del d�a
     *  en el que se ha caminado m�s pasos - "S�BADO"   "DOMINGO" o  "LABORABLES"
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
     * Restablecer los valores iniciales del pod�metro
     * Todos los atributos se ponen a cero salvo el sexo
     * que se establece a MUJER. La marca no var�a
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
