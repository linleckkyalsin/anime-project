import java.time.LocalDate;
import java.util.Objects;

public class Anime {
    private final String title;
    private final float rating;
    private final Genre genre;
    private final LocalDate releasedDate;

    public Anime(String title, float rating, Genre genre, LocalDate releasedDate) {
        this.title = title;
        this.rating = rating;
        this.genre = genre;
        this.releasedDate = releasedDate;
    }

    public String getTitle() {
        return title;
    }

    public float getRating() {
        if (rating >= 0 && rating <= 5) {
            return rating;
        } else {
            throw new IllegalStateException("【レイティングが5以下になければなりません】");
        }


    }

    public Genre getGenre() {
        return genre;
    }

    public LocalDate getReleasedDate() {
        return releasedDate;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Anime)) return false;
        Anime anime = (Anime) o;
        return Float.compare(anime.rating, rating) == 0 && Objects.equals(title, anime.title) && genre == anime.genre && Objects.equals(releasedDate, anime.releasedDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, rating, genre, releasedDate);
    }
}
