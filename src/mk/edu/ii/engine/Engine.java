package mk.edu.ii.engine;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import mk.edu.ii.listeners.Listener;
import net.kwfgrid.gworkflowdl.structure.Workflow;

public class Engine {

	String id;
	String trans;
	
	BlockingQueue blockQueue;
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
		blockQueue =  new LinkedBlockingQueue();
		
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
	

	void run(){
		
	};

	/**
	 * Starts the engine
	 */
	void start(){
		
	}

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
