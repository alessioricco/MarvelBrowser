
package it.alessioricco.marvelbrowser.models.comics;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Result implements Serializable
{

    /**
     * 
     */
    @SerializedName("id")
    @Expose
    private Integer id;
    /**
     * 
     */
    @SerializedName("digitalId")
    @Expose
    private Integer digitalId;
    /**
     * 
     */
    @SerializedName("title")
    @Expose
    private String title;
    /**
     * 
     */
    @SerializedName("issueNumber")
    @Expose
    private Double issueNumber;
    /**
     * 
     */
    @SerializedName("variantDescription")
    @Expose
    private String variantDescription;
    /**
     * 
     */
    @SerializedName("description")
    @Expose
    private String description;
    /**
     * 
     */
    @SerializedName("modified")
    @Expose
    private String modified;
    /**
     * 
     */
    @SerializedName("isbn")
    @Expose
    private String isbn;
    /**
     * 
     */
    @SerializedName("upc")
    @Expose
    private String upc;
    /**
     * 
     */
    @SerializedName("diamondCode")
    @Expose
    private String diamondCode;
    /**
     * 
     */
    @SerializedName("ean")
    @Expose
    private String ean;
    /**
     * 
     */
    @SerializedName("issn")
    @Expose
    private String issn;
    /**
     * 
     */
    @SerializedName("format")
    @Expose
    private String format;
    /**
     * 
     */
    @SerializedName("pageCount")
    @Expose
    private Integer pageCount;
    /**
     * 
     */
    @SerializedName("textObjects")
    @Expose
    private List<TextObject> textObjects = new ArrayList<TextObject>();
    /**
     * 
     */
    @SerializedName("resourceURI")
    @Expose
    private String resourceURI;
    /**
     * 
     */
    @SerializedName("urls")
    @Expose
    private List<Url> urls = new ArrayList<Url>();
    /**
     * 
     */
    @SerializedName("series")
    @Expose
    private Series series;
    /**
     * 
     */
    @SerializedName("variants")
    @Expose
    private List<Variant> variants = new ArrayList<Variant>();
    /**
     * 
     */
    @SerializedName("collections")
    @Expose
    private List<Object> collections = new ArrayList<Object>();
    /**
     * 
     */
    @SerializedName("collectedIssues")
    @Expose
    private List<Object> collectedIssues = new ArrayList<Object>();
    /**
     * 
     */
    @SerializedName("dates")
    @Expose
    private List<Date> dates = new ArrayList<Date>();
    /**
     * 
     */
    @SerializedName("prices")
    @Expose
    private List<Price> prices = new ArrayList<Price>();
    /**
     * 
     */
    @SerializedName("thumbnail")
    @Expose
    private Thumbnail thumbnail;
    /**
     * 
     */
    @SerializedName("images")
    @Expose
    private List<Image> images = new ArrayList<Image>();
    /**
     * 
     */
    @SerializedName("creators")
    @Expose
    private Creators creators;
    /**
     * 
     */
    @SerializedName("characters")
    @Expose
    private Characters characters;
    /**
     * 
     */
    @SerializedName("stories")
    @Expose
    private Stories stories;
    /**
     * 
     */
    @SerializedName("events")
    @Expose
    private Events events;
    private final static long serialVersionUID = 728160600400884074L;

    /**
     * 
     * @return
     *     The id
     */
    public Integer getId() {
        return id;
    }

    /**
     * 
     * @param id
     *     The id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 
     * @return
     *     The digitalId
     */
    public Integer getDigitalId() {
        return digitalId;
    }

    /**
     * 
     * @param digitalId
     *     The digitalId
     */
    public void setDigitalId(Integer digitalId) {
        this.digitalId = digitalId;
    }

    /**
     * 
     * @return
     *     The title
     */
    public String getTitle() {
        return title;
    }

    /**
     * 
     * @param title
     *     The title
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * 
     * @return
     *     The issueNumber
     */
    public Double getIssueNumber() {
        return issueNumber;
    }

    /**
     * 
     * @param issueNumber
     *     The issueNumber
     */
    public void setIssueNumber(Double issueNumber) {
        this.issueNumber = issueNumber;
    }

    /**
     * 
     * @return
     *     The variantDescription
     */
    public String getVariantDescription() {
        return variantDescription;
    }

    /**
     * 
     * @param variantDescription
     *     The variantDescription
     */
    public void setVariantDescription(String variantDescription) {
        this.variantDescription = variantDescription;
    }

    /**
     * 
     * @return
     *     The description
     */
    public String getDescription() {
        return description;
    }

    /**
     * 
     * @param description
     *     The description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * 
     * @return
     *     The modified
     */
    public String getModified() {
        return modified;
    }

    /**
     * 
     * @param modified
     *     The modified
     */
    public void setModified(String modified) {
        this.modified = modified;
    }

    /**
     * 
     * @return
     *     The isbn
     */
    public String getIsbn() {
        return isbn;
    }

    /**
     * 
     * @param isbn
     *     The isbn
     */
    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    /**
     * 
     * @return
     *     The upc
     */
    public String getUpc() {
        return upc;
    }

    /**
     * 
     * @param upc
     *     The upc
     */
    public void setUpc(String upc) {
        this.upc = upc;
    }

    /**
     * 
     * @return
     *     The diamondCode
     */
    public String getDiamondCode() {
        return diamondCode;
    }

    /**
     * 
     * @param diamondCode
     *     The diamondCode
     */
    public void setDiamondCode(String diamondCode) {
        this.diamondCode = diamondCode;
    }

    /**
     * 
     * @return
     *     The ean
     */
    public String getEan() {
        return ean;
    }

    /**
     * 
     * @param ean
     *     The ean
     */
    public void setEan(String ean) {
        this.ean = ean;
    }

    /**
     * 
     * @return
     *     The issn
     */
    public String getIssn() {
        return issn;
    }

    /**
     * 
     * @param issn
     *     The issn
     */
    public void setIssn(String issn) {
        this.issn = issn;
    }

    /**
     * 
     * @return
     *     The format
     */
    public String getFormat() {
        return format;
    }

    /**
     * 
     * @param format
     *     The format
     */
    public void setFormat(String format) {
        this.format = format;
    }

    /**
     * 
     * @return
     *     The pageCount
     */
    public Integer getPageCount() {
        return pageCount;
    }

    /**
     * 
     * @param pageCount
     *     The pageCount
     */
    public void setPageCount(Integer pageCount) {
        this.pageCount = pageCount;
    }

    /**
     * 
     * @return
     *     The textObjects
     */
    public List<TextObject> getTextObjects() {
        return textObjects;
    }

    /**
     * 
     * @param textObjects
     *     The textObjects
     */
    public void setTextObjects(List<TextObject> textObjects) {
        this.textObjects = textObjects;
    }

    /**
     * 
     * @return
     *     The resourceURI
     */
    public String getResourceURI() {
        return resourceURI;
    }

    /**
     * 
     * @param resourceURI
     *     The resourceURI
     */
    public void setResourceURI(String resourceURI) {
        this.resourceURI = resourceURI;
    }

    /**
     * 
     * @return
     *     The urls
     */
    public List<Url> getUrls() {
        return urls;
    }

    /**
     * 
     * @param urls
     *     The urls
     */
    public void setUrls(List<Url> urls) {
        this.urls = urls;
    }

    /**
     * 
     * @return
     *     The series
     */
    public Series getSeries() {
        return series;
    }

    /**
     * 
     * @param series
     *     The series
     */
    public void setSeries(Series series) {
        this.series = series;
    }

    /**
     * 
     * @return
     *     The variants
     */
    public List<Variant> getVariants() {
        return variants;
    }

    /**
     * 
     * @param variants
     *     The variants
     */
    public void setVariants(List<Variant> variants) {
        this.variants = variants;
    }

    /**
     * 
     * @return
     *     The collections
     */
    public List<Object> getCollections() {
        return collections;
    }

    /**
     * 
     * @param collections
     *     The collections
     */
    public void setCollections(List<Object> collections) {
        this.collections = collections;
    }

    /**
     * 
     * @return
     *     The collectedIssues
     */
    public List<Object> getCollectedIssues() {
        return collectedIssues;
    }

    /**
     * 
     * @param collectedIssues
     *     The collectedIssues
     */
    public void setCollectedIssues(List<Object> collectedIssues) {
        this.collectedIssues = collectedIssues;
    }

    /**
     * 
     * @return
     *     The dates
     */
    public List<Date> getDates() {
        return dates;
    }

    /**
     * 
     * @param dates
     *     The dates
     */
    public void setDates(List<Date> dates) {
        this.dates = dates;
    }

    /**
     * 
     * @return
     *     The prices
     */
    public List<Price> getPrices() {
        return prices;
    }

    /**
     * 
     * @param prices
     *     The prices
     */
    public void setPrices(List<Price> prices) {
        this.prices = prices;
    }

    /**
     * 
     * @return
     *     The thumbnail
     */
    public Thumbnail getThumbnail() {
        return thumbnail;
    }

    /**
     * 
     * @param thumbnail
     *     The thumbnail
     */
    public void setThumbnail(Thumbnail thumbnail) {
        this.thumbnail = thumbnail;
    }

    /**
     * 
     * @return
     *     The images
     */
    public List<Image> getImages() {
        return images;
    }

    /**
     * 
     * @param images
     *     The images
     */
    public void setImages(List<Image> images) {
        this.images = images;
    }

    /**
     * 
     * @return
     *     The creators
     */
    public Creators getCreators() {
        return creators;
    }

    /**
     * 
     * @param creators
     *     The creators
     */
    public void setCreators(Creators creators) {
        this.creators = creators;
    }

    /**
     * 
     * @return
     *     The characters
     */
    public Characters getCharacters() {
        return characters;
    }

    /**
     * 
     * @param characters
     *     The characters
     */
    public void setCharacters(Characters characters) {
        this.characters = characters;
    }

    /**
     * 
     * @return
     *     The stories
     */
    public Stories getStories() {
        return stories;
    }

    /**
     * 
     * @param stories
     *     The stories
     */
    public void setStories(Stories stories) {
        this.stories = stories;
    }

    /**
     * 
     * @return
     *     The events
     */
    public Events getEvents() {
        return events;
    }

    /**
     * 
     * @param events
     *     The events
     */
    public void setEvents(Events events) {
        this.events = events;
    }


}
