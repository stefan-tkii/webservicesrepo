
package webproject.filmreview.soapresources;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for GetAllMoviesFiltered complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="GetAllMoviesFiltered">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="FilterModel" type="{http://SoapResources.filmreview.webproject/}filterModel" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "GetAllMoviesFiltered", propOrder = {
    "filterModel"
})
public class GetAllMoviesFiltered {

    @XmlElement(name = "FilterModel")
    protected FilterModel filterModel;

    /**
     * Gets the value of the filterModel property.
     * 
     * @return
     *     possible object is
     *     {@link FilterModel }
     *     
     */
    public FilterModel getFilterModel() {
        return filterModel;
    }

    /**
     * Sets the value of the filterModel property.
     * 
     * @param value
     *     allowed object is
     *     {@link FilterModel }
     *     
     */
    public void setFilterModel(FilterModel value) {
        this.filterModel = value;
    }

}
