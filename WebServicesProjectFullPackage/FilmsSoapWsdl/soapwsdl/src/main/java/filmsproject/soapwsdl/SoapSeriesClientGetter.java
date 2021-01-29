package filmsproject.soapwsdl;

import java.util.List;

import webproject.filmreview.soapresources.Episode;
import webproject.filmreview.soapresources.EpisodeID;
import webproject.filmreview.soapresources.EpisodePojo;
import webproject.filmreview.soapresources.Genres;
import webproject.filmreview.soapresources.InvalidInputException_Exception;
import webproject.filmreview.soapresources.NotFoundException_Exception;
import webproject.filmreview.soapresources.Season;
import webproject.filmreview.soapresources.SeasonID;
import webproject.filmreview.soapresources.SeasonPojo;
import webproject.filmreview.soapresources.Series;
import webproject.filmreview.soapresources.SeriesID;
import webproject.filmreview.soapresources.SeriesListPojo;
import webproject.filmreview.soapresources.SeriesPojo;
import webproject.filmreview.soapresources.SoapFilms;
import webproject.filmreview.soapresources.SoapFilmsService;

public class SoapSeriesClientGetter 
{

    private SoapFilmsService service;
    private SoapFilms port;

    public SoapSeriesClientGetter()
    {
        this.service = new SoapFilmsService();
        this.port = this.service.getSoapFilmsPort();
    }
    
    public static void main(String[] args) 
    {
        SoapSeriesClientGetter client = new SoapSeriesClientGetter();
        //client.getAllSeries();
        //client.getSeriesById("1");
        //client.getSeasonById("1", "1");
        client.getEpisodeById("1", "1", "2");
    }

    public void getAllSeries()
    {
        SeriesListPojo seriesList = port.getAllSeries();
        SeriesListPojo.SeriesList series = seriesList.getSeriesList();
        List<Series> sList = series.getSeries();
        System.out.println("Resulting series list: ");
        for(Series s : sList)
        {
            System.out.println("Id: " + s.getId());
            System.out.println("Name: " + s.getName());
            System.out.println("Description: " + s.getDescription());
            System.out.println("Rating: " + s.getRating());
            String genres = "Genres: ";
            List<Genres> gnrs = s.getGenres();
            for(Genres g : gnrs)
            {
                genres = genres + g.name() + ", ";
            }
            System.out.println(genres);
            System.out.println("Seasons list for series with Id=" + s.getId() + ": {");
            List<Season> sesList = s.getSeasons();
            if(sesList.size() == 0)
            {
                continue;
            }
            for(Season ses: sesList)
            {
                System.out.println("  Season Id: " + ses.getId());
                System.out.println("  Description: " + ses.getDescription());
                System.out.println("  Main actors: " + ses.getMainActors());
                System.out.println("  Rating: " + ses.getRating());
                System.out.println("  Season number: " + ses.getSeasonNumber());
                List<Episode> epsList = ses.getEpisodes();
                if(epsList.size() == 0)
                {
                    continue;
                }
                System.out.println("  Episode list for season with Id=" + ses.getId() + ": [");
                for(Episode eps: epsList)
                {
                    System.out.println("    Episode Id: " + eps.getId());
                    System.out.println("    Episode number: " + eps.getNumber());
                    System.out.println("    Rating: " + eps.getRating());
                    System.out.println("    Description: " + eps.getDescription());
                    System.out.println("    Release date: " + eps.getReleaseDate());
                }
                System.out.println("  ]");
            }
            System.out.println("}");
            System.out.println("\n");
        }
    }

