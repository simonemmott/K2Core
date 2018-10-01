package com.k2.core.model;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

import com.k2.common.annotation.MetaComponent;
import com.k2.common.annotation.MetaField;
import com.k2.core.types.ClassType;

@MetaComponent(id=10)
@Entity
@DiscriminatorValue("ENTITY")
public class K2Entity extends K2Class{

	public K2Entity(Long id) {
		super(id, ClassType.ENTITY);
	}

	
}
