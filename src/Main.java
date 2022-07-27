import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.toList;

public class Main {
    public static void main(String[] args){
        List<Anime> animes = List.of(
                new Anime("ドクターストーン",5, Genre.ACTION, LocalDate.of(2012,2,6)),
                new Anime("進撃の巨人", 4.5F,Genre.ACTION,LocalDate.of(2011,1,5)),
                new Anime("ホリミヤ",5, Genre.ROMANCE,LocalDate.of(2021,2,26)),
                new Anime("その 着せ替え人形 ビスク・ドール は恋をする",5,Genre.ROMANCE,LocalDate.of(2021,10,9)),
                new Anime("日常",3, Genre.COMEDY,LocalDate.of(2020,12,6)),
                new Anime("銀魂",3.5F,Genre.COMEDY,LocalDate.of(2006,4,15)),
                new Anime("Haikyuu",4,Genre.SPORT,LocalDate.of(2014,4,9)),
                new Anime("Haikyuu Season 2",4.5F,Genre.SPORT,LocalDate.of(2015,12,3))
        );
        System.out.println("【すべてのアニメを表示する】");
        animes.forEach(anime -> System.out.printf("Title: %s, Genre: %s, Rating: %s, Release_Date: %s\n", anime.getTitle(), anime.getGenre(), anime.getRating(), anime.getReleasedDate().format(DateTimeFormatter.ofPattern("yyyy年MM月dd日"))));
        List<Anime> comedies = animes.stream().filter(anime -> anime.getGenre().equals(Genre.COMEDY)).collect(toList());
        System.out.println("【コメデイアニメを表示する"+"("+comedies.stream().count()+")"+"】");
        comedies.stream().forEach(comedy -> System.out.printf("Title: %s, Genre: %s, Rating: %s, Release_Date: %s\n",comedy.getTitle(),comedy.getGenre(),comedy.getRating(),comedy.getReleasedDate().format(DateTimeFormatter.ofPattern("yyyy年MM月dd日"))));
        System.out.println("【ジャンルに沿ってアニメを表示する】");
        Map<Genre,List<Anime>> groupByGenre = animes.stream().collect(groupingBy(Anime::getGenre,toList()));

        groupByGenre.forEach((genre, animes1) ->
                {
                    System.out.println(genre+"("+animes1.stream().count()+")");
                    animes1.forEach(anime -> System.out.printf("Title: %s, Genre: %s, Rating: %s, Release_Date: %s\n", anime.getTitle(), anime.getGenre(), anime.getRating(), anime.getReleasedDate().format(DateTimeFormatter.ofPattern("yyyy年MM月dd日"))));
                }
        );

        Map<Genre, List<Anime>> sortedGroupByGenre = animes.stream().collect(groupingBy(Anime::getGenre, LinkedHashMap::new, toList()));
        System.out.println("【ジャンルに沿ってアニメをジャンルの昇順に並び替えて表示する");
        sortedGroupByGenre.forEach((genre, animes2) ->
                {
                    System.out.println(genre+"("+animes2.stream().count()+")");
                    animes2.forEach(anime -> System.out.printf("Title: %s, Genre: %s, Rating: %s, Release_Date: %s\n", anime.getTitle(), anime.getGenre(), anime.getRating(), anime.getReleasedDate().format(DateTimeFormatter.ofPattern("yyyy年MM月dd日"))));
                }
        );
        System.out.println("【レイティングが一番高いアニメ一覧を表示する】");

        Anime highestRate = animes.stream().max(Comparator.comparing(Anime::getRating)).orElseThrow(IllegalStateException::new);
        animes.stream().filter(anime -> anime.getRating()==highestRate.getRating()).collect(toList()).forEach(anime -> System.out.printf("Title: %s, Genre: %s, Rating: %s, Release_Date: %s\n", anime.getTitle(), anime.getGenre(), anime.getRating(), anime.getReleasedDate().format(DateTimeFormatter.ofPattern("yyyy年MM月dd日"))));

        Scanner scanner = new Scanner(System.in);
        System.out.println("【見たいアニメは何でしょうか】");
        String name = scanner.nextLine();

        List<Anime> test = animes.stream().filter(anime -> anime.getTitle().toLowerCase().contains(name.toLowerCase())).collect(toList());
        System.out.println("【検索したアニメの結果"+"("+test.stream().count()+")】");
        if (test.isEmpty()){
            System.out.println("【\uD83E\uDD7A検索した"+name+"が見つかりませんでした】");
        }
        else {

            test.forEach(anime -> System.out.printf("Title: %s, Genre: %s, Rating: %s, Release_Date: %s\n", anime.getTitle(), anime.getGenre(), anime.getRating(), anime.getReleasedDate().format(DateTimeFormatter.ofPattern("yyyy年MM月dd日"))) );
        }
        Scanner scanner1 = new Scanner(System.in);
        System.out.println("【見たいアニメの種類は何でしょうか】");
        String genreInput = scanner1.nextLine();

        Map<Genre, List<Anime>> collect = animes.stream().filter(anime -> anime.getGenre().toString().toLowerCase().contains(genreInput.toLowerCase())).collect(groupingBy(Anime::getGenre));
        if (collect.isEmpty()){
            System.out.println("【\uD83E\uDD7A検索した"+genreInput+"の作品が見つかりませんでした】");
        }
        else {

            collect.forEach((genre, animes3) -> {
                System.out.println("【検索したアニメの結果"+"("+animes3.stream().count()+")"+"】");
                System.out.println(genre+"("+animes3.stream().count()+")");
                animes3.stream().forEach(anime ->System.out.printf("Title: %s, Genre: %s, Rating: %s, Release_Date: %s\n", anime.getTitle(), anime.getGenre(), anime.getRating(), anime.getReleasedDate().format(DateTimeFormatter.ofPattern("yyyy年MM月dd日"))));

            });
        }


        boolean isRatingAbove3 = animes.stream().allMatch(anime -> anime.getRating() > 3);
        if (!isRatingAbove3){
            System.out.println("【すべてのアニメのレイティングが3超過】");
        }
        else {
            System.out.printf("【3未満のレイティングのアニメもあります】");
        }

    }
}
