package webproject.filmreview.Utilities;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import webproject.filmreview.Models.Episode;
import webproject.filmreview.Models.Genres;
import webproject.filmreview.Models.Movie;
import webproject.filmreview.Models.Season;
import webproject.filmreview.Models.Series;
import webproject.filmreview.Models.User;
import webproject.filmreview.Models.WatchesEpisode;
import webproject.filmreview.Models.WatchesMovie;
import webproject.filmreview.Models.WatchesSeason;
import webproject.filmreview.Models.WatchesSeries;

public class WatchTrackerService {

    private Map<Long, User> users = Database.getAllUsers();
    private Map<Long, Movie> movies = Database.getAllMovies();
    private Map<Long, Series> seriesMap = Database.getAllSeries();

    public WatchTrackerService()
    {
        String sDate1="31/12/1998";  
        Date date1 = null;
        try 
        {
            date1 = new SimpleDateFormat("dd/MM/yyyy").parse(sDate1);
        } 
        catch (ParseException e) 
        {
            e.printStackTrace();
        }
        if(!date1.equals(null))
        {
            List<Genres> genres = new ArrayList<>();
            genres.add(Genres.ACTION);
            genres.add(Genres.THRILLER);
            Movie mov = new Movie(Long.valueOf(1), "Aviator", date1, (float)5.7, "meh it's fine", "DiCaprio, Elena", genres);
            movies.put(Long.valueOf(1), mov);
        }
        String sDate2 = "12/11/2002";
        Date date2 = null;
        try
        {
            date2 = new SimpleDateFormat("dd/MM/yyyy").parse(sDate2);
        }
        catch(ParseException e)
        {
            e.printStackTrace();
        }
        String sDate3 = "15/09/2005";
        Date date3 = null;
        try
        {
            date3 = new SimpleDateFormat("dd/MM/yyyy").parse(sDate3);
        }
        catch(ParseException e)
        {
            e.printStackTrace();
        }
        List<Season> seasonsList = new ArrayList<>();
        List<Episode> episodesList = new ArrayList<>();
        List<Genres> gnrs = new ArrayList<>();
        gnrs.add(Genres.BIOGRAPHY);
        gnrs.add(Genres.DRAMA);
        Series s = new Series(Long.valueOf(1), "Hopkins", (float)6.3, "disasta", seasonsList, gnrs);
        Season ss = new Season(Long.valueOf(1), 1, s.getId(), (float)3.3, "mahahaha", episodesList, "Jonathan, Diana");
        Episode ep1 = new Episode(1, ss.getId(), (float)5.4, "dasdasd", date2, 1);
        Episode ep2 = new Episode(2, ss.getId(), (float)2.2, "r23ew", date3, 2);
        episodesList.add(ep1);
        episodesList.add(ep2);
        ss.setEpisodes(episodesList);
        seasonsList.add(ss);
        s.setSeasons(seasonsList);
        seriesMap.put(s.getId(), s);
    }

    public Map<Long, Series> getSeriesMap()
    {
        return this.seriesMap;
    }

    public Map<Long, Movie> getMoviesMap()
    {
        return this.movies;
    }

    public Map<Long, User> getUsersMap()
    {
        return this.users;
    }

    public List<WatchesMovie> getWatchingMovies(long userId)
    {
        User user = users.get(userId);
        if(user.equals(null))
        {
            return null;
        }
        List<WatchesMovie> lis = user.getWatchedMoviesList();
        return lis;
    }

    public List<WatchesMovie> getFinishedWatchingMovies(long userId)
    {
        User user = users.get(userId);
        if(user.equals(null))
        {
            return null;
        }
        List<WatchesMovie> lis = user.getWatchedMoviesList();
        List<WatchesMovie> finishedList = new ArrayList<>();
        if(lis.size() == 0)
        {
            return finishedList;
        }
        for(WatchesMovie m : lis)
        {
            if(m.getStatus())
            {
                finishedList.add(m);
            }
        }
        return finishedList;
    }

