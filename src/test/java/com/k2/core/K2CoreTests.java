package com.k2.core;

import static org.junit.Assert.*;

import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.lang.invoke.MethodHandles;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.k2.EntityMap.EntitiesMap;
import com.k2.JavaAssembly.JavaWidgetFactory;
import com.k2.Util.ObjectUtil;
import com.k2.Util.classes.ClassUtil;
import com.k2.common.reflector.K2Reflector;
import com.k2.core.assemblies.K2ClassAssembly;
import com.k2.core.model.K2Class;
import com.k2.core.model.K2Component;
import com.k2.core.model.K2Field;
import com.k2.core.model.aModel.AK2Class;



public class K2CoreTests {
	
	private static final Logger logger = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());
	
	@Test
	public void componentWriterAssemblyTest() throws IOException {
		
		K2Reflector reflector = K2Reflector.create(K2CoreSequences.class);
		
		K2Class k2Component = (K2Class) reflector.reflect(K2Component.class, K2Component.class);
		
		JavaWidgetFactory javaWidgetFactory = JavaWidgetFactory.create("com.k2.core.widgets.java");
		
		K2ClassAssembly classAssembly = K2ClassAssembly.create(javaWidgetFactory);
				
		Writer sw = classAssembly.output(k2Component.getName(), k2Component, new StringWriter());
				
		System.out.println(sw.toString());
		
	}
	
	@Test
	public void classWriterAssemblyTest() throws IOException {
		
		K2Reflector reflector = K2Reflector.create(K2CoreSequences.class);
		
		K2Class k2Class = (K2Class) reflector.reflect(K2Class.class, K2Component.class);
		
		JavaWidgetFactory javaWidgetFactory = JavaWidgetFactory.create("com.k2.core.widgets.java");
		
		K2ClassAssembly classAssembly = K2ClassAssembly.create(javaWidgetFactory);
				
		Writer sw = classAssembly.output(k2Class.getName(), k2Class, new StringWriter());
				
		System.out.println(sw.toString());
		
	}
	
	@Test
	public void fieldWriterAssemblyTest() throws IOException {
		
		K2Reflector reflector = K2Reflector.create(K2CoreSequences.class);
		
		K2Class k2Field = (K2Class) reflector.reflect(K2Field.class, K2Component.class);
		
		JavaWidgetFactory javaWidgetFactory = JavaWidgetFactory.create("com.k2.core.widgets.java");
		
		K2ClassAssembly classAssembly = K2ClassAssembly.create(javaWidgetFactory);
				
		Writer sw = classAssembly.output(k2Field.getName(), k2Field, new StringWriter());
				
		System.out.println(sw.toString());
		
	}
/*	
	@Test
	public void reflectionLoopTest() throws IOException {
		
		K2Reflector reflector = K2Reflector.create(K2CoreSequences.class); // Done
		
		K2Class k2Class = (K2Class) reflector.reflect(K2Class.class, K2Component.class); // Done
		
		JavaWidgetFactory javaWidgetFactory = JavaWidgetFactory.create("com.k2.core.widgets.java"); // Done
		
		K2ClassAssembly classAssembly = K2ClassAssembly.create(javaWidgetFactory); // Done
		
		StringWriter sw = new StringWriter(); // Done
		
		classAssembly.output(k2Class, sw); // Done
		
		Class<? extends AK2Class> newCls = ClassUtil.createClassFromString(AK2Class.class, "com.k2.core.model", "K2Class", sw.toString()); // Done
		
		K2Reflector reflector2 = K2Reflector.create(EntitiesMap.create()); // Done
		
		K2Class k2Class2 = (K2Class) reflector2.reflect(newCls, K2Component.class); // Done
		
		assertTrue(ObjectUtil.equivalent(k2Class2, k2Class));  // Done

	}
*/
	
	
	
	
	
	
	
	
}
