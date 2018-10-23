package com.k2.core;

import java.io.File;
import java.io.IOException;
import java.lang.invoke.MethodHandles;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.k2.EntityMap.EntitiesMap;
import com.k2.JavaAssembly.JavaWidgetFactory;
import com.k2.common.annotation.MetaDomain;
import com.k2.common.dao.K2Dao;
import com.k2.common.dao.K2DaoFactory;
import com.k2.common.domain.AK2DomainManager;
import com.k2.common.domain.K2DomainManager;
import com.k2.common.model.K2Class;
import com.k2.common.model.K2Component;
import com.k2.common.model.K2Domain;
import com.k2.common.model.K2Entity;
import com.k2.common.reflector.K2Reflector;
import com.k2.core.source.ComponentGenerator;

@MetaDomain(
		name="K2 Core", 
		description="This is the K2 core development domain",
		sequencesClass=K2CoreSequences.class,
		packages= {
				"com.k2.common.model",
				"com.k2.core.model",
				"com.k2.common.types",
				"com.k2.core.types"
		})
public class K2CoreDomainManager extends AK2DomainManager implements K2DomainManager {

	private static final Logger logger = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

	public K2CoreDomainManager(K2Domain k2Domain, K2DaoFactory daoFactory) {
		super(k2Domain, daoFactory);
	}

	public void generateSource(String repoPath) throws IOException {

		File repo = new File("/Users/simon/eclipse-workspace/K2Dynamic/src/main/java");
				
		ComponentGenerator gen = ComponentGenerator.create(repo, getJavaFactory());
				
		for (K2Component k2Comp : this.getDaoFactory().getDao(K2Component.class).list()) {
			
			gen.generate(k2Comp);
		}
		
		gen.generate(this.getDomain());
		
	}

	private JavaWidgetFactory javaFactory;
	public void setJavaFactory(JavaWidgetFactory javaFactory) {
		this.javaFactory = javaFactory;
	}
	public JavaWidgetFactory getJavaFactory() { return javaFactory; }

}
