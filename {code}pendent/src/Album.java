import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.UUID;

public class Album extends Inventory {
    private String artist;
    private String divider = "-----";


    private Input input = Input.getInstance();

    public Album(){
    };

    public Album(String title, String artist, int year, double dailyRent, boolean rentStatus, LocalDate date){
        super(title, dailyRent, year, rentStatus, date);
        this.artist = artist;
    }

    public Album(String title, String artist, int year, double dailyRent){
        super(title, dailyRent, year);
        this.artist = artist;
    }

        public String toString( ){
        return divider + "\nID: " +this.getID() + "\nAlbum: " + this.getTitle() + "\nArtist: " + this.getArtist() + ". " + "\nReleased year: " +
                this.getYear() + ". " + "\nDaily Price: " + this.getDailyRent() + " SEK. \nStatus: " + this.getRentStatus() + "\nRating: " + this.averageRating() ;
 }

    public String getID(){ return super.getId(); }

    public String getTitle(){
        return super.getTitle();
    }

    public String getArtist(){
        return artist;
    }

    public int getYear(){
        return super.getYear();
    }
    public double getRating(){
        return super.averageRating();
    }

    public double getDailyRent(){
        return super.getDailyRent();
    }

    public LocalDate getRentedDate() { return super.getRentedDate(); }

    public String getRentStatus(){
        if(super.isRentStatus()){
            return "unavailable";
        }else return "available";
    }

    public void setRentStatus(Boolean rentStatus){
        super.setRentStatus(rentStatus);
    }

    public void setRentedDate(LocalDate rentedDate){ super.setRentedDate(rentedDate); }


    public Album addAlbum(){
        String addTitle = input.getInput("Title: ");
        String addArtist = input.getInput("Artist: ");
        int addYear = input.getInt("Year: ");
        double addDailyRent = input.getDouble("Daily Rent amount: ");
        Album album = new Album(addTitle, addArtist, addYear, addDailyRent);
        return album;
    }

    private String genAlbumID() {
        String generatedID = UUID.randomUUID().toString();
        return generatedID;
    }

}
