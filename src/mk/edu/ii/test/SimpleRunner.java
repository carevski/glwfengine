package mk.edu.ii.test;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import mk.edu.ii.app.Application;
import net.kwfgrid.gworkflowdl.analysis.WorkflowAnalyzer;
import net.kwfgrid.gworkflowdl.structure.ArrayListOperationClass;
import net.kwfgrid.gworkflowdl.structure.ArrayListPlace;
import net.kwfgrid.gworkflowdl.structure.ArrayListTransition;
import net.kwfgrid.gworkflowdl.structure.ArrayListWorkflow;
import net.kwfgrid.gworkflowdl.structure.CapacityException;
import net.kwfgrid.gworkflowdl.structure.Data;
import net.kwfgrid.gworkflowdl.structure.Edge;
import net.kwfgrid.gworkflowdl.structure.JDOMData;
import net.kwfgrid.gworkflowdl.structure.JDOMOperation;
import net.kwfgrid.gworkflowdl.structure.JdomString;
import net.kwfgrid.gworkflowdl.structure.Operation;
import net.kwfgrid.gworkflowdl.structure.OperationCandidate;
import net.kwfgrid.gworkflowdl.structure.OperationClass;
import net.kwfgrid.gworkflowdl.structure.Place;
import net.kwfgrid.gworkflowdl.structure.StdEdge;
import net.kwfgrid.gworkflowdl.structure.StdOperationCandidate;
import net.kwfgrid.gworkflowdl.structure.StdToken;
import net.kwfgrid.gworkflowdl.structure.Token;
import net.kwfgrid.gworkflowdl.structure.Transition;
import net.kwfgrid.gworkflowdl.structure.Workflow;
import net.kwfgrid.gworkflowdl.structure.WorkflowFormatException;

import org.apache.log4j.Logger;

public class SimpleRunner {

	private String readFile(String fileName) {
		String res = "";
		try {
			BufferedReader inFile = new BufferedReader(new FileReader(fileName));
			String line = "";
			while ((line = inFile.readLine()) != null) {
				line = line.trim();
				res += " " + line;
			}
		} catch (IOException e) {
			System.out.println("IO Error:" + e.getMessage());
		}
		if(res.compareTo("") == 0) return null;
		return res;
	}

	public void runIt() {
		String wfString = readFile("wf_example.gwdl");
		System.out.println(wfString);
		ArrayListWorkflow wf = new ArrayListWorkflow("Test");
		if (wfString == null)
			return;
		try {
			wf.fromXML(wfString);

			System.out.println(wf.getDescription());

			String[] placesId = wf.getPlaceIDs();
			String[] transitionsId = wf.getTransitionIDs();

			// id of places
			for (int i = 0; i < placesId.length; i++)
				System.out.println(placesId[i]);

			// id of transitions
			for (int i = 0; i < transitionsId.length; i++)
				System.out.println(transitionsId[i]);

		} catch (CapacityException ce) {
			// Exception, indicating something is wrong with Place.capacity
			System.out.println("Capacity Exception");
			System.out.println(ce.getMessage());
		} catch (WorkflowFormatException wfe) {
			System.out.println("WorkflowFormatException");
			System.out.println(wfe.getMessage());
		}
		WorkflowAnalyzer wfa = new WorkflowAnalyzer(wf);
		wfa.showAnalysis();
	}

	public Workflow buildWf() {
		ArrayListWorkflow wf = new ArrayListWorkflow("Test");
		wf.setDescription("little workflow example with condition, side effect, and places related to data classes");
		
		try {
			
			Data d1 = new JDOMData();
			d1.fromXML("<value xsi:type=\"xsd:string\">15 8 0</value>");
			Token t1 = new StdToken(d1);
			Data d2 = new JDOMData();
			d2.fromXML("<value xsi:type=\"xsd:string\">15 8 2</value>");
			Token t2 = new StdToken(d2);

			Place begin = new ArrayListPlace();
			begin.setID("begin");

			begin.addToken(t1);
			begin.addToken(t2);

			Place outputData = new ArrayListPlace();
			outputData.setID("outputData");

			Place hasBeenSorted = new ArrayListPlace();
			hasBeenSorted.setID("hasBeenSorted");

			Transition tr = new ArrayListTransition();
			tr.setID("sort");
			tr.setDescription("sorts strings or numbers");
			
			Edge e1 = new StdEdge(begin,"input");
			tr.addInEdge(e1);
			
			Edge e2 = new StdEdge(outputData,"output");
			Edge e3 = new StdEdge(hasBeenSorted,"");
			tr.addOutEdge(e2);
			tr.addOutEdge(e3);
			String [] conds = {"string-length($input/gwdl:token/gwdl:data/*)&gt;0"};
			tr.setConditions(conds);
		
			Operation o = new JDOMOperation();
			OperationClass oc = new ArrayListOperationClass();
			OperationCandidate opc = new StdOperationCandidate();
			opc.setResourceName("http://fhrg.first.fraunhofer.de:8080/linuxtoolbox/services/Sort?wsdl");
			opc.setOperationName("sort");
			opc.setSelected();
			oc.addOperationCandidate(opc);
			o.set(oc);
			
			tr.setOperation(o);
			
			wf.addPlace(begin);
			wf.addPlace(outputData);
			wf.addPlace(hasBeenSorted);
			wf.addTransition(tr);
			
		} catch (CapacityException e) {
			System.out.println(e.getMessage());
		}catch (WorkflowFormatException e) {
			System.out.println(e.getMessage());
		}
		
		
		try {
			String str = JdomString.workflow2string(wf);
			System.out.println(str);
		} catch (IOException e) {
			System.out.println("IO error:" + e.getMessage());
		}
		
		WorkflowAnalyzer wfa = new WorkflowAnalyzer(wf);
		wfa.showAnalysis();
		
		return wf;
	}
	
	public static void doIT(){
		Logger log = Logger.getLogger(SimpleRunner.class);
		log.info("Info od doIT");
		log.warn("nekoja poraka");
	}
	static Logger log = Logger.getLogger(SimpleRunner.class);
	public static void main(String [] args){
		Application app = new Application();
		doIT();
	}
	
}
