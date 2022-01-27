package com.bolsaideas.springboot.di.app.models.service;

import org.springframework.stereotype.Component;


//@Component("MiServicioComplejo")
public class MiServicioComplejo implements IServicio{
	
	@Override
	public String operacion() {
		return "ejecutando algun proceso importante complicado";
	}

	
}
