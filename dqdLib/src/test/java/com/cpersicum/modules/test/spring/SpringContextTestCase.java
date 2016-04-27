package com.cpersicum.modules.test.spring;

import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;

@ActiveProfiles({ "test" })
public abstract class SpringContextTestCase extends
		AbstractJUnit4SpringContextTests {
}
