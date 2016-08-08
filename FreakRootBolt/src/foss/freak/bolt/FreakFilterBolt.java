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

	/**
	 * 
	 */
	private static final long serialVersionUID = 9119846543008229881L;
	private OutputCollector _collector = null;
	@Override
	public void execute(Tuple input) {		
		int val = input.getInteger(0).intValue();
		System.out.print("Filter Bolt received\t"+val+"\t");
		if(val%2==0)			
		{	_collector.emit("EvenStream", new Values(val));
		System.out.print("Forwarding on Even Stream\n");
		}		
		else
			{_collector.emit("OddStream", new Values(val));	
			System.out.print("Forwarding on Odd Stream\n");
			}	
		_collector.ack(input);
	}

	@Override
	public void prepare(Map conf, TopologyContext context, OutputCollector collector) {
		_collector = collector;		
	}

	@Override
	public void declareOutputFields(OutputFieldsDeclarer declarer) {
		declarer.declareStream("EvenStream", new Fields("evenvalue"));
		declarer.declareStream("OddStream", new Fields("oddvalue"));
	}

}
