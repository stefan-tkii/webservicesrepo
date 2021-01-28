package webproject.filmreview.Models;

import java.io.Serializable;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "SeriesListObject")
@XmlAccessorType(XmlAccessType.FIELD)
public class SeriesListPojo implements Serializable
{
    private static final long serialVersionUID = 1L;

    @XmlElementWrapper(name="SeriesList")
    @XmlElement(name="Series")
    private List<Series> seriesList;

    public SeriesListPojo()
    {

    }

    public List<Series> getSeriesList() 
    {
        return this.seriesList;
    }

    public void setSeriesList(List<Series> seriesList) 
    {
        this.seriesList = seriesList;
    }

}