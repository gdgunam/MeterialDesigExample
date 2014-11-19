package amigos.com.materialdesingtwo.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by sati on 12/11/2014.
 */
public class ModelFood {
    @SerializedName("nombre")
    private String titleFood;

    @SerializedName("descripcion")
    private String descriptionFood;

    @SerializedName("imagen")
    private String urlImage;

    public ModelFood(String titleFood, String descriptionFood, String urlImage) {
        this.titleFood = titleFood;
        this.descriptionFood = descriptionFood;
        this.urlImage = urlImage;
    }


    public String getTitleFood() {
        return titleFood;
    }

    public String getDescriptionFood() {
        return descriptionFood;
    }

    public String getUrlImage() {
        return urlImage;
    }
}
