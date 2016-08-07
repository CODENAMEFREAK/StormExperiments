/**
 * 
 */
package foss.freak.bolt;

import java.util.Map;

import org.apache.storm.task.OutputCollector;
import org.apache.storm.task.TopologyContext;
import org.apache.storm.topology.OutputFieldsDeclarer;
import org.apache.storm.topology.base.BaseRichBolt;
import org.apache.storm.tuple.Fields;
import org.apache.storm.tuple.Tuple;
import org.apache.storm.tuple.Values;

/**
 * @author codenamefreak
 *
 */
public class FreakFilterBolt extends BaseRichBolt{

	private OutputCollector _collector = null;
	@Override
	public void execute(Tuple input) {		
		int val = input.getInteger(0).intValue();
		if(val%2==0)
			_collector.emit("First", new Values(val));
		else
			_collector.emit("Second", new Values(val));		
		_collector.ack(input);
	}

	@Override
	public void prepare(Map conf, TopologyContext context, OutputCollector collector) {
		_collector = collector;		
	}

	@Override
	public void declareOutputFields(OutputFieldsDeclarer declarer) {
		declarer.declareStream("First", new Fields("value1"));
		declarer.declareStream("Second", new Fields("value2"));
	}

}
