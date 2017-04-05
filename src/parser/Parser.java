package parser;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.StringTokenizer;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;



/**
 * 
 * @author Gabriel Antonio Pereira dos Santos Carneiro
 *
 */
public class Parser {

	List<Node> facilities;
        List<Node> not_facilities;
	List<Node> pontos;
	List<Tweet> clientes;
	
	public List<Tweet> getClientes() {
		return clientes;
	}

        public List<Node> getNot_facilities() {
            return not_facilities;
        }

        
	public Parser() {
	pontos = new ArrayList<>();
	facilities = new ArrayList<>();
	clientes = new ArrayList<>();
        not_facilities = new ArrayList<>();
        
	}
	
    
	public List<Node> getFacilities() {
		return facilities;
	}


//	public static void main(String[] args) {
//		Parser a = new Parser();
//		a.init(null, "marketplace");
//		a.leClientes(null);
//		Collection<Node> b = a.getNodes();
////		for (Node node : b) {
////			System.out.println(node);
////		}
//		
//		for (Node node : a.facilities) {
//			System.out.println(node);
//		}
//		
//		for (Tweet tweet : a.clientes) {
//			System.out.println(tweet);
//		}
//		System.out.println(a.facilities.size());
//		System.out.println(a.pontos.size());
//		System.out.println(a.clientes.size());
//	}
	
	public List<Node> getNodes() {
		return pontos;
	}

	
	public List<Node> getFacilities(String amenity){
		 
		return facilities;
	}

	public void leClientes(String txt){
		try {
			BufferedReader reader = new BufferedReader(new FileReader(txt));
			//BufferedReader reader = new BufferedReader(new FileReader("/Users/gabrielantonio/Downloads/Archive/Salvador.txt"));
			reader.readLine();
			while(reader.ready()){
				String linha = reader.readLine();
				StringTokenizer token = new StringTokenizer(linha);
				Tweet tmp = new Tweet();
				tmp.id = Integer.parseInt(token.nextToken()); //id
				tmp.lat = Double.parseDouble(token.nextToken());
				tmp.lon = Double.parseDouble(token.nextToken());
				tmp.data = new Date(Long.parseLong(token.nextToken()));
				tmp.texto = token.nextToken("");
				clientes.add(tmp);
			}
			reader.close();
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void init(String xml, String type){
		//String a = "/Users/gabrielantonio/Downloads/map";
                String a = xml;
		Node nodeAtual = null;
		XMLInputFactory factory = XMLInputFactory.newInstance();
		try {
			XMLStreamReader reader =
			        factory.createXMLStreamReader(new FileReader(a));
			        //ClassLoader.getSystemResourceAsStream(a));
			
			while(reader.hasNext()){
				int event = reader.next();
				
				switch (event) {
				case XMLStreamConstants.START_ELEMENT:
					if("node".equals(reader.getLocalName())){
						
						nodeAtual = new Node();
						//if(!nodes.contains(nodeAtual)) 
							pontos.add(nodeAtual);
						//nodeAtual.id = Integer.parseInt(reader.getAttributeValue(0));
						nodeAtual.lat = Double.parseDouble(reader.getAttributeValue(1));
						nodeAtual.lon = Double.parseDouble(reader.getAttributeValue(2));
					}
					else if("way".equals(reader.getLocalName())){
						boolean tmp = true; 
						int tmp_event;
						while(tmp){
							tmp_event = reader.next();
							if(tmp_event == XMLStreamConstants.END_ELEMENT){
								if(reader.getLocalName().equals("way")){
									tmp = false;
								}
							}
							
						} //fim while
						
					}
					else if("tag".equals(reader.getLocalName())){
						if(reader.getAttributeValue(0).equals("amenity")){
							nodeAtual.amenity = reader.getAttributeValue(1);
							
							if(type != null) 
								if(nodeAtual.amenity.equals(type)) 
									//if(!facilities.contains(nodeAtual)) 
									facilities.add(nodeAtual);
                                                                
						}
					}
					break;
				
				default:
						break;
					}
			}
		} catch (XMLStreamException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
                
                
                pontos.removeAll(facilities);
		
	}
}
