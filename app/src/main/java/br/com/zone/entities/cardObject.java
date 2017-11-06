package br.com.zone.entities;

/**
 * Created by Gabriel-PC on 06/11/2017.
 */

public class cardObject {
    private String description;
    private String title;
    private String horario;

    public cardObject(String description, String title, String horario) {
        this.description = description;
        this.title = title;
        this.horario = horario;
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
}
