package amazinginsidestudios.habit.components;

/**
 * Created by Sangeeth Nandakumar on 11-12-2017.
 */

public class XmlCompressor {
    String xml = "<?xml encoding='UTF-8' version='1.0'?>";
    boolean isCompressed = false;

    public XmlCompressor(String xml, boolean isCompressed) {
        this.xml = xml;
        this.isCompressed = isCompressed;
    }

    public String getXml() {
        if (!isCompressed) {
            //Encode
            xml = xml.replace("\t", "");
            xml = xml.replace("\n", "@");
            xml = xml.replace("\"", "|");
            xml = xml.replace("\'", "|");
            xml = xml.replace("<", "[");
            xml = xml.replace(">", "]");
            xml = xml.replace("=", "#");
            xml = xml.replace("/", "!");
            //And compress
        } else {
            //Encode
            xml = xml.replace("\t", "");
            xml = xml.replace("@", "\n");
            xml = xml.replace("|", "\'");
            xml = xml.replace("[", "<");
            xml = xml.replace("]", ">");
            xml = xml.replace("#", "=");
            xml = xml.replace("!", "/");
            //And compress
        }
        return xml;
    }


    public void setXml(String xml) {
        this.xml = xml;
    }

    public boolean isCompressed() {
        return isCompressed;
    }

    public void setCompressed(boolean compressed) {
        isCompressed = compressed;
    }
}
