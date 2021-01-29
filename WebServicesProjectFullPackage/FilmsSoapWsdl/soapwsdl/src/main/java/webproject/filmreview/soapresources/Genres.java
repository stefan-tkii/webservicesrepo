
package webproject.filmreview.soapresources;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for genres.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="genres">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="COMEDY"/>
 *     &lt;enumeration value="HORROR"/>
 *     &lt;enumeration value="ACTION"/>
 *     &lt;enumeration value="DOCUMENTARY"/>
 *     &lt;enumeration value="DRAMA"/>
 *     &lt;enumeration value="THRILLER"/>
 *     &lt;enumeration value="BIOGRAPHY"/>
 *     &lt;enumeration value="ROMANCE"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "genres")
@XmlEnum
public enum Genres {

    COMEDY,
    HORROR,
    ACTION,
    DOCUMENTARY,
    DRAMA,
    THRILLER,
    BIOGRAPHY,
    ROMANCE;

    public String value() {
        return name();
    }

    public static Genres fromValue(String v) {
        return valueOf(v);
    }

}
