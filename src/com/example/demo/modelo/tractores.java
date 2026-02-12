package com.example.demo.modelo;

public class tractores {
	private int id;
	private String modelo;
	private int velocidad;
	private Float precio_venta;
	private int id_construccion;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getModelo() {
		return modelo;
	}
	public void setModelo(String modelo) {
		this.modelo = modelo;
	}
	public int getVelocidad() {
		return velocidad;
	}
	public void setVelocidad(int velocidad) {
		this.velocidad = velocidad;
	}
	public Float getPrecio_venta() {
		return precio_venta;
	}
	public void setPrecio_venta(Float precio_venta) {
		this.precio_venta = precio_venta;
	}
	public int getId_construccion() {
		return id_construccion;
	}
	public void setId_construccion(int id_construccion) {
		this.id_construccion = id_construccion;
	}
}
