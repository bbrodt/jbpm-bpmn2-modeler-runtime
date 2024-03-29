package org.jboss.bpmn2.modeler.runtime.jbpm5.wizards;

import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.INewWizard;
import org.eclipse.ui.IWorkbench;
import org.eclipse.bpmn2.modeler.core.preferences.Bpmn2Preferences;
import org.eclipse.bpmn2.modeler.core.runtime.TargetRuntime;
import org.eclipse.core.runtime.*;
import org.eclipse.jface.operation.*;
import java.lang.reflect.InvocationTargetException;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.core.resources.*;
import org.eclipse.core.runtime.CoreException;
import java.io.*;
import org.eclipse.ui.*;
import org.eclipse.ui.ide.IDE;
import org.jboss.bpmn2.modeler.runtime.jbpm5.JBPM5RuntimeExtension;
import org.osgi.service.prefs.BackingStoreException;

/**
 * This is a sample new wizard. Its role is to create a new file 
 * resource in the provided container. If the container resource
 * (a folder or a project) is selected in the workspace 
 * when the wizard is opened, it will accept it as the target
 * container. The wizard creates one file with the extension
 * "bpmn". If a sample multi-page editor (also available
 * as a template) is registered for the same extension, it will
 * be able to open it.
 */

public class NewJbpmProcessWizard extends Wizard implements INewWizard {
	private NewJbpmProcessWizardPage1 page;
	private ISelection selection;
	private String processName;
	private String processId;
	private String packageName;
	private boolean isSetJbpmRuntime;

	/**
	 * Constructor for NewJbpmProcessWizard.
	 */
	public NewJbpmProcessWizard() {
		super();
		setNeedsProgressMonitor(true);
	}
	
	/**
	 * Adding the page to the wizard.
	 */

	public void addPages() {
		page = new NewJbpmProcessWizardPage1(selection);
		addPage(page);
	}

	/**
	 * This method is called when 'Finish' button is pressed in
	 * the wizard. We will create an operation and run it
	 * using wizard as execution context.
	 */
	public boolean performFinish() {
		final String containerName = page.getContainerName();
		final String fileName = page.getFileName();
		processName = page.getProcessName();
		processId = page.getProcessId();
		packageName = page.getPackageName();
		isSetJbpmRuntime = page.isSetJbpmRuntime();
		
		IRunnableWithProgress op = new IRunnableWithProgress() {
			public void run(IProgressMonitor monitor) throws InvocationTargetException {
				try {
					doFinish(containerName, fileName, monitor);
				} catch (CoreException e) {
					throw new InvocationTargetException(e);
				} finally {
					monitor.done();
				}
			}
		};
		try {
			getContainer().run(true, false, op);
		} catch (InterruptedException e) {
			return false;
		} catch (InvocationTargetException e) {
			Throwable realException = e.getTargetException();
			MessageDialog.openError(getShell(), "Error", realException.getMessage());
			return false;
		}
		return true;
	}
	
	/**
	 * The worker method. It will find the container, create the
	 * file if missing or just replace its contents, and open
	 * the editor on the newly created file.
	 */

	private void doFinish(
		String containerName,
		String fileName,
		IProgressMonitor monitor)
		throws CoreException {
		// create a sample file
		monitor.beginTask("Creating " + fileName, 2);
		IWorkspaceRoot root = ResourcesPlugin.getWorkspace().getRoot();
		IResource resource = root.findMember(new Path(containerName));
		if (!resource.exists() || !(resource instanceof IContainer)) {
			throwCoreException("Container \"" + containerName + "\" does not exist.");
		}
		IContainer container = (IContainer) resource;
		final IFile file = container.getFile(new Path(fileName));
		try {
			InputStream stream = openContentStream();
			if (file.exists()) {
				file.setContents(stream, true, true, monitor);
			} else {
				file.create(stream, true, monitor);
			}
			stream.close();
		} catch (IOException e) {
		}
		
		if (isSetJbpmRuntime) {
			monitor.setTaskName("Configuring project for jBPM Runtime...");
			Bpmn2Preferences prefs = Bpmn2Preferences.getInstance((IProject)container);
			TargetRuntime rt = TargetRuntime.getRuntime(JBPM5RuntimeExtension.JBPM5_RUNTIME_ID);
			prefs.setRuntime(rt);
			try {
				prefs.save();
			} catch (BackingStoreException e) {
				throwCoreException(e.getMessage());
			}
		}
		monitor.worked(1);
		monitor.setTaskName("Opening file for editing...");
		getShell().getDisplay().asyncExec(new Runnable() {
			public void run() {
				IWorkbenchPage page =
					PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage();
				try {
					IDE.openEditor(page, file, true);
				} catch (PartInitException e) {
				}
			}
		});
		monitor.worked(1);
	}
	
	/**
	 * We will initialize file contents with a sample text.
	 */

	private InputStream openContentStream() {
		String contents =
			"<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n"+ 
			"<definitions id=\"Definition\"\n"+
			"             targetNamespace=\"http://www.jboss.org/drools\"\n"+
			"             typeLanguage=\"http://www.java.com/javaTypes\"\n"+
			"             expressionLanguage=\"http://www.mvel.org/2.0\"\n"+
			"             xmlns=\"http://www.omg.org/spec/BPMN/20100524/MODEL\"\n"+
			"             xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"\n"+
			"             xsi:schemaLocation=\"http://www.omg.org/spec/BPMN/20100524/MODEL BPMN20.xsd\"\n"+
			"             xmlns:bpmndi=\"http://www.omg.org/spec/BPMN/20100524/DI\"\n"+
			"             xmlns:dc=\"http://www.omg.org/spec/DD/20100524/DC\"\n"+
			"             xmlns:di=\"http://www.omg.org/spec/DD/20100524/DI\"\n"+
			"             xmlns:tns=\"http://www.jboss.org/drools\">\n"+
			"\n"+
			"  <process processType=\"Private\" isExecutable=\"true\""+
			" id=\""+processId+"\""+
			" name=\""+processName+"\""+
			" tns:packageName=\""+packageName+"\""+
			" >\n"+
			"    <startEvent id=\"StartEvent_1\" name=\"StartProcess\" />\n"+
			"  </process>\n"+
			"\n"+
			"  <bpmndi:BPMNDiagram>\n"+
			"    <bpmndi:BPMNPlane bpmnElement=\""+processId+"\" >\n"+
			"      <bpmndi:BPMNShape bpmnElement=\"StartEvent_1\" >\n"+
			"        <dc:Bounds x=\"45\" y=\"45\" />\n"+
			"      </bpmndi:BPMNShape>\n"+
			"      </bpmndi:BPMNPlane>\n"+
			"  </bpmndi:BPMNDiagram>\n"+
			"\n"+
			"</definitions>\n";
		
		return new ByteArrayInputStream(contents.getBytes());
	}

	private void throwCoreException(String message) throws CoreException {
		IStatus status =
			new Status(IStatus.ERROR, "org.jboss.bpmn2.modeler.runtime.jbpm5", IStatus.OK, message, null);
		throw new CoreException(status);
	}

	/**
	 * We will accept the selection in the workbench to see if
	 * we can initialize from it.
	 * @see IWorkbenchWizard#init(IWorkbench, IStructuredSelection)
	 */
	public void init(IWorkbench workbench, IStructuredSelection selection) {
		this.selection = selection;
	}
}