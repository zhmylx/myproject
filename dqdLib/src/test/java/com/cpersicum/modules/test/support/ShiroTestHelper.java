package com.cpersicum.modules.test.support;

import org.apache.shiro.subject.Subject;
import org.apache.shiro.subject.support.SubjectThreadState;
import org.apache.shiro.util.ThreadState;
import org.mockito.Mockito;
import org.mockito.stubbing.OngoingStubbing;

public class ShiroTestHelper {
	private static ThreadState threadState;

	public static void bindSubject(Subject subject) {
		clearSubject();
		threadState = new SubjectThreadState(subject);
		threadState.bind();
	}

	public static void mockSubject(Object principal) {
		Subject subject = (Subject) Mockito.mock(Subject.class);
		Mockito.when(Boolean.valueOf(subject.isAuthenticated())).thenReturn(
				Boolean.valueOf(true));
		Mockito.when(subject.getPrincipal()).thenReturn(principal);

		bindSubject(subject);
	}

	public static void clearSubject() {
		if (threadState != null) {
			threadState.clear();
			threadState = null;
		}
	}
}
