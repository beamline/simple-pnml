package org.processmining.plugins.pnml.exporting;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;

import org.processmining.framework.connections.ConnectionCannotBeObtained;
import org.processmining.framework.plugin.PluginContext;
import org.processmining.models.connections.GraphLayoutConnection;
import org.processmining.models.connections.petrinets.behavioral.FinalMarkingConnection;
import org.processmining.models.connections.petrinets.behavioral.InitialMarkingConnection;
import org.processmining.models.graphbased.directed.opennet.OpenNet;
import org.processmining.models.graphbased.directed.petrinet.InhibitorNet;
import org.processmining.models.graphbased.directed.petrinet.Petrinet;
import org.processmining.models.graphbased.directed.petrinet.PetrinetGraph;
import org.processmining.models.graphbased.directed.petrinet.ResetInhibitorNet;
import org.processmining.models.graphbased.directed.petrinet.ResetNet;
import org.processmining.models.graphbased.directed.petrinet.configurable.impl.ConfigurableResetInhibitorNet;
import org.processmining.models.semantics.petrinet.Marking;
import org.processmining.plugins.pnml.base.FullPnmlElementFactory;
import org.processmining.plugins.pnml.base.Pnml;
import org.processmining.plugins.pnml.base.Pnml.PnmlType;

public class PnmlExportNet {

	public String exportPetriNetToPNMLOrEPNMLString(Petrinet net, Marking marking, Pnml.PnmlType type,
			boolean xmlTag) {
//		Marking marking;
//		try {
//			marking = context.tryToFindOrConstructFirstObject(Marking.class, InitialMarkingConnection.class,
//					InitialMarkingConnection.MARKING, net);
//		} catch (ConnectionCannotBeObtained e) {
//			// use empty marking\
//			marking = new Marking();
//		}
		if (marking == null) {
			marking = new Marking();
		}
		Collection<Marking> finalMarkings = new HashSet<Marking>();
//		try {
//			Collection<FinalMarkingConnection> connections = context.getConnectionManager().getConnections(
//					FinalMarkingConnection.class, context, net);
//			for (FinalMarkingConnection connection : connections) {
//				finalMarkings.add((Marking) connection.getObjectWithRole(FinalMarkingConnection.MARKING));
//			}
//		} catch (ConnectionCannotBeObtained e) {
//		}

//		GraphLayoutConnection layout;
//		try {
//			layout = context.getConnectionManager().getFirstConnection(GraphLayoutConnection.class, context, net);
//		} catch (ConnectionCannotBeObtained e) {
//			layout = new GraphLayoutConnection(net);
//		}
		GraphLayoutConnection layout = new GraphLayoutConnection(net);
		
		HashMap<PetrinetGraph, Marking> markedNets = new HashMap<PetrinetGraph, Marking>();
		HashMap<PetrinetGraph, Collection<Marking>> finalMarkedNets = new HashMap<PetrinetGraph, Collection<Marking>>();
		markedNets.put(net, marking);
		finalMarkedNets.put(net, finalMarkings);

		Pnml pnml = new Pnml();
		FullPnmlElementFactory factory = new FullPnmlElementFactory();
		synchronized (factory) {
			pnml.setFactory(factory);
			pnml = pnml.convertFromNet(markedNets, finalMarkedNets, layout);
		}
		pnml.setType(type);
//		updateName(context, pnml, net);
		return xmlTag ? "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" + pnml.exportElement(pnml) : pnml
				.exportElement(pnml);
	}

