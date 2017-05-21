package model;

import java.io.File;

/**
 * Created by valeriyagagarina on 5/21/17.
 */
public class NewProduct {

    public String name;
    public String code;
    public String quantity;
    public String weight;
    public String  width;
    public String height;
    public String length;
    public String picture;
    public String headTitle;
    public String price;

    public NewProduct(){ }

    public NewProduct withName(String name) {
        this.name = name;
        return this;
    }

    public NewProduct withCode(String code) {
        this.code = code;
        return this;
    }

    public NewProduct withQuantity(int quantity) {
        this.quantity = String.valueOf(quantity);
        return this;
    }

    public NewProduct withWeight(double weight) {
        this.weight = String.valueOf(weight);
        return this;
    }

    public NewProduct withWidth(double width) {
        this.width = String.valueOf(width);
        return this;
    }

    public NewProduct withHeight(double height) {
        this.height = String.valueOf(height);
        return this;
    }

    public NewProduct withLength(double length) {
        this.length = String.valueOf(length);
        return this;
    }

    public NewProduct withPicture(String pathToPicture) {
        this.picture = pathToPicture;
        return this;
    }

    public NewProduct withHeadTitle(String headTitle) {
        this.headTitle = headTitle;
        return this;
    }

    public NewProduct withPrice(double price) {
        this.price = String.valueOf(price);
        return this;
    }

    public String attributes = "Other\n" +
            "Material: Plastic";
    public String metaDescription = "Lorem ipsum dolor sit amet, ";
    public String shortDescription = "Lorem ipsum dolor sit amet, " +
            "consectetur adipiscing elit. Suspendisse sollicitudin " +
            "ante massa, eget ornare libero porta congue.";
    public String longDescription = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. " +
            "Suspendisse sollicitudin ante massa, eget ornare libero porta congue. " +
            "Cras scelerisque dui non consequat sollicitudin. Sed pretium tortor ac auctor molestie. " +
            "Nulla facilisi. Maecenas pulvinar nibh vitae lectus vehicula semper. Donec et aliquet velit. " +
            "Curabitur non ullamcorper mauris. In hac habitasse platea dictumst. Phasellus ut pretium justo, " +
            "sit amet bibendum urna. Maecenas sit amet arcu pulvinar, facilisis quam at, viverra nisi. " +
            "Morbi sit amet adipiscing ante. Integer imperdiet volutpat ante, sed venenatis urna volutpat a. " +
            "Proin justo massa, convallis vitae consectetur sit amet, facilisis id libero.  ";

}
