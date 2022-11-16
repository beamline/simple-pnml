package org.processmining.plugins.pnml.exporting;

import java.io.File;
import java.io.IOException;

import org.processmining.models.graphbased.directed.opennet.OpenNet;
import org.processmining.models.graphbased.directed.petrinet.Petrinet;
import org.processmining.models.graphbased.directed.petrinet.configurable.impl.ConfigurableResetInhibitorNet;
import org.processmining.models.semantics.petrinet.Marking;
import org.processmining.plugins.pnml.base.Pnml;

public class PnmlExportNetToPNML extends PnmlExportNet {

	public void exportPetriNetToPNMLFile(Petrinet net, Marking marking, File file) throws IOException {
		exportPetriNetToPNMLOrEPNMLFile(net, marking, file, Pnml.PnmlType.PNML);
	}

	public void exportPetriNetToPNMLFile(OpenNet openNet, Marking marking, File file) throws IOException {
		exportPetriNetToPNMLOrEPNMLFile(openNet, marking, file, Pnml.PnmlType.PNML);
	}

	public void exportPetriNetToPNMLFile(ConfigurableResetInhibitorNet configurableNet, Marking marking, File file) throws IOException {
		exportPetriNetToPNMLOrEPNMLFile(configurableNet, marking, file, Pnml.PnmlType.PNML);
	}
}