    public void getSeriesById(String seriesId)
    {
        SeriesID seriesID = new SeriesID();
        seriesID.setId(seriesId);
        try
        {
            SeriesPojo s = port.getSeriesById(seriesID);
            System.out.println("Result: ");
            System.out.println("Id: " + s.getId());
            System.out.println("Name: " + s.getName());
            System.out.println("Description: " + s.getDescription());
            System.out.println("Rating: " + s.getRating());
            String genres = "Genres: ";
            SeriesPojo.Genres gens = s.getGenres();
            List<String> gnrs = gens.getGenre();
            for(String g : gnrs)
            {
                genres = genres + g + ", ";
            }
            System.out.println(genres);
            System.out.println("Seasons list for series with Id=" + s.getId() + ": {");
            SeriesPojo.SeasonsList seasons = s.getSeasonsList();
            List<Season> sesList = seasons.getSeason();
            if(sesList.size() == 0)
            {
                return;
            }
            for(Season ses: sesList)
            {
                System.out.println("  Season Id: " + ses.getId());
                System.out.println("  Description: " + ses.getDescription());
                System.out.println("  Main actors: " + ses.getMainActors());
                System.out.println("  Rating: " + ses.getRating());
                System.out.println("  Season number: " + ses.getSeasonNumber());
                List<Episode> epsList = ses.getEpisodes();
                if(epsList.size() == 0)
                {
                    continue;
                }
                System.out.println("  Episode list for season with Id=" + ses.getId() + ": [");
                for(Episode eps: epsList)
                {
                    System.out.println("    Episode Id: " + eps.getId());
                    System.out.println("    Episode number: " + eps.getNumber());
                    System.out.println("    Rating: " + eps.getRating());
                    System.out.println("    Description: " + eps.getDescription());
                    System.out.println("    Release date: " + eps.getReleaseDate());
                }
                System.out.println("  ]");
            }
            System.out.println("}");
            
        }
        catch(InvalidInputException_Exception | NotFoundException_Exception e)
        {
            System.out.println(e.getLocalizedMessage());
        }
    }

    public void getSeasonById(String seriesId, String seasonId)
    {
        SeriesID seriesID = new SeriesID();
        seriesID.setId(seriesId);
        SeasonID seasonID = new SeasonID();
        seasonID.setId(seasonId);
        try
        {
            SeasonPojo ses = port.getSeasonById(seriesID, seasonID);
            System.out.println("Result: ");
            System.out.println("  Season Id: " + ses.getId());
            System.out.println("  Description: " + ses.getDescription());
            System.out.println("  Main actors: " + ses.getMainActors());
            System.out.println("  Rating: " + ses.getRating());
            System.out.println("  Season number: " + ses.getSeasonNumber());
            SeasonPojo.EpisodesList episodes = ses.getEpisodesList();
            List<Episode> epsList = episodes.getEpisode();
            if(epsList.size() == 0)
            {
                return;
            }
            System.out.println("  Episode list for season with Id=" + ses.getId() + ": [");
            for(Episode eps: epsList)
            {
                System.out.println("    Episode Id: " + eps.getId());
                System.out.println("    Episode number: " + eps.getNumber());
                System.out.println("    Rating: " + eps.getRating());
                System.out.println("    Description: " + eps.getDescription());
                System.out.println("    Release date: " + eps.getReleaseDate());
            }
            System.out.println("  ]");

        }
        catch(InvalidInputException_Exception | NotFoundException_Exception e)
        {
            System.out.println(e.getLocalizedMessage());
        }
    }

    public void getEpisodeById(String seriesId, String seasonId, String episodeId)
    {
        SeriesID seriesID = new SeriesID();
        seriesID.setId(seriesId);
        SeasonID seasonID = new SeasonID();
        seasonID.setId(seasonId);
        EpisodeID episodeID = new EpisodeID();
        episodeID.setId(episodeId);
        try
        {
            EpisodePojo eps = port.getEpisodeById(seriesID, seasonID, episodeID);
            System.out.println("Result: ");
            System.out.println("    Episode Id: " + eps.getId());
            System.out.println("    Episode number: " + eps.getNumber());
            System.out.println("    Rating: " + eps.getRating());
            System.out.println("    Description: " + eps.getDescription());
            System.out.println("    Release date: " + eps.getReleaseDate());
        }
        catch(InvalidInputException_Exception | NotFoundException_Exception e)
        {
            System.out.println(e.getLocalizedMessage());
        }
    }

}