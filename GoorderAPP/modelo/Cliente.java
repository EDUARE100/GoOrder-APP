package modelo;

    public class Cliente {
    
    //Atributos
    
    private int id;
    private String nombre;
    private String apellido;
    private String telefono;
    private int mesa;
   
    
    //Constructor
    public Cliente(){
        this.id=0;
        this.nombre = "";
        this.apellido = "";
        this.telefono = "";
        this.mesa=0;
    }

    public int getMesa() {
        return mesa;
    }

    public void setMesa(int mesa) {
        this.mesa = mesa;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }}



   