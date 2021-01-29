package filmsproject.soapwsdl;

import java.util.ArrayList;
import java.util.List;

import webproject.filmreview.soapresources.Episode;
import webproject.filmreview.soapresources.EpisodeID;
import webproject.filmreview.soapresources.EpisodePojo;
import webproject.filmreview.soapresources.InvalidInputException_Exception;
import webproject.filmreview.soapresources.NotAuthorizedException_Exception;
import webproject.filmreview.soapresources.NotFoundException_Exception;
import webproject.filmreview.soapresources.Season;
import webproject.filmreview.soapresources.SeasonID;
import webproject.filmreview.soapresources.SeasonPojo;
import webproject.filmreview.soapresources.SeriesID;
import webproject.filmreview.soapresources.SeriesPojo;
import webproject.filmreview.soapresources.SoapFilms;
import webproject.filmreview.soapresources.SoapFilmsService;
import webproject.filmreview.soapresources.SoapResponse;
import webproject.filmreview.soapresources.TokenPojo;
import webproject.filmreview.soapresources.SeasonPojo.EpisodesList;
import webproject.filmreview.soapresources.SeriesPojo.Genres;
import webproject.filmreview.soapresources.SeriesPojo.SeasonsList;;

public class SoapSeriesClientPoster 
{

    private SoapFilmsService service;
    private SoapFilms port;
    private static final String adminToken = "5js2PstOpYJ7uZebUWnjYv0se44+ep0ttjAHA9gKG5M=";

    public SoapSeriesClientPoster()
    {
        this.service = new SoapFilmsService();
        this.port = this.service.getSoapFilmsPort();
    }
    
    public static void main(String[] args) 
    {
        SoapSeriesClientPoster client = new SoapSeriesClientPoster();
        //client.addSeries();
        //client.updateSeries("2");
       // client.deleteSeries("2");
       //client.addSeason("1");
       //client.addEpisode("1", "2");
      // client.updateSeason("1", "2");
      client.deleteSeason("1", "2");
    }

    public void addSeries()
    {
        TokenPojo token = new TokenPojo();
        token.setToken(adminToken);
        SeriesPojo seriesToAdd = new SeriesPojo();
        seriesToAdd.setId(0);
        seriesToAdd.setDescription("New series description");
        seriesToAdd.setName("New series");
        seriesToAdd.setRating((float)5.6);
        List<String> genres = new ArrayList<>();
        genres.add("drama");
        genres.add("horror");
        Genres value = new Genres();
        value.setGenre(genres);
        seriesToAdd.setGenres(value);
        SeriesPojo.SeasonsList seasons = new SeasonsList();
        List<Season> emptyList = new ArrayList<>();
        seasons.setSeason(emptyList);
        seriesToAdd.setSeasonsList(seasons);
        try
        {
            SoapResponse response = port.addSeries(seriesToAdd, token);
            System.out.println(response.getDetails());
        }
        catch(InvalidInputException_Exception | NotAuthorizedException_Exception e)
        {
            System.out.println(e.getLocalizedMessage());
        }
    }

    public void updateSeries(String seriesId)
    {
        TokenPojo token = new TokenPojo();
        token.setToken(adminToken);
        SeriesPojo seriesToUpdate = new SeriesPojo();
        seriesToUpdate.setId(Long.valueOf(seriesId));
        seriesToUpdate.setDescription("New updated series description");
        seriesToUpdate.setName("New updated series");
        seriesToUpdate.setRating((float)7.8);
        List<String> genres = new ArrayList<>();
        genres.add("comedy");
        genres.add("horror");
        Genres value = new Genres();
        value.setGenre(genres);
        seriesToUpdate.setGenres(value);
        SeriesPojo.SeasonsList seasons = new SeasonsList();
        List<Season> emptyList = new ArrayList<>();
        seasons.setSeason(emptyList);
        seriesToUpdate.setSeasonsList(seasons);
        try
        {
            SoapResponse response = port.updateSeries(seriesToUpdate, token);
            System.out.println(response.getDetails());
        }
        catch(InvalidInputException_Exception | NotAuthorizedException_Exception | NotFoundException_Exception e)
        {
            System.out.println(e.getLocalizedMessage());
        }
    }

    public void deleteSeries(String seriesId)
    {
        SeriesID seriesID = new SeriesID();
        seriesID.setId(seriesId);
        TokenPojo token = new TokenPojo();
        token.setToken(adminToken);
        try
        {
            SoapResponse response = port.deleteSeries(seriesID, token);
            System.out.println(response.getDetails());
        }
        catch(InvalidInputException_Exception | NotAuthorizedException_Exception | NotFoundException_Exception e)
        {
            System.out.println(e.getLocalizedMessage());
        }
    }

    public void addSeason(String seriesId)
    {
        SeriesID seriesID = new SeriesID();
        seriesID.setId(seriesId);
        TokenPojo token = new TokenPojo();
        token.setToken(adminToken);
        SeasonPojo seasonToAdd = new SeasonPojo();
        seasonToAdd.setDescription("Latest season");
        seasonToAdd.setId(0);
        seasonToAdd.setSeasonNumber(2);
        seasonToAdd.setMainActors("Stalone, Arnold");
        seasonToAdd.setRating((float) 4.6);
        seasonToAdd.setSeriesId(Long.valueOf(seriesId));
        SeasonPojo.EpisodesList episodes = new EpisodesList();
        List<Episode> epsList = new ArrayList<>();
        episodes.setEpisode(epsList);
        seasonToAdd.setEpisodesList(episodes);
        try
        {
            SoapResponse response = port.postSeasonToSeries(seriesID, seasonToAdd, token);
            System.out.println(response.getDetails());
        }
        catch(InvalidInputException_Exception | NotAuthorizedException_Exception e)
        {
            System.out.println(e.getLocalizedMessage());
        }
    }