    public WatchesMovie getFinishedMovieById(long userId, long movieId)
    {
        User user = users.get(userId);
        if(user.equals(null))
        {
            return null;
        }
        List<WatchesMovie> lis = user.getWatchedMoviesList();
        if(lis.size() == 0)
        {
            return null;
        }
        for(WatchesMovie m : lis)
        {
            if(m.getMovieId() == movieId)
            {
                if(m.getStatus())
                {
                    return m;
                }
            }
        }
        return null;
    }

    public WatchesMovie getWatchingMovieById(long userId, long movieId)
    {
        User user = users.get(userId);
        if(user.equals(null))
        {
            return null;
        }
        List<WatchesMovie> lis = user.getWatchedMoviesList();
        if(lis.size() == 0)
        {
            return null;
        }
        else
        {
            for(WatchesMovie m : lis)
            {
                if(m.getMovieId() == movieId)
                {
                    return m;
                }
            }
            return null;
        }
    }

    public boolean addMovieToWatchList(long userId, long movieId)
    {
        User user = users.get(userId);
        if(user.equals(null))
        {
            return false;
        }
        List<WatchesMovie> watchMoviesList = user.getWatchedMoviesList();
        Movie movie = movies.get(movieId);
        if(movie == null)
        {
            return false;
        }
        for(WatchesMovie movieWatch : watchMoviesList)
        {
            if(movieWatch.getMovieId() == movieId)
            {
                return false;
            }
        }
        WatchesMovie watchAdd = new WatchesMovie(watchMoviesList.size()+1, userId, movieId);
        watchMoviesList.add(watchAdd);
        user.setWatchedMoviesList(watchMoviesList);
        users.put(userId, user);
        return true;
    }

    public boolean setMovieAsFinished(long userId, long movieId, String finishDate)
    {
        Date fDate = null;
        try 
        {
            fDate = new SimpleDateFormat("dd/MM/yyyy").parse(finishDate);
        } 
        catch (ParseException e) 
        {
            e.printStackTrace();
        }
        if(fDate.equals(null))
        {
            return false;
        }
        User user = users.get(userId);
        if(user.equals(null))
        {
            return false;
        }
        Movie movie = movies.get(movieId);
        if(movie.equals(null))
        {
            return false;
        }
        Date release = movie.getReleaseDate();
        List<WatchesMovie> watchMoviesList = user.getWatchedMoviesList();
        int i = 0;
        for(WatchesMovie movieWatch : watchMoviesList)
        {
            if(movieWatch.getMovieId() == movieId)
            {
                if(release.after(fDate))
                {
                    return false;
                }
                else 
                {
                    movieWatch.setAsFinished(fDate);
                    watchMoviesList.set(i, movieWatch);
                    user.setWatchedMoviesList(watchMoviesList);
                    users.put(userId, user);
                    return true;
                }
            }
            i++;
        }
        return false;
    }

    public boolean deleteMovieFromWatchList(long userId, long movieId)
    {
        User user = users.get(userId);
        if(user.equals(null))
        {
            return false;
        }
        List<WatchesMovie> watchMoviesList = user.getWatchedMoviesList();
        for(WatchesMovie movieWatch : watchMoviesList)
        {
            if(movieWatch.getMovieId() == movieId)
            {
                watchMoviesList.remove(movieWatch);
                user.setWatchedMoviesList(watchMoviesList);
                users.put(userId, user);
                return true;
            }
        }
        return false;
    }

    public List<WatchesSeries> getWatchingSeries(long userId)
    {
        User user = users.get(userId);
        if(user.equals(null))
        {
            return null;
        }
        List<WatchesSeries> seriesList = user.getWatchedSeriesList();
        return seriesList;
    }

    public WatchesSeries getWatchingSeriesById(long userId, long seriesId)
    {
        User user = users.get(userId);
        if(user.equals(null))
        {
            return null;
        }
        List<WatchesSeries> seriesList = user.getWatchedSeriesList();
        if(seriesList.size() == 0)
        {
            return null;
        }
        else
        {
            for(WatchesSeries s : seriesList)
            {
                if(s.getSeriesId() == seriesId)
                {
                    return s;
                }
            }
            return null;
        }
    }

