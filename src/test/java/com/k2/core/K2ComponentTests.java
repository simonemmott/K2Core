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
import com.k2.common.reflector.K2Reflector;
import com.k2.core.assemblies.K2ClassAssembly;
import com.k2.core.model.K2Class;
import com.k2.core.model.K2Component;
import com.k2.core.model.K2Field;
import com.k2.core.model.aModel.AK2Class;
import com.k2.core.types.ComponentType;



public class K2ComponentTests {
	
	private static final Logger logger = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());
	
	@Test
	public void getSourceFileTest() throws IOException {
		
		K2Component comp = new K2Component(Long.valueOf(1), ComponentType.CLASS);
		comp.setName("com.k2.core.K2Class");
		
		File repo = new File("");
		
		File sourceFile = comp.getSourceFile(repo);
		
		assertNotNull(sourceFile);
		assertEquals(repo.getAbsolutePath()+File.separator+"com"+File.separator+"k2"+File.separator+"core"+File.separator+"K2Class.java", sourceFile.getAbsolutePath());
		
		
	}
	
	@Test
	public void getPackagePathTest() throws IOException {
		
		K2Component comp = new K2Component(Long.valueOf(1), ComponentType.CLASS);
		comp.setName("com.k2.core.K2Class");
		
		Path packagePath = comp.getPackagePath();
		
		assertNotNull(packagePath);
		assertEquals("com"+File.separator+"k2"+File.separator+"core", packagePath.toString());
		
		
	}
	
	@Test
	public void getSourceFileNameTest() throws IOException {
		
		K2Component comp = new K2Component(Long.valueOf(1), ComponentType.CLASS);
		comp.setName("com.k2.core.K2Class");
		
		assertEquals("K2Class.java", comp.getSourceFileName());
		
		
	}
	
	
	
	
	
	
	
	
}
