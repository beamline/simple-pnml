package org.processmining.plugins.pnml.exporting;

import java.io.File;
import java.io.IOException;

import org.processmining.models.graphbased.directed.opennet.OpenNet;
import org.processmining.models.graphbased.directed.petrinet.InhibitorNet;
import org.processmining.models.graphbased.directed.petrinet.Petrinet;
import org.processmining.models.graphbased.directed.petrinet.ResetInhibitorNet;
import org.processmining.models.graphbased.directed.petrinet.ResetNet;
import org.processmining.models.graphbased.directed.petrinet.configurable.impl.ConfigurableResetInhibitorNet;
import org.processmining.models.semantics.petrinet.Marking;
import org.processmining.plugins.pnml.base.Pnml;

public class PnmlExportNetToEPNML extends PnmlExportNet {

	public void exportPetriNetToEPNMLFile(Petrinet net, Marking marking, File file) throws IOException {
		exportPetriNetToPNMLOrEPNMLFile(net, marking, file, Pnml.PnmlType.EPNML);
	}

	public void exportPetriNetToEPNMLFile(OpenNet openNet, Marking marking, File file) throws IOException {
		exportPetriNetToPNMLOrEPNMLFile(openNet, marking, file, Pnml.PnmlType.EPNML);
	}

	public void exportPetriNetToPNMLFile(ResetNet resetNet, Marking marking, File file) throws IOException {
		exportPetriNetToPNMLOrEPNMLFile(resetNet, marking, file, Pnml.PnmlType.EPNML);
	}
	
	public void exportPetriNetToPNMLFile(ConfigurableResetInhibitorNet configurableNet, Marking marking, File file) throws IOException {
		exportPetriNetToPNMLOrEPNMLFile(configurableNet, marking, file, Pnml.PnmlType.EPNML);
	}
	
	public void exportPetriNetToPNMLFile(ResetInhibitorNet resetInhibitorNet, Marking marking, File file) throws IOException {
		exportPetriNetToPNMLOrEPNMLFile(resetInhibitorNet, marking, file, Pnml.PnmlType.EPNML);
	}

	public void exportPetriNetToPNMLFile(InhibitorNet inhibitorNet, Marking marking, File file) throws IOException {
		exportPetriNetToPNMLOrEPNMLFile(inhibitorNet, marking, file, Pnml.PnmlType.EPNML);
	}
}
