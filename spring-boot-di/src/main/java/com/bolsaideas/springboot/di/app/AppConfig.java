package com.bolsaideas.springboot.di.app;

import java.util.Arrays;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import com.bolsaideas.springboot.di.app.models.domain.ItemFactura;
import com.bolsaideas.springboot.di.app.models.domain.Producto;
import com.bolsaideas.springboot.di.app.models.service.IServicio;
import com.bolsaideas.springboot.di.app.models.service.MiServicio;
import com.bolsaideas.springboot.di.app.models.service.MiServicioComplejo;

@Configuration
public class AppConfig {
	
	@Bean("MiServicioSimple")
	@Primary
	public IServicio registrarMiServicio() {
		
		return new MiServicio();
	}
	
	@Bean("MiServicioComplejo")
	public IServicio registrarMiServicioComplejo() {
		
		return new MiServicioComplejo();
	}
	
	@Bean("itemsFactura")
	public List<ItemFactura> registrarItems(){
		
		Producto producto1= new Producto("camara sol", 100);
		Producto producto2= new Producto("bicicleta", 500);
		
		ItemFactura linea1 = new ItemFactura(producto1, 2);
		ItemFactura linea2 = new ItemFactura(producto2, 4);
		
		return Arrays.asList(linea1, linea2);
		
	}
	
	@Bean("itemsFacturaOficina")
	public List<ItemFactura> registrarItemsOficina(){
		
		Producto producto1= new Producto("monitor LG 24", 250);
		Producto producto2= new Producto("laptop asus", 2750);
		Producto producto3= new Producto("impresora", 5240);
		Producto producto4= new Producto("auriculares", 170);
		
		ItemFactura linea1 = new ItemFactura(producto1, 2);
		ItemFactura linea2 = new ItemFactura(producto2, 4);
		ItemFactura linea3 = new ItemFactura(producto3, 5);
		ItemFactura linea4 = new ItemFactura(producto4, 7);
		
		return Arrays.asList(linea1, linea2, linea3, linea4);
		
	}

}
