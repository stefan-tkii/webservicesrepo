
package webproject.filmreview.soapresources;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for season complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="season">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="description" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="episodes" type="{http://SoapResources.filmreview.webproject/}episode" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="id" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="mainActors" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="rating" type="{http://www.w3.org/2001/XMLSchema}float"/>
 *         &lt;element name="seasonNumber" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="seriesId" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "season", propOrder = {
    "description",
    "episodes",
    "id",
    "mainActors",
    "rating",
    "seasonNumber",
    "seriesId"
})
public class Season {

    protected String description;
    @XmlElement(nillable = true)
    protected List<Episode> episodes;
    protected long id;
    protected String mainActors;
    protected float rating;
    protected int seasonNumber;
    protected long seriesId;

    /**
     * Gets the value of the description property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets the value of the description property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDescription(String value) {
        this.description = value;
    }

    /**
     * Gets the value of the episodes property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the episodes property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getEpisodes().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Episode }
     * 
     * 
     */
    public List<Episode> getEpisodes() {
        if (episodes == null) {
            episodes = new ArrayList<Episode>();
        }
        return this.episodes;
    }

    /**
     * Gets the value of the id property.
     * 
     */
    public long getId() {
        return id;
    }

    /**
     * Sets the value of the id property.
     * 
     */
    public void setId(long value) {
        this.id = value;
    }

    /**
     * Gets the value of the mainActors property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMainActors() {
        return mainActors;
    }

    /**
     * Sets the value of the mainActors property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMainActors(String value) {
        this.mainActors = value;
    }

    /**
     * Gets the value of the rating property.
     * 
     */
    public float getRating() {
        return rating;
    }

    /**
     * Sets the value of the rating property.
     * 
     */
    public void setRating(float value) {
        this.rating = value;
    }

    /**
     * Gets the value of the seasonNumber property.
     * 
     */
    public int getSeasonNumber() {
        return seasonNumber;
    }

    /**
     * Sets the value of the seasonNumber property.
     * 
     */
    public void setSeasonNumber(int value) {
        this.seasonNumber = value;
    }

    /**
     * Gets the value of the seriesId property.
     * 
     */
    public long getSeriesId() {
        return seriesId;
    }

    /**
     * Sets the value of the seriesId property.
     * 
     */
    public void setSeriesId(long value) {
        this.seriesId = value;
    }

}
