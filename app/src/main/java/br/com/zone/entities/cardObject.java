package br.com.zone.entities;

/**
 * Created by Gabriel-PC on 06/11/2017.
 */

public class cardObject {
    private String description;
    private String title;
    private String horario;
    private String tipo;
    private String data;
    private int isShown;
    private int id;

    public cardObject(String description, String title, String horario, String tipo, String data, int isShown, int id) {
        this.description = description;
        this.title = title;
        this.horario = horario;
        this.tipo = tipo;
        this.data = data;
        this.isShown = isShown;
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getHorario() {
        return horario;
    }

    public void setHorario(String horario) {
        this.horario = horario;
    }
    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }


    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    public int getIsShown() {
        return isShown;
    }

    public void setIsShown(int isShown) {
        this.isShown = isShown;
    }
}
