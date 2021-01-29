
package webproject.filmreview.soapresources;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for seriesListPojo complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="seriesListPojo">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="SeriesList" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="Series" type="{http://SoapResources.filmreview.webproject/}series" maxOccurs="unbounded" minOccurs="0"/>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "seriesListPojo", propOrder = {
    "seriesList"
})
public class SeriesListPojo {

    @XmlElement(name = "SeriesList")
    protected SeriesListPojo.SeriesList seriesList;

    /**
     * Gets the value of the seriesList property.
     * 
     * @return
     *     possible object is
     *     {@link SeriesListPojo.SeriesList }
     *     
     */
    public SeriesListPojo.SeriesList getSeriesList() {
        return seriesList;
    }

    /**
     * Sets the value of the seriesList property.
     * 
     * @param value
     *     allowed object is
     *     {@link SeriesListPojo.SeriesList }
     *     
     */
    public void setSeriesList(SeriesListPojo.SeriesList value) {
        this.seriesList = value;
    }


    /**
     * <p>Java class for anonymous complex type.
     * 
     * <p>The following schema fragment specifies the expected content contained within this class.
     * 
     * <pre>
     * &lt;complexType>
     *   &lt;complexContent>
     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *       &lt;sequence>
     *         &lt;element name="Series" type="{http://SoapResources.filmreview.webproject/}series" maxOccurs="unbounded" minOccurs="0"/>
     *       &lt;/sequence>
     *     &lt;/restriction>
     *   &lt;/complexContent>
     * &lt;/complexType>
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "series"
    })
    public static class SeriesList {

        @XmlElement(name = "Series")
        protected List<Series> series;

        /**
         * Gets the value of the series property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the series property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getSeries().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link Series }
         * 
         * 
         */
        public List<Series> getSeries() {
            if (series == null) {
                series = new ArrayList<Series>();
            }
            return this.series;
        }

    }

}
