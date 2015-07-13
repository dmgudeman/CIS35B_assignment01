import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.util.*;

/**
 * Created by davidgudeman on 7/12/15.
 */
public class Search
{
    NodeList nodeLists;

    {
        nodeLists = new NodeList()
        {
            @Override
            public Node item(int index)
            {
                return null;
            }

            @Override
            public int getLength()
            {
                return 0;
            }
        };
    }

    public <T extends Comparable>Search(NodeList nodeList)
    {
        this.nodeLists = nodeList;
    }

    public ArrayList template(NodeList nodeList, String pattern)
    {
        Store<Node> store = new Store<Node>(nodeList);
        Map<String, String> items = new HashMap<>();

        int k = 0;
        for (int i = 0; i < nodeList.getLength(); i++)
        {
            Node l = nodeList.item(i);
            if (l.getNodeType() == Node.ELEMENT_NODE)
            {

                Element location = (Element) l;
                NodeList dataList = location.getChildNodes();
                for (int j = 0; j < dataList.getLength(); j++)
                {
                    Node n = dataList.item(j);
                    if (n.getNodeType() == Node.ELEMENT_NODE)
                    {

                            Element place = (Element) n;
                        if (place.getTagName() == pattern)

                            items.put(place.getTextContent(), place.getTagName());
                    }
                }
            }
        }
        ArrayList<String> sortedCityStrings = new ArrayList(items.keySet());
        Collections.sort(sortedCityStrings);
        return sortedCityStrings;
    }


    public ArrayList sortedNodes(NodeList nodeList, ArrayList sortedItemList )
    {

        ArrayList<Node> cityList = new ArrayList<>();
        ArrayList<Node> cityStateList = new ArrayList<>();
        for (int i = 0; i < sortedItemList.size(); i++)
        {
            String pattern = (String) sortedItemList.get(i);
            for (int j = 0; j < nodeList.getLength(); j++)
            {
                Node child = nodeList.item(j);
                if (child.getNodeType() == Node.ELEMENT_NODE)
                {
                    Element eElement = (Element) child;

                    String match = eElement.getElementsByTagName("City").item(0).getTextContent();
                    if (match.compareTo(pattern)== 0)
                    {
                        cityList.add(child);
                    }
                }
            }

        }
        return cityList;
    }
    public void showNodeList(NodeList nodeList)
    {
        try {
            for (int i = 0; i < nodeList.getLength(); i++)
            {
                Node child = nodeList.item(i);
                if (child.getNodeType() == Node.ELEMENT_NODE)
                {
                    Element eElement = (Element) child;

                    System.out.println("Latitude : " + eElement.getElementsByTagName("Latitude").item(0).getTextContent());
                    System.out.println("Longitude : " + eElement.getElementsByTagName("Longitude").item(0).getTextContent());
                    System.out.println("City : " + eElement.getElementsByTagName("City").item(0).getTextContent());
                    System.out.println("State : " + eElement.getElementsByTagName("State").item(0).getTextContent());
                }
            }

            System.out.println("----------------------------");


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public int binarySearch(ArrayList arrayList, String item)
    {
        int index;
        index = Collections.binarySearch(arrayList, item);
        return index;
    }
}

