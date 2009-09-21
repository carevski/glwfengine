package mk.edu.ii.engine;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.logging.Logger;

import mk.edu.ii.events.Event;
import mk.edu.ii.events.impl.EventStart;
import mk.edu.ii.listeners.Listener;
import net.kwfgrid.gworkflowdl.structure.Workflow;

public class Engine {

	private String id;
	private String trans;
	
	private Logger log = Logger.getLogger("mk.edu.ii.engine.Engine");
	
	private BlockingQueue<Event> eventQueue;
	//StateMachine stateMachine;
	
	private List<Listener> stateListeners;
	private List<Listener> fireListeners;
	private List<Listener> markingListeners;
	private List<Listener> workflowListeners;

	public Engine() {
		init();
	}

	public Engine( String id ){
		this.id = id;
	}
	
	public void init() {
		stateListeners = new ArrayList<Listener>();
		fireListeners = new ArrayList<Listener>();
		markingListeners = new ArrayList<Listener>();
		workflowListeners = new ArrayList<Listener>();
		eventQueue =  new LinkedBlockingQueue<Event>();
		
	}

	public void addStateListener(Listener s) {
		if (s != null)
			stateListeners.add(s);
	}

	public void addFireListener(Listener s) {
		if (s != null)
			fireListeners.add(s);
	}

	public void addMarkingListener(Listener s) {
		if (s != null)
			markingListeners.add(s);
	}

	public void addWorkflowListener(Listener s) {
		if (s != null)
			workflowListeners.add(s);
	}

	public void removeStateListener(Listener s) {
		if (s != null)
			stateListeners.remove(s);
	}

	public void removeFireListener(Listener s) {
		if (s != null)
			fireListeners.remove(s);
	}

	public void removeMarkingListener(Listener s) {
		if (s != null)
			markingListeners.remove(s);
	}

	public void removeWorkflowListener(Listener s) {
		if (s != null)
			workflowListeners.remove(s);
	}
	
	public String getId(){
		return id;
	}
	
	/**
	 * Starts the engine
	 */
	void start(){
		eventQueue.add(new EventStart());
		log.info("Event Start happend for engine startup.");
	}

	void run(){
		
	};



	/**
	 * Stops the engine and puts it into the Idle state
	 */
	void stop(){
		
	}

	/**
	 * Resets the engine causing the restart of the current loaded workflow
	 * with the initial marking
	 */
	void reset(){
		
	}

	/**
	 * Pauses the execution of the workflow, the start() method make the execution
	 * to continue
	 */
	void pause(){
		
	}

	/**
	 * Stop the execution of the engine
	 */
	void shutdown(){
		
	}

	/**
	 * Loads a workflow into the engine
	 */
	void loadWorkflow(Workflow wf){
		
	}
	
}