    public List<WatchesSeries> getFinishedSeries(long userId)
    {
        List<WatchesSeries> finishList = new ArrayList<>();
        User user = users.get(userId);
        if(user.equals(null))
        {
            return finishList;
        }
        List<WatchesSeries> seriesList = user.getWatchedSeriesList();
        if(seriesList.size() == 0)
        {
            return finishList;
        }
        for(WatchesSeries s : seriesList)
        {
            if(s.getStatus())
            {
                finishList.add(s);
            }
        }
        return finishList;
    }

    public List<WatchesSeason> getFinishedSeasonsFromSeries(long userId, long seriesId)
    {
        User user = users.get(userId);
        List<WatchesSeason> finishList = new ArrayList<>();
        if(user.equals(null))
        {
            return finishList;
        }
        List<WatchesSeries> seriesList = user.getWatchedSeriesList();
        if(seriesList.size() == 0)
        {
            return finishList;
        }
        for(WatchesSeries s : seriesList)
        {
            if(s.getSeriesId() == seriesId)
            {
                List<WatchesSeason> sesList = s.getSeasons();
                for(WatchesSeason ss : sesList)
                {
                    if(ss.getStatus())
                    {
                        finishList.add(ss);
                    }
                }
            }
        }
        return finishList;
    }

    public List<WatchesEpisode> getFinishedEpisodesFromSeasonOfSeries(long userId, long seriesId, long seasonId)
    {
        List<WatchesEpisode> finishedList = new ArrayList<>();
        User user = users.get(userId);
        if(user.equals(null))
        {
            return finishedList;
        }
        List<WatchesSeries> seriesList = user.getWatchedSeriesList();
        if(seriesList.size() == 0)
        {
            return finishedList;
        }
        for(WatchesSeries s : seriesList)
        {
            if(s.getSeriesId() == seriesId)
            {
                List<WatchesSeason> sesList = s.getSeasons();
                if(sesList.size() == 0)
                {
                    return finishedList;
                }
                for(WatchesSeason ss : sesList)
                {
                    if(ss.getSeasonId() == seasonId)
                    {
                        List<WatchesEpisode> epsList = ss.getEpisodes();
                        if(epsList.size() == 0)
                        {
                            return finishedList;
                        }
                        for(WatchesEpisode p : epsList)
                        {
                            if(p.getStatus())
                            {
                                finishedList.add(p);
                            }
                        }
                    }
                }
            }
        }
        return finishedList;
    }

    public WatchesEpisode getFinishedEpisode(long userId, long seriesId, long seasonId, long episodeId)
    {
        User user = users.get(userId);
        if(user.equals(null))
        {
            return null;
        }
        List<WatchesSeries> seriesList = user.getWatchedSeriesList();
        if(seriesList.size() == 0)
        {
            return null;
        }
        for(WatchesSeries s : seriesList)
        {
            if(s.getSeriesId() == seriesId)
            {
                List<WatchesSeason> sesList = s.getSeasons();
                if(sesList.size() == 0)
                {
                    return null;
                }
                for(WatchesSeason ss : sesList)
                {
                    if(ss.getSeasonId() == seasonId)
                    {
                        List<WatchesEpisode> epsList = ss.getEpisodes();
                        if(epsList.size() == 0)
                        {
                            return null;
                        }
                        for(WatchesEpisode p : epsList)
                        {
                            if(p.getEpisodeId() == episodeId)
                            {
                                if(p.getStatus())
                                {
                                    return p;
                                }
                            }
                        }
                    }
                }
            }
        }
        return null;
    }

