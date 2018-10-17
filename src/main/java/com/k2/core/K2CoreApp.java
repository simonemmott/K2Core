package com.k2.core;

import com.k2.app.K2;
import com.k2.app.K2Config;
import com.k2.common.annotation.K2Application;
import com.k2.common.annotation.Version;

@K2Application(
		name = "K2 Core Application",
		description = "This application provides a web development environment to define and build K2 web applications",
		domainManagers = {
				K2CoreDomainManager.class
		},
		version = @Version(major = 0, minor = 0, point = 1)
		)
public class K2CoreApp {}
