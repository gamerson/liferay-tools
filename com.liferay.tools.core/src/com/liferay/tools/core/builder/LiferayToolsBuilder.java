package com.liferay.tools.core.builder;

import java.util.List;
import java.util.Map;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResourceDelta;
import org.eclipse.core.resources.IncrementalProjectBuilder;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;

import com.liferay.source.formatter.SourceFormatter;
import com.liferay.source.formatter.SourceFormatterArgs;

@SuppressWarnings("restriction")
public class LiferayToolsBuilder extends IncrementalProjectBuilder {

	public final static String ID = "com.liferay.tools.core.builder";

	@Override
	protected IProject[] build(int kind, Map<String, String> args, IProgressMonitor monitor) throws CoreException {
		final IProject project = getProject();

		final boolean fullBuild = kind == FULL_BUILD || kind == CLEAN_BUILD;

		if (fullBuild) {
			format(getProject().getLocation());
		} else {
			final IResourceDelta delta = getDelta(project);

		}

		return null;
	}

	private void format(IPath location) {

		new SourceFormatterArgs();

		SourceFormatterArgs sourceFormatterArgs = new SourceFormatterArgs();

		String baseDirName = location.toOSString();

		sourceFormatterArgs.setAutoFix(false);
		sourceFormatterArgs.setBaseDirName(baseDirName);
		sourceFormatterArgs.setPrintErrors(true);
		sourceFormatterArgs.setThrowException(false);
		sourceFormatterArgs.setUseProperties(false);

		SourceFormatter sourceFormatter = new SourceFormatter(sourceFormatterArgs);

		try {
			sourceFormatter.format();
			List<String> errors = sourceFormatter.getErrorMessages();
			System.out.println(errors);
		} catch (Exception e) {
			System.out.println(e);
		}

	}

}
