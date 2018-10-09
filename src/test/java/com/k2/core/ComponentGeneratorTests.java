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

import com.k2.EntityMap.EntitiesMap;
import com.k2.JavaAssembly.JavaWidgetFactory;
import com.k2.Util.ObjectUtil;
import com.k2.Util.classes.ClassUtil;
import com.k2.common.domain.K2DomainManager;
import com.k2.common.reflector.K2Reflector;
import com.k2.core.assemblies.K2ClassAssembly;
import com.k2.core.model.K2Class;
import com.k2.core.model.K2Component;
import com.k2.core.model.K2Field;
import com.k2.core.model.aModel.AK2Class;
import com.k2.core.source.ComponentGenerator;
import com.k2.core.types.ComponentType;



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
	public void generateComponentTest() throws IOException {
		
		File repo = new File("/Users/simon/eclipse-workspace/K2Dynamic/src/main/java");
		
		JavaWidgetFactory javaFactory = JavaWidgetFactory.create("com.k2.core.widgets.java");
		
		ComponentGenerator gen = ComponentGenerator.create(repo, javaFactory);
		
		K2Reflector reflector = K2Reflector.create(K2CoreSequences.class, new EntitiesMap()).scan("com.k2.common.reflector");
		
		K2Class k2Cls = (K2Class) reflector.reflect(K2Class.class, K2Component.class);
		
		gen.generate(k2Cls);
		
	}
	
	@Test
	public void generateDomainTest() throws IOException {
		
		K2DomainManager domainManager = K2DomainManager.reflect("com.k2.core");
		
		assertTrue(K2CoreDomainManager.class.isAssignableFrom(domainManager.getClass()));
		
		K2CoreDomainManager coreDomainManager = (K2CoreDomainManager)domainManager;
		
		coreDomainManager.setJavaFactory(JavaWidgetFactory.create("com.k2.core.widgets.java"));
		
		coreDomainManager.generateSource("/Users/simon/eclipse-workspace/K2Dynamic/src/main/java");
		
		
	}
	
	
	
	
	
	
	
}
