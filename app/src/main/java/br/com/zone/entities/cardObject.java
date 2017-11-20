package br.com.zone.entities;

/**
 * Created by Gabriel-PC on 06/11/2017.
 */

public class cardObject {
    private String description;
    private String title;
    private String horario;
    private String duração;
    private String isSemanal;
    private String data;
    private int id;

    public cardObject(String description, String title, String horario, String duração, String isSemanal, String data, int id) {
        this.description = description;
        this.title = title;
        this.horario = horario;
        this.duração = duração;
        this.isSemanal = isSemanal;
        this.data = data;
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
    public String getIsSemanal() {
        return isSemanal;
    }

    public void setIsSemanal(String isSemanal) {
        this.isSemanal = isSemanal;
    }

    public void setduração(String duração) {
        this.duração = duração;
    }
    public String getduração(){
        return duração;
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
}
