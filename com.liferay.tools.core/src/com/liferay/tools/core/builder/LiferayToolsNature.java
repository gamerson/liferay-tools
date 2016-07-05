package com.liferay.tools.core.builder;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.resources.ICommand;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IProjectDescription;
import org.eclipse.core.resources.IProjectNature;
import org.eclipse.core.runtime.CoreException;

public class LiferayToolsNature implements IProjectNature {

	public static final Object ID = "com.liferay.tools.core.nature";

	private IProject project;

    @Override
    public IProject getProject() {
        return project;
    }

    @Override
    public void setProject(IProject project) {
        this.project = project;
    }
    
    @Override
    public void configure() throws CoreException {
        final IProjectDescription desc = project.getDescription();
        addBuilder(desc);
    }

    @Override
    public void deconfigure() throws CoreException {
        IProjectDescription desc = project.getDescription();
        removeBuilder(desc);
    }
    
    private static void addBuilder(IProjectDescription desc) {
        ICommand[] commands = desc.getBuildSpec();
        for (ICommand command : commands) {
            if (command.getBuilderName().equals(LiferayToolsBuilder.ID))
                return;
        }

        ICommand[] nu = new ICommand[commands.length + 1];
        System.arraycopy(commands, 0, nu, 0, commands.length);

        ICommand command = desc.newCommand();
        command.setBuilderName(LiferayToolsBuilder.ID);
        nu[commands.length] = command;
        desc.setBuildSpec(nu);
    }

    private static void removeBuilder(IProjectDescription desc) {
        ICommand[] commands = desc.getBuildSpec();
        List<ICommand> nu = new ArrayList<>();
        for (ICommand command : commands) {
            if (!command.getBuilderName().equals(LiferayToolsBuilder.ID)) {
                nu.add(command);
            }
        }
        desc.setBuildSpec(nu.toArray(new ICommand[0]));
    }

}
