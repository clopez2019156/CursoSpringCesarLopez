package com.bolsaideas.springboot.di.app.models.service;



//@Component("MiServicioSimple")
//@Primary
public class MiServicio implements IServicio{
	
	@Override
	public String operacion() {
		return "ejecutando algun proceso importante simple";
	}

	
}
