package org.processmining.plugins.pnml.importing;

import java.io.InputStream;

import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;

import org.processmining.models.graphbased.directed.petrinet.PetrinetGraph;
import org.processmining.models.graphbased.directed.petrinet.impl.PetrinetFactory;
import org.processmining.plugins.pnml.base.Pnml;

public class PnmlImportRNet {

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
		 * Reset net.
		 */
		PetrinetGraph net = PetrinetFactory.newResetNet(pnml.getLabel() + " (imported from file)");

		return utils.connectNet(pnml, net);
	}
}
