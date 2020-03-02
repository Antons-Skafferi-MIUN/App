package se.miun.dt170.antonsskafferi.data;

public class MenuItem {

    private String title = "";
    private String cost = "";
    private String category ="";
    private String id ="";

    //CTOR
    public MenuItem() {
        this.setTitle(title);
        this.setCost(cost);
        this.setCategory(category);
        this.setId(id);
    }

    //Getters
    public String getTitle() {
        return title;
    }

    public String getCost() {
        return cost;
    }


    public void setTitle(String title) {
        this.title = title;
    }

    public void setCost(String cost) {
        this.cost = cost;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
