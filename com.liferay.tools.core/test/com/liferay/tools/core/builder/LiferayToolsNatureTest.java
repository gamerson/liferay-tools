package com.liferay.tools.core.builder;

import static org.junit.Assert.assertTrue;

import java.util.Arrays;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IProjectDescription;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.junit.Test;

public class LiferayToolsNatureTest {

	@Test
	public void testAddLiferayToolsNature() throws Exception {
		IWorkspace workspace = ResourcesPlugin.getWorkspace();
		IWorkspaceRoot root = workspace.getRoot();
		String projectName = "add-liferay-tools-nature-test";

		IProject project = root.getProject(projectName);
		IProjectDescription desc = workspace.newProjectDescription(projectName);
		project.create(desc, new NullProgressMonitor());
		
		LiferayToolsNature nature = new LiferayToolsNature();
		nature.setProject(project);
		nature.configure();
		
		assertTrue(Arrays.asList(project.getDescription().getNatureIds()).contains(LiferayToolsNature.ID));
	}
}
