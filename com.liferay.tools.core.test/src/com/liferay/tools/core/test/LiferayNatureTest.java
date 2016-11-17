package com.liferay.tools.core.test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.File;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IProjectDescription;
import org.eclipse.core.resources.IProjectNature;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.liferay.tools.core.LiferayNature;

public class LiferayNatureTest {

	private IWorkspaceRoot _root;

	@After
	public void deleteWorkspace() throws Exception {
		File workspace = new File("workspace");

		if (workspace.exists()) {
//			IO.delete(workspace);

//			assertFalse(workspace.exists());
		}
	}

	@Before
	public void checkWorkspace() throws Exception {
		_root = ResourcesPlugin.getWorkspace().getRoot();

		assertNotNull(_root);

		assertNotNull(_root.getLocation());

		assertTrue(_root.getLocation().toFile().exists());
	}

	@Test
	public void addLiferayNature() throws Exception {
		IProject fooProject = _root.getProject("foo");

		IWorkspace workspace = _root.getWorkspace();

		IProjectDescription fooDescription = workspace.newProjectDescription("foo");

		IProgressMonitor monitor = new NullProgressMonitor();

		fooProject.create(fooDescription, new NullProgressMonitor());

		fooProject.open(monitor);

		assertFalse(LiferayNature.hasNature(fooProject));

		LiferayNature.addLiferayNature(fooProject, monitor);

		assertTrue(LiferayNature.hasNature(fooProject));

		IProjectNature nature = fooProject.getNature(LiferayNature.NATURE_ID);

		nature.deconfigure();

		assertFalse(LiferayNature.hasNature(fooProject));
	}
}
