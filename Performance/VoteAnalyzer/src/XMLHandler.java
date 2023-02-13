import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

public class XMLHandler extends DefaultHandler
{
    private static SimpleDateFormat birthDayFormat = new SimpleDateFormat("yyyy.MM.dd");
    private int counter;
    private static final int MAX_COUNTER_VALUE = 50000;

    public XMLHandler()
    {
        counter = 0;
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException
    {
        try
        {
            if (qName.equals("voter"))
            {
                Date birthday = birthDayFormat.parse(attributes.getValue("birthDay"));
                DBConnection.countVoter(attributes.getValue("name"), birthDayFormat.format(birthday));
                counter++;
                if (counter == MAX_COUNTER_VALUE)
                {
                    try
                    {
                        DBConnection.executeMultiInsert();
                    } catch (SQLException e)
                    {
                        e.printStackTrace();
                    }
                    counter = 0;
                }
            }

        } catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException
    {
        if (qName.equals("voters"))
        {
            try
            {
                DBConnection.executeMultiInsert();
            } catch (SQLException e)
            {
                e.printStackTrace();
            }
        }
    }
}