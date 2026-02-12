package com.example.demo.modelo;

public class riegos {
	private int id;
	private String tipo;
	private int velocidad; 
	private int id_plantacion;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	public int getVelocidad() {
		return velocidad;
	}
	public void setVelocidad(int velocidad) {
		this.velocidad = velocidad;
	}
	public int getId_plantacion() {
		return id_plantacion;
	}
	public void setId_plantacion(int id_plantacion) {
		this.id_plantacion = id_plantacion;
	}
}
