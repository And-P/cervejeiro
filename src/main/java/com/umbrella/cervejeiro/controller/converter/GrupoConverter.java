package com.umbrella.cervejeiro.controller.converter;

import org.springframework.core.convert.converter.Converter;
import org.thymeleaf.util.StringUtils;

import com.umbrella.cervejeiro.model.Grupo;

public class GrupoConverter implements Converter<String, Grupo> {

	@Override
	public Grupo convert(String codigo) {
<<<<<<< HEAD
		
=======
>>>>>>> 8ff219625608587fecc7d009552241817f9d8816
		if (!StringUtils.isEmpty(codigo)) {
			Grupo grupo = new Grupo();
			grupo.setCodigo(Long.valueOf(codigo));
			return grupo;
		}
		
<<<<<<< HEAD
		return null;							
=======
		return null;
>>>>>>> 8ff219625608587fecc7d009552241817f9d8816
	}

}
