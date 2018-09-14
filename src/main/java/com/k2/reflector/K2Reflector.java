package com.k2.reflector;

import com.k2.EntityMap.EntityMap;
import com.k2.core.model.K2Class;

public class K2Reflector {

	public static K2Reflector create(EntityMap entityMap) {
		return new K2Reflector(entityMap);
		
	}
	
	public K2Reflector(EntityMap entityMap) {
		this.entityMap = entityMap;
	}

	private final EntityMap entityMap;
	public EntityMap getEntityMap() {
		return entityMap;
	}

	public K2Class reflect(Class<?> cls) {
		// TODO Auto-generated method stub
		return null;
	}

}