    public WatchesEpisode getLastWatchedEpisodeFromSeries(long userId, long seriesId)
    {
        User user = users.get(userId);
        if(user.equals(null))
        {
            return null;
        }
        List<WatchesSeries> seriesList = user.getWatchedSeriesList();
        if(seriesList.size() == 0)
        {
            return null;
        }
        for(WatchesSeries s : seriesList)
        {
            if(s.getSeriesId() == seriesId)
            {
                List<WatchesSeason> seasonsList = s.getSeasons();
                if(seasonsList.size() == 0)
                {
                    return null;
                }
                for(WatchesSeason ss : seasonsList)
                {
                    if(!ss.getStatus())
                    {
                        List<WatchesEpisode> episodesList = ss.getEpisodes();
                        if(episodesList.size() == 0)
                        {
                            return null;
                        }
                        for(WatchesEpisode ep : episodesList)
                        {
                            if(!ep.getStatus())
                            {
                                return ep;
                            }
                        }
                    }
                }
                WatchesSeason sea = seasonsList.get(seasonsList.size()-1);
                List<WatchesEpisode> ep = sea.getEpisodes();
                WatchesEpisode last = ep.get(ep.size()-1);
                if(last.getStatus())
                {
                    return last;
                }
            }
        }
        return null;
    }

    public WatchesEpisode getLastWatchedEpisodeFromSeriesUsingDate(long userId, long seriesId)
    {
        User user = users.get(userId);
        if(user.equals(null))
        {
            return null;
        }
        List<WatchesSeries> seriesList = user.getWatchedSeriesList();
        if(seriesList.size() == 0)
        {
            return null;
        }
        for(WatchesSeries s : seriesList)
        {
            if(s.getSeriesId() == seriesId)
            {
                List<WatchesSeason> seasonsList = s.getSeasons();
                if(seasonsList.size() == 0)
                {
                    return null;
                }
                for(WatchesSeason ss : seasonsList)
                {
                    if(!ss.getStatus())
                    {
                        List<WatchesEpisode> episodesList = ss.getEpisodes();
                        if(episodesList.size() == 0)
                        {
                            return null;
                        }
                        WatchesEpisode lastEpisode = episodesList.get(0);
                        for(WatchesEpisode ep : episodesList)
                        {
                            if(ep.getFinishedAt().after(lastEpisode.getFinishedAt()))
                            {
                                lastEpisode = ep;
                            }
                        }
                        return lastEpisode;
                    }
                }
                WatchesSeason sea = seasonsList.get(seasonsList.size()-1);
                List<WatchesEpisode> ep = sea.getEpisodes();
                WatchesEpisode last = ep.get(ep.size()-1);
                if(last.getStatus())
                {
                    return last;
                }
            }
        }
        return null;
    }

    public boolean addSeriesToWatchList(long userId, long seriesId)
    {
        User user = users.get(userId);
        if(user.equals(null))
        {
            return false;
        }
        List<WatchesSeries> watchSeriesList = user.getWatchedSeriesList();
        Series series = seriesMap.get(seriesId);
        if(series.equals(null))
        {
            return false;
        }
        for(WatchesSeries s : watchSeriesList)
        {
            if(s.getSeriesId() == seriesId)
            {
                return false;
            }
        }
        List<WatchesSeason> seasons = new ArrayList<>();
        WatchesSeries watchAdd = new WatchesSeries(watchSeriesList.size()+1, userId, seriesId, seasons);
        watchSeriesList.add(watchAdd);
        user.setWatchedSeriesList(watchSeriesList);
        users.put(userId, user);
        return true;
    }

    public WatchesSeason getWatchingSeasonById(long userId, long seriesId, long seasonId)
    {
        User user = users.get(userId);
        if(user.equals(null))
        {
            return null;
        }
        List<WatchesSeries> seriesList = user.getWatchedSeriesList();
        if(seriesList.size() == 0)
        {
            return null;
        }
        for(WatchesSeries s : seriesList)
        {
            if(s.getSeriesId() == seriesId)
            {
                List<WatchesSeason> seasonsList = s.getSeasons();
                if(seasonsList.size() == 0)
                {
                    return null;
                }
                for(WatchesSeason ss : seasonsList)
                {
                    if(ss.getSeasonId() == seasonId)
                    {
                        return ss;
                    }
                }
            }
        }
        return null;
    }

