package foss.freak.spout;

import java.util.Map;

import org.apache.storm.spout.SpoutOutputCollector;
import org.apache.storm.task.TopologyContext;
import org.apache.storm.topology.IRichSpout;
import org.apache.storm.topology.OutputFieldsDeclarer;
import org.apache.storm.tuple.Fields;
import org.apache.storm.tuple.Values;

public class FreakEvenNumberSpout implements IRichSpout{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7497556423563155037L;
	private SpoutOutputCollector _collector;
	private TopologyContext _context = null;
	
	@Override
	public void ack(Object arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void activate() {
		System.out.println("\n"+this.toString() +" is ACTIVATED\n");		
	}

	@Override
	public void close() {
		System.out.println("\nSpot Closed\n");
		
	}

	@Override
	public void deactivate() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void fail(Object arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void nextTuple() {
		
		System.out.println("Emmiting from Spout1");
		
		for(int i=1;i<=10;i++)
		{
			System.out.println("Sending "+ (2*i));
			_collector.emit(new Values(i));
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {				
				e.printStackTrace();
			}
		}
		
		System.out.println("Emmited from Spout");		
	}

	@Override
	public void open(Map conf, TopologyContext context, SpoutOutputCollector collector) {
		_context =context;
		_collector=collector;
		/*
		 * Reader initialized
		 * */
		
	}

	@Override
	public void declareOutputFields(OutputFieldsDeclarer declarer) {
		declarer.declare(new Fields("value"));
		
	}

	@Override
	public Map<String, Object> getComponentConfiguration() {
		
		return null;
	}
	

}
