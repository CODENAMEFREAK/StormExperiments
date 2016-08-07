/**
 * 
 */
package foss.freak.bolt;

import java.util.Map;

import org.apache.storm.task.OutputCollector;
import org.apache.storm.task.TopologyContext;
import org.apache.storm.topology.IRichBolt;
import org.apache.storm.topology.OutputFieldsDeclarer;
import org.apache.storm.tuple.Fields;
import org.apache.storm.tuple.Tuple;
import org.apache.storm.tuple.Values;

/**
 * @author codenamefreak
 *
 */
public class FreakOddBolt implements IRichBolt{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6609775928904029447L;
	private OutputCollector _collector = null;
	
	@Override
	public void execute(Tuple input) {		
		int val = input.getInteger(0).intValue();
		_collector.emit(new Values(val));		
		_collector.ack(input);
	}

	@Override
	public void prepare(Map conf, TopologyContext context, OutputCollector collector) {
		_collector = collector;		
	}

	@Override
	public void declareOutputFields(OutputFieldsDeclarer declarer) {
		
		declarer.declare(new Fields("values"));
	}

	@Override
	public void cleanup() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Map<String, Object> getComponentConfiguration() {
		// TODO Auto-generated method stub
		return null;
	}



}