    public boolean addSeasonToWatchList(long userId, long seriesId, long seasonId)
    {
        User user = users.get(userId);
        if(user.equals(null))
        {
            return false;
        }
        Series series = seriesMap.get(seriesId);
        if(series.equals(null))
        {
            return false;
        }
        List<Season> seasonsList = series.getSeasons();
        Season ses = null;
        for(Season s : seasonsList)
        {
            if(s.getId() == seasonId)
            {
                ses = s;
            }
        }
        if(ses.equals(null))
        {
            return false;
        }
        List<WatchesSeries> watchSeriesList = user.getWatchedSeriesList();
        int i = 0;
        for(WatchesSeries s : watchSeriesList)
        {
            if(s.getSeriesId() == seriesId)
            {
                List<WatchesSeason> watchSeasons = s.getSeasons();
                for(WatchesSeason sea : watchSeasons)
                {
                    if(sea.getSeasonId() == seasonId)
                    {
                        return false;
                    }
                }
                List<WatchesEpisode> episList = new ArrayList<>();
                WatchesSeason ss = new WatchesSeason(watchSeasons.size()+1, s.getId(), seasonId, episList);
                watchSeasons.add(ss);
                s.setSeasons(watchSeasons);
                watchSeriesList.set(i, s);
                user.setWatchedSeriesList(watchSeriesList);
                users.put(userId, user);
                return true;
            }
            i++;
        }
        return false;
    }

    public WatchesEpisode getWatchingEpisodeById(long userId, long seriesId, long seasonId, long episodeId)
    {
        User user = users.get(userId);
        if(user.equals(null))
        {
            return null;
        }
        List<WatchesSeries> seriesList = user.getWatchedSeriesList();
        if(seriesList.size() == 0)
        {
            return null;
        }
        for(WatchesSeries s : seriesList)
        {
            if(s.getSeriesId() == seriesId)
            {
                List<WatchesSeason> seasonsList = s.getSeasons();
                if(seasonsList.size() == 0)
                {
                    return null;
                }
                for(WatchesSeason ss : seasonsList)
                {
                    if(ss.getSeasonId() == seasonId)
                    {
                        List<WatchesEpisode> episodesList = ss.getEpisodes();
                        if(episodesList.size() == 0)
                        {
                            return null;
                        }
                        for(WatchesEpisode ep : episodesList)
                        {
                            if(ep.getEpisodeId() == episodeId)
                            {
                                return ep;
                            }
                        }
                    }
                }
            }
        }
        return null;
    }

    public boolean addEpisodeToWatchList(long userId, long seriesId, long seasonId, long episodeId)
    {
        User user = users.get(userId);
        if(user.equals(null))
        {
            return false;
        }
        Series series = seriesMap.get(seriesId);
        if(series.equals(null))
        {
            return false;
        }
        List<Season> seasonsList = series.getSeasons();
        Season ses = null;
        for(Season s : seasonsList)
        {
            if(s.getId() == seasonId)
            {
                ses = s;
            }
        }
        if(ses.equals(null))
        {
            return false;
        }
        Episode p = null;
        List<Episode> episodesList = ses.getEpisodes();
        for(Episode ep : episodesList)
        {
            if(ep.getId() == episodeId)
            {
                p = ep;
            }
        }
        if(p.equals(null))
        {
            return false;
        }
        List<WatchesSeries> seriesWatchList = user.getWatchedSeriesList();
        int j = 0;
        for(WatchesSeries s : seriesWatchList)
        {
            if(s.getSeriesId() == seriesId)
            {
                List<WatchesSeason> seasonsWatchList = s.getSeasons();
                int i = 0;
                for(WatchesSeason ss : seasonsWatchList)
                {
                    if(ss.getSeasonId() == seasonId)
                    {
                        List<WatchesEpisode> epsList = ss.getEpisodes();
                        for(WatchesEpisode epa : epsList)
                        {
                            if(epa.getEpisodeId() == episodeId)
                            {
                                return false;
                            }
                        }
                        WatchesEpisode toAdd = new WatchesEpisode(epsList.size()+1, ss.getId(), episodeId);
                        epsList.add(toAdd);
                        ss.setEpisodes(epsList);
                        seasonsWatchList.set(i, ss);
                        s.setSeasons(seasonsWatchList);
                        seriesWatchList.set(j, s);
                        user.setWatchedSeriesList(seriesWatchList);
                        users.put(userId, user);
                        return true;
                    }
                    i++;
                }
            }
            j++;
        }
        return false;
    }

