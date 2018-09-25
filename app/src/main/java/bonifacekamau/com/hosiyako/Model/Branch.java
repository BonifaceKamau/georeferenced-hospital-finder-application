package bonifacekamau.com.hosiyako.Model;

public class Branch {
    private String Name,Image,Description,Address,Phone,Time,Services,MenuId;

    public Branch() {
    }

    public Branch(String name, String image, String description, String address, String phone, String time, String services, String menuId) {
        Name = name;
        Image = image;
        Description = description;
        Address = address;
        Phone = phone;
        Time = time;
        Services = services;
        MenuId = menuId;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getImage() {
        return Image;
    }

    public void setImage(String image) {
        Image = image;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public String getPhone() {
        return Phone;
    }

    public void setPhone(String phone) {
        Phone = phone;
    }

    public String getTime() {
        return Time;
    }

    public void setTime(String time) {
        Time = time;
    }

    public String getServices() {
        return Services;
    }

    public void setServices(String services) {
        Services = services;
    }

    public String getMenuId() {
        return MenuId;
    }

    public void setMenuId(String menuId) {
        MenuId = menuId;
    }
}
