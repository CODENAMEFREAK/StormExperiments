package foss.freak.topology;

import java.io.IOException;
import java.util.ArrayList;

import org.apache.storm.Config;
import org.apache.storm.LocalCluster;
import org.apache.storm.generated.Bolt;
import org.apache.storm.topology.TopologyBuilder;import org.apache.storm.trident.operation.DefaultResourceDeclarer;

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
		config.put(Config.TOPOLOGY_DEBUG,false);
		
		TopologyBuilder builder = new TopologyBuilder();		
		/*
		 * ;;;;;;;;;;Working;;;;
		 * builder.setSpout("readerEven", new FreakEvenNumberSpout());
		builder.setSpout("readerOdd", new FreakOddNumberSpout());
		builder.setBolt("even_bolt", new FreakEvenBolt()).shuffleGrouping("readerEven");
		builder.setBolt("odd_bolt", new FreakOddBolt()).shuffleGrouping("readerOdd");
		builder.setBolt("sink", new FreakSink()).shuffleGrouping("even_bolt").shuffleGrouping("odd_bolt");
		Working*/
		
		builder.setSpout("readerEven", new FreakEvenNumberSpout());
		builder.setSpout("readerOdd", new FreakOddNumberSpout());
		builder.setBolt("root_bolt", new FreakFilterBolt(),2).shuffleGrouping("readerEven").shuffleGrouping("readerOdd");
		
		builder.setBolt("even_bolt", new FreakEvenBolt()).shuffleGrouping("root_bolt","EvenStream");
		builder.setBolt("odd_bolt", new FreakOddBolt(),20).shuffleGrouping("root_bolt","OddStream");
		builder.setBolt("sink", new FreakSink()).shuffleGrouping("even_bolt").shuffleGrouping("odd_bolt");
		
		
		
		LocalCluster cluster = new LocalCluster();
		
		cluster.submitTopology("FreakStormTopology", config, builder.createTopology());
		
		Thread.sleep(100000);
		cluster.shutdown();
		

	}

}
