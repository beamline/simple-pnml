package org.processmining.plugins.pnml.importing;

import java.io.InputStream;

import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;

import org.processmining.models.graphbased.directed.opennet.OpenNet;
import org.processmining.plugins.pnml.base.Pnml;

public class PnmlImportOpenNet {

	public FileFilter getFileFilter() {
		return new FileNameExtensionFilter("PNML files", "pnml");
	}

	public static Object[] importFromStream(InputStream input)
			throws Exception {
		PnmlImportUtils utils = new PnmlImportUtils();
		Pnml pnml = utils.importPnmlFromStream(input);
		if (pnml == null) {
			/*
			 * No PNML found in file. Fail.
			 */
			return null;
		}
		/*
		 * PNML file has been imported. Now we need to convert the contents to a
		 * regular Petri net.
		 */
		OpenNet openNet = new OpenNet(pnml.getLabel() + " (imported from file)");

		return utils.connectOpenNet(pnml, openNet);
	}
}
