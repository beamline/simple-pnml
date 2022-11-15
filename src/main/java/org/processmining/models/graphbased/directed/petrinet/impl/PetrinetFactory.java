package org.processmining.models.graphbased.directed.petrinet.impl;

import java.util.Map;

import org.processmining.models.graphbased.directed.DirectedGraphElement;
import org.processmining.models.graphbased.directed.petrinet.Petrinet;

public class PetrinetFactory {

	private PetrinetFactory() {

	}

	public static Petrinet newPetrinet(String label) {
		return new PetrinetImpl(label);
	}

	public static Petrinet clonePetrinet(Petrinet net) {
		PetrinetImpl newNet = new PetrinetImpl(net.getLabel());
		newNet.cloneFrom(net);
		return newNet;
	}

	public static Petrinet clonePetrinet(Petrinet net, Map<DirectedGraphElement, DirectedGraphElement> map) {
		PetrinetImpl newNet = new PetrinetImpl(net.getLabel());
		map.putAll(newNet.cloneFrom(net));
		return newNet;
	}
}
