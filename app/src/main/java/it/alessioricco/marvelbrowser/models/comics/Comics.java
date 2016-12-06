
package it.alessioricco.marvelbrowser.models.comics;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Comics implements Serializable
{

    /**
     * 
     */
    @SerializedName("code")
    @Expose
    private Integer code;
    /**
     * 
     */
    @SerializedName("status")
    @Expose
    private String status;
    /**
     * 
     */
    @SerializedName("copyright")
    @Expose
    private String copyright;
    /**
     * 
     */
    @SerializedName("attributionText")
    @Expose
    private String attributionText;
    /**
     * 
     */
    @SerializedName("attributionHTML")
    @Expose
    private String attributionHTML;
    /**
     * 
     */
    @SerializedName("etag")
    @Expose
    private String etag;
    /**
     * 
     */
    @SerializedName("data")
    @Expose
    private Data data;
    private final static long serialVersionUID = 1175470497736451923L;

    /**
     * 
     * @return
     *     The code
     */
    public Integer getCode() {
        return code;
    }

    /**
     * 
     * @param code
     *     The code
     */
    public void setCode(Integer code) {
        this.code = code;
    }

    /**
     * 
     * @return
     *     The status
     */
    public String getStatus() {
        return status;
    }

    /**
     * 
     * @param status
     *     The status
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * 
     * @return
     *     The copyright
     */
    public String getCopyright() {
        return copyright;
    }

    /**
     * 
     * @param copyright
     *     The copyright
     */
    public void setCopyright(String copyright) {
        this.copyright = copyright;
    }

    /**
     * 
     * @return
     *     The attributionText
     */
    public String getAttributionText() {
        return attributionText;
    }

    /**
     * 
     * @param attributionText
     *     The attributionText
     */
    public void setAttributionText(String attributionText) {
        this.attributionText = attributionText;
    }

    /**
     * 
     * @return
     *     The attributionHTML
     */
    public String getAttributionHTML() {
        return attributionHTML;
    }

    /**
     * 
     * @param attributionHTML
     *     The attributionHTML
     */
    public void setAttributionHTML(String attributionHTML) {
        this.attributionHTML = attributionHTML;
    }

    /**
     * 
     * @return
     *     The etag
     */
    public String getEtag() {
        return etag;
    }

    /**
     * 
     * @param etag
     *     The etag
     */
    public void setEtag(String etag) {
        this.etag = etag;
    }

    /**
     * 
     * @return
     *     The data
     */
    public Data getData() {
        return data;
    }

    /**
     * 
     * @param data
     *     The data
     */
    public void setData(Data data) {
        this.data = data;
    }


}
