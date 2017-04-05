import java.awt.geom.Point2D;

import ic.ALC;
import parser.Parser;

public class Main {

	
	public static void main(String[] args) {
		Parser parser = new Parser();
		parser.init("/home/adam/Downloads/map", "pharmacy");
                parser.leClientes("/home/adam/Downloads/Salvador.txt");
		
		Point2D alc = ALC.getLC(parser.getNodes(), parser.getFacilities(), parser.getClientes());
		//Point2D minsum = ALC.getMinSum(parser.getNodes(), parser.getFacilities(), parser.getClientes());
		
		System.out.println(alc);
		//System.out.println(minsum);
		
		
	}
}