    public void updateSeason(String seriesId, String seasonId)
    {
        TokenPojo token = new TokenPojo();
        token.setToken(adminToken);
        SeriesID seriesID = new SeriesID();
        seriesID.setId(seriesId);
        SeasonPojo seasonToUpdate = new SeasonPojo();
        seasonToUpdate.setDescription("Latest season updated");
        seasonToUpdate.setId(Long.valueOf(seasonId));
        seasonToUpdate.setSeasonNumber(2);
        seasonToUpdate.setMainActors("Stalone, Arnold, Bruce Willis");
        seasonToUpdate.setRating((float) 10);
        seasonToUpdate.setSeriesId(Long.valueOf(seriesId));
        SeasonPojo.EpisodesList episodes = new EpisodesList();
        List<Episode> epsList = new ArrayList<>();
        episodes.setEpisode(epsList);
        seasonToUpdate.setEpisodesList(episodes);
        try
        {
            SoapResponse response = port.updateSeasonOfSeries(seriesID, seasonToUpdate, token);
            System.out.println(response.getDetails());
        }
        catch(InvalidInputException_Exception | NotAuthorizedException_Exception | NotFoundException_Exception e)
        {
            System.out.println(e.getLocalizedMessage());
        }
    }

    public void deleteSeason(String seriesId, String seasonId)
    {
        TokenPojo token = new TokenPojo();
        token.setToken(adminToken);
        SeriesID seriesID = new SeriesID();
        seriesID.setId(seriesId);
        SeasonID seasonID = new SeasonID();
        seasonID.setId(seasonId);
        try
        {
            SoapResponse response = port.deleteSeasonOfSeries(seriesID, seasonID, token);
            System.out.println(response.getDetails());
        }
        catch(InvalidInputException_Exception | NotAuthorizedException_Exception | NotFoundException_Exception e)
        {
            System.out.println(e.getLocalizedMessage());
        }
    }

    public void addEpisode(String seriesId, String seasonId)
    {
        SeriesID seriesID = new SeriesID();
        seriesID.setId(seriesId);
        SeasonID seasonID = new SeasonID();
        seasonID.setId(seasonId);
        TokenPojo token = new TokenPojo();
        token.setToken(adminToken);
        EpisodePojo episodeToAdd = new EpisodePojo();
        episodeToAdd.setId(0);
        episodeToAdd.setDescription("Latest episode.");
        episodeToAdd.setNumber(1);
        episodeToAdd.setRating((float) 9.9);
        episodeToAdd.setReleaseDate("20/12/2019");
        episodeToAdd.setSeasonId(Long.valueOf(seasonId));
        try
        {
            SoapResponse response = port.postEpisode(seriesID, seasonID, episodeToAdd, token);
            System.out.println(response.getDetails());
        }
        catch(InvalidInputException_Exception | NotAuthorizedException_Exception e)
        {
            System.out.println(e.getLocalizedMessage());
        }
    }

    public void updateEpisode(String seriesId, String seasonId, String episodeId)
    {
        SeriesID seriesID = new SeriesID();
        seriesID.setId(seriesId);
        SeasonID seasonID = new SeasonID();
        seasonID.setId(seasonId);
        TokenPojo token = new TokenPojo();
        token.setToken(adminToken);
        EpisodePojo episodeToUpdate = new EpisodePojo();
        episodeToUpdate.setId(Long.valueOf(episodeId));
        episodeToUpdate.setDescription("Latest episode updated.");
        episodeToUpdate.setNumber(1);
        episodeToUpdate.setRating((float) 2.0);
        episodeToUpdate.setReleaseDate("22/12/2019");
        episodeToUpdate.setSeasonId(Long.valueOf(seasonId));
        try
        {
            SoapResponse response = port.updateEpisode(seriesID, seasonID, episodeToUpdate, token);
            System.out.println(response.getDetails());
        }
        catch(InvalidInputException_Exception | NotAuthorizedException_Exception | NotFoundException_Exception e)
        {
            System.out.println(e.getLocalizedMessage());
        }
    }

    public void deleteEpisode(String seriesId, String seasonId, String episodeId)
    {
        SeriesID seriesID = new SeriesID();
        seriesID.setId(seriesId);
        SeasonID seasonID = new SeasonID();
        seasonID.setId(seasonId);
        TokenPojo token = new TokenPojo();
        token.setToken(adminToken);
        EpisodeID episodeID = new EpisodeID();
        episodeID.setId(episodeId);
        try
        {
            SoapResponse response = port.deleteEpisode(seriesID, seasonID, episodeID, token);
            System.out.println(response.getDetails());
        }
        catch(InvalidInputException_Exception | NotAuthorizedException_Exception | NotFoundException_Exception e)
        {
            System.out.println(e.getLocalizedMessage());
        }
    }

}