	protected void exportPetriNetToPNMLOrEPNMLFile(Petrinet net, Marking marking, File file, Pnml.PnmlType type)
			throws IOException {
//		Marking marking;
//		try {
//			marking = context.tryToFindOrConstructFirstObject(Marking.class, InitialMarkingConnection.class,
//					InitialMarkingConnection.MARKING, net);
//		} catch (ConnectionCannotBeObtained e) {
//			// use empty marking\
//			marking = new Marking();
//		}
		if (marking == null) {
			marking = new Marking();
		}
		Collection<Marking> finalMarkings = new HashSet<Marking>();
//		try {
//			Collection<FinalMarkingConnection> connections = context.getConnectionManager().getConnections(
//					FinalMarkingConnection.class, context, net);
//			for (FinalMarkingConnection connection : connections) {
//				finalMarkings.add((Marking) connection.getObjectWithRole(FinalMarkingConnection.MARKING));
//			}
//		} catch (ConnectionCannotBeObtained e) {
//		}

//		GraphLayoutConnection layout;
//		try {
//			layout = context.getConnectionManager().getFirstConnection(GraphLayoutConnection.class, context, net);
//		} catch (ConnectionCannotBeObtained e) {
//			layout = new GraphLayoutConnection(net);
//		}
		GraphLayoutConnection layout = new GraphLayoutConnection(net);
		HashMap<PetrinetGraph, Marking> markedNets = new HashMap<PetrinetGraph, Marking>();
		HashMap<PetrinetGraph, Collection<Marking>> finalMarkedNets = new HashMap<PetrinetGraph, Collection<Marking>>();
		markedNets.put(net, marking);
		finalMarkedNets.put(net, finalMarkings);

		Pnml pnml = new Pnml();
		FullPnmlElementFactory factory = new FullPnmlElementFactory();
		synchronized (factory) {
			pnml.setFactory(factory);
			pnml = pnml.convertFromNet(markedNets, finalMarkedNets, layout);
		}
		pnml.setType(type);
//		updateName(context, pnml, net);
		String text = "<?xml version=\"1.0\" encoding=\"ISO-8859-1\"?>\n" + pnml.exportElement(pnml);

		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file), StandardCharsets.UTF_8));
		bw.write(text);
		bw.close();
	}

	protected void exportPetriNetToPNMLOrEPNMLFile(OpenNet openNet, Marking marking, File file, Pnml.PnmlType type)
			throws IOException {
//		Marking marking;
//		try {
//			marking = context.tryToFindOrConstructFirstObject(Marking.class, InitialMarkingConnection.class,
//					InitialMarkingConnection.MARKING, openNet);
//		} catch (ConnectionCannotBeObtained e) {
//			// use empty marking\
//			marking = new Marking();
//		}
		if (marking == null) {
			marking = new Marking();
		}
//		GraphLayoutConnection layout;
//		try {
//			layout = context.getConnectionManager().getFirstConnection(GraphLayoutConnection.class, context, openNet);
//		} catch (ConnectionCannotBeObtained e) {
//			layout = new GraphLayoutConnection(openNet);
//		}
		GraphLayoutConnection layout = new GraphLayoutConnection(openNet);
		Pnml pnml = new Pnml();
		FullPnmlElementFactory factory = new FullPnmlElementFactory();
		synchronized (factory) {
			pnml.setFactory(factory);
			pnml = pnml.convertFromNet(openNet, marking, new HashSet<Marking>(), layout);
		}
		pnml.setType(type);
		String text = "<?xml version=\"1.0\" encoding=\"ISO-8859-1\"?>\n" + pnml.exportElement(pnml);

		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file), StandardCharsets.UTF_8));
		bw.write(text);
		bw.close();
	}

	protected void exportPetriNetToPNMLOrEPNMLFile(ResetNet resetNet, Marking marking, File file, PnmlType type)
			throws IOException {
//		Marking marking;
//		try {
//			marking = context.tryToFindOrConstructFirstObject(Marking.class, InitialMarkingConnection.class,
//					InitialMarkingConnection.MARKING, resetNet);
//		} catch (ConnectionCannotBeObtained e) {
//			// use empty marking\
//			marking = new Marking();
//		}
		if (marking == null) {
			marking = new Marking();
		}
		Collection<Marking> finalMarkings = new HashSet<Marking>();
//		try {
//			Collection<FinalMarkingConnection> connections = context.getConnectionManager().getConnections(
//					FinalMarkingConnection.class, context, resetNet);
//			for (FinalMarkingConnection connection : connections) {
//				finalMarkings.add((Marking) connection.getObjectWithRole(FinalMarkingConnection.MARKING));
//			}
//		} catch (ConnectionCannotBeObtained e) {
//		}

//		GraphLayoutConnection layout;
//		try {
//			layout = context.getConnectionManager().getFirstConnection(GraphLayoutConnection.class, context, resetNet);
//		} catch (ConnectionCannotBeObtained e) {
//			layout = new GraphLayoutConnection(resetNet);
//		}
		GraphLayoutConnection layout = new GraphLayoutConnection(resetNet);
		Pnml pnml = new Pnml();
		FullPnmlElementFactory factory = new FullPnmlElementFactory();
		synchronized (factory) {
			pnml.setFactory(factory);
			pnml = pnml.convertFromNet(resetNet, marking, finalMarkings, layout);
		}
		pnml.setType(type);
		String text = "<?xml version=\"1.0\" encoding=\"ISO-8859-1\"?>\n" + pnml.exportElement(pnml);

		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file), StandardCharsets.UTF_8));
		bw.write(text);
		bw.close();

	}

	protected void exportPetriNetToPNMLOrEPNMLFile(
			ConfigurableResetInhibitorNet resetInhibitorNet, Marking marking, File file, PnmlType type) throws IOException {
//		Marking marking;
//		try {
//			marking = context.tryToFindOrConstructFirstObject(Marking.class, InitialMarkingConnection.class,
//					InitialMarkingConnection.MARKING, resetInhibitorNet);
//		} catch (ConnectionCannotBeObtained e) {
//			// use empty marking\
//			marking = new Marking();
//		}
		if (marking == null) {
			marking = new Marking();
		}
		Collection<Marking> finalMarkings = new HashSet<Marking>();
//		try {
//			Collection<FinalMarkingConnection> connections = context.getConnectionManager().getConnections(
//					FinalMarkingConnection.class, context, resetInhibitorNet);
//			for (FinalMarkingConnection connection : connections) {
//				finalMarkings.add((Marking) connection.getObjectWithRole(FinalMarkingConnection.MARKING));
//			}
//		} catch (ConnectionCannotBeObtained e) {
//		}

//		GraphLayoutConnection layout;
//		try {
//			layout = context.getConnectionManager().getFirstConnection(GraphLayoutConnection.class, context,
//					resetInhibitorNet);
//		} catch (ConnectionCannotBeObtained e) {
//			layout = new GraphLayoutConnection(resetInhibitorNet);
//		}
		GraphLayoutConnection layout = new GraphLayoutConnection(resetInhibitorNet);
		Pnml pnml = new Pnml();
		FullPnmlElementFactory factory = new FullPnmlElementFactory();
		synchronized (factory) {
			pnml.setFactory(factory);
			pnml = pnml.convertFromNet(resetInhibitorNet, marking, finalMarkings, layout);
		}
		pnml.setType(type);
		String text = "<?xml version=\"1.0\" encoding=\"ISO-8859-1\"?>\n" + pnml.exportElement(pnml);

		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file), StandardCharsets.UTF_8));
		bw.write(text);
		bw.close();

	}

	protected void exportPetriNetToPNMLOrEPNMLFile(ResetInhibitorNet resetInhibitorNet,  Marking marking,
			File file, PnmlType type) throws IOException {
//		Marking marking;
//		try {
//			marking = context.tryToFindOrConstructFirstObject(Marking.class, InitialMarkingConnection.class,
//					InitialMarkingConnection.MARKING, resetInhibitorNet);
//		} catch (ConnectionCannotBeObtained e) {
//			// use empty marking\
//			marking = new Marking();
//		}
		if (marking == null) {
			marking = new Marking();
		}
		Collection<Marking> finalMarkings = new HashSet<Marking>();
//		try {
//			Collection<FinalMarkingConnection> connections = context.getConnectionManager().getConnections(
//					FinalMarkingConnection.class, context, resetInhibitorNet);
//			for (FinalMarkingConnection connection : connections) {
//				finalMarkings.add((Marking) connection.getObjectWithRole(FinalMarkingConnection.MARKING));
//			}
//		} catch (ConnectionCannotBeObtained e) {
//		}

//		GraphLayoutConnection layout;
//		try {
//			layout = context.getConnectionManager().getFirstConnection(GraphLayoutConnection.class, context,
//					resetInhibitorNet);
//		} catch (ConnectionCannotBeObtained e) {
//			layout = new GraphLayoutConnection(resetInhibitorNet);
//		}
		GraphLayoutConnection layout = new GraphLayoutConnection(resetInhibitorNet);
		Pnml pnml = new Pnml();
		FullPnmlElementFactory factory = new FullPnmlElementFactory();
		synchronized (factory) {
			pnml.setFactory(factory);
			pnml = pnml.convertFromNet(resetInhibitorNet, marking, finalMarkings, layout);
		}
		pnml.setType(type);
		String text = "<?xml version=\"1.0\" encoding=\"ISO-8859-1\"?>\n" + pnml.exportElement(pnml);

		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file), StandardCharsets.UTF_8));
		bw.write(text);
		bw.close();

	}

	protected void exportPetriNetToPNMLOrEPNMLFile(InhibitorNet inhibitorNet,  Marking marking, File file,
			PnmlType type) throws IOException {
//		Marking marking;
//		try {
//			marking = context.tryToFindOrConstructFirstObject(Marking.class, InitialMarkingConnection.class,
//					InitialMarkingConnection.MARKING, inhibitorNet);
//		} catch (ConnectionCannotBeObtained e) {
//			// use empty marking\
//			marking = new Marking();
//		}
		if (marking == null) {
			marking = new Marking();
		}
		Collection<Marking> finalMarkings = new HashSet<Marking>();
//		try {
//			Collection<FinalMarkingConnection> connections = context.getConnectionManager().getConnections(
//					FinalMarkingConnection.class, context, inhibitorNet);
//			for (FinalMarkingConnection connection : connections) {
//				finalMarkings.add((Marking) connection.getObjectWithRole(FinalMarkingConnection.MARKING));
//			}
//		} catch (ConnectionCannotBeObtained e) {
//		}

//		GraphLayoutConnection layout;
//		try {
//			layout = context.getConnectionManager().getFirstConnection(GraphLayoutConnection.class, context,
//					inhibitorNet);
//		} catch (ConnectionCannotBeObtained e) {
//			layout = new GraphLayoutConnection(inhibitorNet);
//		}
		GraphLayoutConnection layout = new GraphLayoutConnection(inhibitorNet);
		Pnml pnml = new Pnml();
		FullPnmlElementFactory factory = new FullPnmlElementFactory();
		synchronized (factory) {
			pnml.setFactory(factory);
			pnml = pnml.convertFromNet(inhibitorNet, marking, finalMarkings, layout);
		}
		pnml.setType(type);
		String text = "<?xml version=\"1.0\" encoding=\"ISO-8859-1\"?>\n" + pnml.exportElement(pnml);

		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file), StandardCharsets.UTF_8));
		bw.write(text);
		bw.close();

	}

//	private static void updateName(PluginContext context, Pnml pnml, PetrinetGraph net) {
//		String name = ProvidedObjectHelper.getProvidedObjectLabel(context, net);
//		if (name != null) {
//			pnml.setName(name);
//		}
//	}
}
