package com.example.demo.modelo;

import java.sql.Timestamp;


public class plantacion {
	private int id;
	private String nombre;
	private Float precio_compra;
	private Float precio_venta;
	private Timestamp proxima_cosecha;
	private int id_granjero;
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
	public Float getPrecio_compra() {
		return precio_compra;
	}
	public void setPrecio_compra(Float precio_compra) {
		this.precio_compra = precio_compra;
	}
	public Float getPrecio_venta() {
		return precio_venta;
	}
	public void setPrecio_venta(Float precio_venta) {
		this.precio_venta = precio_venta;
	}
	public Timestamp getProxima_cosecha() {
		return proxima_cosecha;
	}
	public void setProxima_cosecha(Timestamp proxima_cosecha) {
		this.proxima_cosecha = proxima_cosecha;
	}
	public int getId_granjero() {
		return id_granjero;
	}
	public void setId_granjero(int id_granjero) {
		this.id_granjero = id_granjero;
	}
}