    public boolean setEpisodeAsFinished(long userId, long seriesId, long seasonId, long episodeId, String finishDate)
    {
        Date fDate = null;
        try 
        {
            fDate = new SimpleDateFormat("dd/MM/yyyy").parse(finishDate);
        } 
        catch (ParseException e) 
        {
            e.printStackTrace();
        }
        User user = users.get(userId);
        if(user.equals(null))
        {
            return false;
        }
        Series series = seriesMap.get(Long.valueOf(seriesId));
        if(series == null)
        {
            return false;
        }
        List<Season> seasonsList = series.getSeasons();
        Season ses = null;
        for(Season s : seasonsList)
        {
            if(s.getId() == seasonId)
            {
                ses = s;
            }
        }
        if(ses == null)
        {
            return false;
        }
        Episode p = null;
        List<Episode> episodesList = ses.getEpisodes();
        for(Episode ep : episodesList)
        {
            if(ep.getId() == episodeId)
            {
                p = ep;
            }
        }
        if(p == null)
        {
            return false;
        }
        Date release = p.getReleaseDate();
        List<WatchesSeries> watchList = user.getWatchedSeriesList();
        int k = 0;
        for(WatchesSeries s : watchList)
        {
            if(s.getSeriesId() == seriesId)
            {
                List<WatchesSeason> sList = s.getSeasons();
                int j = 0;
                for(WatchesSeason ss : sList)
                {
                    if(ss.getSeasonId() == seasonId)
                    {
                        List<WatchesEpisode> epsList = ss.getEpisodes();
                        int i = 0;
                        for(WatchesEpisode ep : epsList)
                        {
                            if(ep.getEpisodeId() == episodeId)
                            {
                                if(release.after(fDate))
                                {
                                    return false;
                                }
                                else 
                                {
                                    ep.setAsFinished(fDate);
                                    epsList.set(i, ep);
                                    ss.setEpisodes(epsList);
                                    sList.set(j, ss);
                                    s.setSeasons(sList);
                                    watchList.set(k, s);
                                    user.setWatchedSeriesList(watchList);
                                    users.put(userId, user);
                                    return true; 
                                }
                            }
                            i++;
                        }
                    }
                }
                j++;
            }
            k++;
        }
        return false;
    }

    public boolean setSeasonAsFinished(long userId, long seriesId, long seasonId, String finishDate)
    {
        Date fDate = null;
        try 
        {
            fDate = new SimpleDateFormat("dd/MM/yyyy").parse(finishDate);
        } 
        catch (ParseException e) 
        {
            e.printStackTrace();
        }
        User user = users.get(userId);
        if(user.equals(null))
        {
            return false;
        }
        Series series = seriesMap.get(Long.valueOf(seriesId));
        if(series == null)
        {
            return false;
        }
        List<Season> seasonsList = series.getSeasons();
        Season ses = null;
        for(Season s : seasonsList)
        {
            if(s.getId() == seasonId)
            {
                ses = s;
            }
        }
        if(ses == null)
        {
            return false;
        }
        int count = ses.getEpisodes().size();
        List<WatchesSeries> watchList = user.getWatchedSeriesList();
        int j = 0;
        for(WatchesSeries s : watchList)
        {
            if(s.getSeriesId() == seriesId)
            {
                List<WatchesSeason> watchesSeasons = s.getSeasons();
                int i = 0;
                for(WatchesSeason ss : watchesSeasons)
                {
                    if(ss.getSeasonId() == seasonId)
                    {
                        if(ss.getEpisodes().size() == count)
                        {
                            boolean flag = true;
                            List<WatchesEpisode> watchesEpisodes = ss.getEpisodes();
                            for(WatchesEpisode eps : watchesEpisodes)
                            {
                                if(!eps.getStatus())
                                {
                                    flag = false;
                                }
                                else if(eps.getFinishedAt().after(fDate))
                                {
                                    flag = false;
                                }
                            }
                            if(flag)
                            {
                                ss.setAsFinished(fDate);
                                watchesSeasons.set(i, ss);
                                s.setSeasons(watchesSeasons);
                                watchList.set(j, s);
                                user.setWatchedSeriesList(watchList);
                                users.put(userId, user);
                                return true;
                            }
                        }
                    }
                    i++;
                }
            }
            j++;
        }
        return false;
    }

