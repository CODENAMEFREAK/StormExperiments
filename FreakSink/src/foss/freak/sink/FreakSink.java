/**
 * 
 */
package foss.freak.sink;

import java.util.Map;

import org.apache.storm.task.OutputCollector;
import org.apache.storm.task.TopologyContext;
import org.apache.storm.topology.IRichBolt;
import org.apache.storm.topology.OutputFieldsDeclarer;
import org.apache.storm.tuple.Tuple;

/**
 * @author codenamefreak
 *
 */
public class FreakSink implements IRichBolt{

	/**
	 * 
	 */
	private static final long serialVersionUID = -714290898772561320L;

	private OutputCollector _collector = null;
	@Override
	public void cleanup() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void execute(Tuple input) {
		System.out.println("\nSink Received ->"+input.getInteger(0)+"\tfrom\t"+input.getSourceStreamId() +"\n"); 
		
	}

	@Override
	public void prepare(Map conf, TopologyContext context, OutputCollector collector) {
		_collector = collector;
		
	}

	@Override
	public void declareOutputFields(OutputFieldsDeclarer arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Map<String, Object> getComponentConfiguration() {
		// TODO Auto-generated method stub
		return null;
	}

	
}
