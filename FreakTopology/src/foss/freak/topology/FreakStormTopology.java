package foss.freak.topology;

import java.io.IOException;

import org.apache.storm.Config;
import org.apache.storm.LocalCluster;
import org.apache.storm.topology.TopologyBuilder;

import foss.freak.bolt.FreakEvenBolt;
import foss.freak.bolt.FreakFilterBolt;
import foss.freak.bolt.FreakOddBolt;
import foss.freak.sink.FreakSink;
import foss.freak.spout.FreakEvenNumberSpout;
import foss.freak.spout.FreakOddNumberSpout;

public class FreakStormTopology {

	/**
	 * @param args
	 * @throws InterruptedException 
	 */
	
	public static void main(String[] args) throws InterruptedException, IOException {	
		
		
		
		
		Config config = new Config();
		
		config.setDebug(true);
		config.put(Config.TOPOLOGY_MAX_SPOUT_PENDING, 1);
		
		
		
		TopologyBuilder builder = new TopologyBuilder();		
		builder.setSpout("readerEven", new FreakEvenNumberSpout(),10);
		builder.setSpout("readerOdd", new FreakOddNumberSpout(),10);
		
		
		builder.setBolt("root_bolt", new FreakFilterBolt(),20).shuffleGrouping("readerEven").shuffleGrouping("readerOdd");
		
		builder.setBolt("even_bolt", new FreakEvenBolt(),20).shuffleGrouping("root_bolt");
		builder.setBolt("odd_bolt", new FreakOddBolt(),20).shuffleGrouping("root_bolt");
		/*builder.setBolt("sink", new FreakSink()).shuffleGrouping("even_bolt").shuffleGrouping("odd_bolt");*/
		
		
		
		LocalCluster cluster = new LocalCluster();
		cluster.submitTopology("FreakStormTopology", config, builder.createTopology());
		
		Thread.sleep(100000);
		cluster.shutdown();
		

	}

}