    public boolean setSeriesAsFinished(long userId, long seriesId, String finishDate)
    {
        Date fDate = null;
        try 
        {
            fDate = new SimpleDateFormat("dd/MM/yyyy").parse(finishDate);
        } 
        catch (ParseException e) 
        {
            e.printStackTrace();
        }
        User user = users.get(userId);
        if(user.equals(null))
        {
            return false;
        }
        Series series = seriesMap.get(Long.valueOf(seriesId));
        if(series == null)
        {
            return false;
        }
        int count = series.getSeasons().size();
        List<WatchesSeries> watchList = user.getWatchedSeriesList();
        int i = 0;
        for(WatchesSeries s : watchList)
        {
            if(s.getId() == seriesId)
            {
                List<WatchesSeason> seasonsList = s.getSeasons();
                if(seasonsList.size() == count)
                {
                    boolean flag = true;
                    for(WatchesSeason ss : seasonsList)
                    {
                        if(!ss.getStatus())
                        {
                            flag = false;
                        }
                        else if(ss.getFinishedAt().after(fDate))
                        {
                            flag = false;
                        }
                    }
                    if(flag)
                    {
                        s.setAsFinished(fDate);
                        watchList.set(i, s);
                        user.setWatchedSeriesList(watchList);
                        users.put(userId, user);
                        return true;
                    }
                }
            }
            i++;
        }
        return false;
    }

    public boolean removeSeriesFromWatchList(long userId, long seriesId)
    {
        User user = users.get(userId);
        if(user.equals(null))
        {
            return false;
        }
        List<WatchesSeries> seriesList = user.getWatchedSeriesList();
        for(WatchesSeries s : seriesList)
        {
            if(s.getSeriesId() == seriesId)
            {
                seriesList.remove(s);
                user.setWatchedSeriesList(seriesList);
                users.put(userId, user);
                return true;
            }
        }
        return false;
    }

    public boolean removeSeasonFromWatchList(long userId, long seriesId, long seasonId)
    {
        User user = users.get(userId);
        if(user.equals(null))
        {
            return false;
        }
        List<WatchesSeries> seriesList = user.getWatchedSeriesList();
        int i = 0;
        for(WatchesSeries s : seriesList)
        {
            if(s.getSeriesId() == seriesId)
            {
                List<WatchesSeason> seasonsList = s.getSeasons();
                for(WatchesSeason ss : seasonsList)
                {
                    if(ss.getSeasonId() == seasonId)
                    {
                        seasonsList.remove(ss);
                        s.setSeasons(seasonsList);
                        seriesList.set(i, s);
                        user.setWatchedSeriesList(seriesList);
                        users.put(userId, user);
                        return true;
                    }
                }
            }
            i++;
        }
        return false;
    }

    public boolean removeEpisodeFromWatchList(long userId, long seriesId, long seasonId, long episodeId)
    {
        User user = users.get(userId);
        if(user.equals(null))
        {
            return false;
        }
        List<WatchesSeries> seriesList = user.getWatchedSeriesList();
        int i = 0;
        for(WatchesSeries s : seriesList)
        {
            if(s.getSeriesId() == seriesId)
            {
                List<WatchesSeason> seasonsList = s.getSeasons();
                int j = 0;
                for(WatchesSeason ss : seasonsList)
                {
                    if(ss.getSeasonId() == seasonId)
                    {
                        List<WatchesEpisode> epsLis = ss.getEpisodes();
                        for(WatchesEpisode ep : epsLis)
                        {
                            if(ep.getEpisodeId() == episodeId)
                            {
                                epsLis.remove(ep);
                                ss.setEpisodes(epsLis);
                                seasonsList.set(j, ss);
                                s.setSeasons(seasonsList);
                                seriesList.set(i, s);
                                user.setWatchedSeriesList(seriesList);
                                users.put(userId, user);
                                return true;
                            }
                        }
                    }
                    j++;
                }
            }
            i++;
        }
        return false;
    }

}