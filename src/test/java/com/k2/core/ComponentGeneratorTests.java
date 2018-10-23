package com.k2.core;

import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.lang.invoke.MethodHandles;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.k2.EntityMap.EntitiesMap;
import com.k2.JavaAssembly.JavaWidgetFactory;
import com.k2.Util.ObjectUtil;
import com.k2.Util.classes.ClassUtil;
import com.k2.common.dao.K2Dao;
import com.k2.common.dao.K2DaoFactory;
import com.k2.common.domain.K2DomainManager;
import com.k2.common.model.K2Class;
import com.k2.common.model.K2Component;
import com.k2.common.model.K2Entity;
import com.k2.common.model.K2Field;
import com.k2.common.model.K2Interface;
import com.k2.common.model.aModel.AK2Class;
import com.k2.common.reflector.K2Reflector;
import com.k2.common.types.ComponentType;
import com.k2.core.assemblies.K2ClassAssembly;
import com.k2.core.source.ComponentGenerator;



public class ComponentGeneratorTests {
	
	private static final Logger logger = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());
	
	@Test
	public void createComponentGeneratorTest() throws IOException {
		
		File repo = new File("/Users/simon/eclipse-workspace/K2Dynamic/src/main/java");
		
		JavaWidgetFactory javaFactory = JavaWidgetFactory.create("com.k2.core.widgets.java");
		
		ComponentGenerator gen = ComponentGenerator.create(repo, javaFactory);
		
		assertNotNull(gen);
		
	}
	
	@Test
	public void generateClassTest() throws IOException {
		
		File repo = new File("/Users/simon/eclipse-workspace/K2Dynamic/src/main/java");
		
		JavaWidgetFactory javaFactory = JavaWidgetFactory.create("com.k2.core.widgets.java");
		
		ComponentGenerator gen = ComponentGenerator.create(repo, javaFactory);
		
		K2Reflector reflector = K2Reflector.create(K2CoreSequences.class);
		
		K2Class k2Cls = (K2Class) reflector.reflect(K2Class.class, K2Component.class);
		
		gen.generate(k2Cls);
		
	}
	
	@Test
	public void generateTypeTest() throws IOException {
		
		File repo = new File("/Users/simon/eclipse-workspace/K2Dynamic/src/main/java");
		
		JavaWidgetFactory javaFactory = JavaWidgetFactory.create("com.k2.core.widgets.java");
		
		ComponentGenerator gen = ComponentGenerator.create(repo, javaFactory);
		
		K2Reflector reflector = K2Reflector.create(K2CoreSequences.class);
		
		K2Class k2Cls = (K2Class) reflector.reflect(K2Class.class, K2Component.class);
		
		gen.generate(k2Cls);
		
	}
	
	@Test
	public void generateDomainTest() throws IOException {
		
		K2DomainManager domainManager = K2DomainManager.start(K2CoreDomainManager.class);
		
		assertTrue(K2CoreDomainManager.class.isAssignableFrom(domainManager.getClass()));
		
		K2CoreDomainManager coreDomainManager = (K2CoreDomainManager)domainManager;
		
		coreDomainManager.setJavaFactory(JavaWidgetFactory.create("com.k2.core.widgets.java"));
		
		coreDomainManager.generateSource("/Users/simon/eclipse-workspace/K2Dynamic/src/main/java");
		
		K2Dao<K2Component, Long> dao = coreDomainManager.getDaoFactory().getDao(K2Component.class);
		
		for (K2Component comp : dao.list()) {
			System.out.println("ID: "+comp.getId()+" Name: "+comp.getName());
		}
		
		
	}
	
	
	@Test
	public void K2InterfaceToJsonTest() throws IOException {
		
		K2DomainManager domainManager = K2DomainManager.start(K2CoreDomainManager.class);
		
		K2DaoFactory daoFactory = domainManager.getDaoFactory();
		
		K2Dao<K2Entity, Long> dao = daoFactory.getDao(K2Entity.class);
		
		K2Entity k2e = dao.fetch(Long.valueOf(4));
				
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		
//		System.out.println(gson.toJson(k2e));
		
		String discriminatorField = (k2e.getDiscriminatorField() == null)?"null":k2e.getDiscriminatorField().getAlias();
		String discriminatorValue = (k2e.getDiscriminatorValue() == null)?"null":k2e.getDiscriminatorValue().getAlias();
		String extendsClass = (k2e.getExtendsClass() == null)?"null":k2e.getExtendsClass().getName();
		
		System.out.println("ID:                 "+k2e.getId());
		System.out.println("Type:               "+k2e.getType());
		System.out.println("Name:               "+k2e.getName());
		System.out.println("Description:        "+k2e.getDescription());
		System.out.println("ClassType:          "+k2e.getClassType());
		System.out.println("DiscriminatorField: "+discriminatorField);
		System.out.println("DiscriminatorValue: "+discriminatorValue);
		System.out.println("ExtendsClass:       "+extendsClass);
		
		
	}
	
	
	
	
	
	
	
}
