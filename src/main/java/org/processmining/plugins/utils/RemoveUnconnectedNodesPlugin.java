package org.processmining.plugins.utils;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.processmining.models.graphbased.directed.DirectedGraphElement;
import org.processmining.models.graphbased.directed.petrinet.Petrinet;
import org.processmining.models.graphbased.directed.petrinet.elements.Place;
import org.processmining.models.graphbased.directed.petrinet.elements.Transition;
import org.processmining.models.graphbased.directed.petrinet.impl.PetrinetFactory;
import org.processmining.models.semantics.petrinet.Marking;

public class RemoveUnconnectedNodesPlugin {

    /**
     * Context-unaware version, does not allow us to find markings!
     * 
     * @param net
     * @return
     */
    public static Petrinet apply(Petrinet net) {
        Map<DirectedGraphElement, DirectedGraphElement> pnEMap = new HashMap<DirectedGraphElement, DirectedGraphElement>();
        Petrinet clone = PetrinetFactory.clonePetrinet(net, pnEMap);
        clone = removePlaces(clone);
        clone = removeTransitions(clone);
        return clone;
    }

    private static Petrinet removePlaces(Petrinet net) {
        Set<Place> placesToRemove = placesToRemove(net);
        for (Place place : placesToRemove) {
            net.removePlace(place);
        }
        return net;
    }

    private static Petrinet removeTransitions(Petrinet net) {
        Set<Transition> transitionsToRemove = transitionsToRemove(net);
        for (Transition tr : transitionsToRemove) {
            net.removeTransition(tr);
        }
        return net;
    }

    private static Set<Transition> transitionsToRemove(Petrinet net) {
        Set<Transition> transitionsToRemove = new HashSet<Transition>();
        for (Transition transition : net.getTransitions()) {
            if (net.getInEdges(transition).isEmpty()
                    && net.getOutEdges(transition).isEmpty()) {
                transitionsToRemove.add(transition);
            }
        }
        return transitionsToRemove;
    }

    private static Set<Place> placesToRemove(Petrinet net) {
        Set<Place> placesToRemove = new HashSet<Place>();
        for (Place place : net.getPlaces()) {
            if (net.getInEdges(place).isEmpty()
                    && net.getOutEdges(place).isEmpty()) {
                placesToRemove.add(place);
            }
        }
        return placesToRemove;
    }

    private static Marking computeNewMarking(
            Petrinet net,
            Map<DirectedGraphElement, DirectedGraphElement> pnElementsMap) {
        Marking existingMarking = null;
        Marking newMarking = null;

        if (existingMarking != null) {
            newMarking = new Marking();
            for (Place p : existingMarking) {
                Place pPrime = (Place) pnElementsMap.get(p);
                newMarking.add(pPrime, existingMarking.occurrences(p));
            }
        }
        return newMarking;
    }

}
